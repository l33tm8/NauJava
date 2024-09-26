package org.naujava2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        File file = new File("Task1Input.txt");
        int[] arr;
        try {
            Scanner scanner = new Scanner(file);
            int n = scanner.nextInt();
            int minNumber = 0;
            int maxNumber = 100;
            arr = new int[n];
            for (int i = 0; i < arr.length; i++)
                arr[i] = (int)(Math.random() * (maxNumber - minNumber  + 1 )) + minNumber;

            double avg = Arrays.stream(arr)
                    .summaryStatistics()
                    .getAverage();
            writeOutput(arr, avg);

        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

    private static void writeOutput(int[] arr, double avg) {
            try (FileOutputStream fos = new FileOutputStream("Task1Output.txt")) {
                fos.write(Arrays.toString(arr).getBytes());
                fos.write("\n".getBytes());
                fos.write(String.valueOf(avg).getBytes());
            } catch (IOException e) {
                System.out.println(e.getMessage());
        }
    }
}