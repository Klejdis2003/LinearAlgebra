package Matrices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
/**
 Provides basic functions to do operations on matrices such as adding, subtracting, multiplying, row echelon, reduced row echelon.
 @author Klejdis Beshi
 */
public class Matrix {
    private Double[][] matrix;
    private int rows, columns;
    private HashMap<Integer, ArrayList<Double>> columnMap; //will help with fast access to columns during matrix multiplication.


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
    protected Double[][] getMatrix() {
        return matrix;
    }

    public HashMap<Integer, ArrayList<Double>> getColumnMap() {
        return columnMap;
    }

    /**
     * @param matrix a 2d array of type Double.
     * Builds a Matrix object from the given array.
     */
    public Matrix(Double[][] matrix) {
        this.rows = matrix.length;
        this.columns = matrix[0].length;
        this.matrix = new Double[rows][columns];
        updateMatrix(matrix);
    }

    /**
     * Builds an empty integer Matrix with dimensions of rows x columns.
     * @param rows Rows number of the matrix.
     * @param columns Columns number of the matrix.
     */
    public Matrix(int rows, int columns){

        this.rows = rows;
        this.columns = columns;
        this.matrix = new Double[rows][columns];
        this.columnMap = new HashMap<>();
    }

    /**
     * Updates the matrix with the values of the passed 2-dimensional array.
     * @param m a 2-dimensional array of type Double, Double[][].
     */
    private void updateMatrix(Double[][] m){
        this.columnMap = new HashMap<>();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns ; j++) {
                if(!columnMap.containsKey(j))
                    columnMap.put(j, new ArrayList<>());
                matrix[i][j] = m[i][j];
                columnMap.get(j).add(matrix[i][j]);
            }
        }
    }

    /**
     * It updates the HashMap that keeps track of the Matrix
     * instance to account for the newly added row.
     * @param newRow An array of type Double, represents
     *               the row that was added.
     */
    private void updateColumnMap(Double[] newRow){
        for (int i = 0; i < newRow.length ; i++) {
            columnMap.get(i).add(newRow[i]);
        }

    }
    public void printColumnMap(){
        for(Integer key: columnMap.keySet()){
            System.out.printf("%d = %s\n", key, columnMap.get(key));
        }
    }

    /**
     * Swaps two rows of the matrix. Useful for
     * gauss transformations to reduce to row
     * echelon form.
     * @param r1Index the index of the first row.
     * @param r2Index the index of the second row.
     */
    private void swapRows(int r1Index, int r2Index){
        Double[] firstRow = matrix[r1Index];
        matrix[r1Index] = matrix[r2Index];
        matrix[r2Index] = firstRow;
    }

    /**
     * @param rowIndex The index of the row.
     * @param columnIndex The index of the column.
     * @return The number that is contained at position (rowIndex, columnIndex) in the Matrix instance.
     */
    public Double get(int rowIndex, int columnIndex){
        return matrix[rowIndex][columnIndex];
    }

    /**
     * Sets the position (rowIndex, columnIndex) in the
     * Matrix instance to the passed number.
     * @param rowIndex The index of the row.
     * @param columnIndex The index of the column.
     * @param number A number of type Double.
     */
    public void set(int rowIndex, int columnIndex, Double number){
        matrix[rowIndex][columnIndex] = number;
        //columnMap.get(columnIndex).set(rowIndex, number);
        updateMatrix(matrix);
    }

    /**
     * @return A new Matrix object that is equal to the current instance.
     */
    public Matrix copy(){
        return new Matrix(this.matrix);
    }
    /**
     * Changes the current Matrix instance by adding a new row. Fails if the new row
     * is not compatible with the current rows.
     * @param row an array of type Double that matches in size with the number of
     *            columns of the Matrix instance.
     * @throws IncorrectRowSizeException when the size of the row to be added is different from
     *         the size of the rows of the Matrix instance.
     */
    public void addRow(Double[] row) throws IncorrectRowSizeException {
        if(row.length != columns) throw new IncorrectRowSizeException();
        Double[][] oldMatrix = this.matrix;
        this.rows++;
        this.matrix = new Double[this.rows][this.columns];
        System.arraycopy(oldMatrix, 0, this.matrix, 0, oldMatrix.length);
        this.matrix[rows-1] = row;
        updateColumnMap(row);
    }
    
    /**
     *
     *
     * Multiplies every element of a row of the Matrix instance by the passed number.
     * @param coefficient a number of type Double, the coefficient to multiply the row by.
     * @param rowIndex an integer, specifies the row that is going to be transformed.
     */
    private void rowCoefficientMultiply(Double coefficient, int rowIndex){
        matrix[rowIndex] = Row.multiply(matrix[rowIndex], coefficient);
    }

    /**
     * Multiplies every element of the Matrix instance by the passed coefficient.
     * @param coefficient a number of type Double.
     */
    public void coefficientMultiply(Double coefficient){
        for (int i = 0; i < rows; i++) {
            rowCoefficientMultiply(coefficient, i);
        }
    }

    /**
     * Performs gauss-jordan elimination method to compute a row echelon form of the Matrix instance.
     * @return a new matrix that is in row echelon form.
     * @author Klejdis Beshi
     */
    public Matrix getRowEchelon(){
        Matrix refMatrix = copy();
        Double[][] matrix = refMatrix.matrix;
        Double pivot;

        //Before we do anything, we should check if the first pivot is equal to 0.
        //If it does, we swap rows until the element at position 0,0 is not 0.
        while(matrix[0][0] == 0) {
            for (int i = 0; i < rows - 1 && matrix[rows - 1][0] != 0; i++) {
                refMatrix.swapRows(i, i+1);
            }
        }

        //This is where Gaussian elimination begins
        for(int i = 1; i < refMatrix.rows; i++){
            for(int j = 0; j < i && j < refMatrix.columns; j++){
                pivot = matrix[i][j];
                if(pivot == 0) continue;
                double coefficient = pivot /matrix[j][j];
                Double[] transformedTargetRow = Row.multiply(matrix[j], coefficient);
                Double[] transformedRow = Row.subtract(matrix[i], transformedTargetRow);
                matrix[i] = transformedRow;
            }
        }
        refMatrix.updateMatrix(matrix);
        return refMatrix;
    }
    public Matrix transpose(){
        Matrix transposedMatrix = new Matrix(columns, rows);
        for (int i = 0; i < transposedMatrix.rows; i++) {
            for (int j = 0; j < transposedMatrix.columns ; j++) {
                transposedMatrix.matrix[i][j] = columnMap.get(i).get(j);
            }
        }
        transposedMatrix.updateMatrix(transposedMatrix.matrix);
        return transposedMatrix;
    }
    @Override
    public boolean equals(Object matrix) {

        if(this == matrix)
            return true;
        else if(!(matrix instanceof Matrix)){
            return false;
        }
        Matrix m = (Matrix) matrix;
        return Arrays.deepEquals(this.matrix, m.matrix);
    }

    @Override
    public String toString() {
       StringBuilder matrixToString = new StringBuilder();
        for (int i = 0; i < this.rows; i++) {

            for (int j = 0; j < this.columns; j++) {
                if (matrix[i][j] == (int)((double) matrix[i][j]))
                    matrixToString.append(String.format("%10d ", (int)((double)matrix[i][j])));
                else {
                    matrixToString.append(String.format("%10.2f ", Math.round(matrix[i][j]*100)/100f));
                }

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
    private  static class Row{
        static Double[] multiply(Double[] row, double constant){
            Double[] transformedRow = Arrays.copyOf(row, row.length);
            for (int i = 0; i < transformedRow.length; i++) {
                transformedRow[i] = (transformedRow[i] * constant);
            }
            return transformedRow;
        }
        static Double[] subtract(Double[] row1, Double[] row2){
            Double[] newRow = new Double[row1.length];
            for (int i = 0; i < newRow.length; i++) {
                newRow[i] = row1[i] - row2[i];
            }
            return newRow;
        }
        static boolean containsZeroesOnly(Double[] row){
            for(double d : row){
                if(d != 0)
                    return false;
            }
            return true;
        }
    }
}
