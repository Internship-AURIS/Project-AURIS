package com.aau.auris.game.webcam;

import com.aau.auris.game.AURISGame;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;
import com.github.sarxos.webcam.WebcamResolution;

public class WebcamHandler implements WebcamListener
{

	private AURISGame game;
	private Webcam webcam;

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
		game.setInputImage(e.getImage());
	}

	@Override
	public void webcamOpen(WebcamEvent e)
	{}

	public void dispose()
	{
		if (webcam != null)
		{
			webcam.close();
		}
	}
}
