package com.aau.auris.game.items;

import com.aau.auris.game.Asset.Asset;

public class Achievement implements Asset, Unlockable
{
	public static final int ACHIEVEMENT_1 = 3;
	public static final int ACHIEVEMENT_2 = 4;
	public static final int ACHIEVEMENT_3 = 5;

	private int id;
	private boolean locked;

	public Achievement()
	{}

	public Achievement(boolean locked)
	{
		this.locked = locked;
	}

	public Achievement getAchievement(boolean locked)
	{
		if (id == ACHIEVEMENT_1)
		{

		} else if (id == ACHIEVEMENT_2)
		{

		} else if (id == ACHIEVEMENT_3)
		{

		}
		return null;
	}

	@Override
	public void loadAsset()
	{}

	@Override
	public void disposeAsset()
	{}

	@Override
	public boolean isLocked()
	{
		return locked;
	}

	@Override
	public void setLocked(boolean locked)
	{
		this.locked = locked;
	}

}
