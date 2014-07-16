package com.aau.auris.game.desktop;

import com.aau.auris.game.AURISGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher
{
	public static void main(String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
				config.width = AURISGame.PROJECTOR_SIZE.width;
				config.height = AURISGame.PROJECTOR_SIZE.height;
		new LwjglApplication(new AURISGame(), config);
	}
}
