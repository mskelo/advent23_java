/**
 * Advent of Code 2023
 * Day 15
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        // I/O
        try   { lines = Files.readAllLines(Paths.get("./input")); } 
        catch ( IOException e ) { e.printStackTrace(); }
        String line = lines.get(0);
        int part1 = Arrays.asList(line.split(","))
                                    .stream()
                                    .mapToInt(str -> str.chars()
                                                        .reduce(0, (acc, element) -> ((acc + element)*17)%256))
                                    .sum();

        System.out.println("Advent of Code 2023 // Day 15 // Matej Skelo");
        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " );
    }
} 
