package fer.apr.DZ2.Helpers;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import fer.apr.DZ1.Matrix;

/**
 * Created by Marija on 8.12.2016..
 */
public class TransformedFunction implements IFunction{
    IFunction f;
    double t;
    IOgranicenja[] ogranicenjaNejednakosti;
    IOgranicenja[] ogranicenjaJednakosti;
    //public  TransformedFunction(IFunction f, IOgranicenja[] ogranicenjaNejednakosti, IOgranicenja[] ogranicenjaJednakosti){
    public  TransformedFunction(IFunction f, double t, IOgranicenja[] ogranicenjaNejednakosti, IOgranicenja[] ogranicenjaJednakosti){
        this.f = f;
        this.t = t;
        this.ogranicenjaNejednakosti = ogranicenjaNejednakosti;
        this.ogranicenjaJednakosti = ogranicenjaJednakosti;
    }

    @Override
    public double valueAt(double point) {
        return 0;
    }

    public void setT(double t) {this.t = t;}

    public double getT() {return this.t;}

    @Override
    public double valueAt(Matrix point) {
        //TODO increment();
        double sum = 0;
        sum = sum + f.valueAt(point);
        for (int i = 0; i < ogranicenjaNejednakosti.length; i++) {
            if(ogranicenjaNejednakosti[i].isSatisfied(point)){
                sum = sum - (1/this.t)*Math.log(ogranicenjaNejednakosti[i].valueAt(point));
            }
            else{
                sum = Double.POSITIVE_INFINITY;
            }

        }
        //TODO ako su ogranicenja jednakosti null (nema ih)?
        if (ogranicenjaJednakosti != null) {
            for (int i = 0; i < ogranicenjaJednakosti.length; i++) {
                sum = sum + this.t*Math.pow(ogranicenjaJednakosti[i].valueAt(point),2);
            }
        }
        return sum;
    }

    @Override
    public double valueAt(Matrix point, double t) {
        //TODO increment();
        double sum = 0;
        sum = sum + f.valueAt(point);
        for (int i = 0; i < ogranicenjaNejednakosti.length; i++) {
            sum = sum - (1/t)*Math.log(ogranicenjaNejednakosti[i].valueAt(point));
        }
        //TODO ako su ogranicenja jednakosti null (nema ih)?
        if (ogranicenjaJednakosti != null) {
            for (int i = 0; i < ogranicenjaJednakosti.length; i++) {
                sum = sum + t*Math.pow(ogranicenjaJednakosti[i].valueAt(point),2);
            }
        }
        return sum;
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
