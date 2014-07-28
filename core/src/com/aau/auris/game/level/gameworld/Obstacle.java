package com.aau.auris.game.level.gameworld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Obstacle extends Entity
{
	protected float posX, posY;
	protected final float hx, hy;
	protected final short categoryBits;// what the obstacle is
	protected final short maskBits;// with what it should collide

	public Obstacle(float posX, float posY, float width, float height, EntityCategory categoryBits, EntityCategory maskBits)
	{
		this.posX = posX;
		this.posY = posY;
		this.hx = width / 2f;
		this.hy = height / 2f;
		this.categoryBits = categoryBits.index;
		this.maskBits = maskBits.index;
	}

	public void create(World world)
	{
		bodyDef = new BodyDef();
		bodyDef.position.set(new Vector2(posX + hx, posY + hx));
		body = world.createBody(bodyDef);
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(hx, hy, new Vector2(posX + hx, posY + hy), 0);
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
}
