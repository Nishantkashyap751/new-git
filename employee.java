public class employee {
    public int employeeId;
    protected String department;
    private double salary;
    public void setsalary(double salary){
        this.salary=salary;
        System.out.println("Salary: " + salary);        
    }
}
class manager extends employee{
    public void setDetails(int employeeId,String department){
        this.employeeId=employeeId;
        this.department=department;
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Department: " + department);
    }
    public static void main(String ar[]){
        manager m=new manager();
        m.setDetails(101,"HR");
        m.setsalary(50000);
    }
}
