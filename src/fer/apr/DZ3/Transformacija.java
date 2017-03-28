package fer.apr.DZ3;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.FZaUnutranjuTocku;
import fer.apr.DZ2.Helpers.IFunction;
import fer.apr.DZ2.Helpers.IOgranicenja;
import fer.apr.DZ2.Helpers.TransformedFunction;
import fer.apr.DZ2.HookeJeeves;

/**
 * Created by Marija on 7.12.2016..
 */
public class Transformacija {

    public static Matrix provediTransformacijuUProblemBezOgranicenjaNaMjesovitiNacin(IFunction f, Matrix tocka, double t, IOgranicenja[] ogranicenjaNejednakosti, IOgranicenja[] ogranicenjaJednakosti, double pomak, double factor, double epsilon, boolean print){

        //pronaci unutarnju poc. tocku ako tocka ne zadovoljava ogr. nejedn. (1,1)

        //G, nac minimum, to je poc. tocka za ostatak postupka

        FZaUnutranjuTocku g = new FZaUnutranjuTocku(ogranicenjaNejednakosti);
        Matrix rjesenjeZaUnutarnjuTockuPoHookeJeevesu = HookeJeeves.provediHookeJeeves(g,tocka,0.5,factor,epsilon,print);

        System.out.println("Pronadena unutarnja pocetna tocka: ");
        Matrix.printMatrix(rjesenjeZaUnutarnjuTockuPoHookeJeevesu);


        //TransformedFunction transformedFunction = new TransformedFunction(f, ogranicenjaNejednakosti, ogranicenjaJednakosti);
        TransformedFunction transformedFunction = new TransformedFunction(f, t, ogranicenjaNejednakosti, ogranicenjaJednakosti);
        //Matrix rjesenjePoHookeJeevesu = tocka;
        Matrix rjesenjePoHookeJeevesu = rjesenjeZaUnutarnjuTockuPoHookeJeevesu;

        double udaljenostTocakaIzDvijeIteracije = Double.MAX_VALUE;

        boolean keepGoing = true;

        while(keepGoing){
            Matrix rjesenjeIzPrethodneIteracije = rjesenjePoHookeJeevesu;

            //rjesenjePoHookeJeevesu = HookeJeeves.provediHookeJeeves(f,rjesenjePoHookeJeevesu,pomak,factor,epsilon,print);
            rjesenjePoHookeJeevesu = HookeJeeves.provediHookeJeeves(transformedFunction,rjesenjePoHookeJeevesu,0.5, factor,epsilon,print);


            //t = t*10;
            transformedFunction.setT(transformedFunction.getT()*10);

            keepGoing = false;
            for(int i = 0; i < tocka.getColsCount(); i++){
                double razlika = Math.abs(rjesenjePoHookeJeevesu.getElement(0,i) - rjesenjeIzPrethodneIteracije.getElement(0,i));
                if(razlika > epsilon){
                    keepGoing = true;
                }
            }

        }

        return rjesenjePoHookeJeevesu;

    }
}
