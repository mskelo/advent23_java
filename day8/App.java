/**
 * Advent of Code 2023
 * Day 8, Part 1
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    private static List<Character> instructions = new ArrayList<>();
    private static Map<String, Tuple<String>> lineValues = new LinkedHashMap<>();
    public static void main(String[] args) {
        // I/O
        List<String> lines = new ArrayList<>();
        try   { lines = Files.readAllLines(Paths.get("./input")); } 
        catch ( IOException e ) { e.printStackTrace(); }

        // Parser
        for (String line : lines) {
            if (!line.isBlank() && !line.contains("=")) {
                instructions = line.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
            }
            else if (!line.isBlank()) {
                String label       = line.split(" = ")[0],
                       firstValue  = line.split("\\(")[1]
                                         .split(", ")[0],
                       secondValue = line.split("\\(")[1]
                                         .split(", ")[1]
                                         .split("\\)")[0];
                lineValues.put(label, new Tuple<>(firstValue, secondValue));
            }
        }  
        System.out.println("Advent of Code 2023 // Day 8 // Matej Skelo");
        System.out.println("Part 1: " + part1());
        System.out.println("Part 2: " );
    }

    public static int part1() {
        final String firstLabel = "AAA",
                     finalLabel = "ZZZ";
        int steps = 0;
        String currentLabel = firstLabel;
        Iterator iter = instructions.iterator();
        while(true) { // Infinite loop over the instructions
            if (!iter.hasNext()) {
                iter = instructions.iterator();
            }
            else {
                steps++;
                boolean instructionL = iter.next().equals('L');
                currentLabel = instructionL ? lineValues.get(currentLabel).getFirst() : 
                                              lineValues.get(currentLabel).getSecond();
                if (currentLabel.equals(finalLabel)) break;
            }
        }
        return steps;
    }
}

// How the fuck does Java not have tuples lol
class Tuple<T> {
    private T firstValue;
    private T secondValue;
    public Tuple(T firstValue, T secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }
    public T getFirst()  { return this.firstValue;  }
    public T getSecond() { return this.secondValue; }
}
