package com.aau.auris.game.items;

import com.aau.auris.game.Asset.Asset;

public class BallSkin implements Asset
{
	private transient BallSkin defaultSkin1;
	private transient BallSkin defaultSkin2;
	private transient BallSkin defaultSkin3;

	private String name;
	private int cost;

	public BallSkin(String name, int cost)
	{
		this.name = name;
		this.cost = cost;
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

	public BallSkin getSkin()
	{
		return null;
	}
}
