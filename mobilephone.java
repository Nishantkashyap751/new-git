public class mobilephone {
    String brand,model;
    double price;
    void get(String a, String b, double c){
        brand = a;
        model = b;
        price = c;
    }
    void display(){
        System.out.println("Brand: "+brand);
        System.out.println("Model: "+model);
        System.out.println("Price: "+price);
    }
    public static void main(String[] args) {
        mobilephone phone = new mobilephone();
        phone.get("Apple", "iPhone 17", 999.99);
        phone.display();
    }}

