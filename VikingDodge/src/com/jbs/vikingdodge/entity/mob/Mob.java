package com.jbs.vikingdodge.entity.mob;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.jbs.vikingdodge.Constants;
import com.jbs.vikingdodge.Renderable;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.assets.TextureAsset;
import com.jbs.vikingdodge.entity.Entity;

public class Mob extends Entity implements Renderable {

	private Vector2 size, vel;
	private Rectangle boundingBox;
	private boolean onGround, hitGround, canJump, leaping, leapDir, jumping;
	private TextureAsset shadow;
	
	public Mob(float x, float y, float w, float h, float vx, float vy) {
		this(x, y, w, h, vx, vy, new Rectangle(x, y, w, h));
	}
	
	public Mob(float x, float y, float w, float h) {
		this(x, y, w, h, 0, 0, new Rectangle(x, y, w, h));
	}
	
	public Mob(float x, float y, float w, float h, Rectangle boundingBox) {
		this(x, y, w, h, 0, 0, boundingBox);
	}
	
	public Mob(float x, float y, float w, float h, float vx, float vy, Rectangle boundingBox) {
		super(x, y);
		this.size = new Vector2(w, h);
		this.boundingBox = boundingBox;
		this.vel = new Vector2(vx, vy);
		this.shadow = Assets.shadow;
		vel.y = Constants.GRAVITY;
	}

	@Override
	public void tick() {
		super.tick();
		if(isDirty()) {
			boundingBox.set(getX(), getY(), getWidth(), getHeight());
			setDirty(false);
		}
		
		if(getY() <= Constants.GROUND_HEIGHT) {
			if(jumping){
				Assets.land.play();
				jumping = false;
			}
			onGround = true;
			hitGround = true;
			canJump = true;
			vel.y = 0;
			setY(Constants.GROUND_HEIGHT);
		}
		
		if(leaping) 
			setVel(leapDir ? -14 : 14, 0);
		
		if(!onGround) vel.y += Constants.GRAVITY;
		addPos(vel.x, vel.y);
	}
	
	public void leap(boolean leapDir) {
		if(!leaping) Assets.dash.play();
		this.leapDir = leapDir;
		leaping = true;
		Timer.schedule(new Task() {
			@Override
			public void run() {
				leaping = false;
			}
		}, 0.5f);
	}
	
	public void jump() {
		if(onGround) {
			vel.y = 20.7f;
			canJump = false;
			onGround = false;
			jumping = true;
			setY(Constants.GROUND_HEIGHT + 1);
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(shadow.getTexture(), getCenter().x - (shadow.getWidth() / 2), onGround() ? getY() - 8 : Constants.GROUND_HEIGHT - 5.7f);
	}

	public Vector2 getSize() {
		return size;
	}
	
	public float getWidth() {
		return size.x;
	}
	
	public float getHeight() {
		return size.y;
	}
	
	public Vector2 getCenter() {
		return new Vector2(getX() + (getWidth() / 2), getY() + (getHeight() / 2));
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}
	
	public void setWidth(int width) {
		size.x = width;
	}
	
	public void setHeight(int height) {
		size.y = height;
	}
	
	public Rectangle getBoundingBox() {
		return boundingBox;
	}
	
	public boolean onGround() {
		return onGround;
	}
	
	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}

	public boolean isHitGround() {
		return hitGround;
	}

	public void setHitGround(boolean hitGround) {
		this.hitGround = hitGround;
	}
	
	public void setVel(float x, float y) {
		vel.set(x, y);
	}
	
	public Vector2 getVel() {
		return vel;
	}

	public boolean canJump() {
		return canJump;
	}

	public void setCanJump(boolean canJump) {
		this.canJump = canJump;
	}

	public boolean isLeaping() {
		return leaping;
	}

	public void setLeaping(boolean leaping) {
		this.leaping = leaping;
	}
}
