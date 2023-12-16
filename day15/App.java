/**
 * Advent of Code 2023
 * Day 15
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class App {
    static final int MAX_BOXES = 256;

    public static int HASH(String str) {
        return str.chars().reduce(0, (acc, element) -> ((acc + element) * 17) % 256);
    }

    public static Map<Integer, Map<String, Integer>> arrangeLenses(String[] line) {
        // Initialize 256 empty boxes
        Map<Integer, Map<String, Integer>> boxes = 
            IntStream.range(0, MAX_BOXES)
                     .boxed()
                     .collect(Collectors.toMap(
                         index -> index,
                         index -> new LinkedHashMap<>()
                     ));

        for (String lens : line) {
            String label = lens.split("[-=]")[0];
            Map<String, Integer> box = boxes.get(HASH(label));

            if      (lens.contains("-"))  box.remove(label);
            else if (lens.contains("=")) 
            {
                int focal_length = Integer.parseInt(lens.split("[-=]")[1]);
                if (box.containsKey(label)) box.replace(label, box.get(label), focal_length);
                else                        box.put(label, focal_length);
            }
        }
        return boxes;
    }

    public static void main(String[] args) {
        // I/O
        String line = "";
        try   { line = Files.readAllLines(Paths.get("./input")).get(0); } 
        catch ( IOException e ) { e.printStackTrace(); }

        // ez
        int part1 = Arrays.asList(line.split(","))
                          .stream()
                          .mapToInt(str -> HASH(str))
                          .sum();
        
        
        Map<Integer, Map<String, Integer>> boxes = arrangeLenses(line.split(","));

        int part2 = 0;
        for (int i = 1; i <= MAX_BOXES; i++) {
            Map<String, Integer> box = boxes.get(i-1);
            if (box == null) continue;
            
            List<Integer> values = new ArrayList<>(box.values());
            for (int j = 1; j<=values.size(); j++) {
                part2 += i*j*values.get(j-1);
            }
        }

        System.out.println("Advent of Code 2023 // Day 15 // Matej Skelo");
        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }
}
