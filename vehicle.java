public class vehicle {
    static double registrationFee=100000.0;
    String ownername;
    String vehicleType;
    final int registrationNumber;
    public static void displayRegistrationFee(){
        System.out.println("Registration fee: "+registrationFee);
}
    vehicle(String ownername,String vehicleType,int registrationNumber){
        this.ownername=ownername;
        this.vehicleType=vehicleType;
        this.registrationNumber=registrationNumber;
        if(this instanceof vehicle) {
            System.out.println("Owner name: " + ownername);
            System.out.println("Vehicle type: " + vehicleType);
            System.out.println("Registration number: " + registrationNumber);
            System.out.println("Registration fee: " + registrationFee);
        } else {
            System.out.println("Object is not an instance of vehicle. Details not displayed.");
        }
    }
    public static void main(String[]args){
        vehicle v1=new vehicle("Nishant","Super Car",1234);
        System.out.println("--------------------------------------------------");
        vehicle v2=new vehicle("Kashyap","Private Jet",5678);
        vehicle.displayRegistrationFee();
        System.out.println("Registration fee: "+registrationFee);
    }}
