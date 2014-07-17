package com.aau.auris.game.items;

import com.aau.auris.game.Asset.Asset;

public class Achievement implements Asset
{
	public transient static final int ALL_LEVELS_CLEARED = 0;
	public transient static final int ACHIEVEMENT_2 = 1;
	public transient static final int ACHIEVEMENT_3 = 2;

	private boolean locked;

	public Achievement()
	{}

	public Achievement(boolean locked)
	{
		this.locked = locked;
	}

	public Achievement getAchievent(int index, boolean locked)
	{
		if (index == ALL_LEVELS_CLEARED)
		{

		} else if (index == ACHIEVEMENT_2)
		{

		} else if (index == ACHIEVEMENT_3)
		{

		}
		return null;
	}

	public boolean isLocked()
	{
		return locked;
	}

	public void setLocked(boolean locked)
	{
		this.locked = locked;
	}

	@Override
	public void loadAsset()
	{}

	@Override
	public void disposeAsset()
	{}
}
