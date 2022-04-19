package pl.warsztat.task;

import java.util.Objects;

public class Vehicle implements Comparable<Vehicle> {

    private final String type;
    private final String brand;
    private final String model;
    private final int productionDate;
    private final int mileAge;
    private final String vin;

    public Vehicle(String type, String brand, String model, int productionDate, int mileAge, String vin) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.productionDate = productionDate;
        this.mileAge = mileAge;
        this.vin = vin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        return Double.compare(vehicle.mileAge, mileAge) == 0 && Objects.equals(type, vehicle.type) &&
                Objects.equals(brand, vehicle.brand) && Objects.equals(model, vehicle.model) &&
                Objects.equals(productionDate, vehicle.productionDate) && Objects.equals(vin, vehicle.vin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, brand, model, productionDate, mileAge, vin);
    }

    @Override
    public String toString() {
        return  type + ";" + brand + ";" + model + ";" + productionDate + ";" + mileAge + ";" + vin;
    }

    @Override
    public int compareTo(Vehicle o) {
        return 0;
    }
}

