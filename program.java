
import java.util.Scanner;

public class program {
    public static void main(String [] args) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Type 'train' to train and 'test' to test.");
            String mode = scan.next();
            System.out.println("Input the file name: ");
            String filename = scan.next();

            if (mode.equals("train")) {
                System.out.print("");
            }
            else if (mode.equals("test")) {
                System.out.print("");
            }
            else {
                System.out.println("Command not recognized.");
            }
    }
}
