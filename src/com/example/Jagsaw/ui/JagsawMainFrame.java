package com.example.Jagsaw.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * 主窗口
 * @author 北飞的候鸟
 *
 */
public class JagsawMainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3115166383693274789L;
	
	private String[] items = {"图片一","图片二"};
	
	/**
	 * 图片id
	 */
	public static int pictureId = 1;
	/**
	 * 步数
	 */
	public static int stepNum = 0;
	/**
	 * 数字提示
	 */
	private JRadioButton addNumInfo;
	/**
	 * 清除提示
	 */
	private JRadioButton clearNumInfo;
	/**
	 * 游戏区
	 */
	private PictureCanvas pictureCanvas;
	/**
	 * 图片预览
	 */
	private PicturePreview picturePreview;
	/**
	 * 选择区
	 */
	private JComboBox<String> box;
	/**
	 * 图片的名字
	 */
	private JTextField name;
	/**
	 * 步数
	 */
	public static JTextField step;

	private JButton button;
	
	
	/**
	 * 构造方法
	 */
	public JagsawMainFrame() {
		
		//初始化
		this.init();
		//添加组件
		addComponent();
		
		addPreviewImage();
		
		addActionListener();
	}

	private void addActionListener() {
		
		/*
		 * 数字提示
		 */
		this.addNumInfo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pictureCanvas.reLoadPictureAndNum();
			}
		});
		
		this.clearNumInfo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pictureCanvas.reLoadPictureAndClear();
			}
		});
		
		this.box.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				//更新预览区
				int itemNum = box.getSelectedIndex();
				//更新预览区图片
				JagsawMainFrame.pictureId = itemNum + 1;
				picturePreview.repaint();
				//跟新拼图区
				pictureCanvas.reLoadPictureAndClear();
				//更新游戏状态区
				name.setText("图片名称："+box.getSelectedItem());
				JagsawMainFrame.stepNum = 0;
				step.setText("步数：" + JagsawMainFrame.stepNum );
				//更新按钮区
				clearNumInfo.isSelected();
				
			}
		});
		
		this.button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//清零
				JagsawMainFrame.stepNum = 0;
				//更新
				step.setText("步数：" + JagsawMainFrame.stepNum);
				//打乱顺序
				pictureCanvas.start();
			}
		});
	}

	private void addPreviewImage() {
	
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		
		pictureCanvas = new PictureCanvas();
		pictureCanvas.setBorder(new TitledBorder("拼图区"));
		
		
		picturePreview = new PicturePreview();
		picturePreview.setBorder(new TitledBorder("预览区"));
		
		panel.add(pictureCanvas);
		panel.add(picturePreview);
		
		this.add(panel,BorderLayout.CENTER);
	}

	/*
	 * 添加组件
	 */
	private void addComponent() {
		
		//在主界面上方显示的面板，包含按钮去与游戏状态区
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		
		//游戏按钮区的面板
		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(new TitledBorder("按钮区"));
		leftPanel.setBackground(Color.lightGray);//背景色
		addNumInfo = new JRadioButton("数字提示",false);
		
		
		clearNumInfo = new JRadioButton("清除提示",true);
		//添加按钮组
		ButtonGroup group = new ButtonGroup();
		group.add(addNumInfo);
		group.add(clearNumInfo);
		box = new JComboBox<>(items);
		button = new JButton("开始");
		//添加到区域
		leftPanel.add(addNumInfo);
		leftPanel.add(clearNumInfo);
		leftPanel.add(new JLabel("               选择图片"));
		leftPanel.add(box);
		leftPanel.add(button);
		
		
		panel.add(leftPanel,BorderLayout.WEST);
		//游戏状态区的面板
		JPanel rightPanel = new JPanel();
		rightPanel.setBorder(new TitledBorder("状态区"));
		rightPanel.setBackground(Color.lightGray);//背景色
		rightPanel.setLayout(new GridLayout(1,2));
		
		name = new JTextField("图片名称：");
		name.setEditable(false);
		step = new JTextField("步数：");
		step.setEditable(false);
		
		//添加到容器
		rightPanel.add(name,BorderLayout.WEST);
		rightPanel.add(step,BorderLayout.EAST);
		
		panel.add(rightPanel,BorderLayout.EAST);
		//设置panel在主界面的上方显示
		this.add(panel,BorderLayout.NORTH);
	}

	/*
	 * 界面初始化
	 */
	private void init() {
		
		//设置标题 
		this.setTitle("Jagsaw");
		//设置窗口大小
		this.setSize(1000, 720);
		//设置显示位置
		this.setLocation(150, 10);
		//设置为固定大小
		this.setResizable(false);
		//设置窗口的默认操作
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
