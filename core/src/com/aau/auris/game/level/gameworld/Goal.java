package com.aau.auris.game.level.gameworld;

public class Goal extends Obstacle
{
	public Goal(float posX, float posY, float width, float height)
	{
		super(posX, posY, width, height, EntityCategory.OBSTACLE, EntityCategory.BALL);
	}
}
