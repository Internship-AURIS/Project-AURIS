package com.aau.auris.game.screens;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.Asset;
import com.aau.auris.game.AssetLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public abstract class AbstractScreen implements Screen, Asset
{
	// Asset
	protected BitmapFont bFont;

	protected AURISGame game;
	protected Stage stage;
	protected Skin skin;

	public AbstractScreen(AURISGame game)
	{
		this.game = game;
		this.stage = new Stage();
		skin = new Skin();

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
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void show()
	{
		Gdx.input.setInputProcessor(stage);
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
	{}
}
