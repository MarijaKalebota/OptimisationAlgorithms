package fer.apr.DZ4; /**
 * Created by Marija on 16.11.2016..
 */
import java.util.concurrent.ThreadLocalRandom;
public class Unit {
    public double[] params;
    public double badness = Double.MAX_VALUE;
    public int indexInPopulation;

    public Unit(double[] params) {
        this.params = params.clone();
    }

    public Unit(double[] params, double badness){
        this.params = params;
        this.badness = badness;
    }

    public void setParams(double[] params){
        this.params = params;
    }

    public double[] getParams(){
        return this.params;
    }

    public void setBadness(double badness) {
        this.badness = badness;
    }

    public double getBadness() {return this.badness;};

    public void setIndexInPopulation(int indexInPopulation) {this.indexInPopulation = indexInPopulation;}

    public void mutate() {
        for(int i = 0; i < this.params.length; i++){
            double randomNumber = ThreadLocalRandom.current().nextDouble(0, 1);
            if (randomNumber <= 0.1){
                double randomValue = ThreadLocalRandom.current().nextDouble(-4, 4);
                this.params[i] = randomValue;
            }

        }
    }

    public void printUnit(){
        for (int i = 0; i < params.length; i++) {
            System.out.print(Double.toString(params[i]) + " ");
        }
        System.out.println();
    }
}
