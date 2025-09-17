public class product {
    String productName;
    double price;
    int totalProducts;
    void displayproductdetails(){
        System.out.println("Product Name: " + productName);
        System.out.println("Price: " + price);
        System.out.println("Total Products: " + totalProducts);
    }
    void displaytotalproducts(int totalProducts){
        this.totalProducts=totalProducts;
        System.out.println("Total Products: " + totalProducts);
    }
    public static void main(String ar[]){
        product p=new product();
        p.productName="Laptop";
        p.price=80000;
        p.totalProducts=5;
        p.displayproductdetails();
        p.displaytotalproducts(10);
    }
}