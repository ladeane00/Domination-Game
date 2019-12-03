package com.LADLAB.LADLAB.Objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.LADLAB.LADLAB.Handler;

public class ScoreBoard {
	
	Handler handler;
	public static int blueScore;
	public static int redScore;

	public ScoreBoard(Handler handler) {
		this.handler = handler;
		blueScore = 0;
		redScore = 0;
	}

	public void tick() {

	}

	//paints scoreboard
	public void paint(Graphics g) {
		g.setFont(new Font("Century Gothic", Font.PLAIN, 40));
		//blue
		g.setColor(Color.BLUE);
		g.drawString("BLUE: ", 70, 130);
		g.setColor(Color.WHITE);
		g.drawString("" + blueScore, 180, 130);
		//red
		g.setColor(Color.RED);
		g.drawString("RED: ", 70 + 690, 130);
		g.setColor(Color.WHITE);
		g.drawString("" + redScore, 180 + 670, 130);
	}

	public Rectangle getBounds() {
		return null;
	}
	
	

}
