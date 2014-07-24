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
	private int maxBlobHeight;//in %
	private int maxBlobWidth;// in %
	private int lowerThreshold;
	private int upperThreshold;

	/*
	 * TODO: currently not used!!!
	 */

	public Preferences()
	{}

	public void save()
	{
		maxPlayers = 10;
		soundEnabled = true;
		debugging = false;
		ballRadius = 60f;
		hackEnabled = false;
		maxBlobWidth = 1;
		maxBlobHeight = 1;
		lowerThreshold = 10;
		upperThreshold = 200;
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
				maxBlobWidth = (int) (data.getMaxBlobWidth() * 100);
				maxBlobHeight = (int) (data.getMaxBlobHeight() * 100);
				lowerThreshold = data.getLowerThreshold();
				upperThreshold = data.getUpperThreshold();
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

	public float getMaxBlobWidth()
	{
		return maxBlobWidth / 100f;
	}

	public float getMaxBlobHeight()
	{
		return maxBlobHeight / 100f;
	}

	public int getLowerThreshold()
	{
		return lowerThreshold;
	}

	public int getUpperThreshold()
	{
		return upperThreshold;
	}

}
