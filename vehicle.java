public class vehicle {
    String ownername,vehicletype;
    double registrationfee;
    void setDetails(String ownername,String vehicletype,double registrationfee){
        this.ownername=ownername;
        this.vehicletype=vehicletype;       
        this.registrationfee=registrationfee;
        System.out.println("Owner Name: " + ownername);
        System.out.println("Vehicle Type: " + vehicletype);
        System.out.println("Registration Fee: " + registrationfee);
}
void updateRegistrationFee(double newFee){
    this.registrationfee=newFee;
    System.out.println("Updated Registration Fee: " + registrationfee);
}
    public static void main(String ar[]){
        vehicle v=new vehicle();
        v.setDetails("Nishant","Car",1500.50);
        v.updateRegistrationFee(2000.75);
        
    }
}
