package Matrices;

import java.util.Arrays;

/**
 Provides basic functions to do operations on matrices such as adding, subtracting, multiplying, row echelon, reduced row echelon.
 */
public class Matrix {
    private int[][] matrix;
    private int rows, columns;

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    /**
     *
     * @return The 2d array that the Matrix object is built upon.
     */
    protected int[][] getMatrix() {
        return matrix;
    }

    /**
     * @param matrix a 2d array of integers
     * Builds a Matrix object from the given array.
     */
    public Matrix(int[][] matrix) {
        this.matrix =  matrix;
        this.rows = matrix.length;
        this.columns = matrix[0].length;
    }

    /**
     * Builds an empty integer Matrix with dimensions of rows x columns.
     * @param rows Rows number of the matrix.
     * @param columns Columns number of the matrix.
     */
    public Matrix(int rows, int columns){

        this.rows = rows;
        this.columns = columns;
        this.matrix = new int[rows][columns];
    }

    /**
     * Changes the current Matrix instance by adding a new row.
     * Will not do anything if the row passed is different in size
     * from the matrix number of columns
     * @param row an integer array that matches in size with the number of columns of the Matrix instance.
     */
    public void addRow(int[] row) throws Exception {
        if(row.length != columns) throw new IncorrectRowSizeException();
        int[][] oldMatrix = this.matrix;
        this.rows++;
        this.matrix = new int[this.rows][this.columns];
        System.arraycopy(oldMatrix, 0, this.matrix, 0, oldMatrix.length);
        this.matrix[rows-1] = row;
    }
    
    /**
     * @param coefficient
     *        int type.
     *        Multiplies every element of the matrix by that number.
     */

    public void rowCoefficientMultiply(int coefficient, int rowIndex){
//        for (int j = 0; j < columns ; j++) {
//            Row row = Matr
//            matrix[row][j] *= coefficient;
//        }
        Row row = new Row();
        matrix[rowIndex] = row.multiply(rowIndex, coefficient);
    }
    public void coefficientMultiply(int coefficient){
        for (int i = 0; i < rows; i++) {
            rowCoefficientMultiply(coefficient, i);
        }
    }

    /**
     * <pre>
     * Performs gauss-jordan elimination method to compute a row echelon form of the Matrix instance.
     *
     * Current limitations: Rounding errors because as of now I have only provided methods for integer
     * matrices. Some row reduction operations cannot be performed without usage of floats/fractions, so those
     * position contain numbers that are not exactly right. Will be fixed in the next iteration.
     * </pre>
     * @return a new matrix that is in row echelon form
     */
    public Matrix getRowEchelon(){

        Matrix refMatrix = new Matrix(this.matrix);
        int pivot;
        for(int i = 1; i < this.rows; i++){
            for(int j = 0; j < i; j++){
                Row row = new Row();
                pivot = matrix[i][j];
                if(pivot == 0) continue;
                float coef = (float) pivot /matrix[j][j];
                int[] transformedTargetRow = row.multiply(j, coef);
                int[] transformedRow = row.subtract(matrix[i], transformedTargetRow);

                refMatrix.matrix[i] = Arrays.copyOf(transformedRow, transformedRow.length);
            }
        }
        return refMatrix;
    }

    @Override
    public String toString() {
       StringBuilder matrixToString = new StringBuilder();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                matrixToString.append(String.format("%d ", matrix[i][j]));
            }
            matrixToString.append("\n");
        }
        return matrixToString.toString();
    }

    /**
     * Makes internal row operations needed for various Matrix methods easier.
     * It involves methods of multiplying a Row(array) with a constant and
     * a method to subtract a row from another row.
     */
    private class Row{
        int[] multiply(int rowIndex, float constant){
            int [] row = Arrays.copyOf(matrix[rowIndex], matrix[rowIndex].length);
            for (int i = 0; i < row.length; i++) {
                row[i] *= constant;
            }
            return row;
        }
        int[] subtract(int[] row1, int[] row2){
            int[] newRow = new int[row1.length];
            for (int i = 0; i < newRow.length; i++) {
                newRow[i] = row1[i] - row2[i];
            }
            return newRow;
        }
    }
}
