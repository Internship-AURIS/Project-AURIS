package com.aau.auris.game;

import java.awt.Dimension;

import com.aau.auris.game.items.HighScore;
import com.aau.auris.game.screens.LevelScreen;
import com.aau.auris.game.screens.LoginScreen;
import com.aau.auris.game.screens.MainMenu;
import com.aau.auris.game.userdata.Player;
import com.aau.auris.game.userdata.UserData;
import com.badlogic.gdx.Game;

public class AURISGame extends Game
{
	public static final Dimension SIZE = new Dimension(880, 480);

	private UserData userdata;

	private HighScore highscore;

	private MainMenu menuScreen;
	private LoginScreen loginScreen;
	private LevelScreen levelScreen;

	private Player player = null;

	@Override
	public void create()
	{
		userdata = new UserData();

		highscore = new HighScore(this);

		menuScreen = new MainMenu(this);
		loginScreen = new LoginScreen(this);
		levelScreen = new LevelScreen(this);

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
		System.out.println("player logged in: " + player);
	}

	public int getWidth()
	{
		return SIZE.width;
	}

	public int getHeight()
	{
		return SIZE.height;
	}
}
