package fer.apr.DZ1;

import java.io.IOException;

/**
 * Created by Marija on 28.10.2016..
 */
public class Zad3 {
    public static void main(String [ ] args) throws IOException {

        double[][] elementsOfA = new double[][]{{1,2,3},{4,5,6},{7,8,9}};
        double[][] elementsOfb = new  double[][]{{12},{12},{1}};
        Matrix A = new Matrix(3,3,elementsOfA);
        Matrix b = new Matrix(3,1,elementsOfb);

        double[][] permutationMatrixElements = Matrix.createIdentityMatrixElements(A.numberOfRows);
        Matrix permutationMatrix = new Matrix(3,3,permutationMatrixElements);


        //if(Matrix.hasInverse(A)) {
            //System.out.println(Matrix.hasInverse(A));
            System.out.println("Rjesavanje uz LU dekompoziciju\n\n");
            Matrix LUMatrix = Matrix.lupDecomposition(A, permutationMatrix, false);

            System.out.println("Provodimo supstituciju unaprijed\n");
            Matrix y = Matrix.forwardSubstitution(LUMatrix, b);
            System.out.println("Provodimo supstituciju unatrag\n");
            Matrix x = Matrix.backwardSupstitution(LUMatrix, y);

            System.out.println("Rjesenje (x) =");
            Matrix.printMatrix(x);

            double[][] elementsOfA2 = new double[][]{{1,2,3},{4,5,6},{7,8,9}};
            Matrix A2 = new Matrix(3,3,elementsOfA2);
            System.out.println("Rjesavanje uz LUP dekompoziciju\n");
            Matrix LUMatrix2 = Matrix.lupDecomposition(A2, permutationMatrix, true);

            Matrix.printMatrix(permutationMatrix);
            Matrix permutatedVectorb = Matrix.multiply(permutationMatrix, b);

            System.out.println("Provodimo supstituciju unaprijed\n");
            Matrix y2 = Matrix.forwardSubstitution(LUMatrix2, permutatedVectorb);
            System.out.println("Provodimo supstituciju unatrag\n");
            Matrix x2 = Matrix.backwardSupstitution(LUMatrix2, y2);

            System.out.println("Rjesenje (x) =");
            Matrix.printMatrix(x2);
        //}


    }
}
