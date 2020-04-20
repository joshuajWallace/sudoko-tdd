package jjw.project.sudoku;

import java.util.Arrays;

import lombok.Data;

public @Data class Solver {
	
	private int[][] board;

	public Solver() {
		board = new int[9][9];
		for(int[] temp : board)
			Arrays.fill(temp, 0);
	}
	
	


}
