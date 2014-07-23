package com.aau.auris.game.level.gameworld;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.level.Level;
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

	public Ball(Level level, World world, float posX, float posY, float radius)
	{
		this.game = level.game;
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(new Vector2(posX, posY));
		body = world.createBody(bodyDef);

		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(radius);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circleShape;
		fixtureDef.density = DENSITY;
		fixtureDef.friction = FRICTION;
		fixtureDef.restitution = RESTITUTION;
		body.createFixture(fixtureDef);
		body.setUserData(this);

		dead = false;

		circleShape.dispose();
	}

	public void die()
	{
		dead = true;
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
