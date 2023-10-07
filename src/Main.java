import Matrices.Matrices;
import Matrices.Matrix;

public class Main {
    public static void main(String[] args) throws Exception{
        Matrix m1 = new Matrix(new Float[][]{{1F,2F, 5F}, {2F,34F, 3F}, {3F, 5F, 1F}});
        Matrix m2 = new Matrix(new Float[][]{{1F,2F, 5F}, {2F,34F, 3F}, {3F, 5F, 1F}});
        //System.out.println(Matrices.multiply(m1, m2));
        //System.out.println(m1);
        System.out.println(m1.getRowEchelon());
        //m1.printColumnMap();

    }
}
