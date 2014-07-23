package com.aau.auris.game.webcam;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamListener;
import com.github.sarxos.webcam.WebcamResolution;

public class WebcamHandler
{
	private Webcam webcam;

	public WebcamHandler(WebcamListener listener)
	{
		this.webcam = Webcam.getDefault();
		if (webcam != null)
		{
			webcam.addWebcamListener(listener);
			webcam.setViewSize(WebcamResolution.QVGA.getSize());
			webcam.open(true);
		}
	}

	public void dispose()
	{
		webcam.close();
	}
}
