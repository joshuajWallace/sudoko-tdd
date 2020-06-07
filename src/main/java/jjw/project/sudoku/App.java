package jjw.project.sudoku;

import java.awt.Dimension;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class App {
	private static final JTextPane textField = new JTextPane();

	public static void main(String[] args) {
		JFrame display = new JFrame();
		display.setResizable(false);
		SudokuGUI puzzle = new SudokuGUI();
		
		display.getContentPane().setLayout(new FlowLayout());
		textField.setFont(new Font("Arial", Font.PLAIN, 14));
		textField.setEditable(false);
		textField.setText("Please enter a sudoku to solve!");
		display.getContentPane().add(textField);
		display.getContentPane().add(puzzle);
		
		JButton solve = new JButton("Solve");
		solve.setFont(new Font("Arial", Font.PLAIN, 24));
		solve.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int numberOfSolutions = puzzle.solve();
				if(numberOfSolutions == 1)
					textField.setText("A Valid sudoku with only one solution:");
				else
					textField.setText("Thats not a valid sudoku! There were " + numberOfSolutions + " solutions including:");
			}
		});
		display.getContentPane().add(solve);
		
		JButton reset = new JButton("Reset");
		reset.setFont(new Font("Arial", Font.PLAIN, 24));
		reset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				puzzle.reset();
			}
		});
		display.getContentPane().add(reset);
		display.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		display.setSize(new Dimension(920, 1060));
		display.setVisible(true);
	}

}
