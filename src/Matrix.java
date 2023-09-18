import java.util.Arrays;

/**
 Provides basic functions to do operations on matrices such as adding, subtracting, multiplying, row echelon, reduced row echelon.
 */
public class Matrix {
    private int[][] matrix;
    private int rows, columns;
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
