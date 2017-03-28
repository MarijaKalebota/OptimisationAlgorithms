package fer.apr.DZ5.Zadaci;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ5.RungeKutta;
import fer.apr.DZ5.TrapezniPostupak;

/**
 * Created by Marija on 20.1.2017..
 */
public class Zad1 {

    private static double EPSILON = 1E-6;
    private static double ALPHA = 1;
    private static double BETA = 0.5;
    private static double GAMMA = 2;
    private static double SIGMA = 0.5;
    public static boolean PRINT = true;

    private static int MAX_ITERATIONS = 10000;
    private static double MUTATION_PROBABILITY = 0.13;
    private static int POPULATION_SIZE = 50;
    private static boolean ELITISM = true;

    public static void main(String [ ] args) {
        double[][] elements = new double[][]{{0, 1},{-1, 0}};

        Matrix A = new Matrix(2,2,elements);

        double[][] elements2 = new double[][]{{1}, {0}};
        //double[][] elements2 = new double[][]{{1}, {-2}};

        Matrix x = new Matrix(2,1, elements2);

        RungeKutta.provediRungeKutta(x, A, null, 0.1, 5);
        TrapezniPostupak.provediTrapezniPostupak(x, A, null, 0.1, 5);

    }

}
