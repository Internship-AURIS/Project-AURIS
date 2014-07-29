package com.aau.auris.game.level.gameworld;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.level.Level;
import com.aau.auris.game.screens.GameScreen;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class CollisionHandler implements ContactListener
{
	private AURISGame game;
	private Level level;
	private GameScreen screen;
	private Ball ball;

	public CollisionHandler(AURISGame game, GameScreen screen)
	{
		this.game = game;
		this.screen = screen;
	}

	public void update()
	{
		this.level = game.getLevel();
		this.ball = game.getLevel().getBall();
	}

	@Override
	public void beginContact(Contact contact)
	{
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		Class<? extends Object> classA = fixtureA.getBody().getUserData().getClass();
		Class<? extends Object> classB = fixtureB.getBody().getUserData().getClass();

		if (classA == Home.class || classB == Home.class)
		{
			game.changeScreen(AURISGame.LEVEL_SCREEN, screen);
		}
		if ((classA == Goal.class || classB == Goal.class) && !ball.isDead())
		{
			level.finished();
			game.changeScreen(AURISGame.VICTORY_SCREEN, screen);
		}
		if (classA == BorderLine.class || classB == BorderLine.class)
		{
			ball.die();
		}
		if (classA == Obstacle.class || classB == Obstacle.class)
		{
			game.getPlayer().addPoints(1);
		}
	}

	@Override
	public void endContact(Contact contact)
	{}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold)
	{}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse)
	{}

}
