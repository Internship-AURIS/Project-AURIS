package com.aau.auris.game;

import java.awt.Dimension;

import com.aau.auris.game.screens.LevelScreen;
import com.aau.auris.game.screens.LoginScreen;
import com.aau.auris.game.userdata.Player;
import com.aau.auris.game.userdata.UserData;
import com.badlogic.gdx.Game;

public class AURIS_Game extends Game
{
	public static final Dimension SIZE = new Dimension(880, 480);

	private UserData userdata = null;
	private Player player = null;

	private LoginScreen loginScreen;
	private LevelScreen levelScreen;

	@Override
	public void create()
	{
		userdata = new UserData();
		userdata.load();

		loginScreen = new LoginScreen(this);
		levelScreen = new LevelScreen(this);

		this.setScreen(levelScreen);
	}

	public void setPlayer(Player player)
	{
		this.player = player;
		System.out.println("player logged in: " + player);
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

	@Override
	public void dispose()
	{
		super.dispose();
		userdata.save();
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
