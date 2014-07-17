package com.aau.auris.game.screens;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.Asset.AssetLoader;
import com.aau.auris.game.data.Player;
import com.aau.auris.game.items.HighScore;
import com.aau.auris.game.items.MenuBall;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;

public class MenuScreen extends AbstractScreen
{
	// Asset
	private Texture background;
	private TextureAtlas menuButtons;
	private Animation parachuteBallAnimation1;
	private Animation parachuteBallAnimation2;
	private Animation parachuteBallAnimation3;
	private Sound hoverSound1, hoverSound2, hoverSound3;
	private Sound menuMusic, clickSound;
	private Sound chorusSound;

	// Button Properties
	private static final float BUTTON_WIDTH = 200f;
	private static final float BUTTON_HEIGHT = 80f;
	// private static final float BUTTON_SPACING = 10f;
	private static final float BUTTON_POS_X = 240f;
	private static final float BUTTON_POS_Y = 300f;

	// Local variables
	private SpriteBatch batch;
	private float runTime;

	// Variables for HighScoreList
	private HighScore highscore;
	private ArrayList<Player> playerList;
	private Label lblTop1, lblTop2, lblTop3;

	// Decoration
	private ArrayList<MenuBall> menuballs;
	private long lastBallTime;
	private boolean enableIlluminati = false;
	private float spawnTime = TimeUtils.millis();

	public MenuScreen(AURISGame game)
	{
		super(game);
		spawnTime = 4500;

		menuMusic.play();
		menuMusic.setLooping(0, true);
	}

	@Override
	public void loadAsset()
	{
		super.loadAsset();

		// Textures
		background = AssetLoader.menu_background;
		menuButtons = AssetLoader.menu_buttons;

		// Animation
		parachuteBallAnimation1 = AssetLoader.parachuteBallAnimation1;
		parachuteBallAnimation2 = AssetLoader.parachuteBallAnimation2;
		parachuteBallAnimation3 = AssetLoader.parachuteBallAnimation3;

		// Sound
		hoverSound1 = AssetLoader.hoverSound1;
		hoverSound2 = AssetLoader.hoverSound2;
		hoverSound3 = AssetLoader.hoverSound3;
		menuMusic = AssetLoader.menuMusic1;
		clickSound = AssetLoader.clickSound;
		chorusSound = AssetLoader.chorusSound;
	}

	@Override
	public void disposeAsset()
	{
		super.disposeAsset();
		parachuteBallAnimation1 = null;
		parachuteBallAnimation2 = null;
		parachuteBallAnimation3 = null;
		hoverSound1 = null;
		hoverSound2 = null;
		hoverSound3 = null;
		menuMusic = null;
		clickSound = null;
		background = null;
		menuButtons = null;
	}

	private void spawnBall()
	{
		Random r = new Random();
		final int randomInt = r.nextInt(11);
		TextureRegion[] keyFrames = null;
		if (enableIlluminati)
		{
			if (randomInt > 6)
			{
				keyFrames = r.nextInt(2) == 0 ? parachuteBallAnimation1.getKeyFrames() : parachuteBallAnimation2.getKeyFrames();
			} else
			{
				keyFrames = parachuteBallAnimation3.getKeyFrames();
			}
		} else
		{
			keyFrames = r.nextInt(2) == 0 ? parachuteBallAnimation1.getKeyFrames() : parachuteBallAnimation2.getKeyFrames();
		}

		MenuBall ball = new MenuBall(r.nextInt(10) <= 2 ? r.nextInt(121) : (440 + r.nextInt(270)), game.getHeight(), new Animation(0.10f, keyFrames));
		ball.getAnimation().setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

		menuballs.add(ball);
		lastBallTime = TimeUtils.millis();
	}

	public void updateMenuBalls(float deltaTime)
	{
		Iterator<MenuBall> iter = menuballs.iterator();
		while (iter.hasNext())
		{
			MenuBall ball = iter.next();
			ball.setY((ball.getY() - 50 * deltaTime));
			if (ball.getY() < -120)
			{
				iter.remove();
			}
		}
	}

	protected void initComponents()
	{
		this.highscore = new HighScore(this.game);
		this.playerList = highscore.getScoreList();
		this.menuballs = new ArrayList<MenuBall>();
		this.lastBallTime = 0;

		skin = new Skin(menuButtons);
		batch = new SpriteBatch();

		skin.add("default", bFont);

		// HighScore List
		LabelStyle lStyle = new LabelStyle();
		lStyle.font = bFont;
		lStyle.fontColor = Color.BLACK;
		lStyle.background = skin.getDrawable("score");

		Label lblName = new Label("", lStyle);
		lblName.setAlignment(Align.center);
		lblName.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		lblName.setPosition(443, 200);

		LabelStyle lStylePlayers = new LabelStyle();
		lStylePlayers.font = bFont;
		lStylePlayers.fontColor = Color.BLACK;

		lblTop1 = new Label("", lStylePlayers);
		lblTop2 = new Label("", lStylePlayers);
		lblTop3 = new Label("", lStylePlayers);

		lblTop1.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		lblTop1.setPosition(game.getWidth() / 2 + game.getWidth() / 20, game.getHeight() / 2 - game.getHeight() / 9 * 2);
		lblTop2.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		lblTop2.setPosition(game.getWidth() / 2 + game.getWidth() / 20, game.getHeight() / 2 - game.getHeight() / 9 * 2 - 30);
		lblTop3.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		lblTop3.setPosition(game.getWidth() / 2 + game.getWidth() / 20, game.getHeight() / 2 - game.getHeight() / 9 * 2 - 60);

		// TextButton "PYRAMID"
		TextButtonStyle textbuttonStylePyramid = new TextButtonStyle();
		textbuttonStylePyramid.up = skin.getDrawable("trans");
		textbuttonStylePyramid.down = skin.getDrawable("trans");
		textbuttonStylePyramid.over = skin.getDrawable("trans");
		textbuttonStylePyramid.font = skin.getFont("default");
		skin.add("start", textbuttonStylePyramid);
		TextButton btnPyramid = new TextButton("", textbuttonStylePyramid);
		btnPyramid.setSize(20, 50);
		btnPyramid.setPosition(game.getWidth() / 2 - game.getWidth() / 3 - btnPyramid.getWidth(), game.getHeight() / 2);

		btnPyramid.addListener(new ClickListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				super.touchUp(event, x, y, pointer, button);
				enableIlluminati = true;
				menuMusic.pause();
				chorusSound.play();
				Timer.schedule(new Timer.Task()
				{

					@Override
					public void run()
					{
						menuMusic.resume();
					}
				}, 7);
				spawnTime = 2000;
			}
		});

		// TextButton "START"
		TextButtonStyle textbuttonStyleStart = new TextButtonStyle();
		textbuttonStyleStart.up = skin.getDrawable("btnStart");
		textbuttonStyleStart.down = skin.getDrawable("btnStartSmall");
		textbuttonStyleStart.over = skin.getDrawable("btnStartOver");
		textbuttonStyleStart.font = skin.getFont("default");
		skin.add("start", textbuttonStyleStart);
		TextButton btnStart = new TextButton("", textbuttonStyleStart);
		btnStart.setPosition(BUTTON_POS_X, BUTTON_POS_Y);
		btnStart.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		btnStart.addListener(new ClickListener()
		{
			public void touchUp(InputEvent event, float x, float y, int point, int button)
			{
				super.touchUp(event, x, y, point, button);
				clickSound.play();
				game.changeScreen(AURISGame.LOGIN_SCREEN, MenuScreen.this);
			}
		});
		btnStart.addListener(new InputListener()
		{
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				super.enter(event, x, y, pointer, fromActor);
				hoverSound1.play();
			}
		});

		// TextButton "CREDITS"
		TextButtonStyle textbuttonStyleCredits = new TextButtonStyle();
		textbuttonStyleCredits.up = skin.getDrawable("btnCredits");
		textbuttonStyleCredits.down = skin.getDrawable("btnCreditsSmall");
		textbuttonStyleCredits.over = skin.getDrawable("btnCreditsOver");
		textbuttonStyleCredits.font = skin.getFont("default");
		skin.add("start", textbuttonStyleCredits);
		TextButton btnCredits = new TextButton("", textbuttonStyleCredits);
		btnCredits.setPosition(BUTTON_POS_X, BUTTON_POS_Y - 100);
		btnCredits.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		btnCredits.addListener(new ClickListener()
		{
			public void touchUp(InputEvent event, float x, float y, int point, int button)
			{
				super.touchUp(event, x, y, point, button);
				clickSound.play();
				game.changeScreen(AURISGame.CREDITS_SCREEN, MenuScreen.this);
			}
		});
		btnCredits.addListener(new InputListener()
		{
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				super.enter(event, x, y, pointer, fromActor);
				hoverSound2.play();
			}
		});

		// TextButton "EXIT"
		TextButtonStyle textbuttonStyleExit = new TextButtonStyle();
		textbuttonStyleExit.up = skin.getDrawable("btnExit");
		textbuttonStyleExit.down = skin.getDrawable("btnExitSmalll");
		textbuttonStyleExit.over = skin.getDrawable("btnExitOver");
		textbuttonStyleExit.font = skin.getFont("default");
		skin.add("btnExit", textbuttonStyleExit);
		TextButton btnExit = new TextButton("", textbuttonStyleExit);
		btnExit.setPosition(BUTTON_POS_X, BUTTON_POS_Y - 200);
		btnExit.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		btnExit.addListener(new ClickListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				super.touchUp(event, x, y, pointer, button);
				clickSound.play();
				game.dispose();
				System.exit(0);
			}
		});
		btnExit.addListener(new InputListener()
		{
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				super.enter(event, x, y, pointer, fromActor);
				hoverSound3.play();
			}
		});

		// Background Image
		TextureRegion backTextRegion = new TextureRegion(background, 848, 480);
		Image img = new Image(backTextRegion);

		stage.addActor(img);
		stage.addActor(btnCredits);
		stage.addActor(btnStart);
		stage.addActor(btnExit);
		stage.addActor(lblName);
		stage.addActor(lblTop1);
		stage.addActor(lblTop2);
		stage.addActor(lblTop3);
		stage.addActor(btnPyramid);
	}

	@Override
	public void render(float delta)
	{
		super.render(delta);
		runTime += delta;

		// menuBalls logic
		updateMenuBalls(delta);

		batch.begin();
		for (MenuBall ball : menuballs)
		{
			batch.draw(ball.getKeyFrame(runTime), ball.getX(), ball.getY());
		}
		batch.end();
		if (TimeUtils.millis() - lastBallTime > spawnTime)
		{
			spawnBall();
		}
	}

	private void updateHighScoreList()
	{
		String[] scoreList = new String[3];
		for (int i = 0; i < scoreList.length; i++)
		{
			Player player = playerList.size() > i ? playerList.get(i) : null;
			scoreList[i] = player == null ? ((i + 1) + ". --------: ---") : (i + 1) + ". " + player.getName() + ": " + player.getScore();
		}
		lblTop1.setText(scoreList[0]);
		lblTop2.setText(scoreList[1]);
		lblTop3.setText(scoreList[2]);
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
		updateHighScoreList();
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
	{
		super.dispose();
	}
}
