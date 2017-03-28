package fer.apr.DZ2.Zadaci;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.F1RosenbrockBananaFunction;
import fer.apr.DZ2.Helpers.F2;
import fer.apr.DZ2.Helpers.F3OneDimensional;
import fer.apr.DZ2.Helpers.F4JakobovicFunction;
import fer.apr.DZ2.HookeJeeves;
import fer.apr.DZ2.KoordinatneOsi2;
import fer.apr.DZ2.NelderMead;
import fer.apr.DZ2.ZlatniRez2;

/**
 * Created by Marija on 8.12.2016..
 */
public class Pom2 {


    private static double EPSILON = 1E-6;
    private static double ALPHA = 1;
    private static double BETA = 0.5;
    private static double GAMMA = 2;
    private static double SIGMA = 0.5;
    public static boolean PRINT = false;

    public static void main(String [ ] args) {
        F2 f2 = new F2();
        F3OneDimensional f3 = new F3OneDimensional();
        F1RosenbrockBananaFunction f1 = new F1RosenbrockBananaFunction();
        F4JakobovicFunction f4 = new F4JakobovicFunction();
        double[][] elements = new double[][]{{0, 0}};
        Matrix tocka1 = new Matrix(1, 2, elements);
        double tocka = 0;

        //Matrix rjesenjeHJ = HookeJeeves.provediHookeJeeves(f4, tocka1,1, 0.5, EPSILON);
        //Matrix rjesenjeNelderMead = NelderMead.provediNelderMead(f1, tocka1, 1, ALPHA, BETA, GAMMA, SIGMA, EPSILON);
        //double[] interval = ZlatniRez2.provediZlatniRezSPocTockom(tocka, EPSILON, f3);
        //double rjesenjeZlatnogReza = (interval[0] + interval[1]) / 2;

        //F2 f2 = new F2();
        double[][] elements2 = new double[][]{{-1.9,2.0}};
        Matrix tocka2 =  new Matrix(1,2,elements2);

        KoordinatneOsi2.provediPostupakPoKoordinatnimOsima(f1,tocka2,EPSILON, PRINT);
        return;
    }
}
