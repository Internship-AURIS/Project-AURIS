package com.aau.auris.game.data;

import java.util.ArrayList;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.items.Achievement;
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

	public void addCredits(int amount)
	{
		this.credits += amount;
		if (credits > maxCredits)
		{
			maxCredits = credits;
		}
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

	public void addPoints(int amount)
	{
		this.score += score;
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

	public boolean hasSkinUnlocked(int id)
	{
		for (int i : skinUnlocks)
		{
			if (i == id) { return true; }
		}
		return false;
	}

	public void unlockAchievement(int id)
	{
		achievementUnlocks.add(id);
		score += new Achievement(id).getCreditValue();
	}

	public boolean unlockBallSkin(int id)
	{
		final int costs = new BallSkin(id).getCost();
		if (credits - costs >= 0)
		{
			skinUnlocks.add(id);
			credits -= costs;
			return true;
		}
		return false;
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
