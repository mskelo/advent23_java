/**
 * Advent of Code 2023
 * Day 5, Part 1
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Collections;
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
        for (int i = 0; i < lines.size(); i++) {
            boolean isSeedLine   =  lines.get(i).chars().anyMatch(Character::isLetter) && 
                                    lines.get(i).chars().anyMatch(Character::isDigit);
            boolean isMapName    =  lines.get(i).chars().anyMatch(Character::isLetter) && 
                                   !lines.get(i).chars().anyMatch(Character::isDigit);
            boolean juicyNumbers = !lines.get(i).chars().anyMatch(Character::isLetter) && 
                                    lines.get(i).chars().anyMatch(Character::isDigit);
            boolean isEmpty      =  lines.get(i).length() == 0;

            if (isSeedLine) {
                Stream.of(lines.get(i).split(":")[1].split(" "))
                      .filter(str -> !str.isEmpty())
                      .forEach(seed -> seedLocation.put(seed, ""));
            }
            else if (isMapName) {
                tempLines = new ArrayList<>();
            }
            else if (isEmpty) {
                parser(tempLines);
            }

            else if (juicyNumbers) {
                tempLines.add(lines.get(i));
            }
        }
        System.out.println("Advent of Code 2023 // Day 5 // Matej Skelo");
        System.out.println("Part 1: " + Collections.min(seedLocation.values()));
        System.out.println("Part 2: " );
    }

    public static void parser(List<String> lines) {
        System.out.println();
        for (String seed : seedLocation.keySet()) {
            for (int i = 0; i < lines.size(); i++) {
                System.out.println("\nSeed: "+seed);
                long destRange = Long.parseLong(lines.get(i).split(" ")[0]),
                     srcRange  = Long.parseLong(lines.get(i).split(" ")[1]),
                     length    = Long.parseLong(lines.get(i).split(" ")[2]);
                System.out.println(destRange +" "+srcRange +" "+length);
                
                boolean seedIsUnmapped  = seedLocation.get(seed).equals("");
                boolean seedInDestRange = Long.parseLong(seedLocation.get(seed)) >= destRange && Long.parseLong(seedLocation.get(seed)) < destRange + length;
                boolean seedInSrcRange  = Long.parseLong(seedLocation.get(seed)) >= srcRange && Long.parseLong(seedLocation.get(seed)) < srcRange + length;
                
                // This needs to be fixed
                if (seedIsUnmapped) {
                    seedLocation.put(seed, seed);
                    System.out.println(seedLocation.get(seed));
                }
                else if (seedInDestRange) {
                    String newValue = Long.toString(Long.parseLong(seedLocation.get(seed))-destRange+srcRange);
                    seedLocation.put(seed, newValue);
                    System.out.println("in destRange: "+seedLocation.get(seed));
                } 
                else if (seedInSrcRange) {
                    String newValue = Long.toString(Long.parseLong(seedLocation.get(seed))-srcRange+destRange);
                    seedLocation.put(seed, newValue);
                    System.out.println("in srcRange: "+seedLocation.get(seed));
                } 
                 
            }
        }
    }


}



// for line in lines
    // if line ONLY has letters and numbers
        // put every line in a List until you hit an empty line
        // push List to parser
// print seeds.values().min()

// parser(List lines)
    // for seed in seed.keySet()
        // for line in lines
            // if seed >= destNr && seed < destNr + length
                //seeds.seed = seed-dstNr+srcNr
            // else it's unmapped and keeps its value


