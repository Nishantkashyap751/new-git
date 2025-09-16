public class movieticket {
    String movieName;
    int seatnumber;
    double price;
    void get(String name, int p){
        movieName = name;
        price = p;
    }
    void bookingticket(){
        System.out.println("Ticket booked for " + movieName + " at seat number " + seatnumber + " with price $" + price);
    }
    public static void main(String[] args) {
        movieticket mt = new movieticket();
        mt.get("Interstellar", 1200);
        mt.bookingticket();
    }
}
