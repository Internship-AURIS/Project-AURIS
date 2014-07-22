package com.aau.auris.game.screens;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.Asset.AssetLoader;
import com.aau.auris.game.data.Player;
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
	private Texture levelBalken;

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
	private Texture backGround;

	// UIComponents
	private Label lblLevel;
	private Label lblPlayerName;
	private Label lblPlayerScore;
	private Label lblPlayerCredits;
	private Label lblBalken;
	private TextButton btnBack;

	public GameScreen(AURISGame game)
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
		levelButtons=AssetLoader.levelButtons;
		skin= new Skin(levelButtons);
		levelBalken=AssetLoader.levelBalken;

		// Status Bar: Level, PlayerName, PlayerScore
		LabelStyle lblStyle = new LabelStyle();
		lblStyle.font = bFont;
		lblStyle.fontColor = Color.WHITE;

		lblLevel = new Label("Lvl. -", lblStyle);
		lblLevel.setBounds(10, s_height - (height / 1.3f), width, height);

		lblPlayerName = new Label("Name:-------", lblStyle);
		lblPlayerName.setBounds(s_width / 2f - width, lblLevel.getY(), width, height);
		

		lblPlayerScore = new Label("Score: ---", lblStyle);
		lblPlayerScore.setBounds(s_width - width*1.3f, lblLevel.getY(), width, height);

		lblPlayerCredits = new Label("Credits: ---", lblStyle);
		lblPlayerCredits.setBounds(lblPlayerScore.getX() - lblPlayerScore.getWidth() - width / 2, lblLevel.getY(), width, height);

		//Balken LABEL:
		LabelStyle lblBalkenStyle = new LabelStyle();
		lblBalkenStyle.font = bFont;
		lblBalkenStyle.fontColor = Color.WHITE;
		lblBalkenStyle.background=skin.getDrawable("balken");
		
		lblBalken=new Label("",lblBalkenStyle);
		lblBalken.setBounds(0,game.getHeight()-40,game.getWidth(),lblLevel.getHeight()+5);
		
		//Back-Button
		Pixmap pixmap = new Pixmap(800, 800, Format.RGBA8888);
		pixmap.setColor(Color.BLUE);
		pixmap.fill();
		skin.add("default", new Texture(pixmap));
		TextButtonStyle btnBackStyle= new TextButtonStyle();
		btnBackStyle.font = bFont;
		btnBackStyle.fontColor = Color.WHITE;
		btnBackStyle.up=skin.getDrawable("vertBack");
		skin.add("backstyle", btnBackStyle);
		btnBack=new TextButton("",btnBackStyle);
		btnBack.setSize(game.getWidth()/19, game.getHeight()/4.75f);
		btnBack.setPosition(0, 0);
		btnBack.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				game.changeScreen(AURISGame.LEVEL_SCREEN, GameScreen.this);
			}
			
		});
		stage.addActor(lblBalken);
		stage.addActor(lblLevel);
		stage.addActor(lblPlayerName);
		stage.addActor(lblPlayerCredits);
		stage.addActor(lblPlayerScore);
		
		stage.addActor(btnBack);
	}

	public void updateGameField(BufferedImage img)
	{
		byte[] encodedData = ((DataBufferByte) img.getData().getDataBuffer()).getData();
		Pixmap pixmap = new Pixmap(encodedData, 0, encodedData.length);
		backGround = new Texture(pixmap);
		// TODO: implement image processing
		System.out.println("GameScreen.updateGameField() successfully performed!");
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

		lblLevel.setText("Lvl. " + (level.getID() + 1));
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
		if (backGround != null)
		{
			System.out.println("render");
			spriteBatch.draw(backGround, 0, 0, game.getWidth(), game.getHeight());
		}
		spriteBatch.draw(ball.getCurrentKeyFrame(runTime), ball.getBody().getPosition().x - (ball.CIRCLE_RADIUS + 4), ball.getBody().getPosition().y - (ball.CIRCLE_RADIUS + 3));
//		spriteBatch.draw(levelBalken, 0, game.getHeight()-50);
		
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
