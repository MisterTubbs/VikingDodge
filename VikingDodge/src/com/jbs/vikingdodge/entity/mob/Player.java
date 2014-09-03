package com.jbs.vikingdodge.entity.mob;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jbs.vikingdodge.Collidable;
import com.jbs.vikingdodge.Constants;
import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.assets.AnimationPackage;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.entity.Collision;
import com.jbs.vikingdodge.entity.Entity;
import com.jbs.vikingdodge.game.Game;
import com.jbs.vikingdodge.gui.Heart;

public class Player extends AnimatedMob implements Collidable {
	
	public static int moveSpeed = 8, lives = 3;
	public static boolean carryingSheepJesus, throwingSheep;
	
	private Game game;
	private boolean sheepSoundPlay;
	
	public Player(Game game, float x, float y) {
		super(AnimationPackage.playerAnimations.getPackage(), "stand", x, y, 120, 160);
		this.game = game;
	}
	
	@Override
	public void tick() {
		super.tick();
		if(getX() > Main.screenSize.x) this.setX(0 - getWidth());
		if(getX() + getWidth() < 0) this.setX(Main.screenSize.x);
		if(!onGround()) setAnimation("jump");
		if(onGround() && !isWalking() && !isLeaping()) {
			setAnimation(carryingSheepJesus ? "standSheep" : "stand");
			setHitGround(false);
		}
		if(onGround() && isWalking() && !throwingSheep) 
			setAnimation(carryingSheepJesus ? "runSheep" : "run");
		if(isLeaping()) 
			setAnimation("leap");
		if(throwingSheep) {
			if(!sheepSoundPlay)	{
				int r = Constants.rand.nextInt(1);
				switch(r) {
				case 0:
					Assets.throw0.play();
					break;
				case 1:
					Assets.throw1.play();
					break;
				}
			}
			sheepSoundPlay = true;
			setAnimation("throwSheep");
			if(getCurrentAnimation().isDone()) {
				sheepSoundPlay = false;
				throwingSheep = false;
				carryingSheepJesus = false;
				moveSpeed = 8;
				game.scheduleSheepSpawn(30);
				
				game.getEntityManager().add(new Sheep(getFlip() ? getX() : getX() + getWidth(), getY() + getHeight(), getFlip() ? -50 : 50, -2));
			}
		}
	}
	
	public void throwSheep() {
		throwingSheep = true;
		Game.scoreMod = 1;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
	}

	@Override
	public void isColliding(List<Entity> entities) {
		for(Entity e : entities) {
			 if(e instanceof Mob) {
				 if(e instanceof Rock) {
					 if(Collision.isColliding(getBoundingBox(), ((Rock) e).getBoundingCircle())) onCollide((Mob) e);
				 }
				 if(e instanceof DragonRock) {
					 if(Collision.isColliding(getBoundingBox(), ((DragonRock) e).getBoundingCircle())) onCollide((DragonRock) e);
				 }
				 else if(Collision.isColliding(((Mob) e).getBoundingBox(), getBoundingBox())) onCollide((Mob) e);
			 }
		}
	}

	@Override
	public void onCollide(Mob m) {
		if(m instanceof Sheep && !isLeaping() && onGround() && !((Sheep) m).isThrownSheep()) {
			carryingSheepJesus = true;
			Game.scoreMod = 2;
			moveSpeed = 6;
			m.kill();
		}
		else if(m instanceof Rock) {
			if(lives > 0 && !((Rock) m).hitPlayer()) {
				lives--;
				((Rock) m).setHitPlayer();
				squash();
			}
		} 
		else if(m instanceof DragonRock) {
			if(lives > 0 && !((DragonRock) m).hitPlayer()) {
				lives--;
				((DragonRock) m).setHitPlayer();
				((DragonRock) m).hit();
				squash();
			}
		}
		else if(m instanceof Heart) {
			if(lives < 3 && !((Heart) m).hitPlayer()) {
				lives++;
			}
			game.getEntityManager().add(new RegainAnimation(((Heart) m).getX()));
			((Heart) m).setHitPlayer();
			((Heart) m).kill();
		}
	}
	
	public void reset() {
		super.reset();
		setPos(Main.centered.x, Main.centered.y + 200);
		setOnGround(false);
		carryingSheepJesus = false;
		moveSpeed = 8;
		Game.rockSpawnBlock = false;
		Game.scoreMod = 1;
		Game.dragonScore = 0;
		game.getEntityManager().reset();
		if(game.getEntityManager().getDragon() != null) game.getEntityManager().getDragon().exit();
	}
}
