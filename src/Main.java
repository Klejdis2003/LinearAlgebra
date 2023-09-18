import Matrices.Matrices;
import Matrices.Matrix;

public class Main {
    public static void main(String[] args){
      int m1[][] = {{1,2,3}, {2,3,5}};
      int m2[][] = {{1,8,3}, {7,1,5}};
      Matrix M1 = new Matrix(m1);
      Matrix M2 = new Matrix(m2);
      System.out.println(Matrices.add(M1, M2));
//        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 3));
//        System.out.println(Matrices.Matrices.add(m1, m2));
//        int [][] arr2d = {{1, 3, 4}};
//        Matrices.Matrix m3 = new Matrices.Matrix(arr2d);
//        m3.addRow(arr2d[0]);
//        System.out.println(m3);


    }
}
