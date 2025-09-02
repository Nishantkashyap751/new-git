//15. Write a program to show memory allocation difference between primitive and reference variables.
public class question15 {

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    public static void main(String[] args) {

   
        System.out.println("--- Primitive Variables ---");
        int a = 10;
        int b = a;

        System.out.println("Initial values: a = " + a + ", b = " + b);

        a = 20;

        System.out.println("After changing 'a' to 20: a = " + a + ", b = " + b);
        System.out.println("The value of 'b' is unchanged. This shows primitive variables are independent.\n");



        System.out.println("--- Reference Variables ---");
 
        Point point1 = new Point(5, 10);
        
       
        Point point2 = point1;

        System.out.println("Initial values: point1 = " + point1 + ", point2 = " + point2);

        point1.x = 50;

        System.out.println("After changing point1.x to 50: point1 = " + point1 + ", point2 = " + point2);
        System.out.println("The value of 'point2' is also changed. Both variables point to the same object in memory.");
    }
}


