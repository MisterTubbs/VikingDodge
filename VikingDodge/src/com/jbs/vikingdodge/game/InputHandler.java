package com.jbs.vikingdodge.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.jbs.vikingdodge.InputProxy;
import com.jbs.vikingdodge.Tickable;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.entity.mob.Player;

public class InputHandler implements Tickable, GestureListener {

	private Game game;
	
	public InputHandler(Game game) {
		this.game = game;
	}
	
	@Override
	public void tick() {
		if(Gdx.input.isKeyPressed(Keys.D) && game.getEntityManager().getPlayer().onGround()) {
			game.getEntityManager().getPlayer().setVel(Player.moveSpeed, 0);
			game.getEntityManager().getPlayer().setWalking(true);
			game.getEntityManager().getPlayer().flipRight();
		} else if(Gdx.input.isKeyPressed(Keys.D) && !game.getEntityManager().getPlayer().onGround() && !game.getEntityManager().getPlayer().canJump() && game.getEntityManager().getPlayer().getVel().x < 0) {
			game.getEntityManager().getPlayer().setVel(-game.getEntityManager().getPlayer().getVel().x, game.getEntityManager().getPlayer().getVel().y);
			game.getEntityManager().getPlayer().flipRight();
		}
		if(Gdx.input.isKeyPressed(Keys.A) && game.getEntityManager().getPlayer().onGround()) {
			game.getEntityManager().getPlayer().setVel(-Player.moveSpeed, 0);
			game.getEntityManager().getPlayer().setWalking(true);
			game.getEntityManager().getPlayer().flipLeft();
		} else if(Gdx.input.isKeyPressed(Keys.A) && !game.getEntityManager().getPlayer().onGround() && !game.getEntityManager().getPlayer().canJump() && game.getEntityManager().getPlayer().getVel().x > 0) {
			game.getEntityManager().getPlayer().setVel(-game.getEntityManager().getPlayer().getVel().x, game.getEntityManager().getPlayer().getVel().y);
			game.getEntityManager().getPlayer().flipLeft();
		}
		if(Gdx.input.isKeyPressed(Keys.SPACE) && game.getEntityManager().getPlayer().canJump() && !Player.carryingSheepJesus) {
			game.getEntityManager().getPlayer().jump();
			game.getEntityManager().getPlayer().setWalking(false);
		}
		if(Gdx.input.isKeyPressed(Keys.E) && game.getEntityManager().getPlayer().onGround() && Player.carryingSheepJesus) {
			game.getEntityManager().getPlayer().throwSheep();
		}
		if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) && game.getEntityManager().getPlayer().onGround() && !Player.carryingSheepJesus) {
			game.getEntityManager().getPlayer().leap(game.getEntityManager().getPlayer().getFlip());
		}
		if(!Gdx.input.isTouched() && !Gdx.input.isKeyPressed(Keys.D) && !Gdx.input.isKeyPressed(Keys.A) && game.getEntityManager().getPlayer().onGround()) {
			game.getEntityManager().getPlayer().setWalking(false);
			game.getEntityManager().getPlayer().setVel(0, game.getEntityManager().getPlayer().getVel().y);
		}
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		if(game.getGameGUI().getButton()[3].inputIntersects(InputProxy.getTouch((int) x, (int) y)) && count >= 2) game.getGameGUI().getButton()[3].onDoubleClick();
		else if (game.getGameGUI().getButton()[4].inputIntersects(InputProxy.getTouch((int) x, (int) y)) && count >= 2) game.getGameGUI().getButton()[4].onDoubleClick();
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		return false;
	}
}
