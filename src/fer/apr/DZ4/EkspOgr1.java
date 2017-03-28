package fer.apr.DZ4;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.IOgranicenja;

/**
 * Created by Marija on 6.1.2017..
 */
public class EkspOgr1 implements IOgranicenja {
    @Override
    public boolean isSatisfied(Matrix point) {
        boolean returnValue = true;
        for (int i = 0; i < point.getElements().length; i++) {
            if(point.getElement(0,i) >= -50 && point.getElement(0,i) <= 150){
                //do nothing
            }
            else{
                returnValue = false;
            }
        }
        return returnValue;
    }

    @Override
    public double valueAt(Matrix point) {
        //TODO treba li mi ovo?
        boolean returnValue = true;
        for (int i = 0; i < point.getElements().length; i++) {
            if(point.getElement(0,i) >= -100 && point.getElement(0,i) <= 100){
                //do nothing
            }
            else{
                returnValue = false;
            }
        }
        if(returnValue == true){
            //return
        }
        return 0;
    }

    public double getGG(){
        return 150;
    }

    public double getDG(){
        return -50;
    }
}
