package com.kh.project.mini.cafe.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kh.project.mini.cafe.controller.MemberController;
import com.kh.project.mini.cafe.model.vo.Member;

public class Join extends JPanel {
	// �ʵ� //
	private MemberController mc = new MemberController();
	private MainFrame mf;
	private JTextField idText;
	private JPasswordField pwdText;
	private JTextField nameText;
	private JTextField addressText;

	JComboBox<Integer> birthYearBox;
	JComboBox<Integer> birthMonthBox;
	JComboBox<Integer> birthDayBox;

	// ������ //
	public Join(MainFrame mf) {
		System.out.println("ȸ������ \n");
		this.mf = mf;
		mf.setTitle("Ŀ�Ǳ� - ȸ������");
		setLayout(new GridLayout(0, 1));

		JLabel label1 = new JLabel("Ŀ�Ǳ��� ������ �Ǿ��ּ���!");
		label1.setHorizontalAlignment(JLabel.CENTER);
		add(label1);

		JLabel idLabel = new JLabel("ID");
		add(idLabel);
		idText = new JTextField(10);
		idText.setBounds(100, 10, 160, 25);
		add(idText);

		// ��й�ȣ �Է�
		JLabel pwdLabel = new JLabel("PW");
		add(pwdLabel);
		pwdText = new JPasswordField(20);
		add(pwdText);

		// �̸��Է�
		JLabel nameLabel = new JLabel("�̸�");
		add(nameLabel);
		nameText = new JTextField(10);
		nameText.setBounds(100, 10, 160, 25);
		add(nameText);

		// �����Է�
		JPanel birthdayPanel = new JPanel();
		birthdayPanel.setLayout(new GridLayout(1, 6));

		birthdayPanel.add(new JLabel("�⵵", JLabel.RIGHT));


		// �⵵ �޺��ڽ�-�⵵
		Calendar calendar = Calendar.getInstance(); // Ķ����
		int latestYear = calendar.get(Calendar.YEAR);
		int todayMonth = 1 + calendar.get(Calendar.MONTH);
		int todayDay = 1 + calendar.get(Calendar.DAY_OF_MONTH);
		int oldestYear = latestYear - 90;

		Integer[] birthYear = new Integer[91];
		for (int year = oldestYear; year <= latestYear; year++)
			birthYear[year - oldestYear] = year;

		birthYearBox = new JComboBox<Integer>(birthYear);
		birthYearBox.setSelectedItem(latestYear);
		birthdayPanel.add(birthYearBox);
		
	
		birthdayPanel.add(new JLabel("��", JLabel.RIGHT));
		// �⵵�޺��ڽ�-��
		Integer[] birthMonth = new Integer[12];
		for (int month = 1; month <= 12; month++)
			birthMonth[month - 1] = month;
		birthMonthBox = new JComboBox<Integer>(birthMonth);
		birthMonthBox.setSelectedItem(todayMonth); // ���� �޷� �̸����� �Ǵ°ɷ�..
		birthdayPanel.add(birthMonthBox);
		

		birthdayPanel.add(new JLabel("��", JLabel.RIGHT));
		// �⵵�޺��ڽ�-��
		Integer[] birthDay = new Integer[31];
		for (int day = 1; day <= 31; day++) {
			birthDay[day - 1] = day;
		}
		birthDayBox = new JComboBox<Integer>(birthDay);
		birthDayBox.setSelectedItem(todayDay);
		birthdayPanel.add(birthDayBox);
		add(birthdayPanel);

//		JLabel birthdayLabel = new JLabel("�������(8�ڸ�)");
//		add(birthdayLabel);
//		birthdayText = new JTextField(10);
//		birthdayText.setBounds(100, 10, 160, 25);
//		add(birthdayText);

		// �ּ��Է�
		JLabel addressLabel = new JLabel("�ּ�");
		add(addressLabel);
		addressText = new JTextField(10);
		addressText.setBounds(100, 10, 160, 25);
		add(addressText);

		// ȸ������ ��ư
		JButton joinButton = new JButton("ȸ������");
		joinButton.setPreferredSize(new Dimension(45, 28));
		add(joinButton, "Center");
		add(joinButton);
		
		//���ư����ư���� => MainView â����
		JButton button = new JButton("������������ ���ư���");
		add(button, "Center");
		button.addActionListener(new GoMainViewListener());

		// ��ư�� ������ �̺�Ʈ �����ʸ� �۵�
		joinButton.addActionListener(new GoLoginAfterPageListener());
	}

	// ���� Ŭ����
	class GoLoginAfterPageListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			// ���� ���ԵǾ��ִ� ȸ������ Ȯ���Ѵ�.
			// MemberController�� �̿�.
			// �Է��� ���̵��� �����´�.
			String id = idText.getText();

			// �Է��� ���̵� �ּ� ������ �̻��ΰ�?
			boolean isAvailableID = (id.length() >= 3) ? true : false;
			if (!isAvailableID) {
				JOptionPane.showMessageDialog(null, "�Է��� ���̵� �ʹ�ª���ϴ�! �ּ� 3���� �̻����� �Է����ּ���!", "ȸ������ ����",
						JOptionPane.ERROR_MESSAGE);
			}
			//�׷��ٸ� �ߺ��� ���̵��ΰ�?
			else if(mc.isDuplicatedMember(id)) {
				isAvailableID= false;
				JOptionPane.showMessageDialog(null, "�Է��� ���̵�� �̹� �����ϴ� ���̵��Դϴ�! �ٽ��Է����ּ���!", "ȸ������ ����",
						JOptionPane.ERROR_MESSAGE);
			}
			

			// ��й�ȣ pwdText.getPassword() => char�� �迭
			// char�� �迭 => String���� ��ȯ
			String pwd = mc.getPassWordToText(pwdText.getPassword());
			// �Է��� ��й�ȣ�� �ּ� 3�����̻�
			boolean isAvailablePWD = (pwd.length() >= 3) ? true : false;
			if (!isAvailablePWD) {
				JOptionPane.showMessageDialog(null, "�Է��� ��й�ȣ�� �ʹ�ª���ϴ�! �ּ� 3���� �̻����� �Է����ּ���!", "ȸ������ ����",
						JOptionPane.ERROR_MESSAGE);
			}

			String name = nameText.getText();
			boolean isAvailableName = (name.length() >= 1) ? true : false;
			if (!isAvailableName) {
				JOptionPane.showMessageDialog(null, "�Է��� �̸��� �ʹ�ª���ϴ�! �ּ� 1���� �̻����� �Է����ּ���!", "ȸ������ ����",
						JOptionPane.ERROR_MESSAGE);
			}

			// �Է��� ����
			int year = (Integer) birthYearBox.getSelectedItem();
			int month = (Integer) birthMonthBox.getSelectedItem();
			int day = (Integer) birthDayBox.getSelectedItem();
			String birthday = year + "/" + month + "/" + day;
			

			String address = addressText.getText();

			// �ϴ� ���ο� ��ü ����.
			Member newMember = new Member(pwd, name, birthday, address,id);
			if (isAvailableID && isAvailablePWD && isAvailableName) {
				// ȸ�������ϱ�.
				mc.joinMember(id, newMember);
				
				//���� ������Ʈ �ؾߵɵ�..��
				
	
				JOptionPane.showMessageDialog(null, "Ŀ�Ǳ��� ���Ű� ȯ���մϴ�! " + name + "��!", "ȸ������ ����",
						JOptionPane.PLAIN_MESSAGE);
				
				// ȸ�������� �� �Ǹ� -> �α��� ������ ȭ������ �̵�.
				changePanel(new LoginAfter(mf, id));
			}
		}
	}
	
	//���ư����ư�� ������ MainView â�� ���� ����Ŭ����
	class GoMainViewListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			changePanel(new MainView(mf));
		}
	}

	// �޼ҵ�
	public void changePanel(JPanel panel) {
		mf.remove(this);
		mf.add(panel);
		mf.revalidate();
		mf.repaint();
	}

}
