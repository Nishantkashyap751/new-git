class SUser {
    public int userID;
    public String name;
    public int age;
    public int[] friendIDs;
    
    public SUser next;

    public SUser(int userID, String name, int age, int[] friendIDs) {
        this.userID = userID;
        this.name = name;
        this.age = age;
        this.friendIDs = friendIDs;
        this.next = null;
    }
}
class SocialMedia {
    public SUser head;

    public SocialMedia() {
        this.head = null;
    }
    public SUser findUserByID(int userID) {
        SUser current = this.head;
        while (current != null) {
            if (current.userID == userID) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
    public void addUser(SUser newUser) {
        if (this.head == null) {
            this.head = newUser;
            System.out.println("Added user: " + newUser.name);
            return;
        }
        SUser current = this.head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newUser;
        System.out.println("Added user: " + newUser.name);
    }
    public void displayFriends(int userID) {
        SUser user = findUserByID(userID);
        if (user == null) {
            System.out.println("User ID " + userID + " not found.");
            return;
        }
        System.out.print("Friends of " + user.name + " (ID " + userID + "): ");
        if (user.friendIDs == null || user.friendIDs.length == 0) {
            System.out.println("No friends listed.");
            return;
        }
        for (int i = 0; i < user.friendIDs.length; i++) {
            System.out.print(user.friendIDs[i] + (i < user.friendIDs.length - 1 ? ", " : ""));
        }
        System.out.println();
    } 
    public void countFriends(int userID) {
        SUser user = findUserByID(userID);     
        if (user == null) {
            System.out.println("User ID " + userID + " not found.");
            return;
        }       
        int count = (user.friendIDs != null) ? user.friendIDs.length : 0;
        System.out.println(user.name + " (ID " + userID + ") has " + count + " friends.");
    }  
    public static void main(String[] args) {
        SocialMedia manager = new SocialMedia();

        SUser u1 = new SUser(101, "Nishant", 21, new int[]{102, 103});
        SUser u2 = new SUser(102, "Rajat", 22, new int[]{101, 103, 104});
        SUser u3 = new SUser(103, "Manish", 23, new int[]{101, 102});
        SUser u4 = new SUser(104, "Yash", 22, new int[]{102});
        
        manager.addUser(u1);
        manager.addUser(u2);
        manager.addUser(u3);
        manager.addUser(u4);
        
        manager.displayFriends(102);
        manager.displayFriends(104);
        
        manager.countFriends(101);
        manager.countFriends(102);
        manager.countFriends(103);
    }
}