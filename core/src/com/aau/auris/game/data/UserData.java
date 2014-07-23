package com.aau.auris.game.data;

import java.util.ArrayList;

import com.aau.auris.game.AURISGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class UserData
{
	private transient static final String USERDATA_FILENAME = "userdata.json";
	private transient int MAX_PLAYERS;

	private transient final FileHandle file = Gdx.files.local(USERDATA_FILENAME);
	private ArrayList<Player> players;

	public UserData()
	{}

	public UserData(AURISGame game)
	{
		MAX_PLAYERS = game.getPreferences().getMaxPlayers();
	}

	public ArrayList<Player> getPlayers()
	{
		return players;
	}

	public boolean containsPlayerName(String inputName)
	{
		for (Player p : players)
		{
			if (p != null && p.getName().toLowerCase().equals(inputName.toLowerCase())) { return true; }
		}
		return false;
	}

	public Player getPlayerViaName(String name)
	{
		for (Player p : players)
		{
			if (p != null && p.getName().toLowerCase().equals(name.toLowerCase())) { return p; }
		}
		return null;
	}

	/**
	 * fills the array in sequence with player_stats.
	 * if the array is full, the method overwrites the last player_stats in the array!
	 * @param player
	 */
	public void createPlayer(Player player)
	{
		if (players.size() - 1 <= MAX_PLAYERS)
		{
			players.add(player);
		} else
		{
			//overwrite last player_stats because max amount of stats is reached
			players.set(MAX_PLAYERS - 1, player);
		}
		save();
	}

	public void save()
	{
		Json json = new Json();
		//		json.setElementType(UserData.class, "players", ArrayList.class);
		final String text = json.toJson(this);
		file.writeString(text, false);
	}

	public void load()
	{
		//		players.clear();

		if (file.exists())
		{
			final String text = file.readString();
			Json json = new Json();
			UserData data = json.fromJson(UserData.class, text);
			if (data != null && (data.getPlayers() != null && data.getPlayers().size() > 0))
			{
				players = data.getPlayers();
			}
		}
	}
}
