package Matrices;

import Vectors.Vectors;

public abstract class Matrices {
    /**
     * @param matrix1 an integer 2d array.
     * @param matrix2 an integer 2d array.
     * @return a new Matrix instance built from 2d array that contains the sum of the two-dimensional arrays.
     */
    public static Matrix add(Float[][] matrix1, Float[][] matrix2) {
        if (matrix1.length == matrix2.length && matrix1[0].length == matrix2[0].length) {
            int rows = matrix1.length;
            int columns = matrix1[0].length;
            Float[][] sumMatrix = new Float[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    sumMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
                }
            }
            return new Matrix(sumMatrix);
        }
        return new Matrix(new Float[0][0]);
    }

    /**
     * @param matrix1 a Matrix instance.
     * @param matrix2 a Matrix instance.
     * @return a new Matrix instance that is equal to matrix1 + matrix2.
     */
    public static Matrix add(Matrix matrix1, Matrix matrix2) {
        Float[][] m1 = matrix1.getMatrix();
        Float[][] m2 = matrix2.getMatrix();
        return add(m1, m2);
    }

    /**
     * @param matrix1 an integer 2d array.
     * @param matrix2 an integer 2d array.
     * @return a new Matrix instance where the i,j position is equal to matrix1[i][j] - matrix2[i][j].
     */
    public static Matrix subtract(Float[][] matrix1, Float[][] matrix2) {
        Matrix m1 = new Matrix(matrix1);
        Matrix m2 = new Matrix(matrix2);
        m2.coefficientMultiply(-1);
        return add(m1, m2);

    }

    /**
     * @param matrix1 a Matrix instance.
     * @param matrix2 a Matrix instance.
     * @return a new Matrix instance where the i,j position is equal to matrix1[i][j] - matrix2[i][j].
     */
    public static Matrix subtract(Matrix matrix1, Matrix matrix2) {
        matrix2.coefficientMultiply(-1);
        return add(matrix1, matrix2);
    }

    /**
     * Multiplies two given Matrix instances. Will fail if the two matrices are not compatible.
     * @param matrix1 A Matrix class instance.
     * @param matrix2 A matrix class instance.
     * @return A new Matrix instance that is the product of matrix1 * matrix2.
     * @throws Exception When the multiplication cannot be performed because the number of columns of matrix 1 is not equal to the number of rows of matrix2.
     */
    public static Matrix multiply(Matrix matrix1, Matrix matrix2) throws Exception {
        if (matrix1.getColumns() != matrix2.getRows()) throw new Exception("Incompatible Matrices.");

        Float[][] m3 = new Float[matrix1.getRows()][matrix2.getColumns()];
        for (int i = 0; i < matrix1.getRows(); i++) {
            for (int j = 0; j < matrix1.getColumns(); j++) {
                m3[i][j] = Vectors.dotProduct(matrix1.getMatrix()[i], matrix2.getColumnMap().get(j));
            }
        }
        return new Matrix(m3);
    }

    /**
     * Multiplies the passed 2-dimensional arrays. Will fail if they are not compatible.
     * @param m1 A 2d Integer array.
     * @param m2 A 2d Integer array.
     * @return A new Matrix instance that is built from the product of m1*m2.
     * @throws Exception When the multiplication cannot be performed because the number of columns of m1 is not equal to the number of rows of m2.
     */
    public static Matrix multiply(Float[][] m1, Float[][] m2) throws Exception {
        Matrix matrix1 = new Matrix(m1);
        Matrix matrix2 = new Matrix(m2);
        return multiply(matrix1, matrix2);
    }

}
