package com.jbs.vikingdodge.entity.mob;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.gui.Heart;

public class HeartRay extends TexturedMob {

	private Heart heart;
	private float rot = 360.0f;
	
	public HeartRay(Heart heart) {
		super(Assets.heartRay, heart.getX(), Main.screenSize.y - Assets.heart.getHeight() - 10, Assets.heartRay.getWidth(), Assets.heartRay.getHeight());
		this.heart = heart;
	}
	
	@Override
	public void tick() {
		if(rot <= 0.0f) rot = 360.0f;
		setPos(heart.getX() - (heart.getWidth() / 2), heart.getY() - (heart.getHeight() / 2));
		rot--;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(getTexture().getTexture(), getX(), getY(), getWidth() / 2, getHeight() / 2, getWidth(), getHeight(), 1, 1, rot, 0, 0, (int) getWidth(), (int) getHeight(), false, false);
	}
}
