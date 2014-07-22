package com.aau.auris.game.level;

import java.util.ArrayList;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.Asset.Asset;
import com.aau.auris.game.data.Player;
import com.aau.auris.game.level.gameworld.Ball;
import com.aau.auris.game.level.gameworld.Goal;
import com.aau.auris.game.level.gameworld.Home;
import com.aau.auris.game.level.gameworld.Obstacle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class Level implements Asset
{
	// Level Settings
	public static final transient int DIFFICULTY_1 = 1;
	public static final transient int DIFFICULTY_2 = 2;
	public static final transient int DIFFICULTY_3 = 3;
	public static final int LEVEL_ID_1 = 0;
	public static final int LEVEL_ID_2 = 1;
	public static final int LEVEL_ID_3 = 2;
	public static final int LEVEL_ID_4 = 3;
	public static final int LEVEL_ID_5 = 4;
	public static final int LEVEL_ID_6 = 5;
	public static final int LEVEL_ID_7 = 6;
	public static final int LEVEL_ID_8 = 7;
	public static final int LEVEL_ID_9 = 8;
	public static final int[] costs = new int[] { 5, 6, 7, 10, 12, 14, 18, 22, 26 };

	// GameLogic Settings
	public static final Vector2 GRAVITY = new Vector2(0, 0);
	public static final float BOX_STEP = 1 / 60f;
	public static final int BOX_VELOCITY_ITERATIONS = 6;
	public static final int BOX_POSITION_ITERATIONS = 2;
	public static final float WORLD_TO_BOX = 0.5111111f;

	// Level
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	private Ball ball;
	private ArrayList<Obstacle> obstacles;
	private Home home;
	private Goal goal;

	// Asset
	// TODO: add asset for level display

	// Other
	private AURISGame game;
	private int id;

	public Level(AURISGame game, int id)
	{
		this.game = game;
		this.id = id;

		world = new World(GRAVITY, true);
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera();
		camera.viewportWidth = Gdx.graphics.getWidth();
		camera.viewportHeight = Gdx.graphics.getHeight();
		camera.position.set(camera.viewportWidth * 0.5f, camera.viewportHeight * 0.5f, 0f);
		camera.update();

		generateWorld();
	}

	@Override
	public void loadAsset()
	{}

	@Override
	public void disposeAsset()
	{}

	public void generateWorld()
	{
		// initialize GameObjects
		final int s_width = Gdx.graphics.getWidth();
		final int s_height = Gdx.graphics.getHeight();
		final float menu_width = 90;
		final float menu_height = 200;
		final float goal_width = 40;
		final float goal_height = 150;
		ball = new Ball(world, camera.viewportWidth / 2, camera.viewportHeight / 2);
		home = new Home(world, 0 * WORLD_TO_BOX, 0 * WORLD_TO_BOX, menu_width * WORLD_TO_BOX, menu_height * WORLD_TO_BOX);
		goal = new Goal(world, (s_width - goal_width) * WORLD_TO_BOX, 0, goal_width * WORLD_TO_BOX, goal_height * WORLD_TO_BOX);

		// Initialize GameBorder
		final float factor_height = 1.95f;
		final float factor_width = 0.9367f;
		final float border_width = 20;// the object
		this.obstacles = new ArrayList<Obstacle>();
		obstacles.add(new Obstacle(world, -10 * WORLD_TO_BOX, 0, border_width * WORLD_TO_BOX, (s_height * factor_height) * WORLD_TO_BOX));
		obstacles.add(new Obstacle(world, 0 * WORLD_TO_BOX, -10 * WORLD_TO_BOX, (s_width * factor_height) * WORLD_TO_BOX, border_width * WORLD_TO_BOX));
		obstacles.add(new Obstacle(world, 0 * WORLD_TO_BOX, ((s_height + border_width) * factor_width) * WORLD_TO_BOX, (s_width * factor_height) * WORLD_TO_BOX, border_width * WORLD_TO_BOX));
		obstacles.add(new Obstacle(world, ((s_width + border_width * 1.8f) * factor_width) * WORLD_TO_BOX, 0, border_width * WORLD_TO_BOX, (s_height * factor_height) * WORLD_TO_BOX));

		if (id >= 1 && id <= 3)
		{
			// TODO: generate Level Difficulty 1
		} else if (id >= 4 && id <= 6)
		{
			// TODO: generate Level Difficulty 2
		} else if (id >= 7 && id <= 9)
		{
			// TODO: generate Level Difficulty 3
		}
	}

	public int getID()
	{
		return id;
	}

	public World getWorld()
	{
		return world;
	}

	public Box2DDebugRenderer getDebugRenderer()
	{
		return debugRenderer;
	}

	public OrthographicCamera getCamera()
	{
		return camera;
	}

	public Ball getBall()
	{
		return ball;
	}

	public Home getHome()
	{
		return home;
	}

	public Goal getGoal()
	{
		return goal;
	}

	public int getCreditValue()
	{
		if (id - 1 >= 0 && id - 1 < costs.length) { return costs[id - 1]; }
		return 0;
	}

	public void finished()
	{
		Player player = game.getPlayer();
		player.addCredits(getCreditValue());

		ArrayList<Level> levels = AURISGame.levels;
		for (int i = 0; i < levels.size(); i++)
		{
			if (levels.get(i).getID() == id && i + 1 < levels.size())
			{
				player.addLevelID(levels.get(i + 1).getID());
			}
		}
	}

}
