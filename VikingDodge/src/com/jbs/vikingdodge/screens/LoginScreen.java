package com.jbs.vikingdodge.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jbs.vikingdodge.InputProxy;
import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.Network;
import com.jbs.vikingdodge.Screen;
import com.jbs.vikingdodge.gui.BackButton;

public class LoginScreen implements Screen {

	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private TextButton loginButton;
	private TextField usernameField, passwordField;
	private BackButton backButton;

	@Override
	public void create() {
		backButton = new BackButton(null);
		createLoginField();
	}

	private void createLoginField() {
		stage = new Stage();
		if(Main.onAndroid)
			stage.setViewport(Main.screenSize.x / 2, Main.screenSize.y / 2, false);
		else 
			stage.setViewport(Main.screenSize.x, Main.screenSize.y, false);
		atlas = new TextureAtlas(Gdx.files.internal("ui/button.pack"));
		skin = new Skin(atlas);

		Table table = new Table(skin);
		table.setBounds(0, 0, stage.getWidth(), stage.getHeight());

		stage.addActor(table);

		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("button.up");
		textButtonStyle.down = skin.getDrawable("button.down");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = Main.arialFont;
		textButtonStyle.font.setScale(0.75f);

		TextFieldStyle textFieldStyle = new TextFieldStyle();
		textFieldStyle.background = skin.getDrawable("button.up");
		textFieldStyle.font = Main.arialFont;
		textFieldStyle.fontColor = Color.WHITE;
		textFieldStyle.font.setScale(0.5f);

		usernameField = new TextField("Username", textFieldStyle);

		passwordField = new TextField("Password", textFieldStyle);
		passwordField.setPasswordMode(true);

		loginButton = new TextButton("Login", textButtonStyle);
		loginButton.center();
		loginButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				// Handle Login Correct?
				String user = usernameField.getText();
				String pass = passwordField.getText();

				Network.connectToNetwork(user, pass);
				Main.activeGame.switchState(new MainMenu(Main.avatarTexture));

			}
		});

		table.add(usernameField);
		table.row();
		table.add(passwordField);
		table.row();
		table.add(loginButton);
		
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(SpriteBatch batch) {
		backButton.render(batch);
		renderLogin(batch);
	}

	private void renderLogin(SpriteBatch batch) {
		stage.getCamera().update();
		batch.setProjectionMatrix(stage.getCamera().combined);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void tick() {
		if (Gdx.input.isTouched() && backButton.inputIntersects(InputProxy.getTouch())) {
			backButton.onClick();
		}
	}

	@Override
	public void renderText(SpriteBatch batch) {
	}

	@Override
	public Color getClearColor() {
		return Color.BLACK;
	}
}
