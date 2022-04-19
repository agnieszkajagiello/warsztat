package pl.warsztat.task;

import java.io.IOException;

public class VehicleManagerApp {

    public static void main(String[] args) throws IOException {

        VehicleManager vehicleManager = new VehicleManager();
        vehicleManager.mainLoop();
    }
}
