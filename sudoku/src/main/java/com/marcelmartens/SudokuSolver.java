package com.marcelmartens;

public interface SudokuSolver {
    /**
     * Solves the sudoku.
     * 
     * @return true if the sudoku is solveable
     * @throws IllegalArgumentException if the current contains any sudoku
     *                                  rule-breaks
     */
    boolean solve();

    /**
     * Puts digit in the box row, col.
     * 
     * @param row   The row
     * @param col   The column
     * @param digit The digit to insert in box row, col
     * @throws IllegalArgumentException if row, col or digit is outside the range
     *                                  [0..9]
     */
    void set(int row, int col, int digit);

    /**
     * 
     * @param row row to get
     * @param col col to get
     * @return the digit at chosen row and col
     * @throws IllegalArgumentException if row or col is outside the range [0..9]
     */
    int get(int row, int col);

    /**
     * Clears the digit at the chosen row and col by setting it to 0
     * 
     * @param row row to clear
     * @param col col to clear
     * @throws IllegalArgumentException if row or col is outside the range [0..9]
     */
    void clear(int row, int col);

    /**
     * Clears all digits in the sudoku grid by setting them to 0
     */
    void clearAll();

    /**
     * Checks that the digit in the box row, col follows the sudoku rules.
     * 
     * @param row The row of the cell to validate
     * @param col The col of the cell to validate
     * @throws IllegalArgumentException if row, col is outside the range
     *                                  [0..8] and if the cells digit is outside the
     *                                  range [0...9]
     * @return true if the cell follows the sudoku rules, excluding 0
     */
    boolean isValid(int row, int col);

    /**
     * Itterates over every cell in the sudoku grid and checks if the digit is
     * present in
     * any connected col, row or Box. A itterative version of the isValid() method
     * 
     * @throws IllegalArgumentException if row, col is outside the range
     *                                  [0..8] and if the cells digit is outside the
     *                                  range [0...9]
     * @return false if any digit in the grid breaks the sudoku rules, excluding 0
     */
    boolean isAllValid();

    /**
     * Fills the grid with the digits in m. The digit 0 represents an empty box.
     * 
     * @param m the matrix with the digits to insert
     * @throws IllegalArgumentException if m has the wrong dimension or contains
     *                                  values outside the range [0..9]
     */
    void setGrid(int[][] m);

    /**
     * Returns the grid from the current instance of the suduko solver
     * 
     * @return a int[][] representing the sudoku grid
     */
    int[][] getGrid();
}
