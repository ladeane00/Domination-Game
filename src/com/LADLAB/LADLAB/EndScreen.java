package com.LADLAB.LADLAB;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.LADLAB.LADLAB.Objects.Player;
import com.LADLAB.LADLAB.Objects.Player2;
import com.LADLAB.LADLAB.Objects.ScoreBoard;

public class EndScreen {
	
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	
	//displays endscreen
	public EndScreen() {
		titleFont = new Font("Century Gothic", Font.PLAIN, 120);
	}
	
	public void tick() {
		
	}
	
	//paints to screen
	public void paint(Graphics g) {
		g.setFont(titleFont);
		if (Player.healthBarHealth <= 0 || ScoreBoard.redScore >= 42) {
			g.setColor(Color.RED);
		    g.drawString("RED WINS", 200, 280);
		}
		if (Player2.healthBarHealth <= 0 || ScoreBoard.blueScore >= 42) {
			g.setColor(Color.BLUE);
			g.drawString("BLUE WINS", 200, 280);
		}
	}
}
