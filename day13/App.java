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

public class App {
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

    // Transposes a paragraph (matrix) by 90 degrees to find vertical mirrors
    // Because it's easier to check horizontal lines for equality
    public static List<String> transpose(List<String> lines) {
        return IntStream.range(0, lines.size())
                        .mapToObj(i -> lines.stream()
                                            .map(row -> row.charAt(i))
                                            .map(String::valueOf)
                                            .collect(Collectors.joining()))
        /* --> return */.collect(Collectors.toList());
    }

    // Only finds mirrors in horizontal lines
    // Transpose a matrix first to find vertical mirrors
    // With recursion, 'cuz y nut. 
    // Returns -1 if no mirrors are found. Else first index of repeating lines.
    public static int findMirror(int index, List<String> lines) {
        if (index == lines.size()-1) return -1;
        int indexUp = index, 
            indexDn = index;
        
        if (lines.get(index).equals(lines.get(index+1))) {
            boolean isMirror = true;
            while (indexDn >= 0 && ++indexUp < lines.size()) {
                if (!lines.get(indexDn).equals(lines.get(indexUp))) {isMirror = false; break;}
                indexDn--;
            }
            return isMirror ? index : -1; // Here's the final return
        }
        else return findMirror(++index, lines);
    }

    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        // I/O
        try   { lines = Files.readAllLines(Paths.get("./calibration")); } 
        catch ( IOException e ) { e.printStackTrace(); }
        
        List<List<String>> paragraphs = parseParagraphs(lines);

        for (List<String> paragraph : paragraphs) {
            // 100 * (rows above mirror)
            //     + (columns before mirror)

            int verticalMirror = findMirror(0, transpose(paragraph));
            int horizontalMirror = findMirror(0, paragraph);
            if (verticalMirror != -1) System.out.println(verticalMirror);
            else System.out.println(horizontalMirror);
        }
        System.out.println("Advent of Code 2023 // Day 13 // Matej Skelo");
        System.out.println("Part 1: " );
        System.out.println("Part 2: " );
    }
}