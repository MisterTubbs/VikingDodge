package com.jbs.vikingdodge;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.jumpbuttonstudio.HighscoreResult;

public class Network {
	private static boolean loggedIn = false;
	private static boolean isAvatarDone = false;
	private static byte[] bytes;
	private static int numbytes;

	public static boolean checkLogin(String username, String password) {
		return Main.api.checkLogin(username, password);
	}
	
	public static boolean userIsConnected() {
		return userIsLoggedin() && Main.api.isConnected();
	}
	
	public static void setIsLoggedIn(boolean isLoggedIn){
		loggedIn = isLoggedIn; 
	}
	
	public static boolean userIsLoggedin() {
		return loggedIn;
	}
	
	public static void handlePrefs(String username, String password) {
		Main.prefs.putString("username", username);
		Main.prefs.putString("password", password);
		Main.prefs.flush();
	}
	
	public static void connectToNetwork(final String username, final String password) {
		new Thread(){
			public void run(){
				Network.setIsLoggedIn(checkLogin(username, password));

				if (Network.userIsConnected()) {
					Main.connectedToJBS = true;
					Main.username = username;
					Main.USER_ID = Main.api.getUserID();

					bytes  = new byte[200 * 1024];
					numbytes = ImageDownloader.download(bytes, Main.api.getUserAvatar(Main.USER_ID));
					
					Network.handlePrefs(username, password);
					isAvatarDone = true;
				}
			}
		}.start();
		
	}
	
	public static ArrayList<HighscoreResult> getGlobalHighScores() {
		return Main.api.getGlobalHighscores();
	}

	public static boolean isAvatarDone() {
		return isAvatarDone;
	}

	public static Texture processAvatar() {
		return ImageDownloader.processImage(bytes,numbytes);
	}
	
	public static void submitScore(int score) {
		Main.api.submitHighscore(score);
	}
	
	public static boolean isHighScore(int score) {
		return Main.api.getHighscore() < score;
	}
}
 