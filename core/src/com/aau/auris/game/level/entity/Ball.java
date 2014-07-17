package com.aau.auris.game.level.entity;

import com.aau.auris.game.Asset.Asset;
import com.aau.auris.game.Asset.AssetLoader;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Ball extends Entity implements Asset
{
	public final float CIRCLE_RADIUS = 26.5f;
	private final float DENSITY = 5f;
	private final float FRICTION = 0.0f;
	private final float RESTITUTION = 0f;

	// Asset
	private Animation defaultAnimation;

	public Ball(World world, float posX, float posY)
	{
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(new Vector2(posX, posY));
		body = world.createBody(bodyDef);

		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(CIRCLE_RADIUS);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circleShape;
		fixtureDef.density = DENSITY;
		fixtureDef.friction = FRICTION;
		fixtureDef.restitution = RESTITUTION;
		body.createFixture(fixtureDef);
		body.setUserData(this);

		circleShape.dispose();

		loadAsset();
	}

	@Override
	public void loadAsset()
	{
		defaultAnimation = AssetLoader.parachuteBallAnimation1;
	}

	@Override
	public void disposeAsset()
	{}

	public TextureRegion getCurrentKeyFrame(float runTime)
	{
		return defaultAnimation.getKeyFrame(runTime);
	}

}
