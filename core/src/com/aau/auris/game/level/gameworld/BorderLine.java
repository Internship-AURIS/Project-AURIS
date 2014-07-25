package com.aau.auris.game.level.gameworld;

public class BorderLine extends Obstacle
{
	public BorderLine(float posX, float posY, float width, float height)
	{
		super(posX, posY, width, height, EntityCategory.OBSTACLE, EntityCategory.BALL);
	}
}
