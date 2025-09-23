class book {
    String title;
    int publicationyear;
    void displayinfo(){
        System.out.println("title:"+title);
        System.out.println("publication year"+publicationyear);
    }}
class author extends book{
    String name,bio;
    void displayinfo(){
        super.displayinfo();
        System.out.println("name:"+name);
        System.out.println("bio:"+bio);
    }
}
public class library{
    public static void main(String[] args){
        author a1=new author();
        a1.title="intersteller";
        a1.publicationyear=2004;
        a1.name="stephen hawking";
        a1.bio="scientist";
        a1.displayinfo();
    }
}