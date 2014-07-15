package com.aau.auris.game.screens;

import com.aau.auris.game.AURIS_Game;
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
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class LevelScreen implements Screen
{
	private AURIS_Game game;

	private Stage stage;
	private HorizontalGroup hGroupAchievements;
	private HorizontalGroup hGroupLevelDif1, hGroupLevelDif2, hGroupLevelDif3;

	public LevelScreen(AURIS_Game game)
	{
		this.game = game;
	}

	@Override
	public void render(float delta)
	{
		//TODO: implement parachute balls
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
		stage = new Stage();

		final int width = 50, height = 50;

		hGroupAchievements = new HorizontalGroup();
		hGroupAchievements.setBounds(0, game.getHeight() - height - 30, game.getWidth(), height);
		hGroupAchievements.space(50);
		hGroupAchievements.pad(10);
		hGroupAchievements.center();

		final Skin skin = new Skin();
		Pixmap pixmap = new Pixmap(50, 50, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.GREEN);
		pixmap.fill();
		skin.add("skin", new Texture(pixmap));

		final BitmapFont font = new BitmapFont();

		ImageTextButtonStyle itbStyle = new ImageTextButtonStyle();
		itbStyle.font = font;
		itbStyle.fontColor = Color.WHITE;
		itbStyle.imageUp = skin.newDrawable("skin", Color.GREEN);

		ImageTextButton itb1 = new ImageTextButton("text", itbStyle);
		itb1.setBounds(0, 0, width, height);
		itb1.setSize(width, height);
		itb1.add(itb1.getImage()).row();
		itb1.add(itb1.getLabel());
		hGroupAchievements.addActor(itb1);

		//		ImageTextButton itb2 = new ImageTextButton("text", itbStyle);
		//		itb1.setBounds(0, 0, width, height);
		//		itb2.setSize(width, height);
		//		itb2.add(itb1.getImage()).row();
		//		itb2.add(itb1.getLabel());
		//		hGroupAchievements.addActor(itb2);

		//		ImageTextButton itb3 = new ImageTextButton("text", itbStyle);
		//		itb1.setBounds(0, 0, width, height);
		//		itb3.setSize(width, height);
		//		itb3.add(itb1.getImage()).row();
		//		itb3.add(itb1.getLabel());
		//		hGroupAchievements.addActor(itb3);

		//		ImageTextButton itb4 = new ImageTextButton("text", itbStyle);
		//		itb4.setBounds(0, 0, width, height);
		//		itb4.add(itb1.getImage()).row();
		//		itb4.add(itb1.getLabel());
		//		hGroupAchievements.addActor(itb4);
		//
		//		ImageTextButton itb5 = new ImageTextButton("text", itbStyle);
		//		itb5.setBounds(0, 0, width, height);
		//		itb5.add(itb1.getImage()).row();
		//		itb5.add(itb1.getLabel());
		//		hGroupAchievements.addActor(itb5);

		stage.addActor(hGroupAchievements);
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
