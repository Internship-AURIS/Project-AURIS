package com.aau.auris.game.screens;

import java.util.Random;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.Asset.AssetLoader;
import com.aau.auris.game.data.Player;
import com.aau.auris.game.items.BallSkin;
import com.aau.auris.game.level.Level;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class VictoryScreen extends AbstractScreen {

	// Local
	private Skin skin;
	private Player player;
	private LabelStyle lblPlayerStyle;
	private Label lblPlayer;
	private Label lblScore;
	private Label lblCredits;
	private static final int POS_X=262;
	private float runTime;
	private SpriteBatch batch;
	private BallSkin ballSkin;

	// Assets
	private TextureAtlas levelButtons;
	private Sound clickSound;
	private Sound hoverSound;
	private Texture background;
	private Animation redCheerAnimation;

	public VictoryScreen(AURISGame game) {

		super(game);

	}

	@Override
	protected void initComponents() {

		TextureRegion backTextRegion = new TextureRegion(background, 848, 480);
		Image img = new Image(backTextRegion);
		stage.addActor(img);
		batch=new SpriteBatch();
		ballSkin = new BallSkin();
		
		// LABELS
		lblPlayerStyle = new LabelStyle();
		lblPlayerStyle.font = bFont;
		lblPlayer = new Label("Player: " ,
				lblPlayerStyle);
		lblPlayer.setPosition(POS_X, sHeight / 1.5f);

		lblScore = new Label("Score: " ,
				lblPlayerStyle);
		lblScore.setPosition(lblPlayer.getX(), lblPlayer.getY() - 50);

		lblCredits = new Label("Credits: ",
				lblPlayerStyle);
		lblCredits.setPosition(lblPlayer.getX(), lblPlayer.getY() - 100);

		// Button
		TextButtonStyle btnContinueStyle = new TextButtonStyle();
		btnContinueStyle.up = skin.getDrawable("btnCont");
		btnContinueStyle.down = skin.getDrawable("btnContSmall");
		btnContinueStyle.over = skin.getDrawable("btnContOver");
		btnContinueStyle.font = bFont;
		skin.add("btnContinue", btnContinueStyle);

		TextButton btnContinue = new TextButton("", btnContinueStyle);
		btnContinue.setSize(200, 80);
		btnContinue.setPosition(lblCredits.getX()-20, lblCredits.getY() - 110);
		btnContinue.addListener(new ClickListener() {

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				super.touchUp(event, x, y, pointer, button);
				clickSound.play();
				final int nextId = game.getLevel().getID() + 1;
				if (nextId <= Level.LEVEL_ID_9) {
					game.setLevel(AURISGame.getLevel(nextId));
				} else {
					game.changeScreen(AURISGame.LEVEL_SCREEN,
							VictoryScreen.this);
				}
				game.changeScreen(AURISGame.GAME_SCREEN, VictoryScreen.this);
			}

		});
		TextButtonStyle btnBackStyle = new TextButtonStyle();
		btnBackStyle.up = skin.getDrawable("btnBack");
		btnBackStyle.down = skin.getDrawable("btnBackSmall");
		btnBackStyle.over = skin.getDrawable("btnBackOver");
		btnBackStyle.font = bFont;
		skin.add("btnBack", btnBackStyle);
		
		TextButton btnBack = new TextButton("", btnBackStyle);
		btnBack.setSize(btnContinue.getWidth()-60,btnContinue.getHeight()-20);
		btnBack.setPosition(btnContinue.getX()+btnContinue.getWidth()+10, btnContinue.getY());
		System.out.println(btnContinue.getX());
		btnBack.addListener(new ClickListener() {

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				super.touchUp(event, x, y, pointer, button);
				clickSound.play();

				game.changeScreen(AURISGame.LEVEL_SCREEN, VictoryScreen.this);
			}

		});

		// STAGE
		stage.addActor(lblPlayer);
		stage.addActor(lblScore);
		stage.addActor(lblCredits);

		stage.addActor(btnContinue);
		stage.addActor(btnBack);
		stage.addListener(new InputListener()
		{

			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				
				if(keycode==Keys.ENTER){
					final int nextId = game.getLevel().getID() + 1;
					if (nextId <= Level.LEVEL_ID_9) {
						game.setLevel(AURISGame.getLevel(nextId));
					} else {
						game.changeScreen(AURISGame.LEVEL_SCREEN,
								VictoryScreen.this);
					}
					game.changeScreen(AURISGame.GAME_SCREEN, VictoryScreen.this);
				}
				if(keycode==Keys.ESCAPE){
					game.changeScreen(AURISGame.LEVEL_SCREEN, VictoryScreen.this);
				}
				return super.keyDown(event, keycode);
			}
		
		});
	}

	@Override
	public void show() {
		super.show();
		
		player = game.getPlayer();
		ballSkin.setId(player.getSkinID());
		if(player != null){
		lblPlayer.setText("Player: " + player.getName());

		lblScore.setText("Score: " + player.getScore());

		lblCredits.setText("Credits: " + player.getCredits() + " $");
		}
	}


	@Override
	public void loadAsset() {
		super.loadAsset();
		levelButtons = AssetLoader.levelButtons;
		clickSound = AssetLoader.clickSound;
		hoverSound = AssetLoader.hoverSound1;
		skin = new Skin(levelButtons);
		background=AssetLoader.background_Victory;
		redCheerAnimation=AssetLoader.redCheerAnimation;

	}

	@Override
	public void disposeAsset() {
		super.disposeAsset();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		runTime+=delta;
		if (player != null)
		{
			batch.begin();
			batch.draw(ballSkin.getCheerAnimation().getKeyFrame(runTime, true), 455, 200);
			batch.end();
		}
	}
	

}
