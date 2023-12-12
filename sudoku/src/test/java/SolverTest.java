
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import com.marcelmartens.*;

public class SolverTest {
    private SudokuSolver solver;
    private int[][] solvableTestGrid = { { 0, 0, 8, 0, 0, 9, 0, 6, 2 }, { 0, 0, 0, 0, 0, 0, 0, 0, 5 },
            { 1, 0, 2, 5, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 2, 1, 0, 0, 9, 0 }, { 0, 5, 0, 0, 0, 0, 6, 0, 0 }, { 6, 0, 0, 0, 0, 0, 0, 2, 8 },
            { 4, 1, 0, 6, 0, 8, 0, 0, 0 }, { 8, 6, 0, 0, 3, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 4, 0, 0 } };
    private int[][] solvableTestGridSollution = { { 5, 4, 8, 1, 7, 9, 3, 6, 2 }, { 3, 7, 6, 8, 2, 4, 9, 1, 5 },
            { 1, 9, 2, 5, 6, 3, 8, 7, 4 }, { 7, 8, 4, 2, 1, 6, 5, 9, 3 }, { 2, 5, 9, 3, 8, 7, 6, 4, 1 },
            { 6, 3, 1, 9, 4, 5, 7, 2, 8 }, { 4, 1, 5, 6, 9, 8, 2, 3, 7 }, { 8, 6, 7, 4, 3, 2, 1, 5, 9 },
            { 9, 2, 3, 7, 5, 1, 4, 8, 6 } };
    private int[][] unsolvableTestGridHard = { { 5, 1, 6, 8, 4, 9, 7, 3, 2 }, { 3, 0, 7, 6, 0, 5, 0, 0, 0 },
            { 8, 0, 9, 7, 0, 0, 0, 6, 5 }, { 1, 3, 5, 0, 6, 0, 9, 0, 7 }, { 4, 7, 2, 5, 9, 1, 0, 0, 6 },
            { 9, 6, 8, 3, 7, 0, 0, 5, 0 }, { 2, 5, 3, 1, 8, 6, 0, 7, 4 }, { 6, 8, 4, 2, 0, 7, 5, 0, 0 },
            { 7, 9, 1, 0, 5, 0, 6, 0, 8 } };
    private int[][] unsolvableTestGridCol = { { 0, 0, 0, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 1, 0, 0, 0, 0 } };
    private int[][] unsolvableTestGridRow = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
    private int[][] unsolvableTestGridBox = { { 1, 0, 0, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 1, 0, 0, 0, 0 } };
    private int[][] emptyTestGrid = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

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
        solver.setGrid(solvableTestGrid);
        assertTrue(solver.solve(), "Predefined solvable Sudoku from should be solvable");
        assertArrayEquals(solver.getGrid(), solvableTestGridSollution,
                "Solved sudoku should match the predefined solution");
    }

    @Test
    public void testUnsolvableSudoku() {
        solver.setGrid(unsolvableTestGridHard);
        assertFalse(solver.solve(), "Unsolvable Sudoku should not be solvable");
        solver.setGrid(unsolvableTestGridCol);
        assertThrows(IllegalArgumentException.class, () -> solver.solve(),
                "Solve() should throw an IllegalArgumentException when used on grid with rule-breaks");
        solver.setGrid(unsolvableTestGridRow);
        assertThrows(IllegalArgumentException.class, () -> solver.solve(),
                "Solve() should throw an IllegalArgumentException when used on grid with rule-breaks");
        solver.setGrid(unsolvableTestGridBox);
        assertThrows(IllegalArgumentException.class, () -> solver.solve(),
                "Solve() should throw an IllegalArgumentException when used on grid with rule-breaks");
    }

    @Test
    public void testInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> solver.set(0, 0, 10),
                "Setting digits outside range [0..9] should throw an IllegalArgumentException");
        assertThrows(IllegalArgumentException.class, () -> solver.set(9, 0, 0),
                "Setting digits outside range [0..9] should throw an IllegalArgumentException");
        assertThrows(IllegalArgumentException.class, () -> solver.set(0, 9, 0),
                "Setting digits outside range [0..9] should throw an IllegalArgumentException");
    }

    @Test
    public void testValidSetAndGet() {
        assertEquals(0, solver.get(0, 0), "Get on empty grid should return 0");
        solver.set(0, 0, 5);
        assertEquals(5, solver.get(0, 0), "Get should return what was on the same index set");
    }

    @Test
    public void testClear() {
        solver.set(0, 0, 5);
        solver.clear(0, 0);
        assertEquals(0, solver.get(0, 0), "Clear should reset the cell");
    }

    @Test
    public void testClearAll() {
        solver.clearAll();
        assertArrayEquals(emptyTestGrid, solver.getGrid(), "ClearAll should work even on empty grids");
        solver.setGrid(unsolvableTestGridHard);
        solver.clearAll();
        assertArrayEquals(emptyTestGrid, solver.getGrid(), "ClearAll should reset and fill the entire grid with zeros");
    }

    @Test
    public void testValid() {
        assertTrue(solver.isAllValid(), "isAllValid should return true on empty grid");
        assertTrue(solver.isValid(0, 0), "isValid should return true on empty cell");
        solver.setGrid(unsolvableTestGridCol);
        assertFalse(solver.isAllValid(),
                "isAllValid should return false when any col contains multiple of the same digits");
        assertFalse(solver.isValid(0, 4),
                "isValid should return false when any connected col contains same digit as cell");
        solver.setGrid(unsolvableTestGridRow);
        assertFalse(solver.isAllValid(),
                "isAllValid should return false when any row contains multiple of the same digits");
        assertFalse(solver.isValid(4, 0),
                "isValid should return false when any connected row contains same digit as cell");
        solver.setGrid(unsolvableTestGridBox);
        assertFalse(solver.isAllValid(),
                "isAllValid should return false when any box contains multiple of the same digits");
        assertFalse(solver.isValid(0, 0), "isValid should return false when box contains multiple of the same digit");

    }
}
