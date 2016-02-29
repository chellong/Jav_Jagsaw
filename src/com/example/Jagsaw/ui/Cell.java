package com.example.Jagsaw.ui;

import javax.swing.Icon;
import javax.swing.JButton;

public class Cell extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6855105518386897546L;

	public Cell(Icon icon) {
		super(icon);
		this.setSize(125, 125);
	}

	public Cell(String text, Icon icon) {
		super(text, icon);
		this.setSize(125, 125);
		this.setHorizontalTextPosition(CENTER);
		this.setVerticalTextPosition(CENTER);
	}

	public void move(String direction) {
		
		switch (direction) {
		case "UP":
			this.setLocation(this.getBounds().x,this.getBounds().y - 125);
			break;

		case "DOWN":
			this.setLocation(this.getBounds().x,this.getBounds().y + 125);
			break;

		case "LEFT":
			this.setLocation(this.getBounds().x - 125,this.getBounds().y);
			break;

		case "RIGHT":
			this.setLocation(this.getBounds().x + 125,this.getBounds().y );
			break;

		default:
			break;
		}
	
	}
}
