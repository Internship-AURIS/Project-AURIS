package com.aau.auris.game.items;

import com.aau.auris.game.Asset.Asset;
import com.aau.auris.game.Asset.AssetLoader;
import com.badlogic.gdx.graphics.g2d.Animation;

public class BallSkin implements Asset
{
	// BallSkin Settings
	public static final int BALL_SKIN_ID_1 = 0;
	public static final int BALL_SKIN_ID_2 = 1;
	public static final int BALL_SKIN_ID_3 = 2;
	public static final int BALL_SKIN_ID_4 = 3;
	private static final int BALL_SKIN_1_COST = 0;
	private static final int BALL_SKIN_2_COST = 20;
	private static final int BALL_SKIN_3_COST = 140;
	private static final int BALL_SKIN_4_COST = 70;

	// Asset
	private static Animation ballskinAnimation_1;
	private static Animation ballskinAnimation_2;
	private static Animation ballskinAnimation_3;
	private static Animation ballskinAnimation_4;
	private static Animation ballskinFlyAnimation_1;
	private static Animation ballskinFlyAnimation_2;
	private static Animation ballskinFlyAnimation_3;
	private static Animation ballskinFlyAnimation_4;
	private static Animation ballskinPopAnimation_1;
	private static Animation ballskinPopAnimation_2;
	private static Animation ballskinPopAnimation_3;
	private static Animation ballskinPopAnimation_4;
	private static Animation ballskinCheerAnimation_1;
	private static Animation ballskinCheerAnimation_2;
	private static Animation ballskinCheerAnimation_3;
	private static Animation ballskinCheerAnimation_4;
	

	// Other
	private int id;

	public BallSkin()
	{
		loadAsset();
	}

	public BallSkin(int id)
	{
		super();
		this.id = id;
	}

	@Override
	public void loadAsset()
	{
		ballskinAnimation_1 = AssetLoader.ballskinDefault_animation;
		ballskinAnimation_2 = AssetLoader.ballskinYellow_animation;
		ballskinAnimation_3 = AssetLoader.ballskinGreen_animation;
		ballskinAnimation_4 = AssetLoader.ballskinRed_animation;
		ballskinFlyAnimation_1 = AssetLoader.defaultFlyAnimation;
		ballskinFlyAnimation_2 = AssetLoader.yellowFlyAnimation;
		ballskinFlyAnimation_3 = AssetLoader.greenFlyAnimation;
		ballskinFlyAnimation_4 = AssetLoader.redFlyAnimation;
		ballskinPopAnimation_1 = AssetLoader.defaultPopAnimation;
		ballskinPopAnimation_2 = AssetLoader.yellowPopAnimation;
		ballskinPopAnimation_3 = AssetLoader.greenPopAnimation;
		ballskinPopAnimation_4 = AssetLoader.redPopAnimation;
		ballskinCheerAnimation_1=AssetLoader.defaultCheerAnimation;
//		ballskinCheerAnimation_2
		ballskinCheerAnimation_3=AssetLoader.greenCheerAnimation;
		ballskinCheerAnimation_4=AssetLoader.redCheerAnimation;
	}

	@Override
	public void disposeAsset()
	{}

	public int getCreditValue()
	{
		if (id == BALL_SKIN_ID_1) { return BALL_SKIN_1_COST; }
		if (id == BALL_SKIN_ID_2) { return BALL_SKIN_2_COST; }
		if (id == BALL_SKIN_ID_3) { return BALL_SKIN_3_COST; }
		if (id == BALL_SKIN_ID_4) { return BALL_SKIN_4_COST; }
		return BALL_SKIN_1_COST;
	}

	public Animation getSkinAnimation()
	{
		if (id == BALL_SKIN_ID_1) { return ballskinAnimation_1; }
		if (id == BALL_SKIN_ID_2) { return ballskinAnimation_2; }
		if (id == BALL_SKIN_ID_3) { return ballskinAnimation_3; }
		if (id == BALL_SKIN_ID_4) { return ballskinAnimation_4; }
		return ballskinAnimation_1;
	}

	public Animation getFlyAnimation(int id)
	{
		if (id == BALL_SKIN_ID_1) { return ballskinFlyAnimation_1; }
		if (id == BALL_SKIN_ID_2) { return ballskinFlyAnimation_2; }
		if (id == BALL_SKIN_ID_3) { return ballskinFlyAnimation_3; }
		if (id == BALL_SKIN_ID_4) { return ballskinFlyAnimation_4; }
		return ballskinFlyAnimation_1;
	}

	public Animation getPopAnimation(int id)
	{
		if (id == BALL_SKIN_ID_1) { return ballskinPopAnimation_1; }
		if (id == BALL_SKIN_ID_2) { return ballskinPopAnimation_2; }
		if (id == BALL_SKIN_ID_3) { return ballskinPopAnimation_3; }
		if (id == BALL_SKIN_ID_4) { return ballskinPopAnimation_4; }
		return ballskinPopAnimation_1;
	}
	public Animation getCheerAnimation()
	{
		if (id == BALL_SKIN_ID_1) { return ballskinCheerAnimation_1; }
//		if (id == BALL_SKIN_ID_2) { return ballskinPopAnimation_2; }
		if (id == BALL_SKIN_ID_3) { return ballskinCheerAnimation_3; }
		if (id == BALL_SKIN_ID_4) { return ballskinCheerAnimation_4; }
		return ballskinCheerAnimation_1;
	}

	public int getID()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
}
