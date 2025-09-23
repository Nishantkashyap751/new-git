class course{
    String coursename;
    int duration;
    void displaycourseinfo(){
        System.out.println("Course Name: "+coursename);
        System.out.println("Course Code: "+duration);
    }
}
class onlinecourse extends course{
    String platform;
    boolean isrecorded;
    void displaycourseinfo(){
        super.displaycourseinfo();
        System.out.println("platform name: "+platform);
        System.out.println("is recorded: "+isrecorded);
    }
}
class paidonlinecource extends onlinecourse{
    Double fees;
    int discount;
    void displaycourseinfo(){
        super.displaycourseinfo();
        System.out.println("Fees: "+fees);
        System.out.println("Discount: "+discount);
    }
}

public class schoolcourse {
 public static void main(String[] args) {
        paidonlinecource p1=new paidonlinecource();
        p1.coursename="Java Programming";
        p1.duration=45;
        p1.platform="bridgelabz";
        p1.isrecorded=true;
        p1.fees=100.00;
        p1.discount=10;
        p1.displaycourseinfo();
    }
   
}
