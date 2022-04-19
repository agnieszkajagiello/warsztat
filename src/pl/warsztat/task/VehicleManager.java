package pl.warsztat.task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class VehicleManager {

    private final Scanner scanner = new Scanner(System.in);
    private Queue<Vehicle> vehicleQueue = new LinkedList<>();
    String fileName = "vehicles.csv";

    void mainLoop() throws IOException {
        Option option;
        try {
            vehicleQueue = VehiclesReader.readFile(fileName);
        } catch (FileNotFoundException e) {
            System.err.println("Nie znaleziono pliku o nazwie: " + fileName + "\n");
        }
        do {
            printOptions();
            System.out.println("\nWybierz opcję:");
            option = Option.createFromInt(scanner.nextInt());
            scanner.nextLine();
            switch (option) {
                case EXIT -> {
                    checkQueue();
                    System.out.println("Papa!");
                }
                case READ_AND_ADD -> {
                    readAndAddVehicle();
                    System.out.println("Samochód dodany do kolejki\n");
                }
                case REVIEW -> takeVehicle();
                default -> System.out.println("Wybrano zły numer");
            }
        } while (option != Option.EXIT);
    }

    private void checkQueue() throws IOException {
        if (!vehicleQueue.isEmpty()) {
            VehiclesReader.writeFile(vehicleQueue);
        } else {
            PrintWriter writer = new PrintWriter(fileName);
            writer.print("");
            writer.close();
        }
        scanner.close();
    }

    private void takeVehicle() {
        if (vehicleQueue.isEmpty()) {
            System.out.println("Brak pojazdów oczekujących na przegląd\n");
        } else {
            Vehicle nextVehicle = vehicleQueue.poll();
            System.out.println("Pojazd do przeglądu:");
            System.out.println(nextVehicle);
        }
    }

    private void readAndAddVehicle() {
        System.out.println("Podaj typ pojazdu (samochód osobowy/samochód ciężarowy/motocykl/itd.)");
        String type = scanner.nextLine();
        System.out.println("Podaj markę samochodu");
        String brand = scanner.nextLine();
        System.out.println("Podaj model samochodu");
        String model = scanner.nextLine();
        System.out.println("Podaj rocznik samochodu");
        int productionDate = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Podaj przebieg samochodu (km)");
        int mileAge = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Podaj numer VIN samochodu");
        String vin = scanner.nextLine();
        System.out.println();
        vehicleQueue.offer(new Vehicle(type, brand, model, productionDate, mileAge, vin));
    }

    private void printOptions() {
        Option[] values = Option.values();
        for (Option value : values) {
            System.out.println(value);
        }
    }

    private enum Option {
        EXIT(0, "Zakończ"),
        READ_AND_ADD(1, "Dodaj nowy pojazd do kolejki"),
        REVIEW(2, "Pobierz pojazd z kolejki, który zostanie poddany przeglądowi");

        int option;
        String description;

        Option(int option, String description) {
            this.option = option;
            this.description = description;
        }

        static Option createFromInt(int option) {
            return values()[option];
        }

        @Override
        public String toString() {
            return option + " - " + description;
        }
    }
}

