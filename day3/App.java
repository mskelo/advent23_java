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
import java.util.TreeSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.*;

public class App {
    public static void main(String[] args) {
        List<String> lines = new ArrayList<String>();
        final Set<Character> symbols = Stream.of('@', '#', '$', '%', '&', 
                                                 '*', '+', '-', '=', '/')
                                             .collect(Collectors.toSet());

        // Stores a List of symbol positions of the previous line
        Set<Integer> prevLineSymbolPositions = new HashSet<>();
        Map<Integer, String> prevLineNumberStrings = new HashMap<>();
        List<String> lastNumbersAdded = new ArrayList<>();
        

        // I/O
        try   { lines = Files.readAllLines(Paths.get("./input")); } 
        catch ( IOException e ) { e.printStackTrace(); }
        
        Iterator<String> iterator = lines.iterator();
        int partNumberSum = 0;

        // Record 2 Collections per line that contain:
        // *  multidigit number (as String) and the index it starts at on the line (Integer)
        // *  symbol positions (Integer index)
        while (iterator.hasNext()) {
            String currentLine = iterator.next();
            System.out.println("\n"+currentLine);
            List<String> addedNumbers = new ArrayList<>();
            List<String> addedPrevNumbers = new ArrayList<>();
            Map<Integer, String> currentLineNumberStrings = new HashMap<>();
            Set<Integer> currentLineSymbolPositions = new HashSet<>();
            String numberStr = "";   // Temp variable for number detection

            // Line parser, char by char
            for (int index = 0; index < currentLine.length(); index++) {
                char currentChar = currentLine.charAt(index);

                // Detect digit and append to temp variable
                if (Character.isDigit(currentChar)) {
                    numberStr += currentChar;
                } 
                // End of number. Write to Map with index position and flush temp variable.
                else if (!Character.isDigit(currentChar) && !numberStr.equals("")) {
                    currentLineNumberStrings.put(currentLine.indexOf(numberStr), numberStr);
                    numberStr = "";
                } 
                // Detect symbol positions
                if (!Character.isDigit(currentChar) && numberStr.equals("")) {
                    if(symbols.contains(currentChar)) {
                        currentLineSymbolPositions.add(currentLine.indexOf(currentChar));
                    }
                }
                // LINE END NUMBERS! or add . to the end of each line first lol
            }

            // Combine symbol positions for current and previous lines
            Set<Integer> symbolPositions = new TreeSet<>();
            symbolPositions.addAll(currentLineSymbolPositions);
            symbolPositions.addAll(prevLineSymbolPositions);

            System.out.println("prev+curr symbol positions:\n"+symbolPositions);
            System.out.println("lastNumbersAdded: "+ lastNumbersAdded);

            for (int index : currentLineNumberStrings.keySet()) {
                String num = currentLineNumberStrings.get(index);
                for (int symbolIndex : symbolPositions) {
                    boolean numTouchesSymbol = (symbolIndex >= index-1) && (symbolIndex <= index + num.length());
                    if (numTouchesSymbol) {
                        addedNumbers.add(num);
                    }
                }
            }            
            
            System.out.println("addedNumbers: "+addedNumbers);
            System.out.println("addedPrevNumbers: "+addedPrevNumbers);
            
            lastNumbersAdded = new ArrayList(addedNumbers);
            addedNumbers.addAll(addedPrevNumbers);
            for(String addedNumberStr : addedNumbers) {
                partNumberSum += (Integer.parseInt(addedNumberStr));
            }

            // Flush previous line's symbols and overwrite with current line's symbol positions
            prevLineNumberStrings = currentLineNumberStrings;
            prevLineSymbolPositions = currentLineSymbolPositions;
        }

        System.out.println("\n\n\npartNumberSum: "+partNumberSum);

        
    }
}

// for (String num : currentLineNumberStrings.values()) {
            //     if (lineContainsNumbers) {
            //         if (lineContainsSymbols) {
            //             for (int symbolindex : currentLineSymbolIndices) {
            //                 System.out.println("\nsymbolindex: "+symbolindex);
            //                 boolean symbolTouchesNumber =   currentLine.indexOf(num)+num.length() == symbolindex
            //                                              || currentLine.indexOf(num) == symbolindex+1;
                            
            //                 if (symbolTouchesNumber) {
            //                     System.out.println("num: "+num+", symbolTouchesNumber: "+symbolTouchesNumber);
            //                     System.out.println("added num:"+num);
            //                     //partNumberSum += Integer.parseInt(num);
            //                     addedNumbers.add(num);
            //                 }
            //                 for(int position : prevLineNumberStrings.values()) {
            //                     boolean symbolTouchesPreviousNumber =   symbolindex >= position - 1
            //                                                          && symbolindex <= position + prevNum.length();
            //                     if (symbolTouchesPreviousNumber) {
            //                         System.out.println("prevNum: "+prevNum+", symbolTouchesPreviousNumber: "+symbolTouchesPreviousNumber);
            //                         System.out.println("added prevNum:"+prevNum);
            //                         addedNumbers.add(prevNum);
            //                     }
            //                 }
                            
            //             }
            //         }
            //         if (prevLineContainsSymbols) {
            //             for (int prevSymbolindex : prevLineSymbols) {
            //                 System.out.println("\nprevSymbolindex: "+prevSymbolindex);
            //                 boolean prevSymbolTouchesNumber =   (prevSymbolindex >= currentLine.indexOf(num)-1)
            //                                                  && (prevSymbolindex <= currentLine.indexOf(num)+num.length());
            //                 if (prevSymbolTouchesNumber) {
            //                     System.out.println("prevSymbolindex: "+prevSymbolindex+", prevSymbolTouchesNumber: "+prevSymbolTouchesNumber);
            //                     System.out.println("added num:"+num);
            //                     //partNumberSum += Integer.parseInt(num);
            //                     addedNumbers.add(num);
            //                 }
            //             }
            //         }
            //     } 
            //     if (lineContainsSymbols) {
            //         if (prevLineContainsNumbers) {
            //             for (int symbolindex : currentLineSymbolIndices) {
            //                 System.out.println("\nsymbolindex: "+symbolindex);
            //                 for (String prevNum : prevLineNumberStrings.values()) {
            //                     if (!lastNumbersAdded.contains(prevNum)) {
            //                         int prevNumIndex = prevLineNumberStrings.get(prevNum);
            //                         boolean previousNumberTouchesSymbol =   symbolindex >= prevNumIndex-1
            //                                                             && symbolindex <= prevNumIndex+prevNum.length();
            //                         if (previousNumberTouchesSymbol) {
            //                             System.out.println("symbolindex: "+symbolindex+", previousNumberTouchesSymbol: "+previousNumberTouchesSymbol);
            //                             System.out.println("added prevNum:"+prevNum);
            //                             //partNumberSum += Integer.parseInt(prevNum);
            //                             addedNumbers.add(prevNum);

            //                         }
            //                     }
            //                 }
            //                 System.out.println("lastNumbersAdded: "+ lastNumbersAdded);
            //             }
            //         }
            //     }
            // }