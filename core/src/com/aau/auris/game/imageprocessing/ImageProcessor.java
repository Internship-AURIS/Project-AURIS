package com.aau.auris.game.imageprocessing;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import blobDetection.Blob;
import blobDetection.BlobDetection;

import com.aau.auris.game.AURISGame;
import com.aau.auris.game.screens.GameScreen;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;

public class ImageProcessor implements WebcamListener
{
	private AURISGame game;
	private ImageFilter imageFilter;

	private final float minBlobHeight, minBlobWidth;
	private BlobDetection bd;

	public ImageProcessor(AURISGame game)
	{
		this.game = game;
		this.imageFilter = new ImageFilter(game);
		this.minBlobHeight = game.getPreferences().getMinBlobHeight();
		this.minBlobWidth = game.getPreferences().getMinBlobWidth();
	}

	public void setBlobDetector(int width, int height)
	{
		this.bd = new BlobDetection(width, height);
		bd.setPosDiscrimination(false);
		bd.setThreshold(0.5f);
	}

	private BufferedImage imgTmp;

	private void process(BufferedImage input)
	{
		imgTmp = imageFilter.modify(input, ImageFilter.GRAYSCALE_FILTER | ImageFilter.THRESHOLD_FILTER);
		final int width = input.getWidth();
		final int height = input.getHeight();

		final int[] pixels = imgTmp.getRGB(0, 0, width, height, null, 0, width);
		bd.computeBlobs(pixels);

		ArrayList<Blob> blobs = new ArrayList<Blob>();
		Blob b;
		for (int i = 0; i < bd.getBlobNb(); i++)
		{
			b = bd.getBlob(i);
			if (b.w > minBlobWidth && b.h > minBlobHeight)
			{
				blobs.add(bd.getBlob(i));
			}
		}
		if (game.getScreen().getClass() == GameScreen.class)
		{
			game.getGameScreen().updateGame(blobs);
		}
	}

	@Override
	public void webcamClosed(WebcamEvent e)
	{}

	@Override
	public void webcamDisposed(WebcamEvent e)
	{}

	@Override
	public void webcamImageObtained(WebcamEvent e)
	{
		process(e.getImage());
	}

	@Override
	public void webcamOpen(WebcamEvent e)
	{}
}
