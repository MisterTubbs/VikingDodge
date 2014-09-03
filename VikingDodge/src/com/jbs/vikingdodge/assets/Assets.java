package com.jbs.vikingdodge.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jumpbuttonstudio.logger.Log;

public class Assets {
	
	public static TextureAsset ground, rock00, rock01, rock02, rock03, rock04, rock05, rock, rockBlur, menuBG;
	public static TextureAsset fog, bg, c00, c01, c02, c03;
	public static TextureAsset heart, emptyHeart, heartRay;
	public static TextureAsset pauseWindow, gameOverWindow, playButton, throwSheep, shadow, jump, pauseButton, exitButton, resumeButton, restartButton, logo, playAgain, 
		gameOverExit, login, highScores, backButton, logoutButton, star, newBest, groundBurn, sign0, sign1, userPicture, welcomeBar, loginWindow, loginWindowEffect, okButton,
		highScoreEntry, globalSelected, personalSelected;
	public static TextureAsset rightArrow, leftArrow;
	
	public static TextureRegion[][] numbers;
	
	//animation sets
	public static AnimationSet playerStand, playerRun, playerLeap, playerJump, playerStandSheep, playerRunSheep, playerThrowSheep;
	public static AnimationSet sheepStand;
	public static AnimationSet dragonFly, dragonFire, dragonPostFire, dragonRock, hitEffect;
	public static AnimationSet oneUp;
	public static AnimationSet rockDust;
	public static AnimationSet jbsLoading;
	
	//Sounds
	public static SoundEffect click, dash, highScore, land, launch, throw0, throw1, explode;
	
	public static void load() {
		ground = TextureAsset.loadTexture("ground/Ground.png");
		menuBG = TextureAsset.loadTexture("ui/Background.png");
		rock = TextureAsset.loadTexture("object/rock.png");
		rockBlur = TextureAsset.loadTexture("object/blureffect.png");
		rock00 = TextureAsset.loadTexture("bg/bg00.png");
		rock01 = TextureAsset.loadTexture("bg/bg01.png");
		rock02 = TextureAsset.loadTexture("bg/bg02.png");
		rock03 = TextureAsset.loadTexture("bg/bg03.png");
		rock04 = TextureAsset.loadTexture("bg/bg04.png");
		rock05 = TextureAsset.loadTexture("bg/bg05.png");
		bg = TextureAsset.loadTexture("bg/bg.png");
		fog = TextureAsset.loadTexture("bg/fog.png");
		c00 = TextureAsset.loadTexture("bg/cloud00.png");
		c01 = TextureAsset.loadTexture("bg/cloud01.png");
		c02 = TextureAsset.loadTexture("bg/cloud02.png");
		c03 = TextureAsset.loadTexture("bg/cloud03.png");
		heart = TextureAsset.loadTexture("ui/heart.png");
		emptyHeart = TextureAsset.loadTexture("ui/heartempty.png");
		heartRay = TextureAsset.loadTexture("sprite/heart/ray.png");
		shadow = TextureAsset.loadTexture("sprite/shadow.png");
		playButton = TextureAsset.loadTexture("ui/menuButtons/play.png");
		jump = TextureAsset.loadTexture("ui/jumpclicked.png");
		throwSheep = TextureAsset.loadTexture("ui/throwclicked.png");
		pauseButton = TextureAsset.loadTexture("ui/pauseclicked.png");
		exitButton = TextureAsset.loadTexture("ui/exit.png");
		resumeButton = TextureAsset.loadTexture("ui/resume.png");
		restartButton = TextureAsset.loadTexture("ui/restart.png");
		playAgain = TextureAsset.loadTexture("ui/playagain.png");
		gameOverExit = TextureAsset.loadTexture("ui/exitbutton.png");
		pauseWindow = TextureAsset.loadTexture("ui/pausewindow.png");
		gameOverWindow = TextureAsset.loadTexture("ui/window.png");
		login = TextureAsset.loadTexture("ui/menuButtons/login.png");
		highScores = TextureAsset.loadTexture("ui/menuButtons/highscores.png");
		backButton = TextureAsset.loadTexture("ui/menuButtons/back.png");
		logoutButton = TextureAsset.loadTexture("ui/logout.png");
		star = TextureAsset.loadTexture("ui/star.png");
		newBest = TextureAsset.loadTexture("ui/newbest.png");
		groundBurn = TextureAsset.loadTexture("ground/groundBurn.png");
		sign0 = TextureAsset.loadTexture("sprite/sheeptext.png");
		sign1 = TextureAsset.loadTexture("sprite/sheeptext1.png");
		rightArrow = TextureAsset.loadTexture("ui/arrowclick1.png");
		leftArrow = TextureAsset.loadTexture("ui/arrowclick.png");
		logo = TextureAsset.loadTexture("ui/Logo.png");
		userPicture = TextureAsset.loadTexture("ui/menu/user.png");
		welcomeBar = TextureAsset.loadTexture("ui/menu/bar.png");
		loginWindow = TextureAsset.loadTexture("ui/menu/login.png");
		loginWindowEffect = TextureAsset.loadTexture("ui/menu/effect.png");
		okButton = TextureAsset.loadTexture("ui/menuButtons/ok.png");
		highScoreEntry = TextureAsset.loadTexture("ui/highscore/entry.png");
		globalSelected = TextureAsset.loadTexture("ui/highscore/personal.png");
		personalSelected = TextureAsset.loadTexture("ui/highscore/global.png");

		playerStand = AnimationSet.load("sprite/stand.png", 120, 160, 3, 0);
		playerRun = AnimationSet.load("sprite/run.png", 140, 185, 7, 0);
		playerLeap = AnimationSet.load("sprite/leap.png", 200, 180, 4, 0);
		playerJump = AnimationSet.load("sprite/jump.png", 138, 151, 1, 0);
		playerStandSheep = AnimationSet.load("sprite/standwsheep.png", 105, 169, 3, 0);
		playerRunSheep = AnimationSet.load("sprite/runwsheep.png", 130, 200, 8, 0);
		playerThrowSheep = AnimationSet.load("sprite/throwsheep.png", 200, 200, 3, 0);
		
		sheepStand = AnimationSet.load("sprite/sheepstand.png", 96, 79, 3, 0);
		
		dragonFly = AnimationSet.load("boss/dragon/fly.png", 455, 483, 8, 0);
		dragonFire = AnimationSet.load("boss/dragon/fire.png", 455, 483, 3, 0);
		dragonPostFire = AnimationSet.load("boss/dragon/postFire.png", 455, 483, 4, 0);
		dragonRock = AnimationSet.load("boss/dragon/rock.png", 250, 250, 4, 0);
		hitEffect = AnimationSet.load("boss/dragon/hiteffect.png", 320, 270, 2, 2);
		
		oneUp = AnimationSet.load("sprite/heart/regain.png", (int) 1300 / 7, 186, 6, 0);
		
		rockDust = AnimationSet.load("object/dust.png", 450, 150, 2, 0);
		
		jbsLoading = AnimationSet.load("ui/highscore/loading.png", 46, 54, 6, 0);
		
		numbers = TextureRegion.split(new Texture("ui/score.png"), 50, 59);
		
		click = new SoundEffect("sounds/click.wav");
		throw0 = new SoundEffect("sounds/throw.wav");
		throw1 = new SoundEffect("sounds/throw1.wav");
		dash = new SoundEffect("sounds/dash.wav");
		highScore = new SoundEffect("sounds/highscore.wav");
		land = new SoundEffect("sounds/landing.wav");
		launch = new SoundEffect("sounds/launch.wav");
		explode = new SoundEffect("sounds/explode.wav");
		
		Log.info("VD", "Assets loaded");
	}
}
