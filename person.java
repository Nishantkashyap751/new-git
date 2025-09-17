public class person {
    String name = "John";
    person(){
        System.out.println("Default Constructor");
    }
    person(String name){
        System.out.println("Parameterized Constructor with name: "+name);
    }
    person(person p){
        System.out.println("copy constructor");
    }
    public static void main(String ar[]){
        person p1=new person();
        System.out.println("Name: "+p1.name);
        person p2=new person("Alice");
        person p3=new person(p1);

    }
}