public class student {
    public int rollno;
    protected String name;
    private String CGPA;
    public void modifycgpa(String CGPA){
        this.CGPA=CGPA;
        System.out.println("CGPA: " + CGPA);
    }}
    class postGraduateStudent extends student {
    public void display(){
        rollno=101;
        name="Nishant";
        System.out.println("Roll No: " + rollno);
        System.out.println("Name: " + name);
        modifycgpa("9.1");
    }
    public static void main(String ar[]){
        postGraduateStudent pgs=new postGraduateStudent();
        pgs.display();
        pgs.modifycgpa("9.5");
    }
    }
