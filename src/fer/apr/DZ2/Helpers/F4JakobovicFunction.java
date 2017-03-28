package fer.apr.DZ2.Helpers;

import fer.apr.DZ1.Matrix;

/**
 * Created by Marija on 7.12.2016..
 */
public class F4JakobovicFunction extends AbstractFunction {

    public F4JakobovicFunction() {
        super();
    }

    @Override
    public double valueAt(Matrix point) {
        if (point.getColsCount() != 2)
            throw new IllegalArgumentException("Funckija prima dva parametra.");
        increment();
        double x1 = point.getElement(0, 0);
        double x2 = point.getElement(0, 1);
        return Math.abs((x1 - x2) * (x1 + x2)) + Math.sqrt(Math.pow(x1, 2) + Math.pow(x2, 2));
    }

    @Override
    public double valueAt(Matrix point, double t) {
        return 0;
    }

    @Override
    public double valueAtDerivativeByX1(Matrix point) {
        return 0;
    }

    @Override
    public double valueAtDerivativeByX2(Matrix point) {
        return 0;
    }

    @Override
    public double valueAtDerivativeByX1ThenX1(Matrix point) {
        return 0;
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
        return 0;
    }

}
