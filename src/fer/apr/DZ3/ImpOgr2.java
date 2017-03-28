package fer.apr.DZ3;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.IOgranicenja;

/**
 * Created by Marija on 18.12.2016..
 */
public class ImpOgr2 implements IOgranicenja {

    @Override
    public boolean isSatisfied(Matrix point) {

        if(2 - point.getElement(0,0) >= 0){
            return true;
        }
        else{
            return false;
        }

    }

    public double valueAt(Matrix point){
        if(this.isSatisfied(point)){
            return 2 - point.getElement(0,0);
        }
        else{
            //TODO jel ovo tocno?
            return Double.NEGATIVE_INFINITY;
        }
    }

}
