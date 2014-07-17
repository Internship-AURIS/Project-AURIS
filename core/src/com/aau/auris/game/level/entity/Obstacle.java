package com.aau.auris.game.level.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Obstacle extends Entity
{
	public Obstacle(World world, float posX, float posY, float width, float height)
	{
		bodyDef = new BodyDef();
		bodyDef.position.set(new Vector2(posX, posY));
		body = world.createBody(bodyDef);

		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(width / 2, height / 2, new Vector2(posX, posY), 0);
		body.createFixture(polygonShape, 0.0f);
		body.setUserData(this);

		polygonShape.dispose();
	}
}
