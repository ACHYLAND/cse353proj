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
                System.out.println("Training the learner... please wait.");
                Perceptron p = new Perceptron();
                System.out.println("Training the modified perceptron...");
                double[] w = p.perceptronTrainingAlgorithm(vectors);
                System.out.print("The output weight vector is: [");
                for (double i : w) {
                    System.out.print(i + " ");
                }
                System.out.print("]");
            } else if ("test".equals(mode)) {
                System.out.print("");
            } else {
                System.out.println("Command not recognized.");
            }
    }
}
