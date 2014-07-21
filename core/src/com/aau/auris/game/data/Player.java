package com.aau.auris.game.data;

import java.util.ArrayList;

import com.aau.auris.game.items.Achievement;
import com.aau.auris.game.items.BallSkin;
import com.aau.auris.game.items.Unlockable;
import com.aau.auris.game.level.Level;

public class Player
{
	// unlocks
	private ArrayList<Achievement> achievements;
	private ArrayList<Level> levels;
	private ArrayList<BallSkin> skins;

	private String name;// player name
	private int achievPoints;// get points for unlocking achievements
	private int credits;// current credits, used for bills
	private int maxCredits;// maximal credits the player ever reached
	private int score;// composed of maxCredits + achievements, etc.
	private BallSkin currentSkin;

	/*
	 * default constructor
	 * needed for Json serialization
	 */
	public Player()
	{}

	public Player(String name, int credits, int maxCredits, int score, ArrayList<Achievement> achievements, ArrayList<Level> levels, ArrayList<BallSkin> skins, BallSkin currentSkin)
	{
		this.name = name;
		this.achievPoints = score - maxCredits;
		this.credits = credits;
		this.maxCredits = maxCredits;
		this.score = score;
		this.achievements = achievements;
		this.levels = levels;
		this.skins = skins;
		this.currentSkin = currentSkin;
	}

	public static Player generateNewPlayer(String name)
	{
		ArrayList<Achievement> achievements = Achievement.getList();
		ArrayList<Level> levels = Level.getList();
		ArrayList<BallSkin> skins = BallSkin.getList();
		Player player = new Player(name, 0, 0, 0, achievements, levels, skins, BallSkin.getDefault());
		player.unlock(achievements.get(0));
		return player;
	}

	public void unlock(Unlockable item)
	{
		achievPoints += item.getScore();
		item.setLocked(false);
	}

	public void setSkin(BallSkin skin)
	{
		this.currentSkin = skin;
	}

	private void calcScore()
	{
		this.score = maxCredits + achievPoints;
	}

	public ArrayList<Achievement> getAchievements()
	{
		return achievements;
	}

	public void addAchievement(Achievement a)
	{
		achievPoints += a.getScore();
	}

	public ArrayList<Level> getLevels()
	{
		return levels;
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

	@Override
	public String toString()
	{
		return "{" + Player.class.getSimpleName() + "[" + "name:" + name + ",credits:" + credits + "]}";
	}

}
