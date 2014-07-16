package com.aau.auris.game;

import java.awt.Dimension;

import com.aau.auris.game.items.HighScore;
import com.aau.auris.game.screens.LevelScreen;
import com.aau.auris.game.screens.LoginScreen;
import com.aau.auris.game.screens.MenuScreen;
import com.aau.auris.game.userdata.Player;
import com.aau.auris.game.userdata.UserData;
import com.badlogic.gdx.Game;
<<<<<<< HEAD
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class AURISGame extends Game
{
	public static final Dimension PROJECTOR_SIZE = new Dimension(848, 480);
=======
import com.badlogic.gdx.Screen;

public class AURISGame extends Game {
	public static final Dimension SIZE = new Dimension(848, 480);
>>>>>>> 51fe514afe5ffa3b24d5a5a784cb76dd77d092a3

	public static final int MENU_SCREEN = 0;
	public static final int LOGIN_SCREEN = 1;
	public static final int LEVEL_SCREEN = 2;

	private UserData userdata;

	private MenuScreen menuScreen;
	private LoginScreen loginScreen;
	private LevelScreen levelScreen;

	private Player player = null;

	@Override
<<<<<<< HEAD
	public void create()
	{
		AssetLoader.load();
=======
	public void create() {
>>>>>>> 51fe514afe5ffa3b24d5a5a784cb76dd77d092a3
		userdata = new UserData();
		userdata.load();

		menuScreen = new MenuScreen(this);
		loginScreen = new LoginScreen(this);
		levelScreen = new LevelScreen(this);

		this.setScreen(levelScreen);
	}

	@Override
	public void render() {
		super.render();
	}

	public UserData getUserData() {
		return userdata;
	}

	public void setPlayer(Player player) {
		this.player = player;
		//TODO: debugging
		System.out.println("player logged in: " + player);
	}

<<<<<<< HEAD
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
		}

		if (newScreen != null)
		{
			this.setScreen(newScreen);
		} else
		{
			//TODO: debugging
			System.out.println("...could not switch screens, no valid screenIndex!...");
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

=======
	public int getWidth() {
		return SIZE.width;
	}

	public int getHeight() {
		return SIZE.height;
	}

	public void changeScreen(String name, Screen screen) {
		screen.dispose();
		if (name.toLowerCase().equals("login")) {
			this.setScreen(loginScreen);
		}

	}
>>>>>>> 51fe514afe5ffa3b24d5a5a784cb76dd77d092a3
}
