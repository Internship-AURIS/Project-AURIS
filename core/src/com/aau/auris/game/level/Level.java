package com.aau.auris.game.level;

import java.util.ArrayList;
import java.util.Iterator;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.Asset.Asset;
import com.aau.auris.game.Asset.AssetLoader;
import com.aau.auris.game.data.Player;
import com.aau.auris.game.level.gameworld.Ball;
import com.aau.auris.game.level.gameworld.BorderLine;
import com.aau.auris.game.level.gameworld.Goal;
import com.aau.auris.game.level.gameworld.Home;
import com.aau.auris.game.level.gameworld.Obstacle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

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
	public static final int LEVEL_ID_1_POINTS = 5;
	public static final int LEVEL_ID_2_POINTS = 6;
	public static final int LEVEL_ID_3_POINTS = 7;
	public static final int LEVEL_ID_4_POINTS = 10;
	public static final int LEVEL_ID_5_POINTS = 12;
	public static final int LEVEL_ID_6_POINTS = 14;
	public static final int LEVEL_ID_7_POINTS = 18;
	public static final int LEVEL_ID_8_POINTS = 22;
	public static final int LEVEL_ID_9_POINTS = 26;

	// GameLogic Settings
	public static final Vector2 GRAVITY = new Vector2(0, 0);
	public static final float BOX_STEP = 1 / 60f;
	public static final int BOX_VELOCITY_ITERATIONS = 6;
	public static final int BOX_POSITION_ITERATIONS = 2;
	public static float factorX;
	public static float factorY;

	// Level
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	private Ball ball;

	private ArrayList<BorderLine> border;
	private ArrayList<Obstacle> levelObjects;
	private Home home;
	private Goal goal;
	private Skin skin;

	// Asset
	private TextureAtlas goalTextures;

	// Other
	public AURISGame game;
	private int id;

	public Level(AURISGame game, int id)
	{
		this.game = game;
		this.id = id;
		loadAsset();

		world = new World(GRAVITY, true);
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera();
		camera.viewportWidth = game.getWidth();
		camera.viewportHeight = game.getHeight();
		camera.position.set(camera.viewportWidth * 0.5f, camera.viewportHeight * 0.5f, 0f);
		camera.update();

		factorX = camera.viewportWidth / game.getWidth();
		factorY = camera.viewportHeight / game.getHeight();

		generateWorld(game.getWidth(), game.getHeight());
	}

	public void reset()
	{
		world = new World(GRAVITY, true);
		generateWorld(game.getWidth(), game.getHeight());
	}

	@Override
	public void loadAsset()
	{
		goalTextures = AssetLoader.levelgoals;
		skin = new Skin(goalTextures);
	}

	@Override
	public void disposeAsset()
	{}

	public void generateWorld(int sWidth, int sHeight)
	{
		final float border_size = 10;// the object
		this.border = new ArrayList<BorderLine>();
		this.levelObjects = new ArrayList<Obstacle>();

		// Initialize GameBorder
		border.add(new BorderLine((border_size / 2f * -1) * factorX, 0, border_size * factorX, sHeight * factorY));
		border.add(new BorderLine(0, (border_size / 2f * -1) * factorX, sWidth * factorX, border_size * factorY));
		border.add(new BorderLine(0, sHeight / 2 * factorY, sWidth * factorX, border_size * factorY));
		border.add(new BorderLine(sWidth / 2 * factorX, 0, border_size * factorX, sHeight * factorY));
		createBorder();

		// Initialize Levels
		this.levelObjects = new ArrayList<Obstacle>();
		if (id >= LEVEL_ID_1 && id <= LEVEL_ID_3)
		{
			levelObjects.add(new Obstacle(150, 20, 50, 50));
		} else if (id >= LEVEL_ID_4 && id <= LEVEL_ID_6)
		{
			levelObjects.add(new Obstacle((50 / 2 - 50) * factorX, (50 / 2 - 50) * factorY, 50 * factorX, 50 * factorY));
		} else if (id >= LEVEL_ID_7 && id <= LEVEL_ID_9)
		{
			levelObjects.add(new Obstacle((50 / 2 - 50) * factorX, (50 / 2 - 50) * factorY, 50 * factorX, 50 * factorY));
		}
		levelObjects.clear();// TODO: debugging; place levelDefined objects in other ArrayList

		// initialize ever existing GameObjects
		final int goalHeight = 150;
		final int goalWidth = 60;
		ball = new Ball(this, sWidth / 2 * factorX, sHeight / 2 * factorY, game.getPreferences().getBallRadius() * factorY);
		home = new Home(0, 0, 44 * factorX, 101 * factorY);
		goal = new Goal(410 * factorX, 100 * factorY, goalWidth * factorX, goalHeight * factorY);
		ball.create(world);
		home.create(world);
		goal.create(world);

		// create objects in world
		createObjects(levelObjects);
	}

	private void createBorder()
	{
		for (Obstacle o : border)
		{
			o.create(world);
		}
	}

	private void createObjects(ArrayList<Obstacle> obstacles)
	{
		for (Obstacle o : obstacles)
		{
			o.create(world);
		}
	}

	public ArrayList<Obstacle> getObjects()
	{
		return this.levelObjects;
	}

	public synchronized void destroyObjects()
	{
		Obstacle o = null;
		for (Iterator<Obstacle> iter = levelObjects.iterator(); iter.hasNext();)
		{
			o = iter.next();
			world.destroyBody(o.getBody());
			o.setBodyNull();
		}
		levelObjects.clear();
	}

	public void setObjects(ArrayList<Obstacle> newObjects)
	{
		this.levelObjects = newObjects;
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
		if (id == LEVEL_ID_1) { return LEVEL_ID_1_POINTS; }
		if (id == LEVEL_ID_2) { return LEVEL_ID_2_POINTS; }
		if (id == LEVEL_ID_3) { return LEVEL_ID_3_POINTS; }
		if (id == LEVEL_ID_4) { return LEVEL_ID_4_POINTS; }
		if (id == LEVEL_ID_5) { return LEVEL_ID_5_POINTS; }
		if (id == LEVEL_ID_6) { return LEVEL_ID_6_POINTS; }
		if (id == LEVEL_ID_7) { return LEVEL_ID_7_POINTS; }
		if (id == LEVEL_ID_8) { return LEVEL_ID_8_POINTS; }
		if (id == LEVEL_ID_9) { return LEVEL_ID_9_POINTS; }
		return 0;
	}

	public void finished()
	{
		Player player = game.getPlayer();
		player.addCredits(getCreditValue());

		ArrayList<Level> levels = AURISGame.levels;
		for (int i = 0; i < levels.size(); i++)
		{
			Level nextLevel = AURISGame.getLevel(this.id + 1);
			if (nextLevel != null)
			{
				player.addLevelID(nextLevel.getID());
				player.checkAchievements();
			}
		}
		game.getUserData().save();
	}

	public void draw(SpriteBatch spriteBatch)
	{
		skin.getDrawable("goal4Big").draw(spriteBatch, 790, goal.getPosY() / factorY + 105, 60, 150);
	}
}
