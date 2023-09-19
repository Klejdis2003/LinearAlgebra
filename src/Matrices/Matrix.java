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


    public Matrix(int[][] matrix) {
        this.matrix =  matrix;
        this.rows = matrix.length;
        this.columns = matrix[0].length;
    }



    public void addRow(int[] row){
        int[][] oldMatrix = this.matrix;
        this.rows++;
        this.matrix = new int[this.rows][this.columns];
        for (int i = 0; i < oldMatrix.length; i++) {
            this.matrix[i] = oldMatrix[i];
        }
        this.matrix[rows-1] = row;
    }

    /**
     * @param coefficient
     *        int type.
     *        Multiplies every element of the matrix by that number.
     */
    public void coefficientMultiply(int coefficient){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns ; j++) {
                matrix[i][j] *= coefficient;
            }
        }
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
}
