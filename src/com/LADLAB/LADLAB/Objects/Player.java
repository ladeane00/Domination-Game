package com.LADLAB.LADLAB.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.LADLAB.LADLAB.Handler;

import Framework.GameObject;
import Framework.KeyInput;
import Framework.ObjectId;

//PLAYER CLASS
public class Player extends GameObject {
	
	private float width = 32, height = 64;
	private float gravity = 0.8f;
	private final float MAX_SPEED = 15f;
	public int speed = 5;
	public static float healthBarHealth;
	public static float magicBarMagic;
	
	private Handler handler;
	public static boolean colorChange;

	public Player(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		facingRight = 1;
		this.handler = handler;
		colorChange = false;
		walkingRight = false;
		walkingLeft = false;
		jumping = true;
		healthBarHealth = 200;
		magicBarMagic = 200;
	}

	//updating info for player
	public void tick(LinkedList<GameObject> object) {
		if (healthBarHealth < 200) {
			healthBarHealth += 0.03f;
			}
		if (magicBarMagic < 200) {
			magicBarMagic += 0.5f;
			}
		x += velX;
		y += velY;
		
		if (falling || jumping) {
			velY += gravity;
			
			if (velY > MAX_SPEED) {
				velY = MAX_SPEED;
			}
		}
		if (KeyInput.right1) {
			velX = speed;
		} else if (KeyInput.left1) {
			velX = -speed;
		} else {
			velX = 0;
		}
		if (shooting && magicBarMagic > 20) {
			magicBarMagic -= 20;
		}
		shooting = false;
		collision(object);
		
		//HEALTH REDUCTION FROM PROJECTILE
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ObjectId.Projectile) {
				if (getBoundsRight().intersects(tempObject.getBounds())) {
					if (healthBarHealth > 0) {
					healthBarHealth -= 8;
					}
					handler.object.remove(tempObject);
				}
			}
		}
	}

	//paints player
	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
		g.setColor(Color.WHITE);
		g.drawRect((int)x, (int)y, (int)width, (int)height);
		
		Graphics2D g2d =(Graphics2D) g;
		/*g2d.draw(getBoundsRight());
		g2d.setColor(Color.GREEN);
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsTop());*/
		g.setColor(Color.WHITE);
		g.drawRect(9 * 32 - (200 + 1) - 21, 20 + 20, 200 + 1, 21);
		g.setColor(Color.GREEN);
		g.fillRect(9 * 32 - 200 - 21, 21 + 20, (int)healthBarHealth, 20);
		
		g.setColor(Color.WHITE);
		g.drawRect(9 * 32 - (200 + 1) - 21, 20 + 50, 200 + 1, 21);
		g.setColor(Color.BLUE);
		g.fillRect(9 * 32 - 200 - 21, 21 + 50, (int)magicBarMagic, 20);
	}

	//BOUNDARIES
	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x + (width / 2)) - 16 + 6, (int) ((int)y + (height - 4)), (int) ((int)width - width / 2 + 16 - 12), (int)height / 16);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int)x + 5, (int)y, (int)width - 10, (int)height / 16);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x + width - 5), (int)y + 5, (int)5, (int)height - 10);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y + 5, (int)5, (int)height - 10);
	}
	//COLLISION HANDLING
	private void collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ObjectId.Block) {
					
					if (getBounds().intersects(tempObject.getBounds())) {
						y = tempObject.getY() - height;
						velY = 0;
						falling = false;
						jumping = false;
					} else {
						falling = true;
				}
					if (getBoundsTop().intersects(tempObject.getBounds())) {
						y = tempObject.getY() + height / 2;
						velY = 0;
					}
					if (getBoundsRight().intersects(tempObject.getBounds())) {
						x = tempObject.getX() - width;
					}
					if (getBoundsLeft().intersects(tempObject.getBounds())) {
						x = tempObject.getX() + width;
					}
				}
			if (tempObject.getId() == ObjectId.ColorBlock) {
				
				if (getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
					if (tempObject.getColor() != Color.BLUE) {
						ScoreBoard.blueScore++;
					}
					if (tempObject.getColor() == Color.RED) {
						ScoreBoard.redScore--;
					}
					tempObject.setColor(Color.BLUE);
				} else {
					falling = true;
			}
				if (getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + height / 2;
					velY = 0;
				}
				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width;
				}
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + width;
				}
			}
			}
		}
}

