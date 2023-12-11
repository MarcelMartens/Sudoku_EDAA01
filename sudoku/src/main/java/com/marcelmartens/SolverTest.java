package com.marcelmartens;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SolverTest {
    private SudokuSolver solver;

    @BeforeEach
    public void setUp() {
        solver = new Solver();
    }

    @AfterEach
    public void tearDown() {
        solver = null;
    }

    @Test
    public void testSolveEmptySudoku() {
        solver.clearAll();
        assertTrue(solver.solve(), "Empty Sudoku should be solvable");
    }

    @Test
    public void testSolveSpecificSudoku() {
        int[][] sudokuGridFromFigure1 = {
                // Define the Sudoku grid from figure 1 here
        };
        solver.setGrid(sudokuGridFromFigure1);
        assertTrue(solver.solve(), "Sudoku from figure 1 should be solvable");
    }

    @Test
    public void testUnsolvableSudoku() {
        int[][] unsolvableSudoku = {
                // Define an unsolvable Sudoku grid here
        };
        solver.setGrid(unsolvableSudoku);
        assertFalse(solver.solve(), "Unsolvable Sudoku should not be solvable");
    }

    @Test
    public void testInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            solver.set(0, 0, 10); // Invalid digit
        });
    }

    @Test
    public void testValidSetAndGet() {
        solver.set(0, 0, 5);
        assertEquals(5, solver.get(0, 0), "Get should return what was set");
    }

    @Test
    public void testClear() {
        solver.set(0, 0, 5);
        solver.clear(0, 0);
        assertEquals(0, solver.get(0, 0), "Clear should reset the cell");
    }

    @Test
    public void testClearAll() {
        solver.set(0, 0, 5);
        solver.clearAll();
        assertEquals(0, solver.get(0, 0), "ClearAll should reset the entire grid");
    }

    // Additional tests can be added here to cover more cases and methods
}
