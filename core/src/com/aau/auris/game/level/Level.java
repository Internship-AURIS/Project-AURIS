package com.aau.auris.game.level;

import java.util.ArrayList;

import com.aau.auris.game.Asset.Asset;
import com.aau.auris.game.items.Unlockable;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Level implements Asset, Unlockable
{
	// Level Settings
	public static final transient int DIFFICULTY_1 = 0;
	public static final transient int DIFFICULTY_2 = 1;
	public static final transient int DIFFICULTY_3 = 2;
	public static final transient int LEVEL_ID_1 = 0;
	public static final transient int LEVEL_ID_2 = 0;
	public static final transient int LEVEL_ID_3 = 0;
	public static final transient int LEVEL_ID_4 = 0;
	public static final transient int LEVEL_ID_5 = 0;
	public static final transient int LEVEL_ID_6 = 0;
	public static final transient int LEVEL_ID_7 = 0;
	public static final transient int LEVEL_ID_8 = 0;
	public static final transient int LEVEL_ID_9 = 0;

	// Asset
	// TODO: add asset for level display

	// Other
	private final transient String skin_PreString = "level_skin";
	private transient Skin skin;

	// Other Variables
	private int id;
	private boolean locked;

	public Level(int index, boolean locked)
	{
		this.id = index;
		this.locked = true;
	}

	@Override
	public void loadAsset()
	{
		// TODO: add level display assets for level screen, goals, etc.
		skin = new Skin();
	}

	@Override
	public void disposeAsset()
	{
		// TODO Auto-generated method stub
	}

	public void generateWorld(World world, int lvlDifficulty)
	{
		if (lvlDifficulty == DIFFICULTY_1)
		{
		} else if (lvlDifficulty == DIFFICULTY_2)
		{
		} else if (lvlDifficulty == DIFFICULTY_3)
		{
		}
	}

	public int getIndex()
	{
		return id;
	}

	@Override
	public void setLocked(boolean locked)
	{
		this.locked = locked;
	}

	@Override
	public boolean isLocked()
	{
		return locked;
	}

	@Override
	public int getScore()
	{
		return 0;
	}

	public static ArrayList<Level> getList()
	{
		ArrayList<Level> levels = new ArrayList<Level>();
		levels.add(new Level(1, true));
		levels.add(new Level(2, true));
		levels.add(new Level(3, true));
		levels.add(new Level(4, true));
		levels.add(new Level(5, true));
		levels.add(new Level(6, true));
		levels.add(new Level(7, true));
		levels.add(new Level(8, true));
		levels.add(new Level(9, true));
		return levels;
	}

	@Override
	public Drawable getDrawable()
	{
		return skin.getDrawable(skin_PreString + id);
	}

}
