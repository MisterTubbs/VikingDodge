package com.jbs.vikingdodge.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.jbs.vikingdodge.HighscoreEntry;
import com.jbs.vikingdodge.InputProxy;
import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.Screen;
import com.jbs.vikingdodge.assets.Animation;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.entity.Collision;
import com.jbs.vikingdodge.gui.BackButton;
import com.jbs.vikingdodge.gui.Button;
import com.jbs.vikingdodge.gui.LogoutButton;
import com.jumpbuttonstudio.HighscoreResult;
import com.jumpbuttonstudio.logger.Log;

public class HighscoreScreen implements Screen {

	private ArrayList<HighscoreEntry> scores;
	private Button buttons[];
	public static boolean isDoneLoading, globalSelected = true, personalSelected;
	private Rectangle globalRect, personalRect;
	private Texture avatarTexture;
	private Animation loadingAnim;

	public HighscoreScreen(Texture avatarTexture) {
		this.avatarTexture = avatarTexture;
		buttons = new Button[1];
	}

	@Override
	public void create() {
		loadingAnim = Animation.jbsLoading;
		globalRect = new Rectangle(141, 611, 415, 76);
		personalRect = new Rectangle(482, 611, 415, 76);

		buttons[0] = new BackButton(avatarTexture);

		scores = new ArrayList<HighscoreEntry>();

		new Thread() {
			public void run() {
				ArrayList<HighscoreResult> results = Main.api.getGlobalHighscores();
				for (int i = 0; i < results.size(); i++) {
					// Parse Each
					HighscoreEntry he = new HighscoreEntry(i);
					he.username = results.get(i).username;
					he.score = results.get(i).score;
					he.user_id = results.get(i).user_id;
					he.downloadAvatar();
					scores.add(he);
					Log.info("LoginScreen", "Added Highscore Entry");
				}
				isDoneLoading = true;
			}
		}.start();
	}

	@Override
	public void render(SpriteBatch batch) {
		if (globalSelected)
			batch.draw(Assets.globalSelected.getTexture(), Main.centered.x - (Assets.globalSelected.getWidth() / 2), Main.centered.y - (Assets.globalSelected.getHeight() / 2));
		else if (personalSelected)
			batch.draw(Assets.personalSelected.getTexture(), Main.centered.x - (Assets.globalSelected.getWidth() / 2), Main.centered.y - (Assets.globalSelected.getHeight() / 2));
	}

	@Override
	public void tick() {
		for (Button b : buttons) {
			if (Gdx.input.isTouched() && b.inputIntersects(InputProxy.getTouch())) {
				b.onClick();
			}
		}
		if (Gdx.input.justTouched()) {
			if (Collision.pointToRect(InputProxy.getTouch(), globalRect) && !globalSelected) {
				globalSelected = true;
				personalSelected = false;
			}
			if (Collision.pointToRect(InputProxy.getTouch(), personalRect) && !personalSelected) {
				personalSelected = true;
				globalSelected = false;
			}
		}
	}

	@Override
	public void renderText(SpriteBatch batch) {
		if (isDoneLoading) {
			for(HighscoreEntry he : scores) {
				if(he.avatar != null) {
					he.render(batch);
				} else if(he.isAvatarReady()){
					he.avatar = he.processAvatar();
				}
			}
			for(Button b : buttons)
				b.render(batch);
		} else {
			loadingAnim.tick();
			batch.draw(loadingAnim.getCurrentTexture(), Main.centered.x - 23, Main.centered.y - 23, 92, 92);
		}
		/*
		 * if (isDoneLoading) { // Render Highscores for (int i = 0; i <
		 * scores.size(); i++) {
		 * 
		 * if (scores.get(i).avatar != null) { // Draw Avatar
		 * batch.draw(scores.get(i).avatar, Main.centered.x - 200,
		 * Main.screenSize.y - 300 - (i * 110), 100, 100); } else { if
		 * (scores.get(i).isAvatarReady()) { scores.get(i).avatar =
		 * scores.get(i).processAvatar(); } }
		 * 
		 * Main.arialFont.draw(batch, scores.get(i).username, Main.centered.x -
		 * 95, Main.screenSize.y - 245 - (i * 110)); Main.arialFont.draw(batch,
		 * "" + scores.get(i).score, Main.centered.x + 100, Main.screenSize.y -
		 * 245 - (i * 110)); } }
		 */
	}

	@Override
	public Color getClearColor() {
		return Color.BLACK;
	}
}
