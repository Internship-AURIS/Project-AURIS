package com.aau.auris.game.screens;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.Asset.AssetLoader;
import com.aau.auris.game.items.MenuBall;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.TimeUtils;

public class CreditsScreen extends AbstractScreen
{
	// Asset
	private TextureAtlas menuButtons;
	private Sound clickSound;
	private Sound hoverSound;
	private Texture background;

	// Decoration
	private ArrayList<MenuBall> menuballs;
	private long lastBallTime;
	private float runTime;
	private SpriteBatch batch;

	//Animation
	private Animation parachuteBallAnimation2;
	private Animation parachuteBallAnimation1;

	public CreditsScreen(AURISGame game)
	{
		super(game);
		this.menuballs = new ArrayList<MenuBall>();
		batch = new SpriteBatch();
		lastBallTime = 0;
	}

	@Override
	public void loadAsset()
	{
		super.loadAsset();
		menuButtons = AssetLoader.menu_buttons;
		clickSound = AssetLoader.clickSound;
		hoverSound = AssetLoader.hoverSound1;
		background = AssetLoader.background_Credits;
		parachuteBallAnimation1 = AssetLoader.parachuteBallAnimation1;
		parachuteBallAnimation2 = AssetLoader.parachuteBallAnimation2;
	}

	@Override
	public void disposeAsset()
	{
		super.disposeAsset();
	}

	@Override
	protected void initComponents()
	{
		skin = new Skin(menuButtons);
		skin.add("default", bFont);
		TextureRegion backTextRegion = new TextureRegion(background, 848, 480);
		Image img = new Image(backTextRegion);
		stage.addActor(img);
		// TextButton "BACK"
		TextButtonStyle tbStyleBack = new TextButtonStyle();
		tbStyleBack.up = skin.getDrawable("btnBack");
		tbStyleBack.down = skin.getDrawable("btnBackSmall");
		tbStyleBack.over = skin.getDrawable("btnBackOver");
		tbStyleBack.font = skin.getFont("default");
		skin.add("default", tbStyleBack);
		TextButton tbBack = new TextButton("", tbStyleBack);
		tbBack.setSize(140, 50);
		tbBack.setPosition(10, 10);
		tbBack.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				clickSound.play();
				game.changeScreen(AURISGame.MENU_SCREEN, CreditsScreen.this);
			}
		});
		tbBack.addListener(new InputListener()
		{
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				super.enter(event, x, y, pointer, fromActor);
				hoverSound.play();
			}
		});

		stage.addActor(tbBack);
	}

	@Override
	protected void handleInput()
	{
		if (Gdx.input.isKeyPressed(Keys.DEL))
		{
			game.changeScreen(AURISGame.MENU_SCREEN, CreditsScreen.this);
		}
	}

	@Override
	public void render(float delta)
	{
		super.render(delta);
		runTime += delta;
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

	@Override
	public void resize(int width, int height)
	{
		super.resize(width, height);
	}

	@Override
	public void show()
	{
		super.show();
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

	private void spawnBall()
	{
		Random r = new Random();
		TextureRegion[] keyFrames = r.nextInt(2) == 0 ? parachuteBallAnimation1.getKeyFrames() : parachuteBallAnimation2.getKeyFrames();
		MenuBall ball = new MenuBall(r.nextInt(10) <= 2 ? r.nextInt(121) : (440 + r.nextInt(370)), game.getHeight(), new Animation(0.10f, keyFrames));
		ball.getAnimation().setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

		menuballs.add(ball);
		lastBallTime = TimeUtils.millis();
	}
}
