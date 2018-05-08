package cse353;

import java.util.ArrayList;

class Perceptron {
    final double ETA = .05;

    /* Implement the perceptron algorithm
       Returns the vector of weights
     */
    double[] perceptronTrainingAlgorithm(ArrayList<int[]> trainingSample) {
        System.out.println("Training sample size is " + trainingSample.size());

        double[] w = new double[trainingSample.get(1).length - 1];

        boolean correct = false;
        int n = 0;
        double[] oldw = new double[w.length];
        while (!correct && n < 100) {
            for (int i = 0; i < w.length; i++)
                oldw[i] = w[i];
            w = learn(trainingSample, w);

            // Checks for convergence of the algorithm
            for (int i = 0; i < w.length; i++) {
                if (w[i] != oldw[i])
                    break;
                correct = true;
            }

            /*
            System.out.print("This iteration has coefficients of: [");
            for (double k : w) {
                System.out.print(k + " ");
            }*/
            //System.out.println("]");
            n++;
        }

        return w;
    }

    double perceptronTestingAlgorithm(ArrayList<int[]> testingSample, double[] weightVector) {
        int correctOutput = 0;
        int totalIterations = testingSample.size();
        double result;
        //Is the domain incorrect? are we suppose to start with 1? do we set the first element
        // of the testing sample to something?
        for (int[] temp : testingSample) {
            //checks if the output is correct
            if (modInnerProduct(weightVector, temp) * temp[0] > 0) {
                correctOutput++;
            }
        }

        result = ((double) correctOutput) / (totalIterations);
        return result;
    }

    /* Performs one iteration of the perceptron learning algorithm */
    private double[] learn(ArrayList<int[]> sample, double[] w) {
        // First element of w is the bias, the others are weights
        int[] y = new int[sample.size()]; // vector of results y_i

        // Keep track of results
        for (int i = 0; i < y.length; i++) {
            y[i] = sample.get(i)[0];
        }

        // Rewrite to account for bias - added at end of each vector;
        ArrayList<int[]> trainingSample = new ArrayList<>(sample.size());
        for (int i = 0; i < sample.size(); i++) {
            sample.get(i)[sample.get(i).length-1] = 1;
            trainingSample.add(sample.get(i));
        }


        // Update the weight vector
        for (int i = 0; i < sample.size(); i++) {
            double innerProduct = modInnerProduct(w, sample.get(i));
            // If we have an incorrect output, update the weight vector
            if (innerProduct * y[i] <= 0) {
                for (int j = 0; j < w.length; j++) {
                    w[j] += ETA * y[i] * sample.get(i)[j+1];
                }
                return w;
            }
        }
        return w;
    }

    private double innerProduct(double[] w, int[] featureVector) {
        double sum = 0;
        for (int i = 0; i < w.length; i++) {
            sum += w[i] * featureVector[i];
        }
        return sum;
    }

    private double modInnerProduct(double[] w, int[] featureVector) {
        double sum = 0;
        for (int i = 0; i < w.length; i++) {
            sum += w[i] * featureVector[i+1];
        }
        return sum;
    }

    private int sign(double i) {
        if (i >= 0)
            return 1;
        else
            return -1;
    }
}