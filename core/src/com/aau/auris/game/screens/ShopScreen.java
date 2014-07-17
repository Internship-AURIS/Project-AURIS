package com.aau.auris.game.screens;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.Asset.AssetLoader;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class ShopScreen extends AbstractScreen
{
	// Asset
	private TextureAtlas menuButtons;
	private Sound clickSound;
	private Sound hoverSound;

	public ShopScreen(AURISGame game)
	{
		super(game);
	}

	@Override
	protected void initComponents()
	{}

	@Override
	public void loadAsset()
	{
		super.loadAsset();
		menuButtons = AssetLoader.menu_buttons;
		clickSound = AssetLoader.clickSound;
		hoverSound = AssetLoader.hoverSound1;
	}

	@Override
	public void disposeAsset()
	{
		super.disposeAsset();
	}

	@Override
	public void render(float delta)
	{
		super.render(delta);
	}

	@Override
	public void resize(int width, int height)
	{}

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
}
