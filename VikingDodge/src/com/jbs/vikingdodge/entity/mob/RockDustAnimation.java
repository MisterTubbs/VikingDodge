package com.jbs.vikingdodge.entity.mob;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jbs.vikingdodge.assets.AnimationPackage;

public class RockDustAnimation extends AnimatedMob {

	public RockDustAnimation(float x, float y) {
		super(AnimationPackage.rockAnimation.getPackage(), "dust", x, y, 450, 150);
		setAnimation("dust");
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if(getCurrentAnimation().isDone()) {
			kill();
			getCurrentAnimation().setNotDone();
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
	}
}
