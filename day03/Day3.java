import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day3 {
    public static final String TEST_INPUT_FILE = "day03/test_input.txt";
    public static final String TEST_INPUT2_FILE = "day03/test_input2.txt";
    public static final String REAL_INPUT_FILE = "day03/input.txt";
    static final Pattern mulPattern = Pattern.compile("(mul\\(\\d{1,3},\\d{1,3}\\))|(do\\(\\))|(don't\\(\\))");
    static final Pattern pattern = Pattern.compile("(mul\\(\\d{1,3},\\d{1,3}\\))");
    static String FILE_CACHE = null;

    public static void main(String[] args) throws IOException {
        System.out.println("Part 2: " + part2());
        System.out.println("Part 1: " + part1());
    }

    private static int multiply(String mul) {
        mul = mul.replaceAll("mul", "").replaceAll("\\(", "").replaceAll("\\)", "");
        String[] split = mul.split(",");
        return Integer.parseInt(split[0]) * Integer.parseInt(split[1]);
    }

    public static int part1() {
        int sum = 0;
        Matcher matcher = pattern.matcher(loadFile());

        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                sum += multiply(matcher.group(i));
            }
        }
        return sum;
    }

    public static int part2() {
        int sum = 0;
        boolean enabled = true;
        Matcher mulMatcher = mulPattern.matcher(loadFile());
        while (mulMatcher.find()) {
            if (mulMatcher.group().equals("do()")) {
                enabled = true;
            } else if (mulMatcher.group().equals("don't()")) {
                enabled = false;
            } else if (enabled) {
                sum += multiply(mulMatcher.group());
            }
        }
        return sum;
    }

    public static String loadFile() {
        String path = REAL_INPUT_FILE;
        if (FILE_CACHE == null) {
            try (BufferedReader reader = Files.newBufferedReader(Path.of(path))) {
                FILE_CACHE = reader.lines()
                        .collect(Collectors.joining());

            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }

        return FILE_CACHE;
    }
}