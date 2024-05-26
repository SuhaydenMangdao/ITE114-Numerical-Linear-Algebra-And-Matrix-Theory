/*  
Name: Mangdao, Suhayden S. 
Subjects & Section: ITE114 AAA 
Date: April 17, 2024

Input/s: 
- Matrix A of dimensions n x n, indicating the coefficients of a linear system with 'n' unknowns
- Vector b of size 'n' representing constants within the linear system.

Output/s: 
- Display Matrix Form 
- Display Augmented matrix
- Display Reduced Row Echelon Form (RREF)
- Solution and result
- Verification
- Looping of the code without recompiling
- Vector x of size 'n' that satisfies the equation Ax=b

*/

import java.util.*;
public class Matrix_RREF {
    public static double[][] solve(double[][] augmentedMatrix, double[] constants) {
        double [][] A= augmentedMatrix;
        int m = A.length;
        int n = A[0].length;

        // Perform Gauss-Jordan elimination
        for (int i = 0; i < Math.min(m, n); i++) {
            int pivot = i;
            for (int j = i + 1; j < m; j++) {
                if (Math.abs(A[j][i]) > Math.abs(A[pivot][i])) {
                    pivot = j;
                }
            }
            double[] temp = A[i];
            A[i] = A[pivot];
            A[pivot] = temp;

            if (Math.abs(A[i][i]) > 1e-10) {
                for (int j = i + 1; j < m; j++) {
                    double factor = A[j][i] / A[i][i];
                    for (int k = i; k < n; k++) {
                        A[j][k] -= factor * A[i][k];
                    }
                }
                for (int j = 0; j < i; j++) {
                    double factor = A[j][i] / A[i][i];
                    for (int k = i; k < n; k++) {
                        A[j][k] -= factor * A[i][k];
                    }
                }
                for (int j = 0; j < m; j++) {
                    if (Math.abs(A[j][i]) < 1e-10) {
                        A[j][i] = 0;
                    }
                }
                double pivotVal = A[i][i];
                for (int j = i; j < n; j++) {
                    A[i][j] /= pivotVal;
                }
            }
        }
        return A;
    }
    
     public static void steps(double[][] augmentedMatrix, double[] constants) {
        double [][] A= augmentedMatrix;
        int m = A.length;
        int n = A[0].length;

        // Perform Gauss-Jordan elimination
        for (int i = 0; i < Math.min(m, n); i++) {
            int pivot = i;
            for (int j = i + 1; j < m; j++) {
                if (Math.abs(A[j][i]) > Math.abs(A[pivot][i])) {
                    pivot = j;
                }
            }
            printAugmentedMatrix(round(A));
            double[] temp = A[i];
            A[i] = A[pivot];
            A[pivot] = temp;

            if (Math.abs(A[i][i]) > 1e-10) {
                for (int j = i + 1; j < m; j++) {
                    double factor = A[j][i] / A[i][i];
                    for (int k = i; k < n; k++) {
                        A[j][k] -= factor * A[i][k];
                    }
                }
                printAugmentedMatrix(round(A));
                for (int j = 0; j < i; j++) {
                    double factor = A[j][i] / A[i][i];
                    for (int k = i; k < n; k++) {
                        A[j][k] -= factor * A[i][k];
                    }
                }
                printAugmentedMatrix(round(A));
                for (int j = 0; j < m; j++) {
                    if (Math.abs(A[j][i]) < 1e-10) {
                        A[j][i] = 0;
                    }
                }
                printAugmentedMatrix(round(A));
                double pivotVal = A[i][i];
                for (int j = i; j < n; j++) {
                    A[i][j] /= pivotVal;
                }
            }
            printAugmentedMatrix(round(A));
        }
    }
    
    //method to print a matrix
    public static void printMatrix(double[][] coefficient) {
        int r= coefficient.length;
        for (int i = 0; i <r; i++) {
                System.out.print("| ");
                for (int j = 0; j < r; j++) {
                    // print the value with appropriate spacing
                    if (coefficient[i][j]>=0)
                    {
                       System.out.print(coefficient[i][j] + "  ");
                    }
                    if (coefficient[i][j]<0)
                    {
                        System.out.print(coefficient[i][j] + " ");
                    }
                }
                System.out.print("| \n");
        }
    }
    
    //method to print the augmented matrix
        public static void printAugmentedMatrix(double[][] augmentedMatrix) { 
        int r = augmentedMatrix.length;
                
        for (int i = 0; i < r; i++) {
            System.out.print("  |  ");
            for (int j = 0; j < augmentedMatrix[i].length - 1; j++) {
                int roundedValue = (int)Math.round(augmentedMatrix[i][j]);
                if (roundedValue >= 0) {
                    System.out.print(roundedValue + "  ");
                } else {
                    System.out.print(roundedValue + " ");
                }
            }
            System.out.print(" | ");
            
            int roundedLastValue = (int)Math.round(augmentedMatrix[i][augmentedMatrix[i].length - 1]);
            if (roundedLastValue >= 0) {
                System.out.print("  " + roundedLastValue);
            } else {
                System.out.print(" " + roundedLastValue);
            }
            
            System.out.print("  | \n");
        }
        System.out.println();
    }

    
    //method to print matrix equation
        public static void matEquation(double[][] coefficient, char[] variable, double[] constants) {
        int r = coefficient.length;
        for (int i = 0; i < r; i++) {
            System.out.print("  |  ");
            for (int j = 0; j < r; j++) {
                // Round coefficient to the nearest integer and convert to int
                int roundedCoefficient = (int)Math.round(coefficient[i][j]);
                if (roundedCoefficient >= 0) {
                    System.out.print(roundedCoefficient + "  ");
                } else {
                    System.out.print(roundedCoefficient + " ");
                }
            }
            System.out.print(" | |  ");
    
            // Print variable without rounding
            System.out.print(variable[i] + "  ");
    
            System.out.print(" | ");
            if (i != r) {
                if (i != (r / 2))
                    System.out.print("    ");
            }
            if (i == (r / 2)) {
                System.out.print(" =  ");
            }
            System.out.print("|  ");
    
            // Round constant to the nearest integer and convert to int
            int roundedConstant = (int)Math.round(constants[i]);
            if (roundedConstant >= 0) {
                System.out.print(roundedConstant + "  ");
            } else {
                System.out.print(roundedConstant + " ");
            }
            System.out.print(" |\n");
        }
        System.out.print("\n");
    }

    
    //method to print the equation
    public static void printEquation(double [][] coefficients, char [] variable, double [] constants)
    {
        int n= coefficients.length;
        for (int i = 0; i < n; i++) {
            System.out.print("\n Equation " + (i+1) + ": ");
            for (int j = 0; j < n; j++) {
                System.out.print(Math.round(coefficients[i][j]) + " " + variable[j]+ " ");
                if ((n-1)!=j)
                {   
                        if(coefficients[i][j+1]>=0)
                        {
                            System.out.print(" + ");
                        }
                }
                if ((n-1)==j)
                {
                    System.out.print(" = "+ constants[i]);
                }
            }
        }
        System.out.print("\n");
    }
    
    //method to augmented
    public static double [][] toAug(double [][] coefficients, char [] variable, double [] constants)
    {
        int r= coefficients.length;
        double[][] augmentedMatrix = new double[r][r + 1];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < r; j++) {
                    augmentedMatrix[i][j] = coefficients[i][j];
                }
                augmentedMatrix[i][r] = constants[i];
            }
        return augmentedMatrix;
    }
    
    //method to round off
        public static double[][] round(double[][] array) {
        double[][] roundedArray = new double[array.length][];
        for (int i = 0; i < array.length; i++) {
            roundedArray[i] = new double[array[i].length];
            for (int j = 0; j < array[i].length; j++) {
                roundedArray[i][j] = Math.round(array[i][j]);
            }
        }
        return roundedArray;
    }
    
    public static int findNumberOfSolutions(double[][] coefficients, double[] constants) {
        int numEquations = coefficients.length;
        int numVariables = coefficients[0].length;
        
        // Create a new matrix with the coefficients and constants
        double[][] augmentedMatrix = new double[numEquations][numVariables + 1];
        for (int i = 0; i < numEquations; i++) {
            for (int j = 0; j < numVariables; j++) {
                augmentedMatrix[i][j] = coefficients[i][j];
            }
            augmentedMatrix[i][numVariables] = constants[i];
        }
        
        // Calculate the determinant
        double determinant = calculateDeterminant(coefficients);
        
        // If the determinant is zero, the system has either no solutions or infinitely many solutions
        if (determinant == 0) {
            // Check if the system has infinitely many solutions
            for (int i = 0; i < numEquations; i++) {
                // Create a new matrix by replacing the ith column of the coefficient matrix with the constants
                double[][] subMatrix = new double[numEquations][numVariables];
                for (int j = 0; j < numEquations; j++) {
                    for (int k = 0; k < numVariables; k++) {
                        if (k == i) {
                            subMatrix[j][k] = constants[j];
                        } else {
                            subMatrix[j][k] = coefficients[j][k];
                        }
                    }
                }
                
                // If the determinant of the submatrix is not zero, the system has infinitely many solutions
                if (calculateDeterminant(subMatrix) != 0) {
                    return -1; // Indicate no solutions
                }
            }
            return 0; // Indicate infinitely many solutions
        } else {
            return 1; // Indicate a unique solution
        }
    }

    public static double calculateDeterminant(double[][] matrix) {
        int size = matrix.length;
        double determinant = 0;
    
        if (size == 1) {
            determinant = matrix[0][0];
        } else if (size == 2) {
            determinant = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } else {
            for (int j = 0; j < size; j++) {
                double[][] subMatrix = new double[size - 1][size - 1];
                for (int i = 1; i < size; i++) {
                    for (int k = 0; k < size; k++) {
                        if (k < j) {
                            subMatrix[i - 1][k] = matrix[i][k];
                        } else if (k > j) {
                            subMatrix[i - 1][k - 1] = matrix[i][k];
                        }
                    }
                }
                determinant += matrix[0][j] * Math.pow(-1, j) * calculateDeterminant(subMatrix);
            }
        }
    
        return determinant;
    }
    
    public static double [] getSum(double [][] matrix, double [] R )
    {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        int numZero=numRows;
        boolean[] zeroRows = new boolean[numRows];
        double [] sum= new double [numZero];
        
        for (int i = 0; i < numRows; i++) {
                zeroRows[i] = true;
                for (int j = 0; j < numCols; j++) {
                    if (matrix[i][j] != 0) {
                        zeroRows[i] = false;
                        numZero-=1;
                        break;
                    }
                }
            }
        
        int n=0;
        int m=0;
        for (int i = 0; i < numRows; i++) {
            n=0;
            if (zeroRows[i]==false) 
            {
                for ( int j= 0; j<numCols;j++)
                {
                    if(R.length<2)
                    {
                        n=0;
                    }
                    
                    if(j>i && j<numRows)
                    {
                        sum [i] += -((matrix[i][j]) * R[n]);
                        n++;
                    }
                    else if(j==numRows)
                    {
                        sum [i] += matrix[i][numCols-1];

                    }
                }
            }
            
            for (int t=numRows-numZero;t<numRows; t++)
            {
                sum[m+(numRows-numZero)]=R[t-(numRows-numZero)];
            }
        }
        return sum;
    }
    
    public static void sampleSolution(double [][] augmented, double [][] coe, double [] constants, char [] variable)
    {
            Scanner sc= new Scanner (System.in);
            boolean condition= true;
            double [][] matrix= augmented;
            
            // Identify zero rows
            int numRows = augmented.length;
            int numCols = augmented[0].length;
            int numZero=numRows;
            boolean[] zeroRows = new boolean[numRows];
            double [] sum= new double [numZero];
            
            for (int i = 0; i < numRows; i++) {
                zeroRows[i] = true;
                for (int j = 0; j < numCols; j++) {
                    if (augmented[i][j] != 0) {
                        zeroRows[i] = false;
                        numZero-=1;
                        break;
                    }
                }
            }
    
            // Print zero rows
            int s=1;
            for (int i = 0; i < numRows; i++) {
                if (zeroRows[i]==false) {
                    for (int j=0; j<numCols; j++)
                    {
                        if (matrix[i][j]!=0 && j<numCols-1)
                        {
                            System.out.print("(" + matrix[i][j] + ")" + variable[j]);
                            if (j<numCols-2)
                            {
                                System.out.print(" + ");
                            }
                        }
                        else if (j==numCols-1)
                        {
                            System.out.println(" = " + matrix[i][numCols-1]);
                        }
                    }
                }
                else
                {   
                    System.out.println(variable[i] + " = " + "R");
                    s=s+1;
                }
            }
            System.out.println();
            
            System.out.println("How many values of R you wish to try?");
            int nR=sc.nextInt();
            int an= 0;
            while(condition)
            {
                int h=0;
                for (h=0; h<nR ; h++)
                {
                        System.out.print("Set value R = ");
                        double[] R = new double [numZero];
                        for ( int i = 0; i < numZero; i++) {
                            try
                            {
                                R[i]= sc.nextDouble();
                            }
                            catch (InputMismatchException e)
                            {
                                System.out.println("Invalid Input. Please Try Again!\n");
                                System.out.printf("\nSet value\n");
                                sc.next();
                                R[i]= sc.nextDouble();
                            }
                        }    
    
                        sum=getSum(matrix, R);
                        
                        System.out.println();
                        
                        for (int i = 0; i < numRows-numZero; i++) {
                            
                            if (i<numRows-1)
                            {
                                 System.out.print(variable[i] + " = " + matrix[i][numCols-1]);
                            }
                
                            if (zeroRows[i]==false) {
                                for (int j=0; j<numRows; j++)
                                {
                                    if (j>i)
                                    {
                                        if (matrix[i][j]!=0 && j<numRows)
                                        {
                                            System.out.print("+(" + (-(matrix[i][j]))+ ")" + variable[j]);
                                        }
                                    }
                                    if(j==numRows-1)
                                        {
                                            System.out.println();
                                        }
                                }
                            }
                        }
                        
                        for (int i=numRows-numZero;i<numRows; i++)
                        {
                            System.out.println(variable[i] + " = " + R[i-(numRows-numZero)] + "\n");
                        }
                        
                        System.out.println();
                        int u=0;
                        for (int i = 0; i < numRows-numZero; i++) {
                            
                            if (i<numRows-1)
                            {
                                 System.out.print(variable[i] + " = " + matrix[i][numCols-1]);
                            }
                
                            if (zeroRows[i]==false) {
                                for (int j=0; j<numRows; j++)
                                {
                                    if (j>i)
                                    {
                                        if (matrix[i][j]!=0 && j<numRows)
                                        {
                                            System.out.print("+(" + (-(matrix[i][j]))+ ")" + "(" + R[u] + ")");
                                        }
                                    }
                                    if(j==numRows-1)
                                    {
                                        System.out.println();
                                    }
                                }
                            }
                        }
                        
                        for (int i=numRows-numZero;i<numRows; i++)
                        {
                            System.out.println(variable[i] + " = " + R[i-(numRows-numZero)] + "\n");
                        }
                        
                        for (int i=0;i<numRows; i++)
                        {
                            if (i<numRows-numZero)
                            {
                                System.out.println(variable[i]+ " = " + sum[i]);
                            }
                            else
                            {
                                System.out.println(variable[i]+ " = " + sum[i]);
                            }
                        }
                        System.out.println("\nVerifying...");
                        verify(coe, sum, constants, variable);
                    
                        System.out.println("\nInput another? \n1. Yes \n2. No ");
                        an=sc.nextInt();
                        System.out.println();
                        if (an==1)
                        {
                            h=h;
                        }
                        else if (an==2)
                        {
                            h=nR;
                        }
                        else
                        {
                            h=nR;
                        }
                }
                if(h>=nR)
                        {
                            condition=false;
                            System.out.println("You have reached the number of R you want to input/You choose to proceed.");
                        }
            }
    }

    public static void result(int f, double [][] coe, double [] constants, char [] variable)
    {
        double[][]augmented=round(solve(toAug(coe,variable,constants),constants));
        int det =findNumberOfSolutions(coe, constants);
        double[][] solutions = solve(augmented, constants);
        
        System.out.println("Result:");
        if (det==1) {
            System.out.println("The system has a unique solution.");
            System.out.println("\nSolution:");
            for (int i = 0; i < solutions.length; i++) {
                System.out.println(variable[i] + " = " + Math.round(solutions[i][f]));
            } 
        } else if (det==0) {
            System.out.println("The system has infinitely many solutions");
            sampleSolution(augmented, coe, constants, variable);
        } else if (det==-1) {
            System.out.println("The system has no solution.");
        }
    }

    
    public static void verify(double[][] coefficients, double[] sol, double[] constants, char[] variable) { 
        // Check equations
        int n = sol.length;
        boolean correct = true;
    
        /*System.out.print("\nMatrix Form:\n");
        double[][] augmentedMatrix = toAug(coefficients, variable, constants);
        double[][] rrefMatrix = computeRREF(augmentedMatrix);
        printAugmentedMatrix(augmentedMatrix);*/
        
        /*System.out.print("\nRREF:\n");
        double[][] augmentedMatrix = toAug(coefficients, variable, constants);
        double[][] rrefMatrix = computeRREF(augmentedMatrix);
        printAugmentedMatrix(rrefMatrix);*/
        
        System.out.print("\nMatrix Form:\n");
            for (int i = 0; i < coefficients.length; i++) {
            System.out.print("  |  ");
            for (int j = 0; j < coefficients[i].length; j++) {
                int roundedCoefficient = (int)Math.round(coefficients[i][j]);
                if (roundedCoefficient >= 0) {
                    System.out.print(roundedCoefficient + "  ");
                } else {
                    System.out.print(roundedCoefficient + " ");
                }
            }
            System.out.print(" | |  ");
    
            System.out.print(variable[i] + "  ");
    
            System.out.print(" | ");
            if (i != coefficients.length) {
                if (i != (coefficients.length / 2))
                    System.out.print("    ");
            }
            if (i == (coefficients.length / 2)) {
                System.out.print(" =  ");
            }
            System.out.print("|  ");
    
            int roundedConstant = (int)Math.round(constants[i]);
            if (roundedConstant >= 0) {
                System.out.print(roundedConstant + "  ");
            } else {
                System.out.print(roundedConstant + " ");
            }
            System.out.print(" |\n");
        }
        System.out.print("\n");

        System.out.print("\nRREF:\n");
        /*double[][] rrefMatrix = computeRREF(augmentedMatrix);
        printAugmentedMatrix(rrefMatrix);*/
        double[][] rrefMatrix = computeRREF(toAug(coefficients, variable, constants));
        printAugmentedMatrix(rrefMatrix);
        
    
        if (findNumberOfSolutions(coefficients, constants) == 0) {
            System.out.print("\nResult: Matrix has many solutions");
            System.out.println("\nSample Solution:");
            for (int i = 0; i < sol.length; i++) {
                System.out.println(variable[i] + " = " + (int) Math.round(sol[i]));
            }
        }
            
        int det = findNumberOfSolutions(coefficients, constants);
        double[][] solutions = solve(round(rrefMatrix), constants);
        if (det == 1) {
            //System.out.println("The system has a unique solution.");
            System.out.println("\nValues of variables:");
            for (int i = 0; i < solutions.length; i++) {
                System.out.println(variable[i] + " = " + Math.round(solutions[i][n]));
            }
        } else if (det == 0) {
            System.out.println("The system has infinitely many solutions");
            sampleSolution(rrefMatrix, coefficients, constants, variable);
        } else if (det == -1) {
            System.out.println("The system has no solution.");
        }
                
        
        // Extracting constants from the last column of the RREF augmented matrix
        double[] rrefConstants = new double[n];
        for (int i = 0; i < n; i++) {
            rrefConstants[i] = rrefMatrix[i][n]; // assuming n is the number of columns
        }
    
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += coefficients[i][j] * sol[j]; // Substituting values from the coefficients matrix
            }
            System.out.println("\nEquation " + (i + 1) + ": ");
            for (int j = 0; j < n; j++) {
                System.out.print(Math.round(coefficients[i][j]) + "(" + (int) Math.round(sol[j]) + ")");
                if (j != n - 1) {
                    if (coefficients[i][j + 1] >= 0) {
                        System.out.print("+");
                    }
                }
            }
    
            // Calculate the equation result
            double equationResult = sum;
            System.out.println(" = " + equationResult);
    
            /*if (Math.abs(equationResult - constants[i]) > 1e-6) { // Adjust the comparison for floating-point precision
                correct = false;
                System.out.printf("Equation %d is not satisfied!\n", i + 1);
            }*/
    
             //Add an extra line break after each equation
            System.out.println();
        }
        
        if (correct) {
            System.out.println("\nAll equations are satisfied.\n");
        }
    }

    
    public static double[][] computeRREF(double[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
    
        double[][] rrefMatrix = new double[numRows][numCols];
    
        // Copy the matrix
        for (int i = 0; i < numRows; i++) {
            System.arraycopy(matrix[i], 0, rrefMatrix[i], 0, numCols);
        }
    
        int lead = 0;
        for (int r = 0; r < numRows; r++) {
            if (numCols <= lead) {
                break;
            }
            int i = r;
            while (rrefMatrix[i][lead] == 0) {
                i++;
                if (numRows == i) {
                    i = r;
                    lead++;
                    if (numCols == lead) {
                        break;
                    }
                }
            }
            double[] temp = rrefMatrix[i];
            rrefMatrix[i] = rrefMatrix[r];
            rrefMatrix[r] = temp;
    
            double lv = rrefMatrix[r][lead];
            if (lv != 0) {
                for (int j = 0; j < numCols; j++) {
                    rrefMatrix[r][j] /= lv;
                }
            }
            for (int j = 0; j < numRows; j++) {
                if (j != r) {
                    double nv = rrefMatrix[j][lead];
                    for (int k = 0; k < numCols; k++) {
                        rrefMatrix[j][k] -= nv * rrefMatrix[r][k];
                    }
                }
            }
            lead++;
        }
        return rrefMatrix;
    }
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean con= true;
        int r;
        while(con)
        {
            System.out.print("\nEnter the number of equation & unknowns: ");
            try
            {
                r=sc.nextInt();
            }
            catch(InputMismatchException e)
            {
                System.out.println("\nINVALID INPUT. TRY AGAIN! \n");
                System.out.print("\nEnter the number of equation & unknowns: ");
                sc.next();
                r=sc.nextInt();
            }
            
            while (r<1 || r>10)
            {
                System.out.println("NUMBER OF EQUATIONS MUST NOT BE LESS THAN 1 OR GREATER THAN 10");
                System.out.print("\nEnter the number of equation & unknowns: ");
                
                try
                {
                    r=sc.nextInt();
                }
                catch(InputMismatchException e)
                {
                    System.out.println("\nInvalid Input. Please try again.\n");
                    System.out.print("\nEnter the number of equation & unknowns: ");
                    sc.next();
                    r=sc.nextInt();
                }
            }
            
            //Ask for coefficient matrix
            System.out.printf("Enter the elements of the coefficient matrix: \n");
            double[][] coefficients = new double [r][r];
            int z = 0;
            for (int i = 0; i < r; i++) {
                    for (int j = 0; j < r; j++) {
                        try
                        {
                            
                            System.out.print("      Coefficient "+z +": ");
                            coefficients[i][j] = sc.nextDouble();
                            z++;
                        }
                        catch (InputMismatchException e)
                        {
                            System.out.println("[INVALID INPUT. TRY AGAIN!");
                            System.out.printf("Enter the elements of the coefficient matrix " + z + ": ");
                            sc.next();
                            coefficients[i][j] = sc.nextDouble();
                            z++;
                        }
                    }
            }
            
            
            // Generate variable vector
            char[] variable = new char[r];
            if (r == 1) {
                variable[0] = 'x';
            } else if (r == 2) {
                variable[0] = 'x';
                variable[1] = 'y';
            } else if (r == 3) {
                variable[0] = 'x';
                variable[1] = 'y';
                variable[2] = 'z';
                } else if (r == 4) {
                variable[0] = 'w';
                variable[1] = 'x';
                variable[2] = 'y';
                variable[3] = 'z';
            } else {
                for (int i = 0; i < r; i++) {
                    variable[i] = (char) ('x' + i);
                }
            }
            
            
            // Ask for variable vector
            /*System.out.printf("Enter the elements of the variable vector: \n");
            char[] variable= new char [r];
            int y =0;
            for (int i = 0; i < r; i++) {
                try
                {
                    System.out.print("      Variable "+y +": ");
                    variable[i]= sc.next().charAt(0);
                    y++;
                }
                catch (InputMismatchException e)
                {
                    System.out.println("[INVALID INPUT. TRY AGAIN!");
                    System.out.printf("Enter the elements of the coefficient matrix " + y + ": ");
                    sc.next();
                    variable[i]= sc.next().charAt(0);
                    y++;
                }
            }*/
            
            // Ask for constant vector
            System.out.printf("Enter the elements of the constant vector: \n");
            double[] constants = new double [r];
            int g = 0;
            for (int i = 0; i < r; i++) {
                try
                {
                    System.out.print("      Constant "+g +": ");
                    constants[i]= sc.nextDouble();
                    g++;
                }
                catch (InputMismatchException e)
                {
                    System.out.println("[INVALID INPUT. TRY AGAIN!");
                    System.out.printf("Enter the elements of the constant vector "+g +":\n ");
                    sc.next();
                    constants[i]= sc.nextDouble();
                    g++;
                }
            }        
            
            // Create augmented matrix
            double[][] augmentedMatrix = new double[r][r + 1];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < r; j++) {
                    augmentedMatrix[i][j] = coefficients[i][j];
                }
                augmentedMatrix[i][r] = constants[i];
            }
        
            double []sol=new double[r];
            double[][] solutions = solve(augmentedMatrix, constants);
            for (int i = 0; i < r; i++) {
                    sol[i]=Math.round(solutions[i][r]);
            }
            int n= sol.length;
            
            int ans=0;
                System.out.println("What do you want to do next?");
                System.out.print("\n1. Show Linear System\n2. Show Matrix Equation\n3. Show Augmented Matrix\n4. Show RREF\n");
                System.out.print("5. Show Result and Solution\n6. Show Verification\n7. None\n");
                System.out.print("Answer: ");
                ans= sc.nextInt();
            while (ans>0 || ans<6)
            {
                while(ans==1)
                {
                    System.out.print("\nLinear System: \n");
                    printEquation(coefficients,variable,constants);
                    break;
                }

                while(ans==2)
                {
                    System.out.print("\nMatrix Equation:\n");
                    matEquation(coefficients, variable, constants);
                    break;
                }
                
                while (ans==3)
                {
                    System.out.print("\nAugmented Matrix:\n");
                    printAugmentedMatrix(toAug(coefficients, variable, constants));
                    break;
                }
                
                while (ans==4)
                {
                    System.out.println("\nSteps:");
                    steps(toAug(coefficients, variable, constants), constants);
                    System.out.println("\nReduced Row Echelon Form:");
                    double augmented[][] = round(solve(augmentedMatrix, constants));
                    printAugmentedMatrix(augmented);
                    break;
                }
                
                while (ans==5)
                {
                    System.out.println();
                    result(r,coefficients, constants, variable);
                    System.out.println();
                    break;
                }
                
                while (ans==6)
                {
                    if (findNumberOfSolutions(coefficients, constants) == 1) {
                        System.out.println("\nVerifying...");
                        System.out.print("\nMatrix has a unique solution\n");
                        // System.out.print("\nAugmented Matrix:\n");
                        // printAugmentedMatrix(toAug(coefficients, variable, constants));
                        /*System.out.println("\nSolution:");
                        for (int i = 0; i < sol.length; i++) {
                            System.out.println(variable[i] + " = " + Math.round(solutions[i][r]));
                        }*/
                        verify(coefficients, sol, constants, variable);
                        
                        
                        
                    }
                    else
                    {
                        System.out.println("\nVerification is NOT AVAILABLE.\n");
                    }
                    break;
                }
                
                if(ans<6)
                {
                    System.out.println("What do you want to do next?");
                    System.out.print("\n1. Show Linear System\n2. Show Matrix Equation\n3. Show Augmented Matrix\n4. Show RREF\n");
                    System.out.print("5. Show Result and Solution\n6. Show Verification\n7. None\n");
                    System.out.print("Answer: ");
                    try
                    {
                        ans= sc.nextInt();
                    }
                    catch (InputMismatchException e) {
                        System.out.println("What do you want to do next?");
                        System.out.print("\n1.  Show Linear System\\n2. Show Matrix Equation\\n3. Show Augmented Matrix\\n4. Show RREF\\n");
                        System.out.print("5. Show Result and Solution\\n6. Show Verification\\n7. None\n");
                        System.out.print("Answer: ");
                        sc.next();
                        ans= sc.nextInt();
                    }
                }
                else{
                    break;
                }
            }

            System.out.println("Do you want to solve another one? Yes or No?");
            String answer=sc.next();
            System.out.println();
                    
                if (answer.equalsIgnoreCase("Yes"))
                {
                    con=true;
                }
                 if (answer.equalsIgnoreCase("No"))
                {
                    con=false;
                }
        }
    }
}