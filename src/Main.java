import Matrices.Matrices;
import Matrices.Matrix;

import java.util.*;


public class Main {

    public static void main(String[] args){
        int operation;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Matrix operations app");
            System.out.print(
                    """
                            0: Add two matrices
                            1: Subtract two matrices
                            2: Multiply two matrices
                            3: Find a row echelon form of a matrix
                            4: Terminate Program
                            Enter the operation you want to perform:\s"""
            );
            operation = scanner.nextInt();
            Matrix m1, m2;
            switch (operation) {
                case 0:
                    m1 = getMatrixInput(1);
                    m2 = getMatrixInput(2);
                    try {
                        System.out.printf("%s + \n%s = \n%s\n", m1, m2, Matrices.add(m1, m2));
                    }
                    catch (Exception e) {
                        System.out.println("The sizes of the two matrices do not match." +
                                "Addition cannot be performed.");
                    }
                    break;
                case 1:
                    m1 = getMatrixInput(1);
                    m2 = getMatrixInput(2);

                    try{
                        System.out.printf("%s - \n%s = \n%s\n", m1, m2, Matrices.subtract(m1, m2));
                    }
                    catch (Exception e){
                        System.out.println("The sizes of the two matrices do not match." +
                                "Subtraction cannot be performed.");
                    }
                    break;
                case 2:
                    m1 = getMatrixInput(1);
                    m2 = getMatrixInput(2);

                    try{
                        System.out.printf("%s * \n%s = \n%s\n", m1, m2, Matrices.multiply(m1, m2));
                    }
                    catch (Exception e){
                        System.out.println("First matrix column number " +
                                           "does not match with second " +
                                           "matrix row number. " +
                                           "Multiplication cannot be" +
                                           "performed.");
                    }
                    break;
                case 3:
                    m1 = getMatrixInput(1);
                    System.out.printf("A row echelon form of\n %s is: \n%s", m1, m1.getRowEchelon());
                    break;
                case 4:
                    System.out.println("Exiting ...");
                    continue;
                default:
                    System.out.print("Operation not supported.");
                    break;
            }
            System.out.println("Type any character and press enter to use the app again.");
            scanner.next();
        }while(operation != 4);

    }
    public static Matrix getMatrixInput(int n) {
        ArrayList<Matrix> matrices = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Matrix m;
        boolean running;
        int rows = 0;
        int columns = 0;
        do{
            try {
                System.out.printf("Enter dimensions for matrix %d (rows, columns): ", n);
                String[] dimensionsString = scanner.nextLine().
                        replaceAll("\\s", "")
                        .split(",");

                rows = Integer.parseInt(dimensionsString[0]);
                columns = Integer.parseInt(dimensionsString[1]);

                running = false;
            }
            catch(Exception e){
                System.out.println("Input in the wrong format. Try again");
                scanner = new Scanner(System.in);
                running = true;
            }
        }while(running);

        m = new Matrix(rows, columns);
        System.out.printf("Enter matrix %d row by row as (a1 a2 a3 ... an):\n", n);
        for (int i = 0; i < m.getRows(); i++) {
            do {
                try {
                    System.out.printf("Enter row %d: ", i + 1);
                    for (int j = 0; j < m.getColumns(); j++) {
                        m.set(i, j, scanner.nextDouble());
                    }
                    running = false;
                } catch (Exception e) {
                    System.out.println("Wrong input. " +
                            "Remember that the format should be: " +
                            "a1 a2 a3 ... an. Try again.");

                    scanner = new Scanner(System.in);
                    running = true;
                }
            }while(running);

        }

        return m;
    }
}
