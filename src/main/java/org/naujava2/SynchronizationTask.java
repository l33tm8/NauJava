package org.naujava2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SynchronizationTask implements Task {

    private volatile boolean isRunning = false;
    private final Path mainPath;
    private final Path childPath;

    public SynchronizationTask(Path mainPath, Path childPath) {
        this.mainPath = mainPath;
        this.childPath = childPath;
    }

    @Override
    public void start() {
        isRunning = true;

        while (isRunning) {
            List<Path> mainPathFiles = new ArrayList<>();
            List<Path> childPathFiles = new ArrayList<>();
            addPathToList(mainPath, mainPathFiles);
            addPathToList(childPath, childPathFiles);
            try {
                List<Path> filesToSync = getDifference(mainPathFiles, childPathFiles);
                for (Path path : filesToSync) {
                    System.out.println("copying " + path.toString());
                    Path targetPath = childPath.resolve(path.getFileName());
                    Files.copy(path, targetPath, StandardCopyOption.COPY_ATTRIBUTES);

                }
                childPathFiles.clear();
                addPathToList(childPath, childPathFiles);
                List<Path> filesToDelete = getDifference(childPathFiles, mainPathFiles);
                for (Path path : filesToDelete) {
                    System.out.println("deleting " + path.toString());
                    Files.delete(path);
                }
            }
            catch (IOException e) {
                System.out.println("error with " + childPath.toString());
            }
        }
    }

    @Override
    public void stop() {
        this.isRunning = false;
    }

    private void addPathToList(Path path, List<Path> list) {
        try (Stream<Path> paths = Files.walk(path)) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(list::add);
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    private List<Path> getDifference(List<Path> list1, List<Path> list2) {
        return list1.stream()
                .filter(element -> {
                    for (Path path : list2) {
                        if (element.getFileName().equals(path.getFileName())) {
                            return false;
                        }
                    }
                    return true;
                })
                .toList();
    }

}
