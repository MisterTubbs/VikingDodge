package com.jbs.vikingdodge.gui;

import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.screens.MainMenu;

public class PlayButton extends TexturedButton {

	private MainMenu menu;
	
	public PlayButton(MainMenu menu) {
		super(Assets.playButton, getCentered(Assets.playButton.getWidth(), Assets.playButton.getHeight()).x, getCentered(Assets.playButton.getWidth(), Assets.playButton.getHeight()).y - 140, Assets.playButton.getWidth(), Assets.playButton.getHeight());
		this.menu = menu;
	}

	@Override
	public void onClick() {
		Assets.click.play();
		menu.fadeMenu();
	}
}
