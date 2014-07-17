package com.aau.auris.game.screens;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.Asset.AssetLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LevelScreen extends AbstractScreen
{
	//Asset
	private Texture background;
	private TextureAtlas levelButtons;
	private Sound clickSound;
	private Sound hoverSound;

	public LevelScreen(AURISGame game)
	{
		super(game);
	}

	@Override
	public void loadAsset()
	{
		super.loadAsset();
		background = AssetLoader.menu_background_blank2;
		levelButtons = AssetLoader.levelButtons;
		clickSound = AssetLoader.clickSound;
		hoverSound = AssetLoader.hoverSound1;
	}

	@Override
	public void disposeAsset()
	{
		super.disposeAsset();
	}

	@Override
	protected void initComponents()
	{
		skin = new Skin(levelButtons);
		skin.add("default", bFont);

		TextureRegion backTextRegion = new TextureRegion(background, 848, 480);
		Image img = new Image(backTextRegion);
		stage.addActor(img);

		ImageTextButtonStyle itbStyle = new ImageTextButtonStyle();
		itbStyle.font = bFont;
		itbStyle.fontColor = Color.WHITE;
		itbStyle.imageUp = skin.getDrawable("secret");
		ImageTextButtonStyle itbStyleAch1 = new ImageTextButtonStyle();
		itbStyleAch1.font = bFont;
		itbStyleAch1.fontColor = Color.WHITE;
		itbStyleAch1.imageUp = skin.getDrawable("achLevels");

		final int s_width = Gdx.graphics.getWidth(), s_height = Gdx.graphics.getHeight();//screen dimension
		final float width = s_width / 6.5f, height = s_height / 6.5f;//box dimension, dimension of achievement-labels and level-labels

		final float factor = 1.5f;
		final float x = width * 1.3f;

		final float achiev_w = width+10;
		final float achiev_h = height;

		// Achievements
		ImageTextButton itbAchiev1 = new ImageTextButton("", itbStyleAch1);
		itbAchiev1.setBounds(x+50, (s_height - (height))-55, achiev_w, achiev_h);
		itbAchiev1.add(itbAchiev1.getImage()).row();
		itbAchiev1.add(itbAchiev1.getLabel());

		ImageTextButton itbAchiev2 = new ImageTextButton("", itbStyle);
		itbAchiev2.setBounds(itbAchiev1.getX() + (itbAchiev1.getWidth() * factor), itbAchiev1.getY(), itbAchiev1.getWidth(), itbAchiev1.getHeight());
		itbAchiev2.add(itbAchiev2.getImage()).row();
		itbAchiev2.add(itbAchiev2.getLabel());

		ImageTextButton itbAchiev3 = new ImageTextButton("", itbStyle);
		itbAchiev3.setBounds(itbAchiev2.getX() + (itbAchiev2.getWidth() * factor)-15, itbAchiev2.getY(), itbAchiev1.getWidth(), itbAchiev1.getHeight());
		itbAchiev3.add(itbAchiev3.getImage()).row();
		itbAchiev3.add(itbAchiev3.getLabel());

		// Level Difficulty 1
		TextButtonStyle diff1Lvl1Style = new TextButtonStyle();
		diff1Lvl1Style.up = skin.getDrawable("1diff1");
		diff1Lvl1Style.down = skin.getDrawable("1diff1Small");
		diff1Lvl1Style.over = skin.getDrawable("1diff1Over");
		diff1Lvl1Style.font = skin.getFont("default");
		TextButton diffLvl1Button=new TextButton("", diff1Lvl1Style);
		diffLvl1Button.setBounds(x+60, (s_height - (height) - (height * factor)-35), width, height);
		
		TextButtonStyle diff1Lvl2Style = new TextButtonStyle();
		diff1Lvl2Style.up = skin.getDrawable("1diff2");
		diff1Lvl2Style.down = skin.getDrawable("1diff2Small");
		diff1Lvl2Style.over = skin.getDrawable("1diff2Over");
		diff1Lvl2Style.font = skin.getFont("default");
		TextButton diff1Lvl2Button=new TextButton("", diff1Lvl2Style);
		diff1Lvl2Button.setBounds(diffLvl1Button.getX() + (width * factor), diffLvl1Button.getY(), width, height);

		TextButtonStyle diff1Lvl3Style = new TextButtonStyle();
		diff1Lvl3Style.up = skin.getDrawable("1diff3");
		diff1Lvl3Style.down = skin.getDrawable("1diff3Small");
		diff1Lvl3Style.over = skin.getDrawable("1diff3Over");
		diff1Lvl3Style.font = skin.getFont("default");
		TextButton diff1Lvl3Button=new TextButton("", diff1Lvl3Style);
		diff1Lvl3Button.setBounds(diff1Lvl2Button.getX() + (width * factor), diffLvl1Button.getY(), width, height);

		// Level Difficulty 2
		TextButtonStyle diff2Lvl1Style = new TextButtonStyle();
		diff2Lvl1Style.up = skin.getDrawable("2diff4");
		diff2Lvl1Style.down = skin.getDrawable("2diff4Small");
		diff2Lvl1Style.over = skin.getDrawable("2diff4Over");
		diff2Lvl1Style.font = skin.getFont("default");
		TextButton diff2Lvl1Button = new TextButton("", diff2Lvl1Style);
		diff2Lvl1Button.setBounds(diffLvl1Button.getX(), diffLvl1Button.getY() - (height * factor), width, height);

		TextButtonStyle diff2Lvl2Style = new TextButtonStyle();
		diff2Lvl2Style.up = skin.getDrawable("2diff5");
		diff2Lvl2Style.down = skin.getDrawable("2diff5Small");
		diff2Lvl2Style.over = skin.getDrawable("2diff5Over");
		diff2Lvl2Style.font = skin.getFont("default");
		TextButton diff2Lvl2Button = new TextButton("", diff2Lvl2Style);
		diff2Lvl2Button.setBounds(diff1Lvl2Button.getX(), diff2Lvl1Button.getY(), width, height);

		TextButtonStyle diff2Lvl3Style = new TextButtonStyle();
		diff2Lvl3Style.up = skin.getDrawable("2diff6");
		diff2Lvl3Style.down = skin.getDrawable("2diff6Small");
		diff2Lvl3Style.over = skin.getDrawable("2diff6Over");
		diff2Lvl3Style.font = skin.getFont("default");
		TextButton diff2Lvl3Button = new TextButton("", diff2Lvl3Style);
		diff2Lvl3Button.setBounds(diff1Lvl3Button.getX(), diff2Lvl1Button.getY(), width, height);

		// Level Difficulty 3
		TextButtonStyle diff3Lvl1Style = new TextButtonStyle();
		diff3Lvl1Style.up = skin.getDrawable("3diff7");
		diff3Lvl1Style.down = skin.getDrawable("3diff7Small");
		diff3Lvl1Style.over = skin.getDrawable("3diff7Over");
		diff3Lvl1Style.font = skin.getFont("default");
		TextButton diff3Lvl1Button = new TextButton("", diff3Lvl1Style);
		diff3Lvl1Button.setBounds(diffLvl1Button.getX(), diff2Lvl2Button.getY() - (height * factor), width, height);

		TextButtonStyle diff3Lvl2Style = new TextButtonStyle();
		diff3Lvl2Style.up = skin.getDrawable("3diff8");
		diff3Lvl2Style.down = skin.getDrawable("3diff8Small");
		diff3Lvl2Style.over = skin.getDrawable("3diff8Over");
		diff3Lvl2Style.font = skin.getFont("default");
		TextButton diff3Lvl2Button = new TextButton("", diff3Lvl2Style);
		diff3Lvl2Button.setBounds(diff1Lvl2Button.getX(), diff3Lvl1Button.getY(), width, height);

		TextButtonStyle diff3Lvl3Style = new TextButtonStyle();
		diff3Lvl3Style.up = skin.getDrawable("3diff9");
		diff3Lvl3Style.down = skin.getDrawable("3diff9Small");
		diff3Lvl3Style.over = skin.getDrawable("3diff9Over");
		diff3Lvl3Style.font = skin.getFont("default");
		TextButton diff3Lvl3Button = new TextButton("", diff3Lvl3Style);
		diff3Lvl3Button.setBounds(diff1Lvl3Button.getX(), diff3Lvl1Button.getY(), width, height);

		// Button "BACK"
		TextButtonStyle tbStyleBack = new TextButtonStyle();
		tbStyleBack.up = skin.getDrawable("btnBack");
		tbStyleBack.down = skin.getDrawable("btnBackSmall");
		tbStyleBack.over = skin.getDrawable("btnBackOver");
		tbStyleBack.font = bFont;
		skin.add("btnBack", tbStyleBack);
		TextButton tbBack = new TextButton("", tbStyleBack);
		tbBack.setPosition(10, 10);
		tbBack.setSize(160, 60);
		tbBack.addListener(new ClickListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				super.touchUp(event, x, y, pointer, button);
				clickSound.play();
				game.changeScreen(AURISGame.MENU_SCREEN, LevelScreen.this);
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

		stage.addActor(itbAchiev1);
		stage.addActor(itbAchiev2);
		stage.addActor(itbAchiev3);

		stage.addActor(diffLvl1Button);
		stage.addActor(diff1Lvl2Button);
		stage.addActor(diff1Lvl3Button);

		stage.addActor(diff2Lvl1Button);
		stage.addActor(diff2Lvl2Button);
		stage.addActor(diff2Lvl3Button);

		stage.addActor(diff3Lvl1Button);
		stage.addActor(diff3Lvl2Button);
		stage.addActor(diff3Lvl3Button);

		stage.addActor(tbBack);
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
