public class question18 {

    public static void main(String[] args) {

        System.out.println("--- Integer Overflow and Underflow ---");

        int maxInt = Integer.MAX_VALUE;
        System.out.println("Integer.MAX_VALUE: " + maxInt);

    
        int overflowInt = maxInt + 1;
        System.out.println("Integer.MAX_VALUE + 1: " + overflowInt);
        System.out.println("Expected output is " + Integer.MIN_VALUE + " (wrap-around).\n");

        int minInt = Integer.MIN_VALUE;
        System.out.println("Integer.MIN_VALUE: " + minInt);

       
        int underflowInt = minInt - 1;
        System.out.println("Integer.MIN_VALUE - 1: " + underflowInt);
        System.out.println("Expected output is " + Integer.MAX_VALUE + " (wrap-around).\n");

        System.out.println("--- Floating-Point Overflow and Underflow ---");

        double maxDouble = Double.MAX_VALUE;
        System.out.println("Double.MAX_VALUE: " + maxDouble);

       
        double overflowDouble = maxDouble * 2;
        System.out.println("Double.MAX_VALUE * 2: " + overflowDouble);
        System.out.println("Expected output is Infinity.\n");

        double minDouble = Double.MIN_VALUE;
        System.out.println("Double.MIN_VALUE: " + minDouble);

        
        double underflowDouble = minDouble / 1000000;
        System.out.println("Double.MIN_VALUE / 1000000: " + underflowDouble);
        System.out.println("Expected output is 0.0.");
    }
}


