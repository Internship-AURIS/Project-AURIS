package com.aau.auris.game.screens;

import java.util.ArrayList;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.data.Player;
import com.aau.auris.game.level.Level;
import com.aau.auris.game.level.entity.Ball;
import com.aau.auris.game.level.entity.Obstacle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class GameScreen extends AbstractScreen
{
	// Assets

	// GameLogic Settings
	private static final Vector2 GRAVITY = new Vector2(0, 0);
	private static final float BOX_STEP = 1 / 60f;
	private static final int BOX_VELOCITY_ITERATIONS = 6;
	private static final int BOX_POSITION_ITERATIONS = 2;

	// GameWorld Settings
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	private SpriteBatch spriteBatch;

	private Ball ball;
	private float runTime;
	private ArrayList<Obstacle> obstacles;

	// Other Variables
	private Player player;// Player Stats: score, achievements, etc.
	private Level level;

	public GameScreen(AURISGame game, int levelDiff)
	{
		super(game);
	}

	@Override
	public void loadAsset()
	{
		super.loadAsset();
	}

	@Override
	public void disposeAsset()
	{
		super.disposeAsset();
	}

	@Override
	protected void initComponents()
	{
		player = game.getPlayer();
		runTime = 0.0f;

		// GameWorld settings
		this.world = new World(GRAVITY, true);

		camera = new OrthographicCamera();
		camera.viewportWidth = Gdx.graphics.getWidth();
		camera.viewportHeight = Gdx.graphics.getHeight();
		camera.position.set(camera.viewportWidth * .5f, camera.viewportHeight * 0.5f, 0f);
		camera.update();

		spriteBatch = new SpriteBatch();
		debugRenderer = new Box2DDebugRenderer();

		// Initialize GameObjects
		this.obstacles = new ArrayList<Obstacle>();
		obstacles.add(new Obstacle(world, 50, 50, 10, 100));
		obstacles.add(new Obstacle(world, 85, 20, 100, 10));
		obstacles.add(new Obstacle(world, 100, 50, 10, 100));
		obstacles.add(new Obstacle(world, 85, 150, 100, 10));

		ball = new Ball(world, camera.viewportWidth / 2, camera.viewportHeight / 2);
	}

	public void setLevel(Level lvl)
	{
		this.level = lvl;
		System.out.println("...gamescreen level set to: " + lvl);// TODO: debugging
	}

	@Override
	public void render(float delta)
	{
		super.render(delta);
		if (Gdx.input.isKeyPressed(Keys.UP))
		{
			ball.getBody().setLinearVelocity(0, 120);
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN))
		{
			ball.getBody().setLinearVelocity(0, -120);
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT))
		{
			ball.getBody().setLinearVelocity(-120, 0);
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			ball.getBody().setLinearVelocity(120, 0);
		}
		spriteBatch.setProjectionMatrix(camera.combined);
		debugRenderer.render(world, camera.combined);

		spriteBatch.begin();
		spriteBatch.draw(ball.getCurrentKeyFrame(runTime), ball.getBody().getPosition().x - (ball.CIRCLE_RADIUS + 4), ball.getBody().getPosition().y - (ball.CIRCLE_RADIUS + 3));
		spriteBatch.end();

		//physic updates
		world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
	}

	@Override
	public void resize(int width, int height)
	{
		super.resize(width, height);
	}

	@Override
	public void show()
	{
		super.show();

		final int s_width = Gdx.graphics.getWidth();
		final int s_height = Gdx.graphics.getHeight();
		final int width = s_width / 8;// component width
		final int height = s_height / 10;// component height

		// Status Bar: PlayerName, PlayerScore, Level
		LabelStyle lblStyle = new LabelStyle();
		lblStyle.font = bFont;
		lblStyle.fontColor = Color.WHITE;
		Label lblPlayerName = new Label(game.getPlayer().getName(), lblStyle);
		lblPlayerName.setBounds(10, s_height - (height / 1.3f), width, height);

		Label lblPlayerCredits = new Label("Credits: " + game.getPlayer().getCredits(), lblStyle);
		lblPlayerCredits.setBounds(lblPlayerName.getX() + lblPlayerName.getWidth(), lblPlayerName.getY(), width, height);

		Label lblPlayerScore = new Label("Score: " + game.getPlayer().getScore(), lblStyle);
		lblPlayerScore.setBounds(lblPlayerCredits.getX() + lblPlayerCredits.getWidth(), lblPlayerCredits.getY(), width, height);

		stage.addActor(lblPlayerName);
		stage.addActor(lblPlayerCredits);
		stage.addActor(lblPlayerScore);
	}

	@Override
	public void hide()
	{
		super.hide();
	}

	@Override
	public void pause()
	{
		super.pause();
	}

	@Override
	public void resume()
	{
		super.resume();
	}

	@Override
	public void dispose()
	{
		super.dispose();
	}

}
