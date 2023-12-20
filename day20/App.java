/**
 * Advent of Code 2023
 * Day 20
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Arrays;

public class App {
    public static Map<String, Module> parseInput(List<String> lines) {
        Map<String, Module> moduleMap = new LinkedHashMap<>();
        for (String line : lines) {
            String label  = line.split(" -> ")[0];    
            List<String> labels = new ArrayList<>(Arrays.asList(line.split(" -> ")[1].split(","))); 
            if (label.length() == 3) {
                if (label.charAt(0) == '%') {
                    moduleMap.put(label.substring(1), new FlipFlop(label.substring(1), false, labels));
                } else {
                    moduleMap.put(label.substring(1), new Conjunction(label.substring(1), false, labels));
                }
            } 
            else moduleMap.put(label, new Broadcaster(label, false, labels));
        }
        return moduleMap;
    }

    public static void main(String[] args) {
        // I/O
        List<String> lines = new ArrayList<>();
        try   { lines = Files.readAllLines(Paths.get("./input")); } 
        catch ( IOException e ) { e.printStackTrace(); }

        Map<String, Module> moduleMap = parseInput(lines);
        System.out.println(moduleMap);


        System.out.println("Advent of Code 2023 // Day 20 // Matej Skelo");
        System.out.println("Part 1: ");
        System.out.println("Part 2: ");
    }
}

class Module {
    private boolean state;
    private String label;
    private List<String> labels;
    public Module (String label, boolean state, List<String> labels) {
        this.label = label;
        this.state = state;
        this.labels = labels;
    }
    public String  getLabel() { return this.label; }
    public boolean getState() { return this.state; }
    public List<String> getLabels() { return this.labels; }
    public void    setState(boolean state) { this.state = state; }
}

class Broadcaster extends Module {
    public Broadcaster(String label, boolean state, List<String> labels) {
        super(label, state, labels);
    }
    public void sendPulse() {
        // send low pulse
    }
}

class Conjunction extends Module {
    public Conjunction(String label, boolean state, List<String> labels) {
        super(label, state, labels);
    }
}

class FlipFlop extends Module {
    public FlipFlop (String label, boolean state, List<String> labels) {
        super(label, state, labels);
    }
    public void sendPulse(boolean high) {
        if (!high) super.setState(!super.getState());
    }
}