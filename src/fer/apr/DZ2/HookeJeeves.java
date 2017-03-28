package fer.apr.DZ2;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.IFunction;

/**
 * Created by Marija on 6.12.2016..
 */
public class HookeJeeves {

    public static Matrix provediHookeJeeves(IFunction f, Matrix tocka, double pomak, double factor, double epsilon, boolean print){
        Matrix xb = new Matrix(tocka.getRowsCount(), tocka.getColsCount(), tocka.getElements().clone());
        Matrix xp = new Matrix(tocka.getRowsCount(), tocka.getColsCount(), tocka.getElements().clone());
        Matrix xn = new Matrix(tocka.getRowsCount(), tocka.getColsCount(), tocka.getElements().clone());
        //TODO ispisivanje
        int iteracija = 1;
        while (pomak > epsilon) {
            //nadi xn
            if (print) {
                System.out.println("Iteracija: " + iteracija);
            }
            for(int i = 0; i<xn.getColsCount(); i++){
                //double[][] elementsPlus = copyPoint(xn);
                Matrix plus = copyPoint(xn);
                //elementsPlus[0][i] = elementsPlus[0][i] + pomak;
                plus.getElements()[0][i] = plus.getElements()[0][i] + pomak;
                //System.out.println("elementsPlus " + elementsPlus[0][i]);
                //Matrix plus = new Matrix(xp.getRowsCount(), xn.getColsCount(),elementsPlus.clone());

                //Matrix plus = copyPoint(xn);
                //double[][] elementsMinus =copyPoint(xn);
                //System.out.println("elementsMinus " + elementsMinus[0][i]);
                //elementsMinus[0][i] = elementsMinus[0][i] - pomak;
                //System.out.println("elementsMinus " + elementsMinus[0][i]);
                //Matrix minus = new Matrix(xn.getRowsCount(), xn.getColsCount(),elementsMinus.clone());
                Matrix minus = copyPoint(xn);
                minus.getElements()[0][i] = minus.getElements()[0][i] - pomak;

                /*System.out.println("Matrix plus:");
                Matrix.printMatrix(plus);
                System.out.println("Matrix xn:");
                Matrix.printMatrix(xn);
                System.out.println("Matrix minus:");
                Matrix.printMatrix(minus);
                System.out.println("Value at plus: " + f.valueAt(plus) + "\tValue at xn: " + f.valueAt(xn) + "\tValue at minus: " + f.valueAt(minus));*/

                Matrix currentMinimum = xn;
                if(f.valueAt(plus) < f.valueAt(currentMinimum)){
                    currentMinimum = plus;
                }
                if(f.valueAt(minus) < f.valueAt(currentMinimum)){
                    currentMinimum = minus;
                }
                //System.out.println("Value at current minimum = " + f.valueAt(currentMinimum));
                xn = currentMinimum;
                //System.out.println("New xn = ");
                //Matrix.printMatrix(xn);

            }
            if (print) {
                System.out.print("xb = ");
                Matrix.printMatrix(xb);
                System.out.print("\txp = ");
                Matrix.printMatrix(xp);
                System.out.print("\txn = ");
                Matrix.printMatrix(xn);
                System.out.println("Pomak = " + pomak);
            }
            if(f.valueAt(xn) < f.valueAt(xb)){
                //xp = 2*xn - xb;
                xp = Matrix.subtract(Matrix.scalarMultiply(xn,2),xb);

                xb = HookeJeeves.copyPoint(xn);
                xn = HookeJeeves.copyPoint(xp);
            }
            else{
                pomak = pomak * factor;
                if (print) {
                    System.out.println("Changing pomak to " + pomak);
                }
                xp = HookeJeeves.copyPoint(xb); //xb;
                xn = HookeJeeves.copyPoint(xp); //xp;
            }
            /*System.out.println("Matrices after change");
            System.out.print("xb = ");
            Matrix.printMatrix(xb);
            System.out.print("\txp = ");
            Matrix.printMatrix(xp);
            System.out.print("\txn = ");
            Matrix.printMatrix(xn);*/
            iteracija++;
        }
        return xb;
    }

    public static Matrix copyPoint(Matrix point){
        double[][] copy = new double[point.getRowsCount()][point.getColsCount()];
        for(int i = 0; i<point.getRowsCount();i++){
            copy[i]=point.getElements()[i].clone();
        }

        Matrix copyMatrix = new Matrix(point.getRowsCount(), point.getColsCount(), copy);


        return  copyMatrix;
    }
}
