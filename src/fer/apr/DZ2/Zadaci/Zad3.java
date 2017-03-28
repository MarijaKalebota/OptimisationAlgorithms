package fer.apr.DZ2.Zadaci;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.F4JakobovicFunction;
import fer.apr.DZ2.HookeJeeves;
import fer.apr.DZ2.NelderMead;

/**
 * Created by Marija on 7.12.2016..
 */
public class Zad3 {
    private static double EPSILON = 1E-6;
    private static double ALPHA = 1;
    private static double BETA = 0.5;
    private static double GAMMA = 2;
    private static double SIGMA = 0.5;
    public static boolean PRINT = false;

    public static void main(String [ ] args) {
        F4JakobovicFunction f4 = new F4JakobovicFunction();
        double[][] elements4 = new double[][]{{5,5}};
        Matrix tocka4 =  new Matrix(1,2,elements4);

        Matrix rjesenjeHookeJeeves = HookeJeeves.provediHookeJeeves(f4, tocka4, 1, 0.5, EPSILON, PRINT);
        F4JakobovicFunction f42 = new F4JakobovicFunction();
        Matrix tocka42 =  HookeJeeves.copyPoint(tocka4);//new Matrix(1,2,elements4.clone());
        Matrix rjesenjeNelderMead = NelderMead.provediNelderMead(f42, tocka42, 1, ALPHA, BETA, GAMMA, SIGMA, EPSILON, PRINT);

        System.out.println("HJ broj poziva: " + f4.getNumberOfCalls() + "\tminimum: ");
        Matrix.printMatrix(rjesenjeHookeJeeves);
        System.out.println("NM broj poziva: " + f42.getNumberOfCalls() + "\tminimum: ");
        Matrix.printMatrix(rjesenjeNelderMead);


    }
}
