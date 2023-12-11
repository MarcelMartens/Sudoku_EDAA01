package com.marcelmartens;

public class temp {
    public static void main(String[] args) {
        Solver solver = new Solver();
        int[][] grid = new int[][] { { 5, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 5 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
        solver.setGrid(grid);
        System.out.println(solver.isAllValid());
        for (int i = 0; i < 9; i++) {
            System.out.println();
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j]);
            }
        }

    }

    // public int[] getRow(int rowIndex) {
    // validatePosition(rowIndex, 0); // Validate the row index, column index is
    // arbitrary here
    // return grid[rowIndex];
    // }

    // public int[] getCol(int colIndex) {
    // validatePosition(0, colIndex);

    // int[] col = new int[grid.length]; // Assuming grid is square, length is 9
    // for (int i = 0; i < grid.length; i++) {
    // col[i] = grid[i][colIndex];
    // }
    // return col;
    // }

    // public int[][] getBox(int row, int col) {
    // validatePosition(row, col);

    // int[][] square = new int[3][3];
    // int startRow = (row / 3) * 3;
    // int startCol = (col / 3) * 3;

    // for (int i = 0; i < 3; i++) {
    // for (int j = 0; j < 3; j++) {
    // square[i][j] = grid[startRow + i][startCol + j];
    // }
    // }

    // return square;
    // }

}
