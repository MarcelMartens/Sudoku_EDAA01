package com.marcelmartens;

public class Solver implements SudokuSolver {
    private int[][] grid;

    public Solver() {
        grid = new int[9][9];
    }

    @Override
    public boolean solve() {
        if (!isAllValid()) {
            // todo tell user what grid(s) are wrong
            throw new IllegalArgumentException("Current grid is unsolvable!");
        }
        return solveRecursive(0, 0);
    }

    private boolean solveRecursive(int row, int col) {
        // If we have reached the 9th row, the Sudoku is solved
        if (row == 9) {
            return true;
        }

        // Move to the next row if we have reached the 9th column
        if (col == 9) {
            return solveRecursive(row + 1, 0);
        }

        // Skip already filled cells
        if (grid[row][col] != 0) {
            return solveRecursive(row, col + 1);
        }

        for (int num = 1; num <= 9; num++) {
            if (isValidPlacement(row, col, num)) {
                grid[row][col] = num;

                if (solveRecursive(row, col + 1)) {
                    return true;
                }
                grid[row][col] = 0;
            }
        }

        return false;
    }

    @Override
    public void set(int row, int col, int digit) {
        validatePosition(row, col);
        validateDigit(digit);
        grid[row][col] = digit;
    }

    @Override
    public int get(int row, int col) {
        validatePosition(row, col);
        return grid[row][col];
    }

    @Override
    public void setGrid(int[][] m) {
        if (m.length != 9 || m[0].length != 9) {
            throw new IllegalArgumentException("Grid must be 9x9");
        }
        this.grid = m;
    }

    @Override
    public int[][] getGrid() {
        return grid;
    }

    @Override
    public void clear(int row, int col) {
        validatePosition(row, col);
        grid[row][col] = 0;
    }

    @Override
    public void clearAll() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                grid[row][col] = 0;
            }
        }
    }

    @Override
    public boolean isValid(int row, int col) {
        validatePosition(row, col);
        validateDigit(grid[row][col]);
        if (isValidPlacement(row, col, grid[row][col])) {
            return true;
        }
        return false;
    }

    // change Defaulting to returning true is maybe not the best practice :)
    /// Need to change solver() and recursiveSolver()
    @Override
    public boolean isAllValid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                validatePosition(i, j);
                validateDigit(grid[i][j]);
                if (!isValidPlacement(i, j, grid[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    // change move check for 0 here if smaller methods are not used
    private boolean isValidPlacement(int row, int col, int digit) {
        return (!isInRow(row, col, digit) && !isInCol(row, col, digit) && !isInBox(row, col, digit));
    }

    private boolean isInRow(int row, int col, int digit) {
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == digit && digit != 0 && i != col)
                return true;
        }
        return false;
    }

    private boolean isInCol(int row, int col, int digit) {
        for (int i = 0; i < 9; i++) {
            if (grid[i][col] == digit && digit != 0 && i != row)
                return true;
        }
        return false;
    }

    /**
     * 
     * @param row
     * @param col
     * @param digit
     * @return
     */
    private boolean isInBox(int row, int col, int digit) {
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[startRow + i][startCol + j] == digit && digit != 0 && startRow + i != row
                        && startCol + j != col) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 
     * @param row row number to validate
     * @param col column number to validate
     * @throws IllegalArgumentException if row, column or digit is out of range
     */
    private boolean validatePosition(int row, int col) {
        if (row < 0 || row >= 9 || col < 0 || col >= 9) {
            throw new IllegalArgumentException("Row or column is outside the range [0..8]");
        }
        return true;
    }

    /**
     * 
     * @param digit digit to validate
     * @throws IllegalArgumentException if row, column or digit is out of range
     */
    private boolean validateDigit(int digit) {
        if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException("Digit is outside the range [0..9]");
        }
        return true;
    }

}
