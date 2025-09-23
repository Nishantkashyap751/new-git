public class vehicle {
    String vehicle;
    double maxspeed;
    String fueltype;
    void displayinfo(){
        System.out.println("Vehicle: "+vehicle);
        System.out.println("Max Speed: "+maxspeed);
        System.out.println("Fuel Type: "+fueltype);
    }
}
class car extends vehicle{
    int seatcapacity;
    void displayinfo(){
        super.displayinfo();
        System.out.println("Seat Capacity: "+seatcapacity);
    }    
}
class truck extends vehicle{
    double loadcapacity;
    void displayinfo(){
        super.displayinfo();
        System.out.println("Load Capacity: "+loadcapacity);
    }    
}
class motorcycle extends vehicle{
    double mileage;
    void displayinfo(){
        super.displayinfo();
        System.out.println("Mileage: "+mileage );
    }
}
class VehicleMain{
    public static void main(String[] args) {
        vehicle v1=new car();
        v1.vehicle="Sedan";
        v1.maxspeed=180;
        v1.fueltype="Petrol";
        ((car)v1).seatcapacity=5;
        v1.displayinfo();
        System.out.println();

        vehicle v2=new truck();
        v2.vehicle="Pickup Truck";
        v2.maxspeed=140;
        v2.fueltype="Diesel";
        ((truck)v2).loadcapacity=2000;
        v2.displayinfo();
        System.out.println();

        vehicle v3=new motorcycle();
        v3.vehicle="Sport Bike";
        v3.maxspeed=220;
        v3.fueltype="Petrol";
        ((motorcycle)v3).mileage=25;
        v3.displayinfo();
    }
}
