package com.aau.auris.game.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class Preferences
{
	private static final transient String FILENAME = new String("preferences.cfg");
	private transient FileHandle file = Gdx.files.local(FILENAME);

	private int maxPlayers;
	private boolean soundEnabled;
	private boolean debugging;
	private float ballRadius;
	private boolean hackEnabled;

	/*
	 * future preferences like:
	 * -sound enabled, volume
	 * -debug modus
	 * -ball radius
	 * -parent-control xD
	 */

	public Preferences()
	{}

	public void save()
	{
		maxPlayers = 10;
		soundEnabled = true;
		debugging = false;
		ballRadius = 15f;
		hackEnabled = false;
		Json json = new Json();
		final String text = json.toJson(this);
		file.writeString(text, false);
	}

	public void load()
	{
		if (file.exists())
		{
			final String text = file.readString();
			Json json = new Json();
			Preferences data = json.fromJson(Preferences.class, text);
			if (data != null)
			{
				maxPlayers = data.getMaxPlayers();
				soundEnabled = data.isSoundEnabled();
				debugging = data.isDebugging();
				ballRadius = data.getBallRadius();
			}
		}
	}

	public int getMaxPlayers()
	{
		return maxPlayers;
	}

	public boolean isSoundEnabled()
	{
		return soundEnabled;
	}

	public boolean isDebugging()
	{
		return debugging;
	}

	public float getBallRadius()
	{
		return ballRadius;
	}

	public boolean isHackEnabled()
	{
		return hackEnabled;
	}

}
