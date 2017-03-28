package fer.apr.DZ4.Zadaci;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.F6SchafferFunction;
import fer.apr.DZ4.*;

/**
 * Created by Marija on 6.1.2017..
 */
public class Zad2 {

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

        int[] numberOfDimensions = new int[]{1,3,6,9};
        Matrix[] rjesenjaZaGA6 = new Matrix[4];
        Matrix[] rjesenjaZaGA7 = new Matrix[4];
        for (int i = 0; i < numberOfDimensions.length; i++) {
            F6SchafferFunction f6 = new F6SchafferFunction();
            F7 f7 = new F7();

            double[][] elements6 = new double[1][numberOfDimensions[i]];
            Matrix tocka6 = new Matrix(1, numberOfDimensions[i], elements6);

            double[][] elements7 = new double[1][numberOfDimensions[i]];
            Matrix tocka7 = new Matrix(1, numberOfDimensions[i], elements7);

            AbstractPrikazRjesenja floatingPointPrikazRjesenja6 = new FloatingPoint(-50, 150, false);
            AbstractPrikazRjesenja floatingPointPrikazRjesenja7 = new FloatingPoint(-50, 150, false);

            GeneticAlgorithm ga6 = new GeneticAlgorithm(floatingPointPrikazRjesenja6, tocka6, POPULATION_SIZE, MUTATION_PROBABILITY, MAX_ITERATIONS, EPSILON, f6);
            Unit rjesenjeGenetskogAlgoritma6 = ga6.provediGenetskiAlgoritam3Eliminacijski(ELITISM, 3);

            GeneticAlgorithm ga7 = new GeneticAlgorithm(floatingPointPrikazRjesenja7, tocka7, POPULATION_SIZE, MUTATION_PROBABILITY, MAX_ITERATIONS, EPSILON, f7);
            Unit rjesenjeGenetskogAlgoritma7 = ga7.provediGenetskiAlgoritam3Eliminacijski(ELITISM, 3);

            double[][] matrixElements6 = new double[1][rjesenjeGenetskogAlgoritma6.getParams().length];
            matrixElements6[0] = rjesenjeGenetskogAlgoritma6.getParams().clone();
            Matrix bestUnitInMatrixForm6 = new Matrix(1, rjesenjeGenetskogAlgoritma6.getParams().length, matrixElements6);
            rjesenjaZaGA6[i] = bestUnitInMatrixForm6;

            double[][] matrixElements7 = new double[1][rjesenjeGenetskogAlgoritma7.getParams().length];
            matrixElements7[0] = rjesenjeGenetskogAlgoritma7.getParams().clone();
            Matrix bestUnitInMatrixForm7 = new Matrix(1, rjesenjeGenetskogAlgoritma7.getParams().length, matrixElements7);
            rjesenjaZaGA7[i] = bestUnitInMatrixForm7;

        }

        System.out.println("\n\n");

        System.out.println("Rjesenja genetskog algoritma za f6: ");
        for (int i = 0; i < rjesenjaZaGA6.length; i++) {
            Matrix.printMatrix(rjesenjaZaGA6[i]);
        }
        System.out.println("Rjesenja genetskog algoritma za f7: ");
        for (int i = 0; i < rjesenjaZaGA6.length; i++) {
            Matrix.printMatrix(rjesenjaZaGA7[i]);
        }
    }
}
