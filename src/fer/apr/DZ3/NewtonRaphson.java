package fer.apr.DZ3;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.FunctionForLambda;
import fer.apr.DZ2.Helpers.IFunction;
import fer.apr.DZ2.HookeJeeves;
import fer.apr.DZ2.ZlatniRez2;

/**
 * Created by Marija on 7.12.2016..
 */
public class NewtonRaphson {

    //double[] vektorSmjera = new double[]{f.valueAtDerivativeByX1(tocka), f.valueAtDerivativeByX2(tocka)};

    public static Matrix provediNewtonRaphson(IFunction f, Matrix tocka, double epsilon, boolean provediZlatniRez, boolean print){
        Matrix tockaUTrenutnojIteraciji = HookeJeeves.copyPoint(tocka);
        //double euklidskaNormaVektoraGradijenta = Double.MAX_VALUE;
        double euklidskaNormaVektoraGradijenta = 11.3;
        int brojacZaProvjeruDivergencije = 0;
        int brojIzracunaGradijenta = 0;
        int brojIzracunaHesseoveMatrice = 0;
        while (euklidskaNormaVektoraGradijenta > epsilon) {
            double valueAtDerivativeByX1 = f.valueAtDerivativeByX1(tockaUTrenutnojIteraciji);
            double valueAtDerivativeByX2 = f.valueAtDerivativeByX2(tockaUTrenutnojIteraciji);

            double[][] values = new double[1][2];
            values[0][0] = valueAtDerivativeByX1;
            values[0][1] = valueAtDerivativeByX2;
            brojIzracunaGradijenta++;

            Matrix pomak = new Matrix(1,2,values);

            double[][] hessianElements = new double[2][2];
            hessianElements[0][0] = f.valueAtDerivativeByX1ThenX1(tockaUTrenutnojIteraciji);
            hessianElements[0][1] = f.valueAtDerivativeByX1ThenX2(tockaUTrenutnojIteraciji);
            hessianElements[1][0] = f.valueAtDerivativeByX2ThenX1(tockaUTrenutnojIteraciji);
            hessianElements[1][1] = f.valueAtDerivativeByX2ThenX2(tockaUTrenutnojIteraciji);
            brojIzracunaHesseoveMatrice++;

            Matrix hessian = new Matrix(2,2,hessianElements);

            double[][] permutationMatrixElements = new double[][]{{1,0},{0,1}};
            Matrix permutationMatrix = new Matrix(2,2,permutationMatrixElements);
            Matrix newHessian = Matrix.lupDecomposition(hessian, permutationMatrix,true);
            //Matrix permutatedVektorSmjera = Matrix.multiply(permutationMatrix,pomak);
            Matrix permutatedVektorSmjera = Matrix.multiply(pomak, permutationMatrix);
            //vektor smjera mora bti okomit kako bi radio forward substitution, a trenutno je vodoravan
            double lijeviElementVektoraSmjera = permutatedVektorSmjera.getElements()[0][0];
            double desniElementVektoraSmjera = permutatedVektorSmjera.getElements()[0][1];
            double[][] elementiVertikalnogVektoraSmjera= new double[2][1];
            elementiVertikalnogVektoraSmjera[0][0] = lijeviElementVektoraSmjera;
            elementiVertikalnogVektoraSmjera[1][0] = desniElementVektoraSmjera;
            Matrix vertikalniVektorSmjera = new Matrix(2,1,elementiVertikalnogVektoraSmjera);
            //Matrix y = Matrix.forwardSubstitution(newHessian, permutatedVektorSmjera);
            Matrix y = Matrix.forwardSubstitution(newHessian, vertikalniVektorSmjera);
            Matrix deltaX = Matrix.backwardSupstitution(newHessian, y);
            //deltaX je ovdje isto vertikalni vektor
            double gornjiElementDeltaX = deltaX.getElements()[0][0];
            double donjiElementDeltaX = deltaX.getElements()[1][0];
            double [][] elementiVodoravnogDeltaX = new double[1][2];
            elementiVodoravnogDeltaX[0][0] = gornjiElementDeltaX;
            elementiVodoravnogDeltaX[0][1] = donjiElementDeltaX;
            Matrix vodoravniDeltaX = new Matrix(1,2,elementiVodoravnogDeltaX);
            if(provediZlatniRez){
                //FunctionForLambda fLambda = new FunctionForLambda(f, tockaUTrenutnojIteraciji, deltaX);
                FunctionForLambda fLambda = new FunctionForLambda(f, tockaUTrenutnojIteraciji, vodoravniDeltaX);
                double[] lambdaInterval = ZlatniRez2.provediZlatniRezSPocTockom(0, epsilon, fLambda, print);
                double lambda = (lambdaInterval[0] + lambdaInterval[1])/2;
                //Matrix newMatrix = Matrix.add(HookeJeeves.copyPoint(tockaUTrenutnojIteraciji),Matrix.scalarMultiply(pomak,lambda));
                //Matrix newMatrix = Matrix.subtract(HookeJeeves.copyPoint(tockaUTrenutnojIteraciji),Matrix.scalarMultiply(pomak,lambda));
                //Matrix newMatrix = Matrix.subtract(HookeJeeves.copyPoint(tockaUTrenutnojIteraciji),Matrix.scalarMultiply(vodoravniDeltaX,lambda));
                Matrix newMatrix = Matrix.add(HookeJeeves.copyPoint(tockaUTrenutnojIteraciji),Matrix.scalarMultiply(vodoravniDeltaX,lambda));
                tockaUTrenutnojIteraciji = newMatrix;
            }
            else{
                //TODO je li ovdje mozda trebalo ipak subtract?
                //Matrix newMatrix = Matrix.add(tockaUTrenutnojIteraciji, pomak);
                //Matrix newMatrix = Matrix.subtract(tockaUTrenutnojIteraciji, pomak);
                //Matrix newMatrix = Matrix.subtract(tockaUTrenutnojIteraciji, vodoravniDeltaX);
                Matrix newMatrix = Matrix.add(tockaUTrenutnojIteraciji, vodoravniDeltaX);

                tockaUTrenutnojIteraciji = newMatrix;
            }

            double staraEuklidskaNormaVektoraGradijenta = euklidskaNormaVektoraGradijenta;
            //euklidskaNormaVektoraGradijenta = Math.sqrt(Math.pow(pomak.getElement(0,0),2) + Math.pow(pomak.getElement(0,1),2));
            //euklidskaNormaVektoraGradijenta = Math.sqrt(Math.pow(permutatedVektorSmjera.getElement(0,0),2) + Math.pow(permutatedVektorSmjera.getElement(0,1),2));
            euklidskaNormaVektoraGradijenta = Math.sqrt(Math.pow(vodoravniDeltaX.getElement(0,0),2) + Math.pow(vodoravniDeltaX.getElement(0,1),2));

            if(print){
                System.out.println("Nova euklidska norma:" + euklidskaNormaVektoraGradijenta);
            }
            if(euklidskaNormaVektoraGradijenta == staraEuklidskaNormaVektoraGradijenta){
                brojacZaProvjeruDivergencije++;
            }
            else{
                brojacZaProvjeruDivergencije = 0;
            }
            if(brojacZaProvjeruDivergencije == 100){
                if(print){
                    //TODO PRETVORI U GLEDANJE JEL MANJA VRIJEDNOST FUNKCIJE, ILI DA JE RAZLIKA MANJA OD NEKOG EPSILONA
                    System.out.println("Sustav je poceo divergirati, prekidam.");
                }
                break;
            }

        }
        System.out.println("Broj izracuna funkcije: " + f.getNumberOfCalls());
        System.out.println("Broj izracuna gradijenta: " + brojIzracunaGradijenta);
        System.out.println("Broj izracuna Hesseove matrice: " + brojIzracunaHesseoveMatrice);
        return tockaUTrenutnojIteraciji;
    }
}
