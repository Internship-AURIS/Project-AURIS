package com.aau.auris.game.screens;

import java.awt.image.BufferedImage;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.data.Player;
import com.aau.auris.game.level.Level;
import com.aau.auris.game.level.gameworld.Ball;
import com.aau.auris.game.level.gameworld.CollisionHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class GameScreen extends AbstractScreen
{
	// Assets

	// GameWorld
	private World world;
	private CollisionHandler collisionHandler;
	private Level level;
	private Ball ball;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	private SpriteBatch spriteBatch;

	// Other Variables
	private Player player;// Player Stats: score, achievements, etc.
	private float runTime;

	// UIComponents
	private Label lblLevel;
	private Label lblPlayerName;
	private Label lblPlayerScore;
	private Label lblPlayerCredits;

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
		// GameLogic
		collisionHandler = new CollisionHandler(game, this);

		// UIComponents
		final int s_width = Gdx.graphics.getWidth();
		final int s_height = Gdx.graphics.getHeight();
		final int width = s_width / 8;// component width
		final int height = s_height / 10;// component height

		// Status Bar: Level, PlayerName, PlayerScore
		LabelStyle lblStyle = new LabelStyle();
		lblStyle.font = bFont;
		lblStyle.fontColor = Color.WHITE;

		lblLevel = new Label("Lvl. -", lblStyle);
		lblLevel.setBounds(10, s_height - (height / 1.3f), width, height);

		lblPlayerName = new Label("Name:-------", lblStyle);
		lblPlayerName.setBounds(s_width / 2f - width, lblLevel.getY(), width, height);

		lblPlayerScore = new Label("Score: ---", lblStyle);
		lblPlayerScore.setBounds(s_width - width, lblLevel.getY(), width, height);

		lblPlayerCredits = new Label("Credits: ---", lblStyle);
		lblPlayerCredits.setBounds(lblPlayerScore.getX() - lblPlayerScore.getWidth() - width / 2, lblLevel.getY(), width, height);

		stage.addActor(lblLevel);
		stage.addActor(lblPlayerName);
		stage.addActor(lblPlayerCredits);
		stage.addActor(lblPlayerScore);
	}

	public void updateGameField(BufferedImage img)
	{
		// TODO: implement image processing
	}

	@Override
	protected void handleInput()
	{
		//		if (Gdx.input.isKeyPressed(Keys.DEL))
		//		{
		//			game.changeScreen(AURISGame.MENU_SCREEN, GameScreen.this);
		//		}
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
	}

	private void updateStatusBar()
	{
		level = game.getLevel();
		player = game.getPlayer();

		lblLevel.setText("Lvl. " + level.getID());
		lblPlayerName.setText("Name: " + player.getName());
		lblPlayerScore.setText("Score: " + player.getScore());
		lblPlayerCredits.setText("Credits: " + player.getCredits());
	}

	private void updateData()
	{
		player = game.getPlayer();
		runTime = 0.0f;

		// GameWorld
		this.level = game.getLevel();
		this.world = level.getWorld();
		collisionHandler.update();
		world.setContactListener(collisionHandler);
		this.level = game.getLevel();
		this.ball = level.getBall();
		this.debugRenderer = level.getDebugRenderer();
		this.camera = level.getCamera();
		this.spriteBatch = new SpriteBatch();
	}

	@Override
	public void render(float delta)
	{
		super.render(delta);
		spriteBatch.setProjectionMatrix(camera.combined);
		debugRenderer.render(world, camera.combined);

		spriteBatch.begin();
		spriteBatch.draw(ball.getCurrentKeyFrame(runTime), ball.getBody().getPosition().x - (ball.CIRCLE_RADIUS + 4), ball.getBody().getPosition().y - (ball.CIRCLE_RADIUS + 3));
		spriteBatch.end();

		//physic updates
		world.step(Level.BOX_STEP, Level.BOX_VELOCITY_ITERATIONS, Level.BOX_POSITION_ITERATIONS);
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
		updateData();
		updateStatusBar();
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
