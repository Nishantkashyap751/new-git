public class course {
    String courseName;
    int duration;
    double fee;
    String institutename;
    void displaycoursedetails(){
        System.out.println("Course Name: " + courseName);
        System.out.println("Duration: " + duration + " months");
        System.out.println("Fee: " + fee);
        System.out.println("Institute Name: " + institutename);
    }
    public void updateinstitutename(String institutename){
        this.institutename=institutename;
        System.out.println("Institute Name: " + institutename);
    }
    public static void main(String ar[]){
        course c=new course();
        c.courseName="Java";
        c.duration=6;
        c.fee=30000;
        c.institutename="Aptech";
        c.displaycoursedetails();
        c.updateinstitutename("Allan");
    }
}
