package com.jbs.vikingdodge.gui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.jbs.vikingdodge.assets.TextureAsset;

public abstract class TexturedButton extends Button {

	private TextureAsset texture;
	
	public TexturedButton(TextureAsset texture, float x, float y, float w, float h) {
		this(texture, new Vector2(x, y), new Vector2(w, h));
	}
	
	public TexturedButton(TextureAsset texture, Vector2 loc, Vector2 size) {
		super(loc, size);
		this.texture = texture;
	}

	public void render(SpriteBatch batch) {
		batch.draw(texture.getTexture(), getLoc().x, getLoc().y, getSize().x, getSize().y);
	}
}
