package com.jbs.vikingdodge;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.screens.HighscoreScreen;
import com.jumpbuttonstudio.logger.Log;

public class HighscoreEntry implements Renderable {
	
	public static int firstPos;
	
	public String username = "";
	public int user_id = -1;
	public int score = 0;
	public Texture avatar = null;
	
	private boolean isAvatarDone = false;
	private byte[] bytes;
	private int numBytes;
	private int index;
	
	public HighscoreEntry(int i) {
		this.index = i;
	}
	
	public void downloadAvatar() {
		bytes = new byte[200 * 1024];
		numBytes = ImageDownloader.download(bytes, Main.api.getUserAvatar(user_id));
		isAvatarDone = true;
	}
	
	public boolean isAvatarReady(){
		return isAvatarDone;
	}
	
	public Texture processAvatar(){
		Log.info("HighscoreEntry","Processing Avatar for " + username);
		if (numBytes != 0) {
			// load the pixmap, make it a power of two if necessary (not needed
			// for GL ES 2.0!)
			Pixmap pixmap = new Pixmap(bytes, 0, numBytes);
			int width = MathUtils.nextPowerOfTwo(pixmap.getWidth());
			int height = MathUtils.nextPowerOfTwo(pixmap.getHeight());
			final Pixmap potPixmap = new Pixmap(width, height, pixmap.getFormat());
			potPixmap.drawPixmap(pixmap, 0, 0, 0, 0, pixmap.getWidth(), pixmap.getHeight());
			pixmap.dispose();

			return new Texture(potPixmap);
		}
		return null;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Assets.highScoreEntry.getTexture(), Main.centered.x - (Assets.highScoreEntry.getWidth() / 2), 525 + (index * -60));
		Main.menuFont.render(batch, "" + index + ".", Main.centered.x - (Assets.highScoreEntry.getWidth() / 2) + 20, 570 + (index * -60));
		batch.draw(HighscoreScreen.globalSelected ? Main.avatarTexture : avatar, Main.centered.x - (Assets.highScoreEntry.getWidth() / 2) + 161, 516 + (index * -60), 62, 62);
		Main.menuFont.render(batch, HighscoreScreen.globalSelected ? Main.username.toUpperCase() : username.toUpperCase(), Main.centered.x - (Assets.highScoreEntry.getWidth() / 2) + 240, 570 + (index * -60));
		Main.menuFont.render(batch, "" + (HighscoreScreen.globalSelected ? Main.personalScores[index] : score), Main.centered.x - (Assets.highScoreEntry.getWidth() / 2) + 1025, 570 + (index * -60));
	}
}