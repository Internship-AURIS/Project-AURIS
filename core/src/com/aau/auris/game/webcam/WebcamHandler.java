package com.aau.auris.game.webcam;

import com.aau.auris.game.imageprocessing.ImageProcessor;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

public class WebcamHandler
{
	private Webcam webcam;

	public WebcamHandler(ImageProcessor listener)
	{
		this.webcam = Webcam.getDefault();
		if (webcam != null)
		{
			listener.setBlobDetector(webcam.getViewSize().width, webcam.getViewSize().height);
			webcam.addWebcamListener(listener);
			webcam.setViewSize(WebcamResolution.QVGA.getSize());
			webcam.open(true);
		}
	}

	public void dispose()
	{
		if (webcam != null)
		{
			webcam.close();
		}
	}

	public Webcam getWebcam()
	{
		return webcam;
	}
}
