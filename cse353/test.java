package cse353;

public class test {

    public static void main(String [] args){
        matrixManipulation matrix = new matrixManipulation();
        double[][] a = {{1,2,3},{4,5,6,},{7,8,9}};
        matrix.printMatrix(a);
        matrix.printMatrix(matrix.matrixMultiplication(a,a));
    }
}
