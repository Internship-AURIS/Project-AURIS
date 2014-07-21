package com.aau.auris.game.items;

import com.aau.auris.game.Asset.Asset;

public class Achievement implements Asset
{
	// Achievement Settings
	public static final int ACHIEVEMENT_1 = 3;
	public static final int ACHIEVEMENT_2 = 4;
	public static final int ACHIEVEMENT_3 = 5;
	public static final int ACHIEVEMENT_1_SCORE = 5;
	public static final int ACHIEVEMENT_2_SCORE = 5;
	public static final int ACHIEVEMENT_3_SCORE = 5;

	// Object Variables
	private int id;

	public Achievement()
	{}

	public Achievement(int id)
	{
		this.id = id;
	}

	@Override
	public void loadAsset()
	{
		// TODO: load asset
	}

	@Override
	public void disposeAsset()
	{}

	public int getID()
	{
		return id;
	}
}
