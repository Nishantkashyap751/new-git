class Vehicle{
    Double speed;
    String model;
    void displayinfo(){
        System.out.println("Speed: "+speed);
        System.out.println("Model: "+model);
    }

}
class electricvehicle extends Vehicle{
    String batterytype;
    void charge(){
        System.out.println("Charging...");
    }
    void displayinfo(){
        super.displayinfo();
        System.out.println("Battery Type: "+batterytype);
    }
    }
interface refuelable{
    void refuelable();
}
class petrovehicle extends Vehicle implements refuelable{
    String fueltype;
    public void refuelable(){
        System.out.println("Refueling...");
    }
    void displayinfo(){
        super.displayinfo();
        System.out.println("Fuel Type: "+fueltype);
    }
}
public class vehiclemanagement {
    public static void main(String[] args) {
        electricvehicle ev=new electricvehicle();
        ev.speed=100.0;
        ev.model="Tesla Model S";
        ev.batterytype="Lithium-ion";
        ev.displayinfo();
        ev.charge();
        System.out.println();

        petrovehicle pv=new petrovehicle();
        pv.speed=120.0;
        pv.model="Honda Civic";
        pv.fueltype="Petrol";
        pv.displayinfo();
        pv.refuelable();
    }
    
}
