import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static int getSolutionPart1(List<List<Integer>> input) {
        int largest = 0;

        for (List<Integer> calories : input) {
            int sum = calories.stream()
                    .mapToInt(i -> i)
                    .sum();

            if (sum > largest) {
                largest = sum;
            }
        }

        return largest;
    }

    public static int getSolutionPart2(List<List<Integer>> input) {
        ResultSecondPart result = new ResultSecondPart();

        for (List<Integer> calories : input) {
            int sum = calories.stream()
                    .mapToInt(i -> i)
                    .sum();

            result.add(sum);
        }

        return result.getSumOfThreeLargest();
    }

    public static void main(String[] args) throws IOException {
        String part = System.getenv("part");

        if ("part1".equals(part)) {
            System.out.println(getSolutionPart1(parseInput("input.txt")));
        } else {
            System.out.println(getSolutionPart2(parseInput("input.txt")));
        }
    }

    private static List<List<Integer>> parseInput(String filename) throws IOException {
        List<Integer> calories = new ArrayList<>();
        List<List<Integer>> completeInput = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String s;
        while ((s = reader.readLine()) != null) {
            if (s.isEmpty()) {
                completeInput.add(calories);
                calories = new ArrayList<>();
                continue;
            }

            calories.add(Integer.parseInt(s));
        }

        completeInput.add(calories);
        return completeInput;
    }

    private static class ResultSecondPart {

        int[] largestCalories = {0, 0, 0};

        void add(int calories) {
            if (calories > largestCalories[0]) {
                largestCalories[2] = largestCalories[1];
                largestCalories[1] = largestCalories[0];
                largestCalories[0] = calories;
            } else if (calories > largestCalories[1]) {
                largestCalories[2] = largestCalories[1];
                largestCalories[1] = calories;
            } else if (calories > largestCalories[2]){
                largestCalories[2] = calories;
            }
        }

        int getSumOfThreeLargest() {
            return largestCalories[0] + largestCalories[1] + largestCalories[2];
        }
    }

}
