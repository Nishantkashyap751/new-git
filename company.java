class employee {
    String name;
    int id;
    double salary;
    void displaydetails(){
        System.out.println("Employee Name: "+name);
        System.out.println("Employee ID: "+id);
        System.out.println("Employee Salary: "+salary);
    }
}
class Manager extends employee{
    int teamsize;
    void displaydetails(){
        super.displaydetails();
        System.out.println("Team Size: "+teamsize);
    }
}
class Developer extends employee{
    String programmingLanguage;
    void displaydetails(){
        super.displaydetails();
        System.out.println("Programming Language: "+programmingLanguage);
    }
}
class Intern extends employee{
    String university;
    void displaydetails(){
        super.displaydetails();
        System.out.println("University: "+university);
    }
}
public class company{
    public static void main(String[] args) {
        employee e1=new Manager();
        e1.name="schinchan";
        e1.id=101;
        e1.salary=75000;    
        ((Manager)e1).teamsize=5;
        e1.displaydetails();
        System.out.println();

        employee e2=new Developer();
        e2.name="Nishant";
        e2.id=102;
        e2.salary=65000;
        ((Developer)e2).programmingLanguage="Java";
        e2.displaydetails();
        System.out.println();

        employee e3=new Intern();
        e3.name="bheem";
        e3.id=103;
        e3.salary=30000;
        ((Intern)e3).university="chitkara University";
        e3.displaydetails();
    }
}
