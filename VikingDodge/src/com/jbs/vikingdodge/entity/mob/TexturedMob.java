package com.jbs.vikingdodge.entity.mob;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.jbs.vikingdodge.assets.TextureAsset;

public class TexturedMob extends Mob {

	private TextureAsset texture;
	
	public TexturedMob(TextureAsset texture, float x, float y, float w, float h) {
		super(x, y, w, h);
		this.texture = texture;
	}
	
	public TexturedMob(TextureAsset texture, float x, float y, float w, float h, float vx, float vy) {
		super(x, y, w, h, vx, vy);
		this.texture = texture;
	}
	
	public TexturedMob(TextureAsset texture, float x, float y, float w, float h, Rectangle boundingBox) {
		super(x, y, w, h, boundingBox);
		this.texture = texture;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		batch.draw(texture.getTexture(), getX(), getY(), getWidth(), getHeight());
	}
	
	@Override
	public void tick() {
		super.tick();
	}
	
	public TextureAsset getTexture() {
		return texture;
	}
}
