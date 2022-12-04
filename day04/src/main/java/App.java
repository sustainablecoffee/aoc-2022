import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App {

    public static long getSolutionPart1(String fileName) throws IOException {
        return Files.lines(Path.of(fileName))
                .map(s -> s.split(","))
                .filter(App::oneFullyContainsTheOther)
                .count();
    }

    public static long getSolutionPart2(String fileName) throws IOException {
        return Files.lines(Path.of(fileName))
                .map(s -> s.split(","))
                .filter(App::sectionsOverlap)
                .count();
    }

    private static boolean oneFullyContainsTheOther(String[] strings) {
        String[] str1 = strings[0].split("-"), str2 = strings[1].split("-");
        if (Integer.parseInt(str1[0]) >= Integer.parseInt(str2[0]) && Integer.parseInt(str1[1]) <= Integer.parseInt(str2[1])) {
            return true;
        }
        if (Integer.parseInt(str2[0]) >= Integer.parseInt(str1[0]) && Integer.parseInt(str2[1]) <= Integer.parseInt(str1[1])) {
            return true;
        }
        return false;
    }

    private static boolean sectionsOverlap(String[] strings) {
        String[] str1 = strings[0].split("-"), str2 = strings[1].split("-");
        if (Integer.parseInt(str1[0]) >= Integer.parseInt(str2[0]) && Integer.parseInt(str1[0]) <= Integer.parseInt(str2[1])) {
            return true;
        }
        if (Integer.parseInt(str1[1]) >= Integer.parseInt(str2[0]) && Integer.parseInt(str1[0]) <= Integer.parseInt(str2[1])) {
            return true;
        }
        if (Integer.parseInt(str2[0]) >= Integer.parseInt(str1[0]) && Integer.parseInt(str2[0]) <= Integer.parseInt(str1[1])) {
            return true;
        }
        if (Integer.parseInt(str2[1]) >= Integer.parseInt(str1[0]) && Integer.parseInt(str2[0]) <= Integer.parseInt(str1[1])) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        String part = System.getenv("part");
        if ("part1".equals(part)) {
            System.out.println(getSolutionPart1("input.txt"));
        } else {
            System.out.println(getSolutionPart2("input.txt"));
        }
    }

}
