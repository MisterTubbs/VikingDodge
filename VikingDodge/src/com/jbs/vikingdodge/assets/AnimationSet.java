package com.jbs.vikingdodge.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationSet {
	
	private TextureRegion[][] textures;
	private int tx, ty;
	
	public AnimationSet(TextureRegion[][] textures, int tx, int ty) {
		this.textures = textures;
		this.tx = tx;
		this.ty = ty;
	}
	
	public static AnimationSet load(String loc, int w, int h, int tx, int ty) {
		return new AnimationSet(TextureRegion.split(new Texture(Gdx.files.internal(loc)), w, h), tx, ty);
	}

	public TextureRegion[][] getTextures() {
		return textures;
	}
	
	public TextureRegion getTexture(int x, int y) {
		return textures[x][y];
	}
	
	public void setTexture(int x, int y, TextureRegion texture) {
		textures[x][y] = texture;
	}
	
	public int getWidth() {
		return tx;
	}
	
	public int getHeight() {
		return ty;
	}
}
