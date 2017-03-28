package fer.apr.DZ2.Helpers;

/**
 * Created by Marija on 5.12.2016..
 */
public abstract class AbstractFunction implements IFunction {

    protected int counter;

    public AbstractFunction() {
        counter=0;
    }

    @Override
    public double valueAt(double point) {
        throw new IllegalAccessError("Funckija mora primiti matricu.");
    }

    protected void increment(){
        counter++;
    }

    @Override
    public int getNumberOfCalls() {
        return counter;
    }

}
