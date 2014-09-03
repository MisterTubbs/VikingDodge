package com.jbs.vikingdodge.gui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.jbs.vikingdodge.Constants;
import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.assets.TextureAsset;
import com.jbs.vikingdodge.entity.mob.HeartRay;
import com.jbs.vikingdodge.entity.mob.TexturedMob;
import com.jbs.vikingdodge.game.Game;

public class Heart extends TexturedMob {

	private TextureAsset emptyHeart;
	private boolean empty, mobile, hitPlayer, moveUp;
	private float fade;
	private HeartRay ray;

	public Heart(float x, boolean mobile) {
		super(Assets.heart, x, Main.screenSize.y - Assets.heart.getHeight() - 10, Assets.heart.getWidth(), Assets.heart.getHeight());
		emptyHeart = Assets.emptyHeart;
		this.mobile = mobile;

		if (mobile)
			ray = new HeartRay(this);
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.setColor(1, 1, 1, 1 / fade);
		if (ray != null)
			ray.render(batch);
		batch.draw(empty ? emptyHeart.getTexture() : getTexture().getTexture(), getPos().x, getPos().y, Assets.heart.getWidth(), Assets.heart.getHeight());
		batch.setColor(1, 1, 1, 1);
	}

	@Override
	public void tick() {
		if (mobile) {
			super.tick();
			ray.tick();
			
			if(getY() > Constants.GROUND_HEIGHT + 15) moveUp = false;
			if(getY() <= Constants.GROUND_HEIGHT + 1) moveUp = true;
			
			addPos(0, moveUp ? 0.5f : -0.5f);
		}

		if (onGround()) {
			fade();
		}
	}

	public void fade() {
		Timer.schedule(new Task() {
			@Override
			public void run() {
				if (!Game.paused) {
					fade++;
					if (fade >= 10)
						kill();
				}
			}
		}, 5, 1);
	}

	public void setEmpty() {
		this.empty = true;
	}

	public void setFull() {
		this.empty = false;
	}

	public void setHitPlayer() {
		hitPlayer = true;
	}

	public boolean hitPlayer() {
		return hitPlayer;
	}
}
