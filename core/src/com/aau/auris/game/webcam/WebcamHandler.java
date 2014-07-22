package com.aau.auris.game.webcam;

import java.awt.image.BufferedImage;

import com.aau.auris.game.AURISGame;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;

public class WebcamHandler implements WebcamListener
{
	private Webcam webcam;
	private boolean shouldUpdate = false;
	private BufferedImage inputImage;

	private AURISGame game;

	public WebcamHandler(AURISGame game)
	{
		this.game = game;
		this.webcam = Webcam.getDefault();
		if(webcam != null){
		webcam.addWebcamListener(this);
		start();
		}
	}

	@Override
	public void webcamClosed(WebcamEvent e)
	{
		webcam.close();
	}

	@Override
	public void webcamDisposed(WebcamEvent e)
	{}

	@Override
	public void webcamImageObtained(WebcamEvent e)
	{
		inputImage = e.getImage();
		if (shouldUpdate)
		{
			game.update();
		}
	}

	@Override
	public void webcamOpen(WebcamEvent e)
	{}

	public BufferedImage getInputImage()
	{
		return inputImage;
	}

	public void setUpdate(boolean enabled)
	{
		this.shouldUpdate = enabled;
	}

	public void start()
	{
		if (webcam != null)
		{
			webcam.open();
		}
	}
}
