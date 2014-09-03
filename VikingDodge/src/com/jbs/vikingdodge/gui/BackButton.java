package com.jbs.vikingdodge.gui;

import com.badlogic.gdx.graphics.Texture;
import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.screens.HighscoreScreen;
import com.jbs.vikingdodge.screens.MainMenu;

public class BackButton extends TexturedButton {

	private Texture avatarTexture;
	
	public BackButton(Texture avatarTexture) {
		super(Assets.backButton, Main.centered.x - (Assets.backButton.getWidth() / 2), Main.centered.y - (Assets.personalSelected.getHeight() / 2) + Assets.backButton.getHeight(), Assets.backButton.getWidth(), Assets.backButton.getHeight());
	}
	
	@Override
	public void onClick() {
		super.onClick();
		Assets.click.play();
		Main.activeGame.switchState(new MainMenu(avatarTexture));
		HighscoreScreen.isDoneLoading = false;
	}
}
