/**
 * Advent of Code 2023
 * Day 3, Part 1
 */

import java.io.IOException;
import java.util.Iterator;
import java.util.Collections;

public class App {
    public static void main(String[] args) {
        List<String> lines = new ArrayList<String>();
        
        // I/O
        try   { lines = Files.readAllLines(Paths.get("./input")); } 
        catch ( IOException e ) { e.printStackTrace(); }
        
        ListIterator<String> iterator = lines.listIterator();

        while (iterator.hasNext()) {
            int currentIndex = iterator.nextIndex();
            String current = iterator.next();

        // Check current and next line
        // Detect all sequential number combos on the line
        // Record 2 HashMaps per line that contain:
        // *  multidigit number (as String) and the index it starts at on the line (Integer)
        // *  symbols (as Character) and the index they're located at in the line
        // If current line has symbols
        //   Record symbols and indexes
        //   If current line has numbers
        //     If a number on the line matches numberStartIndex+number.length == symbolIndex-1 OR numberStartIndex == symbolIndex+1
        //       Record number
        
    }
}
