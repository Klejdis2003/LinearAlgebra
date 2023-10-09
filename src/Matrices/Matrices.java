package Matrices;


import java.util.ArrayList;

public abstract class Matrices {

    /**
     * The dot product for two vectors(arrays) v1 = [a1, a2, ..., an] and v2 = [b1, b2, ... , bn]
     * is defined as p = sum(ai * bi) for all i up to n. For example, if v1 = [a1, a2, a3] and
     * v2 = [b1, b2, b3], their dot product would be equal to p = a1*b1 + a2*b2 + a3*b3.
     * @param v1 a Float array.
     * @param v2 a Float ArrayList.
     * @return an integer, the dot product of v1 and v2.
     * @throws Exception, if v1 and v2 have different sizes.
     */
    private static Double dotProduct(Double[] v1, ArrayList<Double> v2) throws Exception {
        if(v1.length != v2.size()) throw new Exception("Vector sizes do not match");
        Double dotProduct = 0d;
        for (int i = 0; i < v1.length; i++) {
            dotProduct += v1[i] * v2.get(i);
        }
        return dotProduct;
    }

    /**
     * @param matrix1 an integer 2d array.
     * @param matrix2 an integer 2d array.
     * @return a new Matrix instance built from 2d array that contains the sum of the two-dimensional arrays.
     */
    public static Matrix add(Double[][] matrix1, Double[][] matrix2) throws Exception {
        if (matrix1.length == matrix2.length && matrix1[0].length == matrix2[0].length) {
            int rows = matrix1.length;
            int columns = matrix1[0].length;
            Double[][] sumMatrix = new Double[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    sumMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
                }
            }
            return new Matrix(sumMatrix);
        }
        throw new Exception("Addition cannot be performed. Matrix sizes are different.");
    }

    /**
     * @param matrix1 a Matrix instance.
     * @param matrix2 a Matrix instance.
     * @return a new Matrix instance that is equal to matrix1 + matrix2.
     */
    public static Matrix add(Matrix matrix1, Matrix matrix2) throws Exception {
        Double[][] m1 = matrix1.getMatrix();
        Double[][] m2 = matrix2.getMatrix();
        return add(m1, m2);
    }

    /**
     * @param matrix1 an integer 2d array.
     * @param matrix2 an integer 2d array.
     * @return a new Matrix instance where the i,j position is equal to matrix1[i][j] - matrix2[i][j].
     */
    public static Matrix subtract(Double[][] matrix1, Double[][] matrix2) throws Exception {
        Matrix m1 = new Matrix(matrix1);
        Matrix m2 = new Matrix(matrix2);
        m2.coefficientMultiply(-1d);
        return add(m1, m2);

    }

    /**
     * @param matrix1 a Matrix instance.
     * @param matrix2 a Matrix instance.
     * @return a new Matrix instance where the i,j position is equal to matrix1[i][j] - matrix2[i][j].
     */
    public static Matrix subtract(Matrix matrix1, Matrix matrix2) throws Exception {
        matrix2.coefficientMultiply(-1d);
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

        Double[][] m3 = new Double[matrix1.getRows()][matrix2.getColumns()];
        for (int i = 0; i < matrix1.getRows(); i++) {
            for (int j = 0; j < matrix1.getColumns(); j++) {
                m3[i][j] = dotProduct(matrix1.getMatrix()[i], matrix2.getColumnMap().get(j));
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
    public static Matrix multiply(Double[][] m1, Double[][] m2) throws Exception {
        Matrix matrix1 = new Matrix(m1);
        Matrix matrix2 = new Matrix(m2);
        return multiply(matrix1, matrix2);
    }

}
