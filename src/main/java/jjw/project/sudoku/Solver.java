package jjw.project.sudoku;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BooleanSupplier;

import lombok.Data;

public class Solver {
	private  int[][] board= new int[9][9];
	private List<int[][]> solution = new CopyOnWriteArrayList<>();
	int solutionIndex = 0;
	
	public int[][] getBoard() {
		return board;
	}


	public void setBoard(int[][] board) {
		this.board = board;
	}




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

	public int[][] nextSolution() {
		int [][] currentSolution = solution.get(solutionIndex);
		solutionIndex++;
		if(solutionIndex == solution.size()) {
			solutionIndex = 0;
		}
		return currentSolution;

	}


	public void solve() {
		for(int rowPosition = 0; rowPosition < 9; rowPosition ++) {
			for(int columnPosition = 0; columnPosition < 9; columnPosition++) { 
				if(board[rowPosition][columnPosition] == 0) {
					for(int value = 1; value < 10; value++) 
						if(possible(rowPosition, columnPosition, value) ) {
							board[rowPosition][columnPosition] = value;
							solve();
							board[rowPosition][columnPosition] = 0;							
						}
					
					return;
				}
			}
		}
		int[][] tempSolution = new int[9][];
		int i = 0;
		for(int[] temp: board) {

			tempSolution[i] = Arrays.copyOf(temp, 9);
			i++;
		}
		solution.add(tempSolution);
	}


	private boolean possible(int rowPosition, int columnPosition, int value) {
		return (!checkRow(rowPosition, value) && !checkColumn(columnPosition, value) && !checkSection(rowPosition, columnPosition, value));
	}


	public boolean isValid() {
		solve();
		return (solution.size() == 1);
		
	}



	
	


}
