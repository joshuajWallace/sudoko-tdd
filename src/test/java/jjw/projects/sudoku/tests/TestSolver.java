package jjw.projects.sudoku.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jjw.project.sudoku.Solver;

class TestSolver {
	private static int[][] testBoard ;
	private static int[][] badTestBoard;
	private Solver testSolver = new Solver();
	
	
	@BeforeEach
	void setup() {			
	int[][] testPuzzle = {	{0,9,6,0,4,0,0,3,0},
							{0,5,7,8,2,0,0,0,0},
							{1,0,0,9,0,0,5,0,0},
							{0,0,9,0,1,0,0,0,8},
							{5,0,0,0,0,0,0,0,2},
							{4,0,0,0,9,0,6,0,0},
							{0,0,4,0,0,3,0,0,1},
							{0,0,0,0,7,9,2,6,0},
							{0,2,0,0,5,0,9,8,0}};
	testBoard = testPuzzle;
	int[][] invalidSudokuTestPuzzle = {	{5,3,0,0,7,0,0,0,0},
										{6,0,0,1,9,5,0,0,0},
										{0,9,8,0,0,0,0,6,0},
										{8,0,0,0,6,0,0,0,3},
										{4,0,0,8,0,3,0,0,1},
										{7,0,0,0,2,0,0,0,6},
										{0,6,0,0,0,0,2,8,0},
										{0,0,0,4,1,9,0,0,5},
										{0,0,0,0,8,0,0,0,0}};
	badTestBoard = invalidSudokuTestPuzzle;
	
	
	}

	@Test
	void testBoardExists() {
		int[][] emptyTestBoard = {	{0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0}};
		
		assertTrue(Arrays.deepEquals(emptyTestBoard, testSolver.getBoard()));	
				
	}
	@Test
	void testCheckRow() {
		testSolver.setBoard(testBoard);
		assertFalse(testSolver.checkRow(0 , 2));
		assertTrue(testSolver.checkRow(0, 9));
	}
	@Test
	void testCheckColumn() {
		testSolver.setBoard(testBoard);
		assertFalse(testSolver.checkColumn(3 , 1));
		assertTrue(testSolver.checkColumn(0, 1));
	}
	@Test
	void testCheckSection() {
		testSolver.setBoard(testBoard);
		assertFalse(testSolver.checkSection(0, 3, 1));
		assertTrue(testSolver.checkSection(0, 0, 5));
		assertTrue(testSolver.checkSection(2,5,4));
	}

	
	@Test
	void testCheckSolution() {
		testSolver.setBoard(testBoard);
		int[][] solution = {{2,9,6,1,4,5,8,3,7},
							{3,5,7,8,2,6,1,4,9},
							{1,4,8,9,3,7,5,2,6},
							{6,3,9,5,1,2,4,7,8},
							{5,8,1,7,6,4,3,9,2},
							{4,7,2,3,9,8,6,1,5},
							{9,6,4,2,8,3,7,5,1},
							{8,1,5,4,7,9,2,6,3},
							{7,2,3,6,5,1,9,8,4}};
		testSolver.solve();
		int[][] testSolution = testSolver.nextSolution();
		assertArrayEquals(solution, testSolution, "Nope");
	}
	@Test
	void moreThenOneSolution(){
	
	int[][] solutionOne = { 	{5,3,4,6,7,8,1,9,2},
								{6,7,2,1,9,5,3,4,8},
								{1,9,8,3,4,2,5,6,7},
								{8,5,9,7,6,1,4,2,3},
								{4,2,6,8,5,3,9,7,1},
								{7,1,3,9,2,4,8,5,6},
								{9,6,1,5,3,7,2,8,4},
								{2,8,7,4,1,9,6,3,5},
								{3,4,5,2,8,6,7,1,9}};
	
	
	int[][] solutionTwo = {		{5,3,4,6,7,8,9,1,2},
								{6,7,2,1,9,5,3,4,8},
								{1,9,8,3,4,2,5,6,7},
								{8,5,9,7,6,1,4,2,3},
								{4,2,6,8,5,3,7,9,1},
								{7,1,3,9,2,4,8,5,6},
								{9,6,1,5,3,7,2,8,4},
								{2,8,7,4,1,9,6,3,5},
								{3,4,5,2,8,6,1,7,9}};
			
	
	testSolver.setBoard(badTestBoard);
	testSolver.solve();
	assertArrayEquals(solutionOne, testSolver.nextSolution(), "One Fail");
	assertArrayEquals(solutionTwo, testSolver.nextSolution(), "Two Fail");	
		
	}
	
	@Test
	void testValidSudoku() {
		testSolver.setBoard(testBoard);
		assertTrue(testSolver.isValid());
		testSolver.setBoard(badTestBoard);
		assertFalse(testSolver.isValid());
	}
	
	


}
