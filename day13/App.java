/**
 * Advent of Code 2023
 * Day 13
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class App {
    // (1) Parses raw lines into easily traversable paragraphs
    //     "yo dawg, i heard you liked Lists"
    public static List<List<String>> parseParagraphs(List<String> lines) {
        List<List<String>> paragraphs = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        for (String line : lines) {
            if   (!line.isBlank()) temp.add(line);
            else {
                paragraphs.add(temp); 
                temp = new ArrayList<>(); 
            }
        }
        return paragraphs;
    }

    // (2) Transposes a paragraph (matrix) by 90 degrees to find vertical mirrors
    //     Because it's easier to check horizontal lines for equality
    public static List<String> transpose(List<String> lines) {
        String[] preResult = new String[lines.get(0).length()];
        Arrays.fill(preResult, "");
        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                preResult[i] = preResult[i].concat(String.valueOf(line.charAt(i)));
            }
        }
        return new ArrayList<>(Arrays.asList(preResult));
    }

    // (3)  Finds mirrors recursively. Only works with horizontal lines
    //      Transpose a matrix first to find vertical mirrors 
    //      Returns -1 if no mirrors are found. Else first index of repeating lines.
    public static int findMirror(int index, List<String> lines) {
        if (index < 0 || index >= lines.size()-1) return -1;
        int indexUp = index, 
            indexDn = index;
        if (lines.get(index).equals(lines.get(index+1))) {
            boolean isMirror = true;
            while (indexDn >= 0 && ++indexUp < lines.size()) {
                if (!lines.get(indexDn).equals(lines.get(indexUp))) { isMirror = false; break; }
                indexDn--;
            }
            return isMirror ? index : findMirror(++index, lines); // Here's the final return
        }
        return findMirror(++index, lines);
    }

    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        // I/O
        try   { lines = Files.readAllLines(Paths.get("./input")); } 
        catch ( IOException e ) { e.printStackTrace(); }
        
        // (1) Parse paragraphs
        List<List<String>> paragraphs = parseParagraphs(lines);
        int part1 = 0;

        // First (2) transpose paragraph and try to (3) find vertical mirrors.
        // If those are not found, check for horizontal mirrors.
        for (List<String> paragraph : paragraphs) {
            int verticalMirrorIndex = findMirror(0, transpose(paragraph));
            int horizontalMirrorIndex = findMirror(0, paragraph);
            part1 +=  (verticalMirrorIndex != -1) ? verticalMirrorIndex + 1 : 
                                                    100 * (horizontalMirrorIndex + 1);
        }
        System.out.println("Advent of Code 2023 // Day 13 // Matej Skelo");
        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " );
    }
}