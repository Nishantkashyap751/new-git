abstract class FoodItem {
    private String itemName;
    private double price;
    private int quantity;

    public FoodItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public abstract double calculateTotalPrice();

    public void getItemDetails() {
        System.out.println("Item: " + itemName + ", Price: " + price + ", Quantity: " + quantity);
    }
}

interface Discountable {
    void applyDiscount(double discount);
    String getDiscountDetails();
}

class VegItem extends FoodItem implements Discountable {
    private double discount;

    public VegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }

    public double calculateTotalPrice() {
        return getPrice() * getQuantity() - discount;
    }

    public void applyDiscount(double discount) {
        this.discount = discount;
    }

    public String getDiscountDetails() {
        return "Discount: " + discount;
    }
}

class NonVegItem extends FoodItem implements Discountable {
    private double discount;
    private double extraCharge = 50;

    public NonVegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }

    public double calculateTotalPrice() {
        return getPrice() * getQuantity() + extraCharge - discount;
    }

    public void applyDiscount(double discount) {
        this.discount = discount;
    }

    public String getDiscountDetails() {
        return "Discount: " + discount;
    }
}

public class OnlineFoodDeliverySystem {
    public static void main(String[] args) {
        FoodItem f1 = new VegItem("Paneer Curry", 200, 2);
        FoodItem f2 = new NonVegItem("Chicken Biryani", 300, 1);

        Discountable d1 = (Discountable) f1;
        Discountable d2 = (Discountable) f2;

        d1.applyDiscount(20);
        d2.applyDiscount(30);

        FoodItem[] order = {f1, f2};

        for (FoodItem item : order) {
            item.getItemDetails();
            System.out.println("Total Price: " + item.calculateTotalPrice());
            System.out.println(((Discountable)item).getDiscountDetails());
            System.out.println();
        }
    }
}
