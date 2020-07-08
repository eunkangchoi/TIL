package com.kh.project.mini.cafe.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.kh.project.mini.cafe.controller.MemberController;
import com.kh.project.mini.cafe.model.vo.Member;
import com.kh.project.mini.cafe.view.MyPage.GoLoginAfterListener;

// �н����� ���� GUI
public class UpdatePassword extends JPanel {

	private MemberController mc = new MemberController();
	private MainFrame mf;
	private JPasswordField newPwd1;
	private JPasswordField newPwd2;
	private JLabel isEqualPwd;
	private String id;
	private Member me; // ���� �α����� ��� ��ü�� �ǹ�.

	// ������.
	public UpdatePassword(MainFrame mf, String id, Member member) {
		System.out.println("�н����� ���� â");
		// �α��μ����Ҷ� ���̵�(HashMap- key)��
		// Member��ü(HashMap- value)��
		// �Ű������� �Ѵ�.

		// Ÿ��Ʋ ����
		mf.setTitle("��й�ȣ ����");
		this.mf = mf;
		this.id = id;
		this.me = member;

		// 1. (��) �����ӿ� Ÿ��Ʋ ���� �ִ´�.
		String name = member.getName();
		JLabel title = new JLabel(name + "�� ��й�ȣ ���� �������Դϴ�.");
		title.setHorizontalAlignment(JLabel.CENTER);
		this.add(title, "North");

		// 2. (�߾�) �����ӿ� �׸��巹�̾ƿ��� �г��� �ִ´�.
		JPanel inputPwdPanel = new JPanel(new GridLayout(4, 0));// 4��1�� �׸��� ���̾ƿ�
		this.add(inputPwdPanel,"Center");
		
		// inputPwdPanel(0�� 0��): ���ο� ��й�ȣ �Է�
		JPanel pwdOnePanel = new JPanel();
		pwdOnePanel.add(new JLabel("���ο� ��ȣ �Է�"));
		newPwd1 = new JPasswordField(10);
		pwdOnePanel.add(newPwd1);
		inputPwdPanel.add(pwdOnePanel);

		// inputPwdPanel(1�� 0��): ���ο� ��й�ȣ �ѹ��� �Է�
		JPanel pwdTwoPanel = new JPanel();
		pwdTwoPanel.add(new JLabel("�ѹ� �� ��ȣ �Է�"));
		newPwd2 = new JPasswordField(10);
		pwdTwoPanel.add(newPwd2);
		inputPwdPanel.add(pwdTwoPanel);

		// ���ο� ��й�ȣ �Է�/ �ٽ��Է� �� ���� ������ Ȯ�����ִ� ��ư
		JButton isEqualPwdButton = new JButton("��й�ȣ Ȯ��");
		inputPwdPanel.add(isEqualPwdButton);

		// inputPwdPanel(3�� 0��): newPwd1�� newPwd2�� ���� ������Ȯ�� ǥ�� ��
		isEqualPwd = new JLabel();
		isEqualPwd.setHorizontalAlignment(JLabel.CENTER);
		inputPwdPanel.add(isEqualPwd);
		this.add(inputPwdPanel, "Center");

		// ��ư ������ - isEqualPwdButton�� ��ư������
		// �Է��� ��й�ȣ�� �ѹ��� �Է��� ��й�ȣ�� ������ Ȯ�����ִ� ��ư.
		isEqualPwdButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ��й�ȣ �ؽ�Ʈ ����
				String newPwd = mc.getPassWordToText(newPwd1.getPassword());
				String newPwdAgain = mc.getPassWordToText(newPwd2.getPassword());

				if (newPwd.length() == 0 || newPwdAgain.length() == 0) {
					// ��й�ȣ�� �ϳ��� �Էµ��� ������ -> isEqualPwd�� ������� ��Ÿ��
					isEqualPwd.setText("��� �Է����ּ���!");

				} else if (newPwd.equals(newPwdAgain)) {
					// ��й�ȣ�� ��ġ�ϴٸ�
					isEqualPwd.setText("�����ġ�մϴ�.");

				} else {
					// ��й�ȣ�� ��ġ���� �ʴ´ٸ�
					isEqualPwd.setText("��ġ���� �ʽ��ϴ�! �ٽ� �Է����ּ���!");
				}
			}
		});

		JButton submitButton = new JButton("��й�ȣ �����ϱ�");
		add(submitButton, "Center");

		// submitButton - ��ư������ ����
		// ��ư�����ʿ� submitButton�� ����
		submitButton.addActionListener(new GoAfterLoginPanelListener());

		// ���ư����ư���� => MyPage�޴� â����
		JButton backButton = new JButton("������������ ���ư���");
		add(backButton, "Center");
		backButton.addActionListener(new GoMyPageListener());

		// ���������Ӱ� �����г��� �����Ѵ�.
		mf.add(this);
	}

	// ���ư��� ��ư ������ MyPage�� �Ѿ��
	class GoMyPageListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			changePanel(new MyPage(mf, me));

		}
	}

	// submitButton�� ������, ���̾�αװ� �ߴ°Ͱ� ���ÿ�, �г��� ����� �� �ֱ⶧����
	// �͸�Ŭ������ ���� �̺�Ʈ �����ʸ� ����´�ſ�
	// ����Ŭ������ ���� submitButton�� ��ư �׼� �̺�Ʈ �����ʸ� �����Ų��.
	class GoAfterLoginPanelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// ��й�ȣ �ؽ�Ʈ ����
			String newPwd = mc.getPassWordToText(newPwd1.getPassword());
			String newPwdAgain = mc.getPassWordToText(newPwd2.getPassword());

			if (newPwd.equals(newPwdAgain)) {// ��й�ȣ ��� ��ġ
				// �����޽���
				JOptionPane.showMessageDialog(null, "��й�ȣ ���� ����!", "Ŀ�Ǳ� - ��й�ȣ ����(����)", JOptionPane.PLAIN_MESSAGE);

				// ��й�ȣ ����
				mc.changePassword(id, newPwd);

				// �α��� ���� �������� ���ư���.
				changePanel(new LoginAfter(mf, id));

			} else {
				// ��й�ȣ�� ��� ��ġ �ʴ´� -> �����޽����� ������.
				JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�!", "Ŀ�Ǳ� - ��й�ȣ ����(����)", JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	public void changePanel(JPanel panel) {
		mf.remove(this); // ���� �г��� �����
		mf.add(panel); // �ٸ� �гη� ����ȭ���� ����
		mf.revalidate();
		mf.repaint();
	}

}