package com.aau.auris.game.items;

import com.aau.auris.game.Asset.Asset;

public class BallSkin implements Asset, Unlockable
{
	private transient BallSkin defaultSkin1;
	private transient BallSkin defaultSkin2;
	private transient BallSkin defaultSkin3;

	private int cost;
	private int id;
	private boolean locked;

	public BallSkin()
	{}

	public BallSkin(int id, int cost)
	{
		this.id = id;
		this.cost = cost;
		locked = true;
	}

	@Override
	public void loadAsset()
	{
		//		defaultSkin1 = AssetLoader.ballSkin1;
		//		defaultSkin2 = AssetLoader.ballSkin2;
		//		defaultSkin3 = AssetLoader.ballSkin3;
	}

	@Override
	public void disposeAsset()
	{}

	public int getCost()
	{
		return cost;
	}

	public BallSkin getSkin()
	{
		if (id == BALL_SKIN_1) { return defaultSkin1; }
		if (id == BALL_SKIN_2) { return defaultSkin2; }
		if (id == BALL_SKIN_3) { return defaultSkin3; }
		return defaultSkin1;
	}

	@Override
	public boolean isLocked()
	{
		return locked;
	}

	public void setLocked(boolean locked)
	{
		this.locked = locked;
	}

	@Override
	public int getID()
	{
		return id;
	}
}
