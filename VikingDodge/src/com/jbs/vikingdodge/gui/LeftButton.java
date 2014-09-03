package com.jbs.vikingdodge.gui;

import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.entity.mob.Player;
import com.jbs.vikingdodge.game.Game;

public class LeftButton extends TexturedButton {

	private Game game;
	
	public LeftButton(Game game) {
		super(Assets.leftArrow, 40, 20, Assets.leftArrow.getWidth() * 1.2f, Assets.leftArrow.getHeight());
		this.game = game;
	}
	
	@Override
	public void onClick() {
		if(game.getEntityManager().getPlayer().onGround()) {
			game.getEntityManager().getPlayer().setVel(-Player.moveSpeed, 0);
			game.getEntityManager().getPlayer().setWalking(true);
		} else if(!game.getEntityManager().getPlayer().onGround() && !game.getEntityManager().getPlayer().canJump() && game.getEntityManager().getPlayer().getVel().x > 0) {
			game.getEntityManager().getPlayer().setVel(-game.getEntityManager().getPlayer().getVel().x, game.getEntityManager().getPlayer().getVel().y);
			game.getEntityManager().getPlayer().flipRight();
		}
		game.getEntityManager().getPlayer().flipLeft();
	}
	
	@Override
	public void onDoubleClick() {
		if(game.getEntityManager().getPlayer().onGround() && !Player.carryingSheepJesus) 
			game.getEntityManager().getPlayer().leap(true);
	}
}
