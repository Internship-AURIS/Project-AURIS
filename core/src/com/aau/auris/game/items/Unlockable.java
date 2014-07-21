package com.aau.auris.game.items;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public interface Unlockable
{

	public boolean isLocked();

	public void setLocked(boolean locked);

	public int getScore();

	public Drawable getDrawable();
}
