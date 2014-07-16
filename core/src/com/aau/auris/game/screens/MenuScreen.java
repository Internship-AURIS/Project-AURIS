package com.aau.auris.game.screens;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.Asset;
import com.aau.auris.game.AssetLoader;
import com.aau.auris.game.items.HighScore;
import com.aau.auris.game.items.MenuBall;
import com.aau.auris.game.userdata.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;

public class MenuScreen implements Screen, Asset
{
	// Asset
	private TextureRegion[][] tmp;
	private Animation parachuteBallAnimation1;
	private Animation parachuteBallAnimation2;
	private Sound hoverSound1, hoverSound2, hoverSound3;
	private Sound menuMusic, clickSound;
	private Texture background;
	private TextureAtlas buttonAtlas;
	private BitmapFont bFont;

	// Button Properties
	private static final float BUTTON_WIDTH = 200f;
	private static final float BUTTON_HEIGHT = 80f;
	private static final float BUTTON_SPACING = 10f;
	private static final float BUTTON_POS_X = 240f;
	private static final float BUTTON_POS_Y = 300f;

	// Local variables
	private AURISGame game;
	private HighScore highscore;

	private Stage stage;
	private SpriteBatch batch;
	private Skin skin;
	private float runTime;

	// Decoration
	private ArrayList<MenuBall> menuballs;
	private long lastBallTime;

	public MenuScreen(AURISGame game)
	{
		this.game = game;
		this.highscore = new HighScore(this.game);
		this.menuballs = new ArrayList<MenuBall>();
		lastBallTime = 0;

		this.stage = new Stage();

		loadAsset();
	}

	@Override
	public void loadAsset()
	{
		// Textures
		tmp = AssetLoader.tmp;
		// Animation
		parachuteBallAnimation1 = AssetLoader.parachuteBallAnimation1;
		parachuteBallAnimation2 = AssetLoader.parachuteBallAnimation2;

		// Sound
		hoverSound1 = AssetLoader.hoverSound1;
		hoverSound2 = AssetLoader.hoverSound2;
		hoverSound3 = AssetLoader.hoverSound3;
		menuMusic = AssetLoader.menuMusic1;
		clickSound = AssetLoader.clickSound;
		background = AssetLoader.menu_background;
		buttonAtlas = AssetLoader.menu_buttons;

		// Font
		bFont = AssetLoader.bFont;
	}

	@Override
	public void disposeAsset()
	{
		tmp = null;
		parachuteBallAnimation1 = null;
		parachuteBallAnimation2 = null;
		hoverSound1 = null;
		hoverSound2 = null;
		hoverSound3 = null;
		menuMusic = null;
		clickSound = null;
		background = null;
		buttonAtlas = null;
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		runTime += delta;

		stage.act(delta);
		stage.draw();

		// menuBalls logic
		updateMenuBalls(delta);

		batch.begin();
		for (MenuBall ball : menuballs)
		{
			batch.draw(ball.getKeyFrame(runTime), ball.getX(), ball.getY());
		}
		batch.end();

		if (TimeUtils.millis() - lastBallTime > 5000)
		{
			spawnBall();
		}
	}

	private void spawnBall()
	{
		Random r = new Random();
		TextureRegion[] keyFrames = r.nextInt(2) == 0 ? parachuteBallAnimation1.getKeyFrames() : parachuteBallAnimation2.getKeyFrames();
		MenuBall ball = new MenuBall(r.nextInt(10) <= 2 ? r.nextInt(121) : (440 + r.nextInt(370)), game.getHeight(), new Animation(0.10f, keyFrames));
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

	@Override
	public void resize(int width, int height)
	{
		stage.getViewport().update(width, height);
	}

	@Override
	public void show()
	{
		Gdx.input.setInputProcessor(stage);

		menuMusic.setLooping(0, true);
		menuMusic.play();

		batch = new SpriteBatch();
		skin = new Skin(buttonAtlas);

		Pixmap pixmap = new Pixmap(100, 100, Format.RGBA8888);
		pixmap.setColor(Color.MAROON);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		BitmapFont bfont = bFont;
		//		bfont.scale(1);
		skin.add("default", bfont);

		// HighScore List
		LabelStyle lStyle = new LabelStyle();
		lStyle.font = bfont;
		lStyle.fontColor = Color.BLACK;
		lStyle.background = skin.getDrawable("score");

		Label lblName = new Label("", lStyle);
		lblName.setAlignment(Align.center);
		lblName.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		lblName.setPosition(443, 200);

		LabelStyle lStylePlayers = new LabelStyle();
		lStylePlayers.font = bfont;
		lStylePlayers.fontColor = Color.BLACK;

		ArrayList<Player> playerList = highscore.getScoreList();
		String[] scoreList = new String[3];
		for (int i = 0; i < scoreList.length; i++)
		{
			Player player = playerList.size() > i ? playerList.get(i) : null;
			scoreList[i] = player == null ? ((i + 1) + ". --------: ---") : (i + 1) + ". " + player.getName() + ": " + player.getScore();
		}

		Label lblTop1 = new Label(scoreList[0], lStylePlayers);
		Label lblTop2 = new Label(scoreList[1], lStylePlayers);
		Label lblTop3 = new Label(scoreList[2], lStylePlayers);

		lblTop1.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		lblTop1.setPosition(game.getWidth() / 2 + game.getWidth() / 20, game.getHeight() / 2 - game.getHeight() / 9 * 2);
		lblTop2.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		lblTop2.setPosition(game.getWidth() / 2 + game.getWidth() / 20, game.getHeight() / 2 - game.getHeight() / 9 * 2 - 30);
		lblTop3.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		lblTop3.setPosition(game.getWidth() / 2 + game.getWidth() / 20, game.getHeight() / 2 - game.getHeight() / 9 * 2 - 60);

		// TextButton "START"
		TextButtonStyle textbuttonStyleStart = new TextButtonStyle();
		textbuttonStyleStart.up = skin.getDrawable("btnStart");
		textbuttonStyleStart.down = skin.newDrawable("white", Color.MAROON);
		textbuttonStyleStart.checked = skin.newDrawable("white", Color.LIGHT_GRAY);
		textbuttonStyleStart.over = skin.getDrawable("btnStartOver");
		textbuttonStyleStart.font = skin.getFont("default");
		skin.add("start", textbuttonStyleStart);

		TextButton btnStart = new TextButton("", textbuttonStyleStart);
		btnStart.setPosition(BUTTON_POS_X, BUTTON_POS_Y);
		btnStart.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

		// TextButton "CREDITS"
		TextButtonStyle textbuttonStyleCredits = new TextButtonStyle();
		textbuttonStyleCredits.up = skin.getDrawable("btnCredits");
		textbuttonStyleCredits.down = skin.newDrawable("white", Color.MAROON);
		textbuttonStyleCredits.checked = skin.newDrawable("white", Color.LIGHT_GRAY);
		textbuttonStyleCredits.over = skin.getDrawable("btnCreditsOver");
		textbuttonStyleCredits.font = skin.getFont("default");
		skin.add("start", textbuttonStyleCredits);

		TextButton btnCredits = new TextButton("", textbuttonStyleCredits);
		btnCredits.setPosition(BUTTON_POS_X, BUTTON_POS_Y - 100);
		btnCredits.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

		// TextButton "EXIT"
		TextButtonStyle textbuttonStyleExit = new TextButtonStyle();
		textbuttonStyleExit.up = skin.getDrawable("btnExit");
		textbuttonStyleExit.down = skin.getDrawable("btnExit");
		textbuttonStyleExit.checked = skin.newDrawable("white", Color.LIGHT_GRAY);
		textbuttonStyleExit.over = skin.getDrawable("btnExitOver");
		textbuttonStyleExit.font = skin.getFont("default");
		skin.add("btnExit", textbuttonStyleExit);

		TextButton btnExit = new TextButton("", textbuttonStyleExit);
		btnExit.setPosition(BUTTON_POS_X, BUTTON_POS_Y - 200);
		btnExit.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

		// EventListener
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

		btnCredits.addListener(new InputListener()
		{
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				super.enter(event, x, y, pointer, fromActor);
				hoverSound2.play();
			}
		});

		btnExit.addListener(new ClickListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				super.touchUp(event, x, y, pointer, button);
				// TODO: implements cleaner way to exit application
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
	}

	@Override
	public void hide()
	{
		menuMusic.stop();
	}

	@Override
	public void pause()
	{}

	@Override
	public void resume()
	{}

	@Override
	public void dispose()
	{
		disposeAsset();
		stage.dispose();
		skin.dispose();
	}

}
