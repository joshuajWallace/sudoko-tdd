package jjw.project.sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumberDisplay extends JTextField {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NumberDisplay(int number) {
		setFont(new Font("Arial", Font.PLAIN, 50));
		setMaximumSize(new Dimension(100,100));
		setMinimumSize(new Dimension(100,100));
		setPreferredSize(new Dimension(100,100));				
		setBackground(Color.white);
		setBorder(BorderFactory.createLineBorder(Color.lightGray));
		setHorizontalAlignment(CENTER);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setText("");				
			}
		});
		if(number == 0)
			setText("");
		else
			setText(String.valueOf(number));
		AbstractDocument document = (AbstractDocument)getDocument();
		final int MAX_NUMBERS = 1;
		document.setDocumentFilter(new DocumentFilter() {
			@Override
			public void replace(FilterBypass filterBypass, int offset, int length,
					String str, AttributeSet attributes) 
							throws BadLocationException {
				String text = str;
				if((filterBypass.getDocument().getLength() + str.length() - length) <= MAX_NUMBERS
						&& text.matches("[0-9]|^$")) {
					super.replace(filterBypass, offset, length, text, attributes);
				}
				else
					Toolkit.getDefaultToolkit().beep();
				
			}
			
			@Override
			public void insertString(FilterBypass filterBypass, int offset,
					String string, AttributeSet attributes)
							throws BadLocationException {
				String text = filterBypass.getDocument().getText(0, filterBypass.getDocument().getLength());
				text += string;
				if((filterBypass.getDocument().getLength() + string.length()) <= MAX_NUMBERS
						&& text.matches("[1-9]")) {
				super.insertString(filterBypass, offset, string, attributes);
				}
				else
					Toolkit.getDefaultToolkit().beep();
			}
		});
	}
	@Override
	public void setText(String str) {
		super.setText(str);
		if(str == "0")
			setText("");
	}
		
		
}
