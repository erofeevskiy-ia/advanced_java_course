package edu.technopolis.homework;


import java.util.Scanner;

/**
 * Created by Игорь on 17.10.2016.
 */
public class MatrixMultiplication {
    private int [][] matrix;
    public MatrixMultiplication(int row, int col)
    {
        if (row < 1 || col < 1) {
            throw new IllegalArgumentException("row<0 or col<0");
        }
        matrix = new int[row][col];
    }

    public void setElement(int row, int col, int el){
        matrix[row][col]=el;
    }
    public int getElement(int row, int col) {
        return matrix[row][col];
    }

    public int getCountRow() {
        return matrix.length;
    }

    public int getCountCol() {
        return matrix[0].length;
    }

    public void MultiplicationPrint(MatrixMultiplication B){
        if(this.getCountCol()!=B.getCountRow()) {
            throw new IllegalArgumentException("M!=X");
        }
        else {
            for (int i=0;i<this.getCountRow();i++){
                for (int j = 0; j<B.getCountCol(); j++){
                    int sum=0;
                    for (int k=0;k<B.getCountRow();k++)
                    {
                        sum+= this.getElement(i,k)*B.getElement(k,j);

                    }
                    System.out.print(sum+" ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
try {
    MatrixMultiplication A = new MatrixMultiplication(sc.nextInt(), sc.nextInt());//NxM
    MatrixMultiplication B = new MatrixMultiplication(sc.nextInt(), sc.nextInt());//XxY

        for (int i=0;i<A.getCountRow();i++)
            for (int j=0;j<A.getCountCol();j++)
            {
                A.setElement(i,j,sc.nextInt());
            }
        for (int i=0;i<B.getCountRow();i++)
            for (int j=0;j<B.getCountCol();j++)
            {
                B.setElement(i,j,sc.nextInt());
            }

            A.MultiplicationPrint(B);
} catch (Exception err)
{
    System.out.println("Exception: " + err);
}
//3 3 3 2 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6

    }
}


