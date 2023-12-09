/**
 * Advent of Code 2023
 * Day 6, Part 1
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        // I/O
        try   { lines = Files.readAllLines(Paths.get("./input")); } 
        catch ( IOException e ) { e.printStackTrace(); }

        List<Integer> times = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();
        long time = 0;
        long distance = 0;

        for(int i = 0; i < lines.size(); i++) {
            String temp = "";
            for (String substr : lines.get(i).split(":")[1].split(" ")) {
                if (!substr.isBlank()) {
                    if (i == 0) { times.add(Integer.parseInt(substr)); }
                    else        { distances.add(Integer.parseInt(substr)); }
                    temp = temp.concat(substr);
                }
            }
            if (i==0) { time = Long.parseLong(temp); } 
            else      { distance = Long.parseLong(temp); }
        }
        int part1 = 1;
        for(int p1time : times) {
            part1 *= (int) part2(times.get(i), distances.get(i));
        }
        System.out.println("Advent of Code 2023 // Day 6 // Matej Skelo");
        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2(time, distance));
    }

    // public static int part1(List<Integer> times, List<Integer> distances) {
    //     int gameCounter = 1;
    //     for (int i = 0; i < times.size(); i++) {
    //         gameCounter*= (int) part2(times.get(i), distances.get(i));
    //     }
    //     return gameCounter;
    // }

    public static long part2(long time, long distance) {
        long gameCounter = 0;
        for (int i = 0; i <= time; i++) {
            gameCounter += (i*(time-i) > distance) ? 1 : 0;
        }
        return gameCounter;
    }
} 