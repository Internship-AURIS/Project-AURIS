package com.aau.auris.game.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.aau.auris.game.AURIS_Game;
import com.aau.auris.game.userdata.Player;

public class HighScore
{
	private AURIS_Game game;
	private ArrayList<Player> scoreList;

	public HighScore(AURIS_Game game)
	{
		this.game = game;
		this.scoreList = this.game.getUserData().getPlayers();
	}

	private void sort()
	{
		Collections.sort(scoreList, new Comparator<Player>()
		{
			@Override
			public int compare(Player p1, Player p2)
			{
				final int score1 = p1.getScore();
				final int score2 = p2.getScore();

				if (score1 > score2) { return 1; }
				if (score1 < score2) { return -1; }
				return 0;
			}
		});
	}

	public ArrayList<Player> getScoreList()
	{
		sort();
		return scoreList;
	}

}
