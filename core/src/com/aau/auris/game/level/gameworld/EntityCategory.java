package com.aau.auris.game.level.gameworld;

public enum EntityCategory
{
	OBSTACLE((short) 2), BALL((short) 3);

	public final short index;

	EntityCategory(short index)
	{
		this.index = index;
	}

	public short index()
	{
		return index;
	}
}
