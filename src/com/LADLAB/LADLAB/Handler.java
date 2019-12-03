package com.LADLAB.LADLAB;

import java.awt.Graphics;
import java.util.LinkedList;

import Framework.GameObject;

public class Handler {
	//HANDLES ALL OBJECTS IN GAME
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	private Handler handler;
	
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.tick(object);
		}
	}
	
	public void paint(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.paint(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

}
