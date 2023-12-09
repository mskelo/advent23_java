/**
 * Advent of Code 2023
 * Day 6, Part 1
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        // I/O
        try   { lines = Files.readAllLines(Paths.get("./input")); } 
        catch ( IOException e ) { e.printStackTrace(); }

        List<Integer> times = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();
        int part2time = 0;
        int part2distance = 0;

        for(int i = 0; i < lines.size(); i++) {
            String temp = "";
            for (String substr : lines.get(i).split(":")[1].split(" ")) {
                if (!substr.isBlank()) {
                    if (i == 0) { 
                        times.add(Integer.parseInt(substr)); 
                        temp += substr;
                    }
                    else { 
                        distances.add(Integer.parseInt(substr)); 
                    }
                }
            }
            if (i==0) { part2time = Integer.parseInt(temp); } 
            else      { part2distance = Integer.parseInt(temp); }
        }
        System.out.println(part2time +" "+ part2distance);
        
        System.out.println("Advent of Code 2023 // Day 6 // Matej Skelo");
        System.out.println("Part 1: " + part1(times, distances));
        System.out.println("Part 2: " );
    }

    public static int part1(List<Integer> times, List<Integer> distances) {
        List<Integer> gameCounters = new ArrayList<>();
        for (int i = 0; i < times.size(); i++) {
            int gameCounter = 0;
            for (int j = 0; j <= times.get(i); j++) {
                if (j*(times.get(i)-j) > distances.get(i)) {
                    gameCounter++;
                }
            }
            gameCounters.add(gameCounter);
        }
        return gameCounters.stream().reduce(1, (a,b) -> a*b);
    }
} 