package com.aau.auris.game.imageprocessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import blobDetection.BlobDetection;
import blobDetection.EdgeDetection;

public class ImageProcessor
{
	private ImageFilter imageFilter;
	private BlobDetection bd;
	private EdgeDetection ed;
	private float thresoldValue = 0.38f;
	private int[] pixels;

	public ImageProcessor()
	{
		imageFilter = new ImageFilter();
	}

	public void setImage(BufferedImage input)
	{
		BufferedImage img = imageFilter.modify(input, ImageFilter.GRAYSCALE_FILTER | ImageFilter.THRESHOLD_FILTER);
		final int width = img.getWidth();
		final int height = img.getHeight();
		final int[] pixels = img.getRGB(0, 0, width, height, null, 0, width);
		try
		{
			ImageIO.write(img, "PNG", new File("test.png"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
}
