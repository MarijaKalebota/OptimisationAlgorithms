package fer.apr.DZ2;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.IFunction;

/**
 * Created by Marija on 6.12.2016..
 */
public class NelderMead {

    public static Matrix reflect(Matrix xc, Matrix xh, double alpha){
        return Matrix.subtract(Matrix.scalarMultiply(xc,(1+alpha)), Matrix.scalarMultiply(xh,alpha));
        //return (1+alpha)*xc - alpha*xh;
    }

    public static Matrix expand(Matrix xc, Matrix xr, double gamma){
        return Matrix.add(Matrix.scalarMultiply(xc,(1-gamma)), Matrix.scalarMultiply(xr,gamma));
        //return (1-gamma)*xc - gamma*xr;
    }

    public static Matrix contract(Matrix xc, Matrix xh, double beta){
        return Matrix.add(Matrix.scalarMultiply(xc,(1-beta)), Matrix.scalarMultiply(xh,beta));
        //return (1-beta)*xc + beta * xh;
    }

    public static Matrix provediNelderMead(IFunction f, Matrix tocka, double pomakZaGeneriranjeSimpleksa, double alpha, double beta, double gamma, double sigma, double epsilon, boolean print){

        //generiraj simpleks od pocetne tocke
        Matrix[] simplex = new Matrix[tocka.getColsCount()+1];
        for(int i = 0; i < tocka.getColsCount(); i++){
            //double[][] newElements = tocka.getElements().clone();
            //newElements[0][i] = newElements[0][i] + pomakZaGeneriranjeSimpleksa;
            //Matrix newMatrix = new Matrix(tocka.getRowsCount(), tocka.getColsCount(), newElements);
            Matrix newMatrix = HookeJeeves.copyPoint(tocka);
            newMatrix.getElements()[0][i] = newMatrix.getElements()[0][i] + pomakZaGeneriranjeSimpleksa;
            simplex[i] = newMatrix;

        }
        simplex[tocka.getColsCount()] = tocka;



        //nadi xl - najbolja tocka
        //nadi xh - najgora tocka

        Matrix centroid;

        int xhIndex = 0;
        int xlIndex = 0;
        boolean keepGoing = true;
        int iteracija = 1;
        while (keepGoing) {

            double max = Double.MIN_VALUE;

            double min = Double.MAX_VALUE;

            for (int i = 0; i < simplex.length; i++) {
                if(f.valueAt(simplex[i])>max){
                    max = f.valueAt(simplex[i]);
                    xhIndex = i;
                }
                if(f.valueAt(simplex[i])<min){
                    min = f.valueAt(simplex[i]);
                    xlIndex = i;
                }

            }

            //izracunaj centroid
            double[][] sumElements = new double[1][tocka.getColsCount()];
            for (int i = 0; i < sumElements[0].length; i++) {
                sumElements[0][i] = 0;
            }
            Matrix sum = new Matrix(1,tocka.getColsCount(),sumElements);
            for (int i = 0; i < simplex.length; i++) {
                if( i == xhIndex){
                    //do nothing
                }
                else{

                    sum = Matrix.add(sum, simplex[i]);

                }
            }
            //centroid = sum/(simplex.length - 2);
            centroid = Matrix.scalarMultiply(sum, (1.0/(simplex.length - 1)));
            if (print) {
                System.out.println("Iteracija: " + iteracija + "\tCentroid: ");
                Matrix.printMatrix(centroid);
                System.out.println("Vrijednost funkcije u centroidu: " + f.valueAt(centroid));
            }
            Matrix xr = NelderMead.reflect(centroid,simplex[xhIndex],alpha);
            if(f.valueAt(xr) < f.valueAt(simplex[xlIndex])){
                Matrix xe = NelderMead.expand(centroid, xr, gamma);
                if(f.valueAt(xe) < f.valueAt(simplex[xlIndex])){
                    simplex[xhIndex] = xe;
                }
                else{
                    simplex[xhIndex] = xr;
                }
                /*
                ako F(Xe)<F(X[l])
                X[h] = Xe;
                inace
                X[h] = Xr;*/
            }
            else{
                boolean flag = true;
                for (int i = 0; i < simplex.length; i++) {
                    if(i==xhIndex){
                        //do nothing
                    }
                    else{
                        if(f.valueAt(xr) <= f.valueAt(simplex[i])){
                            flag = false;
                        }
                    }
                }
                if(flag == true){
                    if(f.valueAt(xr) < f.valueAt(simplex[xhIndex])){
                        simplex[xhIndex] = xr;
                    }

                    Matrix xk = contract(centroid,simplex[xhIndex],beta);
                    if(f.valueAt(xk) < f.valueAt(simplex[xhIndex])){
                        simplex[xhIndex] = xk;
                    }
                    else{
                        //pomakni sve tocke prema xl
                        for (int i = 0; i < simplex.length; i++) {
                            //simplex[i] = 1/2(simplex[i] + simplex[xlIndex]);
                            simplex[i] = Matrix.scalarMultiply(Matrix.add(simplex[i], simplex[xlIndex]),0.5);
                        }
                    }
                }
                else{
                    simplex[xhIndex] = xr;
                }
            }
            if(f.valueAt(xr) < f.valueAt((simplex[xhIndex]))){
                simplex[xhIndex] = xr;
            }
            keepGoing = false;
            for (int i = 0; i < simplex.length; i++) {
                if(Math.abs(f.valueAt(simplex[i]) - f.valueAt(centroid)) > epsilon){
                    keepGoing = true;
                }

            }
            iteracija++;


        }
        //return centroid;
        return  simplex[xlIndex];

    }

}
