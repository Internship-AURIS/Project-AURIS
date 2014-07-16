package com.aau.auris.game.screens;

import com.aau.auris.game.AURISGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LevelScreen implements Screen
{
	private AURISGame game;

	private Stage stage;
	private HorizontalGroup horizontalGroup;

	public LevelScreen(AURISGame game)
	{
		this.game = game;
		stage = new Stage();
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height)
	{}

	@Override
	public void show()
	{
		final int width = 50, height = 50;

		final Skin skin = new Skin();
		Pixmap pixmap = new Pixmap(50, 50, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.GREEN);
		pixmap.fill();
		skin.add("skin", new Texture(pixmap));

		horizontalGroup = new HorizontalGroup();
		horizontalGroup.setPosition(10, game.getHeight() - 150);
		horizontalGroup.setSize(game.getWidth(), 100);
		horizontalGroup.space(10);

		final BitmapFont font = new BitmapFont();
		final ImageTextButtonStyle itbStyle = new ImageTextButtonStyle();
		itbStyle.font = font;
		itbStyle.fontColor = Color.WHITE;
		itbStyle.imageUp = skin.newDrawable("skin", Color.GREEN);

		ImageTextButton itb1 = new ImageTextButton("text", itbStyle);
		itb1.setSize(width, height);
		itb1.add(itb1.getImage()).row();
		itb1.add(itb1.getLabel());

		ImageTextButton itb2 = new ImageTextButton("text", itbStyle);
		itb2.setSize(width, height);
		itb2.add(itb1.getImage()).row();
		itb2.add(itb1.getLabel());

		ImageTextButton itb3 = new ImageTextButton("text", itbStyle);
		itb1.setSize(width, height);
		itb3.add(itb1.getImage()).row();
		itb3.add(itb1.getLabel()).row();

		ImageTextButton itb4 = new ImageTextButton("text", itbStyle);
		itb4.setSize(width, height);
		itb4.add(itb1.getImage()).row();
		itb4.add(itb1.getLabel());

		ImageTextButton itb5 = new ImageTextButton("text", itbStyle);
		itb5.setSize(width, height);
		itb5.add(itb1.getImage()).row();
		itb5.add(itb1.getLabel());

		horizontalGroup.addActor(itb1);
		horizontalGroup.addActor(itb2);
		horizontalGroup.addActor(itb3);
		horizontalGroup.addActor(itb4);
		horizontalGroup.addActor(itb5);

		stage.addActor(horizontalGroup);
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
		stage.dispose();
	}

}
