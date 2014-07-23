package com.aau.auris.game.imageprocessing;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import com.aau.auris.game.AURISGame;
import com.jhlabs.image.EdgeFilter;
import com.jhlabs.image.GrayscaleFilter;
import com.jhlabs.image.InvertFilter;
import com.jhlabs.image.ThresholdFilter;

public class ImageFilter
{
	public static final int THRESHOLD_FILTER = 1;
	public static final int GRAYSCALE_FILTER = 2;
	public static final int EDGE_FILTER = 4;
	public static final int INVERT_FILTER = 8;

	// Filter
	private GrayscaleFilter grayscaleFilter;
	private EdgeFilter edgeFilter;
	private ThresholdFilter thresholdFilter;
	private InvertFilter invertFilter;

	public ImageFilter(AURISGame game)
	{
		this.grayscaleFilter = new GrayscaleFilter();
		this.edgeFilter = new EdgeFilter();
		this.thresholdFilter = new ThresholdFilter();
		this.invertFilter = new InvertFilter();

		// Threshold Filter Settings
		thresholdFilter.setLowerThreshold(game.getPreferences().getLowerThreshold());
		thresholdFilter.setUpperThreshold(game.getPreferences().getUpperThreshold());
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

	private BufferedImage getInvertedColorImage(BufferedImage img1, BufferedImage img2)
	{
		return (invertFilter.filter(img1, img2));
	}

	public BufferedImage modify(BufferedImage img, int filter)
	{
		BufferedImage newImg = copyImage(img);
		if (GRAYSCALE_FILTER == (filter & GRAYSCALE_FILTER))
		{
			getGrayScaleFilteredImage(newImg, newImg);
		}
		if (THRESHOLD_FILTER == (filter & THRESHOLD_FILTER))
		{
			getThresholdFilteredImage(newImg, newImg);
		}
		if (EDGE_FILTER == (filter & EDGE_FILTER))
		{
			getEdgeFilteredImage(newImg, newImg);
		}
		if (INVERT_FILTER == (filter & INVERT_FILTER))
		{
			getInvertedColorImage(newImg, newImg);
		}
		return newImg;
	}

	private BufferedImage copyImage(BufferedImage bi)
	{
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
}
