package fer.apr.DZ3;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.FunctionForLambda;
import fer.apr.DZ2.Helpers.IFunction;
import fer.apr.DZ2.HookeJeeves;
import fer.apr.DZ2.ZlatniRez2;

/**
 * Created by Marija on 7.12.2016..
 */
public class GradijentniSpust {

    /*public static Matrix provediGradijentniSpust(IFunction f,Matrix tocka){

        double[] vektorSmjera = new double[]{f.valueAtDerivativeByX1(tocka), f.valueAtDerivativeByX2(tocka)};
        F(x + lambda*v.s.)


    }*/

    public static Matrix provediGradijentniSpust(IFunction f, Matrix tocka, double epsilon, boolean provediZlatniRez, boolean print){
        if(print){
            System.out.println("Usao sam u gradijentni spust");
        }
        Matrix tockaUTrenutnojIteraciji = HookeJeeves.copyPoint(tocka);
        double euklidskaNormaVektoraGradijenta = Double.MAX_VALUE;
        int brojacZaProvjeruDivergencije = 0;
        int brojIzracunaGradijenta = 0;
        while (euklidskaNormaVektoraGradijenta > epsilon) {
            double valueAtDerivativeByX1 = f.valueAtDerivativeByX1(tockaUTrenutnojIteraciji);
            double valueAtDerivativeByX2 = f.valueAtDerivativeByX2(tockaUTrenutnojIteraciji);

            double[][] values = new double[1][2];
            values[0][0] = valueAtDerivativeByX1;
            values[0][1] = valueAtDerivativeByX2;
            brojIzracunaGradijenta++;

            Matrix vektorSmjera = new Matrix(1,2,values);
            if(print){
                System.out.println("Vektor smjera:");
                Matrix.printMatrix(vektorSmjera);
            }

            if (provediZlatniRez) {
                if(print){
                    System.out.println("Provodim zlatni rez");
                }
                FunctionForLambda fLambda = new FunctionForLambda(f, tockaUTrenutnojIteraciji, vektorSmjera);
                //double[] lambdaInterval = ZlatniRez2.provediZlatniRezSPocTockom(0, epsilon, fLambda, print);
                double[] lambdaInterval = ZlatniRez2.provediZlatniRezSPocTockom(0, epsilon, fLambda, false);
                double lambda = (lambdaInterval[0] + lambdaInterval[1])/2;
                Matrix newMatrix = Matrix.add(HookeJeeves.copyPoint(tockaUTrenutnojIteraciji),Matrix.scalarMultiply(vektorSmjera,lambda));
                tockaUTrenutnojIteraciji = newMatrix;
            }
            else{
                Matrix newMatrix = Matrix.subtract(tockaUTrenutnojIteraciji, vektorSmjera);
                tockaUTrenutnojIteraciji = newMatrix;
                if(print){
                    System.out.println("newMatrix = ");
                    Matrix.printMatrix(newMatrix);
                }
            }
            double staraEuklidskaNormaVektoraGradijenta = euklidskaNormaVektoraGradijenta;
            euklidskaNormaVektoraGradijenta = Math.sqrt(Math.pow(vektorSmjera.getElement(0,0),2) + Math.pow(vektorSmjera.getElement(0,1),2));
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
                    System.out.println("Sustav je poceo divergirati, prekidam.");
                }
                break;
            }

        }
        System.out.println("Broj izracuna funkcije: " + f.getNumberOfCalls());
        System.out.println("Broj izracuna gradijenta: " + brojIzracunaGradijenta);
        return tockaUTrenutnojIteraciji;

    }

}
