package com.aau.auris.game;

import java.awt.Dimension;
import java.util.ArrayList;

import com.aau.auris.game.Asset.AssetLoader;
import com.aau.auris.game.data.Player;
import com.aau.auris.game.data.Preferences;
import com.aau.auris.game.data.UserData;
import com.aau.auris.game.imageprocessing.ImageProcessor;
import com.aau.auris.game.items.Achievement;
import com.aau.auris.game.items.BallSkin;
import com.aau.auris.game.level.Level;
import com.aau.auris.game.screens.CreditsScreen;
import com.aau.auris.game.screens.GameScreen;
import com.aau.auris.game.screens.LevelScreen;
import com.aau.auris.game.screens.LoginScreen;
import com.aau.auris.game.screens.MenuScreen;
import com.aau.auris.game.screens.ShopScreen;
import com.aau.auris.game.screens.VictoryScreen;
import com.aau.auris.game.webcam.WebcamHandler;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class AURISGame extends Game
{
	public static final Dimension PROJECTOR_SIZE = new Dimension(848, 480);

	// Screens
	public static final int MENU_SCREEN = 0;
	public static final int LOGIN_SCREEN = 1;
	public static final int LEVEL_SCREEN = 2;
	public static final int CREDITS_SCREEN = 3;
	public static final int SHOP_SCREEN = 4;
	public static final int GAME_SCREEN = 5;
	public static final int VICTORY_SCREEN = 6;

	private MenuScreen menuScreen;
	private LoginScreen loginScreen;
	private LevelScreen levelScreen;
	private GameScreen gameScreen;
	private ShopScreen shopScreen;
	private CreditsScreen creditsScreen;
	private VictoryScreen victoryScreen;

	// Features
	public static ArrayList<Achievement> achievements;
	public static ArrayList<Level> levels;
	public static ArrayList<BallSkin> ballSkins;

	// ImageProcessing
	private ImageProcessor imageProcessor;
	private WebcamHandler webcamHandler;

	// Data
	private Preferences preferences;
	private UserData userdata;

	// Other
	private Player player = null;
	private Level level = null;

	@Override
	public void create()
	{
		// Data
		AssetLoader.load();
		preferences = new Preferences();
		preferences.load();
		userdata = new UserData(this);
		userdata.load();

		// Screens 
		menuScreen = new MenuScreen(this);
		loginScreen = new LoginScreen(this);
		levelScreen = new LevelScreen(this);
		gameScreen = new GameScreen(this);
		shopScreen = new ShopScreen(this);
		creditsScreen = new CreditsScreen(this);
		victoryScreen = new VictoryScreen(this);

		// Initialization
		initAchievements();
		initBallSkins();
		initLevels();

		// Current Screen, StartScreen
		this.setScreen(menuScreen);

		// Image Processing
		imageProcessor = new ImageProcessor(this);
		webcamHandler = new WebcamHandler(imageProcessor);
	}

	@Override
	public void render()
	{
		super.render();
	}

	private void initAchievements()
	{
		achievements = new ArrayList<Achievement>();
		achievements.add(new Achievement(Achievement.ACHIEVEMENT_ID_1));
		achievements.add(new Achievement(Achievement.ACHIEVEMENT_ID_2));
		achievements.add(new Achievement(Achievement.ACHIEVEMENT_ID_3));
	}

	private void initBallSkins()
	{
		ballSkins = new ArrayList<BallSkin>();
		ballSkins.add(new BallSkin(BallSkin.BALL_SKIN_ID_1));
		ballSkins.add(new BallSkin(BallSkin.BALL_SKIN_ID_2));
		ballSkins.add(new BallSkin(BallSkin.BALL_SKIN_ID_3));
		ballSkins.add(new BallSkin(BallSkin.BALL_SKIN_ID_4));
	}

	private void initLevels()
	{
		levels = new ArrayList<Level>();
		levels.add(new Level(this, Level.LEVEL_ID_1));
		levels.add(new Level(this, Level.LEVEL_ID_2));
		levels.add(new Level(this, Level.LEVEL_ID_3));
		levels.add(new Level(this, Level.LEVEL_ID_4));
		levels.add(new Level(this, Level.LEVEL_ID_5));
		levels.add(new Level(this, Level.LEVEL_ID_6));
		levels.add(new Level(this, Level.LEVEL_ID_7));
		levels.add(new Level(this, Level.LEVEL_ID_8));
		levels.add(new Level(this, Level.LEVEL_ID_9));
	}

	public void changeScreen(int screenIndex, Screen screen)
	{
		screen.hide();
		Screen newScreen = null;
		if (screenIndex == MENU_SCREEN)
		{
			newScreen = menuScreen;
		} else if (screenIndex == LOGIN_SCREEN)
		{
			newScreen = loginScreen;
		} else if (screenIndex == LEVEL_SCREEN)
		{
			newScreen = levelScreen;
		} else if (screenIndex == CREDITS_SCREEN)
		{
			newScreen = creditsScreen;
		} else if (screenIndex == SHOP_SCREEN)
		{
			newScreen = shopScreen;
		} else if (screenIndex == GAME_SCREEN)
		{
			newScreen = gameScreen;
		} else if (screenIndex == VICTORY_SCREEN)
		{
			newScreen = victoryScreen;
		} else
		{
			newScreen = menuScreen;// default solution
		}
		this.setScreen(newScreen);
	}

	public Preferences getPreferences()
	{
		return preferences;
	}

	public UserData getUserData()
	{
		return userdata;
	}

	public Player getPlayer()
	{
		return player;
	}

	public void setPlayer(Player player)
	{
		this.player = player;
	}

	public Level getLevel()
	{
		return level;
	}

	public void setLevel(Level level)
	{
		level.reset();
		this.level = level;
	}

	public int getWidth()
	{
		return Gdx.graphics.getWidth();
	}

	public int getHeight()
	{
		return Gdx.graphics.getHeight();
	}

	public WebcamHandler getWebcamHandler()
	{
		return webcamHandler;
	}

	@Override
	public void dispose()
	{
		super.dispose();
		userdata.save();
		webcamHandler.dispose();
		AssetLoader.dispose();
		System.exit(0);
	}

	public static ArrayList<Integer> getDefaultAchievementUnlocks()
	{
		ArrayList<Integer> unlocks = new ArrayList<Integer>();
		return unlocks;
	}

	public static ArrayList<Integer> getDefaultLevelUnlocks()
	{
		ArrayList<Integer> unlocks = new ArrayList<Integer>();
		unlocks.add(Level.LEVEL_ID_1);
		return unlocks;
	}

	public static ArrayList<Integer> getDefaultSkinUnlocks()
	{
		ArrayList<Integer> unlocks = new ArrayList<Integer>();
		unlocks.add(BallSkin.BALL_SKIN_ID_1);
		return unlocks;
	}

	public static Level getLevel(int id)
	{
		for (Level lvl : levels)
		{
			if (lvl.getID() == id) { return lvl; }
		}
		return null;
	}

	public GameScreen getGameScreen()
	{
		return gameScreen;
	}

}
