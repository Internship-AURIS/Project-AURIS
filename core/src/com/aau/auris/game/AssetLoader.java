package com.aau.auris.game;

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

	// Animations
	public static Animation parachuteBallAnimation1;
	public static Animation parachuteBallAnimation2;

	// Sounds
	public static Sound hoverSound1;
	public static Sound hoverSound2;
	public static Sound hoverSound3;
	public static Sound menuMusic1, menuMusic2;
	public static Sound clickSound;

	// Font
	public static BitmapFont bFont;

	public static void load()
	{
		// Textures
		spritesheet = new Texture(Gdx.files.internal("spriteFly.png"));
		menu_background = new Texture(Gdx.files.internal("menu_background.png"));
		tmp = TextureRegion.split(spritesheet, spritesheet.getWidth() / COLS, spritesheet.getHeight() / ROWS);
		menu_buttons = new TextureAtlas(Gdx.files.internal("menuButtons.atlas"));

		// Animations
		TextureRegion[] parachuteFrames1 = new TextureRegion[COLS];
		TextureRegion[] parachuteFrames2 = new TextureRegion[COLS];
		for (int i = 0; i < COLS; i++)
		{
			parachuteFrames1[i] = tmp[0][i];
			parachuteFrames2[i] = tmp[1][i];
		}
		parachuteBallAnimation1 = new Animation(0.08f, parachuteFrames1);
		parachuteBallAnimation1.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		parachuteBallAnimation2 = new Animation(0.08f, parachuteFrames2);
		parachuteBallAnimation2.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

		// Sounds
		hoverSound1 = Gdx.audio.newSound(Gdx.files.internal("hover1.wav"));
		hoverSound2 = Gdx.audio.newSound(Gdx.files.internal("hover2.wav"));
		hoverSound3 = Gdx.audio.newSound(Gdx.files.internal("hover3.wav"));
		menuMusic1 = Gdx.audio.newSound(Gdx.files.internal("theme1.wav"));
		menuMusic2 = Gdx.audio.newSound(Gdx.files.internal("theme2.wav"));
		clickSound = Gdx.audio.newSound(Gdx.files.internal("click.wav"));

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
