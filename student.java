public class student {
    String name;
    int rollno;
    double marks;
    void get(String a, int b, double c){
        name = a;
        rollno = b;
        marks = c;
}
    void grade(){
        if(marks>=90)
            System.out.println("Grade: A");
        else if(marks>=80)
            System.out.println("Grade: B");
        else if(marks>=70)
            System.out.println("Grade: C");
        else if(marks>=60)
            System.out.println("Grade: D");
        else
            System.out.println("Grade: F");
    }
    void display(){
        System.out.println("Name: "+name);
        System.out.println("Roll No: "+rollno);
        System.out.println("Marks: "+marks);
    }
    public static void main(String[] args) {
        student student = new student();
        student.get("nishant", 1, 95.5);
        student.display();
        student.grade();
    }
}   
