/**
 * Advent of Code 2023
 * Day 4, Part 1
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.*;
 
public class App {
    public static void main(String[] args) {
        List<String> lines = new ArrayList<String>();         
        List<Card> cards = new ArrayList<Card>();

        // I/O
        try   { lines = Files.readAllLines(Paths.get("./input")); } 
        catch ( IOException e ) { e.printStackTrace(); }
        
        // Create initial Card Objects
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            // Needs "\\|", because "|" in regex means OR
            Set<Integer> winningNumbers = Stream.of(line.split(":")[1].split("\\|")[0].split(" "))
                                                .filter(str -> !str.isEmpty())
                                                .map(Integer::parseInt)
                                                .collect(Collectors.toSet());
            Set<Integer> myNumbers      = Stream.of(line.split(":")[1].split("\\|")[1].split(" "))
                                                .filter(str -> !str.isEmpty())
                                                .map(Integer::parseInt)
                                                .collect(Collectors.toSet());       
            
            cards.add(new Card(i+1, winningNumbers, myNumbers));      
        }
        int part1 = cards.stream()
                         .filter(card -> card.getWinningNumbersAmount() > 0)
                         .mapToInt(card -> card.getWinningNumbersAmount())
                         .map(amount -> (amount == 1) ? 1 : (int) Math.pow(2,amount-1))
                         .sum();
        
        // TODO... i'm going to bed
        // for (int i = 0; i < cards.size(); i++) {
        //     if (cards.get(i).getWinningNumbersAmount() > 0) {
        //         System.out.println("CardNumber: "+cards.get(i).getCardNumber()+", winningnumbers: "+cards.get(i).getWinningNumbersAmount());
        //         for (int j = 1; j <= cards.get(i).getWinningNumbersAmount(); j++) {
        //             cards.add(cards.get(cards.get(i).getCardNumber()+j));
        //         }
        //     }
        // }
        
        System.out.println("Advent of Code 2023 // Day 4 // Matej Skelo");
        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: "/* + cards.size()*/);
    }
}

class Card {
    private int cardNumber;
    private Set<Integer> winningNumbers;
    private Set<Integer> myNumbers;
    private int copies;

    public Card(int cardNumber, Set<Integer> winningNumbers, Set<Integer> myNumbers) {
        this.cardNumber = cardNumber;
        this.winningNumbers = winningNumbers;
        this.myNumbers = myNumbers;
        this.copies = 1;
    }

    public int getCardNumber() {
        return this.cardNumber;
    }
    public int getWinningNumbersAmount() {
        return Math.toIntExact(myNumbers.stream()
                                        .filter(myNumber -> this.winningNumbers.contains(myNumber))
                                        .count());
    }
    public void addCopy() {
        this.copies++;
    }
}