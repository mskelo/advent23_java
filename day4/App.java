/**
 * Advent of Code 2023
 * Day 4, Part 1
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.*;
 
public class App {
    public static void main(String[] args) {
        List<String> lines = new ArrayList<String>();         
        int sum = 0;

        // I/O
        try   { lines = Files.readAllLines(Paths.get("./input")); } 
        catch ( IOException e ) { e.printStackTrace(); }
         
        Iterator<String> iterator = lines.iterator();
        
        while (iterator.hasNext()) {
            String line = iterator.next();
            // Needs "\\|", because "|" in regex means OR
            Set<Integer> winningNumbers = Stream.of(line.split(":")[1].split("\\|")[0].split(" "))
                                                .filter(str -> !str.isEmpty())
                                                .map(Integer::parseInt)
                                                .collect(Collectors.toSet());
            Set<Integer> myNumbers = Stream.of(line.split(":")[1].split("\\|")[1].split(" "))
                                                .filter(str -> !str.isEmpty())
                                                .map(Integer::parseInt)
                                                .collect(Collectors.toSet());       
            int numberOfMatches = 0;
            for(int myNumber : myNumbers) {   
                if (winningNumbers.contains(myNumber)) {
                    if (numberOfMatches == 0) { numberOfMatches++; } 
                    else                      { numberOfMatches *= 2; }
                }
            }  
            sum += numberOfMatches;  
        }
        System.out.println("Advent of Code 2023 // Day 4 // Matej Skelo");
        System.out.println("Part 1: " + sum);
    }
}