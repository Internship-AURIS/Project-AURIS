package com.aau.auris.game.screens;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.Asset.AssetLoader;
import com.aau.auris.game.data.Player;
import com.aau.auris.game.level.Level;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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
	private Sound hoverWhistle1;
	private Sound hoverWhistle2;
	private Sound hoverWhistle3;
	private Sound coinSound;

	//Local

	// Level- and Achievement-Display
	private ImageTextButtonStyle itbAchiev1Style;
	private ImageTextButtonStyle itbAchiev2Style;
	private ImageTextButtonStyle itbAchiev3Style;
	private TextButtonStyle tbDiff1Lvl1Style;
	private TextButtonStyle tbDiff1Lvl2Style;
	private TextButtonStyle tbDiff1Lvl3Style;
	private TextButtonStyle tbDiff2Lvl1Style;
	private TextButtonStyle tbDiff2Lvl2Style;
	private TextButtonStyle tbDiff2Lvl3Style;
	private TextButtonStyle tbDiff3Lvl1Style;
	private TextButtonStyle tbDiff3Lvl2Style;
	private TextButtonStyle tbDiff3Lvl3Style;

	// Other
	private Player player;

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
		hoverWhistle1 = AssetLoader.hoverWhistle1;
		hoverWhistle2 = AssetLoader.hoverWhistle2;
		hoverWhistle3 = AssetLoader.hoverWhistle3;
		coinSound= AssetLoader.coinSound;
	}

	@Override
	public void disposeAsset()
	{
		super.disposeAsset();
	}

	@Override
	protected void initComponents()
	{
		player = game.getPlayer();

		skin = new Skin(levelButtons);
		skin.add("default", bFont);

		TextureRegion backTextRegion = new TextureRegion(background, 848, 480);
		Image img = new Image(backTextRegion);
		stage.addActor(img);

		ImageTextButtonStyle itbStyle = new ImageTextButtonStyle();
		itbStyle.font = bFont;
		itbStyle.fontColor = Color.WHITE;
		itbStyle.imageUp = skin.getDrawable("secret");

		final int s_width = Gdx.graphics.getWidth(), s_height = Gdx.graphics.getHeight();//screen dimension
		final float width = s_width / 6.5f, height = s_height / 6.5f;//box dimension, dimension of achievement-labels and level-labels

		final float factor = 1.5f;
		final float x = width * 1.3f;

		final float achiev_w = width + 10;
		final float achiev_h = height;

		// Achievements
		itbAchiev1Style = new ImageTextButtonStyle();
		itbAchiev1Style.font = bFont;
		itbAchiev1Style.fontColor = Color.WHITE;
		itbAchiev1Style.imageUp = skin.getDrawable("achLevels");
		ImageTextButton itbAchiev1 = new ImageTextButton("", itbAchiev1Style);
		itbAchiev1.setBounds(x + 50, (s_height - (height)) - 55, achiev_w, achiev_h);
		itbAchiev1.add(itbAchiev1.getImage()).row();
		itbAchiev1.add(itbAchiev1.getLabel());

		itbAchiev2Style = new ImageTextButtonStyle();
		itbAchiev2Style.font = bFont;
		itbAchiev2Style.fontColor = Color.WHITE;
		itbAchiev2Style.imageUp = skin.getDrawable("achLevels");
		ImageTextButton itbAchiev2 = new ImageTextButton("", itbAchiev2Style);
		itbAchiev2.setBounds(itbAchiev1.getX() + (itbAchiev1.getWidth() * factor), itbAchiev1.getY(), itbAchiev1.getWidth(), itbAchiev1.getHeight());
		itbAchiev2.add(itbAchiev2.getImage()).row();
		itbAchiev2.add(itbAchiev2.getLabel());

		itbAchiev3Style = new ImageTextButtonStyle();
		itbAchiev3Style.font = bFont;
		itbAchiev3Style.fontColor = Color.WHITE;
		itbAchiev3Style.imageUp = skin.getDrawable("achLevels");
		ImageTextButton itbAchiev3 = new ImageTextButton("", itbAchiev3Style);
		itbAchiev3.setBounds(itbAchiev2.getX() + (itbAchiev2.getWidth() * factor) - 15, itbAchiev2.getY(), itbAchiev1.getWidth(), itbAchiev1.getHeight());
		itbAchiev3.add(itbAchiev3.getImage()).row();
		itbAchiev3.add(itbAchiev3.getLabel());

		// Level Difficulty 1
		tbDiff1Lvl1Style = new TextButtonStyle();
		tbDiff1Lvl1Style.up = skin.getDrawable("1diff1");
		tbDiff1Lvl1Style.down = skin.getDrawable("1diff1Small");
		tbDiff1Lvl1Style.over = skin.getDrawable("1diff1Over");
		tbDiff1Lvl1Style.font = skin.getFont("default");
		TextButton diffLvl1Button = new TextButton("", tbDiff1Lvl1Style);
		diffLvl1Button.setBounds(x + 60, (s_height - (height) - (height * factor) - 35), width, height);
		diffLvl1Button.addListener(new ClickListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				super.touchUp(event, x, y, pointer, button);
				clickSound.play();
				final int id = Level.LEVEL_ID_1;
				if (player.hasLevelUnlocked(id))
				{
					game.setLevel(AURISGame.getLevel(id));
					game.changeScreen(AURISGame.GAME_SCREEN, LevelScreen.this);
				}
			}
		});
		diffLvl1Button.addListener(new InputListener()
		{
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				super.enter(event, x, y, pointer, fromActor);
				hoverWhistle1.play();
			}
		});

		tbDiff1Lvl2Style = new TextButtonStyle();
		tbDiff1Lvl2Style.up = skin.getDrawable("1diff2");
		tbDiff1Lvl2Style.down = skin.getDrawable("1diff2Small");
		tbDiff1Lvl2Style.over = skin.getDrawable("1diff2Over");
		tbDiff1Lvl2Style.font = skin.getFont("default");
		TextButton diff1Lvl2Button = new TextButton("", tbDiff1Lvl2Style);
		diff1Lvl2Button.setBounds(diffLvl1Button.getX() + (width * factor), diffLvl1Button.getY(), width, height);
		diff1Lvl2Button.addListener(new ClickListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				super.touchUp(event, x, y, pointer, button);
				final int id = Level.LEVEL_ID_2;
				if (player.hasLevelUnlocked(id))
				{
					game.setLevel(AURISGame.getLevel(id));
					game.changeScreen(AURISGame.GAME_SCREEN, LevelScreen.this);
				}
			}
		});
		diff1Lvl2Button.addListener(new InputListener()
		{
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				super.enter(event, x, y, pointer, fromActor);
				hoverWhistle2.play();
			}
		});

		tbDiff1Lvl3Style = new TextButtonStyle();
		tbDiff1Lvl3Style.up = skin.getDrawable("1diff3");
		tbDiff1Lvl3Style.down = skin.getDrawable("1diff3Small");
		tbDiff1Lvl3Style.over = skin.getDrawable("1diff3Over");
		tbDiff1Lvl3Style.font = skin.getFont("default");
		TextButton diff1Lvl3Button = new TextButton("", tbDiff1Lvl3Style);
		diff1Lvl3Button.setBounds(diff1Lvl2Button.getX() + (width * factor), diffLvl1Button.getY(), width, height);
		diff1Lvl3Button.addListener(new ClickListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				super.touchUp(event, x, y, pointer, button);
				clickSound.play();
				final int id = Level.LEVEL_ID_3;
				if (player.hasLevelUnlocked(id))
				{
					game.setLevel(AURISGame.getLevel(id));
					game.changeScreen(AURISGame.GAME_SCREEN, LevelScreen.this);
				}
			}
		});
		diff1Lvl3Button.addListener(new InputListener()
		{
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				super.enter(event, x, y, pointer, fromActor);
				hoverWhistle3.play();
			}
		});

		// Level Difficulty 2
		tbDiff2Lvl1Style = new TextButtonStyle();
		tbDiff2Lvl1Style.up = skin.getDrawable("2diff4");
		tbDiff2Lvl1Style.down = skin.getDrawable("2diff4Small");
		tbDiff2Lvl1Style.over = skin.getDrawable("2diff4Over");
		tbDiff2Lvl1Style.font = skin.getFont("default");
		TextButton diff2Lvl1Button = new TextButton("", tbDiff2Lvl1Style);
		diff2Lvl1Button.setBounds(diffLvl1Button.getX(), diffLvl1Button.getY() - (height * factor), width, height);
		diff2Lvl1Button.addListener(new ClickListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				super.touchUp(event, x, y, pointer, button);
				clickSound.play();
				final int id = Level.LEVEL_ID_4;
				if (player.hasLevelUnlocked(id))
				{
					game.setLevel(AURISGame.getLevel(id));
					game.changeScreen(AURISGame.GAME_SCREEN, LevelScreen.this);
				}
			}
		});

		tbDiff2Lvl2Style = new TextButtonStyle();
		tbDiff2Lvl2Style.up = skin.getDrawable("2diff5");
		tbDiff2Lvl2Style.down = skin.getDrawable("2diff5Small");
		tbDiff2Lvl2Style.over = skin.getDrawable("2diff5Over");
		tbDiff2Lvl2Style.font = skin.getFont("default");
		TextButton diff2Lvl2Button = new TextButton("", tbDiff2Lvl2Style);
		diff2Lvl2Button.setBounds(diff1Lvl2Button.getX(), diff2Lvl1Button.getY(), width, height);
		diff2Lvl2Button.addListener(new ClickListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				super.touchUp(event, x, y, pointer, button);
				clickSound.play();
				final int id = Level.LEVEL_ID_5;
				if (player.hasLevelUnlocked(id))
				{
					game.setLevel(AURISGame.getLevel(id));
					game.changeScreen(AURISGame.GAME_SCREEN, LevelScreen.this);
				}
			}
		});

		tbDiff2Lvl3Style = new TextButtonStyle();
		tbDiff2Lvl3Style.up = skin.getDrawable("2diff6");
		tbDiff2Lvl3Style.down = skin.getDrawable("2diff6Small");
		tbDiff2Lvl3Style.over = skin.getDrawable("2diff6Over");
		tbDiff2Lvl3Style.font = skin.getFont("default");
		TextButton diff2Lvl3Button = new TextButton("", tbDiff2Lvl3Style);
		diff2Lvl3Button.setBounds(diff1Lvl3Button.getX(), diff2Lvl1Button.getY(), width, height);
		diff2Lvl3Button.addListener(new ClickListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				super.touchUp(event, x, y, pointer, button);
				clickSound.play();
				final int id = Level.LEVEL_ID_6;
				if (player.hasLevelUnlocked(id))
				{
					game.setLevel(AURISGame.getLevel(id));
					game.changeScreen(AURISGame.GAME_SCREEN, LevelScreen.this);
				}
			}
		});

		// Level Difficulty 3
		tbDiff3Lvl1Style = new TextButtonStyle();
		tbDiff3Lvl1Style.up = skin.getDrawable("3diff7");
		tbDiff3Lvl1Style.down = skin.getDrawable("3diff7Small");
		tbDiff3Lvl1Style.over = skin.getDrawable("3diff7Over");
		tbDiff3Lvl1Style.font = skin.getFont("default");
		TextButton diff3Lvl1Button = new TextButton("", tbDiff3Lvl1Style);
		diff3Lvl1Button.setBounds(diffLvl1Button.getX(), diff2Lvl2Button.getY() - (height * factor), width, height);
		diff3Lvl1Button.addListener(new ClickListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				super.touchUp(event, x, y, pointer, button);
				clickSound.play();
				final int id = Level.LEVEL_ID_7;
				if (player.hasLevelUnlocked(id))
				{
					game.setLevel(AURISGame.getLevel(id));
					game.changeScreen(AURISGame.GAME_SCREEN, LevelScreen.this);
				}
			}
		});

		tbDiff3Lvl2Style = new TextButtonStyle();
		tbDiff3Lvl2Style.up = skin.getDrawable("3diff8");
		tbDiff3Lvl2Style.down = skin.getDrawable("3diff8Small");
		tbDiff3Lvl2Style.over = skin.getDrawable("3diff8Over");
		tbDiff3Lvl2Style.font = skin.getFont("default");
		TextButton diff3Lvl2Button = new TextButton("", tbDiff3Lvl2Style);
		diff3Lvl2Button.setBounds(diff1Lvl2Button.getX(), diff3Lvl1Button.getY(), width, height);
		diff3Lvl2Button.addListener(new ClickListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				super.touchUp(event, x, y, pointer, button);
				clickSound.play();
				final int id = Level.LEVEL_ID_8;
				if (player.hasLevelUnlocked(id))
				{
					game.setLevel(AURISGame.getLevel(id));
					game.changeScreen(AURISGame.GAME_SCREEN, LevelScreen.this);
				}
			}
		});

		tbDiff3Lvl3Style = new TextButtonStyle();
		tbDiff3Lvl3Style.up = skin.getDrawable("3diff9");
		tbDiff3Lvl3Style.down = skin.getDrawable("3diff9Small");
		tbDiff3Lvl3Style.over = skin.getDrawable("3diff9Over");
		tbDiff3Lvl3Style.font = skin.getFont("default");
		TextButton diff3Lvl3Button = new TextButton("", tbDiff3Lvl3Style);
		diff3Lvl3Button.setBounds(diff1Lvl3Button.getX(), diff3Lvl1Button.getY(), width, height);
		diff3Lvl3Button.addListener(new ClickListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				super.touchUp(event, x, y, pointer, button);
				clickSound.play();
				final int id = Level.LEVEL_ID_9;
				if (player.hasLevelUnlocked(id))
				{
					game.setLevel(AURISGame.getLevel(id));
					game.changeScreen(AURISGame.GAME_SCREEN, LevelScreen.this);
				}
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

		// Button "SHOP"
		TextButtonStyle tbStyleShop = new TextButtonStyle();
		tbStyleShop.up = skin.getDrawable("btnShop");
		tbStyleShop.down = skin.getDrawable("btnShopSmall");
		tbStyleShop.over = skin.getDrawable("btnShopOver");
		tbStyleShop.font = bFont;
		skin.add("btnBack", tbStyleShop);
		TextButton tbShop = new TextButton("", tbStyleShop);
		tbShop.setPosition(tbBack.getX(), tbBack.getY() + tbBack.getWidth() / 2);
		tbShop.setSize(160, 60);
		tbShop.addListener(new ClickListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				super.touchUp(event, x, y, pointer, button);
				coinSound.play();
				game.changeScreen(AURISGame.SHOP_SCREEN, LevelScreen.this);
			}
		});
		tbShop.addListener(new InputListener()
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
		stage.addActor(tbShop);
	}

	@Override
	protected void handleInput()
	{
		if (Gdx.input.isKeyPressed(Keys.DEL))
		{
			game.changeScreen(AURISGame.MENU_SCREEN, LevelScreen.this);
		}
		if (Gdx.input.isKeyPressed(Keys.S))
		{
			game.changeScreen(AURISGame.SHOP_SCREEN, LevelScreen.this);
		}
		if (Gdx.input.isKeyPressed(Keys.NUM_1))
		{}
		if (Gdx.input.isKeyPressed(Keys.NUM_2))
		{}
		if (Gdx.input.isKeyPressed(Keys.NUM_3))
		{}
		if (Gdx.input.isKeyPressed(Keys.NUM_4))
		{}
		if (Gdx.input.isKeyPressed(Keys.NUM_5))
		{}
		if (Gdx.input.isKeyPressed(Keys.NUM_6))
		{}
		if (Gdx.input.isKeyPressed(Keys.NUM_7))
		{}
		if (Gdx.input.isKeyPressed(Keys.NUM_8))
		{}
		if (Gdx.input.isKeyPressed(Keys.NUM_9))
		{}
	}

	private void update()
	{
		player = game.getPlayer();
		// TODO: implement loading of unlocked things from player

		// Implementation of displaying locked/unlocked achievements
		//				itbAchiev1Style.imageUp = player.getAchievements().get(0).getDrawable();
		//				itbAchiev2Style.imageUp = player.getAchievements().get(1).getDrawable();
		//				itbAchiev3Style.imageUp = player.getAchievements().get(2).getDrawable();
		//		tbDiff1Lvl1Style.up = player.getLevels().get(0).getDrawable();
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
		update();
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
