package com.jbs.vikingdodge.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureAsset {
	
	private Texture texture;
	
	public TextureAsset(Texture texture) {
		this.texture = texture;
	}
	
	public static TextureAsset loadTexture(String loc) {
		return new TextureAsset(new Texture(Gdx.files.internal(loc)));
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public int getWidth() {
		return texture.getWidth();
	}
	
	public int getHeight() {
		return texture.getHeight();
	}
}
