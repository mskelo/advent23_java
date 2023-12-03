/**
 * Advent of Code 2023
 * Day 3, Part 1
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.*;

public class App {
    public static void main(String[] args) {
        List<String> lines = new ArrayList<String>();
        final Set<Character> symbols = Stream.of('@', '#', '$', '%', '&', '*', '+', '-', '=', '/')
                                             .collect(Collectors.toSet());

        // Stores a List of symbol positions of the previous line
        List<Integer> prevLineSymbols = new ArrayList<>();

        // I/O
        try   { lines = Files.readAllLines(Paths.get("./calibration")); } 
        catch ( IOException e ) { e.printStackTrace(); }
        
        Iterator<String> iterator = lines.iterator();
        String numberStr = "";

        // Record 2 HashMaps per line that contain:
        // *  multidigit number (as String) and the index it starts at on the line (Integer)
        // *  symbols (as Character) and the index they're located at in the line
        while (iterator.hasNext()) {
            String currentLine = iterator.next();
            Map <String, Integer> currentLineNumberStrings = new HashMap<>();
            List<Integer> currentLineSymbolIndices = new ArrayList<>();

            // Detect all sequential number combos on the line
            for (int index = 0; index < currentLine.length(); index++) {
                char currentChar = currentLine.charAt(index);

                // Detect all sequential number combos on the line
                if (Character.isDigit(currentChar)) {
                    numberStr += currentChar;
                } else if (!Character.isDigit(currentChar) && !numberStr.equals("")) {
                    currentLineNumberStrings.put(numberStr, currentLine.indexOf(numberStr));
                    numberStr = "";
                } else if (!Character.isDigit(currentChar) && numberStr.equals("")) {
                    if(symbols.contains(currentChar)) {
                        currentLineSymbolIndices.add(currentLine.indexOf(currentChar));
                    }
                }
            }
            // If current line has symbols
            //   Record symbols and indexes
            //   If current line has numbers
            //     If a number on the line matches numberStartIndex+number.length == symbolIndex-1 OR numberStartIndex == symbolIndex+1
            //       Record number




            prevLineSymbols.addAll(currentLineSymbolIndices);
        }

        
        
        
        // Sum all part numbers
        
    }
}
