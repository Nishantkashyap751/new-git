import java.util.Random;

public class matrixoperations {

    public static int[][] createRandomMatrix(int size) {
        Random rand = new Random();
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = rand.nextInt(10);
            }
        }
        return matrix;
    }
    
    public static int[][] addMatrices(int[][] matrixA, int[][] matrixB) {
        int rows = matrixA.length;
        int cols = matrixA[0].length;
        int[][] sum = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }
        return sum;
    }

    public static int[][] subtractMatrices(int[][] matrixA, int[][] matrixB) {
        int rows = matrixA.length;
        int cols = matrixA[0].length;
        int[][] difference = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                difference[i][j] = matrixA[i][j] - matrixB[i][j];
            }
        }
        return difference;
    }

    public static int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB) {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int rowsB = matrixB.length;
        int colsB = matrixB[0].length;

        if (colsA != rowsB) {
            System.out.println("Matrices cannot be multiplied.");
            return null;
        }

        int[][] product = new int[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    product[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return product;
    }
    
    public static int[][] transposeMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] transpose = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transpose[j][i] = matrix[i][j];
            }
        }
        return transpose;
    }

    public static double determinant2x2(int[][] matrix) {
        if (matrix.length != 2 || matrix[0].length != 2) {
            System.out.println("Invalid matrix size for 2x2 determinant.");
            return Double.NaN;
        }
        return (double) (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]);
    }

    public static double determinant3x3(int[][] matrix) {
        if (matrix.length != 3 || matrix[0].length != 3) {
            System.out.println("Invalid matrix size for 3x3 determinant.");
            return Double.NaN;
        }
        double det = 0;
        det += matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1]);
        det -= matrix[0][1] * (matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0]);
        det += matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]);
        return det;
    }
    
    public static double[][] inverse2x2(int[][] matrix) {
        double det = determinant2x2(matrix);
        if (det == 0) {
            System.out.println("Inverse does not exist for this matrix.");
            return null;
        }
        double[][] inverse = new double[2][2];
        inverse[0][0] = matrix[1][1] / det;
        inverse[0][1] = -matrix[0][1] / det;
        inverse[1][0] = -matrix[1][0] / det;
        inverse[1][1] = matrix[0][0] / det;
        return inverse;
    }
    
    public static double[][] inverse3x3(int[][] matrix) {
        double det = determinant3x3(matrix);
        if (det == 0) {
            System.out.println("Inverse does not exist for this matrix.");
            return null;
        }
        double[][] inverse = new double[3][3];
        inverse[0][0] = (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1]) / det;
        inverse[0][1] = -(matrix[0][1] * matrix[2][2] - matrix[0][2] * matrix[2][1]) / det;
        inverse[0][2] = (matrix[0][1] * matrix[1][2] - matrix[0][2] * matrix[1][1]) / det;
        inverse[1][0] = -(matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0]) / det;
        inverse[1][1] = (matrix[0][0] * matrix[2][2] - matrix[0][2] * matrix[2][0]) / det;
        inverse[1][2] = -(matrix[0][0] * matrix[1][2] - matrix[0][2] * matrix[1][0]) / det;
        inverse[2][0] = (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]) / det;
        inverse[2][1] = -(matrix[0][0] * matrix[2][1] - matrix[0][1] * matrix[2][0]) / det;
        inverse[2][2] = (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]) / det;
        
        return inverse;
    }

    public static void displayMatrix(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
    
    public static void displayMatrix(double[][] matrix) {
        if (matrix == null) {
            return;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%.4f\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("Demonstrating operations on 2x2 matrices:");
        int[][] matrixA = createRandomMatrix(2);
        int[][] matrixB = createRandomMatrix(2);

        System.out.println("Matrix A:");
        displayMatrix(matrixA);
        System.out.println("Matrix B:");
        displayMatrix(matrixB);
        
        System.out.println("\nAddition of A and B:");
        displayMatrix(addMatrices(matrixA, matrixB));

        System.out.println("\nSubtraction of A and B:");
        displayMatrix(subtractMatrices(matrixA, matrixB));

        System.out.println("\nMultiplication of A and B:");
        displayMatrix(multiplyMatrices(matrixA, matrixB));

        System.out.println("\nTranspose of A:");
        displayMatrix(transposeMatrix(matrixA));
        
        System.out.println("\nDeterminant of A:");
        System.out.println(determinant2x2(matrixA));

        System.out.println("\nInverse of A:");
        displayMatrix(inverse2x2(matrixA));

        System.out.println("\n------------------------------------------------");

        System.out.println("\nDemonstrating operations on 3x3 matrices:");
        int[][] matrixC = createRandomMatrix(3);
        int[][] matrixD = createRandomMatrix(3);

        System.out.println("Matrix C:");
        displayMatrix(matrixC);
        System.out.println("Matrix D:");
        displayMatrix(matrixD);

        System.out.println("\nAddition of C and D:");
        displayMatrix(addMatrices(matrixC, matrixD));

        System.out.println("\nSubtraction of C and D:");
        displayMatrix(subtractMatrices(matrixC, matrixD));
        
        System.out.println("\nMultiplication of C and D:");
        displayMatrix(multiplyMatrices(matrixC, matrixD));

        System.out.println("\nTranspose of C:");
        displayMatrix(transposeMatrix(matrixC));

        System.out.println("\nDeterminant of C:");
        System.out.println(determinant3x3(matrixC));

        System.out.println("\nInverse of C:");
        displayMatrix(inverse3x3(matrixC));
    }
}
