package com.aau.auris.game.screens;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.Asset.Asset;
import com.aau.auris.game.Asset.AssetLoader;
import com.aau.auris.game.level.gameworld.Ball;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public abstract class AbstractScreen implements Screen, Asset
{
	// Asset
	protected BitmapFont bFont;

	protected AURISGame game;
	protected Stage stage;
	protected Skin skin;
	protected Ball ball;

	// Screen Settings
	protected int sWidth, sHeight;

	public AbstractScreen(AURISGame game)
	{
		this.game = game;
		this.stage = new Stage();
		skin = new Skin();

		this.sWidth = Gdx.graphics.getWidth();
		this.sHeight = Gdx.graphics.getHeight();

		loadAsset();
		initComponents();
	}

	protected abstract void initComponents();

	@Override
	public void loadAsset()
	{
		bFont = AssetLoader.bFont;
	}

	@Override
	public void disposeAsset()
	{
		bFont = null;
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void show()
	{
		Gdx.input.setInputProcessor(stage);
		this.sWidth = Gdx.graphics.getWidth();
		this.sHeight = Gdx.graphics.getHeight();
	}

	@Override
	public void resize(int width, int height)
	{
		stage.getViewport().update(width, height);
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
		Array<Actor> actors = stage.getActors();
		for (Actor a : actors)
		{
			a.clearListeners();
			a.getListeners().clear();
			a.getCaptureListeners().clear();
		}
		disposeAsset();
		skin.dispose();
		stage.dispose();
	}
}
