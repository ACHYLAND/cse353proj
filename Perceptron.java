import java.util.ArrayList;
import java.util.Vector;

class Perceptron {
    final double BIAS = 5;

    //implement the perceptron algorithm
    double[] perceptronTrainingAlgorithm(ArrayList<int[]> trainingSample) {
        System.out.println("Training sample size is " + trainingSample.size());

        double[] w = new double[trainingSample.get(1).length];

        for (int i = 0; i < 500; i++) {
            w = learn(trainingSample, w);
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
            if (sign(innerProduct(weightVector, temp)) == sign(temp[0])) {
                correctOutput++;
            }
        }

        result = ((double) correctOutput) / ((double) totalIterations);
        return result;
    }

    /* Performs one iteration of the perceptron learning algorithm */
    private double[] learn(ArrayList<int[]> trainingSample, double[] w) {
        // First element of w is the bias, the others are weights
        int[] y = new int[trainingSample.size()]; // vector of results y_i

        // Keep track of results
        for (int i = 0; i < y.length; i++) {
            y[i] = trainingSample.get(i)[0];
            trainingSample.get(i)[0] = 1; // First element of vector x is 1 (to account for bias when taking inner product)
        }

        for (int i = 0; i < trainingSample.size(); i++) {
            int output;
            double innerProduct = innerProduct(w, trainingSample.get(i));
            output = sign(innerProduct);
            for (int j = 0; j < w.length; j++)
                w[j] += (y[i] - output) * trainingSample.get(i)[j];
        }
        return w;
    }

    private double modInnerProduct(double[] w, int[] featureVector) {
        double sum = 0;
        /* Modified so we don't include the result y, just the feature vector */
        for (int i = 0; i < w.length; i++) {
            sum += w[i] * featureVector[i + 1];
        }
        return sum;
    }

    private double innerProduct(double[] w, int[] featureVector) {
        double sum = 0;
        for (int i = 0; i < w.length; i++) {
            sum += w[i] * featureVector[i];
        }
        return sum;
    }

    private int sign(double i) {
        if (i >= 0)
            return 1;
        else
            return 0;
    }
}