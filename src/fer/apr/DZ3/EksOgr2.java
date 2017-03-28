package fer.apr.DZ3;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.IOgranicenja;

/**
 * Created by Marija on 18.12.2016..
 */
public class EksOgr2 implements IOgranicenja {

    @Override
    public boolean isSatisfied(Matrix point) {

        if(point.getElement(0,1) - 1 == 0){
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public double valueAt(Matrix point) {

            return point.getElement(0,1) - 1;

    }

    public double getGG(){
        return 1;
    }

    public double getDG(){
        return 1;
    }

}
