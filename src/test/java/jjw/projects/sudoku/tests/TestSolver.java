package jjw.projects.sudoku.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import jjw.project.sudoku.Solver;

class TestSolver {

	@Test
	void testBoardExists() {
		int[][] testBoard = {{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},};
		Solver testSolver = new Solver();
		
		assertTrue(Arrays.deepEquals(testBoard, testSolver.getBoard()));	
				
	}

}
