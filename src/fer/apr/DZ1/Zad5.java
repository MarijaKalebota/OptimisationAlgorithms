package fer.apr.DZ1;

import java.io.IOException;

/**
 * Created by Marija on 28.10.2016..
 */
public class Zad5 {
    public static void main(String [ ] args) throws IOException {

        double[][] elementsOfA = new double[][]{{0,1,2},{2,0,3},{3,5,1}};
        double[][] elementsOfb = new  double[][]{{6},{9},{3}};
        Matrix A = new Matrix(3,3,elementsOfA);
        Matrix b = new Matrix(3,1,elementsOfb);

        double[][] permutationMatrixElements = Matrix.createIdentityMatrixElements(A.numberOfRows);
        Matrix permutationMatrix = new Matrix(3,3,permutationMatrixElements);


        if(Matrix.hasInverse(A)) {
            System.out.println(Matrix.hasInverse(A));

            System.out.println("Rjesavanje uz LUP dekompoziciju\n");
            Matrix LUMatrix2 = Matrix.lupDecomposition(A, permutationMatrix, true);

            Matrix.printMatrix(permutationMatrix);
            Matrix permutatedVectorb = Matrix.multiply(permutationMatrix, b);

            System.out.println("Provodimo supstituciju unaprijed\n");
            Matrix y2 = Matrix.forwardSubstitution(LUMatrix2, permutatedVectorb);
            System.out.println("Provodimo supstituciju unatrag\n");
            Matrix x2 = Matrix.backwardSupstitution(LUMatrix2, y2);

            System.out.println("Rjesenje (x) =");
            Matrix.printMatrix(x2);
        }


    }
}
