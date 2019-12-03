package Framework;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.LADLAB.LADLAB.Game;
import com.LADLAB.LADLAB.Handler;
import com.LADLAB.LADLAB.Menu;
import com.LADLAB.LADLAB.PlayerSelect;
import com.LADLAB.LADLAB.Objects.Player;
import com.LADLAB.LADLAB.Objects.Player2;
import com.LADLAB.LADLAB.Objects.Projectile;

//CLASS HANDLES KEY INPUT
public class KeyInput implements KeyListener {
	
	int time = 333;
	int time1 = 1000;
	int seconds = 0;
	public static int seconds1 = 0;
	public static int end = 0;
	public static int end1 = 0;
	TimerTask task;
	Timer timer;
	public static boolean left1 = false;
	public static boolean right1 = false;
	public static boolean left2 = false;
	public static boolean right2 = false;
	
	public static int spawnNum;
	
	public static Random rnd = new Random();
	
	Handler handler;
    
	public KeyInput(Handler handler) {
		this.handler = handler;
		timer = new Timer();
		timerStart();
		timerStartSpawn();
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (Game.GameState == 1) {
		if (key == KeyEvent.VK_ENTER) {
			Menu.select();
			Menu.g = true;
		}
		if (key == KeyEvent.VK_UP) {
			Menu.currentChoice--;
			if (Menu.currentChoice == -1) {
				Menu.currentChoice = Menu.options.length - 1;
			}
		}
		if (key == KeyEvent.VK_DOWN) {
			Menu.currentChoice++;
			if (Menu.currentChoice == Menu.options.length) {
				Menu.currentChoice = 0;
			}
		}
		}
		if (Game.GameState == 2) {
			System.out.println("hi");
			System.out.println(Game.GameState);
		if (key == KeyEvent.VK_SLASH) {
			PlayerSelect.select1();
			System.out.println("hi");
		}
		if (key == KeyEvent.VK_UP) {
			PlayerSelect.currentChoice1--;
			if (PlayerSelect.currentChoice1 == -1) {
				PlayerSelect.currentChoice1 = PlayerSelect.options.length - 1;
			}
		}
		if (key == KeyEvent.VK_DOWN) {
			PlayerSelect.currentChoice1++;
			if (PlayerSelect.currentChoice1 == PlayerSelect.options.length) {
				PlayerSelect.currentChoice1 = 0;
			}
		}
		}
		if (Game.GameState == 3) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ObjectId.Player) {
				if (key == KeyEvent.VK_D) {
					tempObject.facingRight = 1;
					right1 = true;
				}
				if (key == KeyEvent.VK_A) {
					tempObject.facingRight = -1;
					left1 = true;
				}
				if (Player2.CPUOn == false) {
				if (key == KeyEvent.VK_RIGHT) { 
					right2 = true;
				}
				if (key == KeyEvent.VK_LEFT) { 
					left2 = true;
				}
				}
				if (key == KeyEvent.VK_SPACE && !tempObject.isJumping()) {
					tempObject.setJumping(true);
					tempObject.setVelY(-16);
				}
				if (key == KeyEvent.VK_F) {
					if (Player.magicBarMagic >= 20) {
					handler.addObject(new Projectile(tempObject.getX() + tempObject.facingRight * 48, tempObject.getY() + 32, ObjectId.Projectile, tempObject.facingRight * 15));
					}
					tempObject.shooting = true;
				}
			}
			if (tempObject.getId() == ObjectId.Player2) {
				if (Player2.CPUOn == false) {
				if(key == KeyEvent.VK_UP && !tempObject.isJumping()) {
				tempObject.setJumping(true);
				tempObject.setVelY(-16);
				}
				if (key == KeyEvent.VK_SHIFT) {
					if (Player2.magicBarMagic >= 20) {
						handler.addObject(new Projectile(tempObject.getX() + tempObject.facingRight * 48, tempObject.getY() + 32, ObjectId.Projectile, tempObject.facingRight * 15));
						}
					tempObject.shooting = true;
				}
				if (key == KeyEvent.VK_RIGHT) { 
					right2 = true;
					tempObject.facingRight = 1;
				}
				if (key == KeyEvent.VK_LEFT) { 
					left2 = true;
					tempObject.facingRight = -1;
				}
			}
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
	private void timerStartSpawn() {
		timer.scheduleAtFixedRate(task = new TimerTask() {
			public void run() {
				if (seconds1 > end1) {
					seconds1--;
				}
			}
			
		}, time1, time1);
	}

	public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ObjectId.Player) {
				if (key == KeyEvent.VK_D) {
					tempObject.walkingRight = false;
					right1 = false;
				}
				if (key == KeyEvent.VK_A) {
					tempObject.walkingLeft = false;
					left1 = false;
				}
				if (key == KeyEvent.VK_RIGHT) { 
					right2 = false;
				}
				if (key == KeyEvent.VK_LEFT) { 
					left2 = false;
				}
				if (key == KeyEvent.VK_F) {
					tempObject.shooting = false;
				}
			}
		}
		
	}
public void keyTyped(KeyEvent e) {
	
}
}
