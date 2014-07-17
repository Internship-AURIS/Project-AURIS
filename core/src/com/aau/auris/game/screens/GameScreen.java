package com.aau.auris.game.screens;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.levels.Level;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class GameScreen extends AbstractScreen
{
	private Level level;

	public GameScreen(AURISGame game)
	{
		super(game);
	}

	@Override
	protected void initComponents()
	{
	}

	public void setLevel(Level lvl)
	{
		this.level = lvl;
		System.out.println("..level set to: " + lvl);// TODO: debugging
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
	public void render(float delta)
	{
		super.render(delta);
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

		final int s_width = Gdx.graphics.getWidth();
		final int s_height = Gdx.graphics.getHeight();
		final int width = s_width / 6;// component width
		final int height = s_height / 10;// component height

		// Status Bar: PlayerName, PlayerScore, Level
//		LabelStyle lblStyle = new LabelStyle();
//		lblStyle.font = bFont;
//		lblStyle.fontColor = Color.WHITE;
//		Label lblPlayerName = new Label(game.getPlayer().getName(), lblStyle);
//		lblPlayerName.setBounds(width / 2, height / 2, width, height);
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
