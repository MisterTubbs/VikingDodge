package com.jbs.vikingdodge;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.assets.Font;
import com.jbs.vikingdodge.entity.mob.Clouds;
import com.jbs.vikingdodge.game.Game;
import com.jbs.vikingdodge.screens.MainMenu;
import com.jumpbuttonstudio.api.API;
import com.jumpbuttonstudio.logger.Log;

public class Main implements ApplicationListener, Tickable {

	public static final Vector2 screenSize = new Vector2(1280, 720);
	public static final Vector2 centered = new Vector2(screenSize.x / 2, screenSize.y / 2);
	public static OrthographicCamera camera, textCamera;
	public static Main activeGame;
	public static Vector2 actualScreenSize;
	public static float aspectRatio;
	public static boolean onAndroid, renderHighScoreTable;
	public static ShapeRenderer shape;
	public static BitmapFont arialFont;
	public static Font menuFont, blackMenuFont;

	private SpriteBatch batch, textBatch;
	private Color currentClearColor;
	private GameObject currentState;

	// API stuffs
	public static API api;
	public static int USER_ID;
	public static String username;
	public static boolean connectedToJBS;
	public static Texture avatarTexture;
	public static int personalScores[];

	public static Preferences prefs;

	public Main(boolean onAndroid) {
		Main.onAndroid = onAndroid;
	}

	@Override
	public void create() {
		Log.DEBUG();
		Log.debug("GAME", "Debugger Running");
		
		Texture.setEnforcePotImages(false);
		Assets.load();
		Clouds.load();

		api = new API("902ba3cda115fd9bda5938ed1f0ceefae4fe4cd928ae6f934c2abd9867cad9eeee50033bf1d4310baa0c3c2aed", "tubby", 7);
		api.connect();
		
		prefs = Gdx.app.getPreferences("VDPrefs");
		handlePrefs();
		/*
		 * if(api.checkLogin("account_username _here","account_password_here")){
		 * // It returns boolean is login is good or badd. // Then you will get
		 * a ID of the user. USER_ID = api.getUserID();
		 * 
		 * // To get old highscore int highscore = api.getHighscore(USER_ID,
		 * GAME_ID);
		 * 
		 * // To submit new score int score = 10000;
		 * api.submitHighscore(USER_ID, GAME_ID, score);
		 * 
		 * // Pretty easy API eh? yeah :D }
		 */

		arialFont = new BitmapFont(Gdx.files.internal("fonts/arial.fnt"), Gdx.files.internal("fonts/arial.png"), false);
		menuFont = new Font("fonts/menu/comix");
		blackMenuFont = new Font("fonts/menu/comixBlack");
		
		shape = new ShapeRenderer();

		activeGame = this;

		actualScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		aspectRatio = screenSize.y / screenSize.x;
		camera = new OrthographicCamera(actualScreenSize.x, actualScreenSize.x * aspectRatio);
		camera.setToOrtho(false, (int) screenSize.x, (int) screenSize.y);

		textCamera = new OrthographicCamera(actualScreenSize.x, actualScreenSize.x * aspectRatio);
		textCamera.setToOrtho(false, (int) screenSize.x, (int) screenSize.y);

		switchState(new MainMenu(avatarTexture));

		batch = new SpriteBatch();
		textBatch = new SpriteBatch();
	}

	private void handlePrefs() {
		personalScores = new int[3];
 
		String username = prefs.getString("username", "NULL");
		String password = prefs.getString("password", "NULL");

		for(int i = 0; i < 3; i++) 
			personalScores[i] = prefs.getInteger("score" + i, 0);
		
		if (username != "NULL" && password != "NULL") {
			Network.connectToNetwork(username, password);
		}
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(currentClearColor.r, currentClearColor.g, currentClearColor.b, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		tick();

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		currentState.render(batch);
		batch.end();
		
		/*
		 * shape.setProjectionMatrix(camera.combined); if (currentState
		 * instanceof Game) { for (Entity e : ((Game)
		 * currentState).getEntityManager().getEntities()) { if (e instanceof
		 * Rock) { Rock r = ((Rock) e); Main.shape.begin(ShapeType.Circle);
		 * Main.shape.circle(r.getBoundingCircle().x, r.getBoundingCircle().y,
		 * r.getBoundingCircle().radius); Main.shape.end(); } } }
		 */

		if (currentState instanceof Screen) {
			textCamera.update();
			textBatch.setProjectionMatrix(textCamera.combined);
			textBatch.begin();
			((Screen) currentState).renderText(textBatch);
			textBatch.end();
		}
	}

	@Override
	public void tick() {
		currentState.tick();
	}

	public void switchState(GameObject obj) {
		currentState = obj;
		getClearColor(obj);
	}

	public void getClearColor(GameObject obj) {
		if (obj instanceof Screen) {
			currentClearColor = ((Screen) obj).getClearColor();
			((Screen) currentState).create();
		}
	}

	@Override
	public void resize(int width, int height) {
		actualScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void pause() {
		if (currentState instanceof Game) {
			Game.paused = true;
			Timer.instance.stop();
		}
	}

	@Override
	public void resume() {
		if (currentState instanceof Game) {
			Game.paused = false;
			Timer.instance.start();
		}
	}
}
