package com.aau.auris.game.items;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MenuBall {

	private float x, y;
	private Animation animation;

	public MenuBall(float x, float y, Animation animation) {
		this.x = x;
		this.y = y;
		this.animation = animation;
	}

	public TextureRegion getKeyFrame(float runTime) {
		return animation.getKeyFrame(runTime);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public Animation getAnimation()
	{
		return animation;
	}
}
