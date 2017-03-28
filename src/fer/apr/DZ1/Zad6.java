package fer.apr.DZ1;

import java.io.IOException;

/**
 * Created by Marija on 28.10.2016..
 */
public class Zad6 {
    public static void main(String [ ] args) throws IOException {

        double[][] elementsOfA = new double[][]{{4000000000.0,1000000000.0,3000000000.0},{4,2,7},{0.0000000003,0.0000000005,0.0000000002}};
        double[][] elementsOfb = new  double[][]{{9000000000.0},{15},{0.0000000015}};
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
