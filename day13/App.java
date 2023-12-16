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
            rowsBeforeMirror(paragraph);              // rows
            System.out.println(transpose(paragraph));
            rowsBeforeMirror(transpose(paragraph));   // columns
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

    public static int rowsBeforeMirror(List<String> lines) {
        List<Integer> indexes = new ArrayList<>();

        for (int next = 1; next < lines.size(); next++) {
            int index = next - 1;
            String line = lines.get(index);
            if (line.equals(lines.get(next))) {
                if (line.size()%2==0 && ) {
                    indexes.add(index);
                }
            }
        }

        System.out.println("Sequential equal lines found at indexes: " + indexes);
        

        return 0;
    }
}

// [0,1,2,3|4,5,6]   size=7 index=3 size/2=(int)3
// [0,1,2,3|4,5,6,7] size=8 index=3 size/2=(int)4

