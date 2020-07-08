package com.kh.project.mini.cafe.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.kh.project.mini.cafe.model.vo.Member;

public class ShowMyOrders extends JPanel{
	private MainFrame mf;
	private Member me;
	
	//������
	public ShowMyOrders(MainFrame mf, Member me) {
		System.out.println("[�ֹ� ���� Ȯ���ϱ� ]");
		System.out.println("me: "+ me);
		
		
		this.mf = mf;
		this.me = me;
		String name = me.getName();
		
		mf.setTitle(name + "���� �ֹ����� ");
		setLayout(new GridLayout(5,5,20,20));
		
		//Ÿ��Ʋ ��
		JLabel label= new JLabel(name + "���� �ֹ����� �Դϴ�. ");
		label.setHorizontalAlignment(JLabel.CENTER); //���� ��� �����Ѵ�.
		add(label); //���� �гξȿ� ������Ʈ(Ÿ��Ʋ-��)�� �ִ´�.
		
		
		JPanel resultPanel = new JPanel();
		add(resultPanel);
		
		//����Ʈ �޴��� �Ѱ��� ������ �� �ְ� �Ѵ�.
		JLabel OrderList = new JLabel("�ֱ� �ֹ���� (��������)");
		resultPanel.add(OrderList);
		add(resultPanel, "Center");
		
		
		System.out.println("myorder : " + me.getOrderHistory());
		
		
		//�ֹ������� ǥ���� �ؽ�Ʈ �ָ���
		JTextArea textArea = new JTextArea(30, 30);
		String myHistory= me.getOrderHistory();
		System.out.println("���� �ֹ�����: me.getOrderHistory(): "+ myHistory);
		
		textArea.setText(myHistory);
//		textArea.setLineWrap(true); //�ڵ��ٹٲ�.
//		textArea.setEditable(false);
//		add(textArea);
			
		JScrollPane scroller =new JScrollPane(textArea);
//		scroller.setPreferredSize(new Dimension(30,30));//��ũ�ѷ��� ũ�⸦ �����Ѵ�.
		add(scroller);
		
		

		
		//���ư��� ��ư ����
		JButton backButton = new JButton("������������ ���ư���");
		add(backButton);//��ư ������
		
		backButton.addActionListener(new GoMyPageListener());
		
		mf.add(this);
		
	}
	

	//���ư��� ��ư�� ������ MyPage�� ���ư��� �߰�
	class GoMyPageListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			changePanel(new MyPage(mf,me));
			
		}
	}
	
	public void changePanel(JPanel panel) {
		mf.remove(this); // ���� �г��� �����
		mf.add(panel); // �ٸ� �гη� ����ȭ���� ����
		mf.revalidate();
		mf.repaint();
	}

}