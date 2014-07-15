package com.aau.auris.game.desktop;

import java.awt.Dimension;

import com.aau.auris.game.AURIS_Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher
{

	public static final Dimension PROJECTOR_DIMENSION = new Dimension(848, 480);

	public static void main(String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = PROJECTOR_DIMENSION.width;
		config.height = PROJECTOR_DIMENSION.height;
		new LwjglApplication(new AURIS_Game(), config);
	}
}
