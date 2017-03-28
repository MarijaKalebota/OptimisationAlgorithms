package fer.apr.DZ1;

import java.io.IOException;

/**
 * Created by Marija on 28.10.2016..
 */
public class Zad4 {
    public static void main(String [ ] args) throws IOException {

        double[][] elementsOfA = new double[][]{{0.000001,3000000,2000000},{1000000,2000000,3000000},{2000000,1000000,2000000}};
        double[][] elementsOfb = new  double[][]{{12000000.000001},{14000000},{10000000}};
        Matrix A = new Matrix(3,3,elementsOfA);
        Matrix b = new Matrix(3,1,elementsOfb);

        double[][] permutationMatrixElements = Matrix.createIdentityMatrixElements(A.numberOfRows);
        Matrix permutationMatrix = new Matrix(3,3,permutationMatrixElements);


        if(Matrix.hasInverse(A)) {
            System.out.println(Matrix.hasInverse(A));
            System.out.println("Rjesavanje uz LU dekompoziciju\n\n");
            Matrix LUMatrix = Matrix.lupDecomposition(A, permutationMatrix, false);

            System.out.println("Provodimo supstituciju unaprijed\n");
            Matrix y = Matrix.forwardSubstitution(LUMatrix, b);
            System.out.println("Provodimo supstituciju unatrag\n");
            Matrix x = Matrix.backwardSupstitution(LUMatrix, y);

            System.out.println("Rjesenje (x) =");
            Matrix.printMatrix(x);

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
