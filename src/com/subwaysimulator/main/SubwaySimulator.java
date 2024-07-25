package com.subwaysimulator.main;

import com.subwaysimulator.advertisement.AdvertisementDatabase;
import com.subwaysimulator.model.Train;
import com.subwaysimulator.simulator.CSVParser;
import com.subwaysimulator.utils.TrainSimulator;
import com.subwaysimulator.utils.WeatherFetcher;
import com.subwaysimulator.utils.NewsFetcher;
import com.subwaysimulator.utils.Announcement;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SubwaySimulator extends Application {
    private ImageView adsArea;
    private TextArea weatherArea;
    private TextArea newsArea;
    private TextArea trainInfoArea;
    private TrainSimulator simulator;
    private Timer adTimer;
    private Timer weatherTimer;
    private Timer newsTimer;
    private Timer trainTimer;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Subway Simulator");

        // Create panels for different sections
        adsArea = new ImageView();
        weatherArea = new TextArea();
        weatherArea.setEditable(false);
        newsArea = new TextArea();
        newsArea.setEditable(false);
        trainInfoArea = new TextArea();
        trainInfoArea.setEditable(false);

        // Set up layout
        BorderPane root = new BorderPane();
        root.setCenter(adsArea);
        root.setTop(weatherArea);
        root.setBottom(newsArea);
        root.setRight(trainInfoArea);

        // Initialize and start timers for updating sections
        startAdTimer();
        startWeatherTimer("5913490"); // Example city code
        startNewsTimer("city X"); // Example query
        startTrainTimer();

        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    private void startAdTimer() {
        adTimer = new Timer();
        adTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                List<byte[]> ads = AdvertisementDatabase.fetchAdvertisements();
                for (byte[] ad : ads) {
                    try {
                        // Create an Image from the byte array and set it to the ImageView
                        Image image = new Image(new ByteArrayInputStream(ad));
                        adsArea.setImage(image);
                        Thread.sleep(10000); // Display each ad for 10 seconds
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // Show map for 5 seconds
                // Assuming you have a method to get the map as an image byte array
                byte[] mapData = fetchMapData();
                Image mapImage = new Image(new ByteArrayInputStream(mapData));
                adsArea.setImage(mapImage);
                try {
                    Thread.sleep(5000); // Display map for 5 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 15000);
    }

    private void startWeatherTimer(String cityCode) {
        weatherTimer = new Timer();
        weatherTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    String weather = WeatherFetcher.fetchWeather(cityCode);
                    weatherArea.setText("Current Weather: " + weather);
                } catch (Exception e) {
                    weatherArea.setText("Failed to fetch weather data.");
                }
            }
        }, 0, 3600000); // Update every hour
    }

    private void startNewsTimer(String query) {
        newsTimer = new Timer();
        newsTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    String news = NewsFetcher.fetchNews(query, "YOUR_API_KEY");
                    newsArea.setText("News: " + news);
                } catch (Exception e) {
                    newsArea.setText("Failed to fetch news data.");
                }
            }
        }, 0, 60000); // Update every minute
    }

    private void startTrainTimer() {
        trainTimer = new Timer();
        trainTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                simulator.simulateTrainMovement();
                trainInfoArea.setText(""); // Clear previous output
                for (Train train : simulator.getTrains()) {
                    trainInfoArea.appendText("Train " + train.getId() + " on line " + train.getLine() + " at position " + train.getCurrentPosition() + " moving " + train.getDirection() + " towards " + train.getDestination() + "\n");
                    Announcement.announce("Next stop: " + train.getDestination() + ", you can change your train to line " + train.getLine());
                }
            }
        }, 0, 15000); // Update every 15 seconds
    }

    // Dummy method to fetch map data as byte array
    private byte[] fetchMapData() {
        // Replace with actual logic to fetch the map image data
        return new byte[0];
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java SubwaySimulatorApp <cityCode> <newsKeyword>");
            return;
        }

        final String cityCode = args[0];
        final String newsKeyword = args[1];

        // Pass arguments to application
        Application.launch(SubwaySimulator.class, cityCode, newsKeyword);
    }
}


