package fer.apr.DZ2.DEPRECATED;

import fer.apr.DZ2.Helpers.IFunction;

/**
 * Created by Marija on 10.11.2016..
 */
public class DEPRECATEDZlatniRez {

    private static double EPSILON = 1E-6;

    public static double[] provediZlatniRezSPocTockom(double tocka, double epsilon, IFunction f) {
        double[] unimodalniInterval = DEPRECATEDUnimodal.findUnimodal(tocka, 1, f);
        double a = unimodalniInterval[0];
        double b = unimodalniInterval[1];

        double k = (1. + Math.sqrt(5)) / 2.;

        double c = b - k*(b-a);
        double d = a + k * (b-a);
        double fc = f.valueAt(c);
        double fd = f.valueAt(d);
        while((b-a) > epsilon){
            if(fc < fd){
                b = d;
                d = c;
                c = b - k * (b - a);
                fd = fc;
                fc = f.valueAt(c);
            }
            else{
                a = c;
                c = d;
                d = a + k * (b - a);
                fc = fd;
                fd = f.valueAt(d);

            }
        }
        double[] returnValue = new double[2];
        returnValue[0] = a;
        returnValue[1] = b;
        return returnValue;
    }
/*
    public static double[] provediZlatniRezSPoznatimIntervalom(double a, double b, double epsilon, IFunction f){
        double k = (1. + Math.sqrt(5)) / 2.;

        double c = b - k*(b-a);
        double d = a + k * (b-a);
        double fc = f.valueAt(c);
        double fd = f.valueAt(d);
        while((b-a) > epsilon){
            if(fc < fd){
                b = d;
                d = c;
                c = b - k * (b - a);
                fd = fc;
                fc = f.valueAt(c);
            }
            else{
                a = c;
                c = d;
                d = a + k * (b - a);
                fc = fd;
                fd = f.valueAt(d);

            }
        }
        double[] returnValue = new double[2];
        returnValue[0] = a;
        returnValue[1] = b;
        return returnValue;

    }

    public static double[] provediZlatniRez(double tocka, double h, Callable<T> f){

        double[] unimodalniInterval = pronadiUnimodalniInterval(tocka, h, f);
        double k = (1. + Math.sqrt(5)) / 2.;
        double a = unimodalniInterval[0];
        double b = unimodalniInterval[1];

        double c = b - k*(b-a);
        double d = a + k * (b-a);
        double fc = f(c);
        double fd = f(d);
        while((b-a) > epsilon){
            if(fc < fd){
                b = d;
                d = c;
                c = b - k * (b - a);
                fd = fc;
                fc = f(c);
            }
            else{
                a = c;
                c = d;
                d = a + k * (b - a);
                fc = fd;
                fd = f(d);

            }
        }
        double[] returnValue = new double[2];
        returnValue[0] = a;
        returnValue[1] = b;
        return returnValue;


    }



    public static double[] pronadiUnimodalniInterval(double tocka, double h, Callable<T> f){

        double l = tocka - h;
        double r = tocka + h;
        double m = tocka;
        double fl, fm, fr;
        int step = 1;

        fm = f(tocka);
        fl = f(l);
        fr = f(r);

        if(fm < fr && fm < fl)
            return null;
        else if(fm > fr)
            do
            {	l = m;
                m = r;
                fm = fr;
                r = tocka + h * (step *= 2);
                fr = f(r);
            } while(fm > fr);
        else
            do
            {	r = m;
                m = l;
                fm = fl;
                l = tocka - h * (step *= 2);
                fl = f(l);
            } while(fm > fl);


    }*/
}
