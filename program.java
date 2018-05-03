import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class program {
    public static void main(String [] args) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Type 'train' to train and 'test' to test.");
            String mode = scan.next();

            /* Parse the file */
            System.out.println("Input the file name: ");
            String filename = scan.next();
            CSVReader csvr = new CSVReader();
            int[][] vectors = csvr.read(filename);
            while (vectors == null) {
                System.out.println("Please re-enter the file name.");
                filename = scan.next();
                vectors = csvr.read(filename);
            }

            if ("train".equals(mode)) {
                System.out.print("Training the learner... please wait.");
                System.out.print("");
                System.out.println("Command not recognized.");
            } else if ("test".equals(mode)) {
                System.out.print("");
                System.out.println("Command not recognized.");
            } else {
                System.out.println("Command not recognized.");
            }
    }
}
