import java.util.Scanner;
public class employee {
    Scanner s1=new Scanner(System.in);
    String name,designation;
    int id;
    double salary;
    void get(){
        System.out.print("Enter Name: ");
        name = s1.nextLine();
        System.out.print("Enter ID: ");
        id = s1.nextInt();
        s1.nextLine();
        System.out.print("Enter Designation: ");
        designation = s1.nextLine();
        System.out.print("Enter Salary: ");
        salary = s1.nextDouble();
    }
    void display(){
        System.out.println("Name: "+name);
        System.out.println("ID: "+id);
        System.out.println("Designation: "+designation);
        System.out.println("Salary: "+salary);
    }
    public static void main(String[] args) {
        employee emp = new employee();
        emp.get();
        emp.display();
    }

}