package com.aau.auris.game;

import com.aau.auris.game.userdata.Player;
import com.aau.auris.game.userdata.UserData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LoginScreen implements Screen
{

	private AURIS_Game game;
	private UserData userdata;

	private Stage stage;
	private Skin skin;
	private Label lblName;
	private TextField txtName;
	private TextButton tbStart;

	public LoginScreen(AURIS_Game game)
	{
		this.game = game;
		this.userdata = this.game.getUserData();
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height)
	{}

	@Override
	public void show()
	{
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		skin = new Skin();
		Pixmap pixmap = new Pixmap(100, 100, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.GREEN);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		LabelStyle lStyle = new LabelStyle();
		lStyle.font = new BitmapFont();
		lStyle.fontColor = Color.WHITE;
		lStyle.background = skin.newDrawable("white", Color.BLACK);
		lblName = new Label("NAME", lStyle);
		lblName.setAlignment(Align.center);
		lblName.setSize(200, 30);
		lblName.setPosition(Gdx.graphics.getWidth() / 2 - lblName.getWidth() / 2, Gdx.graphics.getHeight() / 2 - lblName.getHeight() / 2 + 120);
		stage.addActor(lblName);

		BitmapFont bFont = new BitmapFont();
		bFont.scale(1);

		TextFieldStyle tfStyle = new TextFieldStyle();
		tfStyle.fontColor = Color.WHITE;
		tfStyle.font = bFont;
		tfStyle.background = skin.newDrawable("white", Color.BLACK);
		txtName = new TextField("", tfStyle);
		txtName.setSize(200, 30);
		txtName.setPosition(Gdx.graphics.getWidth() / 2 - txtName.getWidth() / 2, Gdx.graphics.getHeight() / 2 - txtName.getHeight() + 100);
		stage.addActor(txtName);

		skin.add("default", bFont);
		TextButtonStyle tbStyle = new TextButtonStyle();
		tbStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		tbStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		tbStyle.checked = skin.newDrawable("white", Color.BLUE);
		tbStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		tbStyle.font = skin.getFont("default");
		skin.add("default", tbStyle);

		tbStart = new TextButton("START", skin);
		tbStart.setSize(200, 60);
		tbStart.setPosition(Gdx.graphics.getWidth() / 2 - tbStart.getWidth() / 2, Gdx.graphics.getHeight() / 2 - tbStart.getHeight() / 2);
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

				if (userdata.containsPlayerName(inputName))//username exists, use this player
				{
					loginPlayer = userdata.getPlayerViaName(inputName);
				} else
				{//no player exists with the given inputName, create new one

					loginPlayer = new Player(inputName, 0);
					userdata.createPlayer(loginPlayer);
					System.out.println("new player created: " + loginPlayer);
				}
				game.setPlayer(loginPlayer);
			}
		});

		stage.addActor(tbStart);
	}

	@Override
	public void hide()
	{}

	@Override
	public void pause()
	{}

	@Override
	public void resume()
	{}

	@Override
	public void dispose()
	{
		stage.dispose();
		skin.dispose();
	}

}
