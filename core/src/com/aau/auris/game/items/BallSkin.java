package com.aau.auris.game.items;

import com.aau.auris.game.Asset.Asset;
import com.aau.auris.game.Asset.AssetLoader;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class BallSkin implements Asset, Unlockable
{
	// Ball Settings
	private static final transient int BALL_SKIN_1 = 0;
	private static final transient int BALL_SKIN_2 = 1;
	private static final transient int BALL_SKIN_3 = 2;
	private static final transient int BALL_SKIN_4 = 3;
	private static final transient int BALL_SKIN_1_COST = 0;
	private static final transient int BALL_SKIN_2_COST = 100;
	private static final transient int BALL_SKIN_3_COST = 100;
	private static final transient int BALL_SKIN_4_COST = 100;

	// Asset
	private transient Animation ballSkinAnimation_1;
	private transient Animation ballSkinAnimation_2;
	private transient Animation ballSkinAnimation_3;
	private transient Animation ballSkinAnimation_4;

	// Other
	private transient final String skin_PreString = "ball_skin";
	private transient Skin skin; // to pass drawable parameter

	private int id;
	private boolean locked;

	public BallSkin()
	{}

	public BallSkin(int id, boolean locked)
	{
		this.id = id;
		this.locked = locked;
	}

	@Override
	public void loadAsset()
	{
		ballSkinAnimation_1 = AssetLoader.ballDefault_animation;
		ballSkinAnimation_2 = AssetLoader.ballskin2_animation;
		ballSkinAnimation_3 = AssetLoader.ballskin3_animation;
		ballSkinAnimation_4 = AssetLoader.ballskin4_animation;

		// TODO: add ball skins
		skin = new Skin();
	}

	@Override
	public void disposeAsset()
	{}

	public int getCost()
	{
		if (id == BALL_SKIN_1)
		{
			return BALL_SKIN_1_COST;
		}
		if (id == BALL_SKIN_2)
		{
			return BALL_SKIN_2_COST;
		}
		if (id == BALL_SKIN_3)
		{
			return BALL_SKIN_3_COST;
		}
		if (id == BALL_SKIN_4)
		{
			return BALL_SKIN_4_COST;
		}
		return BALL_SKIN_1_COST;
	}

	public Animation getSkin()
	{
		if (id == BALL_SKIN_1)
		{
			return ballSkinAnimation_1;
		}
		if (id == BALL_SKIN_2)
		{
			return ballSkinAnimation_2;
		}
		if (id == BALL_SKIN_3)
		{
			return ballSkinAnimation_3;
		}
		if (id == BALL_SKIN_4)
		{
			return ballSkinAnimation_4;
		}
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

	@Override
	public int getScore()
	{
		return 0;
	}

	@Override
	public Drawable getDrawable()
	{
		return skin.getDrawable(skin_PreString + id);
	}

}
