package com.aau.auris.game.screens;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.Asset;
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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class LevelScreen implements Screen, Asset
{
	private AURISGame game;

	private Stage stage;

	public LevelScreen(AURISGame game)
	{
		this.game = game;
	}

	@Override
	public void loadAsset()
	{
		// TODO: load assets
	}

	@Override
	public void disposeAsset()
	{
		// TODO: delete assets
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

		Skin skin = new Skin();
		Pixmap pixmap = new Pixmap(50, 50, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.GREEN);
		pixmap.fill();
		skin.add("skin", new Texture(pixmap));

		final BitmapFont font = new BitmapFont();

		ImageTextButtonStyle itbStyle = new ImageTextButtonStyle();
		itbStyle.font = font;
		itbStyle.fontColor = Color.WHITE;
		itbStyle.imageUp = skin.newDrawable("skin", Color.GREEN);

		final int s_width = Gdx.graphics.getWidth(), s_height = Gdx.graphics.getHeight();//screen dimension
		final int width = s_width / 5, height = s_height / 5;

		final int x = width / 2;
		final float factor = 1.5f;

		// Achievements
		ImageTextButton itbAchiev1 = new ImageTextButton("Achievement 1", itbStyle);
		itbAchiev1.setBounds(width / 2, s_height - height, width, height);
		itbAchiev1.add(itbAchiev1.getImage()).row();
		itbAchiev1.add(itbAchiev1.getLabel());

		ImageTextButton itbAchiev2 = new ImageTextButton("Achievement 2", itbStyle);
		itbAchiev2.setBounds(itbAchiev1.getX() + itbAchiev1.getWidth(), s_height - height, width, height);
		itbAchiev2.add(itbAchiev2.getImage()).row();
		itbAchiev2.add(itbAchiev2.getLabel());

		ImageTextButton itbAchiev3 = new ImageTextButton("Achievement 3", itbStyle);
		itbAchiev3.setBounds(itbAchiev2.getX() + itbAchiev2.getWidth(), s_height - height, width, height);
		itbAchiev3.add(itbAchiev3.getImage()).row();
		itbAchiev3.add(itbAchiev3.getLabel());

		// Level Difficulty 1
		LabelStyle diff1Lvl1Style = new LabelStyle();
		diff1Lvl1Style.font = font;
		diff1Lvl1Style.background = skin.newDrawable("skin", Color.ORANGE);
		Label lblDiff1Lvl1 = new Label("", diff1Lvl1Style);
		lblDiff1Lvl1.setBounds(x, itbAchiev1.getY() - (int) (height * .5f), width, height);

		LabelStyle diff1Lvl2Style = new LabelStyle();
		diff1Lvl2Style.font = font;
		diff1Lvl2Style.background = skin.newDrawable("skin", Color.ORANGE);
		Label lblDiff1Lvl2 = new Label("", diff1Lvl2Style);
		lblDiff1Lvl2.setBounds(x + (int) (width * factor), lblDiff1Lvl1.getY(), width, height);

		LabelStyle diff1Lvl3Style = new LabelStyle();
		diff1Lvl3Style.font = font;
		diff1Lvl3Style.background = skin.newDrawable("skin", Color.ORANGE);
		Label lblDiff1Lvl3 = new Label("", diff1Lvl3Style);
		lblDiff1Lvl3.setBounds(x + (int) (width * (factor * 2f)), lblDiff1Lvl1.getY(), width, height);

		// Level Difficulty 2
		LabelStyle diff2Lvl1Style = new LabelStyle();
		diff2Lvl1Style.font = font;
		diff2Lvl1Style.background = skin.newDrawable("skin", Color.ORANGE);
		Label lblDiff2Lvl1 = new Label("", diff2Lvl1Style);
		lblDiff2Lvl1.setBounds(lblDiff1Lvl1.getX(), lblDiff1Lvl1.getY() - lblDiff1Lvl1.getHeight() - (int) (height * 2f), width, height);

		LabelStyle diff2Lvl2Style = new LabelStyle();
		diff2Lvl2Style.font = font;
		diff2Lvl2Style.background = skin.newDrawable("skin", Color.ORANGE);
		Label lblDiff2Lvl2 = new Label("", diff2Lvl2Style);
		lblDiff2Lvl2.setBounds(lblDiff1Lvl2.getX(), lblDiff2Lvl1.getY(), width, height);

		LabelStyle diff2Lvl3Style = new LabelStyle();
		diff2Lvl3Style.font = font;
		diff2Lvl3Style.background = skin.newDrawable("skin", Color.ORANGE);
		Label lblDiff2Lvl3 = new Label("", diff2Lvl3Style);
		lblDiff2Lvl3.setBounds(lblDiff1Lvl3.getX(), lblDiff2Lvl1.getY(), width, height);

		// Level Difficulty 3
		LabelStyle diff3Lvl1Style = new LabelStyle();
		diff3Lvl1Style.font = font;
		diff3Lvl1Style.background = skin.newDrawable("skin", Color.ORANGE);
		Label lblDiff3Lvl1 = new Label("", diff3Lvl1Style);
		lblDiff3Lvl1.setBounds(lblDiff1Lvl1.getX(), itbAchiev1.getY() - lblDiff1Lvl1.getHeight() - (int) (height * 3.5f), width, height);

		LabelStyle diff3Lvl2Style = new LabelStyle();
		diff3Lvl2Style.font = font;
		diff3Lvl2Style.background = skin.newDrawable("skin", Color.ORANGE);
		Label lblDiff3Lvl2 = new Label("", diff3Lvl2Style);
		lblDiff3Lvl2.setBounds(lblDiff1Lvl2.getX(), lblDiff3Lvl1.getY(), width, height);

		LabelStyle diff3Lvl3Style = new LabelStyle();
		diff3Lvl3Style.font = font;
		diff3Lvl3Style.background = skin.newDrawable("skin", Color.ORANGE);
		Label lblDiff3Lvl3 = new Label("", diff3Lvl3Style);
		lblDiff3Lvl3.setBounds(lblDiff1Lvl3.getX(), lblDiff3Lvl1.getY(), width, height);

		stage.addActor(itbAchiev1);
		stage.addActor(itbAchiev2);
		stage.addActor(itbAchiev3);

		stage.addActor(lblDiff1Lvl1);
		stage.addActor(lblDiff1Lvl2);
		stage.addActor(lblDiff1Lvl3);

		stage.addActor(lblDiff2Lvl1);
		stage.addActor(lblDiff2Lvl2);
		stage.addActor(lblDiff2Lvl3);

		stage.addActor(lblDiff3Lvl1);
		stage.addActor(lblDiff3Lvl2);
		stage.addActor(lblDiff3Lvl3);
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
