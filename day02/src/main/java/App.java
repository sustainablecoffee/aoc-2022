import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class App {

    private static Map<String, Integer> outcomes;

    static {
        outcomes = new HashMap<>();
        outcomes.put("A X", 4);
        outcomes.put("A Y", 8);
        outcomes.put("A Z", 3);
        outcomes.put("B X", 1);
        outcomes.put("B Y", 5);
        outcomes.put("B Z", 9);
        outcomes.put("C X", 7);
        outcomes.put("C Y", 2);
        outcomes.put("C Z", 6);
    }

    public static int getSolutionPart1(String fileName) throws IOException {
        return Files.lines(Path.of(fileName)).mapToInt(outcomes::get).sum();
    }

    public static int getSolutionPart2(String fileName) throws IOException {
        return Files.lines(Path.of(fileName)).map(App::getOutcome).mapToInt(outcomes::get).sum();
    }

    private static String getOutcome(String s) {
        String[] s1 = s.split(" ");
        String opponent = s1[0], outcome = s1[1];
        if ("Y".equals(outcome)) return opponent + " " + getEven(opponent);
        if ("X".equals(outcome)) return opponent + " " + getLoser(opponent);
        return opponent + " " + getWinner(opponent);
    }

    private static String getEven(String s) {
        if (s.equals("A")) return "X";
        if (s.equals("B")) return "Y";
        return "Z";
    }

    private static String getLoser(String s) {
        if (s.equals("A")) return "Z";
        if (s.equals("B")) return "X";
        return "Y";
    }

    private static String getWinner(String s) {
        if (s.equals("A")) return "Y";
        if (s.equals("B")) return "Z";
        return "X";
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
