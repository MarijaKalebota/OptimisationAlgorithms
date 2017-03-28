package fer.apr.DZ2;

import fer.apr.DZ2.Helpers.IFunction;

/**
 * Created by Marija on 6.12.2016..
 */
public class ZlatniRez2 {
    private static double EPSILON = 1E-6;

    public static double[] provediZlatniRezSPocTockom(double tocka, double epsilon, IFunction f, boolean print) {
        if (print) {
            System.out.println("Usao sam u provodenje zlatnog reza");
        }
        double[] unimodalniInterval = Unimodal2.findUnimodal(tocka, 1, f);
        if (print) {
            System.out.println("Izasao sam iz trazenja unimodalnog intervala");
        }
        double a = unimodalniInterval[0];
        double b = unimodalniInterval[1];
        if (print) {
            System.out.println("Unimodalni interval. a = " + a + "\tb = " + b);
        }

        double k = (Math.sqrt(5) - 1) / 2.; //k = 0.618
        if (print) {
            System.out.println("k = " + k);
        }

        double c = b - (b-a)*k;
        double d = a + (b-a)*k;
        if (print) {
            System.out.println("c = " + c + "\td = " + d);
        }
        int iteracija = 1;
        while (Math.abs(a-b) > epsilon) {
            if (print) {
                System.out.println("Iteracija: " + iteracija);
                System.out.println("c = " + c + "\td = " + d);
            }
            if(f.valueAt(c)<=f.valueAt(d)){
                //pomakni c na d, d na b, izračunaj novi c
                if (print) {
                    System.out.println("c je bolji od d");
                }
                b = d;
                d = c;
                c = b - (b-a)*k;
            }
            else if(f.valueAt(c) > f.valueAt(d)){
                //pomakni d na c, c na a, izračunaj novi d
                if (print) {
                    System.out.println("d je bolji od c");
                }
                a = c;
                c = d;
                d = a + (b-a)*k;
            }
            else {
                //System.out.println("U zlatnom rezu nije zadovoljen nijedan od dva uvjeta");
            }
            if (print) {
                System.out.println("Novi a = " + a + "\tc = " + c + "\td = " + d + "\t b = " + b);
            }
            //System.out.println("Novi c = " + c + "\t novi d = " + d);
            iteracija++;
        }
        double[] returnValue = new double[2];
        returnValue[0] = a;
        returnValue[1] = b;
        return returnValue;


    }

    public static double[] provediZlatniRezSPoznatimIntervalom(double a, double b, double epsilon, IFunction f){
        double k = (1. + Math.sqrt(5)) / 2.; //k = 0.618

        double c = b - (b-a)*k;
        double d = a + (b-a)*k;
        while (Math.abs(a-b) > epsilon) {
            if(f.valueAt(c)<=f.valueAt(d)){
                //pomakni c na d, d na b, izračunaj novi c
                b = d;
                d = c;
                c = b - (b-a)*k;
            }
            else if(f.valueAt(c) > f.valueAt(d)){
                //pomakni d na c, c na a, izračunaj novi d
                a = c;
                c = d;
                d = a + (b-a)*k;
            }
        }
        double[] returnValue = new double[2];
        returnValue[0] = a;
        returnValue[1] = b;
        return returnValue;
    }

    }
