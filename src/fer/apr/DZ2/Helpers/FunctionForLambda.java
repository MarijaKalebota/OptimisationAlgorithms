package fer.apr.DZ2.Helpers;

import fer.apr.DZ1.Matrix;

/**
 * Created by Marija on 18.12.2016..
 */

public class FunctionForLambda extends AbstractFunction {

    private IFunction f;
    private Matrix x0;
    private Matrix v;

    public FunctionForLambda(IFunction f, Matrix x0, Matrix v) {
        this.f = f;
        this.x0 = x0;
        this.v = v;
    }

    @Override
    public double valueAt(double lambda) {
        //return f.valueAt(x0.nAdd(v.nScalarMultiply(lambda)));
        increment();
        return f.valueAt(Matrix.add(x0, Matrix.scalarMultiply(v, lambda)));
    }

    @Override
    public double valueAt(Matrix point) {
        return 0;
    }

    @Override
    public double valueAt(Matrix point, double t) {
        return 0;
    }

    @Override
    public double valueAtDerivativeByX1(Matrix point) {
        return f.valueAtDerivativeByX1(point);
    }

    @Override
    public double valueAtDerivativeByX2(Matrix point) {
        return f.valueAtDerivativeByX2(point);
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

    @Override
    public int getNumberOfCalls() {
        return f.getNumberOfCalls();
    }

}