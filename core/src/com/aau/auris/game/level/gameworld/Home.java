package com.aau.auris.game.level.gameworld;

import com.badlogic.gdx.physics.box2d.World;

public class Home extends Obstacle
{
	public Home(World world, float posX, float posY, float width, float height)
	{
		super(world, posX, posY, width, height);
		body.setUserData(this);
	}
}
