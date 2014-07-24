package com.aau.auris.game.level;

import java.util.ArrayList;
import java.util.Random;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.Asset.Asset;
import com.aau.auris.game.Asset.AssetLoader;
import com.aau.auris.game.data.Player;
import com.aau.auris.game.level.gameworld.Ball;
import com.aau.auris.game.level.gameworld.BorderLine;
import com.aau.auris.game.level.gameworld.Goal;
import com.aau.auris.game.level.gameworld.Home;
import com.aau.auris.game.level.gameworld.Obstacle;
import com.badlogic.gdx.Gdx;
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
	public static final float WORLD_TO_BOX = 0.511111f;

	// Level
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	private Ball ball;

	private ArrayList<Obstacle> objects;
	private Home home;
	private Goal goal;
	private Skin skin;
	private Random goalYRandom;

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
		camera.viewportWidth = Gdx.graphics.getWidth();
		camera.viewportHeight = Gdx.graphics.getHeight();
		camera.position.set(camera.viewportWidth * 0.5f, camera.viewportHeight * 0.5f, 0f);
		camera.update();

		generateWorld(game.getWidth(), game.getHeight());
	}

	public void reset()
	{
		ball.setDead(false);
		world = new World(GRAVITY, true);
		generateWorld(game.getWidth(), game.getHeight());
	}

	@Override
	public void loadAsset()
	{
		goalTextures = AssetLoader.levelgoals;
		skin = new Skin(goalTextures);
		goalYRandom = new Random();
	}

	@Override
	public void disposeAsset()
	{}

	public void generateWorld(final int sWidth, final int sHeight)
	{

		// Initialize GameBorder
		final float factor_height = 1.95f;
		final float factor_width = 0.9367f;
		final float border_width = 20;// the object
		this.objects = new ArrayList<Obstacle>();
		objects.add(new BorderLine(world, -10 * WORLD_TO_BOX, 0, border_width * WORLD_TO_BOX, (sHeight * factor_height) * WORLD_TO_BOX));
		objects.add(new BorderLine(world, 0 * WORLD_TO_BOX, -10 * WORLD_TO_BOX, (sWidth * factor_height) * WORLD_TO_BOX, border_width * WORLD_TO_BOX));
		objects.add(new BorderLine(world, 0 * WORLD_TO_BOX, ((sHeight + border_width) * factor_width) * WORLD_TO_BOX, (sWidth * factor_height) * WORLD_TO_BOX, border_width * WORLD_TO_BOX));
		objects.add(new BorderLine(world, ((sWidth + border_width * 1.8f) * factor_width) * WORLD_TO_BOX, 0, border_width * WORLD_TO_BOX, (sHeight * factor_height) * WORLD_TO_BOX));

		// Initialize Levels
		Random r = new Random();
		int goalHeight = 0;
		if (id >= LEVEL_ID_1 && id <= LEVEL_ID_3)
		{
			// TODO: generate Level Difficulty 1
			goalHeight = r.nextInt(sHeight / 2) + sHeight / 4;
//			objects.add(new Obstacle(world, (50 / 2 - 50) * WORLD_TO_BOX, (50 / 2 - 50) * WORLD_TO_BOX, 50 * WORLD_TO_BOX, 50 * WORLD_TO_BOX));
			objects.add(new Obstacle(world, 150, 20, 50, 50));
		} else if (id >= LEVEL_ID_4 && id <= LEVEL_ID_6)
		{
			// TODO: generate Level Difficulty 2
			goalHeight = r.nextInt(sHeight / 2) + sHeight / 4;
			objects.add(new Obstacle(world, (50 / 2 - 50) * WORLD_TO_BOX, (50 / 2 - 50) * WORLD_TO_BOX, 50 * WORLD_TO_BOX, 50 * WORLD_TO_BOX));
		} else if (id >= LEVEL_ID_7 && id <= LEVEL_ID_9)
		{
			// TODO: generate Level Difficulty 3
			goalHeight = r.nextInt(sHeight / 2) + sHeight / 4;
			objects.add(new Obstacle(world, (50 / 2 - 50) * WORLD_TO_BOX, (50 / 2 - 50) * WORLD_TO_BOX, 50 * WORLD_TO_BOX, 50 * WORLD_TO_BOX));
		}

		// initialize static ever existing GameObjects
		final float menu_width = 90;
		final float menu_height = 200;
		final float goal_width = 40;
		final float randomY = (float) goalYRandom.nextInt(330);
		ball = new Ball(this, world, camera.viewportWidth / 2, camera.viewportHeight / 2, game.getPreferences().getBallRadius());
		home = new Home(world, 0 * WORLD_TO_BOX, 0 * WORLD_TO_BOX, menu_width * WORLD_TO_BOX, menu_height * WORLD_TO_BOX);
		goal = new Goal(world, (sWidth - goal_width) * WORLD_TO_BOX, randomY * WORLD_TO_BOX, goal_width, goalHeight);
		

	}

	public void setObjects(ArrayList<Obstacle> newObjects)
	{
		// TODO: blob/camera error?!?
		for (Obstacle o : objects)
		{
			world.destroyBody(o.getBody());
			o.getBody().setUserData(null);
			o = null;
		}
		this.objects = newObjects;
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
		skin.getDrawable("goal4Big").draw(spriteBatch, 790, goal.getBody().getPosition().y / WORLD_TO_BOX - 160, 60, 150);
		//		System.out.println(	goal.getBody().getPosition().y + "POSITION ZEICHNUNG");
	}
}
