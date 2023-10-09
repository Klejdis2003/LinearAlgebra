import Matrices.Matrices;
import Matrices.Matrix;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception{
        Double[][] m = {{0d,0d,0d}, {131d,2d, 5d}, {24d,34d, 3d}, {35d, 53d, 13d}};
        Matrix m1 = new Matrix(m);
        Matrix m2 = new Matrix(new Double[][]{{1d,2d, 5d}, {2d,34d, 3d}, {11d, 5d, 1d}});
        //System.out.println(Matrices.multiply(m1, m2));
        //System.out.println(m1);
        m[0][0] = 324d;
        m1.addRow(new Double[] {2d, 3d, 0d});
        System.out.println(m1.getRowEchelon());
        //System.out.println(Matrices.multiply(m1, m2));
        //m1.printColumnMap();
        //m1.printColumnMap();

    }
}
