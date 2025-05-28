import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day1 {
    public static void main(String[] args) {

        String filePath = "day01/input.txt";
        ArrayList<Integer> leftNumbers = new ArrayList<>();
        ArrayList<Integer> rightNumbers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 2) {
                    leftNumbers.add(Integer.valueOf(Integer.parseInt(parts[0])));
                    rightNumbers.add(Integer.valueOf(Integer.parseInt(parts[1])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        int[] leftArray = leftNumbers.stream().mapToInt(Integer::intValue).toArray();
        int[] rightArray = rightNumbers.stream().mapToInt(Integer::intValue).toArray();

        quickSort(leftArray, 0, leftArray.length - 1);
        quickSort(rightArray, 0, rightArray.length - 1);

        System.out.println(compare(leftArray, rightArray));
        System.out.println(simScore(leftArray, rightArray));
    }

    private static void quickSort(int[] arr, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int pivot = arr[end];
        int lp = begin;
        int rp = end;

        while (lp < rp) {
            while (lp < rp && arr[lp] <= pivot) {
                lp++;
            }
            while (lp < rp && arr[rp] >= pivot) {
                rp--;
            }
            swap(arr, lp, rp);
        }
        swap(arr, lp, end);

        quickSort(arr, begin, lp - 1);
        quickSort(arr, lp + 1, end);

    }

    private static void swap(int[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static int compare(int[] arr1, int[] arr2) {
        int distance = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] < arr2[i]) {
                int temp = arr2[i] - arr1[i];
                distance = distance + temp;
            }
            if (arr1[i] == arr2[i]) {
                int temp = arr1[i] - arr2[i];
                distance = distance + temp;
            }
            if (arr1[i] > arr2[i]) {
                int temp = arr1[i] - arr2[i];
                distance = distance + temp;
            }
        }
        return distance;
    }

    private static int simScore(int[] arr1, int[] arr2) {
        int simScore = 0;
        for (int i = 0; i < arr1.length; i++) {
            simScore = simScore + (arr1[i] * value(arr2, arr1[i]));
        }
        return simScore;
    }

    private static int value(int[] arr, int value) {
        int v = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                v++;
            }
        }
        return v;
    }
}