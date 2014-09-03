package com.jbs.vikingdodge;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Screen extends GameObject {
	public void create();
	public void renderText(SpriteBatch textBatch);
	public Color getClearColor();
}
