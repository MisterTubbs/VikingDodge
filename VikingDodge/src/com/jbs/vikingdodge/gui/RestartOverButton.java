package com.jbs.vikingdodge.gui;

import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.game.Game;

public class RestartOverButton extends TexturedButton {

	private Game game;
	
	public RestartOverButton(Game game) {
		super(Assets.playAgain, Main.centered.x - Assets.playAgain.getWidth() / 2, (Main.centered.y - Assets.playAgain.getHeight() / 2 - Assets.playAgain.getHeight() / 2) - 60, Assets.playAgain.getWidth(), Assets.playAgain.getHeight());
		this.game = game;
	}

	@Override
	public void onClick() {
		super.onClick();
		Assets.click.play();
		game.restart();
	}
}
