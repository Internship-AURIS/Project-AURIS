package com.aau.auris.game.level.gameworld;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.Asset.AssetLoader;
import com.aau.auris.game.level.Level;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Ball extends Entity
{
	private final float DENSITY = 5f;
	private final float FRICTION = 0.0f;
	private final float RESTITUTION = 0f;
	

	// Other
	private AURISGame game;
	private boolean dead;
	private Sound popSound;
	private Sound lost;

	private float posX, posY;
	private float radius;

	public Ball(Level level, float posX, float posY, float radius)
	{
		this.game = level.game;
		this.dead = false;

		this.posX = posX;
		this.posY = posY;
		this.radius = radius;
		popSound=AssetLoader.explosion;
		lost=AssetLoader.lostSound;
	}

	public void create(World world)
	{
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(new Vector2(posX, posY));
		body = world.createBody(bodyDef);

		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(radius - 4);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circleShape;
		fixtureDef.density = DENSITY;
		fixtureDef.friction = FRICTION;
		fixtureDef.restitution = RESTITUTION;
		fixtureDef.filter.categoryBits = EntityCategory.BALL.index;
		fixtureDef.filter.maskBits = EntityCategory.OBSTACLE.index;
		body.createFixture(fixtureDef);
		body.setUserData(this);
		circleShape.dispose();
	}

	public void die()
	{
		dead = true;
		popSound.play();
		lost.play();
		game.getGameScreen().runTime = 0;
	}

	public boolean isDead()
	{
		return dead;
	}

	public void setDead(boolean dead)
	{
		this.dead = dead;
	}

}
