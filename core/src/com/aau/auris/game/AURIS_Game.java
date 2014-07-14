package com.aau.auris.game;

import com.badlogic.gdx.Game;

public class AURIS_Game extends Game
{

	private GamePreferences preferences;

	@Override
	public void create()
	{
		this.preferences = new GamePreferences();

		this.setScreen(new LoginScreen(this));
	}

	@Override
	public void render()
	{
		super.render();
	}

	public GamePreferences getGamePreferences()
	{
		return preferences;
	}
}
