package jjw.projects.sudoku.tests;

import org.junit.jupiter.api.*;

import jjw.project.sudoku.SudokuGUI;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

class TestGui {
	SudokuGUI tester;
	final int ROWS = 9;
	final int COLUMNS = 9;
	
	@BeforeEach
	void setup() {
		tester = new SudokuGUI();
	}
	
	@Test
	void testDisplay() {

		assertTrue(tester != null);
	}
	
	@Test
	void testHasBoard(){
		assertTrue(tester.getBoard() != null);
	}
	

}
