package com.jbs.vikingdodge.entity;

import com.badlogic.gdx.math.Vector2;
import com.jbs.vikingdodge.Tickable;

public abstract class Entity implements Tickable {

	private Vector2 pos;
	private boolean dirty, alive, walking;

	public Entity(float x, float y) {
		this(new Vector2(x, y));
	}
 	
	public Entity(Vector2 pos) {
		this.pos = pos;
		this.alive = true;
	}

	@Override
	public void tick() {
	}

	public void create() {
	}
	
	public Vector2 getPos() {
		return pos;
	}
	
	public float getX() {
		return pos.x;
	}
	
	public float getY() {
		return pos.y;
	}

	public void setX(float x) {
		this.pos.x = x;
	}

	public void setY(float y) {
		this.pos.y = y;
	}
	
	public void addPos(float mx, float my) {
		setPos(getX() + mx, getY() + my);
		dirty = true;
	}

	public void setPos(float x, float y) {
		pos.set(x, y);
	}
	
	public void setPos(Vector2 pos) {
		this.pos.set(pos);
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public boolean isWalking() {
		return walking;
	}

	public void setWalking(boolean walking) {
		this.walking = walking;
	}
	
	public void kill() {
		setAlive(false);
	}
	
	public boolean isDirty() {
		return dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
	
	public void destroy() {
	}
}
