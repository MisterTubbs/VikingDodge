package com.jbs.vikingdodge.entity.mob;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jbs.vikingdodge.Constants;
import com.jbs.vikingdodge.assets.AnimationPackage;

public class RegainAnimation extends AnimatedMob {
	
	public RegainAnimation(float x) {
		super(AnimationPackage.heartAnimations.getPackage(), "regain", x, Constants.GROUND_HEIGHT - 20, 1300 / 7, 186);
		setAnimation("regain");
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
