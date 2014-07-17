package com.aau.auris.game.items;

import com.aau.auris.game.Asset.Asset;

public class Achievement implements Asset, Unlockable
{

	private int id;
	private boolean locked;

	public Achievement()
	{}

	public Achievement(boolean locked)
	{
		this.locked = locked;
	}

	public Achievement getAchievent(int index, boolean locked)
	{
		if (index == ACHIEVEMENT_1)
		{

		} else if (index == ACHIEVEMENT_2)
		{

		} else if (index == ACHIEVEMENT_3)
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

	@Override
	public int getID()
	{
		return id;
	}
}
