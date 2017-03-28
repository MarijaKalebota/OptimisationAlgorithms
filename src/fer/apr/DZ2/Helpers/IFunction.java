package fer.apr.DZ2.Helpers;

import fer.apr.DZ1.Matrix;

/**
 * Created by Marija on 5.12.2016..
 */
public interface IFunction {

    public double valueAt(double point);

    public double valueAt(Matrix point);

    public double valueAt(Matrix point, double t);

    public double valueAtDerivativeByX1(Matrix point);

    public double valueAtDerivativeByX2(Matrix point);

    public double valueAtDerivativeByX1ThenX1(Matrix point);

    public double valueAtDerivativeByX1ThenX2(Matrix point);

    public double valueAtDerivativeByX2ThenX1(Matrix point);

    public double valueAtDerivativeByX2ThenX2(Matrix point);

    public int getNumberOfCalls();

}
