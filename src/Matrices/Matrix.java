package Matrices;

import java.util.Arrays;
import java.util.HashMap;

/**
 Provides basic functions to do operations on matrices such as adding, subtracting, multiplying, row echelon, reduced row echelon.
 */
public class Matrix {
    private Float[][] matrix;
    private int rows, columns;
    private HashMap<Integer, Float[]> columnMap; //will help with fast access to columns during matrix multiplication.

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
    protected Float[][] getMatrix() {
        return matrix;
    }

    public HashMap<Integer, Float[]> getColumnMap() {
        return columnMap;
    }

    /**
     * @param matrix a 2d array of integers
     * Builds a Matrix object from the given array.
     */
    public Matrix(Float[][] matrix) {
        this.matrix =  matrix;
        this.rows = matrix.length;
        this.columns = matrix[0].length;
        updateColumnMap();
    }

    /**
     * Builds an empty integer Matrix with dimensions of rows x columns.
     * @param rows Rows number of the matrix.
     * @param columns Columns number of the matrix.
     */
    public Matrix(int rows, int columns){

        this.rows = rows;
        this.columns = columns;
        this.matrix = new Float[rows][columns];
    }

    /**
     * Updates the HashMap that keeps tracks of the columns of the Matrix instance.
     */
    private void updateColumnMap(){
        this.columnMap = new HashMap<Integer, Float[]>();
        Float[] currColumn;
        for (int i = 0; i < this.columns; i++) {
            currColumn = new Float[this.rows];
            for (int j = 0; j < this.rows ; j++) {
                currColumn[j] = matrix[j][i];
            }
            columnMap.put(i, currColumn);
        }
    }
    private void printColumnMap(){
        for(Integer key: columnMap.keySet()){
            System.out.printf("%d = %s\n", key, Arrays.toString(columnMap.get(key)));
        }
    }
    private void swapRows(int r1Index, int r2Index){
        Float[] firstRow = matrix[r1Index];
        matrix[r1Index] = matrix[(r1Index + 1) % rows];
        matrix[(r1Index + 1) % rows] = firstRow;

    }

    /**
     * @param rowIndex The index of the row.
     * @param columnIndex The index of the column.
     * @return The number that is contained at position (rowIndex, columnIndex) in the Matrix instance.
     */
    public Float get(int rowIndex, int columnIndex){
        return matrix[rowIndex][columnIndex];
    }

    /**
     * Sets the position (rowIndex, columnInedex) in the
     * Matrix instance to the passed number.
     * @param rowIndex The index of the row.
     * @param columnIndex The index of the column.
     * @param number A floating point number.
     */
    public void set(int rowIndex, int columnIndex, Float number){
        matrix[rowIndex][columnIndex] = number;
    }
    public Matrix copy(){
        return new Matrix(this.matrix);
    }
    /**
     * Changes the current Matrix instance by adding a new row. Fails if the new row
     * is not compatible with the current rows.
     * @param row an integer array that matches in size with the number of columns of the
     *            Matrix instance.
     * @throws IncorrectRowSizeException when the size of the row to be added is different from
     *         the size of the rows of the Matrix instance.
     */
    public void addRow(Float[] row) throws IncorrectRowSizeException {
        if(row.length != columns) throw new IncorrectRowSizeException();
        Float[][] oldMatrix = this.matrix;
        this.rows++;
        this.matrix = new Float[this.rows][this.columns];
        System.arraycopy(oldMatrix, 0, this.matrix, 0, oldMatrix.length);
        this.matrix[rows-1] = row;
        updateColumnMap();
    }
    
    /**
     *
     *
     * Multiplies every element of a row of the Matrix instance by the passed number.
     * @param coefficient an integer, the coefficient to multiply the row by.
     * @param rowIndex an integer, specifies the row that is going to be transformed.
     */
    private void rowCoefficientMultiply(int coefficient, int rowIndex){
        Row row = new Row();
        matrix[rowIndex] = row.multiply(rowIndex, coefficient);
    }

    /**
     * Multiplies every element of the Matrix instance by the passed coefficient.
     * @param coefficient an integer.
     */
    public void coefficientMultiply(int coefficient){
        for (int i = 0; i < rows; i++) {
            rowCoefficientMultiply(coefficient, i);
        }
    }

    /**
     *
     * Performs gauss-jordan elimination method to compute a row echelon form of the Matrix instance.
     * @return a new matrix that is in row echelon form.
     * @author Klejdis Beshi
     */
    public Matrix getRowEchelon(){
        Matrix refMatrix = copy();
        Float[][] matrix = refMatrix.matrix;
        Float pivot;

        //Before we do anything, we should check if the first pivot is equal to 0.
        //If it does, we swap rows until the element at position 0,0 is not 0.
        while(matrix[0][0] == 0) {
            for (int i = 0; i < rows - 1 && matrix[rows - 1][0] != 0; i++) {
                swapRows(i, i+1);
            }
        }

        //This is where Gaussian elimination begins
        for(int i = 1; i < refMatrix.rows; i++){
            for(int j = 0; j < i && j < refMatrix.columns; j++){
                Row row = new Row();
                pivot = matrix[i][j];
                if(pivot == 0) continue;
                float coefficient = pivot /matrix[j][j];
                Float[] transformedTargetRow = row.multiply(j, coefficient);
                Float[] transformedRow = row.subtract(matrix[i], transformedTargetRow);

                matrix[i] = transformedRow;
            }
        }
        return refMatrix;
    }

    @Override
    public String toString() {
       StringBuilder matrixToString = new StringBuilder();
        for (int i = 0; i < this.rows; i++) {

            for (int j = 0; j < this.columns; j++) {
                if (matrix[i][j] == (int)((float) matrix[i][j]))
                    matrixToString.append(String.format("%12d ", (int)((float)matrix[i][j])));
                else {
                    matrixToString.append(String.format("%12.2f ", Math.round(matrix[i][j]*100)/100f));
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
    private class Row{
        Float[] multiply(int rowIndex, float constant){
            Float[] row = Arrays.copyOf(matrix[rowIndex], matrix[rowIndex].length);
            for (int i = 0; i < row.length; i++) {
                row[i] = (row[i] * constant);
            }
            return row;
        }
        Float[] subtract(Float[] row1, Float[] row2){
            Float[] newRow = new Float[row1.length];
            for (int i = 0; i < newRow.length; i++) {
                newRow[i] = row1[i] - row2[i];
            }
            return newRow;
        }
    }
}
