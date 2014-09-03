package com.jbs.vikingdodge.gui;

import com.badlogic.gdx.graphics.Texture;
import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.Network;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.screens.MainMenu;

public class LogoutButton extends TexturedButton {

	private Texture avatarTexture;
	
	public LogoutButton(Texture avatarTexture) {
		super(Assets.logoutButton, (Main.screenSize.x - Assets.logoutButton.getWidth()) - 10, 10, Assets.logoutButton.getWidth(), Assets.logoutButton.getHeight());
	}

	@Override
	public void onClick() {
		Assets.click.play();
		if(Main.connectedToJBS) {
			Main.prefs.putString("username", "NULL");
			Main.prefs.putString("password", "NULL");
			Main.prefs.flush();
			
			Main.username = "NULL";
			Main.USER_ID = -1;
			Main.connectedToJBS = false;
			Network.setIsLoggedIn(false);
			
			Main.activeGame.switchState(new MainMenu(avatarTexture));
		}
	}
}
