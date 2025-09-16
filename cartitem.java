public class cartitem {
    String itemName;
    double price;
    int quantity;

    public cartitem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}

class ShoppingCart {
    cartitem[] items = new cartitem[10]; 
    int count = 0;

    void addItem(cartitem item) {
        if (count < items.length) {
            items[count] = item;
            count++;
        } else {
            System.out.println("Cart is full.");
        }
    }

    void removeItem(String itemName) {
        for (int i = 0; i < count; i++) {
            if (items[i].itemName.equals(itemName)) {
                for (int j = i; j < count - 1; j++) {
                    items[j] = items[j + 1];
                }
                items[count - 1] = null;
                count--;
                break;
            }
        }
    }

    double getTotalCost() {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += items[i].price * items[i].quantity;
        }
        return total;
    }

    void displayCart() {
        for (int i = 0; i < count; i++) {
            System.out.println(items[i].itemName + " - " + items[i].quantity + " x " + items[i].price);
        }
        System.out.println("Total Cost: " + getTotalCost());
    }
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new cartitem("Apple", 0.99, 3));
        cart.addItem(new cartitem("Banana", 0.59, 2));
        cart.displayCart();
        cart.removeItem("Apple");
    }
}