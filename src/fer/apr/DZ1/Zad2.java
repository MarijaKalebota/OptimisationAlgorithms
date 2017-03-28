package fer.apr.DZ1;

import java.io.IOException;

/**
 * Created by Marija on 28.10.2016..
 */
public class Zad2 {
    public static void main(String [ ] args) throws IOException {
        double[][] elementsOfA = new double[][]{{3,9,6},{4,12,12},{1,-1,1}};
        double[][] elementsOfb = new  double[][]{{12},{12},{1}};
        Matrix A = new Matrix(3,3,elementsOfA);
        Matrix b = new Matrix(3,1,elementsOfb);

        double[][] permutationMatrixElements = Matrix.createIdentityMatrixElements(A.numberOfRows);
        Matrix permutationMatrix = new Matrix(3,3,permutationMatrixElements);
        System.out.println("Rjesavanje uz LU dekompoziciju\n\n");
        Matrix LUMatrix = Matrix.lupDecomposition(A, permutationMatrix, false);

        double[][] elementsOfA2 = new double[][]{{3,9,6},{4,12,12},{1,-1,1}};
        Matrix A2 = new Matrix(3,3,elementsOfA2);
        System.out.println("Rjesavanje uz LUP dekompoziciju\n");
        Matrix LUMatrix2 = Matrix.lupDecomposition(A2, permutationMatrix, true);

        System.out.println("LUP-dekomponirana matrica =");
        Matrix.printMatrix(LUMatrix2);

        Matrix.printMatrix(permutationMatrix);
        Matrix permutatedVectorb = Matrix.multiply(permutationMatrix,b);

        System.out.println("Provodimo supstituciju unaprijed\n");
        Matrix y = Matrix.forwardSubstitution(LUMatrix2, permutatedVectorb);
        System.out.println("Vektor y =");
        Matrix.printMatrix(y);


        System.out.println("Provodimo supstituciju unatrag\n");
        Matrix x = Matrix.backwardSupstitution(LUMatrix2, y);



        System.out.println("Rjesenje (x) =");
        Matrix.printMatrix(x);





    }
}
