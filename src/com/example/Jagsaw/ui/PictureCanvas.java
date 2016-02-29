package com.example.Jagsaw.ui;

import java.awt.Rectangle;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PictureCanvas extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5893463982932179381L;

	// ����12������
	private Cell[] cell = new Cell[12];
	/**
	 * �Ƿ��м���
	 */
	private boolean hasActionListener = false;

	private Rectangle nullCell;

	public PictureCanvas() {
		// ���ַ�ʽ
		this.setLayout(null);
		// ��
		for (int i = 0; i < 4; i++) {
			// ��
			for (int j = 0; j < 3; j++) {
				// ����ͼƬ
				int num = i * 3 + j + 1;
				ImageIcon icon = new ImageIcon("Picture\\" + JagsawMainFrame.pictureId + "_" + num + ".jpg");
				// ��������
				cell[i * 3 + j] = new Cell(icon);
				// ָ����ʾ��λ��
				cell[i * 3 + j].setLocation(60 + j * 125, 60 + i * 125);
				// ��ӵ�ƴͼ����ʾ
				this.add(cell[i * 3 + j]);
			}
		}
		this.remove(cell[11]);
		nullCell = new Rectangle(310, 435, 125, 125);
	}

	// ���¼���ͼƬ������������Ϣ
	public void reLoadPictureAndNum() {

		for (int i = 0; i < 4; i++) {
			// ��
			for (int j = 0; j < 3; j++) {

				int num = i * 3 + j + 1;
				ImageIcon icon = new ImageIcon("Picture\\" + JagsawMainFrame.pictureId + "_" + num + ".jpg");
				cell[i * 3 + j].setIcon(icon);
				cell[i * 3 + j].setText("" + (i * 3 + j + 1));
				cell[i * 3 + j].setVerticalTextPosition(this.getY() / 2);
				cell[i * 3 + j].setHorizontalTextPosition(this.getX() / 2);
			}
		}

	}

	// ���¼���ͼƬ�������ʾ
	public void reLoadPictureAndClear() {

		for (int i = 0; i < 4; i++) {
			// ��
			for (int j = 0; j < 3; j++) {

				int num = i * 3 + j + 1;
				ImageIcon icon = new ImageIcon("Picture\\" + JagsawMainFrame.pictureId + "_" + num + ".jpg");
				cell[i * 3 + j].setIcon(icon);
				cell[i * 3 + j].setText("");
			}
		}

	}

	public void start() {
		// ���û�и�������Ӽ���
		if (!hasActionListener) {
			for (int i = 0; i < 11; i++) {
				cell[i].addMouseListener(this);
			}
			hasActionListener = true;
		}
		// ���
		while (cell[0].getBounds().x <= 185 && cell[0].getBounds().y <= 185) {
			// ��ȡ�������λ��
			int nullX = nullCell.getBounds().x;
			int nullY = nullCell.getBounds().y;

			// �������Ļ���λ�������0-3��Ӧ��������ת��
			int direction = (int) (Math.random() * 4);
			switch (direction) {
				case 0:
					//��
					nullX -= 125;
					cellMove(nullX,nullY,"RIGHT");
					break;
				case 1:
					//��
					nullX += 125;
					cellMove(nullX,nullY,"LEFT");
					break;
				case 2:
					//��
					nullY -= 125;
					cellMove(nullX,nullY,"DOWN");
					break;
				case 3:
					//��
					nullY +=125;
					cellMove(nullX,nullY,"UP");
					break;
				default:
					break;
			}

		}
	}
	
	/**
	 * 
	 * @param nullX
	 * @param nullY
	 * @param direction
	 */

	private void cellMove(int nullX, int nullY, String direction) {
		for (int i = 0; i < 11; i++) {
			if(cell[i].getBounds().x == nullX && cell[i].getBounds().y == nullY){
				//�����ƶ�
				cell[i].move(direction);
				//�շ�������
				nullCell.setLocation(nullX, nullY);
				//����λ�ú�ѭ������
				break;
			}
		}
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {

	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		
		//��ȡ��ǰ����ķ���
		Cell click = (Cell) e.getSource();
		//��ȡ��x��y����
		int clickX = click.getBounds().x;
		int clickY = click.getBounds().y;
		//��ȡ�հ׵���x��y����
		int nullCellX = nullCell.getBounds().x;
		int nullCellY = nullCell.getBounds().y;
		//�Ƚ���������򻥻�
		//�����Ϊ�շ��������
		if(clickX == nullCellX && clickY - nullCellY == 125){
			click.move("UP");
		}else if(clickX == nullCellX && clickY - nullCellY == -125){
			click.move("DOWN");
		}else if(clickX - nullCellX == 125&& clickY == nullCellY){
			click.move("LEFT");
		}else if(clickX - nullCellX == -125&& clickY == nullCellY){
			click.move("RIGHT");
		}else{
			return;
		}
		nullCell.setLocation(clickX, clickY);
		//ƴͼ�����»���
		repaint();
		
		//���²���
		JagsawMainFrame.stepNum ++;
		JagsawMainFrame.step.setText("������" + JagsawMainFrame.stepNum);
		
		if(isFinish()){
			JOptionPane.showMessageDialog(this, "��ϲ�����");
			//����С����ļ����¼�
			for (int i = 0; i < 11; i++) {
				cell[i].removeMouseListener(this);
			}
			hasActionListener = false;
		}
	}
	
	private boolean isFinish() {
		// ���������ж��Ƿ����
		for (int i = 0; i < cell.length; i++) {
			int x = cell[i].getBounds().x;
			int y = cell[i].getBounds().y;
			if ((y - 60) / 125 * 3 + (x - 60) / 150 != i) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {

	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {

	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {

	}

}
