package fer.apr.DZ2.Zadaci;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.F1RosenbrockBananaFunction;
import fer.apr.DZ2.Helpers.F2;
import fer.apr.DZ2.Helpers.F4JakobovicFunction;
import fer.apr.DZ2.HookeJeeves;

/**
 * Created by Marija on 8.12.2016..
 */
public class Pom1 {
    private static double EPSILON = 1E-6;
    private static double ALPHA = 1;
    private static double BETA = 0.5;
    private static double GAMMA = 2;
    private static double SIGMA = 0.5;
    public static boolean PRINT = false;

    public static void main(String [ ] args) {
        F2 f2 = new F2();
        F1RosenbrockBananaFunction f1 = new F1RosenbrockBananaFunction();
        F4JakobovicFunction f4 = new F4JakobovicFunction();
        double[][] elements = new double[][]{{5.1, 1.1}};
        Matrix tocka1 = new Matrix(1, 2, elements);

        Matrix rjesenjeHJ = HookeJeeves.provediHookeJeeves(f4, tocka1,1, 0.5, EPSILON, PRINT);
    }
}
