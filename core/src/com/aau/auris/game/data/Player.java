package com.aau.auris.game.data;

import java.util.ArrayList;

import com.aau.auris.game.items.Achievement;
import com.aau.auris.game.items.Unlockable;

public class Player
{
	// unlocks
	private ArrayList<Unlockable> unlocked;

	private String name;// player name
	private int score;// composed of maxCredits + achievements, etc.
	private int maxCredits;// maximal credits the player ever reached
	private int credits;// current credits, used for bills

	public Player()
	{}

	public Player(String name, int credits, int score, ArrayList<Unlockable> unlocked)
	{
		this.name = name;
		this.score = score;
		this.credits = credits;
		calcMaxScore();
	}

	private void calcMaxScore()
	{
		// TODO: implement calculation of maximal score
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
	}

	public int getMaxCredits()
	{
		return maxCredits;
	}

	public void setMaxCredits(int maxCredits)
	{
		this.maxCredits = maxCredits;
	}

	public int getScore()
	{
		return score;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	public ArrayList<Achievement> getAchievements()
	{
		return null;
	}

	@Override
	public String toString()
	{
		return "{" + Player.class.getSimpleName() + "[" + "name:" + name + ",credits:" + credits + "]}";
	}

}
