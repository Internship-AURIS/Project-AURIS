package com.aau.auris.game.Asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader
{
	// SpriteSheet Properties
	public static final int FLY_COLS = 7;
	public static final int FLY_ROWS = 4;
	public static final int BALL_COLS = 6;
	public static final int BALL_ROWS = 6;

	// Textures
	public static Texture spritesheet;
	public static Texture defaultBall;
	public static Texture greenBall;
	public static Texture yellowBall;
	public static Texture redBall;
	public static Texture menu_background;
	public static TextureRegion[][] tmp;
	public static TextureRegion[][] tmpDefault;
	public static TextureRegion[][] tmpGreen;
	public static TextureRegion[][] tmpYellow;
	public static TextureRegion[][] tmpRed;
	public static TextureAtlas menu_buttons;
	public static TextureAtlas levelButtons;
	public static TextureAtlas levelgoals;
	public static Texture menu_background_blank;
	public static Texture menu_background_blank2;
	public static Texture background_Credits;
	public static Texture background_Shop;
	public static Texture levelBalken;

	// Animations
	public static Animation parachuteBallAnimation1;
	public static Animation parachuteBallAnimation2;
	public static Animation parachuteBallAnimation3;

	public static Animation ballskinDefault_animation;
	public static Animation ballskinGreen_animation;
	public static Animation ballskinYellow_animation;
	public static Animation ballskinRed_animation;

	public static Animation defaultPopAnimation;
	public static Animation yellowPopAnimation;
	public static Animation greenPopAnimation;
	public static Animation redPopAnimation;

	public static Animation defaultFlyAnimation;
	public static Animation yellowFlyAnimation;
	public static Animation greenFlyAnimation;
	public static Animation redFlyAnimation;

	// Sounds
	public static Sound hoverSound1;
	public static Sound hoverSound2;
	public static Sound hoverSound3;
	public static Sound menuMusic1, menuMusic2;
	public static Sound clickSound;
	public static Sound chorusSound;
	public static Sound hoverWhistle1;
	public static Sound hoverWhistle2;
	public static Sound hoverWhistle3;
	public static Sound clickPlop;
	public static Sound coinSound;

	// Font
	public static BitmapFont bFont;

	public static void load()
	{
		// Textures
		defaultBall = new Texture(Gdx.files.internal("textures/boredskins.png"));
		spritesheet = new Texture(Gdx.files.internal("textures/spriteFly2.png"));
		greenBall = new Texture(Gdx.files.internal("textures/greenBored.png"));
		yellowBall = new Texture(Gdx.files.internal("textures/yellowBored.png"));
		redBall = new Texture(Gdx.files.internal("textures/redBored.png"));
		menu_background = new Texture(Gdx.files.internal("textures/menu_background.png"));
		menu_background_blank = new Texture(Gdx.files.internal("textures/backBlank2.png"));
		menu_background_blank2 = new Texture(Gdx.files.internal("textures/backLevels.png"));
		background_Credits = new Texture(Gdx.files.internal("textures/backCredits.png"));
		background_Shop = new Texture(Gdx.files.internal("textures/backShop.png"));
		background_Credits = new Texture(Gdx.files.internal("textures/backCredits.png"));
		levelBalken = new Texture(Gdx.files.internal("textures/balken.png"));

		tmp = TextureRegion.split(spritesheet, spritesheet.getWidth() / FLY_COLS, spritesheet.getHeight() / FLY_ROWS);
		tmpDefault = TextureRegion.split(defaultBall, defaultBall.getWidth() / FLY_COLS, defaultBall.getHeight() / FLY_ROWS);
		tmpGreen = TextureRegion.split(greenBall, greenBall.getWidth() / FLY_COLS, greenBall.getHeight() / FLY_ROWS);
		tmpYellow = TextureRegion.split(yellowBall, yellowBall.getWidth() / FLY_COLS, yellowBall.getHeight() / FLY_ROWS);
		tmpRed = TextureRegion.split(redBall, redBall.getWidth() / FLY_COLS, redBall.getHeight() / FLY_ROWS);

		// Animations
		TextureRegion[] parachuteFrames1 = new TextureRegion[FLY_COLS];
		TextureRegion[] parachuteFrames2 = new TextureRegion[FLY_COLS];
		TextureRegion[] parachuteFrames3 = new TextureRegion[FLY_COLS];
		TextureRegion[] ballBoredFrames = new TextureRegion[FLY_COLS];
		TextureRegion[] greenballBoredFrames = new TextureRegion[FLY_COLS];
		TextureRegion[] yellowballBoredFrames = new TextureRegion[FLY_COLS];
		TextureRegion[] redballBoredFrames = new TextureRegion[FLY_COLS];
		TextureRegion[] defaultPopFrames = new TextureRegion[FLY_COLS+1];
		TextureRegion[] yellowPopFrames = new TextureRegion[FLY_COLS+1];
		TextureRegion[] greenPopFrames = new TextureRegion[FLY_COLS+1];
		TextureRegion[] redPopFrames = new TextureRegion[FLY_COLS+1];
		TextureRegion[] redFlyFrames = new TextureRegion[FLY_COLS];
		TextureRegion[] yellowFlyFrames = new TextureRegion[FLY_COLS];
		TextureRegion[] greenFlyFrames = new TextureRegion[FLY_COLS];
		TextureRegion[] defaultFlyFrames = new TextureRegion[FLY_COLS];

		for (int i = 0; i < FLY_COLS; i++)
		{
			parachuteFrames1[i] = tmp[0][i];
			parachuteFrames2[i] = tmp[1][i];
			parachuteFrames3[i] = tmp[2][i];
			ballBoredFrames[i] = tmpDefault[0][i];
			greenballBoredFrames[i] = tmpGreen[0][i];
			yellowballBoredFrames[i] = tmpYellow[0][i];
			redballBoredFrames[i] = tmpRed[0][i];
			defaultPopFrames[i] = tmpDefault[2][i];
			yellowPopFrames[i] = tmpYellow[2][i];
			greenPopFrames[i] = tmpGreen[2][i];
			redPopFrames[i] = tmpRed[2][i];
			redFlyFrames[i] = tmpRed[1][i];
			yellowFlyFrames[i] = tmpYellow[1][i];
			greenFlyFrames[i] = tmpGreen[1][i];
			defaultFlyFrames[i] = tmpDefault[1][i];
		}
		defaultPopFrames[7] = tmpDefault[3][1];
		yellowPopFrames[7] = tmpYellow[3][1];
		greenPopFrames[7] = tmpGreen[3][1];
		redPopFrames[7] = tmpRed[3][1];

		//Parachute
		parachuteBallAnimation1 = new Animation(0.08f, parachuteFrames1);
		parachuteBallAnimation1.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		parachuteBallAnimation2 = new Animation(0.08f, parachuteFrames2);
		parachuteBallAnimation2.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		parachuteBallAnimation3 = new Animation(0.08f, parachuteFrames3);
		parachuteBallAnimation3.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		//Boring
		ballskinDefault_animation = new Animation(0.08f, ballBoredFrames);
		ballskinDefault_animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		ballskinGreen_animation = new Animation(0.08f, greenballBoredFrames);
		ballskinGreen_animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		ballskinYellow_animation = new Animation(0.08f, yellowballBoredFrames);
		ballskinYellow_animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		ballskinRed_animation = new Animation(0.08f, redballBoredFrames);
		ballskinRed_animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		//Pop
		defaultPopAnimation = new Animation(0.08f, defaultPopFrames);
		defaultPopAnimation.setPlayMode(Animation.PlayMode.NORMAL);
		yellowPopAnimation = new Animation(0.08f, yellowPopFrames);
		yellowPopAnimation.setPlayMode(Animation.PlayMode.NORMAL);
		greenPopAnimation = new Animation(0.08f, greenPopFrames);
		greenPopAnimation.setPlayMode(Animation.PlayMode.NORMAL);
		redPopAnimation = new Animation(0.08f, redPopFrames);
		redPopAnimation.setPlayMode(Animation.PlayMode.NORMAL);
		//Fly
		redFlyAnimation = new Animation(0.08f, redFlyFrames);
		redFlyAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		yellowFlyAnimation = new Animation(0.08f, yellowFlyFrames);
		yellowFlyAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		greenFlyAnimation = new Animation(0.08f, greenFlyFrames);
		greenFlyAnimation.setPlayMode(Animation.PlayMode.LOOP);
		defaultFlyAnimation = new Animation(0.08f, defaultFlyFrames);
		defaultFlyAnimation.setPlayMode(Animation.PlayMode.LOOP);

		// Sounds
		hoverSound1 = Gdx.audio.newSound(Gdx.files.internal("sounds/hover1.wav"));
		hoverSound2 = Gdx.audio.newSound(Gdx.files.internal("sounds/hover2.wav"));
		hoverSound3 = Gdx.audio.newSound(Gdx.files.internal("sounds/hover3.wav"));
		menuMusic1 = Gdx.audio.newSound(Gdx.files.internal("sounds/theme1.wav"));
		menuMusic2 = Gdx.audio.newSound(Gdx.files.internal("sounds/theme2.wav"));
		clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/click.wav"));
		chorusSound = Gdx.audio.newSound(Gdx.files.internal("sounds/chorus.wav"));
		hoverWhistle1 = Gdx.audio.newSound(Gdx.files.internal("sounds/pfiff1.wav"));
		hoverWhistle2 = Gdx.audio.newSound(Gdx.files.internal("sounds/pfiff2.wav"));
		hoverWhistle3 = Gdx.audio.newSound(Gdx.files.internal("sounds/pfiff3.wav"));
		coinSound = Gdx.audio.newSound(Gdx.files.internal("sounds/coins.wav"));

		// Buttons
		menu_buttons = new TextureAtlas(Gdx.files.internal("menuButtons.atlas"));
		levelButtons = new TextureAtlas(Gdx.files.internal("levelButtons.atlas"));
		levelgoals=new TextureAtlas(Gdx.files.internal("goalTextures.atlas"));

		// Font
		bFont = new BitmapFont(Gdx.files.internal("textfont.fnt"));
	}

	public static void dispose()
	{
		// Textures
		spritesheet.dispose();

		// Sounds
		hoverSound1.dispose();
		hoverSound2.dispose();
		hoverSound3.dispose();
		menuMusic1.dispose();
		menuMusic2.dispose();
		clickSound.dispose();

		// Fonts
		bFont.dispose();
	}
}
