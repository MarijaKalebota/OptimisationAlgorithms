package fer.apr.DZ4.Zadaci;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.F6SchafferFunction;
import fer.apr.DZ4.AbstractPrikazRjesenja;
import fer.apr.DZ4.FloatingPoint;
import fer.apr.DZ4.GeneticAlgorithm;
import fer.apr.DZ4.Unit;

import java.io.*;

/**
 * Created by Marija on 6.1.2017..
 */
public class Zad5 {





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

    private static int ITERATIONS_FOR_FINDING_MEDIAN = 40;

    public static void main(String [ ] args) {

        int[] velicineTurnira = new int[]{3, 6, 10, 20, POPULATION_SIZE};


        Matrix[][] rezultatiZaBoxPlotVelicinaTurnira = new Matrix[5][ITERATIONS_FOR_FINDING_MEDIAN];
        Matrix[][] rezultatiZaBoxPlotVjerojatnostMutacije = new Matrix[4][ITERATIONS_FOR_FINDING_MEDIAN];
        //Matrix[] najboljaJedinkaOvisnoOPopulaciji = new Matrix[4];
        //Matrix[] najboljaJedinkaOvisnoOVjerojatnostiMutacije = new Matrix[4];

        for (int i = 0; i < velicineTurnira.length; i++) {
            for (int j = 0; j < ITERATIONS_FOR_FINDING_MEDIAN; j++) {
                F6SchafferFunction f6 = new F6SchafferFunction();

                double[][] elements6 = new double[1][2];
                Matrix tocka6 = new Matrix(1, 2, elements6);

                AbstractPrikazRjesenja floatingPointPrikazRjesenja6 = new FloatingPoint(-50, 150, false);

                GeneticAlgorithm ga6 = new GeneticAlgorithm(floatingPointPrikazRjesenja6, tocka6, POPULATION_SIZE, MUTATION_PROBABILITY, MAX_ITERATIONS, EPSILON, f6);
                Unit rjesenjeGenetskogAlgoritma6 = ga6.provediGenetskiAlgoritam3Eliminacijski(ELITISM, velicineTurnira[i]);

                double[][] matrixElements6 = new double[1][rjesenjeGenetskogAlgoritma6.getParams().length];
                matrixElements6[0] = rjesenjeGenetskogAlgoritma6.getParams().clone();
                Matrix bestUnitInMatrixForm6 = new Matrix(1, rjesenjeGenetskogAlgoritma6.getParams().length, matrixElements6);
                rezultatiZaBoxPlotVelicinaTurnira[i][j] = bestUnitInMatrixForm6;
            }
        }
        //za sve iteracije i sve velicine populacije zapisali najbolju jedinku u svakoj iteraciji - za svaku velicinu populacije imamo polje iteracija

        F6SchafferFunction f6 = new F6SchafferFunction();
        StringBuilder sb = new StringBuilder();
        sb.append("Turnir = 3,Turnir = 6,Turnir = 10,Turnir = 20,Turnir = " + POPULATION_SIZE + "\n");
        for (int j = 0; j < ITERATIONS_FOR_FINDING_MEDIAN; j++) {
            for (int i = 0; i < rezultatiZaBoxPlotVelicinaTurnira.length; i++) {
                if (i != rezultatiZaBoxPlotVelicinaTurnira.length - 1) {
                    sb.append(f6.valueAt(rezultatiZaBoxPlotVelicinaTurnira[i][j]) + ",");
                } else {
                    sb.append(f6.valueAt(rezultatiZaBoxPlotVelicinaTurnira[i][j]));
                }
            }
            sb.append("\n");
        }


        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("podaciZaBoxPlotVelicinaTurnira.txt"), "UTF-8"));
            writer.write(sb.toString());
            writer.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


        Matrix[] najboljaJedinkaOvisnoOVeliciniTurnira = new Matrix[5];
        for (int i = 0; i < rezultatiZaBoxPlotVelicinaTurnira.length; i++) {
            double min = Double.MAX_VALUE;
            for (int j = 0; j < rezultatiZaBoxPlotVelicinaTurnira[i].length; j++) {
                if (f6.valueAt(rezultatiZaBoxPlotVelicinaTurnira[i][j]) < min) {
                    min = f6.valueAt(rezultatiZaBoxPlotVelicinaTurnira[i][j]);
                    najboljaJedinkaOvisnoOVeliciniTurnira[i] = rezultatiZaBoxPlotVelicinaTurnira[i][j];
                }
            }
        }
        int indexOfBest = 0;
        double min = Double.MAX_VALUE;
        for (int i = 0; i < najboljaJedinkaOvisnoOVeliciniTurnira.length; i++) {
            if (f6.valueAt(najboljaJedinkaOvisnoOVeliciniTurnira[i]) < min) {
                indexOfBest = i;
                min = f6.valueAt((najboljaJedinkaOvisnoOVeliciniTurnira[i]));
            }
        }
        int dobivenaNajboljaVelicinaTurnira = velicineTurnira[indexOfBest];

        System.out.println("\n\n");

        System.out.println("Najbolja dobivena velicina turnira: " + dobivenaNajboljaVelicinaTurnira + "\n");

    }
}
