package fer.apr.DZ4;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.AbstractFunction;

/**
 * Created by Marija on 6.1.2017..
 */
public class F7 extends AbstractFunction {

    public F7() {
        super();
    }

    @Override
    public double valueAt(Matrix point) {
        increment();
        double sumOfXSquared = 0;
        for (int i = 0; i < point.getElements().length; i++) {
            sumOfXSquared += Math.pow(point.getElement(0,i),2);
        }
        double returnValue = Math.pow(sumOfXSquared,0.25) * (1 + Math.pow(Math.sin(50*Math.pow(sumOfXSquared , 0.1)),2));
        return returnValue;
    }

    @Override
    public double valueAt(Matrix point, double t) {
        return 0;
    }

    public double valueAtDerivativeByX1(Matrix point){
        return 0;
    }

    public double valueAtDerivativeByX2(Matrix point){
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