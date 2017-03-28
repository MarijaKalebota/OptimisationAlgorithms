package fer.apr.DZ2.Helpers;

import fer.apr.DZ1.Matrix;

/**
 * Created by Marija on 18.12.2016..
 */
public interface IOgranicenja {

    public boolean isSatisfied(Matrix point);

    public double valueAt(Matrix point);
}
