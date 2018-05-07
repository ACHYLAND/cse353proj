import java.lang.System;
import java.util.ArrayList;
import org.la4j.Matrices;
import org.la4j.Matrix;
import org.la4j.inversion.GaussJordanInverter;
import org.la4j.linear.LeastSquaresSolver;
import org.la4j.matrix.dense.Basic1DMatrix;
import org.la4j.matrix.dense.Basic2DMatrix;
import org.la4j.vector.dense.BasicVector;


public class linearRegression {
    //implement the linearRegression algorithm
    public double[] linearRegressionTrainingAlgorithm(ArrayList<double[]> trainingSample) {
        // Calculate A
        double[][] trainingSampleArrayTemp = trainingSample.toArray(new double[trainingSample.size()][trainingSample.get(0).length]);
        double[][] trainingSampleArray = new double[trainingSampleArrayTemp.length][trainingSampleArrayTemp[0].length-1];
        // trainingSampleArray contains ONLY the feature vectors; no class labels
        for (int i = 0; i < trainingSampleArray.length; i++) {
            System.arraycopy(trainingSampleArrayTemp[i], 1, trainingSampleArray[i], 0, trainingSampleArray[i].length);
        }

        Matrix A = new Basic2DMatrix(trainingSampleArray.length, trainingSampleArray.length);
        for (int i = 0; i < trainingSampleArray.length; i++) {
            Basic2DMatrix xi;
            xi = Basic2DMatrix.from1DArray(1, trainingSampleArray[0].length, trainingSampleArray[i]);
            Basic2DMatrix xiT = (Basic2DMatrix) xi.transpose();
            A.add(xi.multiply(xiT));
        }

        // Calculate b
        double[] bArray = new double[trainingSampleArray[0].length];
        Basic1DMatrix b = new Basic1DMatrix(1, trainingSampleArray[0].length, bArray);
        Basic2DMatrix xi;

        // b = sum{i = 1; n} xi * yi
        for (int i = 0; i < bArray.length; i++) {
            xi = Basic2DMatrix.from1DArray(1, trainingSampleArray[0].length, trainingSampleArray[i]);
            b.add(xi.multiply(trainingSample.get(i)[0]));
        }

        Basic1DMatrix w;
        double[] wArray;
        GaussJordanInverter inverter = new GaussJordanInverter(A);
        Basic2DMatrix Ainv;
        /*if ((Ainv = (Basic2DMatrix)inverter.inverse()) != null) {
            //w = (Basic1DMatrix) Ainv.multiply(b);
            //wArray = w.toArray();
        }*/
        LeastSquaresSolver solver = new LeastSquaresSolver(A);
        BasicVector bVector = (BasicVector) b.toRowVector();
        BasicVector wVector = (BasicVector) solver.solve(bVector);
        wArray = wVector.toArray();

        /*
        //let the initial hypothesis be the average the value of each point
        double[] line = new double[trainingSample.get(1).length-1];
        double[] error;
        double minimumError;
        double precision = 0.00001;
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
        }*/
        return wArray;
    }

    //each iteration of finding the squared error of the hypothesis
    // result[0] is the total square error, other indicies indicate testing error
    private double [] leastSquaresAlgorithm (ArrayList<double[]> trainingSample, double[]hypothesis)
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
                        errorVector[j] += innerProduct(hypothesis, trainingSample.get(i));
                        // logithmic error changing
                        /*
                        if (innerProduct(hypothesis, trainingSample.get(i))>0)
                        {
                            //errorVector[j] -= Math.log(innerProduct(hypothesis, trainingSample.get(i)));
                            errorVector[j] -= 1;
                        } else
                        {
                           // errorVector[j] += Math.log(Math.abs(innerProduct(hypothesis, trainingSample.get(i))));
                            errorVector[j] += 1;
                        }*/
                    }
                }
            }
        }
        errorVector[0] = totalError;
        return errorVector;
    }

    public double linearRegressionTestingAlgorithm(ArrayList<double[]> testingSample, double[] w)
    {
        /*
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
        return result;*/

        double loss = 0;
        for (int i = 0; i < testingSample.size(); i++) {
            loss += (innerProduct(w, testingSample.get(i)) * testingSample.get(i)[0]);
        }
        loss /= testingSample.size();
        return loss;
    }

    private double innerProduct(double[] w, double[] featureVector) {
        double sum = 0;
        /* Modified so we don't include the result y, just the feature vector */
        for (int i = 0; i < w.length; i++) {
            sum += w[i] * featureVector[i+1];
        }
        return sum;
    }

    private double xByxInnerProduct(double[] x1, double[] x2) {
        double sum = 0;
        /* Modified so we don't include the results y, just the feature vector */
        for (int i = 1; i < x1.length; i++) {
            sum += x1[i] * x2[i];
        }
        return sum;
    }
}
