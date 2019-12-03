package com.LADLAB.LADLAB.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.LADLAB.LADLAB.Handler;

import Framework.GameObject;
import Framework.ObjectId;

//BLOCK CLASS
public class Block extends GameObject {
	
	private Handler handler;

	public Block(float x, float y, ObjectId id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

    //updates blocks
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

	//paints blocks
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect((int)x, (int)y, 32, 32);
	}

	//returns hollow rectangle that describes boundary
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}
