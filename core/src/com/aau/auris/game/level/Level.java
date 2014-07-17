package com.aau.auris.game.level;

import com.aau.auris.game.AURISGame;
import com.badlogic.gdx.physics.box2d.World;

public class Level
{
	// Level Settings
	public transient static final int DIFFICULTY_1 = 0;
	public transient static final int DIFFICULTY_2 = 1;
	public transient static final int DIFFICULTY_3 = 2;

	// Other Variables
	private transient AURISGame game;
	private int lvlDifficulty;

	public Level(AURISGame game, int levelDiff)
	{
		this.game = game;
	}

	public void generateWorld(World world, int lvlDifficulty)
	{
		this.lvlDifficulty = lvlDifficulty;
		if (lvlDifficulty == DIFFICULTY_1)
		{} else if (lvlDifficulty == DIFFICULTY_2)
		{} else if (lvlDifficulty == DIFFICULTY_3)
		{}
	}

	public int getLvlDifficulty()
	{
		return lvlDifficulty;
	}
}
