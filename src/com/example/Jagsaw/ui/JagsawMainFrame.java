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
 * ������
 * @author ���ɵĺ���
 *
 */
public class JagsawMainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3115166383693274789L;
	
	private String[] items = {"ͼƬһ","ͼƬ��"};
	
	/**
	 * ͼƬid
	 */
	public static int pictureId = 1;
	/**
	 * ����
	 */
	public static int stepNum = 0;
	/**
	 * ������ʾ
	 */
	private JRadioButton addNumInfo;
	/**
	 * �����ʾ
	 */
	private JRadioButton clearNumInfo;
	/**
	 * ��Ϸ��
	 */
	private PictureCanvas pictureCanvas;
	/**
	 * ͼƬԤ��
	 */
	private PicturePreview picturePreview;
	/**
	 * ѡ����
	 */
	private JComboBox<String> box;
	/**
	 * ͼƬ������
	 */
	private JTextField name;
	/**
	 * ����
	 */
	public static JTextField step;

	private JButton button;
	
	
	/**
	 * ���췽��
	 */
	public JagsawMainFrame() {
		
		//��ʼ��
		this.init();
		//������
		addComponent();
		
		addPreviewImage();
		
		addActionListener();
	}

	private void addActionListener() {
		
		/*
		 * ������ʾ
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
				//����Ԥ����
				int itemNum = box.getSelectedIndex();
				//����Ԥ����ͼƬ
				JagsawMainFrame.pictureId = itemNum + 1;
				picturePreview.repaint();
				//����ƴͼ��
				pictureCanvas.reLoadPictureAndClear();
				//������Ϸ״̬��
				name.setText("ͼƬ���ƣ�"+box.getSelectedItem());
				JagsawMainFrame.stepNum = 0;
				step.setText("������" + JagsawMainFrame.stepNum );
				//���°�ť��
				clearNumInfo.isSelected();
				
			}
		});
		
		this.button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//����
				JagsawMainFrame.stepNum = 0;
				//����
				step.setText("������" + JagsawMainFrame.stepNum);
				//����˳��
				pictureCanvas.start();
			}
		});
	}

	private void addPreviewImage() {
	
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		
		pictureCanvas = new PictureCanvas();
		pictureCanvas.setBorder(new TitledBorder("ƴͼ��"));
		
		
		picturePreview = new PicturePreview();
		picturePreview.setBorder(new TitledBorder("Ԥ����"));
		
		panel.add(pictureCanvas);
		panel.add(picturePreview);
		
		this.add(panel,BorderLayout.CENTER);
	}

	/*
	 * ������
	 */
	private void addComponent() {
		
		//���������Ϸ���ʾ����壬������ťȥ����Ϸ״̬��
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		
		//��Ϸ��ť�������
		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(new TitledBorder("��ť��"));
		leftPanel.setBackground(Color.lightGray);//����ɫ
		addNumInfo = new JRadioButton("������ʾ",false);
		
		
		clearNumInfo = new JRadioButton("�����ʾ",true);
		//��Ӱ�ť��
		ButtonGroup group = new ButtonGroup();
		group.add(addNumInfo);
		group.add(clearNumInfo);
		box = new JComboBox<>(items);
		button = new JButton("��ʼ");
		//��ӵ�����
		leftPanel.add(addNumInfo);
		leftPanel.add(clearNumInfo);
		leftPanel.add(new JLabel("               ѡ��ͼƬ"));
		leftPanel.add(box);
		leftPanel.add(button);
		
		
		panel.add(leftPanel,BorderLayout.WEST);
		//��Ϸ״̬�������
		JPanel rightPanel = new JPanel();
		rightPanel.setBorder(new TitledBorder("״̬��"));
		rightPanel.setBackground(Color.lightGray);//����ɫ
		rightPanel.setLayout(new GridLayout(1,2));
		
		name = new JTextField("ͼƬ���ƣ�");
		name.setEditable(false);
		step = new JTextField("������");
		step.setEditable(false);
		
		//��ӵ�����
		rightPanel.add(name,BorderLayout.WEST);
		rightPanel.add(step,BorderLayout.EAST);
		
		panel.add(rightPanel,BorderLayout.EAST);
		//����panel����������Ϸ���ʾ
		this.add(panel,BorderLayout.NORTH);
	}

	/*
	 * �����ʼ��
	 */
	private void init() {
		
		//���ñ��� 
		this.setTitle("Jagsaw");
		//���ô��ڴ�С
		this.setSize(1000, 720);
		//������ʾλ��
		this.setLocation(150, 10);
		//����Ϊ�̶���С
		this.setResizable(false);
		//���ô��ڵ�Ĭ�ϲ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
