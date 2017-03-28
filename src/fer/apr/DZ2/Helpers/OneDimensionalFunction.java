package fer.apr.DZ2.Helpers;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.HookeJeeves;

/**
 * Created by Marija on 7.12.2016..
 */
public class OneDimensionalFunction implements  IFunction{
    IFunction f;
    //koordinata koja nije fiksirana
    int koordinata;
    Matrix startPoint;

    public OneDimensionalFunction(IFunction f, int i, Matrix startPoint){
        this.startPoint = HookeJeeves.copyPoint(startPoint);
        this.koordinata = i;
        this.f = f;

    }



    @Override
    public double valueAt(double point) {
        //TODO increment();
        Matrix newPoint = new Matrix(startPoint.getRowsCount(), startPoint.getColsCount(), startPoint.getElements());
        newPoint.setElement(0, koordinata, point);
        return f.valueAt(newPoint);
    }

    @Override
    public double valueAt(Matrix point) {
        //TODO increment();
        Matrix newPoint = new Matrix(startPoint.getRowsCount(), startPoint.getColsCount(), startPoint.getElements());
        newPoint.setElement(0, koordinata, point.getElement(0,0));
        return f.valueAt(newPoint);
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

    @Override
    public int getNumberOfCalls() {
        return 0;
    }
}
