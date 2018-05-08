import org.la4j.inversion.GaussJordanInverter;
import org.la4j.Matrices;
import org.la4j.Matrix;
import org.la4j.matrix.dense.Basic2DMatrix;

public class matrixManipulation {
    public Matrix matrixMultiplication(double[][] a, double[][] b) {
        Basic2DMatrix matA = new Basic2DMatrix(a.length, a[0].length);
        Basic2DMatrix matB = new Basic2DMatrix(b.length, b[0].length);
        return matA.multiply(matB);
    }

    public Matrix matrixInverse (double[][] a) {
        Matrix c = new Basic2DMatrix(a);
        GaussJordanInverter inverter = new GaussJordanInverter(c);
        Matrix inverse = inverter.inverse();
        return inverse;
    }

    public Matrix matrixTranspose(double[][] a) {
        Matrix matA = new Basic2DMatrix(a);
        return matA.transpose();
    }

    public void printMatrix(double[][] a) {
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
