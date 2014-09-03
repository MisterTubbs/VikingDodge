package com.jbs.vikingdodge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.Ray;

public class InputProxy implements InputProcessor {

	public static int Scroll = 0;

	public static Vector2 getTouch() {
		Vector2 actual = Main.actualScreenSize;
		int x = Gdx.input.getX();
		int y = (int) actual.y - Gdx.input.getY();
		return new Vector2(x * (Main.screenSize.x / actual.x), y * (Main.screenSize.y / actual.y));
	}
	
	public static Vector2 getTouch(int x, int y) {
		Vector2 actual = Main.actualScreenSize;
		y = (int) actual.y - y;
		return new Vector2(x * (Main.screenSize.x / actual.x), y * (Main.screenSize.y / actual.y));
	}
	
	public static Vector2 getTouch(int i) {
		Vector2 actual = Main.actualScreenSize;
		int x = Gdx.input.getX(i);
		int y = (int) actual.y - Gdx.input.getY(i);
		return new Vector2(x * (Main.screenSize.x / actual.x), y * (Main.screenSize.y / actual.y));
	}

	public static Vector2 getTouchRaw() {
		return new Vector2(Gdx.input.getX(), Gdx.input.getY());
	}

	public static Vector2 screenToWorld(Vector2 v) {
		Ray r = Main.camera.getPickRay(v.x, v.y);
		return new Vector2(r.origin.x, r.origin.y);
	}
	
	public static boolean getTouchSide(float x) {
		//true - left, false - right
		return getTouch().x > x;
	}
	
	public static void clean() {
		Scroll = 0;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		Scroll = amount;
		return false;
	}
}
