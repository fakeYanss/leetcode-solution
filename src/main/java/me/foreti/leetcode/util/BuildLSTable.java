package me.foreti.leetcode.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BuildLSTable {

    private static final String path = "/Users/fakeyanss/project/leetcode-solution/src/main/java/me/foreti/leetcode";
    private static final String LS_TABLE_FILE = "/Users/fakeyanss/project/leetcode-solution/leetcode-algorithm-table.md";
    private static final String LEETCODE_URL_PREFIX = "https://leetcode.com/problems/";
    private static final String SOLUTION_PREFIX = "./src/main/java/me/foreti/leetcode/";
    private static final String SOLUTION_POSTFIX = "/Solution.java";

    static class Problem {
        public int index;
        public String title;
        public String difficulty;
        public String path;

        Problem(int index, String title, String difficulty, String path) {
            this.index = index;
            this.title = title;
            this.difficulty = difficulty;
            this.path = path;
        }
    }

    // 递归遍历所有文件夹和文件
    private static void trace(File file, List<String> files, List<String> folders) {
        File[] fs = file.listFiles();
        for (File f : fs) {
            if (f.isDirectory()) {
                folders.add(f.toString());
                trace(f, files, folders);
            }
            if (f.isFile()) {
                files.add(f.toString());
            }
        }
    }

    private static void writeMarkdown(List<Problem> problems) {
        StringBuffer sb = new StringBuffer();
        sb.append("## LeetCode\n");
        sb.append("\n");
        sb.append("### LeetCode Algorithm\n");
        sb.append("\n");
        sb.append("| # | Title | Solution  | Difficulty |\n");
        sb.append("| --- | --- | --- | --- |\n");
        for (Problem problem : problems) {
            sb.append("| ");
            sb.append(problem.index);
            sb.append(" | ");
            sb.append(String.format("[%s](%s%s)", problem.title, LEETCODE_URL_PREFIX, problem.title.replace(" ", "-")));
            sb.append(" | ");
            sb.append(String.format("[Java](%s%s%s)", SOLUTION_PREFIX, problem.path, SOLUTION_POSTFIX));
            sb.append(" | ");
            sb.append(problem.difficulty);
            sb.append(" |\n");
        }

        try (BufferedWriter out = new BufferedWriter(new FileWriter(LS_TABLE_FILE))) {
            out.write(sb.toString());
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        List<String> files = new ArrayList<>();
        List<String> folders = new ArrayList<>();
        File file = new File(path);

        trace(file, files, folders);

        Pattern pattern = Pattern.compile(".*/_\\d{1,}_[A-Za-z0-9]{1,}.*");

        List<Problem> problems = folders.stream().filter(f -> pattern.matcher(f).matches()).map(f -> {
            String[] parts = f.split("/");
            String type = parts[parts.length - 2];
            String pkg = parts[parts.length - 1];
            parts = pkg.split("_");

            int index = Integer.valueOf(parts[1]);
            String difficulty = parts[parts.length - 1];
            String title = String.join(" ", Arrays.copyOfRange(parts, 2, parts.length - 1));
            return new Problem(index, title, difficulty, type + "/" + pkg);
        }).sorted(Comparator.comparingInt(p -> p.index)).collect(Collectors.toList());

        // JsonUtils.printJsonPrettyString(problems);
        writeMarkdown(problems);
    }

}
