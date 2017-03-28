package fer.apr.DZ2.DEPRECATED;

/**
 * Created by Marija on 10.11.2016..
 */
public class Functions {

    public static IBinaryFunction f1(){


        return (x1,x2) -> {
            return 100 * Math.pow(x2 - Math.pow(x1, 2), 2) + Math.pow(1 - x1, 2);
        };


    }

    public static IBinaryFunction f2(){
        return (x1,x2) -> {
            return Math.pow((x1-4), 2)+ 4* Math.pow((x2-2), 2);
        };
    }

    /*public static IMatrixFunction f3(int dimension){
        return a -> {
            return
        }
    }*/

    public static IBinaryFunction f4(){
        return (x1,x2) -> {
            return Math.abs((x1-x2)*(x1 + x2)) + Math.pow((Math.pow(x1,2) + Math.pow(x2,2)),0.5);
        };
    }
}
