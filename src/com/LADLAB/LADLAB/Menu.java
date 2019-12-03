package com.LADLAB.LADLAB;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {
	
	public static int currentChoice = 0;
    public static String[] options = {"Start", "Help", "Quit"};
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	public static boolean g = false;
	
	public Menu() {
		titleColor = Color.ORANGE;
		titleFont = new Font("Century Gothic", Font.PLAIN, 120);
		
		font = new Font("Arial", Font.PLAIN, 50);
	}
	
	public void tick() {
		
	}
	
	public void paint(Graphics g) {
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Domination", 160, 280);
		
		//draw menu options
		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], 430, 380 + i * 60);
	    }
	}
	public static void select() {
		//Selection to start game
		if (currentChoice == 0) {
			Game.GameState = 2;
		}
		if (currentChoice == 1) {
			// help
		}
		if (currentChoice == 2) {
			System.exit(0);
		}
	}

	public void keyReleased(int key) {
		
	}
}
