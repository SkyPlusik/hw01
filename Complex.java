import java.util.*;
public class Complex {
    private int real, image;
    Complex() {
        real = 0;
        image = 0;
    }
    Complex(int a, int b) {
        real = a;
        image = b;
    }
    public int get_real()
    {
        return real;
    }
    public int get_image()
    {
        return image;
    }
    public void set_real(int a)
    {
        real = a;
    }
    public void set_image(int b)
    {
        image = b;
    }
    public void print() {
        if (real == 0 && image == 0) System.out.print(0);
        else if (real == 0) {
            if (image < 0) System.out.print(image + "i");
            else System.out.print(image + "i");
        }
        else {
            if (image < 0) System.out.print(real+ " - " + (-image) + "i");
            else if (image == 0) System.out.print(real);
            else System.out.print(real + " + " + image + "i");
        }
    }
    public Complex sum(Complex z2) {
        Complex z = new Complex((this.real + z2.real), (this.image + z2.image));
        return z;
    }
    public Complex diff(Complex z2) {
        Complex z = new Complex((this.real - z2.real), (this.image - z2.image));
        return z;
    }
    public Complex multi(Complex z2) {
        int a = this.real * z2.real - this.image * z2.image;
        int b = this.real * z2.image + this.image * z2.real;
        Complex z = new Complex(a, b);
        return z;
    }
}