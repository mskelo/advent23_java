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
    public static void main(String[] args) {
        List<String> lines = new ArrayList<String>();         
        Map<Integer, Integer> resultMap = new HashMap<>(); // seed: int, soil: int

        // I/O
        try   { lines = Files.readAllLines(Paths.get("./input")); } 
        catch ( IOException e ) { e.printStackTrace(); }
        // per line: destRange sourceRange length
        for (int i = 0; i < lines.size(); i++) {
            if (!lines.get(i).chars().anyMatch(Character::isLetter)) {
                System.out.println(lines.get(i).split(" ")[0]);
                long destRange = Long.parseLong(lines.get(i).split(" ")[0]), 
                      srcRange = Long.parseLong(lines.get(i).split(" ")[1]), 
                        length = Long.parseLong(lines.get(i).split(" ")[2]);
                System.out.println(destRange +" "+srcRange +" "+length);
            } else {
                continue;
            }
        }
        
        System.out.println("Advent of Code 2023 // Day 4 // Matej Skelo");
        System.out.println("Part 1: "/*+part1*/);
        System.out.println("Part 2: " );
    }
}
