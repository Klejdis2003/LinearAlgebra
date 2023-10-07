package Matrices;

import java.util.Arrays;
import java.util.HashMap;

/**
 Provides basic functions to do operations on matrices such as adding, subtracting, multiplying, row echelon, reduced row echelon.
 */
public class Matrix {
    private Integer[][] matrix;
    private int rows, columns;
    private HashMap<Integer, Integer[]> columnMap; //will help with fast access to columns during matrix multiplication.

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
    protected Integer[][] getMatrix() {
        return matrix;
    }

    public HashMap<Integer, Integer[]> getColumnMap() {
        return columnMap;
    }

    /**
     * @param matrix a 2d array of integers
     * Builds a Matrix object from the given array.
     */
    public Matrix(Integer[][] matrix) {
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
        this.matrix = new Integer[rows][columns];
    }

    /**
     * Updates the HashMap that keeps tracks of the columns of the Matrix instance.
     */
    private void updateColumnMap(){
        this.columnMap = new HashMap<>();
        Integer[] currColumn;
        for (int i = 0; i < this.columns; i++) {
            currColumn = new Integer[this.rows];
            for (int j = 0; j < this.rows ; j++) {
                currColumn[j] = matrix[j][i];
            }
            columnMap.put(i, currColumn);
        }
    }
    public void printColumnMap(){
        for(Integer key: columnMap.keySet()){
            System.out.printf("%d = %s\n", key, Arrays.toString(columnMap.get(key)));
        }
    }

    /**
     * Changes the current Matrix instance by adding a new row. Fails if the new row
     * is not compatible with the current rows.
     * @param row an integer array that matches in size with the number of columns of the
     *            Matrix instance.
     * @throws IncorrectRowSizeException when the size of the row to be added is different from
     *         the size of the rows of the Matrix instance.
     */
    public void addRow(Integer[] row) throws IncorrectRowSizeException {
        if(row.length != columns) throw new IncorrectRowSizeException();
        Integer[][] oldMatrix = this.matrix;
        this.rows++;
        this.matrix = new Integer[this.rows][this.columns];
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
            for(int j = 0; j < i && j < this.columns; j++){
                Row row = new Row();
                pivot = matrix[i][j];
                if(pivot == 0) continue;
                float coef = (float) pivot /matrix[j][j];
                Integer[] transformedTargetRow = row.multiply(j, coef);
                Integer[] transformedRow = row.subtract(matrix[i], transformedTargetRow);

                refMatrix.matrix[i] = transformedRow;
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
        Integer[] multiply(int rowIndex, float constant){
            Integer[] row = Arrays.copyOf(matrix[rowIndex], matrix[rowIndex].length);
            for (int i = 0; i < row.length; i++) {
                row[i] = (int)(row[i] * constant);
            }
            return row;
        }
        Integer[] subtract(Integer[] row1, Integer[] row2){
            Integer[] newRow = new Integer[row1.length];
            for (int i = 0; i < newRow.length; i++) {
                newRow[i] = row1[i] - row2[i];
            }
            return newRow;
        }
    }
}
