package fer.apr.DZ5;

import fer.apr.DZ1.Matrix;
import fer.apr.DZ2.Helpers.IFunction;
import fer.apr.DZ2.HookeJeeves;

import java.io.*;

/**
 * Created by Marija on 19.1.2017..
 */
public class RungeKutta {

    public static void provediRungeKutta(Matrix pocetnoStanjeX, Matrix A, Matrix B, double T, double tMax){

        double currentPointInTime = 0;

        Matrix xZaSljedecuIteraciju = HookeJeeves.copyPoint(pocetnoStanjeX);

        StringBuilder sb = new StringBuilder();

        while (currentPointInTime < tMax) {
            Matrix xUTrenutnojIteraciji = HookeJeeves.copyPoint(xZaSljedecuIteraciju);

            //Matrix m1Result = Matrix.add(Matrix.multiply(A,xUTrenutnojIteraciji),B);
            Matrix m1Result = Matrix.multiply(A,xUTrenutnojIteraciji);

            Matrix TKrozDvaPutaM1 = Matrix.scalarMultiply(m1Result, T/2);
            Matrix argumentZaFunkciju = Matrix.add(xUTrenutnojIteraciji, TKrozDvaPutaM1);
            //Matrix m2Result = Matrix.add(Matrix.multiply(A, argumentZaFunkciju),B);
            Matrix m2Result = Matrix.multiply(A, argumentZaFunkciju);


            Matrix TKrozDvaPutaM2 = Matrix.scalarMultiply(m2Result, T/2);
            Matrix argumentZaFunkciju2 = Matrix.add(xUTrenutnojIteraciji, TKrozDvaPutaM2);
            //Matrix m3Result = Matrix.add(Matrix.multiply(A, argumentZaFunkciju2),B);
            Matrix m3Result = Matrix.multiply(A, argumentZaFunkciju2);

            Matrix TPutaM3 = Matrix.scalarMultiply(m3Result, T);
            Matrix argumentZaFunkciju3 = Matrix.add(xUTrenutnojIteraciji, TPutaM3);
            //Matrix m4Result = Matrix.add(Matrix.multiply(A, argumentZaFunkciju3),B);
            Matrix m4Result = Matrix.multiply(A, argumentZaFunkciju3);

            Matrix dvaM2 = Matrix.scalarMultiply(m2Result, 2);
            Matrix dvaM3 = Matrix.scalarMultiply(m3Result, 2);
            Matrix m1PlusDvaM2 = Matrix.add(m1Result, dvaM2);
            Matrix m1PlusDvaM2PlusDvaM3 = Matrix.add(m1PlusDvaM2, dvaM3);
            Matrix m1PlusDvaM2PlusDvaM3PlusM4 = Matrix.add(m1PlusDvaM2PlusDvaM3, m4Result);

            xZaSljedecuIteraciju = Matrix.add(xUTrenutnojIteraciji, Matrix.scalarMultiply(m1PlusDvaM2PlusDvaM3PlusM4, T/6));
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
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("rezultatiRungeKutta.txt"), "UTF-8"));
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

}
