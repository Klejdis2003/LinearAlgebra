package Matrices;

public abstract class Matrices {
    /**
     * @param matrix1 an integer 2d array, int[][]
     * @param matrix2 an integer 2d array, int[][]
     * @return        a new Matrices.Matrix built from 2d array that contains the sum of the two matrices.
     */
    public static Matrix add(int[][] matrix1, int[][] matrix2){
        if (matrix1.length == matrix2.length && matrix1[0].length == matrix2[0].length){
            int rows = matrix1.length;
            int columns = matrix1[0].length;
            int[][] sumMatrix = new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    sumMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
                }
            }
            return new Matrix(sumMatrix);
        }
        return new Matrix(new int[0][0]);
    }
    public static Matrix add(Matrix matrix1, Matrix matrix2){
        int [][] m1 = matrix1.getMatrix();
        int[][] m2 = matrix2.getMatrix();
        return add(m1, m2);
    }
    public static Matrix subtract(int[][] matrix1, int[][] matrix2){
        Matrix m1 = new Matrix(matrix1);
        Matrix m2 = new Matrix(matrix2);
        m2.coefficientMultiply(-1);
        return add(m1, m2);

    }
    public static Matrix subtract(Matrix matrix1, Matrix matrix2){
        matrix2.coefficientMultiply(-1);
        return add(matrix1, matrix2);
    }





}
