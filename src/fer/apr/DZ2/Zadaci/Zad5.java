package fer.apr.DZ2.Zadaci;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.F6SchafferFunction;
import fer.apr.DZ2.HookeJeeves;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Marija on 7.12.2016..
 */
public class Zad5 {

    private static double EPSILON = 1E-6;
    private static double ALPHA = 1;
    private static double BETA = 0.5;
    private static double GAMMA = 2;
    private static double SIGMA = 0.5;
    private static int LENGTH = 2000;
    public static boolean PRINT = false;

    public static void main(String [ ] args) {

        Matrix[] rjesenja = new Matrix[LENGTH];
        for (int i = 0; i < LENGTH; i++) {

            F6SchafferFunction f6 = new F6SchafferFunction();
            double randomNumber1 = ThreadLocalRandom.current().nextDouble(-50, 50);
            double randomNumber2 = ThreadLocalRandom.current().nextDouble(-50, 50);
            System.out.println("randomNumber1 = " + randomNumber1 + "\trandomNumber2 = " + randomNumber2);
            double[][] elements = new double[][]{{randomNumber1,randomNumber2}};
            Matrix tocka =  new Matrix(1,2,elements);

            Matrix rjesenjeHookeJeeves = HookeJeeves.provediHookeJeeves(f6, tocka, 1, 0.5, EPSILON, PRINT);

            rjesenja[i] = rjesenjeHookeJeeves;
        }
        F6SchafferFunction f6 = new F6SchafferFunction();
        int numberOfGlobalOptimums = 0;
        for (int i = 0; i < LENGTH; i++) {

            if(f6.valueAt(rjesenja[i])<0.0001){
                numberOfGlobalOptimums++;
            }

        }
        double percentageOfGlobalOptimums = numberOfGlobalOptimums/(double)LENGTH;
        System.out.println("\nUdio globalnih optimuma za " + LENGTH + " iteracija: " + percentageOfGlobalOptimums);

    }


}
