package com.subwaysimulator.model;

public class Train {
    private int id;
    private String line;
    private String currentPosition;
    private String direction;
    private String destination;

    public Train(int id, String line, String currentPosition, String direction, String destination) {
        this.id = id;
        this.line = line;
        this.currentPosition = currentPosition;
        this.direction = direction;
        this.destination = destination;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
