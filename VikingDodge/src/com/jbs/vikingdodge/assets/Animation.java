package com.jbs.vikingdodge.assets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jbs.vikingdodge.Tickable;

public class Animation implements Tickable {
	
	public static Animation playerStand = new Animation(9, Assets.playerStand);
	public static Animation playerRun = new Animation(4, 1.15f, 1.15f, Assets.playerRun);
	public static Animation playerLeap = new Animation(5, Assets.playerLeap);
	public static Animation playerJump = new Animation(1, 1.1f, 1.1f, Assets.playerJump);
	public static Animation playerStandSheep = new Animation(9, 0.9f, 1, Assets.playerStandSheep);
	public static Animation playerRunSheep = new Animation(4, 1.1f, 1.15f, Assets.playerRunSheep);
	public static Animation playerThrowSheep = new Animation(4, 1.7f, 1.1f, Assets.playerThrowSheep);
	
	public static Animation sheepStand = new Animation(9, Assets.sheepStand);
	
	public static Animation dragonFly = new Animation(5, 3, 3, Assets.dragonFly);
	public static Animation dragonFire = new Animation(8, 3, 3, Assets.dragonFire);
	public static Animation dragonPostFire = new Animation(8, 3, 3, Assets.dragonPostFire);
	public static Animation dragonRock = new Animation(6, Assets.dragonRock);
	public static Animation rockEffect = new Animation(4, 3, 3, Assets.hitEffect);
	
	public static Animation oneUp = new Animation(4, Assets.oneUp);
	
	public static Animation rockDust = new Animation(4, 0.5f, 0.5f, Assets.rockDust);
	
	public static Animation jbsLoading = new Animation(4, Assets.jbsLoading);
	
	private AnimationSet set;
	private int cx, cy, delayTime, currentTime;
	private float scaleX, scaleY, dScaleX, dScaleY;
	private boolean done;

	/*
	 * Delay time is the time a single frame is in view. cx is the current texture on the x axis. cy is the current texture on the y axis. currentTime is the current amount of time spent on one texture
	 */
	
	public Animation(int delayTime, AnimationSet set) {
		this(delayTime, 1, 1, set);
	}
	
	public Animation(int delayTime, float scaleX, float scaleY, AnimationSet set) {
		this.delayTime = delayTime;
		this.set = set;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.dScaleX = scaleX;
		this.dScaleY = scaleY;
		this.currentTime = 1;
	}

	@Override
	public void tick() {
		if(currentTime % delayTime == 0) {
			if(cy >= set.getWidth()) {
				cy = 0;
				done = true;
			}
			cy++;
			currentTime = 1;
		}
		currentTime++;
	}
	
	public TextureRegion getTexture(int x, int y) {
		return set.getTexture(x, y);
	}
	
 	public TextureRegion getCurrentTexture() {
		return set.getTexture(cx, cy);
	}
	
	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}

	public float getScaleX() {
		return scaleX;
	}

	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
	}

	public float getScaleY() {
		return scaleY;
	}

	public void setScaleY(float scaleY) {
		this.scaleY = scaleY;
	}
	
	public void resetScale() {
		this.scaleX = dScaleX;
		this.scaleY = dScaleY;
	}
	
	public void setNotDone() {
		this.done = false;
	}
	
	public boolean isDone() {
		return done;
	}
}
