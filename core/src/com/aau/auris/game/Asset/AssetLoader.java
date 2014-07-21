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
	public static final int COLS = 7;
	public static final int ROWS = 4;

	// Textures
	public static Texture spritesheet;
	public static Texture menu_background;
	public static TextureRegion[][] tmp;
	public static TextureAtlas menu_buttons;
	public static TextureAtlas levelButtons;
	public static Texture menu_background_blank;
	public static Texture menu_background_blank2;
	public static Texture background_Credits;
	public static Texture background_Shop;
	
	// Animations
	public static Animation parachuteBallAnimation1;
	public static Animation parachuteBallAnimation2;
	public static Animation parachuteBallAnimation3;
	public static Animation ballskin1_animation;
	public static Animation ballskin2_animation;
	public static Animation ballskin3_animation;
	public static Animation ballskin4_animation;

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

	// Font
	public static BitmapFont bFont;

	public static void load()
	{
		// Textures
		spritesheet = new Texture(Gdx.files.internal("spriteFly2.png"));
		menu_background = new Texture(Gdx.files.internal("menu_background.png"));
		menu_background_blank = new Texture(Gdx.files.internal("backBlank2.png"));
		menu_background_blank2 = new Texture(Gdx.files.internal("backLevels.png"));
		tmp = TextureRegion.split(spritesheet, spritesheet.getWidth() / COLS, spritesheet.getHeight() / ROWS);
		menu_buttons = new TextureAtlas(Gdx.files.internal("menuButtons.atlas"));

		levelButtons=new TextureAtlas(Gdx.files.internal("levelButtons.atlas"));
		background_Credits=new Texture(Gdx.files.internal("backCredits.png"));
		background_Shop= new Texture(Gdx.files.internal("backShop.png"));
		background_Credits = new Texture(Gdx.files.internal("backCredits.png"));


		// Animations
		TextureRegion[] parachuteFrames1 = new TextureRegion[COLS];
		TextureRegion[] parachuteFrames2 = new TextureRegion[COLS];
		TextureRegion[] parachuteFrames3 = new TextureRegion[COLS];
		for (int i = 0; i < COLS; i++)
		{
			parachuteFrames1[i] = tmp[0][i];
			parachuteFrames2[i] = tmp[1][i];
			parachuteFrames3[i] = tmp[2][i];
		}
		parachuteBallAnimation1 = new Animation(0.08f, parachuteFrames1);
		parachuteBallAnimation1.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		parachuteBallAnimation2 = new Animation(0.08f, parachuteFrames2);
		parachuteBallAnimation2.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		parachuteBallAnimation3 = new Animation(0.08f, parachuteFrames3);
		parachuteBallAnimation3.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		ballskin1_animation = new Animation(0.08f, parachuteFrames1);
		ballskin1_animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		ballskin2_animation = new Animation(0.08f, parachuteFrames2);
		ballskin2_animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		ballskin3_animation = new Animation(0.08f, parachuteFrames3);
		ballskin3_animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		ballskin4_animation = new Animation(0.08f, parachuteFrames3);
		ballskin4_animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

		// Sounds
		hoverSound1 = Gdx.audio.newSound(Gdx.files.internal("hover1.wav"));
		hoverSound2 = Gdx.audio.newSound(Gdx.files.internal("hover2.wav"));
		hoverSound3 = Gdx.audio.newSound(Gdx.files.internal("hover3.wav"));
		menuMusic1 = Gdx.audio.newSound(Gdx.files.internal("theme1.wav"));
		menuMusic2 = Gdx.audio.newSound(Gdx.files.internal("theme2.wav"));
		clickSound = Gdx.audio.newSound(Gdx.files.internal("click.wav"));
		chorusSound = Gdx.audio.newSound(Gdx.files.internal("chorus.wav"));
		hoverWhistle1 = Gdx.audio.newSound(Gdx.files.internal("pfiff1.wav"));
		hoverWhistle2 = Gdx.audio.newSound(Gdx.files.internal("pfiff2.wav"));
		hoverWhistle3 = Gdx.audio.newSound(Gdx.files.internal("pfiff3.wav"));
		//		clickPlop=Gdx.audio.newSound(Gdx.files.internal("plop2.wav"));

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
