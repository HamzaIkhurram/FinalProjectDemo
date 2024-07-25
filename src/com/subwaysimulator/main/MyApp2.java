package com.subwaysimulator.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyApp2 extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JTextArea outputTextArea;
    private JButton stopButton;
    private Process process;

    public MyApp2() {
        super("Subway Screen");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Create the text area to display the output
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        // Create the stop button
        stopButton = new JButton("Stop");
        stopButton.addActionListener(this);

        // Add the text area and button to the content pane
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(stopButton, BorderLayout.SOUTH);
        setContentPane(contentPane);
        
        // Set the size and visibility of the frame
        setSize(600, 400);
        setVisible(true);
        
        // Launch the executable jar file
        try {
            String[] command = {"java", "-jar", "./exe/SubwaySimulator.jar", "--in", "./data/subway.csv", "--out", "./out"};
            process = new ProcessBuilder(command).start();
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                outputTextArea.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Add a window listener to stop the process when the window is closed
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (process != null) {
                    process.destroy();
                }
                dispose();
            }
        });

        // Add a shutdown hook to stop the process when the application is closed
        final Process finalProcess = process;
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (finalProcess != null) {
                finalProcess.destroy();
            }
            System.exit(0);
        }));        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == stopButton) {
            process.destroy();
            stopButton.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        new MyApp2();
    }
}



