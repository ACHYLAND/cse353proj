import java.util.ArrayList;

public class linearRegression {
    //implement the linearRegression algorithm
    public  double[] linearRegressionTrainingAlgorithm(ArrayList<int[]> trainingSample)
    {
        //let the initial hypothesis be the average the value of each point
        double[] line = new double[trainingSample.get(1).length];
        double[] error;
        double minimumError;
        double precision = 0.0001;
        double errorPercentDifference = 1;
        double lastError = 0;
        double adjustment = .2;

        while(errorPercentDifference > precision) {
            //run the least squares algorithm to find the error and error vector
            error = leastSquaresAlgorithm(trainingSample, line);
            //adjust the line based on the error vector
            for(int i = 1; i < error.length;i++)
            {

            }


            //update the adjustment, last error, errorPercentDifference for next iteration
            if (error[0] > lastError)
                adjustment /= 2;
            errorPercentDifference = Math.abs(((double)lastError - error[0])/((double)error[0]));
            lastError = error[0];
        }
        return line;
    }

    //each iteration of finding the squared error of the hypothesis
    // result[0] is the total square error, other indicies indicate testing error
    private double [] leastSquaresAlgorithm (ArrayList<int[]> trainingSample, double[]hypothesis)
    {
        double totalError = 0;
        double [] errorVector = new double[hypothesis.length];

        //multiply the training sample by the hypothesis
        // add the magnitude and direction of the error to the error vector
        return errorVector;
    }

    public  double linearRegressionTestingAlgorithm(ArrayList<int[]> trainingSample, double[] line)
    {
        double results = 0;
        return results;
    }
}
