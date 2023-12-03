/**
 * Advent of Code 2023
 * Day 2, Combined exercises 1 and 2
 */

import java.io.IOException;
import java.util.Iterator;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        List<String> lines = new ArrayList<String>();
        int sum = 0, powerSum = 0;
        
        // I/O
        try   { lines = Files.readAllLines(Paths.get("./input")); } 
        catch ( IOException e ) { e.printStackTrace(); }

        for (Iterator iter = lines.iterator(); iter.hasNext(); ) {
            String line = iter.next().toString();
            boolean gamePossible = true;

            // Turn line into String[]
            String[] temp = line.split(":");

            // Get game number [0]
            int game = Integer.parseInt(temp[0].split(" ")[1]);
            
            // Minimum number of colored cubes to play the Game (you just lost it)
            // Multiply and sum later to get the answer for Part 2
            // Set is used to enable use of Collections.max() and to improve legibility later on 
            Set<Integer> reds = new HashSet<>(), 
                         greens = new HashSet<>(), 
                         blues = new HashSet<>();

            // Parse game data [1]
            // Split on "MiniGame" within Game
            temp = temp[1].split(";");
            for (String s : temp) {
                int red = 0, green = 0, blue = 0;
                
                // Split on individual number+color combos
                String[] temptemp = s.split(",");
                
                for (String s1 : temptemp) {
                    // Split number from color
                    String[] temptemptemp = s1.split(" ");
                    switch(temptemptemp[2]) {
                        case "red":   red = Integer.parseInt(temptemptemp[1]);   reds.add(red);     break;
                        case "green": green = Integer.parseInt(temptemptemp[1]); greens.add(green); break;
                        case "blue":  blue = Integer.parseInt(temptemptemp[1]);  blues.add(blue);   break;
                    }
                }
                // Speaks for itself. Flips the flag, else just leaves it true;
                if (red > 12 || green > 13 || blue > 14) { gamePossible = false; }
            }
            
            // Add game number to sum for Part 1 answer
            if (gamePossible) { sum += game; }

            // Add minimum number of colored cubes to powerSum (using max(), go figure) for Part 2 answer
            powerSum += Collections.max(reds) * Collections.max(greens) * Collections.max(blues);
        } 
        System.out.println("Advent of Code 2023 // Day 2 // Matej Skelo");
        System.out.println("Part 1: " + sum);
        System.out.println("Part 2: " + powerSum);
    }
}
