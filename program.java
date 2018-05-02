import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class program {
    public static void main(String [] args) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Type 'train' to train and 'test' to test.");
            String mode = scan.next();
            System.out.println("Input the file name: ");
            String filename = scan.next();

            CSVReader csvr = new CSVReader();
            ArrayList<Vector> vectors = csvr.read(filename);

            switch (mode) {
                case "train": System.out.print("Training the learner... please wait.");
                case "test": System.out.print("");
                default: System.out.println("Command not recognized.");
            }
    }
}
