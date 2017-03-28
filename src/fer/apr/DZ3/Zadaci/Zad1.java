package fer.apr.DZ3.Zadaci;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.HookeJeeves;
import fer.apr.DZ3.F3DZ3;
import fer.apr.DZ3.GradijentniSpust;

/**
 * Created by Marija on 19.12.2016..
 */
public class Zad1 {
    private static double EPSILON = 1E-6;
    private static double ALPHA = 1;
    private static double BETA = 0.5;
    private static double GAMMA = 2;
    private static double SIGMA = 0.5;
    public static boolean PRINT = true;

    public static void main(String [ ] args) {
        F3DZ3 f31 = new F3DZ3();
        F3DZ3 f32 = new F3DZ3();
        double[][] elements = new double[][]{{0,0}};
        Matrix tocka1 = new Matrix(1,2,elements);
        Matrix tocka2 = HookeJeeves.copyPoint(tocka1);
        Matrix rjesenjeGradijentnogSpustaBezZlatnogReza = GradijentniSpust.provediGradijentniSpust(f31,tocka1,EPSILON,false,PRINT);
        Matrix rjesenjeGradijentnogSpustaSaZlatnimRezom = GradijentniSpust.provediGradijentniSpust(f32,tocka2,EPSILON,true,PRINT);

        System.out.println("\n\n");
        System.out.println("Rjesenje bez zlatnog reza: ");
        Matrix.printMatrix(rjesenjeGradijentnogSpustaBezZlatnogReza);
        System.out.println("Rjesenje sa zlatnim rezom: ");
        Matrix.printMatrix(rjesenjeGradijentnogSpustaSaZlatnimRezom);
    }

}
