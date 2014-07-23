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

	private BlobDetection bd;
	private float thresoldValue = 0.38f;

	public ImageProcessor(AURISGame game)
	{
		this.game = game;
		imageFilter = new ImageFilter();
	}

	private BufferedImage imgTmp;

	private void process(BufferedImage input)
	{
		imgTmp = imageFilter.modify(input, ImageFilter.GRAYSCALE_FILTER | ImageFilter.THRESHOLD_FILTER | ImageFilter.INVERT_FILTER);
		final int width = input.getWidth();
		final int height = input.getHeight();

		final int[] pixels = imgTmp.getRGB(0, 0, width, height, null, 0, width);
		bd = new BlobDetection(width, height);
		bd.setPosDiscrimination(true);
		bd.computeBlobs(pixels);
		// TODO: implement Blob-processing

		ArrayList<Blob> blobs = new ArrayList<Blob>();
		for(int i = 0; i < bd.getBlobNb(); i++)
		{
			blobs.add(bd.getBlob(i));
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
