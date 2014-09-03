package com.jbs.vikingdodge.gui;

import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.assets.Assets;

public class ExitButton extends TexturedButton {

	public ExitButton() {
		super(Assets.exitButton, Main.centered.x - Assets.exitButton.getWidth() / 2, Main.centered.y - Assets.exitButton.getHeight() / 2 - Assets.exitButton.getHeight() * 1.5f, Assets.exitButton.getWidth(), Assets.exitButton.getHeight());
	}

	@Override
	public void onClick() {
		Assets.click.play();
		System.exit(0);
	}
}
