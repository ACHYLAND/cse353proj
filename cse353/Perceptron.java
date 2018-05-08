package cse353;

import java.util.ArrayList;
import java.util.Vector;

class Perceptron {
    final double BIAS = 5;

    //implement the perceptron algorithm
    double[] perceptronTrainingAlgorithm(ArrayList<double[]> trainingSample) {
        System.out.println("Training sample size is " + trainingSample.size());

        /* Initialize w to 0 */
        double[] w = new double[trainingSample.get(1).length - 1]; // weight vector
        double[] y = new double[trainingSample.size()]; // vector of results y_i

        for (int i = 0; i < y.length; i++) {
            y[i] = trainingSample.get(i)[0];
            trainingSample.get(i)[0] = 1; // First element of vector x is 1 (to account for bias when taking inner product)
        }

        boolean correct = false;
        int n = 0;
        while (!correct && n < 100000) {
            correct = true;
            for (int i = 0; i < trainingSample.size(); i++)
                if (innerProduct(w, trainingSample.get(i)) * y[i] <= 0) {
                    for (int j = 0; j < w.length; j++)
                        w[j] += trainingSample.get(i)[j+1] * y[i];
                        correct = false;
                        break;
                }
             n++;
        }
        return w;
    }



    double perceptronTestingAlgorithm(ArrayList<double[]> testingSample, double[]weightVector) {
        int correctOutput = 0;
        int totalIterations = testingSample.size();
        double result = 0;
        //Is the domain incorrect? are we suppose to start with 1? do we set the first element
        // of the testing sample to something?
        for(int i = 0; i < testingSample.size(); i++){
            //checks if the output is correct
            double[] temp = testingSample.get(i);
            if ((innerProduct(weightVector, temp) > 0 &&temp[0] > 0)
                    || (innerProduct(weightVector, temp) < 0 &&temp[0] < 0)){
                correctOutput++;
            }
        }

        result = ((double)correctOutput)/((double)totalIterations);
        return result;
    }

    private double innerProduct(double[] w, double[] featureVector) {
        double sum = 0;
        /* Modified so we don't include the result y, just the feature vector */
        for (int i = 0; i < w.length; i++) {
            sum += w[i] * featureVector[i+1];
        }
        return sum;
    }
}
