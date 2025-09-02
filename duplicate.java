import java.util.Arrays;
import java.util.Scanner;
public class duplicate{
    
    int n;
    int ar[];
    int ar1[];
    Scanner s1=new Scanner(System.in);
    void add(){
        System.out.println("enter the size");
        n= s1.nextInt();
        ar=new int[n];
        System.out.println("========================");
        for (int i=0;i<n;i++){
            System.out.println("enter the numbers  in array");
            ar[i]=s1.nextInt();
            Arrays.sort(ar);
        }
    }
    void remove(){
            ar1=new int[n];
            int j=0;
       for (int i = 0; i < n; i++) {
            // If current element is the first element or different from previous, add it
            if (i == 0 || ar[i] != ar[i - 1]) {
                ar1[j++] = ar[i];
            }
        }
        ar1=Arrays.copyOf(ar1, j);
    }
    void display(){
        
        for (int i=0;i<ar1.length;i++){
        
            System.out.println("numbers are sorted without duplicates : " + ar1[i]+" ");

        }
        
    }
    public static void main(String []ar){
       duplicate d1=new duplicate();
       d1.add();
       d1.remove();
       d1.display();
       

    }

}