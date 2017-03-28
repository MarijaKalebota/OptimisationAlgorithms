package fer.apr.DZ4;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Marija on 6.1.2017..
 */
public class FloatingPoint extends AbstractPrikazRjesenja {
    public int dg;
    public int gg;
    public boolean aritmetickoKrizanje_InaceHeuristickoKrizanje;

    public FloatingPoint(int dg, int gg, boolean aritmetickoKrizanje_InaceHeuristickoKrizanje){
        this.dg = dg;
        this.gg = gg;
    }

    @Override
    public Unit cross(Unit parent1, Unit parent2) {
        double randomNumber = ThreadLocalRandom.current().nextDouble(0,1);
        double[] childElements = new double[parent1.getParams().length];
        if(this.aritmetickoKrizanje_InaceHeuristickoKrizanje){
            //aritmeticko krizanje
            for (int i = 0; i < parent1.getParams().length; i++) {
                double newResult = randomNumber * parent1.getParams()[i] + (1 - randomNumber) * parent2.getParams()[i];
                if(newResult > this.gg){
                    newResult = this.gg;
                }
                if(newResult < this.dg){
                    newResult = this.dg;
                }
                //childElements[i] = randomNumber * parent1.getParams()[i] + (1 - randomNumber) * parent2.getParams()[i];
                childElements[i] = newResult;
            }
        }
        else{
            //heuristicko krizanje
            if(parent1.getBadness() < parent2.getBadness()){
                for (int i = 0; i < parent1.getParams().length; i++) {
                    double newResult = randomNumber* (parent1.getParams()[i] - parent2.getParams()[i]) + parent1.getParams()[i];
                    if(newResult > this.gg){
                        newResult = this.gg;
                    }
                    if(newResult < this.dg){
                        newResult = this.dg;
                    }
                    //childElements[i] = randomNumber* (parent1.getParams()[i] - parent2.getParams()[i]) + parent1.getParams()[i];
                    childElements[i] = newResult;
                }
            }
            else{
                for (int i = 0; i < parent1.getParams().length; i++) {
                    double newResult = randomNumber* (parent2.getParams()[i] - parent1.getParams()[i]) + parent2.getParams()[i];
                    if(newResult > this.gg){
                        newResult = this.gg;
                    }
                    if(newResult < this.dg){
                        newResult = this.dg;
                    }
                    //childElements[i] = randomNumber* (parent1.getParams()[i] - parent2.getParams()[i]) + parent1.getParams()[i];
                    childElements[i] = newResult;
                    //childElements[i] = randomNumber* (parent2.getParams()[i] - parent1.getParams()[i]) + parent2.getParams()[i];
                }
            }
        }

        Unit child = new Unit(childElements);

        return child;
    }

    @Override
    public Unit mutate(Unit unit, double vjerojatnostMutacije) {
        double randomNumber = ThreadLocalRandom.current().nextDouble(0,1);
        double[] newElements = new double[unit.getParams().length];
        if(randomNumber < vjerojatnostMutacije){
            Random newRandom = new Random();
            for (int i = 0; i < unit.getParams().length; i++) {
                double newResult = unit.getParams()[i] + newRandom.nextGaussian();
                if(newResult > this.gg){
                    newResult = this.gg;
                }
                if(newResult < this.dg){
                    newResult = this.dg;
                }
                //newElements[i] = unit.getParams()[i] + newRandom.nextGaussian();
                newElements[i] = newResult;
            }
            Unit mutatedUnit = new Unit(newElements);
            return mutatedUnit;
        }
        else{
            Unit returnUnit = new Unit(unit.getParams().clone());
            return returnUnit;
        }
    }
}
