package com.aau.auris.game.screens;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.Asset;
import com.aau.auris.game.AssetLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LevelScreen implements Screen, Asset
{
	//Asset
	private BitmapFont bFont;

	private AURISGame game;

	private Stage stage;

	public LevelScreen(AURISGame game)
	{
		this.game = game;
		stage = new Stage();
		loadAsset();
	}

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
		Gdx.input.setInputProcessor(stage);

		Skin skin = new Skin();
		Pixmap pixmap = new Pixmap(50, 50, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.GREEN);
		pixmap.fill();
		skin.add("skin", new Texture(pixmap));

		skin.add("default", bFont);

		ImageTextButtonStyle itbStyle = new ImageTextButtonStyle();
		itbStyle.font = bFont;
		itbStyle.fontColor = Color.WHITE;
		itbStyle.imageUp = skin.newDrawable("skin", Color.GREEN);

		final int s_width = Gdx.graphics.getWidth(), s_height = Gdx.graphics.getHeight();//screen dimension
		final float width = s_width / 6.5f, height = s_height / 6.5f;//box dimension, dimension of achievement-labels and level-labels

		final float factor = 1.5f;
		final float x = width * 1.3f;

		final float achiev_w = width;
		final float achiev_h = height;

		// Achievements
		ImageTextButton itbAchiev1 = new ImageTextButton("Achievement 1", itbStyle);
		itbAchiev1.setBounds(x, s_height - (height), achiev_w, achiev_h);
		itbAchiev1.add(itbAchiev1.getImage()).row();
		itbAchiev1.add(itbAchiev1.getLabel());

		ImageTextButton itbAchiev2 = new ImageTextButton("Achievement 2", itbStyle);
		itbAchiev2.setBounds(itbAchiev1.getX() + (itbAchiev1.getWidth() * factor), itbAchiev1.getY(), itbAchiev1.getWidth(), itbAchiev1.getHeight());
		itbAchiev2.add(itbAchiev2.getImage()).row();
		itbAchiev2.add(itbAchiev2.getLabel());

		ImageTextButton itbAchiev3 = new ImageTextButton("Achievement 3", itbStyle);
		itbAchiev3.setBounds(itbAchiev2.getX() + (itbAchiev2.getWidth() * factor), itbAchiev2.getY(), itbAchiev1.getWidth(), itbAchiev1.getHeight());
		itbAchiev3.add(itbAchiev3.getImage()).row();
		itbAchiev3.add(itbAchiev3.getLabel());

		// Level Difficulty 1
		LabelStyle diff1Lvl1Style = new LabelStyle();
		diff1Lvl1Style.font = bFont;
		diff1Lvl1Style.background = skin.newDrawable("skin", Color.ORANGE);
		Label lblDiff1Lvl1 = new Label("", diff1Lvl1Style);
		lblDiff1Lvl1.setBounds(x, itbAchiev1.getY() - (height * factor), width, height);

		LabelStyle diff1Lvl2Style = new LabelStyle();
		diff1Lvl2Style.font = bFont;
		diff1Lvl2Style.background = skin.newDrawable("skin", Color.ORANGE);
		Label lblDiff1Lvl2 = new Label("", diff1Lvl2Style);
		lblDiff1Lvl2.setBounds(x + (width * factor), lblDiff1Lvl1.getY(), width, height);

		LabelStyle diff1Lvl3Style = new LabelStyle();
		diff1Lvl3Style.font = bFont;
		diff1Lvl3Style.background = skin.newDrawable("skin", Color.ORANGE);
		Label lblDiff1Lvl3 = new Label("", diff1Lvl3Style);
		lblDiff1Lvl3.setBounds(x + (width * (factor * 2f)), lblDiff1Lvl1.getY(), width, height);

		// Level Difficulty 2
		LabelStyle diff2Lvl1Style = new LabelStyle();
		diff2Lvl1Style.font = bFont;
		diff2Lvl1Style.background = skin.newDrawable("skin", Color.ORANGE);
		Label lblDiff2Lvl1 = new Label("", diff2Lvl1Style);
		lblDiff2Lvl1.setBounds(lblDiff1Lvl1.getX(), lblDiff1Lvl1.getY() - (height * factor), width, height);

		LabelStyle diff2Lvl2Style = new LabelStyle();
		diff2Lvl2Style.font = bFont;
		diff2Lvl2Style.background = skin.newDrawable("skin", Color.ORANGE);
		Label lblDiff2Lvl2 = new Label("", diff2Lvl2Style);
		lblDiff2Lvl2.setBounds(lblDiff1Lvl2.getX(), lblDiff2Lvl1.getY(), width, height);

		LabelStyle diff2Lvl3Style = new LabelStyle();
		diff2Lvl3Style.font = bFont;
		diff2Lvl3Style.background = skin.newDrawable("skin", Color.ORANGE);
		Label lblDiff2Lvl3 = new Label("", diff2Lvl3Style);
		lblDiff2Lvl3.setBounds(lblDiff1Lvl3.getX(), lblDiff2Lvl1.getY(), width, height);

		// Level Difficulty 3
		LabelStyle diff3Lvl1Style = new LabelStyle();
		diff3Lvl1Style.font = bFont;
		diff3Lvl1Style.background = skin.newDrawable("skin", Color.ORANGE);
		Label lblDiff3Lvl1 = new Label("", diff3Lvl1Style);
		lblDiff3Lvl1.setBounds(lblDiff1Lvl1.getX(), lblDiff2Lvl1.getY() - (height * factor), width, height);

		LabelStyle diff3Lvl2Style = new LabelStyle();
		diff3Lvl2Style.font = bFont;
		diff3Lvl2Style.background = skin.newDrawable("skin", Color.ORANGE);
		Label lblDiff3Lvl2 = new Label("", diff3Lvl2Style);
		lblDiff3Lvl2.setBounds(lblDiff1Lvl2.getX(), lblDiff3Lvl1.getY(), width, height);

		LabelStyle diff3Lvl3Style = new LabelStyle();
		diff3Lvl3Style.font = bFont;
		diff3Lvl3Style.background = skin.newDrawable("skin", Color.ORANGE);
		Label lblDiff3Lvl3 = new Label("", diff3Lvl3Style);
		lblDiff3Lvl3.setBounds(lblDiff1Lvl3.getX(), lblDiff3Lvl1.getY(), width, height);

		// Button "BACK"
		TextButtonStyle tbStyleBack = new TextButtonStyle();
		//		tbStyle.up = skin.getDrawable("btnBack");
		//		tbStyle.down = skin.getDrawable("btnBack");
		//		tbStyle.checked = skin.newDrawable("white", Color.LIGHT_GRAY);
		//		tbStyle.over = skin.getDrawable("btnBackOver");
		tbStyleBack.font = bFont;
		skin.add("btnBack", tbStyleBack);

		TextButton tbBack = new TextButton("BACK", tbStyleBack);
		tbBack.setBounds(10, 10, 100, 60);

		// EventListener
		tbBack.addListener(new ClickListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				//TODO: do not work
				super.touchUp(event, x, y, pointer, button);
				game.changeScreen(AURISGame.MENU_SCREEN, LevelScreen.this);
			}
		});
		tbBack.addListener(new InputListener()
		{
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				super.enter(event, x, y, pointer, fromActor);
			}
		});

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

		stage.addActor(tbBack);
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
