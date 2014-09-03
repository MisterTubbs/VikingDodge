package com.jbs.vikingdodge.entity.mob;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.assets.AnimationPackage;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.game.Game;

public class Dragon extends AnimatedMob {

	private Game game;
	
	//false = facing right, true = facing left
	private boolean dir, attacking, fastFly, exiting, playLaunch;
	
	private int moveSpeed = 6, runSpeed = 10;
	
	public Dragon(Game game) {
		super(AnimationPackage.dragonAnimations.getPackage(), "fly", -160, Main.screenSize.y - 190, 160, 170);
		setVel(0, 0);
		this.game = game;
	}
	
	public void tick() {
		getAnimations().get(getCurrentAnimationKey()).tick();
		if(isDirty()) {
			getBoundingBox().set(getX(), getY(), getWidth(), getHeight());
			setDirty(false);
		}
		
		if(getX() > Main.screenSize.x && !dir) {
			if(!exiting) {
				dir = true;
				flipLeft();
			}
			else kill();
		}
		
		else if(getX() + getWidth() < 0 && dir) {
			if(!exiting) {
				dir = false;
				flipRight();
			}
			else kill();
		}
		
		if(attacking && getCurrentAnimationKey() == "fire" && getCurrentAnimation().isDone()) {
			getCurrentAnimation().setNotDone();
			game.getEntityManager().add(new DragonRock(game, this));
			setAnimation("postFire");
		}
		
		if(attacking && getCurrentAnimationKey() == "postFire" && getCurrentAnimation().isDone()) {
			getCurrentAnimation().setNotDone();
			setAnimation("fly");
			attacking = false;
			playLaunch = false;
		}
		
		setVel(dir ? -moveSpeed : moveSpeed, getVel().y);
		addPos(getVel().x, getVel().y);
	}
	
	public void render(SpriteBatch batch) {
		super.render(batch);
	}
	
	public void exit() {
		exiting = true;
	}
	
	public void attack() {
		attacking = true;
		if(!playLaunch) Assets.launch.play();
		playLaunch = true;
		setAnimation("fire");
	}
	
	public boolean isAttacking() {
		return attacking;
	}
	
	public boolean isExiting() {
		return exiting;
	}
}
