public class circle {
    double radius;
    circle(){
        this(40);
        System.out. println("Default Constructor");
    }
    circle(double r){
        this.radius=r;
        System.out. println("Parameterized Constructor");
    }
    public static void main(String ar[]){
        circle c=new circle();
        System.out.println("Radius: "+c.radius);
    }
}
