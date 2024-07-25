package com.subwaysimulator.model;

import java.util.List;

public class Station {
    private String name;
    private String line;
    private int position;
    private double coordinateX;
    private double coordinateY;
    private List<String> connectedStations;

    public Station(String name, String line, int position, double coordinateX, double coordinateY, List<String> connectedStations) {
        this.name = name;
        this.line = line;
        this.position = position;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.connectedStations = connectedStations;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

    public List<String> getConnectedStations() {
        return connectedStations;
    }

    public void setConnectedStations(List<String> connectedStations) {
        this.connectedStations = connectedStations;
    }

    // Additional methods for detailed station information
    public String getDetailedInfo() {
        return String.format("Station: %s, Line: %s, Position: %d, Coordinates: (%.2f, %.2f), Connected Stations: %s",
                name, line, position, coordinateX, coordinateY, String.join(", ", connectedStations));
    }
}
