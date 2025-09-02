public class question17 {
   

    public static void main(String[] args) {

        var message = "Hello, Java 10!";
        System.out.println("Message: " + message);
        var count = 100;
        System.out.println("Count: " + count);
        var price = 9.99;
        System.out.println("Price: " + price);

        var list = new java.util.ArrayList<String>();
        list.add("Apple");
        list.add("Banana");
        System.out.println("List of fruits: " + list);

       
        for (var fruit : list) {
            System.out.println("Fruit: " + fruit);
        }
        
        

    }
}


