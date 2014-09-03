package com.jbs.vikingdodge.gui;

import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.entity.mob.Player;
import com.jbs.vikingdodge.game.Game;

public class SheepButton extends TexturedButton {
	
	private Game game;

	public SheepButton(Game game) {
		super(Assets.throwSheep, Main.screenSize.x - Assets.jump.getWidth() - 220, 20, Assets.throwSheep.getWidth(), Assets.throwSheep.getHeight());
		this.game = game;
	}

	@Override
	public void onClick() {
		super.onClick();
		if(game.getEntityManager().getPlayer().onGround() && Player.carryingSheepJesus ) 
			game.getEntityManager().getPlayer().throwSheep();
	}
}
