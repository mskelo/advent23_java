/**
 * Advent of Code 2023
 * Day 5
 * Note: this only works if the input file has TWO \n's at the end
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Arrays;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.*;

public class App {
    static List<String> lines = new ArrayList<>();         
    static Map<String, String> seedLocation = new LinkedHashMap<>();
    static Map<String, String> seedRange = new LinkedHashMap<>();
    public static void main(String[] args) {
        // I/O
        try   { lines = Files.readAllLines(Paths.get("./input")); } 
        catch ( IOException e ) { e.printStackTrace(); }

        List<String> tempLines = new ArrayList<>();
        for (String line : lines) {
            if (line.isEmpty() && tempLines.size() > 0) {
                parser1(tempLines);
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
                else if (isSeedLine)   { 
                    List<String> seedLine = Arrays.asList(line.split(": ")[1].split(" "));
                    // Fill seedLocation for part 1
                    seedLine.stream()
                            .filter(str -> !str.isEmpty())
                            .forEach(seed -> seedLocation.put(seed, seed)); 
                    // Fill seedRange for part 2 with seed and range
                    String temp = "";
                    for (int i = 0; i < seedLine.size(); i++) {
                        if (i%2 == 0) temp = seedLine.get(i);
                        else          seedRange.put(temp, seedLine.get(i));
                    }
                    // System.out.println(seedRange);
                }
            }    
        }
        long part1 = seedLocation.values()
                                 .stream()
                                 .map(Long::parseLong)
                                 .min(Comparator.naturalOrder())
                                 .get();

        System.out.println("Advent of Code 2023 // Day 5 // Matej Skelo");
        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " );
    }

    public static void parser1(List<String> lines) {
        for (String seed : seedLocation.keySet()) {
            for (String line : lines) {
                Long destRange = Long.parseLong(line.split(" ")[0]),
                     srcRange  = Long.parseLong(line.split(" ")[1]),
                     length    = Long.parseLong(line.split(" ")[2]);
                Long seedVal = Long.parseLong(seedLocation.get(seed));
                if (seedVal >= srcRange && seedVal <= srcRange + length) {
                    Long newValue = (seedVal - srcRange) + destRange;
                    seedLocation.put(seed, newValue.toString());
                    break;
                }
            }
        }
    }
    public static void parser2(List<String> lines) {
        for (String seed : seedLocation.keySet()) {
            for (String line : lines) {
                Long destRange = Long.parseLong(line.split(" ")[0]),
                     srcRange  = Long.parseLong(line.split(" ")[1]),
                     length    = Long.parseLong(line.split(" ")[2]);
                Long seedVal = Long.parseLong(seedLocation.get(seed));
                if (seedVal >= srcRange && seedVal <= srcRange + length) {
                    Long newValue = (seedVal - srcRange) + destRange;
                    seedLocation.put(seed, newValue.toString());
                    break;
                }
            }
        }
    }
}
    
// Part 2 algorithm:
// long lowestLocationNumber = Long.MAX_VALUE;
// for (Map.Entry<String, String> e : seedRanges.entrySet()) {
//     if (currentLocationNumber >= seedRange.entry().key()  && 
//        currentLocationNumber <= seedRange.entry().value() && 
//        currentLocationNumber < lowestLocationNumber)
//     {
//        lowestLocationNumber = currentLocationNumber;
//     }
// }