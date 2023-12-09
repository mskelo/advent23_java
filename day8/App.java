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
    public static void main(String[] args) {
        List<String>    lines        = new ArrayList<>();
        List<Character> instructions = new ArrayList<>();
        Map<String, Tuple<String>> lineValues = new LinkedHashMap<>();
        String firstLabel = "AAA",
               finalLabel = "ZZZ";
        int steps = 0;

        // I/O
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
        String currentLabel = firstLabel;
        while (!currentLabel.equals(finalLabel)) {
            Iterator iter = instructions.iterator();
            while(true) { // Infinite loop over the instructions
                if (!iter.hasNext()) {
                    iter = instructions.iterator();
                }
                else {
                    boolean instructionL = (iter.next().equals('L')) ? true : false;
                    steps++;
                    currentLabel = instructionL ? lineValues.get(currentLabel).getFirst() : 
                                                  lineValues.get(currentLabel).getSecond();
                    if (currentLabel.equals(finalLabel)) { break; }
                }
            }
        }
        System.out.println("Advent of Code 2023 // Day 5 // Matej Skelo");
        System.out.println("Part 1: " + steps);
        System.out.println("Part 2: " );
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
