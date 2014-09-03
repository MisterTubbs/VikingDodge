package com.jbs.vikingdodge.gui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.game.Game;

public abstract class Button {

	protected Vector2 loc, size;

	public Button(float x, float y, float w, float h) {
		this(new Vector2(x, y), new Vector2(w, h));
	}

	public Button(Vector2 loc, Vector2 size) {
		this.loc = loc;
		this.size = size;
	}

	public boolean inputIntersects(Vector2 pos) {
		int x = (int) pos.x;
		int y = (int) pos.y;

		return x >= loc.x && y >= loc.y && x <= loc.x + size.x && y <= loc.y + size.y;
	}

	public void onClick() {
		Game.guiBlock = true;
	}

	public void onDoubleClick() {
	}

	public abstract void render(SpriteBatch batch);

	public Vector2 getLoc() {
		return loc;
	}

	public void setLoc(Vector2 loc) {
		this.loc = loc;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}

	public static Vector2 getCentered(float w, float h) {
		return new Vector2(Main.centered.x - (w / 2), Main.centered.y - (h / 2));
	}
}
