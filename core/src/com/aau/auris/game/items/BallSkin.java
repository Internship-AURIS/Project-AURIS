package com.aau.auris.game.items;

import com.aau.auris.game.Asset.Asset;
import com.aau.auris.game.Asset.AssetLoader;
import com.badlogic.gdx.graphics.g2d.Animation;

public class BallSkin implements Asset
{
	// Ball Settings
	public static final int BALL_SKIN_ID_1 = 0;
	public static final int BALL_SKIN_ID_2 = 1;
	public static final int BALL_SKIN_ID_3 = 2;
	public static final int BALL_SKIN_ID_4 = 3;
	private static final int BALL_SKIN_1_COST = 0;
	private static final int BALL_SKIN_2_COST = 140;
	private static final int BALL_SKIN_3_COST = 100;
	private static final int BALL_SKIN_4_COST = 100;

	// Asset
	private transient Animation ballSkinAnimation_1;
	private transient Animation greenBallSkinAnimation;
	private transient Animation ballSkinAnimation_3;
	private transient Animation ballSkinAnimation_4;

	// Other
	private int id;

	public BallSkin()
	{}

	public BallSkin(int id)
	{
		this.id = id;
	}

	@Override
	public void loadAsset()
	{
		ballSkinAnimation_1 = AssetLoader.ballDefault_animation;
		greenBallSkinAnimation = AssetLoader.ballskinGreen_animation;
		ballSkinAnimation_3 = AssetLoader.ballskin3_animation;
		ballSkinAnimation_4 = AssetLoader.ballskin4_animation;
	}

	@Override
	public void disposeAsset()
	{}

	public int getCost()
	{
		if (id == BALL_SKIN_ID_1) { return BALL_SKIN_1_COST; }
		if (id == BALL_SKIN_ID_2) { return BALL_SKIN_2_COST; }
		if (id == BALL_SKIN_ID_3) { return BALL_SKIN_3_COST; }
		if (id == BALL_SKIN_ID_4) { return BALL_SKIN_4_COST; }
		return BALL_SKIN_1_COST;
	}

	public Animation getSkin()
	{
		if (id == BALL_SKIN_ID_1) { return ballSkinAnimation_1; }
		if (id == BALL_SKIN_ID_2) { return greenBallSkinAnimation; }
		if (id == BALL_SKIN_ID_3) { return ballSkinAnimation_3; }
		if (id == BALL_SKIN_ID_4) { return ballSkinAnimation_4; }
		return ballSkinAnimation_1;
	}

	public int getID()
	{
		return id;
	}
}
