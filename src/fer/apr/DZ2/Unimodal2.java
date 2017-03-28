package fer.apr.DZ2;

import fer.apr.DZ2.Helpers.IFunction;

/**
 * Created by Marija on 6.12.2016..
 */
public class Unimodal2 {

    public static double[] findUnimodal(double tocka, double h, IFunction f){
        double valueAtLeftOfTocka = f.valueAt(tocka - 2*h);
        double valueAtTocka = f.valueAt(tocka);
        double valueAtRightOfTocka = f.valueAt(tocka + 2*h);
        int power = 1;
        double[] returnValue = new double[2];
        if(valueAtLeftOfTocka < valueAtTocka){
            //koristi formulu s minusom
            double rightSideOfInterval = tocka;
            double middleOfInterval = tocka - 2*h;
            double leftSideOfInterval = tocka - 2*2*h;
            //while (valueAtTocka > valueAtLeftOfTocka){
            while (f.valueAt(middleOfInterval) > f.valueAt(leftSideOfInterval)){
                rightSideOfInterval = tocka - Math.pow(2,power)*h;
                middleOfInterval = tocka - Math.pow(2,power+1)*h;
                leftSideOfInterval = tocka - Math.pow(2,power+2)*h;
                //valueAtTocka = valueAtLeftOfTocka;
                //valueAtLeftOfTocka = f.valueAt(tocka - Math.pow(2,power)*h);
                //double leftSideOfInterval = tocka - Math.pow(2,power)*h;
                power++;
            }

            returnValue[0] = leftSideOfInterval;
            returnValue[1] = rightSideOfInterval;
            return returnValue;



        }
        else if(valueAtRightOfTocka < valueAtTocka){
            //koristi formulu s plusom
            double leftSideOfInterval = tocka;
            double middleOfInterval = tocka + 2*h;
            double rightSideOfInterval = tocka + 2*2*h;

            while (f.valueAt(middleOfInterval) > f.valueAt(rightSideOfInterval)){
                leftSideOfInterval = tocka + Math.pow(2,power)*h;
                middleOfInterval = tocka + Math.pow(2,power+1)*h;
                rightSideOfInterval = tocka + Math.pow(2,power+2)*h;
                power++;
            }

            returnValue[0] = leftSideOfInterval;
            returnValue[1] = rightSideOfInterval;
            return returnValue;
        }
        else{
            //System.out.println("Pri trazenju unimod. intervala nije vrijedio ni jedan ni drugi uvjet.");
            returnValue[0] = tocka - 2*h;
            returnValue[1] = tocka + 2*h;
            return returnValue;
        }

    }

}
