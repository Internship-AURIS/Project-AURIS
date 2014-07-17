package com.aau.auris.game;

import java.awt.Dimension;

import com.aau.auris.game.Asset.AssetLoader;
import com.aau.auris.game.data.Player;
import com.aau.auris.game.data.UserData;
import com.aau.auris.game.level.Level;
import com.aau.auris.game.screens.CreditsScreen;
import com.aau.auris.game.screens.GameScreen;
import com.aau.auris.game.screens.LevelScreen;
import com.aau.auris.game.screens.LoginScreen;
import com.aau.auris.game.screens.MenuScreen;
import com.aau.auris.game.screens.ShopScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class AURISGame extends Game
{
	public static final Dimension PROJECTOR_SIZE = new Dimension(848, 480);

	public static final int MENU_SCREEN = 0;
	public static final int LOGIN_SCREEN = 1;
	public static final int LEVEL_SCREEN = 2;
	public static final int CREDITS_SCREEN = 3;
	public static final int SHOP_SCREEN = 4;
	public static final int GAME_SCREEN = 5;

	private UserData userdata;

	private MenuScreen menuScreen;
	private LoginScreen loginScreen;
	private LevelScreen levelScreen;
	private GameScreen gameScreen;
	private ShopScreen shopScreen;
	private CreditsScreen creditsScreen;

	private Player player = null;
	private Level level = null;

	@Override
	public void create()
	{
		AssetLoader.load();
		userdata = new UserData();
		userdata.load();

		menuScreen = new MenuScreen(this);
		loginScreen = new LoginScreen(this);
		levelScreen = new LevelScreen(this);
		gameScreen = new GameScreen(this);
		shopScreen = new ShopScreen(this);
		creditsScreen = new CreditsScreen(this);

		//		player = userdata.getPlayers().get(0);// TODO: debugging for gameScreen
		this.setScreen(menuScreen);
	}

	@Override
	public void render()
	{
		super.render();
	}

	public UserData getUserData()
	{
		return userdata;
	}

	public void setPlayer(Player player)
	{
		this.player = player;
		System.out.println("AURISGame --> player logged in: " + player);//TODO: debugging
	}

	public Player getPlayer()
	{
		return player;
	}

	public void setLevel(Level level)
	{
		this.level = level;
		System.out.println("AURISGame --> level set to: " + level);// TODO: debugging
	}

	public Level getLevel()
	{
		return level;
	}

	public void changeScreen(int screenIndex, Screen screen)
	{
		screen.hide();
		Screen newScreen = null;
		if (screenIndex == MENU_SCREEN)
		{
			newScreen = menuScreen;
		} else if (screenIndex == LOGIN_SCREEN)
		{
			newScreen = loginScreen;
		} else if (screenIndex == LEVEL_SCREEN)
		{
			newScreen = levelScreen;
		} else if (screenIndex == CREDITS_SCREEN)
		{
			newScreen = creditsScreen;
		} else if (screenIndex == SHOP_SCREEN)
		{
			newScreen = shopScreen;
		} else if (screenIndex == GAME_SCREEN)
		{
			newScreen = gameScreen;
		}
		if (newScreen != null)
		{
			this.setScreen(newScreen);
		} else
		{
			System.out.println("...failed to switch screens!...");// TODO: debugging
		}
	}

	public int getWidth()
	{
		return Gdx.graphics.getWidth();
	}

	public int getHeight()
	{
		return Gdx.graphics.getHeight();
	}

	@Override
	public void dispose()
	{
		super.dispose();
		AssetLoader.dispose();
	}
}
