package com.jbs.vikingdodge;

import java.util.List;

import com.jbs.vikingdodge.entity.Entity;
import com.jbs.vikingdodge.entity.mob.Mob;

public interface Collidable {
	public void isColliding(List<Entity> entities);
	public void onCollide(Mob m);
}
