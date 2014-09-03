package com.jbs.vikingdodge.entity.mob;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.jbs.vikingdodge.Constants;
import com.jbs.vikingdodge.assets.Assets;

public class GroundBurn extends TexturedMob {

	private float fade;
	private int time;
	
	public GroundBurn(float x) {
		super(Assets.groundBurn, x, Constants.GROUND_HEIGHT - 20, Assets.groundBurn.getWidth(), Assets.groundBurn.getHeight());
	}
	
	@Override
	public void tick() {
		if(time >= 200)
			fade();
		
		time++;
		setY(Constants.GROUND_HEIGHT - 20);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.setColor(1, 1, 1, 1 / fade);
		batch.draw(getTexture().getTexture(), getX(), getY());
		batch.setColor(1, 1, 1, 1);
	}
	
	public void fade() {
		Timer.schedule(new Task() {
			@Override
			public void run() {
				fade++;
				if (fade >= 10)
					kill();
			}
		}, 0.3f, 1);
	}
}
