package com.aau.auris.game.items;

public interface Unlockable
{
	public boolean isLocked();

	public void setLocked(boolean locked);

	public int getScore();
}
