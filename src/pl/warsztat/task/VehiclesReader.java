package pl.warsztat.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class VehiclesReader {

    public static void writeFile(Queue<Vehicle> vehicleQueue) throws IOException {
        String fileName = "vehicles.csv";
        File resultsFile = new File(fileName);
        FileWriter fileWriter = new FileWriter(resultsFile);
        for (Vehicle vehicle : vehicleQueue) {
            fileWriter.write(vehicle + "\n");
        }
        fileWriter.close();
    }

    public static Queue<Vehicle> readFile(String filename) throws FileNotFoundException {
        Queue<Vehicle> vehicles = new LinkedList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                vehicles.offer(createVehicleFromCsv(line));
            }
        }
        return vehicles;
    }

    private static Vehicle createVehicleFromCsv(String csvLine) {
        String[] data = csvLine.split(";");
        String type = data[0];
        String brand = data[1];
        String model = data[2];
        int productionDate = Integer.parseInt(data[3]);
        int mileAge = Integer.parseInt(data[4]);
        String vin = data[5];
        return new Vehicle(type, brand, model, productionDate, mileAge, vin);
    }
}
