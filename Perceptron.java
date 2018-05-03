import java.util.ArrayList;
import java.util.Vector;

class Perceptron {
    //implement the perceptron algorithm
    double[] perceptronTrainingAlgorithm(int[][] trainingSample) {
        /* Initialize w to 0 */
        double[] w = new double[trainingSample[1].length];

        boolean correct = false;
        while (!correct) {
            correct = true;
            for (int[] instance : trainingSample)
                if (innerProduct(w, instance) * instance[0] <= 0) {
                    for (int i = 0; i < w.length; i++)
                        w[i] += instance[i+1] * instance[0];
                        correct = false;
                        break;
                }
        }
        return w;
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
