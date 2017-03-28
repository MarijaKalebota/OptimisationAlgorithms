package fer.apr.DZ2.Helpers;

import fer.apr.DZ1.Matrix;

/**
 * Created by Marija on 7.12.2016..
 */
public class F6SchafferFunction extends AbstractFunction {

    public F6SchafferFunction() {
        super();
    }

    @Override
    public double valueAt(Matrix point) {
        increment();
        double sum = 0;
        for (int i = 0; i < point.getColsCount(); i++)
            sum += Math.pow(point.getElement(0, i), 2);
        return 0.5 + (Math.pow(Math.sin(Math.sqrt(sum)), 2) - 0.5) / Math.pow(1 + 0.001 * sum, 2);
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