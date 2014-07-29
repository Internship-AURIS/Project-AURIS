package com.aau.auris.game.level.gameworld;

public class BorderLine extends Obstacle
{
	public BorderLine(float centerX, float centerY, float hx, float hy)
	{
		super(centerX, centerY, hx, hy, EntityCategory.OBSTACLE, EntityCategory.BALL, true);
	}
}
