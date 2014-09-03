package com.jbs.vikingdodge.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jbs.vikingdodge.Collidable;
import com.jbs.vikingdodge.GameObject;
import com.jbs.vikingdodge.Renderable;
import com.jbs.vikingdodge.entity.mob.Dragon;
import com.jbs.vikingdodge.entity.mob.GroundBurn;
import com.jbs.vikingdodge.entity.mob.Player;
import com.jbs.vikingdodge.game.Game;

public class EntityManager implements GameObject {
	
	private List<Entity> entities, queue;
	private List<GroundBurn> groundBurns, burnQueue;
	private Player player;
	private Dragon dragon;
	
	public EntityManager() {
		entities = new ArrayList<Entity>();
		queue = new ArrayList<Entity>();
		groundBurns = new ArrayList<GroundBurn>();
		burnQueue = new ArrayList<GroundBurn>();
	}

	public void add(Entity e) {
		queue.add(e);
	}
	
	public void addBurn(GroundBurn b) {
		burnQueue.add(b);
	}
	
	public void setPlayer(Player p) {
		this.player = p;
		this.player.create();
	}
	
	public void setDragon(Dragon dragon) {
		this.dragon = dragon;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Dragon getDragon() {
		return dragon;
	}
	
	@Override
	public void tick() {
		player.tick();
		if(dragon != null) {
			dragon.tick();
			if(!dragon.isAlive()) {
				dragon = null;
				Game.dragonSpawned = false;
			}
		}
		player.isColliding(entities);
		Iterator<Entity> e = entities.iterator();
		while(e.hasNext()) {
			Entity entity = e.next();
			entity.tick();
			if(entity instanceof Collidable)
				((Collidable) entity).isColliding(entities);
			if(!entity.isAlive()) {
				entity.destroy();
				e.remove();
			}
		}
		Iterator<Entity> q = queue.iterator();
		while(q.hasNext()) {
			Entity entity = q.next();
			entity.create();
			entities.add(entity);
			q.remove();
		}
		
		Iterator<GroundBurn> gb = groundBurns.iterator();
		while(gb.hasNext()) {
			GroundBurn burn = gb.next();
			burn.tick();
			if(!burn.isAlive()) {
				burn.destroy();
				gb.remove();
			}
		}
		Iterator<GroundBurn> burnQ = burnQueue.iterator();
		while(burnQ.hasNext()) {
			GroundBurn burn = burnQ.next();
			burn.create();
			groundBurns.add(burn);
			burnQ.remove();
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		for(GroundBurn gb : groundBurns)
			gb.render(batch);
		player.render(batch);
		if(dragon != null) dragon.render(batch);
		for(Entity e : entities) {
			if(e instanceof Renderable) 
				((Renderable) e).render(batch);
		}
	}
	
	public void reset() {
		for(Entity e : entities)
			e.kill();
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public List<Entity> getQueue() {
		return queue;
	}
}
