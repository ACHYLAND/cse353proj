package cse353;
import java.util.ArrayList;
import java.util.Scanner;


public class program {
    public static void main(String [] args) {
        Scanner scan = new Scanner(System.in);
        double[] weightVector = null; // the weightVector for perceptron
        Perceptron p = new Perceptron(); //Perceptron for
        linearRegression l = new linearRegression();
        ArrayList<double[]> vectors; //ArrayList of the input files

        System.out.println("Type 'p' to use the perceptron classifier or 'l' to use " +
                "the linear regression model");
        String model = scan.next();
        if (!("p".equals(model)||"l".equals(model)))
        {
            System.out.println("Unknown learning model");
            return;
        }

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

                if ("p".equals(model)) {
                    System.out.println("Training the modified perceptron...");
                    weightVector = p.perceptronTrainingAlgorithm(vectors);
                    System.out.print("The output weight vector is: [");
                } else if ("l".equals(model)) {
                    System.out.println("Training the linear regression model...");
                    weightVector = l.linearRegressionTraining(vectors);
                    System.out.print("The output line has coefficients of: [");
                }
                for (double i : weightVector) {
                    System.out.print(i + " ");
                }
                System.out.print("]");
                System.out.println();
            } else if ("test".equals(mode)) {
                System.out.println("Testing the learner... please wait.");
                double result = 0;
                if ("p".equals(model)) {
                    result = p.perceptronTestingAlgorithm(vectors, weightVector);
                } else if ("l".equals(model)) {
                    result = l.linearRegressionTestingAlgorithm(vectors, weightVector);
                }
                System.out.println("The accuracy is: " + result);
            } else {
                System.out.println("Command not recognized.");
            }
        }
    }
}
