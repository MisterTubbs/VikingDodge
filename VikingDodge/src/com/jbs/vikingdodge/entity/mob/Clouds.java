package com.jbs.vikingdodge.entity.mob;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.jbs.vikingdodge.Constants;
import com.jbs.vikingdodge.GameObject;
import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.assets.TextureAsset;

public class Clouds implements GameObject {

	public static TextureAsset c00, c01, c02, c03;
	
	private static List<Cloud> clouds;
	private int numClouds;
	
	public Clouds() {
		clouds = new ArrayList<Cloud>();
	}
	
	public static void load() {
		c00 = Assets.c00;
		c01 = Assets.c01;
		c02 = Assets.c02;
		c03 = Assets.c03;
	}

	@Override
	public void render(SpriteBatch batch) {
		for(int i = 0; i < clouds.size(); i++)
			clouds.get(i).render(batch);
	}

	@Override
	public void tick() {
		if(clouds.isEmpty()) 
			clouds.add(new Cloud());
		if(numClouds <= 8 && !clouds.isEmpty() && clouds.get(clouds.size() - 1).pos.x > Constants.rand.nextInt(5) + 10) {
			clouds.add(new Cloud());
			numClouds++;
		}
		for(int i = 0; i < clouds.size(); i++)
			clouds.get(i).tick();
	}
	
	private class Cloud implements GameObject {
		Vector2 pos;
		TextureAsset asset;
		float moveSpeed;
		
		Cloud() {
			int i = Constants.rand.nextInt(3);
			moveSpeed = Constants.rand.nextInt(4) + 1;
			switch(i) {
			case 0:
				asset = c00;
				break;
			case 1:
				asset = c01;
				break;
			case 2:
				asset = c02;
				break;
			case 3:
				asset = c03;
				break;
			}
			pos = new Vector2(0 - asset.getWidth(), Assets.ground.getHeight() - (asset.getHeight() / 2));
		}

		@Override
		public void render(SpriteBatch batch) {
			batch.draw(asset.getTexture(), pos.x, pos.y, asset.getWidth(), asset.getHeight());
		}

		@Override
		public void tick() {
			pos.x += moveSpeed;
			if(pos.x + 5 > Main.screenSize.x) {
				clouds.remove(this);
				numClouds--;
			}
		}
	}
}
