import java.util.*;

public class Main {
    public static void main(String[] arg) {
        Matrix A = new Matrix(3, 3);
        A.fill();
        System.out.println("Matrix A:");
        A.print_matrix();
        Complex z = A.det();
        System.out.print("Determinate A: ");
        z.print();
        System.out.println('\n');
        Matrix T = A.transpose();
        System.out.println("Transpose matrix A:");
        T.print_matrix();
        Matrix B = new Matrix(3, 3);
        B.fill();
        System.out.println("Matrix B:");
        B.print_matrix();
        Matrix C = B.sum(A);
        System.out.println("Matrix C = A + B:");
        C.print_matrix();
        Matrix D = A.diff(B);
        System.out.println("Matrix D = A - B:");
        D.print_matrix();
        Matrix E = A.multi(B);
        System.out.println("Matrix E = A * B:");
        E.print_matrix();
    }
}
