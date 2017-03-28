package fer.apr.DZ2.Zadaci;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.F3OneDimensional;
import fer.apr.DZ2.HookeJeeves;
import fer.apr.DZ2.NelderMead;
import fer.apr.DZ2.ZlatniRez2;

/**
 * Created by Marija on 7.12.2016..
 */
public class Zad1 {
    private static double EPSILON = 1E-6;
    private static double ALPHA = 1;
    private static double BETA = 0.5;
    private static double GAMMA = 2;
    private static double SIGMA = 0.5;
    public static boolean PRINT = false;

    public static void main(String [ ] args) {
        F3OneDimensional f3OneDimensional1 = new F3OneDimensional();
        F3OneDimensional f3OneDimensional12 = new F3OneDimensional();
        F3OneDimensional f3OneDimensional13 = new F3OneDimensional();
        double[][] elements = new double[][]{{10}};
        Matrix tocka1 = new Matrix(1, 1, elements);
        Matrix tocka12 = HookeJeeves.copyPoint(tocka1);//new Matrix(1, 1, elements.clone());
        Matrix tocka13 = HookeJeeves.copyPoint(tocka1); //new Matrix(1, 1, elements.clone());

        double[] interval = ZlatniRez2.provediZlatniRezSPocTockom(tocka1.getElement(0, 0), EPSILON, f3OneDimensional1, PRINT);
        //System.out.println("Izasao sam iz zlatnog reza");
        double rjesenjeZlatnogReza = (interval[0] + interval[1]) / 2;
        Matrix rjesenjeHookeJeeves = HookeJeeves.provediHookeJeeves(f3OneDimensional12, tocka12, 1, 0.5, EPSILON, PRINT);
        //System.out.println("Izasao sam iz Hooke-Jeevesa");
        Matrix rjesenjeNelderMead = NelderMead.provediNelderMead(f3OneDimensional13, tocka13, 1, ALPHA, BETA, GAMMA, SIGMA, EPSILON, PRINT);

        System.out.println("\n-------------------------------------------------------------------------------\n");

        System.out.println("ZR 10\tMinimum: " + rjesenjeZlatnogReza + "\tBroj poziva: " +f3OneDimensional1.getNumberOfCalls());
        System.out.print("HJ 10\tMinimum: ");
        Matrix.printMatrix(rjesenjeHookeJeeves);
        System.out.println("\tBroj poziva: " +f3OneDimensional12.getNumberOfCalls());
        System.out.print("NM 10\tMinimum: ");
        Matrix.printMatrix(rjesenjeNelderMead);
        System.out.println("\tBroj poziva: " +f3OneDimensional13.getNumberOfCalls());

        System.out.println("\n-------------------------------------------------------------------------------\n");

        F3OneDimensional f3OneDimensional2 = new F3OneDimensional();
        F3OneDimensional f3OneDimensional22 = new F3OneDimensional();
        F3OneDimensional f3OneDimensional23 = new F3OneDimensional();
        double[][] elements2 = new double[][]{{20}};
        Matrix tocka2 = new Matrix(1, 1, elements2);
        Matrix tocka22 = HookeJeeves.copyPoint(tocka2);//new Matrix(1, 1, elements2.clone());
        Matrix tocka23 = HookeJeeves.copyPoint(tocka2);//new Matrix(1, 1, elements2.clone());

        double[] interval2 = ZlatniRez2.provediZlatniRezSPocTockom(tocka2.getElement(0, 0), EPSILON, f3OneDimensional2, PRINT);
        double rjesenjeZlatnogReza2 = (interval2[0] + interval2[1]) / 2;
        Matrix rjesenjeHookeJeeves2 = HookeJeeves.provediHookeJeeves(f3OneDimensional22, tocka22, 1, 0.5, EPSILON, PRINT);
        Matrix rjesenjeNelderMead2 = NelderMead.provediNelderMead(f3OneDimensional23, tocka23, 1, ALPHA, BETA, GAMMA, SIGMA, EPSILON, PRINT);

        System.out.println("ZR 20\tMinimum: " + rjesenjeZlatnogReza2 + "\tBroj poziva: " +f3OneDimensional2.getNumberOfCalls());
        System.out.print("HJ 20\tMinimum: ");
        Matrix.printMatrix(rjesenjeHookeJeeves2);
        System.out.println("\tBroj poziva: " +f3OneDimensional22.getNumberOfCalls());
        System.out.print("NM 20\tMinimum: ");
        Matrix.printMatrix(rjesenjeNelderMead2);
        System.out.println("\tBroj poziva: " +f3OneDimensional23.getNumberOfCalls());

        System.out.println("\n-------------------------------------------------------------------------------\n");

        F3OneDimensional f3OneDimensional3 = new F3OneDimensional();
        F3OneDimensional f3OneDimensional32 = new F3OneDimensional();
        F3OneDimensional f3OneDimensional33 = new F3OneDimensional();
        double[][] elements3 = new double[][]{{40}};
        Matrix tocka3 = new Matrix(1, 1, elements3);
        Matrix tocka32 = HookeJeeves.copyPoint(tocka3);//new Matrix(1, 1, elements3.clone());
        Matrix tocka33 = HookeJeeves.copyPoint(tocka3);//new Matrix(1, 1, elements3.clone());

        double[] interval3 = ZlatniRez2.provediZlatniRezSPocTockom(tocka3.getElement(0, 0), EPSILON, f3OneDimensional3, PRINT);
        double rjesenjeZlatnogReza3 = (interval3[0] + interval3[1]) / 2;
        Matrix rjesenjeHookeJeeves3 = HookeJeeves.provediHookeJeeves(f3OneDimensional32, tocka32, 1, 0.5, EPSILON, PRINT);
        Matrix rjesenjeNelderMead3 = NelderMead.provediNelderMead(f3OneDimensional33, tocka33, 1, ALPHA, BETA, GAMMA, SIGMA, EPSILON, PRINT);

        System.out.println("ZR 40\tMinimum: " + rjesenjeZlatnogReza3 + "\tBroj poziva: " +f3OneDimensional3.getNumberOfCalls());
        System.out.print("HJ 40\tMinimum: ");
        Matrix.printMatrix(rjesenjeHookeJeeves3);
        System.out.println("\tBroj poziva: " +f3OneDimensional32.getNumberOfCalls());
        System.out.print("NM 40\tMinimum: ");
        Matrix.printMatrix(rjesenjeNelderMead3);
        System.out.println("\tBroj poziva: " +f3OneDimensional33.getNumberOfCalls());

    }
}
