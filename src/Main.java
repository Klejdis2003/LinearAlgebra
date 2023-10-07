import Matrices.Matrices;
import Matrices.Matrix;

public class Main {
    public static void main(String[] args) throws Exception {
        Matrix m1 = new Matrix(new int[][]{{1,2, 5}, {2,34, 3}, {3, 5, 1}});
        m1.addRow(new int[]{1, 2});
        System.out.println(m1);
        System.out.println(m1.getRowEchelon());
    }
}
