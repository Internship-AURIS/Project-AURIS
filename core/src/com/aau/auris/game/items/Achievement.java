package com.aau.auris.game.items;

import com.aau.auris.game.Asset.Asset;

public class Achievement implements Asset
{
	// Achievement Settings
	public static final int ACHIEVEMENT_ID_1 = 3;
	public static final int ACHIEVEMENT_ID_2 = 4;
	public static final int ACHIEVEMENT_ID_3 = 5;
	public static final int ACHIEVEMENT_1_POINTS = 5;
	public static final int ACHIEVEMENT_2_POINTS = 5;
	public static final int ACHIEVEMENT_3_POINTS = 5;

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

	public int getCreditValue()
	{
		if (id == ACHIEVEMENT_ID_1) { return ACHIEVEMENT_1_POINTS; }
		if (id == ACHIEVEMENT_ID_2) { return ACHIEVEMENT_2_POINTS; }
		if (id == ACHIEVEMENT_ID_3) { return ACHIEVEMENT_3_POINTS; }
		return 0;
	}
}
