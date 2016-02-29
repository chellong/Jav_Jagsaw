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
		//ͼƬ·��
		String name = "Picture\\" + JagsawMainFrame.pictureId +".jpg";
		//��ȡͼ��
		ImageIcon imageIcon = new ImageIcon(name);
		Image image = imageIcon.getImage();
		
		g.drawImage(image,60,60,380,500,this);
	}
}
