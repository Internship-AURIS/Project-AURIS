package com.aau.auris.game.level.gameworld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Ball extends Entity
{
	public final float CIRCLE_RADIUS = 26.5f;
	private final float DENSITY = 5f;
	private final float FRICTION = 0.0f;
	private final float RESTITUTION = 0f;

	// Other
	private boolean dead;

	public Ball(World world, float posX, float posY)
	{
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(new Vector2(posX, posY));
		body = world.createBody(bodyDef);

		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(CIRCLE_RADIUS);

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

	public void setDead(boolean dead)
	{
		this.dead = dead;
	}

	public boolean isDead()
	{
		return dead;
	}

}
