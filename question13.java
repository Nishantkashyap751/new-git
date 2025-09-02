//
public class question13 {

    public static int sharedCounter = 0;

    public int objectID;

    public question13() {
        sharedCounter++;
        this.objectID = sharedCounter;
    }

    public static void main(String[] args) {
        System.out.println("Creating the first object...");
        question13 obj1 = new question13();
        System.out.println("obj1 has objectID: " + obj1.objectID);
        System.out.println("The sharedCounter is: " + question13.sharedCounter);

        System.out.println("\nCreating the second object...");
        question13 obj2 = new question13();
        System.out.println("obj2 has objectID: " + obj2.objectID);
        System.out.println("The sharedCounter is: " + question13.sharedCounter);

        System.out.println("\nNotice how each object has a unique objectID, but they both refer to the same sharedCounter.");
    }
}


