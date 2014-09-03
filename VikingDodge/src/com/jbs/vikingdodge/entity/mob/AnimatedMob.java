package com.jbs.vikingdodge.entity.mob;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.jbs.vikingdodge.assets.Animation;
import com.jbs.vikingdodge.game.Game;

public class AnimatedMob extends Mob {

	private HashMap<String, Animation> animations;
	//terrible code... 
	private Animation currentAnimation;
	private String currentAnim, defaultAnim;
	private boolean flip, squashing, animate;
	
	public AnimatedMob(HashMap<String, Animation> animations, String defaultAnim, float x, float y, float w, float h) {
		super(x, y, w, h);
		this.animations = animations;
		this.currentAnim = defaultAnim;
		this.animate = true;
	}

	@Override
	public void tick() {
		super.tick();
		animations.get(currentAnim).tick();
		if(squashing) {
			Game.rockSpawnBlock = true;
			animations.get(currentAnim).setScaleY(0);
			if(animations.get(currentAnim).getScaleY() <= 0) {
				Timer.schedule(new Task() {
					@Override
					public void run() {
						reset();
					}
				}, 0.6f);
				squashing = false;
			}
		}
	}
	
	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		if(!animate) return;
		batch.draw(animations.get(currentAnim).getCurrentTexture(), getX(), getY(), getWidth() / 2, getHeight() / 2, getWidth(), getHeight(), flip ? -animations.get(currentAnim).getScaleX() : animations.get(currentAnim).getScaleX(), animations.get(currentAnim).getScaleY(), 0);
	}
	
	public void setAnimation(String currentAnim) {
		this.currentAnim = currentAnim;
		this.currentAnimation = animations.get(currentAnim);
	}
	
	public void squash() {
		squashing = true;
		animate = false;
	}
	
	public String getDefaultAnimation(){
		return defaultAnim;
	}
	
	public String getCurrentAnimationKey() {
		return currentAnim;
	}
	
	public Animation getCurrentAnimation() {
		return currentAnimation;
	}
	
	public HashMap<String, Animation> getAnimations() {
		return animations;
	}
	
	public boolean getFlip() {
		//left = true, right = false
		return flip;
	}
	
	public void flipLeft() {
		flip = true;
	}
	
	public void flipRight() {
		flip = false;
	}

	public void reset() {
		for(Animation a : getAnimations().values()) 
			a.resetScale();		
		animate = true;
	}
}
