package com.kh.project.mini.cafe.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.kh.project.mini.cafe.controller.MemberController;
import com.kh.project.mini.cafe.model.vo.Member;

public class LoginAfter extends JPanel {

	private MemberController mc = new MemberController();
	private MainFrame mf;
	private Member me;

	public LoginAfter(MainFrame mf, String id) {
		this.mf = mf;
		this.me = mc.getMemberFromID(id);
		
		System.out.println("me:"+ this.me);
		
		String name = me.getName();
		mf.setTitle(name + "��");
		setBounds(700, 300, 300, 400);

		setLayout(new GridLayout(5, 5, 20, 20));
		JLabel label = new JLabel(name + " �� ȯ���մϴ�");
		label.setHorizontalAlignment(JLabel.CENTER);
		add(label);

		JButton orderBtn = new JButton("�ֹ��ϱ�");
		JButton userPageBtn = new JButton("����������");
		JButton logoutbtn = new JButton("�α׾ƿ�");

		add(orderBtn, "Center");
		add(userPageBtn, "Center");
		add(logoutbtn, "Center");

		orderBtn.addActionListener(new GoOrderPageListener());
		userPageBtn.addActionListener(new GoUserPageListener());
		logoutbtn.addActionListener(new GoLogoutPageListener());

		mf.add(this);
	}

	// �ֹ��ϱ� ��ư ������ �ֹ��ϱ� �������� �Ѿ��
	class GoOrderPageListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			changePanel(new Order(mf, me));
		}
	}

	class GoUserPageListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			changePanel(new MyPage(mf, me));
		}
	}

	// �α׾ƿ� ��ư Ŭ���� �̺�Ʈ ����
	class GoLogoutPageListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int result = JOptionPane.showConfirmDialog(null, "�α׾ƿ� �Ͻðڽ��ϱ�?", "�α׾ƿ�", JOptionPane.YES_NO_OPTION);

			// â �ݱ�, ��, �ƴϿ� ��ư Ŭ���� �̺�Ʈ ����
			if (result == JOptionPane.CLOSED_OPTION) {
				System.exit(0);
			} else if (result == JOptionPane.YES_OPTION) {
				// Yes ��ư�� ������ ����ȭ������ ���ư���
				changePanel(new MainView(mf));
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
