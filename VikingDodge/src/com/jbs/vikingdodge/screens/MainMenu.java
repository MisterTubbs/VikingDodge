package com.jbs.vikingdodge.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.jbs.vikingdodge.InputProxy;
import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.Network;
import com.jbs.vikingdodge.Screen;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.entity.mob.Clouds;
import com.jbs.vikingdodge.game.Game;
import com.jbs.vikingdodge.gui.Button;
import com.jbs.vikingdodge.gui.HighScoreButton;
import com.jbs.vikingdodge.gui.LoginButton;
import com.jbs.vikingdodge.gui.PlayButton;
import com.jbs.vikingdodge.gui.UserSlider;

public class MainMenu implements Screen {

	public static boolean readyToPlay, fading, renderLogin;
	public static float fade = 1;

	private Button buttons[];
	private Texture avatarTexture;
	private UserSlider userSlider;
	private boolean renderSlider;
	private TextField username, password;
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;

	public MainMenu(Texture avatarTexture) {
		this.avatarTexture = avatarTexture;
		buttons = new Button[3];
	}

	@Override
	public void create() {
		stage = new Stage();
		if(Main.onAndroid)
			stage.setViewport(Main.screenSize.x / 2, Main.screenSize.y / 2, false);
		else 
			stage.setViewport(Main.screenSize.x, Main.screenSize.y, false);
		buttons[0] = new PlayButton(this);
		buttons[1] = new HighScoreButton();
		buttons[2] = new LoginButton();
		
		atlas = new TextureAtlas(Gdx.files.internal("ui/button.pack"));
		skin = new Skin(atlas);

		Table table = new Table(skin);
		table.setBounds(0, 0, stage.getWidth(), stage.getHeight());
		
		stage.addActor(table);
		
		TextFieldStyle textFieldStyle = new TextFieldStyle();
		textFieldStyle.background = skin.getDrawable("button.down");
		textFieldStyle.font = Main.arialFont;
		textFieldStyle.fontColor = Color.BLACK;
		textFieldStyle.font.setScale(0.5f);

		username = new TextField("Username", textFieldStyle);
		password = new TextField("Password", textFieldStyle);
		password.setPasswordMode(true);
	
		table.add(username).padBottom(25);
		table.row();
		table.add(password);
		table.padBottom(160);
		
		table.debug();
		
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.setColor(1, 1, 1, 1);
		batch.draw(Assets.menuBG.getTexture(), 0, 0, Main.screenSize.x, Main.screenSize.y);
		batch.setColor(1, 1, 1, 1 / fade);
		batch.draw(Assets.logo.getTexture(), Button.getCentered(Assets.logo.getWidth(), Assets.logo.getHeight()).x, Button.getCentered(Assets.logo.getWidth(), Assets.logo.getHeight()).y + 200, Assets.logo.getWidth(), Assets.ground.getHeight());
		for (Button b : buttons)
			b.render(batch);

		if (renderSlider)
			userSlider.render(batch);

		if (renderLogin)
			renderLogin(batch);

		if (fading)
			fade++;
		if (fade >= 10 && fading) {
			fading = false;
			batch.setColor(1, 1, 1, 1);
			Main.activeGame.switchState(new Game(new Clouds()));
			fade = 1;
		}
	}

	@Override
	public void tick() {
		if (!renderLogin) {
			for (Button b : buttons) {
				if (Gdx.input.justTouched() && b.inputIntersects(InputProxy.getTouch())) {
					b.onClick();
				}
			}
		}
		if (renderSlider)
			userSlider.tick();
		if (renderLogin)
			updateLoginOverlay();
	}

	@Override
	public void renderText(SpriteBatch textBatch) {
		if (Network.userIsConnected()) {
			if (avatarTexture == null) {
				if (Network.isAvatarDone()) {
					Main.avatarTexture = Network.processAvatar();
					avatarTexture = Main.avatarTexture;
					renderSlider = true;
					userSlider = new UserSlider(avatarTexture);
				}
			}
		}
	}

	private void renderLogin(SpriteBatch batch) {
		batch.draw(Assets.loginWindowEffect.getTexture(), 0, 0);
		batch.draw(Assets.loginWindow.getTexture(), Main.centered.x - (Assets.loginWindow.getWidth() / 2), Main.centered.y - (Assets.login.getHeight() / 2) - 150);
		batch.draw(Assets.okButton.getTexture(), Main.centered.x - (Assets.okButton.getWidth() / 2), Main.centered.y - (Assets.loginWindow.getHeight() / 2) + (Assets.okButton.getHeight() / 2) + 20);
		batch.end();
		batch.begin();
		stage.getCamera().update();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}	

	private void updateLoginOverlay() {
		if(Gdx.input.justTouched()) {
			Vector2 pos = InputProxy.getTouch();
		}
	}

	public void fadeMenu() {
		fading = true;
	}

	@Override
	public Color getClearColor() {
		return Color.GRAY;
	}
}
