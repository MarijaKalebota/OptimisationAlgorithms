package fer.apr.DZ2.Helpers;

import fer.apr.DZ1.Matrix;

/**
 * Created by Marija on 12.1.2017..
 */
public class FZaUnutranjuTocku implements IFunction{
    IOgranicenja[] ogranicenjaNejednakosti;
    //public  TransformedFunction(IFunction f, IOgranicenja[] ogranicenjaNejednakosti, IOgranicenja[] ogranicenjaJednakosti){
    public  FZaUnutranjuTocku(IOgranicenja[] ogranicenjaNejednakosti){
        this.ogranicenjaNejednakosti = ogranicenjaNejednakosti;
    }

    @Override
    public double valueAt(double point) {
        return 0;
    }

    @Override
    public double valueAt(Matrix point) {
        //TODO increment();
        double sum = 0;
        //sum = sum + f.valueAt(point);
        for (int i = 0; i < ogranicenjaNejednakosti.length; i++) {
            if(ogranicenjaNejednakosti[i].isSatisfied(point)){
                //do nothing
            }
            else{
                sum = sum - ogranicenjaNejednakosti[i].valueAt(point);
            }

        }
        return sum;
    }

    @Override
    public double valueAt(Matrix point, double t) {
        //TODO increment();
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

    @Override
    public int getNumberOfCalls() {
        return 0;
    }
}
