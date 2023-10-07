import Matrices.Matrices;
import Matrices.Matrix;

public class Main {
    public static void main(String[] args) throws Exception{
        Matrix m1 = new Matrix(new Integer[][]{{1,2, 5}, {2,34, 3}, {3, 5, 1}});
        Matrix m2 = new Matrix(new Integer[][]{{1,2, 5}, {2,34, 3}, {3, 5, 1}});
        System.out.println(Matrices.multiply(m1, m2));
        m1.addRow(new Integer[]{1, 2, 3});
        m1.addRow(new Integer[]{2, 4, 7});
        System.out.println(m1);
        System.out.println(m1.getRowEchelon());
        m1.printColumnMap();

    }
}
