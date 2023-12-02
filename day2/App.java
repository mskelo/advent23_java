/**
 * Advent of Code 2023
 * Day 2, Part 1
 */

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

class MiniGame {
    private int red = 0, green = 0, blue = 0;

    public MiniGame(int red, int green, int blue) {
        this.red = red; this.green = green; this.blue = blue;
    }
    public boolean isPossibleGame() {
        return (this.red > 12 || this.green > 13 || this.blue > 14) ? false : true;
    }
}


public class App {
    public static void main(String[] args) {
        List<String> lines = new ArrayList<String>();
        int sum = 0;
        
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

            // Parse game data [1]
            // Split on "MiniGame" within Game
            temp = temp[1].split(";");
            for (String s : temp) {
                int red = 0, green = 0, blue = 0;
                
                // Split on individuel number+color combos
                String[] temptemp = s.split(",");
                
                for (String s1 : temptemp) {
                // Split number from color
                    String[] temptemptemp = s1.split(" ");
                    
                    switch(temptemptemp[2]) {
                        case "red": red = Integer.parseInt(temptemptemp[1]); break;
                        case "green": green = Integer.parseInt(temptemptemp[1]); break;
                        case "blue": blue = Integer.parseInt(temptemptemp[1]); break;
                    }
                }
                
                // Throw everything into an Object and check if (mini)game is possible
                MiniGame miniGame = new MiniGame(red, green, blue);
                if (!miniGame.isPossibleGame()) {
                    gamePossible = false; break;
                }
            }
            // Add game number to sum for Part 1
            if (gamePossible) {
                sum += game;
            }
        } 

        System.out.println("Advent of Code 2023 // Day 2 // Matej Skelo");
        System.out.println("Part 1: " + sum);
    }
}


