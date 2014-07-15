package com.aau.auris.game.desktop;

import com.aau.auris.game.AURIS_Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
<<<<<<< HEAD
import com.aau.auris.game.AURIS_Game;
=======
>>>>>>> menu_login

public class DesktopLauncher
{
	public static void main(String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
<<<<<<< HEAD
		config.width=848;
		config.height=480;
=======
		config.width = AURIS_Game.SIZE.width;
		config.height = AURIS_Game.SIZE.height;
>>>>>>> menu_login
		new LwjglApplication(new AURIS_Game(), config);
	}
}


