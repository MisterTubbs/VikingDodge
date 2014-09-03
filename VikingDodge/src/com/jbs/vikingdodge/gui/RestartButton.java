package com.jbs.vikingdodge.gui;

import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.game.Game;

public class RestartButton extends TexturedButton {

	private Game game;
	
	public RestartButton(Game game) {
		super(Assets.restartButton, Main.centered.x - Assets.restartButton.getWidth() / 2, Main.centered.y - Assets.restartButton.getHeight() / 2 - Assets.restartButton.getHeight() / 2, Assets.restartButton.getWidth(), Assets.restartButton.getHeight());
		this.game = game;
	}

	@Override
	public void onClick() {
		super.onClick();
		Assets.click.play();
		game.restart();
	}
}
