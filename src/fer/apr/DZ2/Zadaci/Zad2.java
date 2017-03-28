package fer.apr.DZ2.Zadaci;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.*;
import fer.apr.DZ2.HookeJeeves;
import fer.apr.DZ2.KoordinatneOsi2;
import fer.apr.DZ2.NelderMead;

/**
 * Created by Marija on 7.12.2016..
 */
public class Zad2 {

    private static double EPSILON = 1E-6;
    private static double ALPHA = 1;
    private static double BETA = 0.5;
    private static double GAMMA = 2;
    private static double SIGMA = 0.5;
    public static boolean PRINT = false;

    public static void main(String [ ] args) {

        F1RosenbrockBananaFunction f1 = new F1RosenbrockBananaFunction();
        double[][] elements1 = new double[][]{{-1.9,2}};
        Matrix tocka1 =  new Matrix(1,2,elements1);
        Matrix tocka12 =  HookeJeeves.copyPoint(tocka1);//new Matrix(1,2,elements1.clone());
        Matrix tocka13 =  HookeJeeves.copyPoint(tocka1);//new Matrix(1,2,elements1.clone());
        F2 f2 = new F2();
        double[][] elements2 = new double[][]{{0.1,0.3}};
        Matrix tocka2 =  new Matrix(1,2,elements2);
        Matrix tocka22 =  HookeJeeves.copyPoint(tocka2);//new Matrix(1,2,elements2.clone());
        Matrix tocka23 =  HookeJeeves.copyPoint(tocka2);//new Matrix(1,2,elements2.clone());
        F3 f3 = new F3();
        double[][] elements3 = new double[][]{{0,0,0,0,0}};
        Matrix tocka3 =  new Matrix(1,5,elements3);
        Matrix tocka32 =  HookeJeeves.copyPoint(tocka3);//new Matrix(1,5,elements3.clone());
        Matrix tocka33 =  HookeJeeves.copyPoint(tocka3);//new Matrix(1,5,elements3.clone());
        F4JakobovicFunction f4 = new F4JakobovicFunction();
        double[][] elements4 = new double[][]{{5.1,1.1}};
        Matrix tocka4 =  new Matrix(1,2,elements4);
        Matrix tocka42 =  HookeJeeves.copyPoint(tocka4);//new Matrix(1,2,elements4.clone());
        Matrix tocka43 =  HookeJeeves.copyPoint(tocka4);new Matrix(1,2,elements4.clone());

        Matrix rjesenjeHookeJeeves1 = HookeJeeves.provediHookeJeeves(f1, tocka1, 1, 0.5, EPSILON, PRINT);
        F1RosenbrockBananaFunction f12 = new F1RosenbrockBananaFunction();
        Matrix rjesenjeNelderMead1 = NelderMead.provediNelderMead(f12, tocka12, 1, ALPHA, BETA, GAMMA, SIGMA, EPSILON, PRINT);
        F1RosenbrockBananaFunction f13 = new F1RosenbrockBananaFunction();
        Matrix rjesenjeKoordinatneOsi1 = KoordinatneOsi2.provediPostupakPoKoordinatnimOsima(f13,tocka13, EPSILON, PRINT);
        System.out.println("HJ f1 broj poziva: " + f1.getNumberOfCalls() + "\tminimum: ");
        Matrix.printMatrix(rjesenjeHookeJeeves1);
        System.out.println("NM f1 broj poziva: " + f12.getNumberOfCalls() + "\tminimum: ");
        Matrix.printMatrix(rjesenjeNelderMead1);
        System.out.println("KO f1 broj poziva: " + f13.getNumberOfCalls() + "\tminimum: ");
        Matrix.printMatrix(rjesenjeKoordinatneOsi1);

        System.out.println("\n-------------------------------------------------------------------------------\n");

        Matrix rjesenjeHookeJeeves2 = HookeJeeves.provediHookeJeeves(f2, tocka2, 1, 0.5, EPSILON, PRINT);
        F2 f22 = new F2();
        Matrix rjesenjeNelderMead2 = NelderMead.provediNelderMead(f22, tocka22, 1, ALPHA, BETA, GAMMA, SIGMA, EPSILON, PRINT);
        F2 f23 = new F2();
        Matrix rjesenjeKoordinatneOsi2 = KoordinatneOsi2.provediPostupakPoKoordinatnimOsima(f23,tocka23, EPSILON, PRINT);
        System.out.println("HJ f2 broj poziva: " + f2.getNumberOfCalls() + "\tminimum: ");
        Matrix.printMatrix(rjesenjeHookeJeeves2);
        System.out.println("NM f2 broj poziva: " + f22.getNumberOfCalls() + "\tminimum: ");
        Matrix.printMatrix(rjesenjeNelderMead2);
        System.out.println("KO f2 broj poziva: " + f23.getNumberOfCalls() + "\tminimum: ");
        Matrix.printMatrix(rjesenjeKoordinatneOsi2);

        System.out.println("\n-------------------------------------------------------------------------------\n");

        Matrix rjesenjeHookeJeeves3 = HookeJeeves.provediHookeJeeves(f3, tocka3, 1, 0.5, EPSILON, PRINT);
        F3 f32 = new F3();
        Matrix rjesenjeNelderMead3 = NelderMead.provediNelderMead(f32, tocka32, 1, ALPHA, BETA, GAMMA, SIGMA, EPSILON, PRINT);
        F3 f33 = new F3();
        Matrix rjesenjeKoordinatneOsi3 = KoordinatneOsi2.provediPostupakPoKoordinatnimOsima(f33,tocka33,EPSILON, PRINT);
        System.out.println("HJ f3 broj poziva: " + f3.getNumberOfCalls() + "\tminimum: ");
        Matrix.printMatrix(rjesenjeHookeJeeves3);
        System.out.println("NM f3 broj poziva: " + f32.getNumberOfCalls() + "\tminimum: ");
        Matrix.printMatrix(rjesenjeNelderMead3);
        System.out.println("KO f3 broj poziva: " + f33.getNumberOfCalls() + "\tminimum: ");
        Matrix.printMatrix(rjesenjeKoordinatneOsi3);

        System.out.println("\n-------------------------------------------------------------------------------\n");

        Matrix rjesenjeHookeJeeves4 = HookeJeeves.provediHookeJeeves(f4, tocka4, 1, 0.5, EPSILON, PRINT);
        F4JakobovicFunction f42 = new F4JakobovicFunction();
        Matrix rjesenjeNelderMead4 = NelderMead.provediNelderMead(f42, tocka42, 1, ALPHA, BETA, GAMMA, SIGMA, EPSILON, PRINT);
        F4JakobovicFunction f43 = new F4JakobovicFunction();
        Matrix rjesenjeKoordinatneOsi4 = KoordinatneOsi2.provediPostupakPoKoordinatnimOsima(f43,tocka43, EPSILON, PRINT);
        System.out.println("HJ f4 broj poziva: " + f4.getNumberOfCalls() + "\tminimum: ");
        Matrix.printMatrix(rjesenjeHookeJeeves4);
        System.out.println("NM f4 broj poziva: " + f42.getNumberOfCalls() + "\tminimum: ");
        Matrix.printMatrix(rjesenjeNelderMead4);
        System.out.println("KO f4 broj poziva: " + f43.getNumberOfCalls() + "\tminimum: ");
        Matrix.printMatrix(rjesenjeKoordinatneOsi4);


    }

}
