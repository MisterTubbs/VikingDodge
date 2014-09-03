package com.jbs.vikingdodge.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.jbs.vikingdodge.Constants;
import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.Network;
import com.jbs.vikingdodge.Screen;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.assets.FontNumbers;
import com.jbs.vikingdodge.entity.EntityManager;
import com.jbs.vikingdodge.entity.mob.Clouds;
import com.jbs.vikingdodge.entity.mob.Dragon;
import com.jbs.vikingdodge.entity.mob.Player;
import com.jbs.vikingdodge.entity.mob.Rock;
import com.jbs.vikingdodge.entity.mob.Rock.Type;
import com.jbs.vikingdodge.entity.mob.Sheep;
import com.jbs.vikingdodge.gui.GameGUI;
import com.jbs.vikingdodge.gui.GameOver;
import com.jbs.vikingdodge.gui.Heart;
import com.jbs.vikingdodge.screens.PauseGUI;

public class Game implements Screen {

	public static int scoreMod = 1;
	public static int score = 0; 
	public static int dragonScore = 0;
	public static boolean paused, guiBlock, heartSpawnBlock, rockSpawnBlock, gameOver, rockSpawnRight, rockSpawnLeft, dragonSpawned, sheepText = true;
	
	private Clouds clouds;
	private EntityManager entityManager;
	private GameGUI gui;
	private PauseGUI pauseGUI;
	private GameOver overGUI;
	private FontNumbers font;
	private InputHandler input;
	
	private int rockSpawnChance = 200;
	
	public Game(Clouds clouds) {
		this.clouds = clouds;
	}
	
	@Override
	public void create() {
		entityManager = new EntityManager();
		gui = new GameGUI(this);
		pauseGUI = new PauseGUI(this);
		overGUI = new GameOver(this);
		font = new FontNumbers(30, Main.screenSize.y - 150);
		input = new InputHandler(this);
		Gdx.input.setInputProcessor(new GestureDetector(input));

		Timer.schedule(new Task() {
			@Override
			public void run() {
				if(!paused && !gameOver) {
					score += scoreMod;
					dragonScore += scoreMod;
				}
			}
		}, 1, 1);
		
		restart();
	}
	
	@Override
	public void render(SpriteBatch batch) {
		if(gameOver || paused)
			batch.setColor(0.5f, 0.5f, 0.5f, 1);
		else 
			batch.setColor(Color.WHITE);
		renderBackground(batch);
		clouds.render(batch);
		batch.draw(Assets.ground.getTexture(), 0, 0, Main.screenSize.x, Assets.ground.getHeight());
		entityManager.render(batch);
		if(paused) {
			batch.setColor(Color.WHITE);
			pauseGUI.render(batch);
		}
		else if(gameOver) {
			batch.setColor(Color.WHITE);
			overGUI.render(batch);
		}
	}
	
	private void renderBackground(SpriteBatch batch) {
		batch.draw(Assets.bg.getTexture(), 0, 0, Main.screenSize.x, Main.screenSize.y);
		batch.draw(Assets.rock01.getTexture(), -60, Assets.ground.getHeight() - 60, Assets.rock01.getWidth(), Assets.rock01.getHeight());
		batch.draw(Assets.rock03.getTexture(), 275, Assets.ground.getHeight() - 125, Assets.rock03.getWidth(), Assets.rock03.getHeight());
		batch.draw(Assets.rock02.getTexture(), 440, Assets.ground.getHeight() - 275, Assets.rock02.getWidth(), Assets.rock02.getHeight());
		batch.draw(Assets.rock04.getTexture(), 50, Assets.ground.getHeight() - 260, Assets.rock04.getWidth(), Assets.rock04.getHeight());
		batch.draw(Assets.rock05.getTexture(), 1100, Assets.ground.getHeight() - 100, Assets.rock05.getWidth(), Assets.rock05.getHeight());
		batch.draw(Assets.rock00.getTexture(), 725, Assets.ground.getHeight() - 160, Assets.rock00.getWidth(), Assets.rock00.getHeight());
		batch.draw(Assets.fog.getTexture(), 0, 0, Main.screenSize.x, Main.screenSize.y);
	}

	@Override
	public void tick() {
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) Player.lives = 0;
		if(!paused && !gameOver) {
			gui.tick();
			input.tick();
			entityManager.tick();
			spawnRocks();
			spawnHearts();
			clouds.tick();
			
			if(Player.lives <= 0) {
				gameOver = true;
				if(Main.connectedToJBS) Network.submitScore(Game.score);
			}
			if(Player.carryingSheepJesus) sheepText = false;
			if(dragonScore >= 70 + (Constants.rand.nextInt(10)) && !dragonSpawned) {
				entityManager.setDragon(new Dragon(this));
				dragonSpawned = true;
				dragonScore = 0;
			}
			
			if(dragonSpawned && !entityManager.getDragon().isAttacking() && !entityManager.getDragon().isExiting() && Constants.rand.nextInt(150) == 0) {
				entityManager.getDragon().attack();
			}
				
		} else if(paused) {
			pauseGUI.tick();
		} else if(gameOver) {
			overGUI.tick();
		}
		guiBlock = false;
	}
	
	private void spawnRocks() {
		if(rockSpawnBlock) return;
		if(Constants.rand.nextInt(250) == 0) {
			if(rockSpawnChance > 50) rockSpawnChance--;
		}
		if(Constants.rand.nextInt(rockSpawnChance) == 0) {
			Rock.Type t = null;
			switch(Constants.rand.nextInt(3)) {
			case 0:
				t = Rock.Type.TOP;
				break;
			case 1:
				t = Rock.Type.RIGHT;
				break;
			case 2:
				t = Rock.Type.LEFT;
				break;
			}
			if((t == Type.LEFT && !rockSpawnRight) || (t == Type.RIGHT && !rockSpawnLeft) || t == Type.TOP) {
				entityManager.add(new Rock(this, t));
				if(t == Type.LEFT) rockSpawnLeft = true;
				else if (t == Type.RIGHT) rockSpawnRight = true;
			}
		}
	}
	
	private void spawnHearts() {
		if(heartSpawnBlock) return;
		if(Constants.rand.nextInt(300) == 0) {
			entityManager.add(new Heart(Constants.rand.nextInt((int) Main.screenSize.x), true));
			Timer.schedule(new Task(){
				@Override
				public void run() {
					if(!paused);
					heartSpawnBlock = false;
				}
			}, Constants.rand.nextInt(10) + 20);
			heartSpawnBlock = true;
		}
	}

	@Override
	public void renderText(SpriteBatch textBatch) {
		if(gameOver) return;
		gui.render(textBatch);
		font.render(score, textBatch);
	}

	public void scheduleSheepSpawn(int range) {
		Timer.schedule(new Task() {
			@Override
			public void run() {
				int x = Constants.rand.nextInt((int) Main.screenSize.x);
				//10 is padding, 96 is the width of the sheep
				if(x <= 10) x = 10;
				if(x >= (int) Main.screenSize.x - 96 - 10) x = (int) Main.screenSize.x - 96 - 10;
				entityManager.add(new Sheep(x, Main.screenSize.y + 200));
			}
		}, Constants.rand.nextInt(range));
	}
	
	public void restart() {
		guiBlock = false;
		paused = false;
		gameOver = false;
		dragonSpawned = false;
		score = 0;
		scoreMod = 1;
		Player.lives = 3;
		dragonScore = 0;
		
		entityManager.reset();
		entityManager.setPlayer(new Player(this, Main.centered.x, Main.centered.y));
		
		scheduleSheepSpawn(1);
	}
	
	private void gameOver() {
		for(int i = Main.personalScores.length - 1; i >= 0; i--) {
			System.out.println(i + " , " + Main.personalScores[i] + " , " + score);
			if(Main.personalScores[i] < score) {
				Main.personalScores[i] = score;
				Main.prefs.putInteger("score" + i, score);
				Main.prefs.flush();
			}
		}
	}
	
	@Override
	public Color getClearColor() {
		return Color.GRAY;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public InputHandler getInput() {
		return input;
	}
	
	public GameGUI getGameGUI() {
		return gui;
	}
}
