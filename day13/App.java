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
    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        // I/O
        try   { lines = Files.readAllLines(Paths.get("./calibration")); } 
        catch ( IOException e ) { e.printStackTrace(); }
        
        List<List<String>> paragraphs = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        for (String line : lines) {
            if (!line.isBlank()) 
                temp.add(line);
            else {
                paragraphs.add(temp); 
                temp = new ArrayList<>(); 
            }
        }

        for (List<String> paragraph : paragraphs) {
            // 100 * (rows above mirror)
            //     + (columns before mirror)
            System.out.println(paragraph);
            System.out.println(transpose(paragraph));
            int verticalMirror = findMirror(0, transpose(paragraph));
            System.out.println(verticalMirror);
            System.out.println(findMirror(0, paragraph));
            System.out.println();
        }

        System.out.println("Advent of Code 2023 // Day 13 // Matej Skelo");
        System.out.println("Part 1: " );
        System.out.println("Part 2: " );
    }

    public static List<String> transpose(List<String> lines) {
        return IntStream.range(0, lines.size())
                        .mapToObj(i -> lines.stream()
                                            .map(row -> row.charAt(i))
                                            .map(String::valueOf)
                                            .collect(Collectors.joining()))
        /* --> return */.collect(Collectors.toList());
    }

    public static int findMirror(int index, List<String> lines) {
        if (index == lines.size()-1) return -1;
        
        int indexUp = index, 
            indexDn = index;
        
        if (lines.get(index).equals(lines.get(index+1))) {
            boolean isMirror = true;
            while (indexDn >= 0 && indexUp < lines.size()) {
                indexUp++;
                if (!lines.get(indexDn).equals(lines.get(indexUp))) {isMirror = false; break;}
                indexDn--;
                return index;
            }
            return isMirror ? index : -1;
        }
        else return findMirror(++index, lines);
    }
}