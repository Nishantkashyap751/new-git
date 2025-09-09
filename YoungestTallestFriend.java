import java.util.Scanner;

public class YoungestTallestFriend {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Amar's age: ");
        int ageAmar = sc.nextInt();
        System.out.print("Enter Akbar's age: ");
        int ageAkbar = sc.nextInt();
        System.out.print("Enter Anthony's age: ");
        int ageAnthony = sc.nextInt();

        System.out.print("Enter Amar's height: ");
        int heightAmar = sc.nextInt();
        System.out.print("Enter Akbar's height: ");
        int heightAkbar = sc.nextInt();
        System.out.print("Enter Anthony's height: ");
        int heightAnthony = sc.nextInt();

        // Youngest
        if (ageAmar <= ageAkbar && ageAmar <= ageAnthony)
            System.out.println("Youngest friend: Amar");
        else if (ageAkbar <= ageAmar && ageAkbar <= ageAnthony)
            System.out.println("Youngest friend: Akbar");
        else
            System.out.println("Youngest friend: Anthony");

        // Tallest
        if (heightAmar >= heightAkbar && heightAmar >= heightAnthony)
            System.out.println("Tallest friend: Amar");
        else if (heightAkbar >= heightAmar && heightAkbar >= heightAnthony)
            System.out.println("Tallest friend: Akbar");
        else
            System.out.println("Tallest friend: Anthony");

        sc.close();
    }
}