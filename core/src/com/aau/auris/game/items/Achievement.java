package com.aau.auris.game.items;

import java.util.ArrayList;

import com.aau.auris.game.Asset.Asset;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Achievement implements Asset, Unlockable
{
	// Achievement Settings
	public static transient final int ACHIEVEMENT_1 = 3;
	public static transient final int ACHIEVEMENT_2 = 4;
	public static transient final int ACHIEVEMENT_3 = 5;
	public static final transient int ACHIEVEMENT_1_SCORE = 5;
	public static final transient int ACHIEVEMENT_2_SCORE = 5;
	public static final transient int ACHIEVEMENT_3_SCORE = 5;

	// Asset

	// Other
	private final transient String skin_PreString = "achiev_skin";
	private transient Skin skin;

	// Object Variables
	private int id;
	private boolean locked;

	public Achievement()
	{}

	public Achievement(int id, boolean locked)
	{
		this.id = id;
		this.locked = locked;
	}

	@Override
	public void loadAsset()
	{
		// TODO: load asset
		skin = new Skin();
	}

	@Override
	public void disposeAsset()
	{}

//	public static Achievement getAchievement(int id)
//	{
//		if (id == ACHIEVEMENT_1)
//		{
//			return new Achievement(ACHIEVEMENT_1, true);
//		} else if (id == ACHIEVEMENT_2)
//		{
//			return new Achievement(ACHIEVEMENT_2, true);
//		} else if (id == ACHIEVEMENT_3)
//		{
//			return new Achievement(ACHIEVEMENT_2, true);
//		}
//		return null;
//	}

	@Override
	public boolean isLocked()
	{
		return locked;
	}

	@Override
	public void setLocked(boolean locked)
	{
		this.locked = locked;
	}

	@Override
	public int getScore()
	{
		if (id == ACHIEVEMENT_1)
		{
			return ACHIEVEMENT_1_SCORE;
		} else if (id == ACHIEVEMENT_2)
		{
			return ACHIEVEMENT_2_SCORE;
		} else if (id == ACHIEVEMENT_3)
		{
			return ACHIEVEMENT_3_SCORE;
		}
		return 0;
	}

	public static ArrayList<Achievement> getList()
	{
		ArrayList<Achievement> achievements = new ArrayList<Achievement>();
		achievements.add(new Achievement(ACHIEVEMENT_1, true));
		achievements.add(new Achievement(ACHIEVEMENT_2, true));
		achievements.add(new Achievement(ACHIEVEMENT_3, true));
		return achievements;
	}

	@Override
	public Drawable getDrawable()
	{
		return skin.getDrawable(skin_PreString + id);
	}

}
