public class item {
    int itemcode;
    double price;
    String itemname;
    void get(int a, double b, String c){
        itemcode = a;
        price = b;
        itemname = c;}
        void display(){
            System.out.println("Item Code: "+itemcode);
            System.out.println("Item Name: "+itemname);
            System.out.println("Price: "+price);
        }
        public static void main(String[] args) {
            item item = new item();
            item.get(101, 29.99, "Notebook");
            item.display();
        }
}

