/**
 * Advent of Code 2023
 * Day 9, Part 1
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
        // I/O
        List<String> lines = new ArrayList<>();
        try   { lines = Files.readAllLines(Paths.get("./calibration")); } 
        catch ( IOException e ) { e.printStackTrace(); }

        // Parser
        for (String line : lines) {
            
        }  
        System.out.println("Advent of Code 2023 // Day 9 // Matej Skelo");
        System.out.println("Part 1: " );
        System.out.println("Part 2: " );
    }
}

