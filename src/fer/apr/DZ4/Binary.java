package fer.apr.DZ4;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Marija on 6.1.2017..
 */
public class Binary extends AbstractPrikazRjesenja {

    public int numberOfBytes;
    public int dg;
    public int gg;
    public boolean krizanjeSJednomTockomPrekida_InaceUniformno;

    public Binary(int numberOfBytes, int dg, int gg, boolean krizanjeSJednomTockomPrekida_InaceUniformno){
        this.numberOfBytes = numberOfBytes;
        this.dg = dg;
        this.gg = gg;
        this.krizanjeSJednomTockomPrekida_InaceUniformno = krizanjeSJednomTockomPrekida_InaceUniformno;

    }

    @Override
    public Unit cross(Unit parent1, Unit parent2) {
        //if(this.krizanjeSJednomTockomPrekida_InaceUniformno){
            //TODO implementiraj krizanje s jednom tockom prekida
            //prebaci parente u binarno
            int [][] parent1BinaryParameters = new int[parent1.getParams().length][this.numberOfBytes];
            for (int i = 0; i < parent1.getParams().length; i++) {
                double valueInInterval = ((parent1.getParams()[i] - this.dg) / (this.gg - this.dg))*(Math.pow(2, this.numberOfBytes) - 1);
                int roundValueInInterval = (int)(valueInInterval + 0.5);
                int maska = 1;
                int[] binaryParameter = new int[this.numberOfBytes];


                int remainder;
                double binary = 0;
                int iterator = 0;

                while(roundValueInInterval != 0) {
                    remainder = roundValueInInterval%2;
                    roundValueInInterval = roundValueInInterval/2;
                    //binary = binary + (remainder*iterator);
                    binaryParameter[iterator] = remainder;
                    iterator++;
                }


                /*for (int j = 0; j < this.numberOfBytes; j++) {
                    if ((roundValueInInterval & maska) != 0){
                        //postavi i-ti boolean na true
                        binaryParameter[j] = 1;
                    }
                    else{
                        binaryParameter[j] = 0;
                    }
                    maska = maska << 1;
                }*/
                parent1BinaryParameters[i] = binaryParameter;
            }

            int [][] parent2BinaryParameters = new int[parent1.getParams().length][this.numberOfBytes];
            for (int i = 0; i < parent2.getParams().length; i++) {
                double valueInInterval = ((parent2.getParams()[i] - this.dg) / (this.gg - this.dg))*(Math.pow(2, this.numberOfBytes) - 1);
                int roundValueInInterval = (int)(valueInInterval + 0.5);
                int maska = 1;
                int[] binaryParameter = new int[this.numberOfBytes];

                int remainder;
                double binary = 0;
                int iterator = 0;

                while(roundValueInInterval != 0) {
                    remainder = roundValueInInterval%2;
                    roundValueInInterval = roundValueInInterval/2;
                    //binary = binary + (remainder*iterator);
                    binaryParameter[iterator] = remainder;
                    iterator++;
                }

                /*for (int j = 0; j < this.numberOfBytes; j++) {
                    if ((roundValueInInterval & maska) != 0){
                        //postavi i-ti boolean na true
                        binaryParameter[j] = 1;
                    }
                    else{
                        binaryParameter[j] = 0;
                    }
                    maska = maska << 1;
                }*/
                parent2BinaryParameters[i] = binaryParameter;
            }

            //iskrizaj
        int[][] childBinaryParameters = new int[0][];
        childBinaryParameters = new int[parent1.getParams().length][this.numberOfBytes];

        if(this.krizanjeSJednomTockomPrekida_InaceUniformno){
            int randomNumber = ThreadLocalRandom.current().nextInt(0, this.numberOfBytes);
            for (int i = 0; i < parent1.getParams().length; i++) {
                for (int j = 0; j < this.numberOfBytes; j++) {
                    if (j < randomNumber) {
                        childBinaryParameters[i][j] = parent1BinaryParameters[i][j];
                    }
                    else{
                        childBinaryParameters[i][j] = parent2BinaryParameters[i][j];
                    }
                }
            }
        }
        else {
            //TODO implementiraj uniformno krizanje
            for (int i = 0; i < parent1.getParams().length; i++) {
                for (int j = 0; j < this.numberOfBytes; j++) {
                    int random01 = ThreadLocalRandom.current().nextInt(0,2);
                    if(parent1BinaryParameters[i][j] == parent2BinaryParameters[i][j]){
                        childBinaryParameters[i][j] = parent1BinaryParameters[i][j];
                    }
                    else {
                        childBinaryParameters[i][j] = random01;
                    }
                }
            }
        }
        //vrati dijete u Unit
        //za svaku varijablu djeteta
        double[] childElements = new double[parent1.getParams().length];
        for (int i = 0; i < childBinaryParameters.length; i++) {
            //za svaki bit u toj varijabli
            double floatingPointValue = 0;
            for (int j = 0; j < childBinaryParameters[i].length; j++) {
                floatingPointValue = floatingPointValue + childBinaryParameters[i][j] * Math.pow(2,j);
            }
            //childElements[i] = floatingPointValue;
            childElements[i] = this.dg + (floatingPointValue / (Math.pow(2,this.numberOfBytes) - 1))*(this.gg - this.dg);
        }
        Unit child = new Unit(childElements);



        return child;
    }

    @Override
    public Unit mutate(Unit unit, double vjerojatnostMutacije) {

        //prebaci parente u binarno
        int [][] unitBinaryParameters = new int[unit.getParams().length][this.numberOfBytes];
        for (int i = 0; i < unit.getParams().length; i++) {
            double valueInInterval = ((unit.getParams()[i] - this.dg) / (this.gg - this.dg))*(Math.pow(2, this.numberOfBytes) - 1);
            int roundValueInInterval = (int)(valueInInterval + 0.5);
            int maska = 1;
            int[] binaryParameter = new int[this.numberOfBytes];

            int remainder;
            double binary = 0;
            int iterator = 0;

            while(roundValueInInterval != 0) {
                remainder = roundValueInInterval%2;
                roundValueInInterval = roundValueInInterval/2;
                //binary = binary + (remainder*iterator);
                binaryParameter[iterator] = remainder;
                iterator++;
            }

            /*for (int j = 0; j < this.numberOfBytes; j++) {
                if ((roundValueInInterval & maska) != 0){
                    //postavi i-ti boolean na true
                    binaryParameter[j] = 1;
                }
                else{
                    binaryParameter[j] = 0;
                }
                maska = maska << 1;
            }*/
            unitBinaryParameters[i] = binaryParameter;
        }

        //TODO implementiraj mutiranje s binarnim prikazom
        //jednostavna mutacija
        double vjerojatnostDaCeBaremJedanBitBitiMutiran = 1 - Math.pow(1-vjerojatnostMutacije,this.numberOfBytes);
        double randomNumber = ThreadLocalRandom.current().nextDouble(0,1);
        if(randomNumber < vjerojatnostDaCeBaremJedanBitBitiMutiran){
            //mutiraj po jedan bit u svakoj varijabli
            for (int i = 0; i < unitBinaryParameters.length; i++) {
                int mjestoKojeCeSeMutirati = ThreadLocalRandom.current().nextInt(0,this.numberOfBytes);
                unitBinaryParameters[i][mjestoKojeCeSeMutirati] = Math.abs(unitBinaryParameters[i][mjestoKojeCeSeMutirati] - 1);
            }
        }

        //vrati binarno u Unit
        //za svaku varijablu jedinke
        double[] mutatedUnitElementa = new double[unit.getParams().length];
        for (int i = 0; i < unitBinaryParameters.length; i++) {
            //za svaki bit u toj varijabli
            double floatingPointValue = 0;
            for (int j = 0; j < unitBinaryParameters[i].length; j++) {
                floatingPointValue = floatingPointValue + unitBinaryParameters[i][j] * Math.pow(2,j);
            }
            //mutatedUnitElementa[i] = floatingPointValue;
            mutatedUnitElementa[i] = this.dg + (floatingPointValue / (Math.pow(2,this.numberOfBytes) - 1))*(this.gg - this.dg) ;
        }
        Unit mutatedUnit = new Unit(mutatedUnitElementa);

        return mutatedUnit;
    }
}
