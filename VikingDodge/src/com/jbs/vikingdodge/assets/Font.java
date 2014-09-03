package com.jbs.vikingdodge.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Font {

	private BitmapFont font;
	
	public Font(String path) {
		font = new BitmapFont(Gdx.files.internal(path + ".fnt"), Gdx.files.internal(path + ".png"), false);
	}
	
	public void render(SpriteBatch batch, String text, float x, float y) {
		font.draw(batch, text, x, y);
	}
}
