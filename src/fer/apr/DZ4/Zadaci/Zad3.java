package fer.apr.DZ4.Zadaci;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.F6SchafferFunction;
import fer.apr.DZ2.HookeJeeves;
import fer.apr.DZ4.*;

/**
 * Created by Marija on 6.1.2017..
 */
public class Zad3 {

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

        int[] numberOfDimensions = new int[]{3,6};
        Matrix[] rjesenjaZaGA6FloatingPoint = new Matrix[2];
        Matrix[] rjesenjaZaGA7FloatingPoint = new Matrix[2];
        Matrix[] rjesenjaZaGA6Binarno = new Matrix[2];
        Matrix[] rjesenjaZaGA7Binarno = new Matrix[2];
        for (int i = 0; i < numberOfDimensions.length; i++) {
            F6SchafferFunction f6 = new F6SchafferFunction();
            F6SchafferFunction f62 = new F6SchafferFunction();
            F7 f7 = new F7();
            F7 f72 = new F7();

            double[][] elements6 = new double[1][numberOfDimensions[i]];
            Matrix tocka6 = new Matrix(1, numberOfDimensions[i], elements6);
            Matrix tocka62 = HookeJeeves.copyPoint(tocka6);

            double[][] elements7 = new double[1][numberOfDimensions[i]];
            Matrix tocka7 = new Matrix(1, numberOfDimensions[i], elements7);
            Matrix tocka72 = HookeJeeves.copyPoint(tocka7);

            AbstractPrikazRjesenja floatingPointPrikazRjesenja6 = new FloatingPoint(-50, 150, false);
            AbstractPrikazRjesenja floatingPointPrikazRjesenja7 = new FloatingPoint(-50, 150, false);

            AbstractPrikazRjesenja binaryPrikazRjesenja6 = new Binary(4,-50,150,false);
            AbstractPrikazRjesenja binaryPrikazRjesenja7 = new Binary(4,-50, 150, false);

            GeneticAlgorithm ga6 = new GeneticAlgorithm(floatingPointPrikazRjesenja6, tocka6, POPULATION_SIZE, MUTATION_PROBABILITY, MAX_ITERATIONS, EPSILON, f6);
            Unit rjesenjeGenetskogAlgoritma6 = ga6.provediGenetskiAlgoritam3Eliminacijski(ELITISM, 3);

            GeneticAlgorithm ga62 = new GeneticAlgorithm(binaryPrikazRjesenja6, tocka62, POPULATION_SIZE, MUTATION_PROBABILITY, MAX_ITERATIONS, EPSILON, f62);
            Unit rjesenjeGenetskogAlgoritma62 = ga62.provediGenetskiAlgoritam3Eliminacijski(ELITISM, 3);

            GeneticAlgorithm ga7 = new GeneticAlgorithm(floatingPointPrikazRjesenja7, tocka7, POPULATION_SIZE, MUTATION_PROBABILITY, MAX_ITERATIONS, EPSILON, f7);
            Unit rjesenjeGenetskogAlgoritma7 = ga7.provediGenetskiAlgoritam3Eliminacijski(ELITISM, 3);

            GeneticAlgorithm ga72 = new GeneticAlgorithm(binaryPrikazRjesenja7, tocka72, POPULATION_SIZE, MUTATION_PROBABILITY, MAX_ITERATIONS, EPSILON, f72);
            Unit rjesenjeGenetskogAlgoritma72 = ga72.provediGenetskiAlgoritam3Eliminacijski(ELITISM, 3);

            double[][] matrixElements6 = new double[1][rjesenjeGenetskogAlgoritma6.getParams().length];
            matrixElements6[0] = rjesenjeGenetskogAlgoritma6.getParams().clone();
            Matrix bestUnitInMatrixForm6 = new Matrix(1, rjesenjeGenetskogAlgoritma6.getParams().length, matrixElements6);
            rjesenjaZaGA6FloatingPoint[i] = bestUnitInMatrixForm6;

            double[][] matrixElements7 = new double[1][rjesenjeGenetskogAlgoritma7.getParams().length];
            matrixElements7[0] = rjesenjeGenetskogAlgoritma7.getParams().clone();
            Matrix bestUnitInMatrixForm7 = new Matrix(1, rjesenjeGenetskogAlgoritma7.getParams().length, matrixElements7);
            rjesenjaZaGA7FloatingPoint[i] = bestUnitInMatrixForm7;

            double[][] matrixElements62 = new double[1][rjesenjeGenetskogAlgoritma62.getParams().length];
            matrixElements62[0] = rjesenjeGenetskogAlgoritma62.getParams().clone();
            Matrix bestUnitInMatrixForm62 = new Matrix(1, rjesenjeGenetskogAlgoritma62.getParams().length, matrixElements62);
            rjesenjaZaGA6Binarno[i] = bestUnitInMatrixForm62;

            double[][] matrixElements72 = new double[1][rjesenjeGenetskogAlgoritma72.getParams().length];
            matrixElements72[0] = rjesenjeGenetskogAlgoritma72.getParams().clone();
            Matrix bestUnitInMatrixForm72 = new Matrix(1, rjesenjeGenetskogAlgoritma72.getParams().length, matrixElements72);
            rjesenjaZaGA7Binarno[i] = bestUnitInMatrixForm72;

        }

        System.out.println("\n\n");

        System.out.println("Rjesenja genetskog algoritma za f6 s pomicnom tockom: ");
        for (int i = 0; i < rjesenjaZaGA6FloatingPoint.length; i++) {
            Matrix.printMatrix(rjesenjaZaGA6FloatingPoint[i]);
        }
        System.out.println("Rjesenja genetskog algoritma za f7 s pomicnom tockom: ");
        for (int i = 0; i < rjesenjaZaGA6FloatingPoint.length; i++) {
            Matrix.printMatrix(rjesenjaZaGA7FloatingPoint[i]);
        }
        System.out.println("Rjesenja genetskog algoritma za f6 uz binarni prikaz: ");
        for (int i = 0; i < rjesenjaZaGA6Binarno.length; i++) {
            Matrix.printMatrix(rjesenjaZaGA6Binarno[i]);
        }
        System.out.println("Rjesenja genetskog algoritma za f7 uz binarni prikaz: ");
        for (int i = 0; i < rjesenjaZaGA6Binarno.length; i++) {
            Matrix.printMatrix(rjesenjaZaGA7Binarno[i]);
        }
    }

}
