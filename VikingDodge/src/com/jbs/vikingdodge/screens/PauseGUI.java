package com.jbs.vikingdodge.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jbs.vikingdodge.GameObject;
import com.jbs.vikingdodge.InputProxy;
import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.assets.TextureAsset;
import com.jbs.vikingdodge.game.Game;
import com.jbs.vikingdodge.gui.Button;
import com.jbs.vikingdodge.gui.ExitButton;
import com.jbs.vikingdodge.gui.RestartButton;
import com.jbs.vikingdodge.gui.ResumeButton;

public class PauseGUI implements GameObject {

	private Button[] buttons;
	private TextureAsset pauseWindow;
	
	public PauseGUI(Game game) {
		pauseWindow = Assets.pauseWindow;
		buttons = new Button[3];
		buttons[0] = new ExitButton();
		buttons[1] = new ResumeButton();
		buttons[2] = new RestartButton(game);
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(pauseWindow.getTexture(), Main.centered.x - pauseWindow.getWidth() / 2, Main.centered.y - pauseWindow.getHeight() / 2, pauseWindow.getWidth(), pauseWindow.getHeight());
		for(Button b : buttons) 
			b.render(batch);
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
}
