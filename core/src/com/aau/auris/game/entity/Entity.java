package com.aau.auris.game.entity;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

public class Entity
{
	protected BodyDef bodyDef;
	protected Body body;

	public Body getBody()
	{
		return body;
	}

	public BodyDef getBodyDef()
	{
		return bodyDef;
	}
}
