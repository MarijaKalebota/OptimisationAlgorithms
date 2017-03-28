package fer.apr.DZ2.Helpers;

import fer.apr.DZ1.Matrix;

/**
 * Created by Marija on 5.12.2016..
 */
public class F1RosenbrockBananaFunction extends AbstractFunction {

    public F1RosenbrockBananaFunction() {
        super();
    }

    @Override
    public double valueAt(Matrix point) {
        if (point.getColsCount() != 2)
            throw new IllegalArgumentException("Funckija prima dva parametra.");
        increment();
        return 100 * Math.pow(point.getElement(0, 1) - Math.pow(point.getElement(0, 0), 2), 2) + Math.pow(1 - point.getElement(0, 0), 2);
    }

    @Override
    public double valueAt(Matrix point, double t) {
        return 0;
    }

    public double valueAtDerivativeByX1(Matrix point){
        return (-400)*(point.getElement(0,1) - Math.pow(point.getElement(0,0), 2))*point.getElement(0,0) + 2*point.getElement(0,0) - 2;
    }

    public double valueAtDerivativeByX2(Matrix point){
        return (200*(point.getElement(0,1) - Math.pow(point.getElement(0,0), 2)) );
    }

    public double valueAtDerivativeByX1ThenX1(Matrix point){

        //return(800*Math.pow(point.getElement(0,0),2)-2);
        return(1200*Math.pow(point.getElement(0,0),2) - 400* point.getElement(0,1)+2);

    }

    public double valueAtDerivativeByX1ThenX2(Matrix point){
        return (-400*point.getElement(0,0));
    }

    public double valueAtDerivativeByX2ThenX1(Matrix point){
        return (-400*point.getElement(0,0));
    }

    public double valueAtDerivativeByX2ThenX2(Matrix point){
        return 200;
    }

}