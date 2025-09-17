public class hotelbooking {
 String guestname,roomtype;
 int nights;
 hotelbooking(){
    System.out.println("Default Constructor");
 }
 hotelbooking(String guestname, String roomtype, int nights){
    this.guestname = guestname;
    this.roomtype = roomtype;
    this.nights = nights;
    System.out.println("Parameterized Constructor");
 }
 hotelbooking(hotelbooking h){
    this.guestname = h.guestname;
    this.roomtype = h.roomtype;
    this.nights = h.nights;
    System.out.println("Copy Constructor");
 }
 public static void main(String ar[]){
    hotelbooking h1 = new hotelbooking();

    hotelbooking h2 = new hotelbooking("nishant", "deluxe", 3);
    System.out.println("Guest Name: " + h2.guestname);
    System.out.println("Room Type: " + h2.roomtype);
    System.out.println("Nights: " + h2.nights);
    hotelbooking h3 = new hotelbooking(h2);
    System.out.println("Guest Name: " + h3.guestname);
    System.out.println("Room Type: " + h3.roomtype);
    System.out.println("Nights: " + h3.nights); 
}
}