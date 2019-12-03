package com.LADLAB.LADLAB.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.LADLAB.LADLAB.Handler;

import Framework.GameObject;
import Framework.ObjectId;

//COLORED BLOCK CLASS
public class ColorBlock extends GameObject {
	
	private Handler handler;

	public ColorBlock(float x, float y, ObjectId id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		color = Color.WHITE;
	}

	//updates colored block
	public void tick(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ObjectId.Projectile) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.object.remove(tempObject);
				}
			}
		}
	}

	//paints colored block
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x, (int)y, 32, 32);
	}

	//returns hollow rectangle representing boundaries
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
}
