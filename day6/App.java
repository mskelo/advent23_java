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
        
        System.out.println("Advent of Code 2023 // Day 6 // Matej Skelo");
        System.out.println("Part 1: " + part1(lines));
        System.out.println("Part 2: " );
    }

    public static int part1(List<String> lines) {
        List<Integer> times = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();
        List<Integer> gameCounters = new ArrayList<>();

        for(int i = 0; i < lines.size(); i++) {
            for (String substr : lines.get(i).split(":")[1].split(" ")) {
                if (!substr.isBlank()) {
                    if (i == 0) { times.add(Integer.parseInt(substr)); }
                    else        { distances.add(Integer.parseInt(substr)); }
                }
            }
        }
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