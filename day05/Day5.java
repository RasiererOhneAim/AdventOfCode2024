import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day5 {

    public static String TEST_INPUT_FILE = "day05/test_input.txt";
    public static String REAL_INPUT_FILE = "day05/input.txt";


    public static void main(String[] args) throws IOException {
        System.out.println("Result part 1: " + part1());
        System.out.println("Result part 2: " + part2());
    }

    public static List<String> lines() throws IOException {
        String filePath = REAL_INPUT_FILE;
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        List<String> lineArrays = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            lineArrays.add(line);
        }
        return lineArrays;
    }

    public static int part1() throws IOException {
        List<String> lines = lines();

        Map<String, List<String>> rules = new HashMap<>();
        int result = 0;
        for (String line : lines) {
            if (line.isBlank()) continue;
            if (line.contains("|")) {
                String[] parts = line.split("\\|");
                if (!rules.containsKey(parts[0])) {
                    rules.put(parts[0], new ArrayList<>());
                }
                rules.get(parts[0]).add(parts[1]);
            }
            if (line.contains(",")) {
                Map<String, Integer> lineToCheck = new HashMap<>();
                String[] splitArray = line.split(",");

                for (int i = 0; i < splitArray.length; i++) {
                    lineToCheck.put(splitArray[i], Integer.valueOf(i));
                }

                boolean correct = true;
                for (Map.Entry<String, List<String>> entry : rules.entrySet()) {
                    if (lineToCheck.containsKey(entry.getKey())) {
                        int start = lineToCheck.get(entry.getKey());
                        for (String temp : entry.getValue()) {
                            if (lineToCheck.containsKey(temp) && start > lineToCheck.get(temp)) {
                                correct = false;
                                break;
                            }
                        }
                    }
                }
                if (correct) {
                    result += Integer.parseInt(splitArray[splitArray.length / 2]);
                }
            }
        }
        return result;
    }

    public static int part2() throws IOException {
        List<String> lines = lines();

        Map<String, List<String>> rules = new HashMap<>();
        int result = 0;
        for (String line : lines) {
            if (line.isBlank()) continue;
            if (line.contains("|")) {
                String[] parts = line.split("\\|");
                if (!rules.containsKey(parts[0])) {
                    rules.put(parts[0], new ArrayList<>());
                }
                rules.get(parts[0]).add(parts[1]);
            }
            if (line.contains(",")) {
                String[] splitArray = line.split(",");

                List<String> before = Arrays.asList(splitArray);
                List<String> after = new ArrayList<>();
                after.addAll(Arrays.asList(splitArray));

                after.sort((a, b) -> {
                    if (!rules.containsKey(a)) return 0;
                    List<String> right = rules.get(a);
                    if (right.contains(b)) return -1;
                    return 0;
                });

                if (!before.toString().equals(after.toString())) {
                    result += Integer.parseInt(after.get(after.size() / 2));
                }
            }
        }
        return result;
    }
}