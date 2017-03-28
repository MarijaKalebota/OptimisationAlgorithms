package fer.apr.DZ2.Zadaci;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.F1RosenbrockBananaFunction;
import fer.apr.DZ2.NelderMead;

/**
 * Created by Marija on 7.12.2016..
 */
public class Zad4 {

    private static double EPSILON = 1E-6;
    private static double ALPHA = 1;
    private static double BETA = 0.5;
    private static double GAMMA = 2;
    private static double SIGMA = 0.5;
    public static boolean PRINT = false;

    public static void main(String [ ] args) {
        /*F1RosenbrockBananaFunction f1 = new F1RosenbrockBananaFunction();
        double[][] elements1 = new double[][]{{0.5,0.5}};
        Matrix tocka1 =  new Matrix(1,3,elements1);*/

        int[] brojEvaluacija05 = new int[4];
        int[] brojEvaluacija20 = new int[4];
        Matrix[] minimumi05 = new Matrix[4];
        Matrix[] minimumi20 = new Matrix[4];
        int iterator = 0;
        for (int i = 1; i < 20; i+=5) {
            F1RosenbrockBananaFunction f1 = new F1RosenbrockBananaFunction();
            double[][] elements1 = new double[][]{{0.5,0.5}};
            Matrix tocka1 =  new Matrix(1,2,elements1);

            Matrix rjesenjeNelderMead = NelderMead.provediNelderMead(f1, tocka1, i, ALPHA, BETA, GAMMA, SIGMA, EPSILON, PRINT);
            brojEvaluacija05[iterator] = f1.getNumberOfCalls();
            minimumi05[iterator] = rjesenjeNelderMead;

            F1RosenbrockBananaFunction f12 = new F1RosenbrockBananaFunction();
            double[][] elements2 = new double[][]{{20,20}};
            Matrix tocka2 =  new Matrix(1,2,elements2);

            Matrix rjesenjeNelderMead2 = NelderMead.provediNelderMead(f12, tocka2, i, ALPHA, BETA, GAMMA, SIGMA, EPSILON, PRINT);
            brojEvaluacija20[iterator] = f12.getNumberOfCalls();
            minimumi20[iterator] = rjesenjeNelderMead2;
            iterator++;
        }

        //TODO ispis
        System.out.println("Evaluacije za 0.5:\t" + brojEvaluacija05[0] + "\t" + brojEvaluacija05[1] + "\t" + brojEvaluacija05[2] + "\t" + brojEvaluacija05[3]);
        System.out.println("Evaluacije za 20:\t" + brojEvaluacija20[0] + "\t" + brojEvaluacija20[1] + "\t" + brojEvaluacija20[2] + "\t" + brojEvaluacija20[3]);

        System.out.println("Minimumi za 0.5:\n");
        for (int i = 0; i < 4; i++) {
            Matrix.printMatrix(minimumi05[i]);
        }

        System.out.println("Minimumi za 20:\n");
        for (int i = 0; i < 4; i++) {
            Matrix.printMatrix(minimumi20[i]);
        }


    }
}
