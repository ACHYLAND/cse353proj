package cse353;

import java.lang.System;
import java.util.ArrayList;
import org.la4j.Matrices;
import org.la4j.Matrix;
import org.la4j.Vector;
import org.la4j.decomposition.EigenDecompositor;
import org.la4j.inversion.GaussJordanInverter;
import org.la4j.linear.LeastSquaresSolver;
import org.la4j.matrix.dense.Basic1DMatrix;
import org.la4j.matrix.dense.Basic2DMatrix;
import org.la4j.vector.dense.BasicVector;


public class linearRegression {

    public double[]linearRegressionTraining(ArrayList<int[]> trainingSample)
    {
        ArrayList<int[]> sub =new ArrayList<int[]>(trainingSample.subList(0,100));
        return linearRegressionTrainingAlgorithm(trainingSample);
    }


    //implement the cse353.linearRegression algorithm
    public double[] linearRegressionTrainingAlgorithm(ArrayList<int[]> trainingSample) {
        // Calculate A
        double[][] trainingSampleArrayTemp = new double[trainingSample.size()][trainingSample.get(0).length];
        for(int i = 0; i < trainingSample.size();i++)
        {
            for(int j = 0; j< trainingSample.get(0).length; j++)
            {
                trainingSampleArrayTemp[i][j]= trainingSample.get(i)[j];
            }
        }
        double[][] trainingSampleArray = new double[trainingSampleArrayTemp.length][trainingSampleArrayTemp[0].length-1];
        // trainingSampleArray contains ONLY the feature vectors; no class labels
        for (int i = 0; i < trainingSampleArray.length; i++) {
            System.arraycopy(trainingSampleArrayTemp[i], 1, trainingSampleArray[i], 0, trainingSampleArray[i].length);
        }

        //calculate X
        Matrix X = new Basic2DMatrix(trainingSampleArray).toSparseMatrix();
        X = X.transpose().toSparseMatrix();
        Matrix A = X.multiply(X.transpose()).toSparseMatrix();

        // Calculate b
        double[] yArray = new double[trainingSample.size()];
        for(int i = 0; i < yArray.length; i++)
        {
            yArray[i] = trainingSample.get(i)[0];
        }
        Vector y = new BasicVector(yArray);

        Vector B = X.multiply(y);

        //calculate W A-1b


        //finding the generalized inverse of A
        EigenDecompositor decomp = new EigenDecompositor(A);
        //w = A+ * b|| A+ = VD+V
        Matrix [] eigenParts = decomp.decompose();
        Matrix DInverse = new Basic2DMatrix(new double[eigenParts[1].rows()][eigenParts[1].columns()]);
        for (int i = 0; i < eigenParts[1].rows(); i++)//Create DInverse
        {
            for (int j = 0; j < eigenParts[1].columns(); j++)
            {
                if(eigenParts[1].get(i,j)!=0) {
                    DInverse.set(i, j, 1 / eigenParts[1].get(i, j));
                } else{
                    DInverse.set(i, j, 0);
                }
            }
        }
        //A+ = VD+V^-1
        GaussJordanInverter invert = new GaussJordanInverter(eigenParts[0]);
        Matrix AInverse = eigenParts[0].multiply(DInverse).multiply(invert.inverse());
        Vector w = AInverse.multiply(B);

        double[]wArray = new double[trainingSample.get(0).length-1];
        for(int i = 0; i < wArray.length; i++)
        {
            wArray[i] = w.get(i);
        }
        return wArray;
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
                        errorVector[j] += innerProduct(hypothesis, trainingSample.get(i));

                    }
                }
            }
        }
        errorVector[0] = totalError;
        return errorVector;
    }

    public double linearRegressionTestingAlgorithm(ArrayList<int[]> testingSample, double[] w)
    {
        int correctOutput = 0;
        int totalIterations = testingSample.size();
        double result = 0;


        for(int i = 0; i < testingSample.size(); i++){
            //checks if the output is correct
            int [] temp = testingSample.get(i);
            if ((innerProduct(w, temp) > 0 &&temp[0] > 0)
                    || (innerProduct(w, temp) < 0 &&temp[0] < 0)){
                correctOutput++;
            }
        }

        result = ((double)correctOutput)/((double)totalIterations);
        return result;
        /*
        double loss = 0;
        for (int i = 0; i < testingSample.size(); i++) {
            loss += (innerProduct(w, testingSample.get(i)) * testingSample.get(i)[0]);
        }
        loss /= testingSample.size();
        return loss;
        */
    }

    private double innerProduct(double[] w, int[] featureVector) {
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
