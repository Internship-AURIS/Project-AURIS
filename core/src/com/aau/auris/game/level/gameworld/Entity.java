package com.aau.auris.game.level.gameworld;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

public abstract class Entity
{
	protected BodyDef bodyDef;
	protected Body body;
	protected boolean deadly = false;

	public Body getBody()
	{
		return body;
	}

	public BodyDef getBodyDef()
	{
		return bodyDef;
	}

	public boolean isDeadly()
	{
		return deadly;
	}

	public void setDeadly(boolean deadly)
	{
		this.deadly = deadly;
	}

}
