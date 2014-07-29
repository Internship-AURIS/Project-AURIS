package com.aau.auris.game.level.gameworld;

public class Laser extends Obstacle
{

	public Laser(float posX, float posY, float width, float height, EntityCategory categoryBits, EntityCategory maskBits)
	{
		super(posX, posY, width, height, categoryBits, maskBits);
		setDeadly(true);
	}
}
