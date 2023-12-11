/**
 * Advent of Code 2023
 * Day 5
 * Note: this only works if the input file has TWO \n's at the end
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.*;

public class App {
    static List<String> lines = new ArrayList<>();         
    static Map<Long, Long> seedRanges = new LinkedHashMap<>();
    static List<Long> part1seeds = new ArrayList<>();
    static List<Thread> bruteForceFactory1 = new ArrayList<>();
    static List<Thread> bruteForceFactory2 = new ArrayList<>();
    static List<Long> locations = new ArrayList<>();
    static Long part1 = 0l,
                part2 = 0l;
    public static void main(String[] args) {
        // I/O
        try   { lines = Files.readAllLines(Paths.get("./input")); } 
        catch ( IOException e ) { e.printStackTrace(); }

        // Parse seed line
        List<String> seedLine = Arrays.asList(lines.get(0).split(": ")[1].split(" "));
        // Fill part1seeds
        seedLine.stream()
                .filter(str -> !str.isEmpty())
                .forEach(seed -> part1seeds.add(Long.parseLong(seed))); 
        // Fill seedRanges for part 2 with seed and range
        String temp = "";
        for (int i = 0; i < seedLine.size(); i++) {
            if (i%2 == 0) temp = seedLine.get(i);
            else {
                bruteForceFactory2.add(
                    new BruteForcer9000(lines, Long.parseLong(temp), Long.parseLong(seedLine.get(i)))
                );
            }
        }

        System.out.println("Part 1 start");
        Long part1 = Long.MAX_VALUE;
        for (Long seed : part1seeds) {
            bruteForceFactory1.add(new BruteForcer9000(lines, seed));
        }
        for (Thread inator : bruteForceFactory1) { 
            inator.start();
        }
        for (Thread inator : bruteForceFactory1) { 
            try { inator.join(); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
        part1 = locations.stream().min(Comparator.naturalOrder()).get();
        locations = new ArrayList<>();
        
        System.out.println("Part 2 start");
        Long part2 = Long.MAX_VALUE;
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (Thread inator : bruteForceFactory2) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(inator::run);
            futures.add(future);
        }
        CompletableFuture<Void>[] futureArray = futures.toArray(new CompletableFuture[0]);
        CompletableFuture.allOf(futureArray).join();
        part2 = locations.stream().min(Comparator.naturalOrder()).get();

        System.out.println("Advent of Code 2023 // Day 5 // Matej Skelo");
        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    

    public static synchronized void callback(int hashCode, Long location) {
        System.out.println(hashCode+": "+location);
        locations.add(location);
    }

}


class BruteForcer9000 extends Thread {
    private Long seedStart;
    private Long length;
    private Long lowestLocation = Long.MAX_VALUE;
    private List<String> lines = new ArrayList<>();         

    public BruteForcer9000(List<String> lines, Long seedStart) {
        this.lines = lines;
        this.seedStart = seedStart;
    }
    public BruteForcer9000(List<String> lines, Long seedStart, Long length) {
        this.lines = lines;
        this.seedStart = seedStart;
        this.length = length;
    }
    @Override
    public void run() {
        if (this.length == null) {
            Long seedLocation = parser(seedStart);
            if (seedLocation < lowestLocation) {
                lowestLocation = seedLocation;
            }
            App.callback(this.hashCode(),lowestLocation);
        }
        else {
            for (Long l = this.seedStart; l<this.length; l++) {
                Long seedLocation = parser(l);
                if (seedLocation >= seedStart && 
                    seedLocation <  length    && 
                    seedLocation <  lowestLocation)
                {
                    System.out.println(this.hashCode()+": NEW lowest location number: "+seedLocation);
                    lowestLocation = seedLocation;
                }
            }
            App.callback(this.hashCode(),lowestLocation);
        }
    }

    public Long parser(Long seed) {
        boolean redFlag = false;
        for (int i = 1; i<lines.size(); i++) {
            String line = lines.get(i);
            if (!line.isEmpty()) {
                boolean isMapName    =  line.chars().anyMatch(Character::isLetter) && 
                                       !line.chars().anyMatch(Character::isDigit);
                boolean juicyNumbers = !line.chars().anyMatch(Character::isLetter) && 
                                        line.chars().anyMatch(Character::isDigit);
                
                if (isMapName) {
                    redFlag = false;   
                }
                else if (juicyNumbers) {
                    if (!redFlag) {
                        Long destRange = Long.parseLong(line.split(" ")[0]),
                             srcRange  = Long.parseLong(line.split(" ")[1]),
                             length    = Long.parseLong(line.split(" ")[2]);
                        if (seed >= srcRange && seed <= srcRange + length) {
                            redFlag = true;
                            seed = (seed - srcRange) + destRange;
                        }
                    }
                    else continue;
                }
            }
        }
        return seed;
    }

    
}