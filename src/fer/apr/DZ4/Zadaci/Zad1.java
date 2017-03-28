package fer.apr.DZ4.Zadaci;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.AbstractFunction;
import fer.apr.DZ2.Helpers.F1RosenbrockBananaFunction;
import fer.apr.DZ2.Helpers.F3;
import fer.apr.DZ2.Helpers.F6SchafferFunction;
import fer.apr.DZ2.HookeJeeves;
import fer.apr.DZ3.GradijentniSpust;
import fer.apr.DZ4.*;

/**
 * Created by Marija on 6.1.2017..
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
        F1RosenbrockBananaFunction f1 = new F1RosenbrockBananaFunction();
        F3 f3 = new F3();
        F6SchafferFunction f6 = new F6SchafferFunction();
        F7 f7 = new F7();

        double[][] elements = new double[][]{{-1.9,2}};
        Matrix tocka1 = new Matrix(1,2,elements);

        double[][] elements3 = new double[][]{{0,0,0,0,0}};
        Matrix tocka3 = new Matrix(1,5,elements3);

        double[][] elements6 = new double[][]{{0,0}};
        Matrix tocka6 = new Matrix(1,2,elements6);

        double[][] elements7 = new double[][]{{0,0}};
        Matrix tocka7 = new Matrix(1,2,elements7);

        AbstractPrikazRjesenja floatingPointPrikazRjesenja1 = new FloatingPoint(-50,150,false);
        AbstractPrikazRjesenja floatingPointPrikazRjesenja3 = new FloatingPoint(-50,150,false);
        AbstractPrikazRjesenja floatingPointPrikazRjesenja6 = new FloatingPoint(-50,150,false);
        AbstractPrikazRjesenja floatingPointPrikazRjesenja7 = new FloatingPoint(-50,150,false);

        GeneticAlgorithm ga1 = new GeneticAlgorithm(floatingPointPrikazRjesenja1,tocka1,POPULATION_SIZE,MUTATION_PROBABILITY,MAX_ITERATIONS,EPSILON,f1);
        Unit rjesenjeGenetskogAlgoritma1 = ga1.provediGenetskiAlgoritam3Eliminacijski(ELITISM,3);

        GeneticAlgorithm ga3 = new GeneticAlgorithm(floatingPointPrikazRjesenja3,tocka3,POPULATION_SIZE,MUTATION_PROBABILITY,MAX_ITERATIONS,EPSILON,f3);
        Unit rjesenjeGenetskogAlgoritma3 = ga3.provediGenetskiAlgoritam3Eliminacijski(ELITISM,3);

        GeneticAlgorithm ga6 = new GeneticAlgorithm(floatingPointPrikazRjesenja6,tocka6,POPULATION_SIZE,MUTATION_PROBABILITY,MAX_ITERATIONS,EPSILON,f6);
        Unit rjesenjeGenetskogAlgoritma6 = ga6.provediGenetskiAlgoritam3Eliminacijski(ELITISM,3);

        GeneticAlgorithm ga7 = new GeneticAlgorithm(floatingPointPrikazRjesenja7,tocka7,POPULATION_SIZE,MUTATION_PROBABILITY,MAX_ITERATIONS,EPSILON,f7);
        Unit rjesenjeGenetskogAlgoritma7 = ga7.provediGenetskiAlgoritam3Eliminacijski(ELITISM,3);

        double[][] matrixElements1 = new double[1][rjesenjeGenetskogAlgoritma1.getParams().length];
        matrixElements1[0] = rjesenjeGenetskogAlgoritma1.getParams().clone();
        Matrix bestUnitInMatrixForm1 = new Matrix(1,rjesenjeGenetskogAlgoritma1.getParams().length,matrixElements1);

        double[][] matrixElements3 = new double[1][rjesenjeGenetskogAlgoritma3.getParams().length];
        matrixElements3[0] = rjesenjeGenetskogAlgoritma3.getParams().clone();
        Matrix bestUnitInMatrixForm3 = new Matrix(1,rjesenjeGenetskogAlgoritma3.getParams().length,matrixElements3);

        double[][] matrixElements6 = new double[1][rjesenjeGenetskogAlgoritma6.getParams().length];
        matrixElements6[0] = rjesenjeGenetskogAlgoritma6.getParams().clone();
        Matrix bestUnitInMatrixForm6 = new Matrix(1,rjesenjeGenetskogAlgoritma6.getParams().length,matrixElements6);

        double[][] matrixElements7 = new double[1][rjesenjeGenetskogAlgoritma7.getParams().length];
        matrixElements7[0] = rjesenjeGenetskogAlgoritma7.getParams().clone();
        Matrix bestUnitInMatrixForm7 = new Matrix(1,rjesenjeGenetskogAlgoritma7.getParams().length,matrixElements7);

        System.out.println("\n\n");
        System.out.println("Rjesenje genetskog algoritma za f1: ");
        Matrix.printMatrix(bestUnitInMatrixForm1);
        System.out.println("Rjesenje genetskog algoritma za f3: ");
        Matrix.printMatrix(bestUnitInMatrixForm3);
        System.out.println("Rjesenje genetskog algoritma za f6: ");
        Matrix.printMatrix(bestUnitInMatrixForm6);
        System.out.println("Rjesenje genetskog algoritma za f7: ");
        Matrix.printMatrix(bestUnitInMatrixForm7);
        /*System.out.println("Rjesenje sa zlatnim rezom: ");
        Matrix.printMatrix(rjesenjeGradijentnogSpustaSaZlatnimRezom);

        /*GeneticAlgorithm GA1 = new GeneticAlgorithm();

        rjesenjeGenetskogAlgoritma1 = GeneticAlgorithm.provediGenetskiAlgoritam(tocka1);

        System.out.println("\n\n");
        System.out.println("Rjesenje bez zlatnog reza: ");
        Matrix.printMatrix(rjesenjeGradijentnogSpustaBezZlatnogReza);
        System.out.println("Rjesenje sa zlatnim rezom: ");
        Matrix.printMatrix(rjesenjeGradijentnogSpustaSaZlatnimRezom);*/
    }
}
