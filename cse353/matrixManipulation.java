package cse353;

import org.la4j.Matrices;
import org.la4j.Matrix;
import org.la4j.matrix.dense.Basic2DMatrix;

public class matrixManipulation {
    public double [][] matrixMultiplication(double[][] a, double[][]b)
    {
        double[][] product = new double[a.length][b.length];
        double tempSum;
        for (int i = 0; i<product.length;i++)
        {

            for(int j = 0; j<product[0].length;j++){
                tempSum = 0;

                for(int k = 0; k <a[0].length;k++){
                    tempSum += a[i][k] * b[k][j];
                }
                product[i][j] = tempSum;
            }
        }
        return product;
    }



    public double [][] matrixTranspose(double[][] a)
    {
        double [][] transpose = new double[a[0].length][a.length];
        for (int i = 0; i< a.length; i++)
        {
            for(int j = 0; j < a[0].length; j++)
            {
                transpose[j][i] = a[i][j];
            }
        }
        return transpose;
    }

    public void printMatrix(double[][]a)
    {
        for (int i = 0; i< a.length; i++)
        {
            for(int j = 0; j < a[0].length; j++)
            {
                System.out.print( a[i][j]+ " ");
            }
            System.out.println();
        }
    }
}
