package com.aau.auris.game.imageprocessing;

import java.awt.image.BufferedImage;

import com.jhlabs.image.EdgeFilter;
import com.jhlabs.image.GrayscaleFilter;
import com.jhlabs.image.ThresholdFilter;

public class ImageFilter
{
	public static final int THRESHOLD_FILTER = 1;
	public static final int GRAYSCALE_FILTER = 2;
	public static final int EDGE_FILTER = 4;

	// Filter
	private GrayscaleFilter grayscaleFilter;
	private EdgeFilter edgeFilter;
	private ThresholdFilter thresholdFilter;

	// ThresholdFilter Settings
	public float lowThreshold = 0.0f;
	public float highThreshold = 0.0f;

	public ImageFilter()
	{
		this.grayscaleFilter = new GrayscaleFilter();
		this.edgeFilter = new EdgeFilter();
		this.thresholdFilter = new ThresholdFilter();
	}

	private BufferedImage getGrayScaleFilteredImage(BufferedImage img1, BufferedImage img2)
	{
		return (grayscaleFilter.filter(img1, img2));
	}

	private BufferedImage getEdgeFilteredImage(BufferedImage img1, BufferedImage img2)
	{
		return (edgeFilter.filter(img1, img2));
	}

	private BufferedImage getThresholdFilteredImage(BufferedImage img1, BufferedImage img2)
	{
		return (thresholdFilter.filter(img1, img2));
	}

	public BufferedImage modify(BufferedImage img, int filter)
	{
		BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		if (filter == (filter & GRAYSCALE_FILTER))
		{
			newImg = getGrayScaleFilteredImage(img, newImg);
		}
		if (filter == (filter & THRESHOLD_FILTER))
		{
			newImg = getThresholdFilteredImage(img, newImg);
		}
		if (filter == (filter & EDGE_FILTER))
		{
			newImg = getEdgeFilteredImage(img, newImg);
		}
		return newImg;
	}

	public void setLowThreshold(float low)
	{
		this.lowThreshold = low;
	}

	public void setHighThreshold(float high)
	{
		this.highThreshold = high;
	}

}
