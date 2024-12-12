public static void main(String[] args) throws IOException {
    List<String> input = Files.readAllLines(Paths.get("day04/input.txt"));
    List<String> test = Files.readAllLines(Paths.get("day04/test_input.txt"));


    part1(test);
    part1(input);
    part2(test);
    part2(input);
}

private static void part1(List<String> in) {
    long result = 0;

    for (int i = 0; i < in.size(); i++) {
        String x = in.get(i);
        int j = x.indexOf('X');
        while (j != -1) {
            result += masOccurence(in, i, j);
            j = x.indexOf('X', j + 1);
        }
    }
    System.out.println("part1: " + result);
}

private static int masOccurence(List<String> in, int i, int j) {
    int score = 0;
    //up
    if (i > 2) {
        if (in.get(i - 1).charAt(j) == 'M' && in.get(i - 2).charAt(j) == 'A' && in.get(i - 3).charAt(j) == 'S') {
            score++;
        }
    }
    //upleft
    if (i > 2 && j > 2) {
        if (in.get(i - 1).charAt(j - 1) == 'M' && in.get(i - 2).charAt(j - 2) == 'A' && in.get(i - 3).charAt(j - 3) == 'S') {
            score++;
        }
    }
    //left
    if (j > 2) {
        String line = in.get(i);
        if (line.charAt(j - 1) == 'M' && line.charAt(j - 2) == 'A' && line.charAt(j - 3) == 'S') {
            score++;
        }
    }
    //downleft
    if (i < in.size() - 3 && j > 2) {
        if (in.get(i + 1).charAt(j - 1) == 'M' && in.get(i + 2).charAt(j - 2) == 'A' && in.get(i + 3).charAt(j - 3) == 'S') {
            score++;
        }
    }
    //down
    if (i < in.size() - 3) {
        if (in.get(i + 1).charAt(j) == 'M' && in.get(i + 2).charAt(j) == 'A' && in.get(i + 3).charAt(j) == 'S') {
            score++;
        }
    }
    //downright
    if (i < in.size() - 3 && j < in.get(0).length() - 3) {
        if (in.get(i + 1).charAt(j + 1) == 'M' && in.get(i + 2).charAt(j + 2) == 'A' && in.get(i + 3).charAt(j + 3) == 'S') {
            score++;
        }
    }
    //right
    if (j < in.get(0).length() - 3) {
        String line = in.get(i);
        if (line.charAt(j + 1) == 'M' && line.charAt(j + 2) == 'A' && line.charAt(j + 3) == 'S') {
            score++;
        }
    }
    //upright
    if (i > 2 && j < in.get(0).length() - 3) {
        if (in.get(i - 1).charAt(j + 1) == 'M' && in.get(i - 2).charAt(j + 2) == 'A' && in.get(i - 3).charAt(j + 3) == 'S') {
            score++;
        }
    }
    return score;
}

private static void part2(List<String> in) {
    long result = 0;

    for (int i = 1; i < in.size(); i++) {
        String x = in.get(i);
        int j = x.indexOf('A');
        while (j != -1) {
            result += xmasOccurence(in, i, j);
            j = x.indexOf('A', j + 1);
        }
    }

    System.out.println("part2: " + result);
}

private static final int xmasOccurence(List<String> in, int i, int j) {
    if (i < 1 || j < 1 || i > in.size() - 2 || j > in.get(0).length() - 2)
        return 0;

    char tl = in.get(i - 1).charAt(j - 1);
    char br = in.get(i + 1).charAt(j + 1);
    char tr = in.get(i - 1).charAt(j + 1);
    char bl = in.get(i + 1).charAt(j - 1);

    boolean b1 = ((tl == 'M' && br == 'S') || (tl == 'S' && br == 'M'));
    boolean b2 = ((tr == 'M' && bl == 'S') || (tr == 'S' && bl == 'M'));
    return (b1 && b2) ? 1 : 0;
}