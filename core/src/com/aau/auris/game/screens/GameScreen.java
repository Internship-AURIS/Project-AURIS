package com.aau.auris.game.screens;

import java.awt.image.BufferedImage;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.Asset.AssetLoader;
import com.aau.auris.game.data.Player;
import com.aau.auris.game.imageprocessing.ImageProcessor;
import com.aau.auris.game.items.BallSkin;
import com.aau.auris.game.level.Level;
import com.aau.auris.game.level.gameworld.Ball;
import com.aau.auris.game.level.gameworld.CollisionHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameScreen extends AbstractScreen
{
	// Assets
	private TextureAtlas levelButtons;

	// GameWorld
	private World world;
	private CollisionHandler collisionHandler;
	private Level level;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	private SpriteBatch spriteBatch;

	// Player
	private int ball_radius;
	private Ball ball;

	// Other Variables
	private Player player;// Player Stats: score, achievements, etc.
	private float runTime;
	private Texture backGround;

	// UIComponents
	private Label lblLevel;
	private Label lblPlayerName;
	private Label lblPlayerScore;
	private Label lblPlayerCredits;
	private Label lblBalken;
	private Label lblStatus;
	private TextButton btnBack;
	private BallSkin ballskin;

	public GameScreen(AURISGame game)
	{
		super(game);
		ball_radius = (int) game.getPreferences().getBallRadius() + 12;
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
		this.ballskin = new BallSkin();
		// GameLogic
		collisionHandler = new CollisionHandler(game, this);

		// UIComponents
		final int s_width = game.getWidth();
		final int s_height = game.getHeight();
		final int width = s_width / 8;// component width
		final int height = s_height / 10;// component height
		levelButtons = AssetLoader.levelButtons;
		skin = new Skin(levelButtons);

		// Status Bar: Level, PlayerName, PlayerScore
		LabelStyle lblStyle = new LabelStyle();
		lblStyle.font = bFont;
		lblStyle.fontColor = Color.WHITE;

		lblLevel = new Label("Lvl. -", lblStyle);
		lblLevel.setBounds(10, s_height - (height / 1.3f), width, height);

		lblPlayerName = new Label("Name:-------", lblStyle);
		lblPlayerName.setBounds(s_width / 2f - width, lblLevel.getY(), width, height);

		lblPlayerScore = new Label("Score: ---", lblStyle);
		lblPlayerScore.setBounds(s_width - width * 1.3f, lblLevel.getY(), width, height);

		lblPlayerCredits = new Label("Credits: --- $", lblStyle);
		lblPlayerCredits.setBounds(lblPlayerScore.getX() - lblPlayerScore.getWidth() - width / 2, lblLevel.getY(), width, height);

		lblStatus = new Label("", lblStyle);
		lblStatus.setBounds(s_width / 2 - width, s_height / 2 - height, width, height);

		//Balken LABEL:
		LabelStyle lblBalkenStyle = new LabelStyle();
		lblBalkenStyle.font = bFont;
		lblBalkenStyle.fontColor = Color.WHITE;
		lblBalkenStyle.background = skin.getDrawable("balken");

		lblBalken = new Label("", lblBalkenStyle);
		lblBalken.setBounds(0, game.getHeight() - 40, game.getWidth(), lblLevel.getHeight() + 5);

		//Back-Button
		Pixmap pixmap = new Pixmap(800, 800, Format.RGBA8888);
		pixmap.setColor(Color.BLUE);
		pixmap.fill();
		skin.add("default", new Texture(pixmap));
		TextButtonStyle btnBackStyle = new TextButtonStyle();
		btnBackStyle.font = bFont;
		btnBackStyle.fontColor = Color.WHITE;
		btnBackStyle.up = skin.getDrawable("vertBack");
		skin.add("backstyle", btnBackStyle);
		btnBack = new TextButton("", btnBackStyle);
		btnBack.setSize(game.getWidth() / 19, game.getHeight() / 4.75f);
		btnBack.setPosition(0, 0);
		btnBack.addListener(new ClickListener()
		{

			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				super.clicked(event, x, y);
				game.changeScreen(AURISGame.LEVEL_SCREEN, GameScreen.this);
			}

		});
		stage.addActor(lblBalken);
		stage.addActor(lblLevel);
		stage.addActor(lblPlayerName);
		stage.addActor(lblPlayerCredits);
		stage.addActor(lblPlayerScore);
		stage.addActor(lblStatus);
		stage.addActor(btnBack);
	}

	public void updateGame(BufferedImage img)
	{
		// TODO: implement creation of objects in gameWorld
	}

	@Override
	protected void handleInput()
	{
		if (ball.isDead()) { return; }

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
		lblLevel.setText("Lvl. " + (level.getID() + 1));
		lblPlayerName.setText("Name: " + player.getName());
		lblPlayerScore.setText("Score: " + player.getScore());
		lblPlayerCredits.setText("Credits: " + player.getCredits() + " $");
	}

	private void updateData()
	{
		runTime = 0.0f;

		// GameWorld
		this.player = game.getPlayer();
		this.level = game.getLevel();
		this.world = level.getWorld();
		this.ball = level.getBall();
		collisionHandler.update();
		world.setContactListener(collisionHandler);
		this.debugRenderer = level.getDebugRenderer();
		this.camera = level.getCamera();
		this.spriteBatch = new SpriteBatch();
	}

	@Override
	public void render(float delta)
	{
		super.render(delta);
		runTime += delta;

		lblStatus.setText(ball.isDead() ? "GAME OVER" : "");
		spriteBatch.setProjectionMatrix(camera.combined);
		debugRenderer.render(world, camera.combined);

		spriteBatch.begin();

		if (backGround != null)
		{
			spriteBatch.draw(backGround, 0, 0, game.getWidth(), game.getHeight());
		}

		final int id = player.getSkinID();
		if (ball != null)
		{
			if (ball.isDead())
			{
				spriteBatch.draw(ballskin.getPopAnimation(id).getKeyFrame(runTime), ball.getBody().getPosition().x - (ball_radius + 4), ball.getBody().getPosition().y, ball_radius * 2f,
						ball_radius * 2f);
			} else
			{
				spriteBatch.draw(ballskin.getFlyAnimation(player.getSkinID()).getKeyFrame(runTime), ball.getBody().getPosition().x - (ball_radius + 4), ball.getBody().getPosition().y
						- (ball_radius + 3), ball_radius * 2f, ball_radius * 2f);
			}
		}

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
