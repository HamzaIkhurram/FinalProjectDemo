package com.subwaysimulator.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyApp1 {
    public static void main(String[] args) {
        // Runs the simulator 
        Process process = null;        
        try {
            String[] command = {"java", "-jar", "./exe/SubwaySimulator.jar", "--in", "./data/subway.csv", "--out", "./out"};
            process = new ProcessBuilder(command).start();
        } catch (IOException e) {
            System.err.println(e);
            e.printStackTrace();
        }
        final Process finalProcess = process;
        
        // It will destroy the simulator process at the end 
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (finalProcess != null) {
                finalProcess.destroy();
            }
        }));
        
        // Keep the application alive for 5 minutes 
        try {
            Thread.sleep(5 * 60 * 1000); // 5 minutes in milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Prints simulator output to the console 
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

