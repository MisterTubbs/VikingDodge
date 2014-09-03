package com.jbs.vikingdodge.gui;

import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.screens.MainMenu;

public class LoginButton extends TexturedButton {
	
	public LoginButton() {
		super(Assets.login, Main.centered.x + (Assets.login.getWidth()) + 25, getCentered(Assets.playButton.getWidth(), Assets.playButton.getHeight()).y - 140, Assets.login.getWidth(), Assets.login.getHeight());
	}
	
	@Override
	public void onClick() {
		Assets.click.play();
		MainMenu.renderLogin = true;
	}
}
