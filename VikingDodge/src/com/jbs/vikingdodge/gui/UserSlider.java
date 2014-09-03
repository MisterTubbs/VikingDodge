package com.jbs.vikingdodge.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.jbs.vikingdodge.GameObject;
import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.assets.Assets;

public class UserSlider implements GameObject {
	
	private Texture avatar;
	private float y, yLimit;
	private boolean showing;
	
	public UserSlider(Texture avatar) {
		this.avatar = avatar;
		y = Main.screenSize.y;
		yLimit = Main.screenSize.y - Assets.welcomeBar.getHeight();
		showing = true;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Assets.welcomeBar.getTexture(), 0, y, Assets.welcomeBar.getWidth(), Assets.welcomeBar.getHeight());
		batch.draw(Assets.userPicture.getTexture(), 200, y + (Assets.userPicture.getHeight() / 2) + 4);
		batch.draw(avatar, 209, y + (Assets.userPicture.getHeight() / 2) - 20, 135, 135);
		Main.blackMenuFont.render(batch, "WELCOME BACK " + new String(Main.username).toUpperCase() + "!", 350 , y + 150);
	}

	@Override
	public void tick() {
		if(showing) {
			if(y > yLimit) y -= 5;
			else if(y <= yLimit) {
				Timer.schedule(new Task() {
					@Override
					public void run() {
						showing = false;
					}
				}, 5);
			}
		} else {
			y += 5;
		}
	}
}
