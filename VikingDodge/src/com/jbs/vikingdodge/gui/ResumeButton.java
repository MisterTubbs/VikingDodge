package com.jbs.vikingdodge.gui;

import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.game.Game;

public class ResumeButton extends TexturedButton {

	public ResumeButton() {
		super(Assets.resumeButton, Main.centered.x - Assets.resumeButton.getWidth() / 2, Main.centered.y - Assets.resumeButton.getHeight() / 2 + Assets.resumeButton.getHeight() / 2, Assets.resumeButton.getWidth(), Assets.resumeButton.getHeight());
	}

	@Override
	public void onClick() {
		super.onClick();
		Assets.click.play();
		Game.paused = false;
	}
}
