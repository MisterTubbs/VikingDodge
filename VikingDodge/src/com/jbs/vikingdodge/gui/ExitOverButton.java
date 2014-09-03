package com.jbs.vikingdodge.gui;

import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.screens.MainMenu;

public class ExitOverButton extends TexturedButton {

	public ExitOverButton() {
		super(Assets.gameOverExit, Main.centered.x - Assets.gameOverExit.getWidth() / 2, 142 - Assets.gameOverExit.getHeight() + 15, Assets.gameOverExit.getWidth(), Assets.gameOverExit.getHeight());
	}

	@Override
	public void onClick() {
		Assets.click.play();
		Main.activeGame.switchState(new MainMenu(Main.avatarTexture));
	}
}
