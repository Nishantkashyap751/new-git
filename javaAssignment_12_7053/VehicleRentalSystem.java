abstract class Vehicle {
    private String vehicleNumber;
    private String type;
    private double rentalRate;

    public Vehicle(String vehicleNumber, String type, double rentalRate) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
        this.rentalRate = rentalRate;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getType() {
        return type;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(double rentalRate) {
        this.rentalRate = rentalRate;
    }

    public abstract double calculateRentalCost(int days);
}

interface Insurable {
    double calculateInsurance();
    String getInsuranceDetails();
}

class Car extends Vehicle implements Insurable {
    private String policyNumber;

    public Car(String vehicleNumber, double rentalRate, String policyNumber) {
        super(vehicleNumber, "Car", rentalRate);
        this.policyNumber = policyNumber;
    }

    public double calculateRentalCost(int days) {
        return getRentalRate() * days;
    }

    public double calculateInsurance() {
        return getRentalRate() * 0.10;
    }

    public String getInsuranceDetails() {
        return "Car Policy: " + policyNumber;
    }
}

class Bike extends Vehicle implements Insurable {
    private String policyNumber;

    public Bike(String vehicleNumber, double rentalRate, String policyNumber) {
        super(vehicleNumber, "Bike", rentalRate);
        this.policyNumber = policyNumber;
    }

    public double calculateRentalCost(int days) {
        return getRentalRate() * days;
    }

    public double calculateInsurance() {
        return getRentalRate() * 0.05;
    }

    public String getInsuranceDetails() {
        return "Bike Policy: " + policyNumber;
    }
}

class Truck extends Vehicle implements Insurable {
    private String policyNumber;

    public Truck(String vehicleNumber, double rentalRate, String policyNumber) {
        super(vehicleNumber, "Truck", rentalRate);
        this.policyNumber = policyNumber;
    }

    public double calculateRentalCost(int days) {
        return getRentalRate() * days;
    }

    public double calculateInsurance() {
        return getRentalRate() * 0.15;
    }

    public String getInsuranceDetails() {
        return "Truck Policy: " + policyNumber;
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Vehicle v1 = new Car("C101", 2000, "P123");
        Vehicle v2 = new Bike("B202", 500, "P456");
        Vehicle v3 = new Truck("T303", 3000, "P789");

        Vehicle[] vehicles = {v1, v2, v3};

        for (Vehicle v : vehicles) {
            System.out.println(v.getType() + " " + v.getVehicleNumber());
            System.out.println("Rental Cost (5 days): " + v.calculateRentalCost(5));
            if (v instanceof Insurable) {
                Insurable i = (Insurable) v;
                System.out.println("Insurance: " + i.calculateInsurance());
                System.out.println(i.getInsuranceDetails());
            }
            System.out.println();
        }
    }
}
