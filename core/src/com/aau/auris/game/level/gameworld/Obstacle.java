package com.aau.auris.game.level.gameworld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Obstacle extends Entity
{
	protected float posX, posY;
	protected float hx, hy;
	protected final short categoryBits;
	protected final short maskBits;

	public Obstacle(float centerX, float centerY, float hx, float hy, EntityCategory categoryBits, EntityCategory maskBits, boolean deadly)
	{
		this.posX = centerX;
		this.posY = centerY;
		this.hx = hx;
		this.hy = hy;
		this.categoryBits = categoryBits.index;
		this.maskBits = maskBits.index;
		this.deadly = deadly;
	}

	public void create(World world)
	{
		bodyDef = new BodyDef();
		bodyDef.position.set(new Vector2(posX + hx / 2, posY + hy / 2));
		body = world.createBody(bodyDef);
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(hx / 2, hy / 2, new Vector2(posX, posY), 0);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = polygonShape;
		fixtureDef.friction = 0f;
		fixtureDef.filter.categoryBits = categoryBits;
		fixtureDef.filter.maskBits = maskBits;
		body.createFixture(fixtureDef);
		body.setUserData(this);

		polygonShape.dispose();
	}
	public void create1(World world)
	{
		bodyDef = new BodyDef();
		bodyDef.position.set(new Vector2(posX, posY));
		body = world.createBody(bodyDef);
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(hx, hy, new Vector2(posX, posY), 0);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = polygonShape;
		fixtureDef.friction = 0f;
		fixtureDef.filter.categoryBits = categoryBits;
		fixtureDef.filter.maskBits = maskBits;
		body.createFixture(fixtureDef);
		body.setUserData(this);

		polygonShape.dispose();
	}

	public void setBodyNull()
	{
		body.setUserData(null);
		body = null;
	}

	public float getPosX()
	{
		return posX;
	}

	public float getPosY()
	{
		return posY;
	}

	public float getWidth()
	{
		return hx;
	}

	public float getHeight()
	{
		return hy;
	}
}
