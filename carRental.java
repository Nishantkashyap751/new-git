public class carRental {
    String customerName, carModel;
    int rentalDays;
    double totalCost;
    carRental(){
        System.out.println("Default Constructor");
    }
    carRental(String customerName, String carModel, int rentalDays){
        this.customerName = customerName;
        this.carModel = carModel;
        this.rentalDays = rentalDays;
        System.out.println("Parameterized Constructor");
        totalCost = calculateTotalCost();
    }
    double calculateTotalCost(){
        double dailyRate = 50.0; 
        return rentalDays * dailyRate;
    }
    public static void main(String ar[]){
        carRental cr1 = new carRental();
        carRental cr2 = new carRental("Nishant Kashyap", "Toyota", 5);
        System.out.println("Customer Name: " + cr2.customerName);
        System.out.println("Car Model: " + cr2.carModel);
        System.out.println("Rental Days: " + cr2.rentalDays);
        System.out.println("Total Cost: " + cr2.totalCost);
    }

}
