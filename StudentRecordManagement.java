class Student {
    public int rollNumber;
    public String name;
    public int age;
    public String grade;
    private int rollNumber;
    private String name;
    private int age;
    private String grade;

    public Student(int rollNumber, String name, int age, String grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    // --- Getters to access private fields ---
    public int getRollNumber() { return rollNumber; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGrade() { return grade; }

    // --- Setter to modify a field in a controlled way ---
    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Roll No: " + rollNumber + ", Name: " + name + ", Grade: " + grade;
    }
}

class Node {
    public Student data;
    public Node next;
    Student data; // package-private is fine for an inner/helper class
    Node next;   // package-private is fine for an inner/helper class

    public Node(Student data) {
        this.data = data;
        this.next = null;
    }
}

class StudentRecordManagement {
    public Node head;
    private Node head;

    public StudentRecordManagement() {
        this.head = null;
    }

    public void addAtEnd(Student newStudent) {
        Node newNode = new Node(newStudent);

        if (this.head == null) {
            this.head = newNode;
            return;
        }

        Node current = this.head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
        System.out.println(" Added: " + newStudent.name);
        System.out.println(" Added: " + newStudent.getName());
    }

    public boolean updateGrade(int rollNumber, String newGrade) {
        Node current = this.head;
        while (current != null) {
            if (current.data.getRollNumber() == rollNumber) {
                current.data.setGrade(newGrade);
                System.out.println("Updated grade for " + current.data.getName() + " to " + newGrade);
                return true; // Found and updated
            }
            current = current.next;
        }
        System.out.println("Student with Roll No " + rollNumber + " not found.");
        return false; // Not found
    }

    public void displayAllRecords() {
        Node current = this.head;
        if (current == null) {
            System.out.println("The student record list is empty.");
            return;
        }

        System.out.println("\n--- Current Student Records ---");
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
        System.out.println("-----------------------------\n");
    }

    public static void main(String[] args) {
        StudentRecordManagement list = new StudentRecordManagement();

        Student s1 = new Student(101, "Alice", 20, "A+");
        Student s2 = new Student(102, "Bob", 21, "B");
        
        list.addAtEnd(s1);
        list.addAtEnd(s2);
        
        list.displayAllRecords();

        System.out.println("--- Public Field Demonstration ---");
        list.head.data.grade = "C-";
        System.out.println("Directly manipulated Alice's grade.");
        
        System.out.println("--- Controlled Update Demonstration ---");
        // Instead of direct manipulation, we use a method.
        list.updateGrade(101, "C-");

        list.displayAllRecords();
    }
}