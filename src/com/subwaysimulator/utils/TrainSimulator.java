package com.subwaysimulator.utils;

import com.subwaysimulator.model.Train;
import java.util.List;

public class TrainSimulator {
    private List<Train> trains;

    public TrainSimulator(List<Train> trains) {
        this.trains = trains;
    }

    public void simulateTrainMovement() {
        for (Train train : trains) {
            String currentPosition = train.getCurrentPosition();
            String direction = train.getDirection();
            int nextPosition = Integer.parseInt(currentPosition.substring(1)) + (direction.equals("forward") ? 1 : -1);
            train.setCurrentPosition(currentPosition.charAt(0) + String.valueOf(nextPosition));
        }
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }
}