package jjw.project.sudoku;

import java.util.Arrays;
import java.util.List;

import lombok.Data;

public @Data class Solver {
	
	private  int[][] board= new int[9][9];
	private int[][] solution = new int[9][9];

	public Solver() { 
		for(int[] temp : board)
			Arrays.fill(temp, 0);
	}


	public boolean checkRow(int row, int value) {
		for(int i = 0; i<9; i++) {
			if(board[row][i] == value)
				return true;
		}
		return false;
	}

	public Boolean checkColumn(int column, int value) {
		for(int rowPosition = 0; rowPosition<9; rowPosition++) {
			if(board[rowPosition][column] == value)
				return true;
		}
		return false;
	}
	
	public Boolean checkSection(int row, int column, int value) {
		int sectionStartrow = ((row)/3)*3;
		int sectionStartColumn = ((column)/3)*3;
		for(int rowPosition = 0; rowPosition < 3;rowPosition++) {
			for(int columnPosition = 0; columnPosition < 3;columnPosition++)
				if(board[sectionStartrow + rowPosition][sectionStartColumn + columnPosition] == value)
					return true;						
		}
		return false;
	}

	public int[][] solution() {
		solve();
		return solution;
	}


	private void solve() {
		for(int rowPosition = 0; rowPosition < 9; rowPosition ++) 
			for(int columnPosition = 0; columnPosition < 9; columnPosition++) 
				if(board[rowPosition][columnPosition] == 0) {
					for(int value = 1; value < 10; value++) 
						if(possible(rowPosition, columnPosition, value) ) {
							board[rowPosition][columnPosition] = value;
							solve();
							board[rowPosition][columnPosition] = 0;
													
						}
					return;
				}
		
		for(int rowPosition = 0; rowPosition < 9; rowPosition ++) 
			for(int columnPosition = 0; columnPosition < 9; columnPosition++)
				solution[rowPosition][columnPosition] = board[rowPosition][columnPosition];

	}


	private boolean possible(int rowPosition, int columnPosition, int value) {
		return (!checkRow(rowPosition, value) && !checkColumn(columnPosition, value) && !checkSection(rowPosition, columnPosition, value));
	}



	
	


}
