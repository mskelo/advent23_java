/**
 * Advent of Code 2023
 * Day 1, Combined exercises 1 and 2
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class App {
    public static void main(String[] args) {
        List<String> lines = new ArrayList<String>();
        String[] strDigits = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        int sum = 0, sumDigits = 0;
        
        // I/O
        try   { lines = Files.readAllLines(Paths.get("./input")); } 
        catch ( IOException e ) { e.printStackTrace(); }
        
        for (Iterator iter = lines.iterator(); iter.hasNext(); ) {
            // next() advances the loop / Iterator by 1
            String line = iter.next().toString();
            
            HashMap<Integer, Integer> digitPositions = new HashMap<>();
            // Look for all numbers that are represented as Strings and convert them to Integer
            // Checks if first and last occurrence in the word are the same
            // If not, it adds both to the result HashMap
            for (int index = 0; index < strDigits.length; index++) {        
                if (line.contains(strDigits[index])) {
                    digitPositions.put(line.indexOf(strDigits[index]), index+1);
                    if (line.indexOf(strDigits[index]) != line.lastIndexOf(strDigits[index])) {
                        digitPositions.put(line.lastIndexOf(strDigits[index]), index+1);
                    }
                }
            }

            HashMap<Integer, Integer> digitsOnly = new HashMap<>();
            // Find only characters that are digits
            for (int index = 0; index < line.length(); index++) {
                char currentChar = line.charAt(index);
                if (Character.isDigit(currentChar)) {
                    int digitValue = Character.getNumericValue(currentChar);
                    digitsOnly.put(index, digitValue);
                }
            } 

            // Adds to the sum for part 1 of the exercise
            // n*10^1 + n*10^0
            sumDigits += 10 * digitsOnly.get(Collections.min(digitsOnly.keySet()))
                            + digitsOnly.get(Collections.max(digitsOnly.keySet()));

            // Merges digitsOnly Map with the other Map that contains the "word numbers"
            digitPositions.putAll(digitsOnly);
            
            // Calculate sum for part 2 from merged HashMap
            // n*10^1 + n*10^0
            sum += 10 * digitPositions.get(Collections.min(digitPositions.keySet()))
                      + digitPositions.get(Collections.max(digitPositions.keySet()));
            
        }
        System.out.println("Advent of Code 2023 // Day 1 // Matej Skelo");
        System.out.println("Part 1: " + sumDigits);
        System.out.println("Part 2: " + sum);
    }
}