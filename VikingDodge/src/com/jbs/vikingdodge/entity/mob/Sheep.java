package com.jbs.vikingdodge.entity.mob;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jbs.vikingdodge.Collidable;
import com.jbs.vikingdodge.Constants;
import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.assets.AnimationPackage;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.assets.TextureAsset;
import com.jbs.vikingdodge.entity.Entity;

public class Sheep extends AnimatedMob implements Collidable {

	private boolean thrownSheep, sign0, sign1;
	private TextureAsset sheepText, sheepText1;
	
	private float sx, sy, speedMod;
	
	public Sheep(float x, float y, float vx, float vy) {
		this(x, y);
		this.thrownSheep = true;
		this.sheepText = Assets.sign0;
		this.sheepText1 = Assets.sign1;
		this.sx = x;
		this.sy = Constants.GROUND_HEIGHT;
		sign0 = false;
		sign1 = true;
		this.setVel(vx, vy);
		if(vx < 0) this.flipLeft();
		if(vx > 0) this.flipRight();
	}
	
	public Sheep(float x, float y) {
		super(AnimationPackage.sheepAnimations.getPackage(), "stand", x, y, 96, 79);
		sign0 = true;
		this.sheepText = Assets.sign0;
		this.sheepText1 = Assets.sign1;
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if(sign1) sy += speedMod;
		speedMod += 0.15f;
		
		if(getX() + getWidth() < -15000 || getX() > Main.screenSize.x + 15000) kill();
		
		if(onGround()) {
			setAnimation("stand");
			setHitGround(false);
		}
	}
	
	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		if(sign0) batch.draw(sheepText.getTexture(), getX() - (getFlip() ? 45 : 45), getY() + getHeight() - 35);
		if(sign1) batch.draw(sheepText1.getTexture(), sx, sy);
	}

	@Override
	public void isColliding(List<Entity> entities) {
	}

	@Override
	public void onCollide(Mob m) {
	}
	
	public boolean isThrownSheep() {
		return thrownSheep;
	}
}
