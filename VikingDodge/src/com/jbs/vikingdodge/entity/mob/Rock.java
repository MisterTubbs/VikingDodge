package com.jbs.vikingdodge.entity.mob;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.jbs.vikingdodge.Constants;
import com.jbs.vikingdodge.Main;
import com.jbs.vikingdodge.assets.Assets;
import com.jbs.vikingdodge.assets.TextureAsset;
import com.jbs.vikingdodge.game.Game;

public class Rock extends TexturedMob {

	private float fade;
	private boolean hitPlayer;
	private Type type;
	private Circle boundingBox;
	private Game game;
	private TextureAsset blur;

	public static enum Type {
		TOP, LEFT, RIGHT
	}

	public Rock(Game game, Type t) {
		super(Assets.rock, 0, 0, Assets.rock.getWidth() / 1.5f, Assets.rock.getHeight() / 1.5f, 0, 0);
		this.type = t;
		this.game = game;
		this.blur = Assets.rockBlur;
		init(t);
	}

	private void init(Type t) {
		setPos(getInitPos(t));
		boundingBox = new Circle(getPos().x + 80, getPos().y + 80, 80);
		setInitVel(t);
	}

	private Vector2 getInitPos(Type t) {
		switch (t) {
		case TOP:
			int x = Constants.rand.nextInt((int) Main.screenSize.x);
			if (x <= 0)
				x = 0;
			if (x + getWidth() >= Main.screenSize.x)
				x = (int) (Main.screenSize.x - getWidth());
			return new Vector2(x, (int) Main.screenSize.y);
		case LEFT:
			int y = Constants.GROUND_HEIGHT + 200;
			return new Vector2(0 - getWidth(), y);
		case RIGHT:
			int yy = Constants.GROUND_HEIGHT + 200;
			return new Vector2(Main.screenSize.x, yy);
		}
		return null;
	}

	private void setInitVel(Type t) {
		switch (t) {
		case LEFT:
			setVel(6f, 0);
			break;
		case RIGHT:
			setVel(-6f, 0);
			break;
		default:
			break;
		}
	}

	@Override
	public void tick() {
		if (isDirty()) {
			getBoundingCircle().set(getX() + 80, getY() + 80, 80);
			setDirty(false);
		}

		if (getY() <= Constants.GROUND_HEIGHT) {
			if (type == Type.TOP) {
				setVel(getVel().x, 0);
				setY(Constants.GROUND_HEIGHT);
				setOnGround(true);
				setHitGround(true);
			} else {
				setVel(type == Type.LEFT ? getVel().x - 0.35f : getVel().x + 0.35f, -getVel().y - 2);
				if (getVel().x <= 0 && type == Type.LEFT) {
					hitPlayer = true;
					setY(Constants.GROUND_HEIGHT);
					setOnGround(true);
					setVel(0, 0);
					fade();
				} else if (getVel().x >= 0 && type == Type.RIGHT) {
					hitPlayer = true;
					setY(Constants.GROUND_HEIGHT);
					setOnGround(true);
					setVel(0, 0);
					fade();
				}
			}
		}

		if (onGround()) {
			if (type == Type.TOP) {
				hitPlayer = true;
				fade();
			}
		}

		if (!onGround())
			setVel(getVel().x, getVel().y + Constants.ROCK_GRAVITY);
		addPos(getVel().x, getVel().y);
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

	@Override
	public void render(SpriteBatch batch) {
		batch.setColor(1, 1, 1, 1 / fade);
		super.render(batch);
		batch.setColor(1, 1, 1, 1);
	}

	public boolean hitPlayer() {
		return hitPlayer;
	}

	public void setHitPlayer() {
		hitPlayer = true;
	}

	public void kill() {
		super.kill();
		if(type == Type.LEFT) 
			Game.rockSpawnLeft = false;
		else if(type == Type.RIGHT)
			Game.rockSpawnRight = false;
	}
	
	public Circle getBoundingCircle() {
		return boundingBox;
	}
}
