package com.aau.auris.game.levels;

import java.util.ArrayList;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.Asset;
import com.aau.auris.game.entity.Obstacle;
import com.aau.auris.game.userdata.Player;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Level implements Screen, Asset
{
	// Level Settings
	public static final int DIFFICULTY_1 = 0;
	public static final int DIFFICULTY_2 = 1;
	public static final int DIFFICULTY_3 = 2;

	// GameLogic Settings
	private static final Vector2 GRAVITY = new Vector2(0, 0);

	// GameWorld Settings
	private World world;
	private int levelDifficulty;
	private ArrayList<Obstacle> obstacles;

	// Other Variables
	private AURISGame game;
	private Player player;// Player Stats: score, achievements, etc.

	public Level(AURISGame game, int levelDiff)
	{
		this.game = game;
		this.levelDifficulty = (levelDiff == DIFFICULTY_1 || levelDiff == DIFFICULTY_2 || levelDiff == DIFFICULTY_3) ? levelDiff : DIFFICULTY_1;
		this.world = new World(GRAVITY, true);
		this.obstacles = new ArrayList<Obstacle>();
	}

	private void initWorld()
	{
		if (levelDifficulty == DIFFICULTY_1)
		{

		} else if (levelDifficulty == DIFFICULTY_2)
		{

		} else if (levelDifficulty == DIFFICULTY_3)
		{

		}
	}

	@Override
	public void render(float delta)
	{}

	@Override
	public void resize(int width, int height)
	{}

	@Override
	public void show()
	{
		initWorld();
	}

	@Override
	public void hide()
	{}

	@Override
	public void pause()
	{}

	@Override
	public void resume()
	{}

	@Override
	public void dispose()
	{}

	@Override
	public void loadAsset()
	{}

	@Override
	public void disposeAsset()
	{}
}
