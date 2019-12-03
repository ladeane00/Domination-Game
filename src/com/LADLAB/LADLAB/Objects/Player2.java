package com.LADLAB.LADLAB.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.LADLAB.LADLAB.Handler;

import Framework.GameObject;
import Framework.KeyInput;
import Framework.ObjectId;

//SAME AS PLAYER1
public class Player2 extends GameObject {
	
	public int time = 300;
	public int seconds = 1;
	public int end = 0;
	public int time1 = 1100;
	public int seconds1 = 2;
	public int end1 = 0;
	TimerTask task;
	Timer timer;
	
	private float width = 32, height = 64;
	private float gravity = 0.8f;
	private final float MAX_SPEED = 15f;
	public int speed = 5;
	
	public static float healthBarHealth;
	public static float magicBarMagic;
	
	Random r;
	
	public static boolean CPUOn;
	
	private Handler handler;

	public Player2(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		walkingRight = false;
		walkingLeft = false;
		healthBarHealth = 200;
		magicBarMagic = 200;
		timer = new Timer();
		r = new Random();
		timerStart();
		timerStartJump();
		CPUOn = false;
	}

	public void tick(LinkedList<GameObject> object) {
		//ai
		if (CPUOn) {
			if (seconds1 == 0) {
				velY = -22;
			}
			if (seconds == 0) {
				if (r.nextInt(2) == 0) {
					KeyInput.left2 = true;
					KeyInput.right2 = false;
				} else {
					System.out.println("NDVHGHD");
					KeyInput.right2 = true;
					KeyInput.left2 = false;
				}
			}
	}
		if (seconds == 0) {
			seconds = 2;
		}
		if (seconds1 == 0) {
			seconds1 = 1;
		}
		if (healthBarHealth <= 200) {
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
		if (KeyInput.right2) {
			x += speed;
		}
		if (KeyInput.left2) {
			x -= speed;
		}
		if (shooting && magicBarMagic > 20) {
			magicBarMagic -= 20;
		}
		shooting = false;
		collision(object);
		
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

	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
		g.setColor(Color.WHITE);
		g.drawRect((int)x, (int)y, (int)width, (int)height);
		
		g.setColor(Color.WHITE);
		g.drawRect(28 * 32 - (200 + 1) - 21, 20 + 20, 200 + 1, 21);
		g.setColor(Color.GREEN);
		g.fillRect(28 * 32 - 200 - 21, 21 + 20, (int)healthBarHealth, 20);
		
		g.setColor(Color.WHITE);
		g.drawRect(28 * 32 - (200 + 1) - 21, 20 + 50, 200 + 1, 21);
		g.setColor(Color.BLUE);
		g.fillRect(28 * 32 - 200 - 21, 21 + 50, (int)magicBarMagic, 20);
		
	}

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
					if (tempObject.getColor() != Color.RED) {
						ScoreBoard.redScore++;
					}
					if (tempObject.getColor() == Color.BLUE) {
						ScoreBoard.blueScore--;
					}
					tempObject.setColor(Color.RED);
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
	
	private void timerStart() {
		timer.scheduleAtFixedRate(task = new TimerTask() {
			public void run() {
				if (seconds > end) {
					seconds--;
				}
			}
			
		}, time, time);
	}
	private void timerStartJump() {
		timer.scheduleAtFixedRate(task = new TimerTask() {
			public void run() {
				if (seconds1 > end1) {
					seconds1--;
				}
			}
			
		}, time1, time1);
	}
}


