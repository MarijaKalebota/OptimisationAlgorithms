package fer.apr.DZ4.Zadaci;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.F6SchafferFunction;
import fer.apr.DZ2.HookeJeeves;
import fer.apr.DZ4.*;

import java.io.*;

/**
 * Created by Marija on 6.1.2017..
 */
public class Zad4 {



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

        int[] populationSizes = new int[]{30,50,100,200};
        double[] mutationProbabilities = new double[]{0.1,0.3,0.6,0.9};


        Matrix[][] rezultatiZaBoxPlotPopulacija = new Matrix[4][ITERATIONS_FOR_FINDING_MEDIAN];
        Matrix[][] rezultatiZaBoxPlotVjerojatnostMutacije = new Matrix[4][ITERATIONS_FOR_FINDING_MEDIAN];
        //Matrix[] najboljaJedinkaOvisnoOPopulaciji = new Matrix[4];
        //Matrix[] najboljaJedinkaOvisnoOVjerojatnostiMutacije = new Matrix[4];

        for (int i = 0; i < populationSizes.length; i++) {
            for (int j = 0; j < ITERATIONS_FOR_FINDING_MEDIAN; j++) {
                F6SchafferFunction f6 = new F6SchafferFunction();

                double[][] elements6 = new double[1][2];
                Matrix tocka6 = new Matrix(1, 2, elements6);

                AbstractPrikazRjesenja floatingPointPrikazRjesenja6 = new FloatingPoint(-50, 150, false);

                GeneticAlgorithm ga6 = new GeneticAlgorithm(floatingPointPrikazRjesenja6, tocka6, populationSizes[i], MUTATION_PROBABILITY, MAX_ITERATIONS, EPSILON, f6);
                Unit rjesenjeGenetskogAlgoritma6 = ga6.provediGenetskiAlgoritam3Eliminacijski(ELITISM, 3);

                double[][] matrixElements6 = new double[1][rjesenjeGenetskogAlgoritma6.getParams().length];
                matrixElements6[0] = rjesenjeGenetskogAlgoritma6.getParams().clone();
                Matrix bestUnitInMatrixForm6 = new Matrix(1, rjesenjeGenetskogAlgoritma6.getParams().length, matrixElements6);
                rezultatiZaBoxPlotPopulacija[i][j] = bestUnitInMatrixForm6;
            }
        }
        //za sve iteracije i sve velicine populacije zapisali najbolju jedinku u svakoj iteraciji - za svaku velicinu populacije imamo polje iteracija

        F6SchafferFunction f6 = new F6SchafferFunction();
        StringBuilder sb = new StringBuilder();
        sb.append("Population = 30,Population = 50,Population = 100,Population = 200\n");
        for (int j = 0; j < ITERATIONS_FOR_FINDING_MEDIAN; j++) {
            for (int i = 0; i < rezultatiZaBoxPlotPopulacija.length; i++) {
                if (i != rezultatiZaBoxPlotPopulacija.length - 1) {
                    sb.append(f6.valueAt(rezultatiZaBoxPlotPopulacija[i][j]) + ",");
                }
                else{
                    sb.append(f6.valueAt(rezultatiZaBoxPlotPopulacija[i][j]));
                }
            }
            sb.append("\n");
        }


        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("podaciZaBoxPlotPopulacija.txt"), "UTF-8"));
            writer.write(sb.toString());
            writer.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


        Matrix[] najboljaJedinkaOvisnoOPopulaciji = new Matrix[4];
        for (int i = 0; i < rezultatiZaBoxPlotPopulacija.length; i++) {
            double min = Double.MAX_VALUE;
            for (int j = 0; j < rezultatiZaBoxPlotPopulacija[i].length; j++) {
                if(f6.valueAt(rezultatiZaBoxPlotPopulacija[i][j]) < min){
                    min = f6.valueAt(rezultatiZaBoxPlotPopulacija[i][j]);
                    najboljaJedinkaOvisnoOPopulaciji[i] = rezultatiZaBoxPlotPopulacija[i][j];
                }
            }
        }
        int indexOfBest = 0;
        double min = Double.MAX_VALUE;
        for (int i = 0; i < najboljaJedinkaOvisnoOPopulaciji.length; i++) {
            if(f6.valueAt(najboljaJedinkaOvisnoOPopulaciji[i]) < min){
                indexOfBest = i;
                min = f6.valueAt((najboljaJedinkaOvisnoOPopulaciji[i]));
            }
        }
        int dobivenaNajboljaVelicinaPopulacije = populationSizes[indexOfBest];

        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


        for (int i = 0; i < mutationProbabilities.length; i++) {
            for (int j = 0; j < ITERATIONS_FOR_FINDING_MEDIAN; j++) {
                F6SchafferFunction f61 = new F6SchafferFunction();

                double[][] elements6 = new double[1][2];
                Matrix tocka6 = new Matrix(1, 2, elements6);

                AbstractPrikazRjesenja floatingPointPrikazRjesenja6 = new FloatingPoint(-50, 150, false);

                GeneticAlgorithm ga6 = new GeneticAlgorithm(floatingPointPrikazRjesenja6, tocka6, dobivenaNajboljaVelicinaPopulacije, mutationProbabilities[i], MAX_ITERATIONS, EPSILON, f61);
                Unit rjesenjeGenetskogAlgoritma6 = ga6.provediGenetskiAlgoritam3Eliminacijski(ELITISM, 3);

                double[][] matrixElements6 = new double[1][rjesenjeGenetskogAlgoritma6.getParams().length];
                matrixElements6[0] = rjesenjeGenetskogAlgoritma6.getParams().clone();
                Matrix bestUnitInMatrixForm6 = new Matrix(1, rjesenjeGenetskogAlgoritma6.getParams().length, matrixElements6);
                rezultatiZaBoxPlotVjerojatnostMutacije[i][j] = bestUnitInMatrixForm6;
            }
        }
        //za sve iteracije i sve velicine populacije zapisali najbolju jedinku u svakoj iteraciji - za svaku velicinu populacije imamo polje iteracija

        F6SchafferFunction f61 = new F6SchafferFunction();
        StringBuilder sb1 = new StringBuilder();
        sb1.append("Mutation = 0.1,Mutation = 0.3,Mutation = 0.6,Mutation = 0.9\n");
        for (int j = 0; j < ITERATIONS_FOR_FINDING_MEDIAN; j++) {
            for (int i = 0; i < rezultatiZaBoxPlotVjerojatnostMutacije.length; i++) {
                if (i != rezultatiZaBoxPlotPopulacija.length - 1) {
                    sb1.append(f61.valueAt(rezultatiZaBoxPlotVjerojatnostMutacije[i][j]) + ",");
                }
                else{
                    sb1.append(f61.valueAt(rezultatiZaBoxPlotVjerojatnostMutacije[i][j]));
                }
            }
            sb1.append("\n");
        }


        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("podaciZaBoxPlotVjerojatnostMutacije.txt"), "UTF-8"));
            writer.write(sb1.toString());
            writer.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


        Matrix[] najboljaJedinkaOvisnoOVjerojatnostiMutacije = new Matrix[4];
        for (int i = 0; i < rezultatiZaBoxPlotVjerojatnostMutacije.length; i++) {
            double min1 = Double.MAX_VALUE;
            for (int j = 0; j < rezultatiZaBoxPlotVjerojatnostMutacije[i].length; j++) {
                if(f61.valueAt(rezultatiZaBoxPlotVjerojatnostMutacije[i][j]) < min1){
                    min1 = f61.valueAt(rezultatiZaBoxPlotVjerojatnostMutacije[i][j]);
                    najboljaJedinkaOvisnoOVjerojatnostiMutacije[i] = rezultatiZaBoxPlotVjerojatnostMutacije[i][j];
                }
            }
        }
        int indexOfBest1 = 0;
        double min1 = Double.MAX_VALUE;
        for (int i = 0; i < najboljaJedinkaOvisnoOVjerojatnostiMutacije.length; i++) {
            if(f61.valueAt(najboljaJedinkaOvisnoOVjerojatnostiMutacije[i]) < min1){
                indexOfBest1 = i;
                min1 = f6.valueAt((najboljaJedinkaOvisnoOVjerojatnostiMutacije[i]));
            }
        }
        double dobivenaNajboljaVjerojatnostMutacije = mutationProbabilities[indexOfBest];



        System.out.println("\n\n");

        System.out.println("Najbolja dobivena velicina populacije: " + dobivenaNajboljaVelicinaPopulacije + "\n");
        System.out.println("Najbolja dobivena vjerojatnost mutacije: " + dobivenaNajboljaVjerojatnostMutacije);

    }

}
