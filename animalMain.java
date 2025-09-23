class animal{
    String name;
    int age;
    void makesound(){
        System.out.println("Animal sound");
    }
}
class Dog extends animal{
    void makesound(){
        System.out.println("Bark");
    }
    void displaydetails(){
        System.out.println("Name: "+name);
        System.out.println("Age: "+age);
    }
    
}
class cat extends animal{
    void makesound(){
        System.out.println("Meow");
    }
    void displaydetails(){
        System.out.println("Name: "+name);
        System.out.println("Age: "+age);
    }
}
class bird extends animal{
    void makesound(){
        System.out.println("Chirp");
    }
    void displaydetails(){
        System.out.println("Name: "+name);
        System.out.println("Age: "+age);
    }
}
public class animalMain{
    public static void main(String[] args) {
        animal a1=new Dog();
        animal a2=new cat();
        animal a3=new bird();
        a1.makesound();
        ((Dog)a1).name="sheru";
                ((Dog)a1).age=2;

        a2.makesound();
                ((cat)a2).name="kitty";
        ((Dog)a2).age=1;

        a3.makesound();
                ((bird)a3).name="baaj";
        ((Dog)a3).age=4;

    }
}