import java.util.Scanner;
public class Input 
{ 
   public static void main(String[] args) 
   { 
      Scanner in = new Scanner(System.in); 
      System.out.print("Enter an integer: "); 
      int m = in.nextInt(); 
      System.out.print("Enter another integer: "); 
      int n = in.nextInt(); 
      System.out.println(m + " " + n); 
   } 
}
