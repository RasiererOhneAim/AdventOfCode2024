import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main2 {
    public static String TEST_INPUT_FILE_NAME = "day02/test_input.txt";
    public static String REAL_INPUT_FILE_NAME = "day02/input.txt";

    public static void main(String[] args) {
        printList(List());
        System.out.println(checkArrays());
    }

    public static void printList(List<int[]> lineArrays) {
        System.out.println("Line Arrays:");
        for (int[] array : lineArrays) {
            for (int num : array) {
                System.out.print(num + " ");
            }
            System.out.println(); // Move to the next line after printing each array
        }
    }


    public static List<int[]> List() {

        String filePath = TEST_INPUT_FILE_NAME;
        List<int[]> lineArrays = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                int[] numbers = new int[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    numbers[i] = Integer.parseInt(parts[i]);
                }
                lineArrays.add(numbers);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        return lineArrays;
    }

    public static int[] grabArray(List<int[]> lineArrays, int indexToAccess) {
        return lineArrays.get(indexToAccess);
    }

    public static int checkArrays() {
        int safeness = 0;
        for (int i = 0; i < List().size(); i++) {
            int[] row = grabArray(List(), i);
            int puffer = 1;

            if (!checkIfArrayIsAscendingOrDescending(row)) {
                if (puffer != 1) {
                    continue;
                }
                puffer--;
            }

            if (puffer == 1) {
                boolean isSafe = true;
                for (int j = 1; j < row.length; j++) {
                    int diff = Math.abs(row[j - 1] - row[j]);
                    if (diff < 1 || diff > 3) {
                        try {
                            int diff2 = Math.abs(row[j - 1] - row[j + 1]);
                            if ((diff2 > 0 && diff2 < 4)) {
                                break;
                            }
                            puffer--;
                            isSafe = false;
                            break;
                        } catch (Exception e) {
                            puffer--;
                            if (diff != 0) {
                                continue;
                            }
                            isSafe = false;
                            break;
                        }
                    }
                }
                if (isSafe) safeness++;
            }else{
                boolean isSafe = true;
                for (int j = 1; j < row.length; j++) {
                    int diff = Math.abs(row[j - 1] - row[j]);
                    if (diff < 1 || diff > 3) {
                        try {
                            int diff2 = Math.abs(row[j - 2] - row[j]);
                            if ((diff2 > 0 && diff2 < 4)) {
                                break;
                            }
                            puffer--;
                            isSafe = false;
                            break;
                        } catch (Exception e) {
                            puffer--;
                            if (diff != 0) {
                                continue;
                            }
                            isSafe = false;
                            break;
                        }
                    }
                }
                if (isSafe) safeness++;

            }
        }
        return safeness;
    }

    public static boolean checkIfArrayIsAscendingOrDescending(int[] array) {
        Integer[] baseArray = cloneIntArrayToBoxedArray(array);

        Integer[] descendingArray = cloneIntArrayToBoxedArray(array);
        Arrays.sort(descendingArray);

        Integer[] ascendingArray = cloneIntArrayToBoxedArray(array);
        Arrays.sort(ascendingArray, Collections.reverseOrder());

        return (Arrays.equals(baseArray, descendingArray) || Arrays.equals(baseArray, ascendingArray));
    }

    public static Integer[] cloneIntArrayToBoxedArray(int[] array) {
        return Arrays.stream(array).boxed().toArray(Integer[]::new);
    }
}