package com.jbs.vikingdodge.entity;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Collision {
	
	public static enum Type {
		LEFT, RIGHT, TOP, BOTTOM;
	}
	
	public static boolean isColliding(Rectangle a, Rectangle b) {
		return a.overlaps(b);
	}
	
	public static boolean isColliding(Rectangle a, Circle b) {
		return Intersector.overlapCircleRectangle(b, a);
	}
	
	public static boolean pointToRect(Vector2 mouse, Rectangle r) {
		return mouse.x > r.getX() && mouse.y > r.getY() && mouse.x < r.getY() + r.getWidth() && mouse.y < r.getY() + r.getHeight();
	}
}
