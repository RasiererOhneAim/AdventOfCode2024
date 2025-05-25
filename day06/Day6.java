import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day6 {
    public static String TEST_INPUT_FILE = "day06/test_input.txt";
    public static String REAL_INPUT_FILE = "day06/input.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("Result part 1: " + part1());
        System.out.println("Result part 2: " + part2());
        System.out.println(lines());
    }

    public static List<String> lines() throws IOException {
        String filePath = TEST_INPUT_FILE;
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        List<String> lineArrays = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            lineArrays.add(line);
        }
        return lineArrays;
    }

    public static int part1() throws IOException{
        return 0;
    }

    public static int part2() throws IOException{
        return 0;
    }

}
