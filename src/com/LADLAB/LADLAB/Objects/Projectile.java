package com.LADLAB.LADLAB.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import Framework.GameObject;
import Framework.ObjectId;

//PROJECTILE CLASS
public class Projectile extends GameObject {

	public Projectile(float x, float y, ObjectId id, int velX) {
		super(x, y, id);
		this.velX = velX;
	}

	//updates
	public void tick(LinkedList<GameObject> object) {
		x += velX;
	}

	//paints projectile
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 15, 3);
	}

	//returns hollow rectangle representing projectile boundaries
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 15, 3);
	}
	
	

}
