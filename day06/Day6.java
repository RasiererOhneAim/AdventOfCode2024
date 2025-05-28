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
        List<String> inputLines = lines();
        List<String> tempLines = inputLines;
        int count = 0;
        boolean event = false;
        while (!event) {
            boolean move = false;
            informations infos = CheckForArrows(tempLines);
            if (infos.thisLinesIndex == -1 && infos.thisCharIndex == -1) {
                event = true;
                continue;
            }
            String line = tempLines.get(infos.thisLinesIndex);
            StringBuilder prevLine = new StringBuilder(line);
            switch (infos.thisDirection) {
                case "right":
                    if (infos.thisCharIndex < prevLine.length() - 1) {
                        if (prevLine.charAt(infos.thisCharIndex + 1) == '#') {
                            prevLine.setCharAt(infos.thisCharIndex, 'v');
                            tempLines.set(infos.thisLinesIndex, prevLine.toString());
                        } else {
                            if (prevLine.charAt(infos.thisCharIndex + 1) == 'X') {
                                count--;
                            }
                            prevLine.setCharAt(infos.thisCharIndex + 1, '>');
                            tempLines.set(infos.thisLinesIndex, prevLine.toString());
                            move = true;
                        }
                    } else {
                        event = true;
                        move = true;
                    }
                    break;
                case "left":
                    if (infos.thisCharIndex > 0) {
                        if (prevLine.charAt(infos.thisCharIndex - 1) == '#') {
                            prevLine.setCharAt(infos.thisCharIndex, '^');
                            tempLines.set(infos.thisLinesIndex, prevLine.toString());
                        } else {
                            if (prevLine.charAt(infos.thisCharIndex - 1) == 'X') {
                                count--;
                            }
                            prevLine.setCharAt(infos.thisCharIndex - 1, '<');
                            tempLines.set(infos.thisLinesIndex, prevLine.toString());
                            move = true;
                        }
                    } else {
                        event = true;
                        move = true;
                    }
                    break;
                case "up":
                    if (infos.thisLinesIndex > 0) {
                        String temp3 = tempLines.get(infos.thisLinesIndex - 1);
                        StringBuilder newLine3 = new StringBuilder(temp3);
                        if (temp3.charAt(infos.thisCharIndex) == '#') {
                            prevLine.setCharAt(infos.thisCharIndex, '>');
                            tempLines.set(infos.thisLinesIndex, prevLine.toString());
                        } else {
                            if (temp3.charAt(infos.thisCharIndex) == 'X') {
                                count--;
                            }
                            newLine3.setCharAt(infos.thisCharIndex, '^');
                            tempLines.set(infos.thisLinesIndex - 1, newLine3.toString());
                            move = true;
                        }
                    } else {
                        event = true;
                        move = true;
                    }
                    break;
                case "down":
                    if (infos.thisLinesIndex < tempLines.size() - 1) {
                        String temp4 = tempLines.get(infos.thisLinesIndex + 1);
                        StringBuilder newLine4 = new StringBuilder(temp4);
                        if (temp4.charAt(infos.thisCharIndex) == '#') {
                            prevLine.setCharAt(infos.thisCharIndex, '<');
                            tempLines.set(infos.thisLinesIndex, prevLine.toString());
                        } else {
                            if (temp4.charAt(infos.thisCharIndex) == 'X') {
                                count--;
                            }
                            newLine4.setCharAt(infos.thisCharIndex, 'v');
                            tempLines.set(infos.thisLinesIndex + 1, newLine4.toString());
                            move = true;
                        }
                    } else {
                        event = true;
                        move = true;
                    }

                    break;
            }
            if (move) {
                prevLine.setCharAt(infos.thisCharIndex, 'X');
                tempLines.set(infos.thisLinesIndex, prevLine.toString());
                count++;
            }
        }
        return count;
    }

    public static int part2() throws IOException {
        return 0;
    }

    public static informations CheckForArrows(List<String> inputLines) throws IOException {
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
        return new informations(safedLinesIndex, safedCharsIndex, direction);
    }
}