public class product {
    static int discount=10;
    String productName;
    double price;
    int quantity;
    final int productID;
    public static void  UpdateDiscount(){
        System.out.println("UpdateDiscount: " + (discount+5));
    }
    product(String productName,double price,int quantity,int productID){
        this.productName=productName;
        this.price=price;
        this.quantity=quantity;
        this.productID=productID;
        if(this instanceof product) {
            System.out.println("Product Name: " + productName);
            System.out.println("Price: " + price);
            System.out.println("Quantity: " + quantity);
            System.out.println("Product ID: " + productID);
            System.out.println("Discount: " + discount + "%");
        } else {
            System.out.println("Object is not an instance of product. Details not displayed.");
        }
    }
    public static void main(String[]args){
        product p1=new product("Iphone",150000,2,101);
        System.out.println("--------------------------------------------------");
        product p2=new product("Ipad",200000,3,102);
        product.UpdateDiscount();
        System.out.println("Discount after update: " + discount);
    }}
