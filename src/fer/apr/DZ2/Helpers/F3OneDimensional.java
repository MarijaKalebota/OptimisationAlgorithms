package fer.apr.DZ2.Helpers;

import fer.apr.DZ1.Matrix;

/**
 * Created by Marija on 7.12.2016..
 */
public class F3OneDimensional extends AbstractFunction {

    public F3OneDimensional() {
        super();
    }

    @Override
    public double valueAt(double point) {
        increment();
        return Math.pow(point - 3, 2);
    }

    @Override
    public double valueAt(Matrix point) {
        if(point.getColsCount()!=1 && point.getRowsCount()!=1)
            throw new IllegalArgumentException("Tocka mora biti u jednodimenzijskom prostoru.");
        return valueAt(point.getElement(0, 0));
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
