package com.marcelmartens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SudokuGUI extends JFrame {
    private JTextField[][] grid;
    private JButton solveButton, clearButton;
    private SudokuSolver solver;

    public SudokuGUI(SudokuSolver solver) {
        this.solver = solver;
        initUI();
    }

    private void initUI() {
        setTitle("Sudoku Solver");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        grid = new JTextField[9][9];
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 9));
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                grid[row][col] = new JTextField();
                grid[row][col].setHorizontalAlignment(JTextField.CENTER);
                grid[row][col].addComponentListener(new FontSizeAdjuster());
                if ((row / 3 + col / 3) % 2 == 0) {
                    grid[row][col].setBackground(new Color(240, 240, 240));
                } else {
                    grid[row][col].setBackground(new Color(200, 200, 200));
                }
                panel.add(grid[row][col]);
            }
        }
        add(panel, BorderLayout.CENTER);

        solveButton = new JButton("Solve");
        clearButton = new JButton("Clear");

        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveSudoku();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearGrid();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void solveSudoku() {
        try {
            setSolverGrid();
            if (solver.solve()) {
                getSolverGrid();
            } else {
                JOptionPane.showMessageDialog(this, "No solution found.", "Sudoku Solver", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setSolverGrid() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                String text = grid[row][col].getText().trim();
                int value = text.isEmpty() ? 0 : Integer.parseInt(text);
                solver.set(row, col, value);
            }
        }
    }

    private void getSolverGrid() {
        int[][] solvedGrid = solver.getGrid();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                grid[row][col].setText(solvedGrid[row][col] == 0 ? "" : String.valueOf(solvedGrid[row][col]));
            }
        }
    }

    private void clearGrid() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                grid[row][col].setText("");
            }
        }
    }

    public static void main(String[] args) {
        SudokuSolver solver = new Solver();
        new SudokuGUI(solver);
    }

    private class FontSizeAdjuster extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            JTextField textField = (JTextField) e.getComponent();
            scaleFont(textField);
        }

        private void scaleFont(JTextField textField) {
            float size = textField.getHeight() * 0.75f; // Adjust the factor as needed
            textField.setFont(textField.getFont().deriveFont(size));
        }
    }
}
