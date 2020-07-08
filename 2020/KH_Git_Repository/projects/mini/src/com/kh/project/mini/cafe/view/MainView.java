package com.kh.project.mini.cafe.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainView extends JPanel{
	
	private MainFrame mf;
	
	public MainView(MainFrame mf){
//		setSize(500,500);
//		setLayout(null);
		mf.setTitle("Ŀ�Ǳ�");
		this.mf = mf;
	
		setLayout(new GridLayout(5,5,20,20));
		
		
		JLabel label = new JLabel("�ȳ��ϼ���. Ŀ�Ǳ��Դϴ�*^^*");
		label.setHorizontalAlignment(JLabel.CENTER);
		add(label);
		
		
		JButton loginBtn = new JButton("�α���");
//		loginBtn.setSize(200, 100);
//		loginBtn.setLocation(40, 40);		
		JButton joinBtn = new JButton("ȸ������");
		JButton exitBtn = new JButton("�����ϱ�");
		
		add(loginBtn,"Center");
		add(joinBtn,"Center");
		add(exitBtn,"Center");
		
		
		loginBtn.addActionListener(new GoLoginPageListener());
		
		joinBtn.addActionListener(new GoJoinPageListener());
		
		exitBtn.addActionListener(new GoExitPageListener());
		
		mf.add(this);
	}
	
	class GoLoginPageListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			changePanel(new Login(mf));
		}
	}	
	
	class GoJoinPageListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			changePanel(new Join(mf));
			
		}
	}
	
	class GoExitPageListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int result = JOptionPane.showConfirmDialog(null, "������ �����Ͻðڽ��ϱ�?", "EXIT", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "������ �� ������ ~ ! ","���α׷� ����",JOptionPane.PLAIN_MESSAGE);
				System.exit(0);	
			} 
		}
	}
	
	public void changePanel(JPanel panel) {
		mf.remove(this);
		mf.add(panel);
		mf.revalidate();
		mf.repaint();
	}
}
	
