class Person{
    String name;
    int age;
    void displayrole(){
        System.out.println("Name: "+name);
        System.out.println("Age: "+age);
    }
}
class student extends Person{
    int studentid;
    String Grade;
    void displayrole(){
        super.displayrole();
        System.out.println("Student ID: "+studentid);
        System.out.println("Grade:"+Grade);
    }
}
class teacher extends Person{
    String subject;
    void displayrole(){
        super.displayrole();
        System.out.println("Subject: "+subject);
    }

}
class staff extends Person{
    String department;
    void displayrole(){
        super.displayrole();
        System.out.println("Department: "+department);
    }
}
public class schoolsystem {
    public static void main(String ar[]){
        Person p1=new student();
        Person p2=new teacher();
        Person p3=new staff();
        ((student)p1).name="Nishant";
        ((student)p1).age=20;
        ((student)p1).studentid=101;
        ((student)p1).Grade="A";
        p1.displayrole();
        System.out.println();
        ((teacher)p2).name="bheem";
        ((teacher)p2).age=30;
        ((teacher)p2).subject="Maths";
        p2.displayrole();
        System.out.println();
        ((staff)p3).name="Batch2025";
        ((staff)p3).age=0;
        ((staff)p3).department="Computer Application";
        p3.displayrole();
    }
}
