package com.kh.project.mini.cafe.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kh.project.mini.cafe.controller.MemberController;
import com.kh.project.mini.cafe.model.vo.Member;

public class Login extends JPanel {

	private MainFrame mf;
	private MemberController mc = new MemberController();

	private JTextField idText;
	private JPasswordField pwdText;

	public Login(MainFrame mf) {
		System.out.println("�α��� â");
		
		mf.setTitle("�α���");
		this.mf = mf; // ���������� �ʱ�ȭ
		mc.showEnrolledMember();

		// �α��� ȭ���� ���̾ƿ� ����.
//		setBounds(700,300,300,400);
		setLayout(new GridLayout(0, 1));

		JLabel label1 = new JLabel("���̵�� ��й�ȣ�� �Է����ּ���.");
		add(label1);
		label1.setHorizontalAlignment(JLabel.CENTER);

		JLabel idLabel = new JLabel("ID");
		add(idLabel);

		idText = new JTextField(10);
		idText.setBounds(100, 10, 160, 25);
		add(idText);

		JLabel pwdLabel = new JLabel("PW");
		add(pwdLabel);

		pwdText = new JPasswordField(20);
		add(pwdText);

		JButton button1 = new JButton("�α���");
		button1.setPreferredSize(new Dimension(45, 28));
		add(button1, "Center");
		add(button1);

		button1.addActionListener(new GoLoginAfterListener());

		// ���ư����ư���� => MainView â����
		JButton button = new JButton("������������ ���ư���");
		add(button, "Center");
		button.addActionListener(new MainViewListener());

		// ���� �г��� ���������� ����(ȭ�麯��)
		mf.add(this);

	}

	// �α��� ��ư ������ LoginAfter�� �Ѿ��
	class GoLoginAfterListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String inputID = idText.getText();
			String inputPW = mc.getPassWordToText(pwdText.getPassword());

			if (!mc.isDuplicatedMember(inputID)) {
				// ��ϵǾ����� ���� ���̵��Է�
				JOptionPane.showMessageDialog(null, "�߸��� �����Դϴ�.", "�α��� ����", JOptionPane.ERROR_MESSAGE);
			} else {
				// ���̵� ��й�ȣ �� �����ϴ� ����� ���Ѵ�.
				// ����κ� ����.
				Member me = mc.logIn(inputID, inputPW);

				if (me != null) {
					// �α��� ���� ���̾�α� ����.
					String userName = me.getName();
					JOptionPane.showMessageDialog(null, userName + "�� �ݰ����ϴ�!", "�α��� ����", JOptionPane.PLAIN_MESSAGE);

					// �α��� ������ �������� �̵�.
					changePanel(new LoginAfter(mf, inputID));
				} else {
					// �α��� ���� ���̾�α� ����.
					JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�!\n�ٽ��Է����ּ���!", "�α��� ����",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	// ���ư��� ��ư ������ MainView �� �Ѿ��
	class MainViewListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			changePanel(new MainView(mf));
		}
	}

	public void changePanel(JPanel panel) {
		mf.remove(this);
		mf.add(panel);
		mf.revalidate();
		mf.repaint();

	}
}