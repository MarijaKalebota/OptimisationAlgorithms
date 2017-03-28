package fer.apr.DZ3.Zadaci;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.F1RosenbrockBananaFunction;
import fer.apr.DZ2.Helpers.F2;
import fer.apr.DZ2.HookeJeeves;
import fer.apr.DZ2.NelderMead;
import fer.apr.DZ3.F3DZ3;
import fer.apr.DZ3.GradijentniSpust;
import fer.apr.DZ3.NewtonRaphson;

/**
 * Created by Marija on 19.12.2016..
 */
public class Zad2 {

    private static double EPSILON = 1E-6;
    private static double ALPHA = 1;
    private static double BETA = 0.5;
    private static double GAMMA = 2;
    private static double SIGMA = 0.5;
    public static boolean PRINT = false;

    public static void main(String [ ] args) {
        F1RosenbrockBananaFunction f11 = new F1RosenbrockBananaFunction();
        F1RosenbrockBananaFunction f12 = new F1RosenbrockBananaFunction();
        F2 f21 = new F2();
        F2 f22 = new F2();
        double[][] elements1 = new double[][]{{-1.9,2}};
        Matrix tocka11 =  new Matrix(1,2,elements1);
        Matrix tocka12 = HookeJeeves.copyPoint(tocka11);

        double[][] elements2 = new double[][]{{0.1,0.3}};
        Matrix tocka21 =  new Matrix(1,2,elements2);
        Matrix tocka22 =  HookeJeeves.copyPoint(tocka21);

        Matrix rjesenjeGradijentnogSpustaF1 = GradijentniSpust.provediGradijentniSpust(f11,tocka11,EPSILON,true,PRINT);
        Matrix rjesenjeNewtonRhapsonaF1 = NewtonRaphson.provediNewtonRaphson(f12,tocka12,EPSILON,true,PRINT);

        Matrix rjesenjeGradijentnogSpustaF2 = GradijentniSpust.provediGradijentniSpust(f21,tocka21,EPSILON,true,PRINT);
        Matrix rjesenjeNewtonRhapsonaF2 = NewtonRaphson.provediNewtonRaphson(f22,tocka22,EPSILON,true,PRINT);

        //TODO Ispišite broj izračuna funkcije, gradijenta i Hesseove matrice.

        System.out.println("\n\n");
        System.out.println("Rjesenje gradijentnog spusta za f1: ");
        Matrix.printMatrix(rjesenjeGradijentnogSpustaF1);
        System.out.println("Rjesenje Newton-Raphsona za f1: ");
        Matrix.printMatrix(rjesenjeNewtonRhapsonaF1);

        System.out.println("Rjesenje gradijentnog spusta za f2: ");
        Matrix.printMatrix(rjesenjeGradijentnogSpustaF2);
        System.out.println("Rjesenje Newton-Raphsona za f2: ");
        Matrix.printMatrix(rjesenjeNewtonRhapsonaF2);
    }

}
