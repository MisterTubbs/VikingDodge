package com.jbs.vikingdodge.gui;

import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.game.Game;

public class PauseButton extends TexturedButton {

	public PauseButton() {
		super(Assets.pauseButton, Main.screenSize.x - Assets.pauseButton.getWidth() - 10, Main.screenSize.y - Assets.pauseButton.getHeight() - 10, Assets.pauseButton.getWidth(), Assets.pauseButton.getHeight());
	}

	@Override
	public void onClick() {
		super.onClick();
		Assets.click.play();
		Game.paused = true;
	}
}
