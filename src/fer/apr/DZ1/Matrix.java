package fer.apr.DZ1;

import fer.apr.DZ2.HookeJeeves;

import java.io.*;
import java.util.*;

import java.util.Arrays;

/**
 * Created by Marija on 22.10.2016..
 */
public class Matrix{
    private static double EPSILON = 1E-6;
    int numberOfRows;
    int numberOfColumns;
    double[][] elements;


    public double[][] getElements(){
        return this.elements;
    }


    public Matrix(int rowNumber, int columnNumber, double[][] elements){

        this.numberOfRows = rowNumber;
        this.numberOfColumns = columnNumber;
        this.elements = elements;

    }
    public static void printMatrix(Matrix a){
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for(int i = 0; i < a.numberOfRows; i ++){
            sb.append("[ ");
            for(int j = 0; j < a.numberOfColumns; j++){
                sb.append(a.elements[i][j]);
                sb.append("\t");
            }
            sb.append("]\n");
        }
        sb.append("]\n");

        System.out.println(sb.toString());

    }

    //pridruživanje
    public Matrix makeEqualTo(Matrix B){
        this.numberOfColumns = B.numberOfColumns;
        this.numberOfRows = B.numberOfRows;
        this.elements = B.elements;
        return B;
    }



    public void readFromTxt(String pathToFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(pathToFile));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            /*//String[] matrixRow = new String[];
            String[] matrixRowStringArray = line.trim().split("\\s+");
            double[] rowElements = new double[matrixRowStringArray.length];
            for(int i = 0; i < matrixRowStringArray.length; i++){
                rowElements[i] = Double.parseDouble(matrixRowStringArray[i]);
            }*/
            List<double[]> elementsArrayList = new ArrayList<double[]>();
            while (line != null) {
                //String[] matrixRow = new String[];
                String[] matrixRowStringArray = line.trim().split("\\s+");
                double[] rowElements = new double[matrixRowStringArray.length];
                for(int i = 0; i < matrixRowStringArray.length; i++){
                    rowElements[i] = Double.parseDouble(matrixRowStringArray[i]);
                }
                //imam redak [[el][el][el][el][el]]
                //double[][] arr = Arrays.copyOf(this.elements, this.elements.length + 1);
                //arr[this.elements.length] = rowElements;
                //this.elements = arr;

                elementsArrayList.add(rowElements);


                line = br.readLine();
            }

            elementsArrayList.toArray(this.elements);

        } finally {
            br.close();
        }
    }

    public void writeToTxt(String pathToFile) throws IOException{
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < numberOfRows; i++){
            for(int j = 0; j < numberOfColumns; j++){
                sb.append(this.elements[i][j]);
                sb.append("\t");
            }
            sb.append("\n");

        }
        //String everything = sb.toString();

        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("matrix.out"), "UTF-8"));
        writer.write(sb.toString());
        writer.close();

    }

    public void writeToStdout(){
        for(int i = 0; i < numberOfRows; i++){
            for(int j = 0; j < numberOfColumns; j++){
                System.out.print(this.elements[i][j]);
                System.out.print("\t");
            }
            System.out.print("\n");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Matrix other = (Matrix) obj;
        if (this.numberOfColumns != other.numberOfColumns)
            return false;
        if (!Arrays.deepEquals(elements, other.elements))
            return false;
        if (this.numberOfRows != other.numberOfRows)
            return false;
        return true;
    }


    public double getElement(int row, int column) {
        if (row >= this.numberOfRows || column >= this.numberOfColumns)
            throw new IndexOutOfBoundsException("Uneseni indeks je izvan opsega matrice.");
        if (row < 0 || column < 0)
            throw new IndexOutOfBoundsException("Indeks mora biti pozitivan broj.");
        return this.elements[row][column];
    }


    public Matrix setElement(int row, int column, double value) {
        if (row >= this.numberOfRows || column >= this.numberOfColumns)
            throw new IndexOutOfBoundsException("Uneseni indeks je izvan opsega matrice.");
        if (row < 0 || column < 0)
            throw new IndexOutOfBoundsException("Indeks mora biti pozitivan broj.");
        this.elements[row][column] = value;
        return this;
    }


    public int getRowsCount() {
        return this.numberOfRows;
    }


    public int getColsCount() {
        return this.numberOfColumns;
    }

    public static Matrix add(Matrix a, Matrix b){
        if(a.numberOfColumns != b.numberOfColumns || a.numberOfRows != b.numberOfRows){
            System.out.println("Matrice nisu jednakih dimentija i ne mogu se zbrojiti.");
            return null;
        }

        double [][] resultElements = new double[a.numberOfRows][a.numberOfColumns];
        for(int i = 0; i < a.numberOfRows; i++){
            for(int j = 0; j < a.numberOfColumns; j++){
                resultElements[i][j] = a.elements[i][j] + b.elements[i][j];
            }
        }
        Matrix resultMatrix = new Matrix(a.numberOfRows, a.numberOfColumns, resultElements);
        return resultMatrix;

    }

    public static Matrix subtract(Matrix a, Matrix b){
        if(a.numberOfColumns != b.numberOfColumns || a.numberOfRows != b.numberOfRows){
            System.out.println("Matrice nisu jednakih dimentija i oduzimanje se ne moze provesti.");
            return null;
        }

        double [][] resultElements = new double[a.numberOfRows][a.numberOfColumns];
        for(int i = 0; i < a.numberOfRows; i++){
            for(int j = 0; j < a.numberOfColumns; j++){
                resultElements[i][j] = a.elements[i][j] - b.elements[i][j];
            }
        }
        Matrix resultMatrix = new Matrix(a.numberOfRows, a.numberOfColumns, resultElements);
        return resultMatrix;
    }

    private Matrix plusEquals(Matrix matrix) { //add
        //if (this.getColsCount() != matrix.getColsCount() || this.getRowsCount() != matrix.getRowsCount())
            //throw new IncompatibleOperandException("Matrice su različitih dimenzija.");
        for (int i = this.getRowsCount() - 1; i >= 0; --i)
            for (int j = this.getColsCount() - 1; j >= 0; --j)
                this.setElement(i, j, this.getElement(i, j) + matrix.getElement(i, j));
        return this;
    }

    private Matrix minusEquals(Matrix matrix) { //subtract
        //if (this.getColsCount() != matrix.getColsCount() || this.getRowsCount() != matrix.getRowsCount())
            //throw new IncompatibleOperandException("Matrice su različitih dimenzija.");
        for (int i = this.getRowsCount() - 1; i >= 0; --i)
            for (int j = this.getColsCount() - 1; j >= 0; --j)
                this.setElement(i, j, this.getElement(i, j) - matrix.getElement(i, j));
        return this;
    }

    public Matrix multiply(Matrix matrix) {
        //if (this.getColsCount() != matrix.getRowsCount())
            //throw new IncompatibleOperandException("Matrice su različitih dimenzija.");
        double[][] elements = new double[this.getRowsCount()][matrix.getColsCount()];
        for (int i = this.getRowsCount() - 1; i >= 0; --i)
            for (int j = matrix.getColsCount() - 1; j >= 0; --j)
                for (int k = matrix.getRowsCount() - 1; k >= 0; --k)
                    elements[i][j] += this.getElement(i, k) * matrix.getElement(k, j);
        //return new Matrix(this.getRowsCount(), matrix.getColsCount(), elements, false);
        return new Matrix(this.getRowsCount(), matrix.getColsCount(), elements);
    }

    public static Matrix multiply(Matrix a, Matrix b){
        if(a.numberOfColumns != b.numberOfRows){
            System.out.println("Matrice nisu kompatibilne za mnozenje.");
            return null;
        }

        double [][] resultElements = new double[a.numberOfRows][b.numberOfColumns];
        for(int i = 0; i < a.numberOfRows; i++){
            for(int j = 0; j < b.numberOfColumns; j++){
                double resultElement = 0;
                for(int k = 0; k < a.numberOfColumns; k++){
                    resultElement = resultElement + a.elements[i][k]*b.elements[k][j];
                }
                resultElements[i][j] = resultElement;
            }
        }
        Matrix resultMatrix = new Matrix(a.numberOfRows, b.numberOfColumns, resultElements);
        return resultMatrix;
    }


    public static Matrix scalarMultiply(Matrix matrix, double scalar) {
        double[][] resultElements = new double[matrix.numberOfRows][matrix.numberOfColumns];
        for (int i = 0; i < matrix.getRowsCount(); i++)
            for (int j = 0; j <matrix.getColsCount(); j++)
                resultElements[i][j] = matrix.elements[i][j] * scalar;

        Matrix resultMatrix = new Matrix(matrix.numberOfRows, matrix.numberOfColumns, resultElements);
        return resultMatrix;
    }

    private static double sanityCheck(double d) {
        if((Math.abs(Math.round(d) - d) < EPSILON)&&(Math.abs(Math.round(d) - d) != 0) ){
            System.out.println("Pojavila se vrijednost " + d + " koja ima decimalno odstupanje od cijelog broja manje od epsilon = " + EPSILON + ". Postavljam na " + Math.round(d));
            return Math.round(d);
        }
        return d;
    }

    //region old
    /*public static Matrix forwardSubstitution1(Matrix a, Matrix b) {
        for (int i = 0, rows = a.getRowsCount(); i < rows - 1; i++)
            for (int j = i + 1; j < rows; j++)
                b.setElement(j, 0, sanityCheck(b.getElement(j, 0) - a.getElement(j, i) * b.getElement(i, 0)));
        return b;
    }*/
    //endregion


    public static Matrix forwardSubstitution(Matrix a, Matrix b){
        if(b.numberOfRows!=a.numberOfRows){
            System.out.println("Vektor b nije jednake dimenzije kao matrica, nije moguce provesti supstituciju unaprijed.");
        }
        Matrix resultB = new Matrix(b.numberOfRows, b.numberOfColumns, b.elements.clone());
        //Matrix resultB = b.clone();
        double[][] resultElements = new double[b.numberOfRows][1];
        for (int i = 0; i < a.numberOfRows - 1; i++)
            for (int j = i + 1; j < a.numberOfRows; j++)
                //treba li bas raditi sanity check pri svakom oduzimanju?
                resultB.elements[j][0] = sanityCheck(resultB.elements[j][0] - a.elements[j][i]*resultB.elements[i][0]);
        return resultB;
    }

    /*public static Matrix backwardSupstitution1(Matrix a, Matrix y) {
        for (int i = y.getRowsCount() - 1; i >= 0; i--) {
            if (sanityCheck(a.getElement(i, i)) == 0)
                throw new IllegalArgumentException("Gornja trokutasta matrica na dijagonali sadrži nulu.");
            y.setElement(i, 0, sanityCheck(y.getElement(i, 0) / a.getElement(i, i)));
            for (int j = 0; j < i; j++)
                y.setElement(j, 0, sanityCheck(y.getElement(j, 0) - a.getElement(j, i) * y.getElement(i, 0)));
        }
        return y;
    }*/

    public static Matrix backwardSupstitution(Matrix a, Matrix vector) {
        if(vector.numberOfRows!=a.numberOfRows){
            System.out.println("Vektor y nije jednake dimenzije kao matrica, nije moguce provesti supstituciju unatrag.");
        }
        for(int i=a.numberOfRows-1; i>=0; i--) {
            if(Math.abs(a.getElement(i, i))<EPSILON) {
                System.out.println("Gornja trokutasta matrica na dijagonali sadrzi nulu. Nije moguce provesti supstituciju unatrag");
                return null;
            }
            vector.setElement(i, 0, sanityCheck(vector.getElement(i, 0)/a.getElement(i,i)));
            for(int j=0; j<i; j++) {
                vector.setElement(j, 0, sanityCheck(vector.getElement(j, 0)-a.getElement(j, i)*vector.getElement(i, 0)));
            }
        }
        //return new Matrica(1, vector.colSize(), vector.getElements());
        return vector;
    }

    /*public static Matrix backwardSupstitution( Matrix a, Matrix y){
        za i = n do 1
        b[i] /= A[i,i];
        za j = 1 do i-1
        b[j] -= A[j,i] * b[i];


    }*/

    /*public static Matrix LUDecomposition(){

    }*/

    private static void swapRowsOfMatrix(Matrix matrix, int firstRow, int secondRow) {
        double[] tmp = matrix.elements[firstRow];
        matrix.elements[firstRow] = matrix.elements[secondRow];
        matrix.elements[secondRow] = tmp;
    }

    public static Matrix lupDecomposition(Matrix a, Matrix permutationMatrix, boolean lup) {
        if (a.numberOfRows != a.numberOfColumns)
            throw new IllegalArgumentException("Matrica mora biti kvadratna.");
        double[][] identityMatrixElements = createIdentityMatrixElements(a.numberOfRows);
        //Matrix permutationMatrix = new Matrix(a.numberOfRows, a.numberOfColumns, identityMatrixElements);
        //double[][] workingElements = a.elements.clone();
        //Matrix workingMatrix = new Matrix(a.numberOfRows, a.numberOfColumns, workingElements);
        Matrix workingMatrix = HookeJeeves.copyPoint(a);
        //System.out.println(workingMatrix.elements.toString());
        Matrix.printMatrix(workingMatrix);
        for (int i = 0; i < a.numberOfRows - 1; i++) {
            if (lup) {
                int index = findMaxApsElement(workingMatrix, i, workingMatrix.elements[i][i]);
                if (index != i) {
                    swapRowsOfMatrix(permutationMatrix, i, index);
                    //double[][] tmpEyes = createIdentityMatrixElements(a.numberOfRows);
                    swapRowsOfMatrix(workingMatrix, i, index);
                    System.out.println("Obavljamo mijenjanje redaka " + i + " i " + index);
                    /*if (i != index)
                        System.out.println("Mijenjamo " + i + ". i " + index + ". redak.");
                    permutateCurrentMatrix(this, new Matrix(a.numberOfRows, a.numberOfColumns, tmpEyes, true));
                    */
                    //System.out.println(workingMatrix.elements.toString());
                    Matrix.printMatrix(workingMatrix);
                }
            }
            double pivot = workingMatrix.getElement(i, i);
            if (pivot == 0) {
                //System.out.println(workingMatrix.elements.toString());
                Matrix.printMatrix(workingMatrix);
                //throw new IllegalArgumentException("Pivot je jednak nuli.");
                System.out.println("Pivot je jednak nuli - nije moguce provesti dekompoziciju");
                return null;
            }
            for (int j = i + 1; j < a.numberOfRows; j++) {
                workingMatrix.setElement(j, i, sanityCheck(workingMatrix.getElement(j, i) / pivot));
                for (int k = i + 1; k < a.numberOfRows; k++) {
                    workingMatrix.setElement(j, k, sanityCheck(workingMatrix.getElement(j, k) - workingMatrix.getElement(j, i) * workingMatrix.getElement(i, k)));
                }
            }
            //System.out.println(workingMatrix.elements.toString());
            Matrix.printMatrix(workingMatrix);
        }
        System.out.println("Vracam dekomponiranu matricu, a predana permutacijska matrica promijenjena je ako je bilo potrebno. Njome sada treba pomnoziti vektor b.");

        //workingMatrix = Matrix.multiply(permutationMatrix, workingMatrix);
        return workingMatrix;
    }

    public static double[][] createIdentityMatrixElements(int n) {
        double[][] elements = new double[n][n];
        for (int i = 0; i < n; i++)
            elements[i][i] = 1;
        return elements;
    }

    private static int findMaxApsElement(Matrix matrix, int startIndex, double value) {
        int indexOfMax = startIndex;
        double max = value;
        for (int i = startIndex; i < matrix.numberOfRows; i++)
            if (Math.abs(matrix.getElement(i, startIndex)) > max) {
                max = Math.abs(matrix.getElement(i, startIndex));
                indexOfMax = i;
            }
        return indexOfMax;
    }

    public static Matrix transpose(Matrix matrix){
        double [][] resultElements = new double[matrix.numberOfColumns][matrix.numberOfRows];
        for(int i = 0; i < matrix.numberOfRows; i++){
            for(int j = 0; j < matrix.numberOfColumns; j++){
                resultElements[j][i] = matrix.elements[i][j];
            }
        }
        Matrix resultMatrix = new Matrix(matrix.numberOfColumns, matrix.numberOfRows, resultElements);
        return resultMatrix;
    }

    public static boolean hasInverse(Matrix matrix){
        if(matrix.numberOfRows != matrix.numberOfColumns){
            System.out.println("Matrica nije kvadratna, nema inverz");
            return false;
        }
        double determinant = Matrix.determinant(matrix);
        if(determinant == 0){
            System.out.println("Matrica je singularna, nema inverz");
            return false;
        }
        return true;

    }
    private static double determinant(Matrix matrix){
        return matrix.elements[0][0] * matrix.elements[1][1] * matrix.elements[2][2] +
                matrix.elements[0][1] * matrix.elements[1][2] * matrix.elements[2][0] +
                matrix.elements[0][2] * matrix.elements[1][0] * matrix.elements[2][1] -
                matrix.elements[2][0] * matrix.elements[1][1] * matrix.elements[0][2] -
                matrix.elements[2][1] * matrix.elements[1][2] * matrix.elements[0][0] -
                matrix.elements[2][2] * matrix.elements[1][0] * matrix.elements[0][1];
    }

    public static Matrix invert(Matrix matricaZaInvertirati) {

        //Ax = prvi stupac jedinicne
        // => x je prvi stupac invertirane, itd. za svaki stupac

        Matrix kopijaMatriceZaInvertirati = HookeJeeves.copyPoint(matricaZaInvertirati);


        double[][] jedinicnaMatricaElements = Matrix.createIdentityMatrixElements(kopijaMatriceZaInvertirati.getRowsCount());//) stvoi jedinicnu matricu;
        Matrix jedinicnaMatrica = new Matrix(kopijaMatriceZaInvertirati.getRowsCount(), kopijaMatriceZaInvertirati.getColsCount(), jedinicnaMatricaElements);

        Matrix dekomponirana = Matrix.lupDecomposition(kopijaMatriceZaInvertirati,jedinicnaMatrica,true);
        //Matrix matricaZaSpremanjeStupacaInverza = new Matrix()
        double[][] elementiKonacneInvertiraneMatrice = new double[matricaZaInvertirati.getColsCount()][matricaZaInvertirati.getRowsCount()];

        for (int j = 0; j < matricaZaInvertirati.getColsCount(); j++) {

            double[][] elementiIpsilona = new double[matricaZaInvertirati.getRowsCount()][1];
            elementiIpsilona[j][0] = 1;
            Matrix y = new Matrix(matricaZaInvertirati.getRowsCount(), 1, elementiIpsilona);

            Matrix permutiraniY = Matrix.multiply(jedinicnaMatrica,y);

            Matrix ySupstituiranUnaprijed = Matrix.forwardSubstitution(dekomponirana, permutiraniY);
            Matrix ySupstituiranUnatrag = Matrix.backwardSupstitution(dekomponirana, ySupstituiranUnaprijed);

            for (int i = 0; i < ySupstituiranUnatrag.getRowsCount(); i++) {
                elementiKonacneInvertiraneMatrice[i][j] = ySupstituiranUnatrag.getElements()[i][0];

            }

        }
        Matrix konacnaInvertiranaMatrica = new Matrix(matricaZaInvertirati.getRowsCount(), matricaZaInvertirati.getColsCount(), elementiKonacneInvertiraneMatrice);
        return konacnaInvertiranaMatrica;



    }
    //NE TRIBA
    /*private static double determinant(Matrix matrix) {
        if(matrix.numberOfRows != matrix.numberOfColumns){
            System.out.println("Matrica nije kvadratna, nema determinantu");
            return 0;
        }
        if (matrix.getRowsCount() == 1)
            return matrix.getElement(0, 0);
        else if (matrix.getRowsCount() == 2)
            return matrix.getElement(0, 0) * matrix.getElement(1, 1) - matrix.getElement(0, 1) * matrix.getElement(1, 0);
        else {
            double resultValue = 0;
            for (int i = 0, limit = matrix.getColsCount(); i < limit; ++i) {
                resultValue += matrix.getElement(0, i) * determinant(matrix.subMatrix(0, i, true)) * Math.pow(-1, i);
            }
            return resultValue;
        }

    }
    //NE TRIBA
    private static Matrix subMatrix(Matrix matrix, int rowToEliminate, int colToEliminate){
        int returnRowNumber = matrix.numberOfRows - 1;
        int returnColNumber = matrix.numberOfColumns - 1;
        double [][] returnElements = new double[returnRowNumber][returnColNumber];
        for(int i = 0; i < matrix.numberOfRows; i++){
            for(int j = 0; j < matrix.numberOfColumns; j++){

            }
        }
    }*/

}
