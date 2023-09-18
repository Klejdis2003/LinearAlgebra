public abstract class Matrices {
    /**
     * @param matrix1 an integer 2d array, int[][]
     * @param matrix2 an integer 2d array, int[][]
     * @return        a new Matrix built from 2d array that contains the sum of the two matrices.
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

}
