package com.jbs.vikingdodge.entity.mob;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.jbs.vikingdodge.Constants;
import com.jbs.vikingdodge.assets.AnimationPackage;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.game.Game;

public class DragonRock extends AnimatedMob {

	private Game game;
	private Circle boundingBox;
	private boolean hitPlayer, playSound;

	private static int size = 200;
	
	public DragonRock(Game game, Dragon d) {
		super(AnimationPackage.dragonRockAnimations.getPackage(), "fly", d.getFlip() ? d.getX() - 180 : (d.getX() + d.getWidth()) + 20, d.getY() - 115, size, size);
		boundingBox = new Circle(getPos().x + size / 2, getPos().y + size / 2, size / 2);
		setVel(d.getFlip() ? -12 : 12, -12);
		this.game = game;
	}
	
	public void render(SpriteBatch batch) {
		super.render(batch);
	}
	
	public void tick() {
		getAnimations().get(getCurrentAnimationKey()).tick();
		if(isDirty()) {
			getBoundingCircle().set(getPos().x + size / 2, getPos().y + size / 2, size / 2);
			setDirty(false);
		}

		if(getY() <= Constants.GROUND_HEIGHT - 35) {
			setOnGround(true);
			setHitGround(true);
			hit();
			
			if(!playSound) Assets.explode.play();
			playSound = true;
		}
		
		if(getCurrentAnimationKey() == "hit" && getCurrentAnimation().isDone()) {
			getCurrentAnimation().setNotDone();
			kill();
			game.getEntityManager().addBurn(new GroundBurn(getX()));
		}
		
		addPos(getVel().x, getVel().y);
	}
	
	public void hit() {
		setVel(0, 0);
		setAnimation("hit");
	}
	
	public Circle getBoundingCircle() {
		return boundingBox;
	}

	public boolean hitPlayer() {
		return hitPlayer;
	}
	
	public void setHitPlayer() {
		hitPlayer = true;
	}
}
 