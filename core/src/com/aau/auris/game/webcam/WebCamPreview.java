package com.aau.auris.game.webcam;

import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

public class WebCamPreview extends WebcamPanel {

	private static final long serialVersionUID = -384462278159931796L;

	public WebCamPreview(Webcam webcam) {
		super(webcam);
		setFPSDisplayed(true);
		setImageSizeDisplayed(true);
		
		JFrame window = new JFrame("Test webcam panel");
		window.add(this);
		window.setResizable(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
	}

}
