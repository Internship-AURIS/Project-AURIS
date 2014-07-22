package com.aau.auris.game.level.gameworld;

import com.badlogic.gdx.physics.box2d.World;

public class Goal extends Obstacle
{
	public Goal(World world, float posX, float posY, float width, float height)
	{
		super(world, posX, posY, width, height);
	}
}
