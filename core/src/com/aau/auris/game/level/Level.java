package com.aau.auris.game.level;

import java.util.ArrayList;

import com.aau.auris.game.Asset.Asset;
import com.aau.auris.game.items.Unlockable;
import com.aau.auris.game.level.gameworld.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Level implements Asset, Unlockable
{
	// Level Settings
	public static final transient int DIFFICULTY_1 = 0;
	public static final transient int DIFFICULTY_2 = 1;
	public static final transient int DIFFICULTY_3 = 2;
	public static final transient int LEVEL_ID_1 = 0;
	public static final transient int LEVEL_ID_2 = 0;
	public static final transient int LEVEL_ID_3 = 0;
	public static final transient int LEVEL_ID_4 = 0;
	public static final transient int LEVEL_ID_5 = 0;
	public static final transient int LEVEL_ID_6 = 0;
	public static final transient int LEVEL_ID_7 = 0;
	public static final transient int LEVEL_ID_8 = 0;
	public static final transient int LEVEL_ID_9 = 0;

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
	private final transient String skin_PreString = "level_skin";
	private transient Skin skin;

	// Other Variables
	private int id;
	private boolean locked;

	public Level(int index, boolean locked)
	{
		this.id = index;
		this.locked = true;

		world = new World(GRAVITY, true);
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera();
		camera.viewportWidth = Gdx.graphics.getWidth();
		camera.viewportHeight = Gdx.graphics.getHeight();
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0f);
		camera.update();

		generateWorld();
	}

	@Override
	public void loadAsset()
	{
		// TODO: add level display assets for level screen, goals, etc.
		skin = new Skin();
	}

	@Override
	public void disposeAsset()
	{
		// TODO Auto-generated method stub
	}

	public void generateWorld()
	{
		// initialize GameObjects
		final int s_width = Gdx.graphics.getWidth();
		final int s_height = Gdx.graphics.getHeight();
		final float menu_width = 40;
		final float menu_height = 150;
		ball = new Ball(world, camera.viewportWidth / 2, camera.viewportHeight / 2);
		home = new Home(world, 0 * WORLD_TO_BOX, 0 * WORLD_TO_BOX, menu_width * WORLD_TO_BOX, menu_height * WORLD_TO_BOX);
		goal = new Goal(world, (s_width - menu_width) * WORLD_TO_BOX, 0, menu_width * WORLD_TO_BOX, menu_height * WORLD_TO_BOX);

		System.out.println(s_height);
		// Initialize GameBorder
		final float factor_height = 1.95f;
		final float factor_width = 0.95f;
		final float border_width = 20;// the object
		this.obstacles = new ArrayList<Obstacle>();
		obstacles.add(new Obstacle(world, -10 * WORLD_TO_BOX, 0, border_width * WORLD_TO_BOX, (s_height * factor_height) * WORLD_TO_BOX));
		obstacles.add(new Obstacle(world, 0 * WORLD_TO_BOX, -10 * WORLD_TO_BOX, (s_width * factor_height) * WORLD_TO_BOX, border_width * WORLD_TO_BOX));
		obstacles.add(new Obstacle(world, 0 * WORLD_TO_BOX, (s_height * factor_width + border_width/2) * WORLD_TO_BOX, (s_width * factor_height) * WORLD_TO_BOX, border_width * WORLD_TO_BOX));
		obstacles.add(new Obstacle(world, 85, 150, 100, 10));

		if (id / 9 == DIFFICULTY_1)
		{
			System.out.println("Level.generateWorld() --> difficulty: " + DIFFICULTY_1);
		} else if (id / 9 == DIFFICULTY_2)
		{
			System.out.println("Level.generateWorld() --> difficulty: " + DIFFICULTY_2);
		} else if (id / 9 == DIFFICULTY_3)
		{
			System.out.println("Level.generateWorld() --> difficulty: " + DIFFICULTY_3);
		}
	}

	public int getIndex()
	{
		return id;
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

	@Override
	public Drawable getDrawable()
	{
		return skin.getDrawable(skin_PreString + id);
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

}
