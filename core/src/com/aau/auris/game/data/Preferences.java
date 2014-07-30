package com.aau.auris.game.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class Preferences
{
	private static final transient String FILENAME = new String("preferences.cfg");
	private transient FileHandle file = Gdx.files.local(FILENAME);

	private int maxPlayers;
	private boolean debugging;
	private boolean debug_shape_polygons;
	private boolean debug_shape_vertices;
	private float ballRadius;
	private float minBlobHeight;// in %
	private float minBlobWidth;// in %
	private int lowerThreshold;
	private int upperThreshold;

	public Preferences()
	{}

	public void save()
	{
		maxPlayers = 10;
		debugging = true;
		debug_shape_polygons = true;
		debug_shape_vertices = true;
		ballRadius = 60f;
		minBlobWidth = .1f;
		minBlobHeight = .1f;
		lowerThreshold = 75;
		upperThreshold = 10;
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
				debugging = data.isDebugging();
				debug_shape_polygons = data.isDebuggingShapePolygons();
				debug_shape_vertices = data.isDebuggingShapeVertices();
				ballRadius = data.getBallRadius();
				minBlobWidth = data.getMinBlobWidth();
				minBlobHeight = data.getMinBlobHeight();
				lowerThreshold = data.getLowerThreshold();
				upperThreshold = data.getUpperThreshold();
			}
		} else
		{
			save();
		}
	}

	public int getMaxPlayers()
	{
		return maxPlayers;
	}

	public float getBallRadius()
	{
		return ballRadius;
	}

	public float getMinBlobWidth()
	{
		return minBlobWidth;
	}

	public float getMinBlobHeight()
	{
		return minBlobHeight;
	}

	public int getLowerThreshold()
	{
		return lowerThreshold;
	}

	public int getUpperThreshold()
	{
		return upperThreshold;
	}

	public boolean isDebugging()
	{
		return debugging;
	}

	public boolean isDebuggingShapePolygons()
	{
		return debug_shape_polygons;
	}

	public boolean isDebuggingShapeVertices()
	{
		return debug_shape_vertices;
	}

}
