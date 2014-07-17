package com.aau.auris.game.screens;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.Asset.AssetLoader;
import com.aau.auris.game.items.BallSkin;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ShopScreen extends AbstractScreen
{
	// Asset
	private TextureAtlas levelButtons;
	private Sound clickSound;
	private Sound hoverSound;

	// ShopItemButtons
	private ImageTextButtonStyle shopItem1Style, shopItem2Style, shopItem3Style;

	public ShopScreen(AURISGame game)
	{
		super(game);
	}

	@Override
	public void loadAsset()
	{
		super.loadAsset();
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

		final int s_width = Gdx.graphics.getWidth(), s_height = Gdx.graphics.getHeight();//screen dimension
		final float width = s_width / 6.5f, height = s_height / 6.5f;//box dimension, dimension of achievement-labels and level-labels

		final float factor = 1.5f;
		final float x = width * 1.3f;

		final float achiev_w = width + 10;
		final float achiev_h = height;

		// ShopItems
		shopItem1Style = new ImageTextButtonStyle();
		shopItem1Style.font = bFont;
		shopItem1Style.fontColor = Color.WHITE;
		shopItem1Style.imageUp = skin.getDrawable("secret");
		ImageTextButton shopItem1 = new ImageTextButton("", shopItem1Style);
		shopItem1.setBounds(x + 50, (s_height - (height)) - 55, achiev_w, achiev_h);
		shopItem1.add(shopItem1.getImage()).row();
		shopItem1.add(shopItem1.getLabel());
		shopItem1.addListener(new ClickListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				super.touchUp(event, x, y, pointer, button);
				clickSound.play();
				System.out.println("Shop-->purchase: " + event.getButton());
			}
		});
		shopItem1.addListener(new InputListener()
		{

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				super.enter(event, x, y, pointer, fromActor);
				hoverSound.play();
			}
		});

		shopItem2Style = new ImageTextButtonStyle();
		shopItem2Style.font = bFont;
		shopItem2Style.fontColor = Color.WHITE;
		shopItem2Style.imageUp = skin.getDrawable("secret");
		ImageTextButton shopItem2 = new ImageTextButton("", shopItem2Style);
		shopItem2.setBounds(shopItem1.getX() + (shopItem1.getWidth() * factor), shopItem1.getY(), shopItem1.getWidth(), shopItem1.getHeight());
		shopItem2.add(shopItem2.getImage()).row();
		shopItem2.add(shopItem2.getLabel());
		shopItem1.addListener(new ClickListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				super.touchUp(event, x, y, pointer, button);
				clickSound.play();
				System.out.println("Shop-->purchase: " + event.getButton());
			}
		});
		shopItem2.addListener(new InputListener()
		{

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				super.enter(event, x, y, pointer, fromActor);
				hoverSound.play();
			}
		});

		shopItem3Style = new ImageTextButtonStyle();
		shopItem3Style.font = bFont;
		shopItem3Style.fontColor = Color.WHITE;
		shopItem3Style.imageUp = skin.getDrawable("secret");
		ImageTextButton shopItem3 = new ImageTextButton("", shopItem3Style);
		shopItem3.setBounds(shopItem2.getX() + (shopItem2.getWidth() * factor) - 15, shopItem2.getY(), shopItem1.getWidth(), shopItem1.getHeight());
		shopItem3.add(shopItem3.getImage()).row();
		shopItem3.add(shopItem3.getLabel());
		shopItem3.addListener(new ClickListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				super.touchUp(event, x, y, pointer, button);
				clickSound.play();
				System.out.println("Shop-->purchase: " + event.getButton());
			}
		});
		shopItem1.addListener(new InputListener()
		{

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				super.enter(event, x, y, pointer, fromActor);
				hoverSound.play();
			}
		});

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
				game.changeScreen(AURISGame.LEVEL_SCREEN, ShopScreen.this);
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

		stage.addActor(shopItem1);
		stage.addActor(shopItem2);
		stage.addActor(shopItem3);

		stage.addActor(tbBack);
	}

	private void updateShopItemButtons()
	{
		//		shopItem1Style.imageUp = skin.getDrawable("");
		//		shopItem2Style.imageUp = skin.getDrawable("");
		//		shopItem3Style.imageUp = skin.getDrawable("");
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
		updateShopItemButtons();
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
