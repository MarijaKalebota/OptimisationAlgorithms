package fer.apr.DZ3;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.AbstractFunction;

/**
 * Created by Marija on 7.12.2016..
 */
public class F4DZ3 extends AbstractFunction {

    public F4DZ3() {
        super();
    }

    @Override
    public double valueAt(Matrix point) {
        if (point.getColsCount() != 2)
            throw new IllegalArgumentException("Funckija prima dva parametra.");
        increment();
        return Math.pow(point.getElement(0, 0) - 3, 2) + Math.pow(point.getElement(0, 1), 2);
    }

    @Override
    public double valueAt(Matrix point, double t) {
        return 0;
    }

    public double valueAtDerivativeByX1(Matrix point){
        return 2*(point.getElement(0,0) - 3);
    }

    public double valueAtDerivativeByX2(Matrix point){
        return 2*(point.getElement(0,1));
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
        return 2;
    }

}