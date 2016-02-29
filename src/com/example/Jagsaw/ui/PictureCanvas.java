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

	// 创建12个方格
	private Cell[] cell = new Cell[12];
	/**
	 * 是否有监听
	 */
	private boolean hasActionListener = false;

	private Rectangle nullCell;

	public PictureCanvas() {
		// 布局方式
		this.setLayout(null);
		// 行
		for (int i = 0; i < 4; i++) {
			// 列
			for (int j = 0; j < 3; j++) {
				// 加载图片
				int num = i * 3 + j + 1;
				ImageIcon icon = new ImageIcon("Picture\\" + JagsawMainFrame.pictureId + "_" + num + ".jpg");
				// 创建方格
				cell[i * 3 + j] = new Cell(icon);
				// 指定显示的位置
				cell[i * 3 + j].setLocation(60 + j * 125, 60 + i * 125);
				// 添加到拼图区显示
				this.add(cell[i * 3 + j]);
			}
		}
		this.remove(cell[11]);
		nullCell = new Rectangle(310, 435, 125, 125);
	}

	// 重新加载图片并加载数组信息
	public void reLoadPictureAndNum() {

		for (int i = 0; i < 4; i++) {
			// 列
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

	// 重新加载图片并清空提示
	public void reLoadPictureAndClear() {

		for (int i = 0; i < 4; i++) {
			// 列
			for (int j = 0; j < 3; j++) {

				int num = i * 3 + j + 1;
				ImageIcon icon = new ImageIcon("Picture\\" + JagsawMainFrame.pictureId + "_" + num + ".jpg");
				cell[i * 3 + j].setIcon(icon);
				cell[i * 3 + j].setText("");
			}
		}

	}

	public void start() {
		// 如果没有给方格添加监听
		if (!hasActionListener) {
			for (int i = 0; i < 11; i++) {
				cell[i].addMouseListener(this);
			}
			hasActionListener = true;
		}
		// 随机
		while (cell[0].getBounds().x <= 185 && cell[0].getBounds().y <= 185) {
			// 获取到方格的位置
			int nullX = nullCell.getBounds().x;
			int nullY = nullCell.getBounds().y;

			// 和其他的互换位置随机数0-3对应上下左右转换
			int direction = (int) (Math.random() * 4);
			switch (direction) {
				case 0:
					//左
					nullX -= 125;
					cellMove(nullX,nullY,"RIGHT");
					break;
				case 1:
					//右
					nullX += 125;
					cellMove(nullX,nullY,"LEFT");
					break;
				case 2:
					//上
					nullY -= 125;
					cellMove(nullX,nullY,"DOWN");
					break;
				case 3:
					//下
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
				//方格移动
				cell[i].move(direction);
				//空方格坐标
				nullCell.setLocation(nullX, nullY);
				//交换位置后循环结束
				break;
			}
		}
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {

	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		
		//获取当前点击的方格
		Cell click = (Cell) e.getSource();
		//获取下x，y坐标
		int clickX = click.getBounds().x;
		int clickY = click.getBounds().y;
		//获取空白的下x，y坐标
		int nullCellX = nullCell.getBounds().x;
		int nullCellY = nullCell.getBounds().y;
		//比较如果满足则互换
		//点击的为空方格的下面
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
		//拼图区重新绘制
		repaint();
		
		//更新步数
		JagsawMainFrame.stepNum ++;
		JagsawMainFrame.step.setText("步数：" + JagsawMainFrame.stepNum);
		
		if(isFinish()){
			JOptionPane.showMessageDialog(this, "恭喜你完成");
			//撤销小方格的监听事件
			for (int i = 0; i < 11; i++) {
				cell[i].removeMouseListener(this);
			}
			hasActionListener = false;
		}
	}
	
	private boolean isFinish() {
		// 根据坐标判断是否完成
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
