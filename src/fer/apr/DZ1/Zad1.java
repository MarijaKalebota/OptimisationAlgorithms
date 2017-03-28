package fer.apr.DZ1;

import java.io.IOException;

/**
 * Created by Marija on 23.10.2016..
 */
public class Zad1 {

    public static void main(String [ ] args) throws IOException {
        double[][] elements1 = new double[][]{{5,5,5},{3,3,3},{1,1,1}};
        double[][] elements2 = new  double[][]{{5,5,5},{3,3,3},{1,1,1}};
        Matrix m1 = new Matrix(3,3,elements1);
        Matrix m2 = new Matrix(3,3,elements2);
        //testMatrix.readFile();

        if (m1.equals(m2)){
            System.out.println("Matrice su iste.");
        }
        else{
            System.out.println("Matrice nisu iste.");
        }

        elements1 = new double[][]{{5.0001,5,5},{3,3,3},{1,1,1}};
        elements2 = new  double[][]{{5.0001,5,5},{3,3,3},{1,1,1}};
        m1 = new Matrix(3,3,elements1);
        m2 = new Matrix(3,3,elements2);

        if (m1.equals(m2)){
            System.out.println("Matrice su iste.");
        }
        else{
            System.out.println("Matrice nisu iste.");
        }
        Matrix m3 = new Matrix(3,3,elements1);
        Matrix m4 = new Matrix(3,3,elements2);
        Matrix.scalarMultiply(m4, 5000000000017.00000000017);
        Matrix.scalarMultiply(m4, 1/5000000000017.00000000017);

        if (m3.equals(m4)){
            System.out.println("Matrice su iste.");
        }
        else{
            System.out.println(m4);
            System.out.println("Matrice nisu iste.");
        }

    }


}