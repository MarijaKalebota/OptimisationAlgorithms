package fer.apr.DZ2.Helpers;

import fer.apr.DZ1.Matrix;

/**
 * Created by Marija on 7.12.2016..
 */
public class F2 extends AbstractFunction {

    public F2() {
        super();
    }

    @Override
    public double valueAt(Matrix point) {
        if (point.getColsCount() != 2)
            throw new IllegalArgumentException("Funckija prima dva parametra.");
        increment();
        return Math.pow(point.getElement(0, 0) - 4, 2) + 4 * Math.pow(point.getElement(0, 1) - 2, 2);
    }

    @Override
    public double valueAt(Matrix point, double t) {
        return 0;
    }

    public double valueAtDerivativeByX1(Matrix point){
        return 2*(point.getElement(0,0) - 4);
    }

    public double valueAtDerivativeByX2(Matrix point){
        return 8*(point.getElement(0,1)-2);
    }

    @Override
    public double valueAtDerivativeByX1ThenX1(Matrix point) {
        return 2;
    }

    @Override
    public double valueAtDerivativeByX1ThenX2(Matrix point) {
        return 0;
    }

    @Override
    public double valueAtDerivativeByX2ThenX1(Matrix point) {
        return 0;
    }

    @Override
    public double valueAtDerivativeByX2ThenX2(Matrix point) {
        return 8;
    }

}