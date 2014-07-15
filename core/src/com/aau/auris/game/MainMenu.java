package com.aau.auris.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenu implements Screen {

	// BUTTON PROP.
	private static final float BUTTON_WIDTH = 200f;
	private static final float BUTTON_HEIGHT = 80f;
	private static final float BUTTON_SPACING = 10f;
	private static final float BUTTON_POS_X = 240f;
	private static final float BUTTON_POS_Y = 300f;
	//DESIGN:
	Stage stage;
	SpriteBatch batch;
	Skin skin;
	BitmapFont font;
	TextureAtlas atlas;
	Table scoreTable;
	private Game menugame;
	
	//SOUNNDS:
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
	//MUSIC:
	public MainMenu(Game menugame) {
		this.menugame = menugame;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		create();

	}

	public void create() {
		menuMusic.setLooping(true);
		menuMusic.play();
		batch = new SpriteBatch();

		atlas = new TextureAtlas("buttons.pack");
		skin = new Skin(atlas);
		scoreTable = new Table();
//		scoreTable.setFillParent(true);

		Pixmap pixmap = new Pixmap(100, 100, Format.RGBA8888);
		pixmap.setColor(Color.MAROON);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		BitmapFont bfont = new BitmapFont();
		bfont.scale(1);
		skin.add("default", bfont);

		// DESGIN FÜR EXIT:

		TextButtonStyle textbuttonStyleExit = new TextButtonStyle();
		textbuttonStyleExit.up = skin.getDrawable("btnexit");
		textbuttonStyleExit.down = skin.getDrawable("btnexit");
		textbuttonStyleExit.checked = skin.newDrawable("white",
				Color.LIGHT_GRAY);
		textbuttonStyleExit.over = skin.getDrawable("btnexitover");
		textbuttonStyleExit.font = skin.getFont("default");
		skin.add("default", textbuttonStyleExit);

		// DESIGN FÜR START:
		
		TextButtonStyle textbuttonStyleStart = new TextButtonStyle();
		textbuttonStyleStart.up = skin.getDrawable("btnstart");
		textbuttonStyleStart.down = skin.newDrawable("white", Color.MAROON);
		textbuttonStyleStart.checked = skin.newDrawable("white",
				Color.LIGHT_GRAY);
		textbuttonStyleStart.over = skin.getDrawable("btnstartover");
		textbuttonStyleStart.font = skin.getFont("default");
		skin.add("start", textbuttonStyleStart);
		
		//DESIGN FÜR CREDTIS:
		
		TextButtonStyle textbuttonStyleCredits = new TextButtonStyle();
		textbuttonStyleCredits.up = skin.getDrawable("btncredits");
		textbuttonStyleCredits.down = skin.newDrawable("white", Color.MAROON);
		textbuttonStyleCredits.checked = skin.newDrawable("white",
				Color.LIGHT_GRAY);
		textbuttonStyleCredits.over = skin.getDrawable("btncreditsover");
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
				Gdx.files.internal("Backpic.png"));
		TextureRegion backTextRegion = new TextureRegion(backgroundTexture,
				800, 600);
		Image img = new Image(backTextRegion);
		stage.addActor(img);
		stage.addActor(btnCredits);
		stage.addActor(btnStart);
		stage.addActor(btnExit);

		//LISTENER:
		
		btnStart.addListener(new ClickListener() {
			public void touchUp(InputEvent event, float x, float y, int point,
					int button) {
				clickSound.play();
				btnStart.setText("Starting...");
				// menugame.setScreen(new LoginScreen());
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
		

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		scoreTable.debug(); // turn on all debug lines (table, cell, and widget)
		scoreTable.debugTable();
		
		stage.act(delta);
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
	}

	@Override
	public void show() {

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
		stage.dispose();
		skin.dispose();

	}

}
