package com.aau.auris.game;

import com.aau.auris.game.userdata.Player;
import com.aau.auris.game.userdata.UserData;
import com.badlogic.gdx.Game;

public class AURIS_Game extends Game
{
	private UserData userdata = null;
	private Player player = null;

	@Override
	public void create()
	{
		userdata = new UserData();
		userdata.load();

		this.setScreen(new LoginScreen(this));
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

}
