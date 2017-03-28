package fer.apr.DZ5;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.HookeJeeves;

import java.io.*;

/**
 * Created by Marija on 19.1.2017..
 */
public class TrapezniPostupak {

    public static void provediTrapezniPostupak(Matrix pocetnoStanjeX, Matrix A, Matrix B, double T, double tMax){

        double currentPointInTime = 0;

        Matrix xZaSljedecuIteraciju = HookeJeeves.copyPoint(pocetnoStanjeX);

        StringBuilder sb = new StringBuilder();

        while(currentPointInTime < tMax){

            Matrix xUTrenutnojIteraciji = HookeJeeves.copyPoint(xZaSljedecuIteraciju);

            double[][] jedinicnaMatricaElements = Matrix.createIdentityMatrixElements(A.getRowsCount());//) stvoi jedinicnu matricu;
            Matrix jedinicnaMatrica = new Matrix(A.getRowsCount(), A.getColsCount(), jedinicnaMatricaElements);
            Matrix nepromijenjenaJedinicnaMatrica = HookeJeeves.copyPoint(jedinicnaMatrica);
            Matrix matricaZaInvertirati = Matrix.subtract(jedinicnaMatrica, Matrix.scalarMultiply(A,T/2));
            Matrix invertiranaMatrica = Matrix.invert(matricaZaInvertirati);

            Matrix matricaZaMnozenje = Matrix.add(nepromijenjenaJedinicnaMatrica,  Matrix.scalarMultiply(A,T/2));

            xZaSljedecuIteraciju = Matrix.multiply(Matrix.multiply(invertiranaMatrica, matricaZaMnozenje), xUTrenutnojIteraciji);

            //sb.append("[");
            for (int i = 0; i < xZaSljedecuIteraciju.getRowsCount(); i++) {
                //sb.append("[");
                for (int j = 0; j < xZaSljedecuIteraciju.getColsCount(); j++) {
                    sb.append(xZaSljedecuIteraciju.getElements()[i][j]);
                    sb.append(" ");
                }
                //sb.append("]");
            }
            //sb.append("]\n");
            sb.append("\n");

            currentPointInTime = currentPointInTime + T;
        }

        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("rezultatiTrapezniPostupak.txt"), "UTF-8"));
            writer.write(sb.toString());
            writer.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }


    public static void provjeriUvjetStabilnosti(Matrix lambda, double T) {

        double[][] jedinicnaMatricaElements = Matrix.createIdentityMatrixElements(lambda.getRowsCount());//) stvoi jedinicnu matricu;
        Matrix jedinicnaMatrica = new Matrix(lambda.getRowsCount(), lambda.getColsCount(), jedinicnaMatricaElements);

        Matrix jedinicnaMinusLambdaTKrozDva = Matrix.subtract(jedinicnaMatrica, Matrix.scalarMultiply(lambda, T/2));

        Matrix invertiranaMatrica = Matrix.invert(jedinicnaMinusLambdaTKrozDva);

        Matrix drugiFaktor = Matrix.add(jedinicnaMatrica, Matrix.scalarMultiply(lambda,T/2));

        Matrix umnozak = Matrix.multiply(invertiranaMatrica, drugiFaktor);

        Matrix.printMatrix(umnozak);


    }
}
