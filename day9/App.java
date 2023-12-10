/**
 * Advent of Code 2023
 * Day 9, Part 1
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        // I/O
        List<String> lines = new ArrayList<>();
        try   { lines = Files.readAllLines(Paths.get("./input")); } 
        catch ( IOException e ) { e.printStackTrace(); }
        int sum = 0;
        // Parser
        for (String line : lines) {
            if (!line.isBlank()) {
                List<Integer> numbers = Arrays.stream(line.split(" "))
                                           .mapToInt(i -> Integer.parseInt(i))
                                           .boxed()
                                           .collect(Collectors.toList());
                sum += rec(numbers);
            }
        }

        System.out.println("Advent of Code 2023 // Day 9 // Matej Skelo");
        System.out.println("Part 1: " + sum);
        System.out.println("Part 2: " );
    }

    public static Integer rec(List<Integer> numberRow) {
        List<Integer> diffSequence = new ArrayList<>();
        Iterator iter = numberRow.iterator();
        Integer prevValue = null,
                currValue = 0;
        while (iter.hasNext()) {
            if (prevValue == null) { prevValue = (Integer) iter.next(); continue; } 
            prevValue = currValue;
            currValue = (Integer) iter.next();
            diffSequence.add(currValue-prevValue);
        }
        if (diffSequence.stream().allMatch(i -> i == 0)) return 0;
        else return rec(diffSequence) + currValue; // Thinking with portals
    }
}
