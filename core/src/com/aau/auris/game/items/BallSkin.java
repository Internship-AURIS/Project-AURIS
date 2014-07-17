package com.aau.auris.game.items;

import com.aau.auris.game.Asset.Asset;
import com.aau.auris.game.Asset.AssetLoader;
import com.badlogic.gdx.graphics.g2d.Animation;

public class BallSkin implements Asset, Unlockable
{
	private transient static final int BALL_SKIN_1 = 0;
	private transient static final int BALL_SKIN_2 = 1;
	private transient static final int BALL_SKIN_3 = 2;
	private transient static final int BALL_SKIN_4 = 3;
	private transient static final int BALL_SKIN_1_COST = 0;
	private transient static final int BALL_SKIN_2_COST = 100;
	private transient static final int BALL_SKIN_3_COST = 100;
	private transient static final int BALL_SKIN_4_COST = 100;

	// Asset
	private transient Animation ballSkinAnimation_1;
	private transient Animation ballSkinAnimation_2;
	private transient Animation ballSkinAnimation_3;
	private transient Animation ballSkinAnimation_4;

	private int id;
	private boolean locked;

	public BallSkin()
	{}

	public BallSkin(int id)
	{
		this.id = id;
		this.locked = true;
	}

	@Override
	public void loadAsset()
	{
		ballSkinAnimation_1 = AssetLoader.ballskin1_animation;
		ballSkinAnimation_2 = AssetLoader.ballskin2_animation;
		ballSkinAnimation_3 = AssetLoader.ballskin3_animation;
		ballSkinAnimation_4 = AssetLoader.ballskin4_animation;
	}

	@Override
	public void disposeAsset()
	{}

	public int getCost()
	{
		if (id == BALL_SKIN_1) { return BALL_SKIN_1_COST; }
		if (id == BALL_SKIN_2) { return BALL_SKIN_2_COST; }
		if (id == BALL_SKIN_3) { return BALL_SKIN_3_COST; }
		if (id == BALL_SKIN_4) { return BALL_SKIN_4_COST; }
		return BALL_SKIN_1_COST;
	}

	public Animation getSkin()
	{
		if (id == BALL_SKIN_1) { return ballSkinAnimation_1; }
		if (id == BALL_SKIN_2) { return ballSkinAnimation_2; }
		if (id == BALL_SKIN_3) { return ballSkinAnimation_3; }
		if (id == BALL_SKIN_4) { return ballSkinAnimation_4; }
		return ballSkinAnimation_1;
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
}
