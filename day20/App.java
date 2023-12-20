/**
 * Advent of Code 2023
 * Day 20
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class App {
    public static void main(String[] args) {
        // I/O
        String line = "";
        try   { line = Files.readAllLines(Paths.get("./input")).get(0); } 
        catch ( IOException e ) { e.printStackTrace(); }


        System.out.println("Advent of Code 2023 // Day 20 // Matej Skelo");
        System.out.println("Part 1: ");
        System.out.println("Part 2: ");
    }
}
