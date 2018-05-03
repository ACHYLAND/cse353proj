import java.util.ArrayList;
import java.util.Vector;

class Perceptron {
    final double BIAS = 5;

    //implement the perceptron algorithm
    double[] perceptronTrainingAlgorithm(int[][] trainingSample) {
        System.out.println("Training sample size is " + trainingSample.length);

        /* Initialize w to 0 */
        double[] w = new double[trainingSample[1].length - 1]; // weight vector
        int[] y = new int[trainingSample.length]; // vector of results y_i

        for (int i = 0; i < y.length; i++) {
            y[i] = trainingSample[i][0];
            trainingSample[i][0] = 1; // First element of vector x is 1 (to account for bias when taking inner product)
        }

        boolean correct = false;
        int n = 0;
        while (!correct && n < 100000) {
            correct = true;
            for (int i = 0; i < trainingSample.length; i++)
                if (innerProduct(w, trainingSample[i]) * y[i] <= 0) {
                    for (int j = 0; j < w.length; j++)
                        w[j] += trainingSample[i][j+1] * y[i];
                        correct = false;
                        break;
                }
             n++;
        }
        return w;
    }



    double[] perceptronTestingAlgorithm(int[][] trainingSample)
    {

    }

    private double innerProduct(double[] w, int[] featureVector) {
        double sum = 0;
        /* Modified so we don't include the result y, just the feature vector */
        for (int i = 0; i < w.length; i++) {
            sum += w[i] * featureVector[i+1];
        }
        return sum;
    }
}
