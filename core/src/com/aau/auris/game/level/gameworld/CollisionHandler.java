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

	/*
	 * With the maskBits and categoryBits set, it should objects only be possible
	 * to collide ball & obstacle
	 * 
	 */
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
		Entity e1 = (Entity) fixtureA.getBody().getUserData();
		Entity e2 = (Entity) fixtureB.getBody().getUserData();
		if (e1.isDeadly() || e2.isDeadly())
		{
			ball.die();
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
