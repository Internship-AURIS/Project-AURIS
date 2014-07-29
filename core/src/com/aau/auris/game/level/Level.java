package com.aau.auris.game.level;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.Asset.Asset;
import com.aau.auris.game.Asset.AssetLoader;
import com.aau.auris.game.data.Player;
import com.aau.auris.game.level.gameworld.Ball;
import com.aau.auris.game.level.gameworld.BorderLine;
import com.aau.auris.game.level.gameworld.Goal;
import com.aau.auris.game.level.gameworld.Home;
import com.aau.auris.game.level.gameworld.Laser;
import com.aau.auris.game.level.gameworld.Obstacle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
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
	public static final float BOX_TO_WORLD = 0.5f;

	// Level
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	private Ball ball;

	private ArrayList<BorderLine> border;
	private ArrayList<Obstacle> defObjects;
	private ArrayList<Obstacle> levelObjects;
	private Home home;
	private Goal goal;
	private Skin skin;
	private float runTime;

	// Asset
	private TextureAtlas goalTextures;
	private Animation laserAnimation;

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
		camera = new OrthographicCamera(game.getWidth(), game.getHeight());
		camera.setToOrtho(false, game.getWidth(), game.getHeight());
		camera.update();

		generateWorld(game.getWidth(), game.getHeight());
	}

	public void reset()
	{
		world = new World(GRAVITY, true);
		generateWorld(game.getWidth(), game.getHeight());
		runTime = 0f;
	}

	@Override
	public void loadAsset()
	{
		goalTextures = AssetLoader.levelgoals;
		laserAnimation = AssetLoader.laserAnimation;
		skin = new Skin(goalTextures);
	}

	@Override
	public void disposeAsset()
	{}

	public void generateWorld(int sWidth, int sHeight)
	{
		final float borderWidth = 1;// the object
		final float laserWidth = 10;
		this.border = new ArrayList<BorderLine>();
		this.defObjects = new ArrayList<Obstacle>();
		this.levelObjects = new ArrayList<Obstacle>();

		// Initialize GameBorder
		border.add(new BorderLine(toWorldCoord((borderWidth / 2f) * -1), toWorldCoord(sHeight / 2f), toWorldCoord(borderWidth), toWorldCoord(sHeight)));// left border
		border.add(new BorderLine(toWorldCoord(sWidth / 2f), toWorldCoord((borderWidth / 2f) * -1), toWorldCoord(sWidth), toWorldCoord(borderWidth)));// bottom border
		border.add(new BorderLine(toWorldCoord(sWidth / 2f), toWorldCoord(sHeight + borderWidth / 2f), toWorldCoord(sWidth), toWorldCoord(borderWidth)));// top border
		border.add(new BorderLine(toWorldCoord(sWidth + borderWidth / 2f), toWorldCoord(sHeight / 2f), toWorldCoord(borderWidth), toWorldCoord(sHeight)));// right border
		createBorder();

		// General Level Initialization
		if (id >= LEVEL_ID_2 && id <= LEVEL_ID_3)
		{
			defObjects.add(new Obstacle(toWorldCoord(sWidth - sWidth / 4), toWorldCoord(getRandom(sHeight / 4f, sHeight - sHeight / 4f)), toWorldCoord(getRandom(10, sWidth / 8f)), toWorldCoord(getRandom(10, sHeight / 4f)), false));
		} else if (id >= LEVEL_ID_4 && id <= LEVEL_ID_5)
		{
			defObjects.add(new Laser(toWorldCoord(sWidth - sWidth / 3f), toWorldCoord(0), toWorldCoord(laserWidth), toWorldCoord(getRandom(sHeight / 2f, sHeight))));// bottom laser
		} else if (id >= LEVEL_ID_7 && id <= LEVEL_ID_9)
		{
			defObjects.add(new Obstacle(toWorldCoord(50), toWorldCoord(50), toWorldCoord(50), toWorldCoord(50), false));
		}

		// Specific Level Initialization
		if (id == LEVEL_ID_1)
		{} else if (id == LEVEL_ID_2)
		{} else if (id == LEVEL_ID_3)
		{} else if (id == LEVEL_ID_4)
		{} else if (id == LEVEL_ID_5)
		{} else if (id == LEVEL_ID_6)
		{
			defObjects.add(new Laser(toWorldCoord(sWidth - sWidth / 2f), toWorldCoord(sHeight), toWorldCoord(laserWidth), toWorldCoord(getRandom(sHeight / 3f, sHeight/2f))));// top laser
			defObjects.add(new Laser(toWorldCoord(sWidth - sWidth / 3.5f), toWorldCoord(0), toWorldCoord(laserWidth), toWorldCoord(getRandom(sHeight / 3f, sHeight/3f))));// bottom laser
		} else if (id == LEVEL_ID_7)
		{

		} else if (id == LEVEL_ID_8)
		{

		} else if (id == LEVEL_ID_9)
		{

		}

		// initialize ever existing GameObjects
		final float ballRadius = game.getPreferences().getBallRadius();
		final int goalHeight = 150;
		final int goalWidth = 40;
		final int homeWidth = 44;
		final int homeHeight = 101;
		ball = new Ball(this, toWorldCoord(getRandom(sWidth / 3f, sWidth / 2f)), sHeight / 2, ballRadius);
		home = new Home(toWorldCoord(homeWidth / 2f), toWorldCoord(homeHeight / 2f), toWorldCoord(homeWidth), toWorldCoord(homeHeight));
		goal = new Goal(toWorldCoord(sWidth - goalWidth / 2f), getRandom(toWorldCoord(goalHeight / 2f), toWorldCoord(sHeight - goalHeight * 2)), toWorldCoord(goalWidth), toWorldCoord(goalHeight));
		ball.create(world);
		home.create(world);
		goal.create(world);

		// create defined objects in world
		createObjects(defObjects);
	}

	private float getRandom(float min, float max)
	{
		Random r = new Random();
		return min + r.nextInt((int) max);
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

	public void createLevelObjects()
	{
		createObjects(levelObjects);
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
	}

	public void setObjects(ArrayList<Obstacle> newObjects)
	{
		levelObjects.clear();
		levelObjects.addAll(newObjects);
	}

	public ArrayList<Obstacle> getObjects()
	{
		return levelObjects;
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

	public void draw(SpriteBatch spriteBatch, float delta)
	{
		runTime += delta;
		skin.getDrawable("goal4Big").draw(spriteBatch, 790, toBoxCoord(goal.getPosY() - goal.getHeight() / 2f), 60, toBoxCoord(goal.getHeight()));
		for (Obstacle l : defObjects)
		{
			if (l instanceof Laser)
			{
				spriteBatch.draw(laserAnimation.getKeyFrame(runTime, true), toBoxCoord(l.getPosX() - 16), toBoxCoord(l.getPosY() - l.getHeight() / 2f) - 4, 60, toBoxCoord(l.getHeight() + 7));
			}
		}
	}

	/*
	 * return value from screen to world coordinates
	 */
	public float toWorldCoord(float a)
	{
		return a * BOX_TO_WORLD;
	}

	/*
	 * return value from world to screen coordinates
	 */
	public float toBoxCoord(float a)
	{
		return a / BOX_TO_WORLD;
	}
}
