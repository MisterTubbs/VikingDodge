package com.jbs.vikingdodge.gui;

import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.entity.mob.Player;
import com.jbs.vikingdodge.game.Game;

public class JumpButton extends TexturedButton {

	private Game game;
	
	public JumpButton(Game game) {
		super(Assets.jump, Main.screenSize.x - Assets.jump.getWidth() - 80, 20, Assets.jump.getWidth(), Assets.jump.getHeight());
		this.game = game;
	}

	@Override
	public void onClick() {
		super.onClick();
		if(game.getEntityManager().getPlayer().canJump() && !Player.carryingSheepJesus)
			game.getEntityManager().getPlayer().jump();
	}
}
