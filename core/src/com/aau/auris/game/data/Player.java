package com.aau.auris.game.data;

import java.util.ArrayList;

import com.aau.auris.game.items.Achievement;

public class Player
{
	private String name;// player name
	private int score;// composed of maxCredits + achievements, etc.
	private int maxCredits;// maximal credits the player ever reached
	private int credits;// current credits, used for bills
	private ArrayList<Achievement> achievements;// unlocked achievements

	public Player()
	{}

	public Player(String name, int credits, ArrayList<Achievement> achievements)
	{
		this.name = name;
		this.credits = credits;
		this.achievements = achievements != null ? achievements : new ArrayList<Achievement>();
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
		return achievements;
	}

	public void addAchievement(Achievement a)
	{
		achievements.add(a);
	}

	@Override
	public String toString()
	{
		return "{" + Player.class.getSimpleName() + "[" + "name:" + name + ",credits:" + credits + "]}";
	}

}
