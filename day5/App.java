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
        try   { lines = Files.readAllLines(Paths.get("./calibration")); } 
        catch ( IOException e ) { e.printStackTrace(); }

        List<String> tempLines = new ArrayList<>();
        for (int i = 0; i < lines.size()+1; i++) {
            System.out.println(i);
            if (i == lines.size() || lines.get(i).isEmpty()) {
                parser(tempLines);
            }
            else {
                boolean isSeedLine   =  lines.get(i).chars().anyMatch(Character::isLetter) && 
                                        lines.get(i).chars().anyMatch(Character::isDigit);
                boolean isMapName    =  lines.get(i).chars().anyMatch(Character::isLetter) && 
                                       !lines.get(i).chars().anyMatch(Character::isDigit);
                boolean juicyNumbers = !lines.get(i).chars().anyMatch(Character::isLetter) && 
                                        lines.get(i).chars().anyMatch(Character::isDigit);
                if (isMapName) {
                    tempLines = new ArrayList<>();
                    System.out.println("Map name: "+ lines.get(i));
                }
                else if (isSeedLine) {
                    Stream.of(lines.get(i).split(":")[1].split(" "))
                        .filter(str -> !str.isEmpty())
                        .forEach(seed -> seedLocation.put(seed, "-1"));
                }

                else if (juicyNumbers) {
                    tempLines.add(lines.get(i));
                    System.out.println("line added "+ lines.get(i));
                }
            }    
            if (i == lines.size()) {
                parser(tempLines);
            }    
        }
        System.out.println("End: "+seedLocation);
        System.out.println("Advent of Code 2023 // Day 5 // Matej Skelo");
        System.out.println("Part 1: " + Collections.min(seedLocation.values()));
        System.out.println("Part 2: " );
    }

    public static void parser(List<String> lines) {
        System.out.println("top of parser(): "+seedLocation);
        for (String seed : seedLocation.keySet()) {
            for (int i = 0; i < lines.size(); i++) {
                System.out.println("\nSeed: "+seed);
                System.out.println(seedLocation.values());
                long destRange = Long.parseLong(lines.get(i).split(" ")[0]),
                     srcRange  = Long.parseLong(lines.get(i).split(" ")[1]),
                     length    = Long.parseLong(lines.get(i).split(" ")[2]);
                System.out.println(destRange +" "+srcRange +" "+length);
                Long val = Long.parseLong(seedLocation.get(seed));
                boolean seedIsUnmapped  = seedLocation.get(seed).equals("-1");
                if (seedIsUnmapped) {
                    seedLocation.put(seed, seed);
                    System.out.println(seedLocation.get(seed));
                }
                else if (srcRange <= val && val < srcRange + length) {
                    Long newValue = (val - srcRange) + destRange;
                    seedLocation.put(seed, newValue.toString());
                    System.out.println("seed: "+seed+"newValue: "+newValue);
                }
            }
        }
    }


}
