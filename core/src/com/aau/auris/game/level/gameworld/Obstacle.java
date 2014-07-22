package com.aau.auris.game.level.gameworld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Obstacle extends Entity
{

	public Obstacle(World world, float posX, float posY, float width, float height)
	{
		bodyDef = new BodyDef();
		bodyDef.position.set(new Vector2(posX + width / 2, posY + height / 2));
		body = world.createBody(bodyDef);

		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(width / 2, height / 2, new Vector2(posX, posY), 0);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = polygonShape;
		fixtureDef.friction = 0f;
		body.createFixture(fixtureDef);
		body.setUserData(this);

		polygonShape.dispose();
	}

}
