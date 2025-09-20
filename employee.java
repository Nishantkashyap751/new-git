public class employee {
    static String companyName="BridgeLabz";
    String Name, Designation;   
    final int ID;

    public static int displayTotalEmployees(){
        return 50;
    }
    employee(String Name, int ID,String Designation){
        this.ID = ID;
        this.Name = Name;
        this.Designation=Designation;
        if(this instanceof employee) {
            this.Name = Name;
            System.out.println("Employee name: " + Name);
            System.out.println("Employee ID: " + ID);
            System.out.println("Employee Designation: " + Designation);
            System.out.println("Company name: " + companyName);
        } else {
            System.out.println("Object is not an instance of employee. Details not displayed.");
        }
    }
    public static void main(String[] args){
        int total;
        employee e1 = new employee("Nishant", 7031,"Developer");
        employee e2 = new employee("Kashyap", 07031,"Manager"  );
        total=employee.displayTotalEmployees();
        System.out.println("Total employees: "+total);
    }
    
}
