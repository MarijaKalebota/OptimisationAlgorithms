package fer.apr.DZ3;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.IFunction;
import fer.apr.DZ2.Helpers.IOgranicenja;
import fer.apr.DZ2.HookeJeeves;
import fer.apr.DZ2.NelderMead;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Marija on 7.12.2016..
 */
public class Box {

    public static Matrix provediPostupakPoBoxu2(IFunction f, Matrix tocka, double[] donjeGranice, double[] gornjeGranice, IOgranicenja[] implicitnaOgranicenja, double epsilon, double alpha, boolean print){
        for (int i = 0; i < tocka.getElements().length; i++) {
            if(tocka.getElement(0,i) < donjeGranice[i] || tocka.getElement(0,i) > gornjeGranice[i]){
                System.out.println("Ponudili ste tocku koja ne zadovoljava eksplicitna ogranicenja!");
                return null;
            }
        }

        int n = tocka.getColsCount();

        Matrix centroid = HookeJeeves.copyPoint(tocka);

        List<Matrix> listaPrihvacenihTocaka = new LinkedList<Matrix>();

        listaPrihvacenihTocaka.add(tocka);

        for (int t = 0; t < 2 * n; t++) {
            double[][] elements = new double[1][tocka.getColsCount()];
            for (int i = 0; i < tocka.getColsCount(); i++) {
                double R = ThreadLocalRandom.current().nextDouble(0, 1);
                elements[0][i] = donjeGranice[i] + R * (gornjeGranice[i] - donjeGranice[i]);
            }
            Matrix novaTocka = new Matrix(1,tocka.getColsCount(),elements);
            for (int j = 0; j < implicitnaOgranicenja.length; j++) {
                while (!implicitnaOgranicenja[j].isSatisfied(novaTocka)){
                    novaTocka = Matrix.scalarMultiply(Matrix.add(novaTocka,centroid),0.5);
                }
            }

            listaPrihvacenihTocaka.add(novaTocka);

            //izracunaj novi centroid (sa novom prihvacenom tockom)
            double[][] sumElements = new double[1][tocka.getColsCount()];
            for (int i = 0; i < sumElements[0].length; i++) {
                sumElements[0][i] = 0;
            }
            Matrix sum = new Matrix(1,tocka.getColsCount(),sumElements);
            for (int i = 0; i < listaPrihvacenihTocaka.size(); i++) {
                sum = Matrix.add(sum, listaPrihvacenihTocaka.get(i));
            }
            //centroid = sum/(simplex.length - 2);
            centroid = Matrix.scalarMultiply(sum, (1.0/(listaPrihvacenihTocaka.size())));

        }

        boolean keepGoing = true;
        int iteracija = 1;

        while(keepGoing){

            double max = Double.MIN_VALUE;
            double valueAtXh = Double.MIN_VALUE;
            double valueAtXh2 = Double.MIN_VALUE;
            int xhIndex = 0;
            int xh2Index = 0;
            for (int i = 0; i < listaPrihvacenihTocaka.size(); i++) {
                //if(f.valueAt(i) > f.valueAt(xhIndex)){
                if(f.valueAt(listaPrihvacenihTocaka.get(i)) > f.valueAt(listaPrihvacenihTocaka.get(xhIndex))){
                    xh2Index = xhIndex;
                    xhIndex = i;
                }
            }
            //izracunaj centroid bez xh
            double[][] sumElements = new double[1][tocka.getColsCount()];
            for (int i = 0; i < sumElements[0].length; i++) { sumElements[0][i] = 0;}
            Matrix sum = new Matrix(1,tocka.getColsCount(),sumElements);
            for (int i = 0; i < listaPrihvacenihTocaka.size(); i++) {
                if( i == xhIndex){ /*do nothing*/ }
                else{ sum = Matrix.add(sum, listaPrihvacenihTocaka.get(i));}
            }
            centroid = Matrix.scalarMultiply(sum, (1.0/(listaPrihvacenihTocaka.size() - 1)));
            Matrix xr = NelderMead.reflect(centroid,listaPrihvacenihTocaka.get(xhIndex),alpha);
            for (int i = 0; i < n; i++) {
                if(xr.getElement(0,i) < donjeGranice[i]){
                    xr.getElements()[0][i] = donjeGranice[i];
                }
                else if(xr.getElements()[0][i] > gornjeGranice[i]){
                    xr.getElements()[0][i] = gornjeGranice[i];
                }
            }
            for (int i = 0; i < implicitnaOgranicenja.length; i++) {
                while (!implicitnaOgranicenja[i].isSatisfied(xr)){
                    xr = Matrix.scalarMultiply(Matrix.add(xr,centroid),0.5);
                }
            }
            if(f.valueAt(xr) > f.valueAt(listaPrihvacenihTocaka.get(xh2Index))){
                xr = Matrix.scalarMultiply(Matrix.add(xr,centroid),0.5);
            }
            Matrix[] arrayPrihvacenihTocaka = listaPrihvacenihTocaka.toArray(new Matrix[]{});
            arrayPrihvacenihTocaka[xhIndex] = xr;
            listaPrihvacenihTocaka = new LinkedList<Matrix>();
            for (int i = 0; i < arrayPrihvacenihTocaka.length; i++) {
                listaPrihvacenihTocaka.add(arrayPrihvacenihTocaka[i]);
            }

            keepGoing = false;
            for (int i = 0; i < listaPrihvacenihTocaka.size(); i++) {
                if(Math.abs(f.valueAt(listaPrihvacenihTocaka.get(i)) - f.valueAt(centroid)) > epsilon){
                    keepGoing = true;
                }

            }
            iteracija++;

        }

        return centroid;
    }

    /*public static Matrix provediPostupakPoBoxu(IFunction f, Matrix tocka, double pomakZaGeneriranjeSimpleksa, IOgranicenja[] implicitnaOgranicenja, IOgranicenja[] eksplicitnaOgranicenja, double epsilon, double alpha, boolean print){
        for (int i = 0; i < eksplicitnaOgranicenja.length; i++) {
            if(eksplicitnaOgranicenja[i].isSatisfied(tocka) == false){
                System.out.println("Ponudili ste tocku koja ne zadovoljava eksplicitna ogranicenja!");
                return null;
            }
        }
        for (int t = 0; t < 2 * n; t++) {
            for (int i = 0; i < n; i++) {
                double R = ThreadLocalRandom.current().nextDouble(0, 1);

            }
        }
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

            Matrix xr = NelderMead.reflect(centroid,simplex[xhIndex],alpha);

            //provjerit vrijede li eksplicitna ogranicenja za xr

    }*/

}
