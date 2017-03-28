package fer.apr.DZ4;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.IFunction;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Marija on 16.11.2016..
 */
public class GeneticAlgorithm {
    int populationSize = 50;
    double mutationProbability = 0.01;
    //IFunction f;
    Unit[] population;
    double[][] dataSet;
    Unit bestUnit;
    int numberOfIterations = 10000;
    double epsilon = 1E-6;
    Matrix pocetnaTocka;
    AbstractPrikazRjesenja vrstaPrikazaRjesenja;
    IFunction f;


    /**
     * Konstruktor
     *
     * @param populationSize
     *          velicina populacije - cijeli broj izmedu 20 i 100
     * @param mutationProbability
     *          decimalni broj izmedu 0 i 1 - vjerojatnost mutacije
     * @param dataSet
     *          polje primjera za ucenje
     * @param numberOfIterations
     *          broj iteracija - izmedu 10^3 i 10^7
     *
     */
    //VISE NE KORISTIMO, relikt NENR-a
    public GeneticAlgorithm(Integer populationSize, Double mutationProbability, /*IFunction f,*/ double [][] dataSet, Integer numberOfIterations){
        if(populationSize != null)
        {
            this.populationSize = populationSize;
        }
        if(mutationProbability != null) this.mutationProbability = mutationProbability;
        if(numberOfIterations != null) this.numberOfIterations = numberOfIterations;
        //this.f = f;
        this.dataSet = dataSet;

        Unit[] population = new Unit[populationSize];
        //mora postojat donja i gornja granica za elemente
        for (int i = 0; i < populationSize; i++) {
            double[] params = new double[5];
            for (int j = 0; j < 5; j++) {
                double randomNumber = ThreadLocalRandom.current().nextDouble(-4, 4);
                params[j] = randomNumber;
            }
            Unit newUnit = new Unit(params);
            newUnit.setBadness(calculateBadness(newUnit));
            newUnit.setIndexInPopulation(i);
            population[i] = newUnit;
        }
        this.population = population;
        this.bestUnit = population[findBestUnit(population)];
    }

    public GeneticAlgorithm(Integer populationSize, boolean binary, Integer mutationProbability, Integer maxIterations, Integer epsilon, Integer crossOperator){
        this.populationSize = populationSize;
        this.mutationProbability = mutationProbability;
        this.numberOfIterations = maxIterations;
        if(epsilon != null) this.epsilon = epsilon;
    }

    //OVO JE PRAVIIIII KONSTRUKTOR
    public GeneticAlgorithm(AbstractPrikazRjesenja vrstaPrikaza, Matrix tocka, Integer populationSize, double mutationProbability, Integer maxIterations, double epsilon, IFunction f){
        this.populationSize = populationSize;
        this.mutationProbability = mutationProbability;
        this.numberOfIterations = maxIterations;
        this.epsilon = epsilon;
        this.pocetnaTocka = tocka;
        this.vrstaPrikazaRjesenja = vrstaPrikaza;
        this.f = f;

        //TODO zelimo li populaciju Unita ili Matrixa?
        Unit[] population = new Unit[populationSize];
        //Matrix[] population = new Matrix[populationSize];
        //mora postojat donja i gornja granica za elemente
        for (int i = 0; i < populationSize; i++) {
            double[] params = new double[tocka.getElements()[0].length];
            for (int j = 0; j < tocka.getElements()[0].length; j++) {
                double randomNumber = ThreadLocalRandom.current().nextDouble(-50, 150);
                params[j] = randomNumber;
            }
            double[][] params2 = new double[1][params.length];
            params2[0] = params;
            Unit newUnit = new Unit(params);
            //Matrix newUnit = new Matrix(tocka.getRowsCount(), tocka.getColsCount(), params2);
            newUnit.setBadness(calculateBadness(newUnit));
            newUnit.setIndexInPopulation(i);
            population[i] = newUnit;
        }
        this.population = population;
        this.bestUnit = population[findBestUnit(population)];


    }

    //PRAVI ZA PROVODENJE GGA
    public Unit provediGenetskiAlgoritam3Generacijski(boolean elitism, Integer k){

        int iteracija = 0;

        while(iteracija < this.numberOfIterations) {

            int popunjenostSljedeceGeneracije = 0;
            Unit[] nextGeneration = new Unit[this.populationSize];

            if(elitism){
                //uzmi najboljeg i baci ga u sljedecu generaciju
                nextGeneration[popunjenostSljedeceGeneracije] = this.bestUnit;
                popunjenostSljedeceGeneracije++;
            }

            while(popunjenostSljedeceGeneracije < this.populationSize){
                //roulette wheelon izaberi k jedinki za k-turnir
                //uzmi dvi najbolje
                //Unit[] parents = rouletteWheel(k, this.population);
                Unit[] kTournament = kTournamentResult(k, this.population);
                //križaj ih
                //Unit child = cross(kTournament[0], kTournament[1]);
                Unit child = this.vrstaPrikazaRjesenja.cross(kTournament[0], kTournament[1]);
                //mutiraj dijete
                //child.mutate();
                Unit mutatedChild = this.vrstaPrikazaRjesenja.mutate(child, mutationProbability);
                //TODO popravi ovo sa setBadness?
                //child.setBadness(calculateBadness(child));
                mutatedChild.setBadness(calculateBadness(mutatedChild));
                //stavi ga u sljedeću generaciju
                nextGeneration[popunjenostSljedeceGeneracije] = mutatedChild;
                popunjenostSljedeceGeneracije++;
            }

            //this.populacija = novaPopulacija
            this.population = nextGeneration;
            //this.bestUnit = findBestUnit(nextGeneration);
            //this.bestUnit = nextGeneration[findBestUnit(nextGeneration)];
            /*System.out.println("Populacija:");
            for (int i = 0; i < population.length; i++) {
                //population[i].printUnit();
                nextGeneration[i].printUnit();
            }*/
            this.bestUnit = this.population[findBestUnit(this.population)];
            System.out.print("GGA, iteracija " + iteracija + ", najbolji: "); //+ this.bestUnit.getParams().toString());
            double[] bestUnitParams = this.bestUnit.getParams();
            for (int i = 0; i < bestUnitParams.length; i++) {
                System.out.print(bestUnitParams[i] + " ");
            }
            //System.out.println("Badness: " + this.bestUnit.badness);
            System.out.print("Badness: " + this.bestUnit.badness);
            double[][] matrixElements = new double[1][this.bestUnit.getParams().length];
            matrixElements[0] = this.bestUnit.getParams().clone();
            Matrix bestUnitInMatrixForm = new Matrix(1,this.bestUnit.getParams().length,matrixElements);
            System.out.println(" Vr. f-je cilja: " + this.f.valueAt(bestUnitInMatrixForm));
            iteracija++;

        }
        System.out.print("GGA, zadnja iteracija, najbolji: ");// + this.bestUnit.getParams().toString());
        double[] bestUnitParams = this.bestUnit.getParams();
        for (int i = 0; i < bestUnitParams.length; i++) {
            System.out.print(bestUnitParams[i] + " ");
        }
        //System.out.println("Badness: " + this.bestUnit.badness);
        System.out.print("Badness: " + this.bestUnit.badness);
        double[][] matrixElements = new double[1][this.bestUnit.getParams().length];
        matrixElements[0] = this.bestUnit.getParams().clone();
        Matrix bestUnitInMatrixForm = new Matrix(1,this.bestUnit.getParams().length,matrixElements);
        System.out.println(" Vr. f-je cilja: " + this.f.valueAt(bestUnitInMatrixForm));
        return this.bestUnit;

    }

    //PRAVI ZA PROVODENJE EGA
    public Unit provediGenetskiAlgoritam3Eliminacijski(boolean elitism, Integer k){
        int iteracija = 0;

        while(iteracija < this.numberOfIterations) {

            //napravi k-turnir, dobij dva najbolja i najgoreg
            Unit[] kTournament = kTournamentResult(k, this.population);
            //križaj dva najbolja
            //Unit child = cross(kTournament[0], kTournament[1]);
            Unit child = this.vrstaPrikazaRjesenja.cross(kTournament[0], kTournament[1]);
            //child.mutate();
            Unit mutatedChild = this.vrstaPrikazaRjesenja.mutate(child, mutationProbability);
            //TODO POPRAVI
            //child.setBadness(calculateBadness(child));
            mutatedChild.setBadness(calculateBadness(mutatedChild));
            //child.setIndexInPopulation(kTournament[2].indexInPopulation);
            mutatedChild.setIndexInPopulation(kTournament[2].indexInPopulation);
            //populacija[najgori.indexInPopulation] = dijete
            this.population[kTournament[2].indexInPopulation] = mutatedChild;
            //this.bestUnit = findBestUnit(this.population);
            this.bestUnit = this.population[findBestUnit(this.population)];
            if(iteracija%100 == 0) {
                //System.out.println("EGA, iteracija " + iteracija + ", najbolji: " + this.bestUnit.getParams());
                System.out.print("EGA, iteracija " + iteracija + ", najbolji: "); //+ this.bestUnit.getParams().toString());
                double[] bestUnitParams = this.bestUnit.getParams();
                for (int i = 0; i < bestUnitParams.length; i++) {
                    System.out.print(bestUnitParams[i] + " ");
                }
                //System.out.println("Badness: " + this.bestUnit.badness);
                System.out.print("Badness: " + this.bestUnit.badness);
                double[][] matrixElements = new double[1][this.bestUnit.getParams().length];
                matrixElements[0] = this.bestUnit.getParams().clone();
                Matrix bestUnitInMatrixForm = new Matrix(1,this.bestUnit.getParams().length,matrixElements);
                System.out.println(" Vr. f-je cilja: " + this.f.valueAt(bestUnitInMatrixForm));
            }
            iteracija++;

        }
        System.out.println("EGA, zadnja iteracija, najbolji: ");// + this.bestUnit.getParams());
        double[] bestUnitParams = this.bestUnit.getParams();
        for (int i = 0; i < bestUnitParams.length; i++) {
            System.out.print(bestUnitParams[i] + " ");
        }
        System.out.print("Badness: " + this.bestUnit.badness);
        double[][] matrixElements = new double[1][this.bestUnit.getParams().length];
        matrixElements[0] = this.bestUnit.getParams().clone();
        Matrix bestUnitInMatrixForm = new Matrix(1,this.bestUnit.getParams().length,matrixElements);
        System.out.println(" Vr. f-je cilja: " + this.f.valueAt(bestUnitInMatrixForm));
        return this.bestUnit;
    }


    /**
     * Metoda služi za generiranje izlaza generacijskog algoritma (polja brojeva beta0-beta4)
     *
     * @param elitism
     *            zastavica kojom biramo zelimo li elitizam ili ne
     * @param k
     *            broj elemenata koji ce ici u k-turnirsku selekciju
     *
     */
    //VISE NE KORISTIMO, relikt NENR-a
    public Unit generateResultGGA(boolean elitism, Integer k){

        if(k == null) k = 3;

        //Unit trenutnoNajbolji;
        int iteracija = 0;

        while(iteracija < this.numberOfIterations) {

            int popunjenostSljedeceGeneracije = 0;
            Unit[] nextGeneration = new Unit[this.populationSize];

            if(elitism){
                //uzmi najboljeg i baci ga u sljedecu generaciju
                nextGeneration[popunjenostSljedeceGeneracije] = this.bestUnit;
                popunjenostSljedeceGeneracije++;
            }

            while(popunjenostSljedeceGeneracije < this.populationSize){
                //roulette wheelon izaberi k jedinki za k-turnir
                //uzmi dvi najbolje
                //Unit[] parents = rouletteWheel(k, this.population);
                Unit[] kTournament = kTournamentResult(k, this.population);
                //križaj ih
                Unit child = cross(kTournament[0], kTournament[1]);
                //mutiraj dijete
                child.mutate();
                child.setBadness(calculateBadness(child));
                //stavi ga u sljedeću generaciju
                nextGeneration[popunjenostSljedeceGeneracije] = child;
                popunjenostSljedeceGeneracije++;
            }

            //this.populacija = novaPopulacija
            this.population = nextGeneration;
            //this.bestUnit = findBestUnit(nextGeneration);
            //this.bestUnit = nextGeneration[findBestUnit(nextGeneration)];
            /*System.out.println("Populacija:");
            for (int i = 0; i < population.length; i++) {
                //population[i].printUnit();
                nextGeneration[i].printUnit();
            }*/
            this.bestUnit = this.population[findBestUnit(this.population)];
            System.out.print("GGA, iteracija " + iteracija + ", najbolji: "); //+ this.bestUnit.getParams().toString());
            double[] bestUnitParams = this.bestUnit.getParams();
            for (int i = 0; i < bestUnitParams.length; i++) {
                System.out.print(bestUnitParams[i] + " ");
            }
            System.out.println("Badness: " + this.bestUnit.badness);
            iteracija++;

        }
        System.out.print("GGA, zadnja iteracija, najbolji: ");// + this.bestUnit.getParams().toString());
        double[] bestUnitParams = this.bestUnit.getParams();
        for (int i = 0; i < bestUnitParams.length; i++) {
            System.out.print(bestUnitParams[i] + " ");
        }
        System.out.println("Badness: " + this.bestUnit.badness);
        return this.bestUnit;

    }

    private Unit[] kTournamentResult(Integer k, Unit[] population) {
        Unit[] values = new Unit[k];
        for(int i = 0; i < k; i++){
            //values[i] = rouletteWheel(population);
            values[i] = population[ThreadLocalRandom.current().nextInt(0, populationSize)];
        }

        //Arrays.sort(values, values[]);
        //Collections.sort(values, (Unit value1, Unit value2) -> (value1.getBadness() - value2.getBadness());

        //Unit[] parents = findTwoBestUnits(values);
        int bestIndex = findBestUnit(values);
        //Unit bestParent = values[findBestUnit(values)];
        Unit bestParent = values[bestIndex];
        Unit[] values2 = new Unit[k-1];
        int iterator = 0;
        for (int i = 0; i < values.length; i++) {
            if(i!=bestIndex){
                values2[iterator] = values[i];
                iterator++;
            }
        }

        Unit secondBestParent = values2[findBestUnit(values2)];
        Unit worst = values[findWorstUnit(values)];
        Unit[] returnValues = new Unit[k];
        returnValues[0] = bestParent;
        returnValues[1] = secondBestParent;
        returnValues[2] = worst;
        return returnValues;
    }

    //VISE NE KORISTIMO, jer ne koristin nigdi roulette wheel
    private Unit rouletteWheel(Unit[] population) {
        double sum = 0;
        for(int i = 0; i < populationSize; i++){
            sum += (1.0 / population[i].getBadness());
        }

        double randomNumber = ThreadLocalRandom.current().nextDouble(0, sum);

        double sum2 = 0;
        int indexOfFoundElement = 0;

        for(int i = 0; i < populationSize; i++){
            sum2+=population[i].getBadness();
            if(randomNumber < sum2){
                indexOfFoundElement = i;
                break;
            }

        }

        return population[indexOfFoundElement];
    }


    private /*Unit*/ int findWorstUnit(Unit[] values) {
        double maxBadness = 0;
        int indexOfWorst = 0;
        for(int i = 0; i < values.length; i++){
            if(values[i].getBadness() > maxBadness){
                maxBadness = values[i].getBadness();
                indexOfWorst = i;
            }
        }
        //return values[indexOfWorst];
        return indexOfWorst;
    }

    private /*Unit*/ int findBestUnit(Unit[] values) {
        /*System.out.println("Length(values) = " + values.length);
        for (int i = 0; i < values.length; i++) {
            System.out.print(i + " ");
            values[i].printUnit();

        }*/
        double minBadness = Double.MAX_VALUE;
        int indexOfBest = 0;
        for(int i = 0; i < values.length; i++){
            if(values[i].getBadness() < minBadness){
                minBadness = values[i].getBadness();
                indexOfBest = i;
            }
        }
        //return values[indexOfBest];
        return indexOfBest;
    }

    public Unit generateResultEGA(Integer k){
        if(k == null) k = 3;

        //Unit trenutnoNajbolji;
        int iteracija = 0;

        while(iteracija < this.numberOfIterations) {

            //napravi k-turnir, dobij dva najbolja i najgoreg
            Unit[] kTournament = kTournamentResult(k, this.population);
            //križaj dva najbolja
            Unit child = cross(kTournament[0], kTournament[1]);
            child.mutate();
            child.setBadness(calculateBadness(child));
            child.setIndexInPopulation(kTournament[2].indexInPopulation);
            //populacija[najgori.indexInPopulation] = dijete
            this.population[kTournament[2].indexInPopulation] = child;
            //this.bestUnit = findBestUnit(this.population);
            this.bestUnit = this.population[findBestUnit(this.population)];
            if(iteracija%100 == 0) {
                //System.out.println("EGA, iteracija " + iteracija + ", najbolji: " + this.bestUnit.getParams());
                System.out.print("EGA, iteracija " + iteracija + ", najbolji: "); //+ this.bestUnit.getParams().toString());
                double[] bestUnitParams = this.bestUnit.getParams();
                for (int i = 0; i < bestUnitParams.length; i++) {
                    System.out.print(bestUnitParams[i] + " ");
                }
                System.out.println("Badness: " + this.bestUnit.badness);
            }
            iteracija++;

        }
        System.out.println("EGA, zadnja iteracija, najbolji: ");// + this.bestUnit.getParams());
        double[] bestUnitParams = this.bestUnit.getParams();
        for (int i = 0; i < bestUnitParams.length; i++) {
            System.out.print(bestUnitParams[i] + " ");
        }
        System.out.println();
        return this.bestUnit;
    }

    //VISE NE KORISTIMO, cross je sad u AbstractPrikazRjesenja
    public Unit cross(Unit unit1, Unit unit2) {
        int crossPoint = ThreadLocalRandom.current().nextInt(0, 6);
        int firstOrSecond = ThreadLocalRandom.current().nextInt(0,2);
        Unit[] units = new Unit[2];
        units[0] = unit1;
        units[1] = unit2;

        double[] resultParams = new double[5];
        for(int i = 0; i < crossPoint; i++){
            resultParams[i] = units[firstOrSecond].params[i];
        }
        for(int i = crossPoint; i < 5; i++){
            resultParams[i] = (unit1.params[i] + unit2.params[i]) / 2.0;
        }
        Unit result = new Unit(resultParams);
        result.setBadness(calculateBadness(result));
        return result;
    }

    private double calculateBadness(Unit unit) {

        /*double differenceSquared[] = new double[this.dataSet.length];
        double differenceSquaredSummed = 0;
        //prodi kroz sve ulazne primjere
        for(int i = 0; i < this.dataSet.length; i++){
            //svaki ULAZ od njih povuci kroz treutnu jedinku, dobij izlaz jedinke
            //usporedi izlaz jedinke sa stvarnim izlazom:
            //to "usporedi" znači (a - b)^2

            double a = this.dataSet[i][2];
            //double b = this.f.valueAt(dataSet[i][0]);
            double b = function(unit.getParams(), this.dataSet[i][0], this.dataSet[i][1]);
            differenceSquared[i] = Math.pow((a - b),2);
            //posumiraj te kvadrate razlike
            differenceSquaredSummed += Math.pow((a - b),2);
        }
        //podijeli sumu kvadrata razlike sa brojem primjera
        //upravo si dobio MEAN SQUARED ERROR ZA TU JEDINKU
        double returnValue = differenceSquaredSummed / this.dataSet.length;*/
        double[][] matrixElements = new double[1][unit.getParams().length];
        matrixElements[0] = unit.getParams().clone();
        Matrix newMatrix = new Matrix(1,unit.getParams().length,matrixElements);
        double returnValue = this.f.valueAt(newMatrix);
        return returnValue;


    }

    //VISE NE KORISTIMO, funkcija se sad prima kroz konstruktor
    private double function(double[] betas, double x, double y){

        return Math.sin(betas[0] - betas[1]*x) + betas[2]*Math.cos(x*(betas[3] + y))*(1/1+Math.pow(Math.E,Math.pow(x - betas[4], 2)));

    }
}
