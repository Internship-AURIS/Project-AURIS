package com.aau.auris.game.desktop;

import com.aau.auris.game.AURIS_Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher
{
	public static void main(String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = AURIS_Game.SIZE.width;
		config.height = AURIS_Game.SIZE.height;
		new LwjglApplication(new AURIS_Game(), config);
	}
}
