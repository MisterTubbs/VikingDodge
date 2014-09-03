package com.jbs.vikingdodge.gui;

import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.screens.HighscoreScreen;

public class HighScoreButton extends TexturedButton {

	public HighScoreButton() {
		super(Assets.highScores, Main.centered.x - (Assets.highScores.getWidth() * 2), getCentered(Assets.playButton.getWidth(), Assets.playButton.getHeight()).y - 140, Assets.highScores.getWidth(), Assets.highScores.getHeight());
	}
	
	@Override
	public void onClick() {
		Assets.click.play();
		Main.activeGame.switchState(new HighscoreScreen(Main.avatarTexture));
	}
}
