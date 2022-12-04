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

    public static int getSolutionPart1(String fileName) throws IOException {
        return Files.lines(Path.of(fileName))
                .map(str -> str.split(""))
                .map(App::findSameCharacter)
                .mapToInt(App::getPriority)
                .sum();
    }

    public static int getSolutionPart2(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        List<List<String>> lists = new ArrayList<>();
        List<String> list = new ArrayList<>();
        String str;
        int count = 1;
        while ((str = reader.readLine()) != null) {
            if (count == 4) {
                lists.add(list);
                list = new ArrayList<>();
                count = 1;
            }
            list.add(str);
            count++;
        }
        lists.add(list);

        return lists.stream()
                .map(App::findSameCharacter)
                .mapToInt(App::getPriority)
                .sum();
    }

   private static char findSameCharacter(String[] str) {
       HashSet<String> firstHalf = new HashSet<>();
       HashSet<String> secondHalf = new HashSet<>();
       int i = 0, j = str.length - 1;
       while (true) {
           firstHalf.add(str[i]);
           secondHalf.add(str[j]);
           if (firstHalf.contains(str[j])) return str[j].charAt(0);
           if (secondHalf.contains(str[i])) return str[i].charAt(0);
           i++;
           j--;
           if (i == j) return str[i].charAt(0);
       }
   }

    private static char findSameCharacter(List<String> strings) {
        return Arrays.stream(strings.get(2).split(""))
                .filter(s -> strings.get(0).contains(s) && strings.get(1).contains(s))
                .findFirst()
                .map(s -> s.charAt(0))
                .get();
    }

    private static int getPriority(char c) {
       if (c >= 65 && c <= 90) return c - 38;
       return c -96;
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
