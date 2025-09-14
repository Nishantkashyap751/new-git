public class findSmallestandLargestof3 {
    
    public findSmallestandLargestof3(int i, int j, int k) {
        int smallest = findSmallest(i, j, k);
        int largest = findLargest(i, j, k);
        System.out.println("Smallest: " + smallest);
        System.out.println("Largest: " + largest);
    }
    
    public static int findSmallest(int number1, int number2, int number3) {
        int smallest = number1;
        if (number2 < smallest) {
            smallest = number2;
        }
        if (number3 < smallest) {
            smallest = number3;
        }
        return smallest;
    }
    
    public static int findLargest(int number1, int number2, int number3) {
        int largest = number1;
        if (number2 > largest) {
            largest = number2;
        }
        if (number3 > largest) {
            largest = number3;
        }
        return largest;
    }
    
    public static void main(String[] args) {
        findSmallestandLargestof3 f1 = new findSmallestandLargestof3(3, 5, 7);
    }
}
