package com.aau.auris.game.data;

import java.util.ArrayList;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.items.BallSkin;

public class Player
{
	// unlocks
	private ArrayList<Integer> achievementUnlocks;
	private ArrayList<Integer> levelUnlocks;
	private ArrayList<Integer> skinUnlocks;

	private String name;// player name
	private int achievPoints;// get points for unlocking achievements
	private int credits;// current credits, used for bills
	private int maxCredits;// maximal credits the player ever reached
	private int score;// composed of maxCredits + achievements, etc.
	private int skinID;

	/*
	 * default constructor needed for Json serialization
	 */
	public Player()
	{}

	public Player(String name, int credits, int maxCredits, int score, ArrayList<Integer> achievementUnlocks, ArrayList<Integer> levelUnlocks, ArrayList<Integer> skinUnlocks, int skinID)
	{
		this.name = name;
		this.achievPoints = score - maxCredits;
		this.credits = credits;
		this.maxCredits = maxCredits;
		this.score = score;
		this.achievementUnlocks = achievementUnlocks;
		this.levelUnlocks = levelUnlocks;
		this.skinUnlocks = skinUnlocks;
		this.skinID = skinID;
	}

	public static Player generateNewPlayer(String name)
	{
		Player player = new Player(name, 0, 0, 0, AURISGame.getDefaultAchievementUnlocks(), AURISGame.getDefaultLevelUnlocks(), AURISGame.getDefaultSkinUnlocks(), BallSkin.BALL_SKIN_ID_1);
		return player;
	}

	private void calcScore()
	{
		this.score = maxCredits + achievPoints;
	}

	public void setSkin(int skinID)
	{
		this.skinID = skinID;
	}

	public int getSkinID()
	{
		return skinID;
	}

	public ArrayList<Integer> getAchievementUnlocks()
	{
		return achievementUnlocks;
	}

	public ArrayList<Integer> getLevelUnlocks()
	{
		return levelUnlocks;
	}

	public ArrayList<Integer> getSkinUnlocks()
	{
		return skinUnlocks;
	}

	public String getName()
	{
		return name;
	}

	public int getCredits()
	{
		return credits;
	}

	public void setCredits(int credits)
	{
		this.credits = credits;
		if (credits > maxCredits)
		{
			maxCredits = credits;
		}
	}

	public int getMaxCredits()
	{
		return maxCredits;
	}

	public int getScore()
	{
		calcScore();
		return score;
	}

	public void setScore(int score)
	{
		achievPoints = 0;// TODO: debugging
		maxCredits = score;
		this.score = score;
	}

	public void setMaxScore(int mScore)
	{}

	public boolean hasLevelUnlocked(int id)
	{
		for (int i : levelUnlocks)
		{
			if (i == id) { return true; }
		}
		return false;
	}

	public void addAchievementID(int id)
	{
		achievementUnlocks.add(id);
	}

	public void addLevelID(int id)
	{
		levelUnlocks.add(id);
	}

	public void addSkinID(int id)
	{
		skinUnlocks.add(id);
	}

}
