import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day6 {
    public static String TEST_INPUT_FILE = "day06/test_input.txt";
    public static String REAL_INPUT_FILE = "day06/input.txt";

    public static class informations {
        public int thisLinesIndex;
        public int thisCharIndex;
        public String thisDirection;

        public informations(int linesIndex, int charIndex, String direction) {
            this.thisLinesIndex = linesIndex;
            this.thisCharIndex = charIndex;
            this.thisDirection = direction;
        }
    }

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
        List<String> inputLines = lines();
        int count = 0;
        boolean event = false;
        while(!event) {
            informations infos = CheckForArrows(inputLines);
            if (infos.thisLinesIndex == -1 && infos.thisCharIndex == -1) {
                event = true;
                continue;
            }
            String line = inputLines.get(infos.thisLinesIndex);
            StringBuilder prevLine = new StringBuilder(line);
            prevLine.setCharAt(infos.thisCharIndex, 'X');
            inputLines.set(infos.thisLinesIndex, prevLine.toString());
            switch (infos.thisDirection) {
                case "right":
                    prevLine.setCharAt(infos.thisCharIndex + 1, '>');
                case "left":
                    prevLine.setCharAt(infos.thisCharIndex - 1, '<');
                case "up":
                    String temp = inputLines.get(infos.thisLinesIndex - 1);
                    StringBuilder newLine = new StringBuilder(temp);
                    newLine.setCharAt(infos.thisCharIndex, '^');
                case "down":
                    temp = inputLines.get(infos.thisLinesIndex + 1);
                    newLine = new StringBuilder(temp);
                    newLine.setCharAt(infos.thisCharIndex, 'v');
                    inputLines.set(infos.thisLinesIndex - 1, temp);
                    inputLines.set(infos.thisLinesIndex + 1, newLine.toString());
                    break;
            }
            count++;
            System.out.println("Direction: " + infos.thisDirection);
        }
        return count;
    }

    public static int part2() throws IOException{
        return 0;
    }

    public static informations CheckForArrows(List<String> inputLines) throws IOException{
        String direction = "";
        int safedLinesIndex = -1;
        int safedCharsIndex = -1;
        for (int linesIndex = 0; linesIndex < inputLines.size(); linesIndex++) {
            String line = inputLines.get(linesIndex);
            for (int charsIndex = 0; charsIndex < line.length(); charsIndex++) {
                switch (line.charAt(charsIndex)) {
                    case '>':
                        direction = "right";
                        safedCharsIndex = charsIndex;
                        safedLinesIndex = linesIndex;
                        break;
                    case '<':
                        direction = "left";
                        safedCharsIndex = charsIndex;
                        safedLinesIndex = linesIndex;
                        break;
                    case '^':
                        direction = "up";
                        safedCharsIndex = charsIndex;
                        safedLinesIndex = linesIndex;
                        break;
                    case 'v':
                        direction = "down";
                        safedCharsIndex = charsIndex;
                        safedLinesIndex = linesIndex;
                        break;
                }
            }
        }
        System.out.println("Line: "+ (safedLinesIndex+1) + ", Char: "+ (safedCharsIndex+1));
        return new informations(safedLinesIndex, safedCharsIndex, direction);
    }
}