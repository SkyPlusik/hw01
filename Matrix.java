import java.util.*;
class Matrix {
    private Complex [][] matrix;
    private int row, col;
    Matrix(int n, int m) {
        row = n;
        col = m;
        matrix = new Complex[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = new Complex();
            }
        }
    }
    public void fill() {
        Random r = new Random();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int real = (r.nextInt(41) - 20);
                int image = (r.nextInt(41) - 20);
                matrix[i][j].set_real(real);
                matrix[i][j].set_image(image);
            }
        }
    }
    public void print_matrix() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print("\t");
                matrix[i][j].print();
                System.out.print("\t");
            }
            System.out.println();
        }
        System.out.println();
    }
    public Matrix transpose() {
        if (row <= 0 || col <= 0) {
            throw new IllegalArgumentException("Number of rows or columns should be greater than zero");
        }
        Matrix T = new Matrix(col, row);
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                T.matrix[j][i] = this.matrix[i][j];
            }
        }
        return T;
    }
    public Matrix sum(Matrix other) {
        if (this.row != other.row || this.col != other.col) {
            throw new IllegalArgumentException("The matrices should have the same size");
        }
        Matrix result = new Matrix(row, col);
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                result.matrix[i][j] = this.matrix[i][j].sum(other.matrix[i][j]);
            }
        }
        return result;
    }
    public Matrix diff(Matrix other) {
        if (this.row != other.row || this.col != other.col) {
            throw new IllegalArgumentException("The matrices should have the same size");
        }
        Matrix result = new Matrix(row, col);
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                result.matrix[i][j] = this.matrix[i][j].diff(other.matrix[i][j]);
            }
        }
        return result;
    }
    public Matrix multi(Matrix other) {
        if (this.col != other.row) {
            throw new IllegalArgumentException("The matrices should be consistent");
        }
        Matrix result = new Matrix(this.row, other.col);
        for(int i = 0; i < this.row; i++){
            for(int j = 0; j < other.col; j++) {
                for (int k = 0; k < other.row; k++) {
                    result.matrix[i][j] = result.matrix[i][j].sum(this.matrix[i][k].multi(other.matrix[k][j]));
                }
            }
        }
        return result;
    }
    public Complex det() {
        if (row != col) {
            throw new IllegalArgumentException("The column and the row should be equal");
        }
        return determinate(matrix, row);
    }
    private Complex determinate(Complex[][] mtrx, int size) {
        if (size == 1) return mtrx[0][0];
        if (size == 2) {
            Complex tmp1 = mtrx[0][0].multi(mtrx[1][1]), tmp2 = mtrx[0][1].multi(mtrx[1][0]);
            return tmp1.diff(tmp2);
        }
        Complex d = new Complex(), k = new Complex(1, 0), tmp = new Complex();
        for (int j = 0; j < size; j++) {
            Complex M[][] = minor(0, j, size, mtrx);
            tmp = mtrx[0][j].multi(k);
            tmp = tmp.multi(determinate(M, size - 1));
            d = d.sum(tmp);
            if (k.get_real() == 1) k.set_real(-1);
            else k.set_real(1);
        }
        return d;
    }
    private Complex[][] minor(int i, int j, int n, Complex[][] M) {
        int a = 0, b = 0;
        Complex tmp[][] = new Complex [n-1][n-1];
        for (int x = 1; x < n; x++) {
            for (int y = 0; y < n; y++)
                if (y != j) {
                    tmp[a][b] = M[x][y];
                    b++;
                }
            a++;
            b = 0;
        }
        return tmp;
    }
}