package com.aau.auris.game.data;

import java.util.ArrayList;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.items.Achievement;
import com.aau.auris.game.items.BallSkin;
import com.aau.auris.game.level.Level;

public class Player
{
	// unlocks
	private ArrayList<Integer> achievementUnlocks;
	private ArrayList<Integer> levelUnlocks;
	private ArrayList<Integer> skinUnlocks;

	private String name;// player name
	private int credits;// current credits, used for bills
	private int score;// maximal credits the player ever reached
	private int skinID;

	/*
	 * default constructor needed for Json serialization
	 */
	public Player()
	{}

	public Player(String name, int credits, int score, ArrayList<Integer> achievementUnlocks, ArrayList<Integer> levelUnlocks, ArrayList<Integer> skinUnlocks, int skinID)
	{
		this.name = name;
		this.credits = credits;
		this.score = score;
		this.achievementUnlocks = achievementUnlocks;
		this.levelUnlocks = levelUnlocks;
		this.skinUnlocks = skinUnlocks;
		this.skinID = skinID;
		checkAchievements();
	}

	public static Player generateNewPlayer(String name)
	{
		Player player = new Player(name, 0, 0, AURISGame.getDefaultAchievementUnlocks(), AURISGame.getDefaultLevelUnlocks(), AURISGame.getDefaultSkinUnlocks(), BallSkin.BALL_SKIN_ID_1);
		return player;
	}

	public void checkAchievements()
	{
		boolean allLevelsCleared = true;
		ArrayList<Level> levels = AURISGame.levels;
		for (Level level : levels)
		{
			if (!hasLevelUnlocked(level.getID()))
			{
				allLevelsCleared = false;
				break;
			}
		}
		if (allLevelsCleared)
		{			
			addAchievementID(Achievement.ACHIEVEMENT_ID_1);
			//TODO: 19 coins EINMALIG adden

		}
	}

	public void setSkin(int skinID)
	{
		for (int i = 0; i < skinUnlocks.size(); i++)
		{
			this.skinID = skinID;
		}
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

	public void addCredits(int value)
	{
		this.credits += value;
		if (credits > 999)
		{
			credits = 999;
		}
		addPoints(value);
	}

	public void addPoints(int points)
	{
		score += points;
		if (score > 999)
		{
			score = 999;
		}
	}

	public int getScore()
	{
		return score;
	}

	public boolean hasAchievementUnlocked(int id)
	{
		for (int i : achievementUnlocks)
		{
			if (i == id) { return true; }
		}
		return false;
	}

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

	public void addAchievementID(int id)
	{
		if (!hasAchievementUnlocked(id))
		{
			achievementUnlocks.add(id);
			if(id==5){
			addCredits(new Achievement(id).getCreditValue());	
			}else
			{
			addPoints(new Achievement(id).getCreditValue());
			}
		}
		
	}

	public boolean addBallSkinID(int id)
	{
		if (!hasSkinUnlocked(id))
		{
			final int costs = new BallSkin(id).getCreditValue();
			if (credits - costs >= 0)
			{
				skinUnlocks.add(id);
				credits -= costs;
				if(getSkinUnlocks().size()==4){
					addAchievementID(4);

				}
				return true;
			}
		}
		return false;
	}

	public boolean addLevelID(int id)
	{
		if (!hasLevelUnlocked(id))
		{
			levelUnlocks.add(id);
			return true;
		}
		return false;
	}

	public boolean addSkinID(int id)
	{
		if (!hasSkinUnlocked(id))
		{
			skinUnlocks.add(id);
			return true;
		}
		return false;
	}
}
