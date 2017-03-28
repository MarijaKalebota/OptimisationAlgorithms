package fer.apr.DZ2.DEPRECATED;

import fer.apr.DZ2.Helpers.IFunction;

/**
 * Created by Marija on 5.12.2016..
 */
public class DEPRECATEDUnimodal {

    public static double[] findUnimodal(double tocka, double h, IFunction f){
        double l = tocka - h;
        double r = tocka + h;

        double m = tocka;

        double fl, fm, fr;

        int step = 1;

        fm = f.valueAt(tocka);
        fl = f.valueAt(l);
        fr = f.valueAt(r);

        double[] returnValue = new double[2];

        if(fm < fr && fm < fl){
            returnValue[0] = l;
            returnValue[1] = r;
            return returnValue;

        }
        else if(fm > fr){
            do{
                l = m;
                m = r;
                fm = fr;
                r = tocka + h * (step *= 2);
                fr = f.valueAt(r);
            }while (fm>fr);
        }
        else{
            do{
                r = m;
                m = l;
                fm = fl;
                l = tocka - h*(step *= 2);
                fl = f.valueAt(l);

            }while(fm > fl);
        }
        returnValue[0] = l;
        returnValue[1] = r;
        return returnValue;


    }

}
