package org.naujava2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        try {
            File file = new File("Task2Input.txt");
            Scanner scanner = new Scanner(file);
            int n = scanner.nextInt();
            ArrayList<Double> randomDoubles = new ArrayList<>();
            for (int i = 0; i < n; i++)
                randomDoubles.add(Math.random());
            ArrayList<Double> sortedArr = new ArrayList<>(randomDoubles);
            bubbleSort(sortedArr);
            writeOutput(randomDoubles, sortedArr);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void bubbleSort(ArrayList<Double> arr) {
        int n = arr.size();
        for (int i = 1; i < n; i++) {
            boolean isSorted = true;
            for (int j = 0; j < n - i; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    double tmp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, tmp);
                    isSorted = false;
                }
            }
            if (isSorted)
                break;
        }
    }

    private static void writeOutput(ArrayList<Double> randomDoubles, ArrayList<Double> sortedArr) {
        try (FileOutputStream fos = new FileOutputStream("Task2Output.txt")) {
            fos.write(randomDoubles.toString().getBytes());
            fos.write("\n".getBytes());
            fos.write(sortedArr.toString().getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
