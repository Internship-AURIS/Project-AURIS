package com.aau.auris.game.screens;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.Asset.AssetLoader;
import com.aau.auris.game.data.Player;
import com.aau.auris.game.data.UserData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LoginScreen extends AbstractScreen
{
	// Assets
	private TextureAtlas menuButtons;
	private Texture background;
	private Sound clickSound;
	private Sound hoverSound;

	// Other Variables
	private UserData userdata;

	public LoginScreen(AURISGame game)
	{
		super(game);
	}

	@Override
	public void loadAsset()
	{
		super.loadAsset();
		menuButtons = AssetLoader.menu_buttons;
		background = AssetLoader.menu_background_blank;
		clickSound = AssetLoader.clickSound;
		hoverSound = AssetLoader.hoverSound1;
	}

	@Override
	public void disposeAsset()
	{
		super.disposeAsset();
	}

	@Override
	protected void initComponents()
	{
		this.userdata = this.game.getUserData();

		skin = new Skin(menuButtons);

		TextureRegion backTextRegion = new TextureRegion(background, 848, 480);
		Image img = new Image(backTextRegion);
		stage.addActor(img);

		skin.add("default", bFont);

		// Label "NAME"
		LabelStyle lStyle = new LabelStyle();
		lStyle.font = bFont;
		lStyle.fontColor = Color.WHITE;
		lStyle.background = skin.getDrawable("btnName");
		Label lblName = new Label("", lStyle);
		lblName.setAlignment(Align.center);
		lblName.setSize(200, 80);
		lblName.setPosition(Gdx.graphics.getWidth() / 2 - lblName.getWidth() / 2, Gdx.graphics.getHeight() / 2 - lblName.getHeight() / 2 + 120);
		stage.addActor(lblName);

		// TextField to enter PlayerName
		TextFieldStyle tfStyle = new TextFieldStyle();
		tfStyle.fontColor = Color.WHITE;
		tfStyle.font = bFont;
		tfStyle.cursor = skin.getDrawable("bestCursor");
		final TextField txtName = new TextField("", tfStyle);
		txtName.setSize(160, 60);
		txtName.setPosition(Gdx.graphics.getWidth() / 2 - txtName.getWidth() / 2, Gdx.graphics.getHeight() / 2 - txtName.getHeight() + 60);
		stage.addActor(txtName);

		// TextButton "BACK"
		TextButtonStyle tbStyleBack = new TextButtonStyle();
		tbStyleBack.up = skin.getDrawable("btnBack");
		tbStyleBack.down = skin.getDrawable("btnBackSmall");
		tbStyleBack.over = skin.getDrawable("btnBackOver");
		tbStyleBack.font = skin.getFont("default");
		skin.add("default", tbStyleBack);
		TextButton tbBack = new TextButton("", tbStyleBack);
		tbBack.setSize(200, 80);
		tbBack.setPosition(10, 10);
		tbBack.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				clickSound.play();
				game.changeScreen(AURISGame.MENU_SCREEN, LoginScreen.this);
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

		// TextButton "START"
		TextButtonStyle tbStyleStart = new TextButtonStyle();
		tbStyleStart.up = skin.getDrawable("btnLoginStart");
		tbStyleStart.down = skin.getDrawable("btnLoginStartSmall");
		tbStyleStart.over = skin.getDrawable("btnLoginStartOver");
		tbStyleStart.font = skin.getFont("default");
		skin.add("default", tbStyleStart);
		TextButton tbStart = new TextButton("", tbStyleStart);
		tbStart.setSize(200, 80);
		tbStart.setPosition(Gdx.graphics.getWidth() / 2 - tbStart.getWidth() / 2, Gdx.graphics.getHeight() / 2 - tbStart.getHeight() / 3 * 4);
		tbStart.addListener(new ClickListener()
		{
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				super.touchUp(event, x, y, pointer, button);
				String inputName = txtName.getText();
				if (inputName == null || inputName.length() == 0)
				{
					System.out.println("...invalid player name!!! try again...");
					event.cancel();
					return;
				}
				/*
				 * valid input string
				 */
				Player loginPlayer = null;

				// UserName exists, use this Player
				if (userdata.containsPlayerName(inputName))
				{
					loginPlayer = userdata.getPlayerViaName(inputName);
				} else
				{
					// no player exists with the given inputName, create new  one
					loginPlayer = new Player(inputName, 0, null);
					userdata.createPlayer(loginPlayer);
					System.out.println("new player created: " + loginPlayer);
				}
				game.setPlayer(loginPlayer);
				game.changeScreen(AURISGame.LEVEL_SCREEN, LoginScreen.this);
			}
		});

		stage.addActor(tbBack);
		stage.addActor(tbStart);
	}

	@Override
	public void render(float delta)
	{
		super.render(delta);
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
		stage.dispose();
		skin.dispose();
	}
}
