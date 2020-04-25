package jjw.project.sudoku;

import java.awt.Dimension;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class App {

	public static void main(String[] args) {
		JFrame display = new JFrame();
		display.setResizable(false);
		SudokuGUI puzzle = new SudokuGUI();
		int[][] testPuzzle = {	{0,9,6,0,4,0,0,3,0},
								{0,5,7,8,2,0,0,0,0},
								{1,0,0,9,0,0,5,0,0},
								{0,0,9,0,1,0,0,0,8},
								{5,0,0,0,0,0,0,0,2},
								{4,0,0,0,9,0,6,0,0},
								{0,0,4,0,0,3,0,0,1},
								{0,0,0,0,7,9,2,6,0},
								{0,2,0,0,5,0,9,8,0}};
		
		display.getContentPane().setLayout(new FlowLayout());
		puzzle.setBoard(testPuzzle);
		display.getContentPane().add(puzzle);
		
		JButton solve = new JButton("Solve");
		solve.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				puzzle.solve();
			}
		});
		display.getContentPane().add(solve);
		display.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		display.setSize(new Dimension(920, 1026));
		display.setVisible(true);
	}

}
