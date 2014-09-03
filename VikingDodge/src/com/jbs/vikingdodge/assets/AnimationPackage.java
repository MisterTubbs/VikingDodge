package com.jbs.vikingdodge.assets;

import java.util.HashMap;

public class AnimationPackage {
	
	public static AnimationPackage playerAnimations = new AnimationBuilder().setKeys("stand", "run", "leap", "jump", "standSheep", "runSheep", "throwSheep").setAnimations(Animation.playerStand, Animation.playerRun, Animation.playerLeap, Animation.playerJump, Animation.playerStandSheep, Animation.playerRunSheep, Animation.playerThrowSheep).build();
	public static AnimationPackage sheepAnimations = new AnimationBuilder().setKeys("stand").setAnimations(Animation.sheepStand).build();
	public static AnimationPackage dragonAnimations = new AnimationBuilder().setKeys("fly", "fire", "postFire").setAnimations(Animation.dragonFly, Animation.dragonFire, Animation.dragonPostFire).build();
	public static AnimationPackage dragonRockAnimations = new AnimationBuilder().setKeys("fly", "hit").setAnimations(Animation.dragonRock, Animation.rockEffect).build();
	public static AnimationPackage heartAnimations = new AnimationBuilder().setKeys("regain").setAnimations(Animation.oneUp).build();
	public static AnimationPackage rockAnimation = new AnimationBuilder().setKeys("dust").setAnimations(Animation.rockDust).build();
	
	private HashMap<String, Animation> anims;
	
	public AnimationPackage(AnimationBuilder builder) {
		this.anims = new HashMap<String, Animation>();
		for(int i = 0; i < builder.keys.length; i++)
			anims.put(builder.keys[i], builder.anims[i]);
	}
	
	public HashMap<String, Animation> getPackage() {
		return anims;
	}

	public static class AnimationBuilder {
		
		public String[] keys;
		public Animation[] anims;
		
		public AnimationBuilder setKeys(String... keys) {
			this.keys = keys;
			return this;
		}
		
		public AnimationBuilder setAnimations(Animation... anims) {
			this.anims = anims;
			return this;
		}
		
		public AnimationPackage build() {
			return new AnimationPackage(this);
		}
	}
}
