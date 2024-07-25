package com.subwaysimulator.simulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    public List<String[]> parseCSV(String filePath, boolean hasHeader) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            if (hasHeader) {
                br.readLine();  // Skip header line
            }
            while ((line = br.readLine()) != null) {
                data.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public List<String[]> parseRedCSV() {
        return parseCSV("./data/Red.csv", true);
    }

    public List<String[]> parseBlueCSV() {
        return parseCSV("./data/Blue.csv", true);
    }

    public List<String[]> parseGreenCSV() {
        return parseCSV("./data/Green.csv", true);
    }

    public List<String[]> parseMapCSV() {
        return parseCSV("./data/Map.csv", true);
    }

    public List<String[]> parseTrainsCSV() {
        return parseCSV("./data/Trains_1680832574555.csv", true);
    }
}
