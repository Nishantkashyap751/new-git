public class student {
    static String universityname="Chitkara university";
    String name;
    String grade;
    final int rollnumber;
    public static int displayTotalStudent(){
        return 5000;
    }
    student(String name,String grade,int rollnumber){
        this.name=name;
        this.grade=grade;
        this.rollnumber=rollnumber;
        if(this instanceof student) {
            System.out.println("Student name: " + name);
            System.out.println("Grade: " + grade);
            System.out.println("Roll number: " + rollnumber);
            System.out.println("University name: " + universityname);
        } else {
            System.out.println("Object is not an instance of student. Details not displayed.");
        }
    }
    public static void main(String[]args){
        int total;
        student s1=new student("Nishant","A+",7031);
        student s2=new student("Kashyap","O",07031);
        total=student.displayTotalStudent();
        System.out.println("Total students: "+total);
    }
}
