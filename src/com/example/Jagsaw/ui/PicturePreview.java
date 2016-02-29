package com.example.Jagsaw.ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PicturePreview extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7740148182143228008L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Í¼Æ¬Â·¾¶
		String name = "Picture\\" + JagsawMainFrame.pictureId +".jpg";
		//»ñÈ¡Í¼Ïñ
		ImageIcon imageIcon = new ImageIcon(name);
		Image image = imageIcon.getImage();
		
		g.drawImage(image,60,60,380,500,this);
	}
}
