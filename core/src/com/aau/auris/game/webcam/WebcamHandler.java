package com.aau.auris.game.webcam;

import java.awt.image.BufferedImage;

import com.aau.auris.game.AURISGame;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;
import com.github.sarxos.webcam.WebcamResolution;

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
		if (webcam != null)
		{
			webcam.addWebcamListener(this);
			webcam.setViewSize(WebcamResolution.QVGA.getSize());
			webcam.open();
		}
	}

	@Override
	public void webcamClosed(WebcamEvent e)
	{
		if (webcam != null)
		{
			webcam.close();
		}
	}

	@Override
	public void webcamDisposed(WebcamEvent e)
	{}

	@Override
	public void webcamImageObtained(WebcamEvent e)
	{
		System.out.println("image obtained");
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
}
