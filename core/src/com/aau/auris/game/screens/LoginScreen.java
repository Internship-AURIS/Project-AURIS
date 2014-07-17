package com.aau.auris.game.screens;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.Asset;
import com.aau.auris.game.AssetLoader;
import com.aau.auris.game.items.MenuBall;
import com.aau.auris.game.userdata.Player;
import com.aau.auris.game.userdata.UserData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;

public class LoginScreen implements Screen, Asset {
	// Assets
	private TextureAtlas menuButtons;
	private BitmapFont bFont;
	private Texture background;
	private Sound backMusic;
	private Sound hoverSound1;
	private Animation parachuteBallAnimation2;
	private Animation parachuteBallAnimation1;
	private SpriteBatch batch;
	private float runTime;

	private AURISGame game;
	private UserData userdata;

	private Stage stage;
	private Skin skin;
	private Label lblName;
	private TextField txtName;
	private TextButton tbStart;
	private TextButton tbBack;
	//DECORATION:
	private ArrayList<MenuBall> menuballs;
	private long lastBallTime;

	public LoginScreen(AURISGame game) {
		this.game = game;
		this.menuballs = new ArrayList<MenuBall>();
		batch = new SpriteBatch();
		lastBallTime = 0;
		this.userdata = this.game.getUserData();
		loadAsset();
	}

	@Override
	public void loadAsset() {
		menuButtons = AssetLoader.menu_buttons;
		bFont = AssetLoader.bFont;
		background = AssetLoader.menu_background_blank;
		backMusic = AssetLoader.menuMusic1;
		hoverSound1=AssetLoader.hoverSound1;
		parachuteBallAnimation1 = AssetLoader.parachuteBallAnimation1;
		parachuteBallAnimation2 = AssetLoader.parachuteBallAnimation2;
	}

	@Override
	public void disposeAsset() {
		// TODO: delete assets
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		runTime += delta;
		
		stage.act(delta);
		stage.draw();
		updateMenuBalls(delta);

		batch.begin();
		for (MenuBall ball : menuballs) {
			batch.draw(ball.getKeyFrame(runTime), ball.getX(), ball.getY());
		}
		batch.end();
		if (TimeUtils.millis() - lastBallTime > 5000) {
			spawnBall();
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		// backMusic.play();
		// backMusic.setLooping(0, true);
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		skin = new Skin(menuButtons);
		Pixmap pixmap = new Pixmap(100, 100, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.GREEN);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		TextureRegion backTextRegion = new TextureRegion(background, 848, 480);
		Image img = new Image(backTextRegion);
		stage.addActor(img);

		LabelStyle lStyle = new LabelStyle();
		lStyle.font = bFont;
		lStyle.fontColor = Color.WHITE;
		lStyle.background = skin.getDrawable("btnName");
		lblName = new Label("", lStyle);
		lblName.setAlignment(Align.center);
		lblName.setSize(200, 80);
		lblName.setPosition(Gdx.graphics.getWidth() / 2 - lblName.getWidth()
				/ 2, Gdx.graphics.getHeight() / 2 - lblName.getHeight() / 2
				+ 120);
		stage.addActor(lblName);

		BitmapFont bFont = AssetLoader.bFont;
		//bFont.scale(1);

		TextFieldStyle tfStyle = new TextFieldStyle();
		tfStyle.fontColor = Color.WHITE;
		tfStyle.font = bFont;
		tfStyle.cursor=skin.getDrawable("bestCursor");
//		tfStyle.background = skin.newDrawable("white", Color.BLACK);
		txtName = new TextField("", tfStyle);
		txtName.setSize(160, 60);
		txtName.setPosition(Gdx.graphics.getWidth() / 2 - txtName.getWidth()
				/ 2, Gdx.graphics.getHeight() / 2 - txtName.getHeight() + 60);
		stage.addActor(txtName);

		skin.add("default", bFont);
		TextButtonStyle tbStyleBack = new TextButtonStyle();
		tbStyleBack.up = skin.getDrawable("btnBack");
		tbStyleBack.down = skin.getDrawable("btnBackSmall");
		tbStyleBack.over = skin.getDrawable("btnBackOver");
		tbStyleBack.font = skin.getFont("default");
		skin.add("default", tbStyleBack);
		
		TextButtonStyle tbStyleStart = new TextButtonStyle();
		tbStyleStart.up = skin.getDrawable("btnLoginStart");
		tbStyleStart.down = skin.getDrawable("btnLoginStartSmall");
		tbStyleStart.over = skin.getDrawable("btnLoginStartOver");
		tbStyleStart.font = skin.getFont("default");
		skin.add("default", tbStyleStart);

		tbStart = new TextButton("", tbStyleStart);
		tbStart.setSize(200, 80);
		tbStart.setPosition(Gdx.graphics.getWidth() / 2 - tbStart.getWidth()
				/ 2, Gdx.graphics.getHeight() / 2 - tbStart.getHeight() / 3 *4);
		
		tbStart.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				String inputName = txtName.getText();
				if (inputName == null || inputName.length() == 0) {
					System.out
							.println("...invalid player name!!! try again...");
					event.cancel();

					return;
				}
				/*
				 * valid input string
				 */
				Player loginPlayer = null;

				if (userdata.containsPlayerName(inputName))// username exists,
															// use this player
				{
					loginPlayer = userdata.getPlayerViaName(inputName);
				} else {// no player exists with the given inputName, create new
						// one

					loginPlayer = new Player(inputName, 0);
					userdata.createPlayer(loginPlayer);
					System.out.println("new player created: " + loginPlayer);
				}
				game.setPlayer(loginPlayer);
				game.changeScreen(AURISGame.LEVEL_SCREEN, LoginScreen.this);
			}
		});

		tbBack = new TextButton("", tbStyleBack);
		tbBack.setSize(200, 80);
		tbBack.setPosition(10, 10);
		tbBack.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				game.changeScreen(AURISGame.MENU_SCREEN, LoginScreen.this);
			}
		});

		tbBack.addListener(new InputListener() {
			@Override
			public void enter(InputEvent event, float x, float y, int pointer,
					Actor fromActor) {
				super.enter(event, x, y, pointer, fromActor);
				hoverSound1.play();
			}
		});
		stage.addActor(tbBack);
		stage.addActor(tbStart);
	}

	@Override
	public void hide() {
//		backMusic.stop();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
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
	private void spawnBall() {
		Random r = new Random();
		TextureRegion[] keyFrames = r.nextInt(2) == 0 ? parachuteBallAnimation1
				.getKeyFrames() : parachuteBallAnimation2.getKeyFrames();
		MenuBall ball = new MenuBall(r.nextInt(10) <= 2 ? r.nextInt(121)
				: (440 + r.nextInt(370)), game.getHeight(), new Animation(
				0.10f, keyFrames));
		ball.getAnimation().setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

		menuballs.add(ball);
		lastBallTime = TimeUtils.millis();
	}

}
