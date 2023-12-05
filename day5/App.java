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
        List<String> lines = new ArrayList<>();         
        List<String> seeds = new ArrayList<>();
        Map<Integer, Integer> seedLocation = new HashMap<>(); // seed: int, soil: int

        // I/O
        try   { lines = Files.readAllLines(Paths.get("./input")); } 
        catch ( IOException e ) { e.printStackTrace(); }
        List<Integer> toNextLoop = new ArrayList<>();
        // parses the line with the seeds
        if (lines.get(i).chars().anyMatch(Character::isLetter) && 
            lines.get(i).chars().anyMatch(Character::isDigit)) 
        {
            seeds.addAll(Stream.of(lines.get(i).split(":")[1].split(" "))
                                .filter(str -> !str.isEmpty())
                                .collect(Collectors.toList()));
        }

        System.out.println("Advent of Code 2023 // Day 5 // Matej Skelo");
        System.out.println("Part 1: "/*+part1*/);
        System.out.println("Part 2: " );
    }

    public int parser(int seed) {
        for (int i = 0; i < lines.size(); i++) {
            List<Integer> temp = new ArrayList<>();

            // the juicy, juicy numbers
            if (!lines.get(i).chars().anyMatch(Character::isLetter) &&
                      lines.get(i).chars().anyMatch(Character::isDigit)) 
            {
                long destRange = Long.parseLong(lines.get(i).split(" ")[0]), 
                      srcRange = Long.parseLong(lines.get(i).split(" ")[1]), 
                        length = Long.parseLong(lines.get(i).split(" ")[2]);
                System.out.println(destRange +" "+srcRange +" "+length);
            } 
            else if (line.length() == 0) {
                // flush the Map and 
            }

            for (int seed : seeds) {
            
            }



            if (seed > d) {

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
            // if seed > destNr && seed < destNr + length
                //seeds.seed = seed-dstNr+srcNr
            // else it's unmapped and keeps its value


