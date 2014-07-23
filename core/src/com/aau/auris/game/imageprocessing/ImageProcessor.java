package com.aau.auris.game.imageprocessing;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import blobDetection.BlobDetection;
import blobDetection.EdgeDetection;

import com.aau.auris.game.AURISGame;
import com.badlogic.gdx.Gdx;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;

public class ImageProcessor implements WebcamListener
{
	private AURISGame game;
	private ImageFilter imageFilter;

	private BlobDetection bd;
	private EdgeDetection ed;
	private float thresoldValue = 0.38f;

	public ImageProcessor(AURISGame game)
	{
		this.game = game;
		imageFilter = new ImageFilter();
	}

	public void setImage(BufferedImage input)
	{
		BufferedImage img = imageFilter.modify(input, ImageFilter.GRAYSCALE_FILTER | ImageFilter.THRESHOLD_FILTER);
		final int width = img.getWidth();
		final int height = img.getHeight();
		final int[] pixels = img.getRGB(0, 0, width, height, null, 0, width);
		//		try
		//		{
		//			ImageIO.write(img, "PNG", new File("test.png"));
		//		} catch (IOException e)
		//		{
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		bd = new BlobDetection(width, height);
		bd.setPosDiscrimination(false);
		bd.blobWidthMin = 1;
		bd.blobHeightMin = 1;
		bd.setImage(pixels);
		bd.computeTriangles();
		bd.computeMesh();
		bd.computeBlobs(pixels);
		System.out.println(bd.getBlobNb());

	}

	private BufferedImage imgTmp;

	private void process(BufferedImage input)
	{
		imgTmp = imageFilter.modify(input, ImageFilter.GRAYSCALE_FILTER | ImageFilter.THRESHOLD_FILTER | ImageFilter.INVERT_FILTER);
		final int width = input.getWidth();
		final int height = input.getHeight();

		int[] pixels = imgTmp.getRGB(0, 0, width, height, null, 0, width);
		System.out.println("processing image: " + pixels.length);//TODO: debugging
		bd = new BlobDetection(width, height);
		bd.setPosDiscrimination(false);

		try
		{
			ImageIO.write(imgTmp, "PNG", Gdx.files.internal("asset/inputImage").file());
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
