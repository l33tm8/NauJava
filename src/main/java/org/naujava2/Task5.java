package org.naujava2;

import java.nio.file.Path;

public class Task5 {
    public static void main(String[] args) throws InterruptedException {
        Path mainPath = Path.of("mainFolder");
        Path childPath = Path.of("childFolder");
        SynchronizationTask syncTask = new SynchronizationTask(mainPath, childPath);
        Thread t1 = new Thread(syncTask::start);

        t1.start();
        Thread.sleep(5000);
        syncTask.stop();
    }
}
