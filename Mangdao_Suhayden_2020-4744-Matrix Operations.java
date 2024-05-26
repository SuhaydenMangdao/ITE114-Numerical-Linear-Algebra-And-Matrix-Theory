//Suhayden S. Mangdao
//ITE114-AAA
//Matrix Operations

/*
 PROGRAM DESCRIPTION:
 
 ADDITION
 -Sample inputs are equal rows and columns of two matrices and it ensures the compatibility of the two matrices 
 and it displays the sum of the two matrices, it also provides an option to add another matrix to the result.
 
 MULTIPLICATION
 -it ensures the compatibility of the dimensions of two matrices and display a result after multiplication. 
 It also offers an option to multipy the result by another matrix iteratively.
 
 MULTIPLY BY CONSTANT
 - it allows the user to multiply the matrix by a constant and it based on user's input. 
 It also display the resultant matrix after multiplication and provides an option to multiply the result by 
 another constant iteratively.
 
 DIAGONALIZED MATRIX
- It facilitates the diagonalization of a square matrix and displays the original matrix and its diagonalized form it also 
offers an option to diagonalize another matrix.
 
NOTE: Ensure input matrices are correctly sized and compatible for the chosen operation.
*/

import java.util.*;

public class MatrixOperations{
    public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    boolean decider = true;
    while(decider){
        System.out.println("Choose what Operation to run \nEnter 1 for Addition \nEnter 2 for Multiplication \nEnter 3 for Multiplication by a Constant \nEnter 4 for Diagonalizing a Matrix  \nEnter 0 to Exit Program");
        int operationUse = validInput(); //Asking the user which operation to Run

        if(operationUse == 1){
        //Addition Operation
            System.out.print("Enter Row size for Matrix 1: ");
            int M1row = validInput();
            System.out.print("Enter Column size for Matrix 1: ");
            int M1column = validInput();
            int [][] Matrix1 = new int[M1row][M1column]; //Setting up the Matrix 1

            System.out.print("Enter Row size for Matrix 2: ");
            int M2row = validInput();
            while(M2row != M1row){
                System.out.print("Matrix 1 row and Matrix 2 row should be equal\nEnter Row size for Matrix 2: ");
                M2row = validInput();                
            }//Program is making sure that the Rows of both matrices is equal
            System.out.print("Enter Column size for Matrix 2: ");
            int M2column = validInput();
            while(M2column!= M1column){
                System.out.print("-Matrix 1 colum and Matrix 2 column should be equal-\nEnter Colum size for Matrix 2: ");
                M2column = validInput();                
            }//Program is making sure that the Columns of both matrices is equal            
            int [][] Matrix2 = new int[M2row][M2column]; //Setting up the Matrix 2

            for (int i = 0; i < Matrix1.length; i++) {
                for (int j = 0; j < Matrix1[i].length; j++) {
                    System.out.print("Matrix 1: Enter a value for ["+i+"]["+j+"]:: ");
                    Matrix1[i][j] = validInput();
                }
            } //Asking the user to input values in Matrix 1
            System.out.println();
            for (int i = 0; i < Matrix2.length; i++) {
                for (int j = 0; j < Matrix2[i].length; j++) {
                    System.out.print("Matrix 2: Enter a value for ["+i+"]["+j+"]:: ");
                    Matrix2[i][j] = validInput();
                }
            } //Asking the user to input values in Matrix 2

            int [][] ProTotal = new int[M1row][M2column]; //Setting up the resultant matrix
            add(Matrix1, Matrix2, ProTotal); //adding the two matrix
            showOutput(Matrix1, Matrix2, ProTotal); //Displays all the matrices
            do{
                System.out.println("Do you want to add another Matrix? Press Y if Yes, otherwise, Press Any Key"); //Asking the user if they want to add another matrix to the previous result
                String AddDecision = input.next();
                    if(AddDecision.equalsIgnoreCase("y")){
                        Matrix1 = ProTotal; //Storing the Previous Result to Matrix 1 to conserve space
                        int [][] ProTotal2 = new int[M1row][M2column]; //Setting up a new resultant matrix
                        for (int i = 0; i < Matrix2.length; i++) {
                            for (int j = 0; j < Matrix2[i].length; j++) {
                                System.out.print("Matrix 3: Enter a value for ["+i+"]["+j+"]:: ");
                                Matrix2[i][j] = validInput();
                            }
                        } //Asking the user to input values in Matrix 3
                        System.out.println();
                        add(Matrix1, Matrix2, ProTotal2); 
                        ProTotal = ProTotal2;//Stores 
                        showOutput(Matrix1, Matrix2, ProTotal2);
                    }else{
                        break;
                    }
            }while(true);
            
        }else if(operationUse == 2){
        //Multiplication Operation
            System.out.print("Enter Row size for Matrix 1: ");
            int M1row = validInput();
            System.out.print("Enter Column size for Matrix 1: ");
            int M1column = validInput();
            int [][] Matrix1 = new int[M1row][M1column];//Setting up the Matrix 1

            System.out.print("Enter Row size for Matrix 2: ");
            int M2row = validInput();
                while(M1column != M2row){
                    System.out.print("-Matrix 1 column and Matrix 2 row should be equal-\nEnter Row size for Matrix 2: ");
                    M2row = validInput();  
                } //Program is making sure that the Matrix 1 Column and M2 Row is equal
            System.out.print("Enter Column size for Matrix 2: ");
            int M2column = validInput();
            int [][] Matrix2 = new int[M2row][M2column]; //Setting up the Matrix 2

            for (int i = 0; i < Matrix1.length; i++) {
                for (int j = 0; j < Matrix1[i].length; j++) {
                    System.out.print("Matrix 1: Enter a value for ["+i+"]["+j+"]: ");
                    Matrix1[i][j] = validInput();
                }
            } //Asking the user to input values in Matrix 1
            System.out.println();
            for (int i = 0; i < Matrix2.length; i++) {
                for (int j = 0; j < Matrix2[i].length; j++) {
                    System.out.print("Matrix 2: Enter a value for ["+i+"]["+j+"]: ");
                    Matrix2[i][j] = validInput();
                }
            } //Asking the user to input values in Matrix 2
            int [][]ProTotal = new int[M1row][M2column]; //Setting up the resultant matrix
            multiply(Matrix1, Matrix2, ProTotal); //Multiplying the matrices
            showOutput(Matrix1, Matrix2, ProTotal); //Displaying the matrices
           do {
                System.out.println("Do you want to add another Matrix? Press Y if Yes, otherwise, Press Any Key");
                String addDecision = input.next();
                if (!addDecision.equalsIgnoreCase("y")) {
                    break;
                }

                // Store the previous result
                Matrix1 = ProTotal;

                // Prompt for new row size
                System.out.print("Enter Row size for New Matrix: ");
                 int newRowSize = validInput();

                // Check if the column size of the previous result matches the row size of the new matrix
                if (ProTotal[0].length != newRowSize) {
                    System.out.println("Error: The column size of the previous result matrix does not match the row size of the new matrix.");
                    continue; // Skip to the next iteration of the loop
                }

                // Prompt for new column size
                System.out.print("Enter Column size for New Matrix: ");
                int newColumnSize = validInput();
                int[][] newMatrix = new int[newRowSize][newColumnSize]; // Setting up the new matrix

                // Input values for the new matrix
                for (int i = 0; i < newMatrix.length; i++) {
                    for (int j = 0; j < newMatrix[i].length; j++) {
                        System.out.print("New Matrix: Enter a value for [" + i + "][" + j + "]: ");
                        newMatrix[i][j] = validInput();
                    }
                }
                System.out.println();

                // Multiply the previous result by the new matrix
                int[][] newProTotal = new int[ProTotal.length][newColumnSize];
                try {
                    multiply(ProTotal, newMatrix, newProTotal);
                    ProTotal = newProTotal;
                    // Display the matrices
                    showOutput(Matrix1, newMatrix, ProTotal);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: Matrices dimensions are incompatible for multiplication.");
                    // If multiplication fails, skip to the next iteration of the loop
                    continue;
                }
        } while (true);

        }
        
        else if (operationUse == 3) {
    
        System.out.print("Enter Row size for Matrix: ");
        int Mrow = validInput();
        System.out.print("Enter Column size for Matrix: ");
        int Mcolumn = validInput();
        int[][] originalMatrix = new int[Mrow][Mcolumn]; // Original Matrix

        // Input values for the original matrix
        for (int i = 0; i < originalMatrix.length; i++) {
            for (int j = 0; j < originalMatrix[i].length; j++) {
                boolean valid = false;
                while (!valid) {
                    System.out.print("Original Matrix: Enter a value for [" + i + "][" + j + "]: ");
                    originalMatrix[i][j] = validInput();
                    if (originalMatrix[i][j] != Integer.MIN_VALUE) {
                    valid = true;
                    } else {
                        System.out.println("Invalid input. Please enter a valid integer.");
                    }
                }
            }
        }

        // Initialize the resultant matrix
        int[][] ProTotal = new int[Mrow][Mcolumn];
        int[][] Matrix = null; // Matrix for multiplication

        // Initialize Matrix with originalMatrix for the first iteration
        Matrix = originalMatrix;
    
        // Declare and initialize the constant variable
        System.out.print("Enter the constant multiplier: ");
        int constant = validInput();

        do {
            // Prompt for the next constant multiplier

            // Perform the constant multiplication
            multiplyByConstant(Matrix, constant, ProTotal);

            // Update Matrix with ProTotal for subsequent iterations
            Matrix = ProTotal;

            // Display the matrices
            System.out.println(" ");
            System.out.println("Result: ");
            System.out.println(" ");
            System.out.println("Constant multiplier: " + constant); // Displaying the constant multiplier
            System.out.println(" ");
            newmultiplyByConstant(originalMatrix, ProTotal); // Displaying the original matrix and the current resultant matrix

            // Prompt for another constant multiplier or exit the loop
            System.out.print("Do you want to input another constant multiplier? (Press Y for Yes, any other key for No): ");
            String decision = input.next();
            if (!decision.equalsIgnoreCase("y")) {
                break;
            }
            System.out.println(" ");
            System.out.print("Enter the new constant multiplier: ");
            constant = validInput();

            // Store the current result in originalMatrix for the next iteration
            for (int i = 0; i < ProTotal.length; i++) {
            System.arraycopy(ProTotal[i], 0, originalMatrix[i], 0, ProTotal[i].length);
            }
        } while (true);

    }
    else if (operationUse == 4) {
                // Diagonalization Operation
                do {
                    try {
                        System.out.print("Enter Row size for Matrix: ");
                        int Mrow = validInput();
                         if (Mrow == 0) {
                            throw new IllegalArgumentException("Row and column sizes must not be equal to zero");
                        }
                        System.out.print("Enter Column size for Matrix: ");
                        int Mcolumn = validInput();
                        if (Mcolumn == 0) {
                            throw new IllegalArgumentException("Row and column sizes must not be equal to zero");
                        }

                        if (Mrow != Mcolumn) {
                            throw new IllegalArgumentException("Row and column sizes must be equal for diagonalization.");
                        }
                        
                        
                        int[][] Matrix = new int[Mrow][Mcolumn]; // Setting up the Matrix

                        for (int i = 0; i < Matrix.length; i++) {
                            for (int j = 0; j < Matrix[i].length; j++) {
                                System.out.print("Matrix: Enter a value for [" + i + "][" + j + "]: ");
                                Matrix[i][j] = validInput();
                            }
                        } // Asking the user to input values in Matrix

                        int[][] diagonalMatrix = diagonalize(Matrix);
                        showDiagonalMatrix(Matrix, diagonalMatrix);

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    System.out.println("Do you want to diagonalize another matrix? (Enter 'Y' to continue or any other key to exit): ");
                } while (input.next().equalsIgnoreCase("Y"));
            } else if (operationUse == 0) {
                decider = false;
            }
            
        if (!decider) {
                break;
        }
    System.out.println("Do you want to run the program again? Press Y if Yes, otherwise, Press Any Key"); //Asking the user if they want to run the program again
    String userDecision = input.next();
        if(!(userDecision.equalsIgnoreCase("Y"))){
            decider = false;
    }
    }
    }
    
    public static int validInput(){
        //method is responsible for checking if user input is valid for the program
        Scanner input = new Scanner(System.in);
        String userInput = input.next();
        boolean valid = true;
        while(valid){
            try{
                Integer.parseInt(userInput);
                valid = false;
            }catch(Exception e){
                System.out.println("Invalid Input, Try again.");
                userInput = input.next();
            }
        }
        return Integer.parseInt(userInput);
    }
    
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.print("| ");
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println("|");
        }
    }
    
    public static void showOutput(int[][]Matrix1, int[][]Matrix2, int[][]total){
        System.out.println("  ");
        System.out.println("Matrix 1");
        for (int i = 0; i < Matrix1.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < Matrix1[i].length; j++) {
                System.out.print(Matrix1[i][j] + " ");
            }
            System.out.println("|");
        } //Outputs the Matrix 1
        
        System.out.println("  ");
        System.out.println("Matrix 2");
        for (int i = 0; i < Matrix2.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < Matrix2[i].length; j++) {
                System.out.print(Matrix2[i][j] + " ");
            }
            System.out.println("|");
        } //Outputs the Matrix 2
        
        System.out.println("  ");
        System.out.println("Total");
        for (int i = 0; i < total.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < total[i].length; j++) {
                System.out.print(total[i][j] + " ");
            }
            System.out.println("|");
        } //Outputs the Total Matrix 
    }
        
    public static void add(int[][] Matrix, int[][] Matrix2, int[][] M12){
        
        for(int i = 0; i < Matrix.length; i++){
            for(int j = 0; j < Matrix[i].length; j++){
                M12[i][j] = Matrix[i][j]+Matrix2[i][j]; //Adding the two matrix and Storing it
            }
        } 
    }
    
    public static void multiply(int[][] M3, int[][] M4, int[][]totalM){
        int x = 0; //Temporary storage of the operation
        for(int i = 0; i < M3.length; i++){
                for(int k = 0 ; k< M4[0].length; k++){
                    for(int j = 0; j < M4.length; j++){
                        x = M3[i][j] * M4 [j][k] + x;
                    }
                    totalM[i][k] = x; //Storing the result in the resultant matrix
                    x = 0; //Setting the temp. storage to zero for another use
                }
        }
    }
    
    public static void multiplyByConstant(int[][] Matrix, int constant, int[][] total) {
        for (int i = 0; i < Matrix.length; i++) {
            for (int j = 0; j < Matrix[i].length; j++) {
                total[i][j] = Matrix[i][j] * constant; // Multiplying each element by constant
            }
        }
    }
    
    public static void newmultiplyByConstant(int[][] Matrix, int[][] ProTotal) {
    System.out.println("Matrix");
    for (int i = 0; i < Matrix.length; i++) {
        System.out.print("| ");
        for (int j = 0; j < Matrix[i].length; j++) {
            System.out.print(Matrix[i][j] + " ");
        }
        System.out.println("|");
    } // Outputs the Matrix

    System.out.println(" ");
    System.out.println("Result after constant multiplication");
    for (int i = 0; i < ProTotal.length; i++) {
        System.out.print("| ");
        for (int j = 0; j < ProTotal[i].length; j++) {
            System.out.print(ProTotal[i][j] + " ");
        }
        System.out.println("|");
    } // Outputs the Matrix after constant multiplication
    System.out.println(" ");
    }
    
    public static int[][] diagonalize(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        if (rows != columns) {
            throw new IllegalArgumentException("Input matrix must be square to be diagonalized.");
        }

        int[][] diagonalMatrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == j) {
                    diagonalMatrix[i][j] = 0; // Diagonal elements becomes zero
                    //diagonalMatrix[i][j] = 1; // Diagonal elements have the same values (necessary if you want to create scalar matrix)
                    //diagonalMatrix[i][j] = matrix[i][j]; //Diagonal elements remain the same
                } else {
                    //diagonalMatrix[i][j] = 0; // Off-diagonal elements become zero (it can be change to "1")
                    diagonalMatrix[i][j] = matrix[i][j]; // Off-diagonal elements remain the same //it also becomes scalar matrix if uncommented
                }
            }
           
        }
        return diagonalMatrix;
    }
    
    public static void showDiagonalMatrix(int[][] originalMatrix, int[][] diagonalMatrix) {
        System.out.println("Original Matrix:");
        printMatrix(originalMatrix);
        System.out.println("Diagonalized Matrix:");
        printMatrix(diagonalMatrix);
    }
    
}
/**
 * ALGORITHM
 * 
 * Multiplication: (Product of two matrices stored on Mtotal)
 * total <- 0 //temporary storage
 * for i <- 0 to M1.length do{
 *     for j <- 0 to M2[0].length do{
 *         for k <- 0 to M2.length do{
 *             total <- total + (M1[i][k]*M2[k][j])
 *         }
 *     Mtotal[i][j] <- total; 
 *     //Storing the product and sum on the new matrix
 *     total <- 0; //returning the value to 0 for future use
 *     }
 *  }
 *  
 * Addition: (Sum of two matrices stored on Mtotal)
 * for i <- 0 to M1.length do{
 *     for j <- 0 to M1[i].length do{
 *         Mtotal[i][j] <- M1[i][j] + M2[i][j];
 *     }
 * }
 * 
 * Multiplication by Constant: (Product of a matrix and a constant stored on Mtotal)
 * for i <- 0 to M.length do{
 *     for j <- 0 to M[i].length do{
 *         Mtotal[i][j] <- M[i][j] * constant;
 *     }
 * }
 * 
 * Diagonalization: (Creating a diagonal matrix from a given array)
 * for i <- 0 to arr.length do{
 *     for j <- 0 to arr.length do{
 *         if i == j then
 *             Mtotal[i][j] <- arr[i];
 *         else
 *             Mtotal[i][j] <- 0; // Non-diagonal elements are 0
 *     }
 * }
 */
