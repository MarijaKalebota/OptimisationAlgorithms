package fer.apr.DZ2;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.IFunction;
import fer.apr.DZ2.Helpers.OneDimensionalFunction;

/**
 * Created by Marija on 7.12.2016..
 */
public class KoordinatneOsi2{

    static Matrix tockaIzPrethIter;

    public static Matrix provediPostupakPoKoordinatnimOsima(IFunction f, Matrix tocka, double epsilon, boolean print){
        //Matrix copyOfTocka = new Matrix(tocka.getRowsCount(),tocka.getColsCount(),tocka.getElements().clone());
        Matrix copyOfTocka = HookeJeeves.copyPoint(tocka);
        boolean keepGoing = true;
        while (keepGoing) {
            tockaIzPrethIter = HookeJeeves.copyPoint(copyOfTocka);
            double numberOfCoordinates = copyOfTocka.getColsCount();
            for(int i = 0; i < numberOfCoordinates; i++){
                OneDimensionalFunction oneDimF = new OneDimensionalFunction(f, i, copyOfTocka);
                double[] interval = ZlatniRez2.provediZlatniRezSPocTockom(copyOfTocka.getElement(0,i), epsilon, oneDimF, print);
                double newValueForCoordinate = (interval[0] + interval[1])/2;
                copyOfTocka.setElement(0,i, newValueForCoordinate);
            }
            keepGoing = false;
            for(int i = 0; i < tocka.getColsCount(); i++){
                double razlika = Math.abs(copyOfTocka.getElement(0,i) - tockaIzPrethIter.getElement(0,i));
                if(razlika > epsilon){
                    keepGoing = true;
                }
            }
            //System.out.println();
        }
        return copyOfTocka;
    }
    //for
        //učvrsti sve točke osim i-te
        //stvori 1-dim f-ju s točkom

        //pošalji nju u zl. rez - dobij i-tu koordinatu
        //



}
