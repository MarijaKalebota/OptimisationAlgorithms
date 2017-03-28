package fer.apr.DZ2.Helpers;

import fer.apr.DZ1.Matrix;

/**
 * Created by Marija on 8.12.2016..
 */
public class GSOneDimFunction {
    IFunction f;
    double[] vs;
    Matrix startPoint;

    public GSOneDimFunction(IFunction f, int i, Matrix startPoint){

    }


    /*@Override
    public double valueAt(double lambda) {
        Matrix newPoint = new Matrix(startPoint.getRowsCount(), startPoint.getColsCount(), startPoint.getElements()+lambda*vs);
        newPoint.setElement(0, koordinata, point.getElement(0,0));
        return f.valueAt(newPoint);
    }*/
}
