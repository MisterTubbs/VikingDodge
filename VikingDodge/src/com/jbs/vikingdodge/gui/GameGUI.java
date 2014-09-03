package com.jbs.vikingdodge.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jbs.vikingdodge.GameObject;
import com.jbs.vikingdodge.InputProxy;
import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.entity.mob.Player;
import com.jbs.vikingdodge.game.Game;

public class GameGUI implements GameObject {

	private Button buttons[];
	private Heart hearts[];
	private Game game;

	public GameGUI(Game game) {
		this.game = game;
		buttons = new Button[5];
		buttons[0] = new JumpButton(game);
		buttons[1] = new SheepButton(game);
		buttons[2] = new PauseButton();
		buttons[3] = new LeftButton(game);
		buttons[4] = new RightButton(game);

		hearts = new Heart[3];
		for (int i = 0; i < 3; i++)
			hearts[i] = new Heart(20 + (i * Assets.heart.getWidth()), false);
	}

	@Override
	public void render(SpriteBatch batch) {
		if (Main.onAndroid) {
			for (Button b : buttons)
				b.render(batch);
		}
		buttons[2].render(batch);
		for (Heart h : hearts)
			h.render(batch);
	}

	@Override
	public void tick() {
		if (Main.onAndroid) {
			for (int i = 0; i < 20; i++) {
				for (Button b : buttons) {
					if (Gdx.input.isTouched(i) && b.inputIntersects(InputProxy.getTouch(i))) {
						b.onClick();
					}
				}
			}
		}
		if (Gdx.input.isTouched() && buttons[2].inputIntersects(InputProxy.getTouch())) {
			buttons[2].onClick();
		}
		for (int i = 0; i < 3; i++) {
			if (i + 1 <= Player.lives) {
				hearts[i].setFull();
			} else
				hearts[i].setEmpty();
		}
	}
	
	public Button[] getButton() {
		return buttons;
	}
}
