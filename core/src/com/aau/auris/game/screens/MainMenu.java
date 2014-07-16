package com.aau.auris.game.screens;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.items.HighScore;
import com.aau.auris.game.items.MenuBall;
import com.aau.auris.game.userdata.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;

public class MainMenu implements Screen {

	private Texture region = new Texture(Gdx.files.internal("SpriteFly.png"));
	private Animation parachuteAnimation;

	public float runTime;

	// BUTTON PROP.
	private static final float BUTTON_WIDTH = 200f;
	private static final float BUTTON_HEIGHT = 80f;
	private static final float BUTTON_SPACING = 10f;
	private static final float BUTTON_POS_X = 240f;
	private static final float BUTTON_POS_Y = 300f;

	// DESIGN:
	private Stage stage;
	private SpriteBatch batch;
	private Skin skin;
	private BitmapFont font;
	private TextureAtlas atlas;
	private Table scoreTable;
	private AURISGame game;
	private ArrayList<MenuBall> menuballs = new ArrayList<MenuBall>();
	private Rectangle fallingBall;
	private long lastBallTime;
	private Label lblName;
	private Label lblScore;
	private Label lblTop1;
	private Label lblTop2;
	private Label lblTop3;
	private HighScore highscore;
	// Animation:
	private TextureRegion[][] tmp;

	// SOUNNDS:
	private TextButton btnStart;
	private Sound hoverSound = Gdx.audio.newSound(Gdx.files
			.internal("hover.wav"));
	private Sound hoverSound2 = Gdx.audio.newSound(Gdx.files
			.internal("hover2.wav"));
	private Sound hoverSound3 = Gdx.audio.newSound(Gdx.files
			.internal("hover3.wav"));
	private Music menuMusic = Gdx.audio.newMusic(Gdx.files
			.internal("Theme2.wav"));
	private Sound clickSound = Gdx.audio.newSound(Gdx.files
			.internal("click.wav"));

	// MUSIC:

	public MainMenu(AURISGame game) {
		this.game = game;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		highscore = new HighScore(game);

		initAnimation();

	}

	private void initAnimation() {
		runTime = 0;
		final int COLS = 7, ROWS = 4;

		tmp = TextureRegion.split(region, region.getWidth() / COLS,
				region.getHeight() / ROWS);

		// TextureRegion[] swing = new TextureRegion[COLS];
		// for (int i = 0; i < COLS; i++)
		// {
		// swing[i] = tmp[0][i];
		//
		// }
		// parachuteAnimation = new Animation(0.1f, swing);
		// parachuteAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
	}

	private void spawnBall() {
		Random r = new Random();
		TextureRegion[] keyFrames = new TextureRegion[7];
		int row = r.nextInt(2);
		MenuBall ball;
		if (r.nextInt(10) <= 2) {
			ball = new MenuBall(r.nextInt(121), game.getHeight(),
					new Animation(0.10f, keyFrames));
		} else {
			ball = new MenuBall(440 + r.nextInt(370), game.getHeight(),
					new Animation(0.10f, keyFrames));
		}

		for (int i = 0; i < keyFrames.length; i++) {
			keyFrames[i] = tmp[row][i];
		}

		ball.getAnimation().setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		menuballs.add(ball);
		lastBallTime = TimeUtils.millis();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		scoreTable.debug(); // turn on all debug lines (table, cell, and widget)
		scoreTable.debugTable();

		stage.act(delta);
		stage.draw();

		runTime += delta;
		// MenuBall logic
		updateMenuBalls(delta);

		batch.begin();
		// draw sample ball
		// batch.draw(parachuteAnimation.getKeyFrame(runTime, true), 100, 100);
		// draw menuballs
		for (MenuBall ball : menuballs) {
			batch.draw(ball.getKeyFrame(runTime), ball.getX(), ball.getY());
		}
		batch.end();

		if (TimeUtils.millis() - lastBallTime > 5000)
			spawnBall();

	}

	public void updateMenuBalls(float deltaTime) {
		Iterator<MenuBall> iter = menuballs.iterator();
		while (iter.hasNext()) {
			MenuBall ball = iter.next();
			ball.setY((ball.getY() - 50 * deltaTime));
			if (ball.getY() < -120) {
				iter.remove();
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		menuMusic.setLooping(true);
		menuMusic.play();
		batch = new SpriteBatch();

		atlas = new TextureAtlas("newbuttons.atlas");
		skin = new Skin(atlas);
		scoreTable = new Table();
		// scoreTable.setFillParent(true);

		Pixmap pixmap = new Pixmap(100, 100, Format.RGBA8888);
		pixmap.setColor(Color.MAROON);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		BitmapFont bfont = new BitmapFont(Gdx.files.internal("menufont.fnt"));
//		bfont.scale(0.5f);
		skin.add("default", bfont);

		LabelStyle lStyle = new LabelStyle();
		lStyle.font = bfont;
		lStyle.fontColor = Color.BLACK;
		lStyle.background = skin.getDrawable("score");
		lblName = new Label("", lStyle);
		lblName.setAlignment(Align.center);
		lblName.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		lblName.setPosition(443, 200);

		LabelStyle lStylePlayers = new LabelStyle();
		lStylePlayers.font = bfont;
		lStylePlayers.fontColor = Color.BLACK;

		ArrayList<Player> playerList = highscore.getScoreList();
		System.out.println(playerList.size());
		String[] scoreList = new String[3];
		for (int i = 0; i < scoreList.length; i++) {
			Player player = playerList.size() > i ? playerList.get(i) : null;
			if (player != null) {
				scoreList[i] = (i + 1) + ". " + player.getName() + ": "
						+ player.getScore();
			} else {
				scoreList[i] = (i + 1) + ". --------: ---";
			}
		}

		lblTop1 = new Label(scoreList[0], lStylePlayers);
		lblTop2 = new Label(scoreList[1], lStylePlayers);
		lblTop3 = new Label(scoreList[2], lStylePlayers);
		

		lblTop1.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		lblTop1.setPosition(game.getWidth() / 2 + game.getWidth() / 20,
				game.getHeight() / 2 - game.getHeight() / 9 * 2);
		lblTop2.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		lblTop2.setPosition(game.getWidth() / 2 + game.getWidth() / 20,
				game.getHeight() / 2 - game.getHeight() / 9 * 2 -30);
		lblTop3.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		lblTop3.setPosition(game.getWidth() / 2 + game.getWidth() / 20,
				game.getHeight() / 2 - game.getHeight() / 9 * 2 -60);
		// DESGIN FÜR EXIT:

		TextButtonStyle textbuttonStyleExit = new TextButtonStyle();
		textbuttonStyleExit.up = skin.getDrawable("btnExit");
		textbuttonStyleExit.down = skin.getDrawable("btnExitSmalll");
		textbuttonStyleExit.over = skin.getDrawable("btnExitOver");
		textbuttonStyleExit.font = skin.getFont("default");
		skin.add("default", textbuttonStyleExit);

		// DESIGN FÜR START:

		TextButtonStyle textbuttonStyleStart = new TextButtonStyle();
		textbuttonStyleStart.up = skin.getDrawable("btnStart");
		textbuttonStyleStart.down = skin.getDrawable("btnStartSmall");
		textbuttonStyleStart.over = skin.getDrawable("btnStartOver");
		textbuttonStyleStart.font = skin.getFont("default");
		skin.add("start", textbuttonStyleStart);

		// DESIGN FÜR CREDTIS:

		TextButtonStyle textbuttonStyleCredits = new TextButtonStyle();
		textbuttonStyleCredits.up = skin.getDrawable("btnCredits");
		textbuttonStyleCredits.down = skin.getDrawable("btnCreditsSmall");
		textbuttonStyleCredits.over = skin.getDrawable("btnCreditsOver");
		textbuttonStyleCredits.font = skin.getFont("default");
		skin.add("start", textbuttonStyleCredits);

		btnStart = new TextButton("", textbuttonStyleStart);
		btnStart.setPosition(BUTTON_POS_X, BUTTON_POS_Y);
		btnStart.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

		TextButton btnCredits = new TextButton("", textbuttonStyleCredits);
		btnCredits.setPosition(BUTTON_POS_X, BUTTON_POS_Y - 100);
		btnCredits.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

		TextButton btnExit = new TextButton("", textbuttonStyleExit);
		btnExit.setPosition(BUTTON_POS_X, BUTTON_POS_Y - 200);
		btnExit.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

		Texture backgroundTexture = new Texture(
				Gdx.files.internal("testback.png"));
		TextureRegion backTextRegion = new TextureRegion(backgroundTexture,
				848, 480);
		Image img = new Image(backTextRegion);

		stage.addActor(img);
		stage.addActor(btnCredits);
		stage.addActor(btnStart);
		stage.addActor(btnExit);
		stage.addActor(lblName);
		stage.addActor(lblTop1);
		stage.addActor(lblTop2);
		stage.addActor(lblTop3);

		// LISTENER:

		btnStart.addListener(new ClickListener() {
			public void touchUp(InputEvent event, float x, float y, int point,
					int button) {
				clickSound.play();
				game.changeScreen("login", MainMenu.this);
			}
		});
	

		btnStart.addListener(new InputListener() {
			@Override
			public void enter(InputEvent event, float x, float y, int pointer,
					Actor fromActor) {
				hoverSound.play();
				super.enter(event, x, y, pointer, fromActor);
			}
		});

		btnCredits.addListener(new InputListener() {
			@Override
			public void enter(InputEvent event, float x, float y, int pointer,
					Actor fromActor) {
				hoverSound2.play();
				super.enter(event, x, y, pointer, fromActor);
			}
		});

		btnExit.addListener(new InputListener() {
			@Override
			public void enter(InputEvent event, float x, float y, int pointer,
					Actor fromActor) {
				hoverSound3.play();
				super.enter(event, x, y, pointer, fromActor);
			}

		});

		btnExit.addListener(new ClickListener() {
			public void touchUp(InputEvent event, float x, float y, int point,
					int button) {
				System.exit(1);
			}

		});
		spawnBall();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// stage.dispose();
		// skin.dispose();
		// batch.dispose();
		// hoverSound.dispose();
		// hoverSound2.dispose();
		// hoverSound3.dispose();

	}

}
