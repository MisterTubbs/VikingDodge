package com.jbs.vikingdodge.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jbs.vikingdodge.GameObject;
import com.jbs.vikingdodge.InputProxy;
import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.Network;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.assets.FontNumbers;
import com.jbs.vikingdodge.assets.TextureAsset;
import com.jbs.vikingdodge.game.Game;

public class GameOver implements GameObject {

	private Game game;
	private Button[] buttons;
	private TextureAsset window, star;
	private FontNumbers font;
	private boolean isHighScore;

	public GameOver(Game game) {
		this.game = game;
		this.buttons = new Button[2];
		buttons[0] = new ExitOverButton();
		buttons[1] = new RestartOverButton(game);
		star = Assets.star;
		window = Assets.gameOverWindow;
		font = new FontNumbers(Main.centered.x, (Main.centered.y - window.getHeight() / 2) + 180);
		
		isHighScore = Network.isHighScore(Game.score);
		if(isHighScore) Assets.highScore.play();
	}

	@Override
	public void tick() {
		for(int i = 0; i < 20; i++) {
			for(Button b : buttons) {
				if(Gdx.input.isTouched(i) && b.inputIntersects(InputProxy.getTouch(i))) {
					b.onClick();
				}
			}
		}
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(window.getTexture(), Main.centered.x - window.getWidth() / 2, Main.centered.y - window.getHeight() / 2, window.getWidth(), window.getHeight());
		renderStars(batch);
		for(Button b : buttons) 
			b.render(batch);
		if(isHighScore) {
			batch.draw(Assets.newBest.getTexture(), (Main.centered.x - Assets.newBest.getWidth() / 2), (Main.centered.y - window.getHeight() / 2) + 220);
		}
		String s_score = String.valueOf(Game.score);
		int xOffset = (s_score.length() - 1) * 50;
		if(xOffset == 0)
			xOffset = 23;
		if((s_score.length() - 1) == 2)
			xOffset = 78;
		font.setPos((Main.centered.x) - xOffset, (Main.centered.y - window.getHeight() / 2) + 160);
		font.render(Game.score, batch);
	}
	
	private void renderStars(SpriteBatch batch) {
		if(Game.score >= 50) batch.draw(star.getTexture(), (Main.centered.x - window.getWidth() / 2) + 60, (Main.centered.y - window.getHeight() / 2) + 207, star.getWidth(), star.getHeight());
		if(Game.score >= 150) batch.draw(star.getTexture(), (Main.centered.x - window.getWidth() / 2) + 154, (Main.centered.y - window.getHeight() / 2) + 248, star.getWidth(), star.getHeight());
		if(Game.score >= 300) batch.draw(star.getTexture(), (Main.centered.x - window.getWidth() / 2) + 248, (Main.centered.y - window.getHeight() / 2) + 207, star.getWidth(), star.getHeight());
	}
}
