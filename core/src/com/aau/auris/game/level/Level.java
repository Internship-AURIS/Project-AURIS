package com.aau.auris.game.level;

import java.util.ArrayList;

import com.aau.auris.game.items.Unlockable;
import com.badlogic.gdx.physics.box2d.World;

public class Level implements Unlockable
{
	// Level Settings
	public transient static final int DIFFICULTY_1 = 0;
	public transient static final int DIFFICULTY_2 = 1;
	public transient static final int DIFFICULTY_3 = 2;

	// Other Variables
	private int index;
	private boolean locked;

	public Level(int index, boolean locked)
	{
		this.index = index;
		this.locked = true;
	}

	public void generateWorld(World world, int lvlDifficulty)
	{
		if (lvlDifficulty == DIFFICULTY_1)
		{
		} else if (lvlDifficulty == DIFFICULTY_2)
		{
		} else if (lvlDifficulty == DIFFICULTY_3)
		{
		}
	}

	public int getIndex()
	{
		return index;
	}

	@Override
	public void setLocked(boolean locked)
	{
		this.locked = locked;
	}

	@Override
	public boolean isLocked()
	{
		return locked;
	}

	@Override
	public int getScore()
	{
		return 0;
	}

	public static ArrayList<Level> getList()
	{
		ArrayList<Level> levels = new ArrayList<Level>();
		levels.add(new Level(1, true));
		levels.add(new Level(2, true));
		levels.add(new Level(3, true));
		levels.add(new Level(4, true));
		levels.add(new Level(5, true));
		levels.add(new Level(6, true));
		levels.add(new Level(7, true));
		levels.add(new Level(8, true));
		levels.add(new Level(9, true));
		return levels;
	}
}
