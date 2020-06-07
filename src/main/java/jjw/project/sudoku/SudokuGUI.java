package jjw.project.sudoku;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import lombok.*;

public class SudokuGUI extends JInternalFrame  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static 	Solver solver = new Solver();
	private static final int MAX_ROWS = 9;
	private static final int MAX_COLUMNS = 9;
	private static Font bold = new Font("Arial", Font.BOLD, 50);
	private static Font plain = new Font("Arial", Font.PLAIN, 50);
	@Getter
	private int[][] board = {	{0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0}};
	@Getter
	JTextField[] components = new JTextField[81];
	
	public SudokuGUI() {
		
		Dimension size = new Dimension(900,900);
		getContentPane().setLayout(new GridLayout(MAX_ROWS, MAX_COLUMNS));
		setSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
		setResizable(false);
		int a = 0;
		for(int row = 0; row < MAX_ROWS; row++) 
			for(int column = 0; column < MAX_COLUMNS; column++) {
				components[a] = new NumberDisplay(board[row][column]);
				getContentPane().add(components[a]);
				a++;
			}
		SectionDrawer glass = new SectionDrawer();
		setGlassPane(glass);
		glass.setVisible(true);
		setVisible(true);

	}

	public void setBoard(int[][] board) {
		this.board = board;
		int counter = 0;
		for(int row = 0; row < MAX_ROWS; row++)
			for(int column = 0;column < MAX_COLUMNS;column++) {
				if(board[row][column] != 0) {
				components[counter].setText(String.valueOf(board[row][column]));
				components[counter].setEditable(false);
				components[counter].setFont(bold);
				}
				counter++;
			}
		
	}
	public int solve(){
		solver.reset();
		updateBoard();
		solver.setBoard(board);
		if(solver.isValid()) {	
			setBoard(solver.nextSolution());
			return 1;
		}
		else {
			setBoard(solver.nextSolution());
			return solver.getSolutionSize();
		}
	}

	private void updateBoard() {
		int counter = 0;
		for(int row = 0; row < MAX_ROWS; row++)
			for(int column = 0;column < MAX_COLUMNS;column++) {
				if(board[row][column] == 0 && !components[counter].getText().equals("")) {
					board[row][column] = Integer.parseInt(components[counter].getText());
				}
				counter++;
			}
	}
	public void reset() {
		int[][] emptyBoard = {	{0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0}};		
		setBoard(emptyBoard);
		solver.reset();
		for(JTextField temp : components) {
			temp.setText("0");
			temp.setEditable(true);
			temp.setFont(plain);
		}
	}

	private class SectionDrawer extends JComponent {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		

		public SectionDrawer() {
			super();
			repaint();		
		}



		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLACK);
			g.drawLine(300, 0, 300, 900);
			g.drawLine(0,300, 900, 300);
			g.drawLine(600, 0, 600, 900);
			g.drawLine(0,600, 900, 600);
		}

	}


}
