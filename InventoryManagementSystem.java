class Item {
    public String itemName;
    public int itemID;
    public int quantity;
    public double price;
    public Item(String itemName, int itemID, int quantity, double price) {
        this.itemName = itemName;
        this.itemID = itemID;
        this.quantity = quantity;
        this.price = price;
    }
    @Override
    public String toString() {
        return "ID: " + itemID + ", Name: " + itemName + ", Qty: " + quantity + ", Price: $" + String.format("%.2f", price);
    }
}
class InventoryNode {
    public Item data;
    public InventoryNode next;
    public InventoryNode(Item data) {
        this.data = data;
        this.next = null;
    }
}
class InventoryManager {
    public InventoryNode head;
    public InventoryManager() {
        this.head = null;
    }
    // --- Core Operations ---
    public void addAtBeginning(Item newItem) {
        InventoryNode newNode = new InventoryNode(newItem);
        newNode.next = this.head;
        this.head = newNode;
        System.out.println(" Added to Start: " + newItem.itemName);
    }
    public void addAtEnd(Item newItem) {
        InventoryNode newNode = new InventoryNode(newItem);
        if (this.head == null) {
            this.head = newNode;
            return;
        }
        InventoryNode current = this.head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        System.out.println(" Added to End: " + newItem.itemName);
    }
    public void removeByID(int itemID) {
        if (this.head == null) {
            System.out.println(" Inventory is empty. Cannot remove.");
            return;
        }
        if (this.head.data.itemID == itemID) {
            System.out.println(" Removed: " + this.head.data.itemName);
            this.head = this.head.next;
            return;
        }
        InventoryNode current = this.head;
        InventoryNode prev = null;
        while (current != null && current.data.itemID != itemID) {
            prev = current;
            current = current.next;
        }
        if (current == null) {
            System.out.println(" Item with ID " + itemID + " not found.");
            return;
        }
        prev.next = current.next;
        System.out.println(" Removed: " + current.data.itemName);
    }
    public void calculateTotalValue() {
        InventoryNode current = this.head;
        double totalValue = 0.0;
        while (current != null) {
            totalValue += current.data.quantity * current.data.price;
            current = current.next;
        }
        System.out.println("\n Total Inventory Value: $" + String.format("%.2f", totalValue));
    }
    public void displayAllItems() {
        InventoryNode current = this.head;
        if (current == null) {
            System.out.println("The inventory is empty.");
            return;
        }
        System.out.println("\n--- Current Inventory ---");
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
        System.out.println("---------------------------\n");
    }
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();

        Item i1 = new Item("Laptop", 101, 10, 1200.50);
        Item i2 = new Item("Mouse", 102, 50, 25.00);
        Item i3 = new Item("Keyboard", 103, 30, 75.99);
        manager.addAtBeginning(i1); 
        manager.addAtEnd(i2);      
        manager.addAtEnd(i3);    
        manager.displayAllItems();
        manager.calculateTotalValue();
        // Test Removal
        manager.removeByID(102); 
        manager.displayAllItems();
        manager.calculateTotalValue();
        // Test removing non-existent
        manager.removeByID(999);
    }
}