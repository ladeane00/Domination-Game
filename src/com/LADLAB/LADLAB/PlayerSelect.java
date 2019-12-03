package com.LADLAB.LADLAB;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.LADLAB.LADLAB.Objects.Player2;

public class PlayerSelect {
	
	public static int currentChoice1 = 0;
    public static String[] options = {"Player", "CPU"};
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	
	public PlayerSelect() {
		titleColor = Color.ORANGE;
		titleFont = new Font("Century Gothic", Font.PLAIN, 120);
		
		font = new Font("Arial", Font.PLAIN, 50);
	}
	
	public void tick() {
		
	}
	
	public void paint(Graphics g) {
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Player Select", 120, 280);
		//draw menu options
		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice1) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], 420, 380 + i * 60);
	    }
	}
	//game mode selection
	public static void select1() {
		if (currentChoice1 == 0) {
			Game.GameState = 3;
		}
		if (currentChoice1 == 1) {
		    Game.GameState = 3;
		    Player2.CPUOn = true;
		}
	}

	public void keyReleased(int key) {
		
	}

}
