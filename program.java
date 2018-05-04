import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class program {
    public static void main(String [] args) {
        Scanner scan = new Scanner(System.in);
        double[] weightVector = null; // the weightVector for perceptron
        Perceptron p = new Perceptron(); //Perceptron for
        ArrayList<int[]> vectors; //ArrayList of the input files

        while (true) {
            System.out.println("Type 'train' to train, 'test' to test, 'exit' to exit.");
            String mode = scan.next();
            if ("exit".equals(mode)) {
                return;
            }
            /* Parse the file */
            System.out.println("Input the file name: ");
            String filename = scan.next();
            CSVReader csvr = new CSVReader();
            vectors = csvr.read(filename);
            while (vectors == null) {
                System.out.println("Please re-enter the file name.");
                filename = scan.next();
                vectors = csvr.read(filename);
            }

            if ("train".equals(mode)) {
                System.out.println("Training the learner... please wait.");

                System.out.println("Training the modified perceptron...");
                weightVector = p.perceptronTrainingAlgorithm(vectors);
                System.out.print("The output weight vector is: [");
                for (double i : weightVector) {
                    System.out.print(i + " ");
                }
                System.out.print("]");
                System.out.println();
            } else if ("test".equals(mode)) {
                System.out.println("Testing the learner... please wait.");
                double result = p.perceptronTestingAlgorithm(vectors,weightVector);
                System.out.println("The perceptron accuracy is: " + result);
            } else {
                System.out.println("Command not recognized.");
            }
        }
    }
}
