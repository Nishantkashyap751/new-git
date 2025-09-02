//7. Write a program to print default values of instance variables in a class.
class question7 {

    public static void main(String ar[]){
        sample obj=new sample();
        System.out.println("Default long value: " + obj.value);
    }

static class sample{
        int value;
    };
}