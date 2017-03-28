package fer.apr.DZ2.Helpers;

import fer.apr.DZ1.Matrix;

/**
 * Created by Marija on 7.12.2016..
 */
public class F3 extends AbstractFunction {

    public F3() {
        super();
    }

    @Override
    public double valueAt(double point) {
        increment();
        return Math.pow(point - 1, 2);
    }

    @Override
    public double valueAt(Matrix point) {
        increment();
        double sum = 0;
        for (int i = 0; i < point.getColsCount(); i++)
            sum += Math.pow(point.getElement(0, i) - (i + 1), 2);
        return sum;
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
