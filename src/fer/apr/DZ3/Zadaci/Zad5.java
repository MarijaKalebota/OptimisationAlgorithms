package fer.apr.DZ3.Zadaci;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.F1RosenbrockBananaFunction;
import fer.apr.DZ2.Helpers.F2;
import fer.apr.DZ2.Helpers.F4JakobovicFunction;
import fer.apr.DZ2.Helpers.IOgranicenja;
import fer.apr.DZ2.HookeJeeves;
import fer.apr.DZ3.*;

/**
 * Created by Marija on 19.12.2016..
 */
public class Zad5 {

    private static double EPSILON = 1E-6;
    private static double ALPHA = 1;
    private static double BETA = 0.5;
    private static double GAMMA = 2;
    private static double SIGMA = 0.5;
    public static boolean PRINT = false;

    public static void main(String [ ] args) {
        F4DZ3 f4 = new F4DZ3();
        F4DZ3 f42 = new F4DZ3();

        double[][] elements1 = new double[][]{{0, 0}};
        Matrix tocka11 = new Matrix(1, 2, elements1);


        double[][] elements2 = new double[][]{{5, 5}};
        Matrix tocka21 = new Matrix(1, 2, elements2);



        IOgranicenja impOgr3 = new ImpOgr3();
        IOgranicenja impOgr4 = new ImpOgr4();

        IOgranicenja[] implicitnaOgranicenja = new IOgranicenja[2];
        implicitnaOgranicenja[0] = impOgr3;
        implicitnaOgranicenja[1] = impOgr4;

        IOgranicenja ekspOgr2 = new EksOgr2();
        IOgranicenja[] eksplicitnaOgranicenja = new IOgranicenja[1];

        eksplicitnaOgranicenja[0] = ekspOgr2;


        Matrix rjesenjeSTransformacijom1 = Transformacija.provediTransformacijuUProblemBezOgranicenjaNaMjesovitiNacin(f4, tocka11, 1, implicitnaOgranicenja, eksplicitnaOgranicenja, 1, 0.5, EPSILON, PRINT);
        Matrix rjesenjeSTransformacijom2 = Transformacija.provediTransformacijuUProblemBezOgranicenjaNaMjesovitiNacin(f42, tocka21, 1, implicitnaOgranicenja, eksplicitnaOgranicenja, 1, 0.5, EPSILON, PRINT);

        //TODO Ispišite broj izračuna funkcije, gradijenta i Hesseove matrice.

        System.out.println("\n\n");
        System.out.println("Rjesenje s transformacijom za f1 za pocetnu tocku (0,0): ");
        Matrix.printMatrix(rjesenjeSTransformacijom1);
        System.out.println("Rjesenje s transformacijom za f2 za pocetnu tocku (5,5): ");
        Matrix.printMatrix(rjesenjeSTransformacijom2);
    }
}
