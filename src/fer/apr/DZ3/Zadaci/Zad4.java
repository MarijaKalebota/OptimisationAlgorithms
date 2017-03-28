package fer.apr.DZ3.Zadaci;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.F1RosenbrockBananaFunction;
import fer.apr.DZ2.Helpers.F2;
import fer.apr.DZ2.Helpers.IOgranicenja;
import fer.apr.DZ2.HookeJeeves;
import fer.apr.DZ3.Box;
import fer.apr.DZ3.ImpOgr1;
import fer.apr.DZ3.ImpOgr2;
import fer.apr.DZ3.Transformacija;

/**
 * Created by Marija on 19.12.2016..
 */
public class Zad4 {

    private static double EPSILON = 1E-6;
    private static double ALPHA = 1;
    private static double BETA = 0.5;
    private static double GAMMA = 2;
    private static double SIGMA = 0.5;
    public static boolean PRINT = true;

    public static void main(String [ ] args) {
        F1RosenbrockBananaFunction f11 = new F1RosenbrockBananaFunction();
        F1RosenbrockBananaFunction f12 = new F1RosenbrockBananaFunction();
        F2 f21 = new F2();
        F2 f22 = new F2();
        //double[][] elements1 = new double[][]{{-1.9, 2}};
        double[][] elements1 = new double[][]{{1.5, 1.5}};
        Matrix tocka11 = new Matrix(1, 2, elements1);
        Matrix tocka12 = HookeJeeves.copyPoint(tocka11);

        double[][] elements2 = new double[][]{{0.1, 0.3}};
        Matrix tocka21 = new Matrix(1, 2, elements2);
        Matrix tocka22 = HookeJeeves.copyPoint(tocka21);

        double[] donjeGranice = new double[]{-100, -100};
        double[] gornjeGranice = new double[]{100, 100};
        IOgranicenja impOgr1 = new ImpOgr1();
        IOgranicenja impOgr2 = new ImpOgr2();

        IOgranicenja[] implicitnaOgranicenja = new IOgranicenja[2];
        implicitnaOgranicenja[0] = impOgr1;
        implicitnaOgranicenja[1] = impOgr2;

        Matrix rjesenjeSTransformacijom1 = Transformacija.provediTransformacijuUProblemBezOgranicenjaNaMjesovitiNacin(f11, tocka11, 1, implicitnaOgranicenja, null, 1, 0.5, EPSILON, PRINT);
        Matrix rjesenjeSTransformacijom2 = Transformacija.provediTransformacijuUProblemBezOgranicenjaNaMjesovitiNacin(f21, tocka21, 1, implicitnaOgranicenja, null, 1, 0.5, EPSILON, PRINT);

        System.out.println("\n\n");
        System.out.println("Rjesenje s transformacijom za f1: ");
        Matrix.printMatrix(rjesenjeSTransformacijom1);
        System.out.println("Rjesenje s transformacijom za f2: ");
        Matrix.printMatrix(rjesenjeSTransformacijom2);
    }

}
