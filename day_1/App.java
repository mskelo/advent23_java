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
            // next() advances the loop / Iterator by 1 iteration
            String temp = iter.next().toString();
            
            HashMap<Integer, Integer> digitPositions = new HashMap<>();
            // Look for all numbers that are represented as Strings and convert them to Integer
            // Checks if first and last occurrence in the word are the same
            // If not, it adds both to the result HashMap
            for (int i = 0; i < strDigits.length; i++) {        
                if (temp.contains(strDigits[i])) {
                    digitPositions.put(temp.indexOf(strDigits[i]), i+1);
                    if (temp.indexOf(strDigits[i]) != temp.lastIndexOf(strDigits[i])) {
                        digitPositions.put(temp.lastIndexOf(strDigits[i]), i+1);
                    }
                }
            }

            HashMap<Integer, Integer> digitsOnly = new HashMap<>();
            // Find only characters that are digits
            for (int i = 0; i < temp.length(); i++) {
                char currentChar = temp.charAt(i);
                if (Character.isDigit(currentChar)) {
                    int value = Character.getNumericValue(currentChar);
                    digitsOnly.put(i, value);
                }
            } 

            // Adds to the sum for part 1 of the exercise
            sumDigits += (
                digitsOnly.get(Collections.min(digitsOnly.keySet())) * 10 + 
                digitsOnly.get(Collections.max(digitsOnly.keySet()))
            );

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