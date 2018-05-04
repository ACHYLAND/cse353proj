import java.util.ArrayList;

public class linearRegression {
    //implement the linearRegression algorithm
    public  double[] linearRegressionTrainingAlgorithm(ArrayList<int[]> trainingSample)
    {
        //let the initial hypothesis be the average the value of each point
        double[] line = new double[trainingSample.get(1).length-1];
        double[] error;
        double minimumError;
        double precision = 0.0001;
        double errorPercentDifference = 1;
        double lastError = 0;
        double adjustment = .2;

        for(int i = 0; i < trainingSample.size();i++){
            for (int j = 0; j < line.length; j++){
                line[j] += trainingSample.get(i)[j+1];
            }
        }
        for (int j = 0; j < line.length; j++){
            line[j]/=trainingSample.size();
        }
        while(errorPercentDifference > precision) {
            //run the least squares algorithm to find the error and error vector
            error = leastSquaresAlgorithm(trainingSample, line);
            //adjust the line based on the error vector
            for(int i = 1; i < error.length;i++)
            {
                line[i] += adjustment * error[i];
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
        double currentError;
        //multiply the training sample by the hypothesis
        for(int i = 0; i< trainingSample.size(); i++)
        {
            //check if the prediction is correct
            if(((innerProduct(hypothesis, trainingSample.get(i))) > 0 && trainingSample.get(i)[0] < 0)
                    ||((innerProduct(hypothesis, trainingSample.get(i))) < 0 && trainingSample.get(i)[0] > 0)
                    );
            {
                //if it's wrong then alter the error and increment the total error
                totalError += (innerProduct(hypothesis, trainingSample.get(i)) * (innerProduct(hypothesis, trainingSample.get(i))));
                for (int j = 0; j < hypothesis.length;j++)
                {
                    if(trainingSample.get(i)[j] != 0)
                    {
                        //change the errorVector by how much the line is off
                        errorVector[j] -= innerProduct(hypothesis, trainingSample.get(i));
                    }
                }
            }
        }
        errorVector[0] = totalError;
        return errorVector;
    }

    public  double linearRegressionTestingAlgorithm(ArrayList<int[]> testingSample, double[] line)
    {
        int correctOutput = 0;
        int totalIterations = testingSample.size();
        double result = 0;


        for(int i = 0; i < testingSample.size(); i++){
            //checks if the output is correct
            int [] temp = testingSample.get(i);
            if ((innerProduct(line, temp) > 0 &&temp[0] > 0)
                    || (innerProduct(line, temp) < 0 &&temp[0] < 0)){
                correctOutput++;
            }
        }

        result = ((double)correctOutput)/((double)totalIterations);
        return result;
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
