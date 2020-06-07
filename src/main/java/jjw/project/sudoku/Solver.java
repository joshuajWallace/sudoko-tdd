package jjw.project.sudoku;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class Solver {
	private  int[][] board= new int[9][9];
	private List<int[][]> solution;
	private int timeout = 2000;
	private int solutionIndex ;
	
	public int[][] getBoard() {
		return board;
	}


	public void setBoard(int[][] board) {
		this.board = board;
	}




	public Solver() { 
		reset();
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
	
	// Using integer math.
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

	public List<int[][]> getSolution() {
		return solution;
	}


	public void solve(long time) {
		for(int rowPosition = 0; rowPosition < 9; rowPosition ++) {
			for(int columnPosition = 0; columnPosition < 9; columnPosition++) { 
				if(board[rowPosition][columnPosition] == 0) {
					for(int value = 1; value < 10; value++) 
						if(possible(rowPosition, columnPosition, value) ) {
							board[rowPosition][columnPosition] = value;
							if((System.currentTimeMillis()- time) > timeout) {
								return;
							}
							solve(time);
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
		if(checkValidColumns() && checkValidRows() && checkValidSections()) {
			solve(System.currentTimeMillis());
			return (solution.size() == 1);
		}
		return false;
	}
	// working on.set.add(grid[r][c])
	private boolean checkValidColumns() {
		for(int column = 0; column<9;column++) {
			Set<Integer> set = new HashSet<>();
			for(int row = 0; row<9;row++) {
				if(board[row][column] != 0 && !set.add(board[row][column]))
					return false;
				}
		}
		return true;
	}
	private boolean checkValidRows() {
		for(int row = 0; row<9;row++) {
			Set<Integer> set = new HashSet<>();
			for(int column = 0; column<9;column++) {
				if(board[row][column] != 0 && !set.add(board[row][column]))
					return false;
			}
		}
		return true;
	}
	private boolean checkValidSections() {	
		for(int section = 0; section <9; section+=3) {
			Set<Integer>set = new HashSet<>();
			for(int row = section; row < section+3;row++)
				for(int column = section; column < section+3;column++)
					if(board[row][column] != 0 && !set.add(board[row][column])) {
						return false;
					}
		}
		return true;
	}


	public void reset() {
		for(int[] temp : board)
			Arrays.fill(temp, 0);
		solutionIndex = 0;
		solution = new CopyOnWriteArrayList<>();		
		
	}


	public int getSolutionSize() {
		return solution.size();
	}



	
	


}
