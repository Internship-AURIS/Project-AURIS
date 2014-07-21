package com.aau.auris.game.screens;

import java.util.Random;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.Asset.AssetLoader;
import com.aau.auris.game.data.Player;
import com.aau.auris.game.items.BallSkin;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ShopScreen extends AbstractScreen
{
	// Asset
	private TextureAtlas levelButtons;
	private Sound clickSound;
	private Sound hoverSound;
	private Texture background;

	// ShopItemButtons
	private TextButtonStyle shopItem1Style, shopItem2Style, shopItem3Style;

	// Local
	private Player player;
	private final float LABEL_WIDTH = 70f;
	private final float LABEL_HEIGHT = 45f;
	private Label playerLbl;
	private Label creditsLbl;
	private Animation boringMoves;
	private SpriteBatch batch;
	private float runTime;
	private TextureRegion currentFrame;
	private Random r;
	private BallSkin ballSkin;

	public ShopScreen(AURISGame game)
	{
		super(game);
	}

	@Override
	public void loadAsset()
	{
		super.loadAsset();
		levelButtons = AssetLoader.levelButtons;
		clickSound = AssetLoader.clickSound;
		hoverSound = AssetLoader.hoverSound1;
		background = AssetLoader.background_Shop;
		boringMoves = AssetLoader.ballDefault_animation;
		batch = new SpriteBatch();
		r = new Random();
	}

	@Override
	public void disposeAsset()
	{
		super.disposeAsset();
	}

	@Override
	protected void initComponents()
	{
		player = game.getPlayer();
		ballSkin=new BallSkin();
		skin = new Skin(levelButtons);
		skin.add("default", bFont);
		TextureRegion backTextRegion = new TextureRegion(background, 848, 480);
		Image img = new Image(backTextRegion);
		stage.addActor(img);

		// ShopItems
		Pixmap pixmap = new Pixmap(800, 800, Format.RGBA8888);
		//		pixmap.setColor(Color.BLUE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		shopItem1Style = new TextButtonStyle();
		shopItem1Style.font = bFont;
		shopItem1Style.fontColor = Color.WHITE;
		shopItem1Style.up = skin.newDrawable("white");
		skin.add("shopItem1", shopItem1Style);
		TextButton shopItem1 = new TextButton("", shopItem1Style);
		shopItem1.setSize(game.getWidth() / 3, game.getHeight() / 2.75f);
		shopItem1.setPosition(0, game.getHeight() - shopItem1.getTop());
		//		shopItem1.add(shopItem1.getLabel());
		shopItem1.addListener(new ClickListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				super.touchUp(event, x, y, pointer, button);
				clickSound.play();
//				System.out.println("Shop-->purchase: " + event.getButton());
				if(player.getCredits()>=20){
				player.setSkin(BallSkin.BALL_SKIN_ID_2);
				player.setCredits(player.getCredits()-20);
				}else
				{
					System.out.println("Not enough credits!");
				}
			}
		});
		shopItem1.addListener(new InputListener()
		{

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				super.enter(event, x, y, pointer, fromActor);
				hoverSound.play();
			}
		});

		shopItem2Style = new TextButtonStyle();
		shopItem2Style.font = bFont;
		shopItem2Style.fontColor = Color.WHITE;
		shopItem2Style.up = skin.newDrawable("white");
		skin.add("shopItem2", shopItem2Style);
		TextButton shopItem2 = new TextButton("", shopItem2Style);
		shopItem2.setSize(shopItem1.getWidth() + 19, shopItem1.getHeight());
		shopItem2.setPosition(shopItem1.getX() + (shopItem1.getWidth() + 2), shopItem1.getY());
		shopItem2.addListener(new ClickListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				super.touchUp(event, x, y, pointer, button);
				clickSound.play();
				System.out.println("Shop-->purchase: " + event.getButton());
				player.setSkin(BallSkin.BALL_SKIN_ID_3);
			}
		});
		shopItem2.addListener(new InputListener()
		{

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				super.enter(event, x, y, pointer, fromActor);
				hoverSound.play();
			}
		});

		shopItem3Style = new TextButtonStyle();
		shopItem3Style.font = bFont;
		shopItem3Style.fontColor = Color.WHITE;
		shopItem3Style.up = skin.newDrawable("white");
		skin.add("shopItem3", shopItem3Style);
		TextButton shopItem3 = new TextButton("", shopItem3Style);
		shopItem3.setSize(shopItem1.getWidth() - 15, shopItem1.getHeight());
		shopItem3.setPosition(shopItem1.getX() + 23 + shopItem1.getWidth() * 2, shopItem1.getY());
		shopItem3.addListener(new ClickListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				super.touchUp(event, x, y, pointer, button);
				clickSound.play();
//				System.out.println("Shop-->purchase: " + event.getButton());
//				player.setSkin(BallSkin.BALL_SKIN_ID_3);
			}
		});
		shopItem3.addListener(new InputListener()
		{

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				super.enter(event, x, y, pointer, fromActor);
				hoverSound.play();
			}
		});

		// Button "BACK"
		TextButtonStyle tbStyleBack = new TextButtonStyle();
		tbStyleBack.up = skin.getDrawable("transBack");
		tbStyleBack.down = skin.getDrawable("transBackSmall");
		tbStyleBack.over = skin.getDrawable("transBackOver");
		tbStyleBack.font = bFont;
		skin.add("btnBack", tbStyleBack);
		TextButton tbBack = new TextButton("", tbStyleBack);
		tbBack.setPosition(game.getWidth() / 2 - game.getWidth() / 3 * 1.445f, game.getHeight() / 4.7f);
		tbBack.setSize(110, 45);
		tbBack.addListener(new ClickListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				super.touchUp(event, x, y, pointer, button);
				clickSound.play();
				game.changeScreen(AURISGame.LEVEL_SCREEN, ShopScreen.this);
			}
		});
		tbBack.addListener(new InputListener()
		{
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				super.enter(event, x, y, pointer, fromActor);
				hoverSound.play();
			}
		});

		// TEXTBUTTON DEFAULT:
		TextButtonStyle tbStyleBtnDefault = new TextButtonStyle();
		tbStyleBtnDefault.up = skin.getDrawable("btnDefault");
		tbStyleBtnDefault.down = skin.getDrawable("btnDefaultSmall");
		tbStyleBtnDefault.over = skin.getDrawable("btnDefaultOver");
		tbStyleBtnDefault.font = bFont;
		skin.add("btnDefault", tbStyleBack);
		TextButton btnDefault = new TextButton("", tbStyleBtnDefault);
		btnDefault.setPosition(game.getWidth() / 2 - game.getWidth() / 4.88f, game.getHeight() / 2 - game.getHeight() / 3.5f);
		btnDefault.setSize(80, 30);
		btnDefault.addListener(new ClickListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				super.touchUp(event, x, y, pointer, button);
				clickSound.play();
				
				player.setSkin(BallSkin.BALL_SKIN_ID_1);
				
				
			}
		});

		LabelStyle lblPlayerStyle = new LabelStyle();
		lblPlayerStyle.font = bFont;
		playerLbl = new Label("Player: --------", lblPlayerStyle);
		playerLbl.setSize(LABEL_WIDTH, LABEL_HEIGHT);
		playerLbl.setPosition(game.getWidth() - game.getWidth() / 4.2f, game.getHeight() / 2 - game.getHeight() / 50);

		creditsLbl = new Label("Credits: ---", lblPlayerStyle);
		creditsLbl.setSize(LABEL_WIDTH, LABEL_HEIGHT);
		creditsLbl.setPosition(playerLbl.getX(), playerLbl.getY() - 32);

		stage.addActor(shopItem1);
		stage.addActor(shopItem2);
		stage.addActor(shopItem3);

		stage.addActor(playerLbl);
		stage.addActor(creditsLbl);

		stage.addActor(tbBack);
		stage.addActor(btnDefault);
	}

	@Override
	protected void handleInput()
	{
		//		if (Gdx.input.isKeyPressed(Keys.DEL))
		//		{
		//			game.changeScreen(AURISGame.LEVEL_SCREEN, ShopScreen.this);
		//		}
	}

	private void updateShopItemButtons()
	{

		player = game.getPlayer();
		playerLbl.setText("Player: " + player.getName());
		creditsLbl.setText("Credits: " + player.getCredits());
		//		 shopItem1Style.imageUp = skin.getDrawable("");
		//		 shopItem2Style.imageUp = skin.getDrawable("");
		//		 shopItem3Style.imageUp = skin.getDrawable("");
	}

	@Override
	public void render(float delta)
	{
		super.render(delta);
//		System.out.println(player.getSkinID());
		runTime += delta;
		ballSkin.setId(player.getSkinID());
		if(player != null){
		currentFrame =  ballSkin.getSkin().getKeyFrame(runTime, true);
		batch.begin();
		batch.draw(currentFrame, game.getWidth() / 2 - game.getWidth() / 5.3f - 25, game.getHeight() / 2 - game.getHeight() / 6.3f, 100, 100);
		batch.end();
		}

	}

	@Override
	public void resize(int width, int height)
	{
		super.resize(width, height);
	}

	@Override
	public void show()
	{
		super.show();
		updateShopItemButtons();
	}

	@Override
	public void hide()
	{
		super.hide();
	}

	@Override
	public void pause()
	{
		super.pause();
	}

	@Override
	public void resume()
	{
		super.resume();
	}

	@Override
	public void dispose()
	{
		super.dispose();
	}
}
