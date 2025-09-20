public class patient {
    static String hospitalname="LastLife Hospital";
    String name;
    int age;
    String ailment;
    static int totalpatients;
    final int patientID;
    public static int getTotalPatients(){
        return totalpatients=12;
}
    patient(String name,int age,String ailment,int patientID){
        this.name=name;
        this.age=age;
        this.ailment=ailment;
        this.patientID=patientID;
        if(this instanceof patient) {
            System.out.println("Patient name: " + name);
            System.out.println("Age: " + age);
            System.out.println("Ailment: " + ailment);
            System.out.println("Patient ID: " + patientID); 
            System.out.println("Hospital name: " + hospitalname);
        } else {
            System.out.println("Object is not an instance of patient. Details not displayed.");
        }
    }
    public static void main(String[]args){
        int total;
        patient p1=new patient("Nishant",21,"Fever",101);
        patient p2=new patient("Kashyap",22,"Cold",102);
        total=patient.getTotalPatients();
        System.out.println("Total patients: "+total);
    }
}
