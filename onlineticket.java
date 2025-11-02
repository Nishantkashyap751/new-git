class Ticket {
    public int ticketID;
    public String customerName;
    public String movieName;
    public String seatNumber;
    public String bookingTime;

    public Ticket(int id, String customer, String movie, String seat, String time) {
        this.ticketID = id;
        this.customerName = customer;
        this.movieName = movie;
        this.seatNumber = seat;
        this.bookingTime = time;
    }

    @Override
    public String toString() {
        return "ID: " + ticketID + ", Customer: " + customerName + ", Movie: " + movieName + ", Seat: " + seatNumber;
    }
}

class TicketNode {
    public Ticket data;
    public TicketNode next; // Points to the next node in the circle

    public TicketNode(Ticket data) {
        this.data = data;
        this.next = null;
    }
}

class ReservationSystem {
    public TicketNode head;
    public TicketNode tail; 

    public ReservationSystem() {
        this.head = null;
        this.tail = null;
    }

    

    public void addReservation(Ticket newTicket) {
        TicketNode newNode = new TicketNode(newTicket);

        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            newNode.next = this.head;
        } else {
            this.tail.next = newNode;
            newNode.next = this.head; 
            this.tail = newNode;
        }
        System.out.println("Added: " + newTicket.customerName + " for " + newTicket.movieName);
    }

    public void removeTicketByID(int ticketID) {
        if (this.head == null) {
            System.out.println("Cannot remove. System is empty.");
            return;
        }

        
        if (this.head == this.tail && this.head.data.ticketID == ticketID) {
            System.out.println("Removed: " + this.head.data.customerName + " (ID " + ticketID + ")");
            this.head = null;
            this.tail = null;
            return;
        }

        TicketNode current = this.head.next;
        TicketNode prev = this.head;

        
        if (this.head.data.ticketID == ticketID) {
            System.out.println("Removed: " + this.head.data.customerName + " (ID " + ticketID + ")");
            this.head = this.head.next;
            this.tail.next = this.head; 
            return;
        }

       
        while (current != this.head) {
            if (current.data.ticketID == ticketID) {
                System.out.println("Removed: " + current.data.customerName + " (ID " + ticketID + ")");
                prev.next = current.next; 

                
                if (current == this.tail) {
                    this.tail = prev;
                }
                return;
            }
            prev = current;
            current = current.next;
        }
        
        System.out.println("Ticket ID " + ticketID + " not found.");
    }

    public void displayAllTickets() {
        if (this.head == null) {
            System.out.println("The reservation list is empty.");
            return;
        }

        TicketNode current = this.head;
        System.out.println("\n--- Current Ticket Reservations ---");
        
        do {
            System.out.println(current.data);
            current = current.next;
        } while (current != this.head); 

        System.out.println("-----------------------------------\n");
    }
    
  

    public void countTotalBookedTickets() {
        if (this.head == null) {
            System.out.println("Total booked tickets: 0");
            return;
        }

        int count = 0;
        TicketNode current = this.head;
        
        do {
            count++;
            current = current.next;
        } while (current != this.head);

        System.out.println("Total booked tickets: " + count);
    }


    public static void main(String[] args) {
        ReservationSystem system = new ReservationSystem();

        Ticket t1 = new Ticket(1001, "Nishant", "yara", "A1", "10:00");
        Ticket t2 = new Ticket(1002, "Kashyap", "golmal", "B5", "11:30");
        Ticket t3 = new Ticket(1003, "chotu", "Dhamal", "C2", "10:00");
        
        system.addReservation(t1);
        system.addReservation(t2);
        system.addReservation(t3);
        
        system.displayAllTickets();
        system.countTotalBookedTickets();

        // Test Removal
        system.removeTicketByID(1002); 
        system.displayAllTickets();
        
        system.removeTicketByID(1001); 
        system.displayAllTickets();
        
        system.countTotalBookedTickets();
    }
}