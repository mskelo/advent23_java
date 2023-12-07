/**
 * Advent of Code 2023
 * Day 5, Part 1
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.*;

public class App {
    static List<String> lines = new ArrayList<>();         
    static Map<String, String> seedLocation = new HashMap<>();
    public static void main(String[] args) {
        // I/O
        try   { lines = Files.readAllLines(Paths.get("./input")); } 
        catch ( IOException e ) { e.printStackTrace(); }

        List<String> tempLines = new ArrayList<>();
        for (String line : lines) {
            if (line.isEmpty() && tempLines.size() > 0) {
                parser(tempLines);
            }
            else if (!line.isEmpty()) {
                boolean isSeedLine   =  line.chars().anyMatch(Character::isLetter) && 
                                        line.chars().anyMatch(Character::isDigit);
                boolean isMapName    =  line.chars().anyMatch(Character::isLetter) && 
                                       !line.chars().anyMatch(Character::isDigit);
                boolean juicyNumbers = !line.chars().anyMatch(Character::isLetter) && 
                                        line.chars().anyMatch(Character::isDigit);
                
                if      (isMapName)    { tempLines = new ArrayList<>(); }
                else if (juicyNumbers) { tempLines.add(line); }
                else if (isSeedLine)   { Stream.of(line.split(":")[1].split(" "))
                                               .filter(str -> !str.isEmpty())
                                               .forEach(seed -> seedLocation.put(seed, seed)); }
            }    
        }
        long part1 = seedLocation.values().stream()
                                          .map(Long::parseLong)
                                          .min(Comparator.naturalOrder())
                                          .get();
        System.out.println("Advent of Code 2023 // Day 5 // Matej Skelo");
        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " );
    }

    public static void parser(List<String> lines) {
        for (String seed : seedLocation.keySet()) {
            for (String line : lines) {
                Long destRange = Long.parseLong(line.split(" ")[0]),
                     srcRange  = Long.parseLong(line.split(" ")[1]),
                     length    = Long.parseLong(line.split(" ")[2]);
                Long seedVal = Long.parseLong(seedLocation.get(seed));
                if (seedVal >= srcRange && seedVal <= srcRange + length) {
                                        //          ^     this ended up being the issue all along lmao
                    Long newValue = (seedVal - srcRange) + destRange;
                    seedLocation.put(seed, newValue.toString());
                    break; // Holy shit. This line ended two days of suffering.
                    // You don't need to keep looking once you've matched your seed's number
                    // Don't forget about context and what the values you work with represent
                }
            }
        }
    }
}
    