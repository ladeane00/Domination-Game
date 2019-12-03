package Framework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

//SUPERCLASS FOR ALL GAME OBJECTS
public abstract class GameObject {
	
	protected float x, y;
	protected ObjectId id;
	protected float velX = 0;
	protected float velY = 0;
	protected boolean falling = true;
	protected boolean jumping = false;
	protected boolean walkingRight;
	protected boolean walkingLeft;
	protected boolean shooting;
	protected Color color;
	protected int facingRight;
	protected int healthBarHealth;
	protected int magicBarMagic;
	protected Random r;
	
	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}
	public boolean isShooting() {
		return shooting;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public GameObject(float x, float y, ObjectId id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick(LinkedList<GameObject> object);
	public abstract void paint(Graphics g);
	public abstract Rectangle getBounds();
	//xs and ys
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
	
	//velocity values
	public float getVelX() {
		return velX;
	}
	public float getVelY() {
		return velY;
	}
	public void setVelX(float velX) {
		this.velX = velX;
	}
	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	//return ID (very important to identify what the game object is)
	public ObjectId getId() {
		return id;
	}

}
