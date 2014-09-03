package com.jbs.vikingdodge.assets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class FontNumbers {

	private Vector2 pos;
	
	public FontNumbers(float x, float y) {
		this.pos = new Vector2(x, y);
	}
	
	public void render(int n, SpriteBatch batch) {
		String num = Integer.toString(n);
		for(int i = 0; i < num.length(); i++) 
			batch.draw(Assets.numbers[0][num.charAt(i) - '0'], pos.x + (50 * i), pos.y);
	}
	
	public void setPos(float x, float y) {
		pos.set(x, y);
	}
}
