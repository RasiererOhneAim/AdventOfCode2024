import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public static String TEST_INPUT_FILE = "day02/test_input.txt";
public static String REAL_INPUT_FILE = "day02/input.txt";

public static void main(String[] args) {
    printList(list());
    System.out.println(checkList(list()));
}

public static List<int[]> list() {

    String filePath = REAL_INPUT_FILE;
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

public static void printList(List<int[]> lineArrays) {
    System.out.println("Line Arrays:");
    for (int[] array : lineArrays) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

private static boolean isValid(int[] lineArrays) {
    boolean decreasing = false;
    boolean increasing = false;
    for (int i = 1; i < lineArrays.length; i++) {
        long diff = Math.abs(lineArrays[i] - lineArrays[i - 1]);
        if (diff < 1 || diff > 3) {
            return false;
        }

        if (lineArrays[i] > lineArrays[i - 1]) {
            increasing = true;
            if (decreasing) {
                return false;
            }
        } else if (lineArrays[i] < lineArrays[i - 1]) {
            decreasing = true;
            if (increasing) {
                return false;
            }
        }
    }
    return true;
}

public static int checkList(List<int[]> array) {
    int safeness = 0;
    for (int j = 0; j < array.size(); j++) {
        int[] lineArrays = Day2.grabArray(array, j);
        if (isValid(lineArrays)) {
            safeness++;
            continue;
        }

        for (int k = 0; k < lineArrays.length; k++) {
            int[] changed = new int[lineArrays.length - 1];
            for (int i = 0, l = 0; i < lineArrays.length; i++) {
                if (i != k) {
                    changed[l] = lineArrays[i];
                    l++;
                }
            }
            if (isValid(changed)) {
                safeness++;
                break;
            }
        }
    }
    return safeness;
}
