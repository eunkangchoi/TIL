package com.kh.project.mini.cafe.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kh.project.mini.cafe.controller.MemberController;
import com.kh.project.mini.cafe.model.vo.Member;

public class LoginAgain extends JPanel {
	// ���� CafeView�� �ʱ�ȭ - ��Ʈ�����̶� ������.
	private MemberController mc = new MemberController();
	private MainFrame mf;
	private JTextField idText;
	private JPasswordField pwdText;
	private String menuName;
	private int menuIdx;
	private String id;
	// ������
	// �α����� �Է��ؾ��Ѵ�.
	public LoginAgain(MainFrame mf, String id, int menuIdx) {

//		//�ʿ� root�� �����ϴ��� Ȯ��
//		//�ؽø�
//		HashMap<String, Member> map=mc.enrolledMembers();
//		System.out.print("result: "+map.containsKey("root")  );
		mf.setTitle("Ŀ�Ǳ� - �α���");
		this.mf = mf; // ���������� �ʱ�ȭ

		this.id=id;
		this.menuIdx = menuIdx;


		// �α��� ȭ���� ���̾ƿ� ����.
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

		JButton submitButton = new JButton("�α���");
		submitButton.setPreferredSize(new Dimension(45, 28));
		add(submitButton, "Center");
		add(submitButton);

		// �α��� ��ư�� ������ ����Ǵ� �̺�Ʈ ������
		// �Է��� ���̵�� �н����尡, ��ϵ� ���̵����� Ȯ���Ѵ�.
		// �״����� MemberController�� �ؽøʿ� ��ϵ� ���̵�� �н������ ��ġ���� Ȯ��.
		submitButton.addActionListener(new GoEditPage());

		// ���� �г��� ���������� ����(ȭ�麯��)
		mf.add(this);
	}

	// ����Ŭ���� - ���������� public�ȵ�
	// submitButton�� ������ ��ưŬ�� �̺�Ʈ ������.
	class GoEditPage implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// yourID, yourPW���� �Է��� id�� ��й�ȣ �ؽ�Ʈ�� �ҷ�����
			String inputID = idText.getText();
			String inputPW = mc.getPassWordToText(pwdText.getPassword());

			// �Է��� ���̵� ��ϵǾ����� ���� ���̵���
			if (!mc.isDuplicatedMember(inputID)) {
				// �����޽��� Dialog�� ������.
				JOptionPane.showMessageDialog(null, "��ϵǾ� ���� ���� ���̵��Դϴ�!", "�α��� ����", JOptionPane.ERROR_MESSAGE);
			} else {

				// �Է��� ���̵� ��ϵǾ��ִ� ���̵���
				// �ؽøʿ� ����� ���̵�� �н������
				// �Է��� ���̵�� �н����尡 ��ġ���� Ȯ���Ѵ�.
				Member me = mc.logIn(inputID, inputPW);
				if (me != null) {
					// �α��� ����
					String userName = me.getName();

					// Dialog�� ����- �α��� �����޽���
					JOptionPane.showMessageDialog(null, userName + "�� ���� ���� �߽��ϴ�!", "�α��� ����",
							JOptionPane.PLAIN_MESSAGE);

					// menuIdx: ����Ʈ���� Ŭ���� �޴��� �ε�����ȣ
					// menuName: �������������� ������ �޴��̸�
					System.out.println(menuIdx);
					System.out.println(menuName);
					
					//ȸ��Ż���϶��� ����.
					if (menuIdx == 4) {
						// ȸ��Ż��
						// JDialog �� �ҷ��ͼ� ȸ��Ż�� �Ұ������� Ȯ��.
						// �ű⼭ ���� ���� ���� Ż��ó���� �Ѵ�.
						int isQuit = JOptionPane.showConfirmDialog(null, "ȸ�� Ż���Ͻðڽ��ϱ�?", "Ŀ�Ǳ� - ȸ��Ż��",
								JOptionPane.YES_NO_OPTION);
						if (isQuit == 0) {
							// ��
							Member dropOuted = mc.dropOutMember(inputID); // inputID�� ���� ȸ���� Ż��ó���Ѵ�.
							// Ż��ó�� �Ϸ�
							String dropOutedName = dropOuted.getName();
							JOptionPane.showMessageDialog(null, dropOutedName + "�� ȸ��Ż�� ó���Ǿ����ϴ�.", "Ŀ�Ǳ� - ȸ��Ż�� (����)",
									JOptionPane.PLAIN_MESSAGE);

							changePanel(new MainView(mf)); // �α��� ����ȭ������ ���ư���.
						} else {
							// �ƴϿ�.
							changePanel(new LoginAfter(mf, id));// �α��� ����ȭ������ ���ư���.
						}
					}

				} else {
					// �α��� ����
					JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�!\n�ٽ��Է����ּ���!", "�α��� ����",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	// �޼ҵ� - �г� ����
	public void changePanel(JPanel panel) {
		// �������� �����г��� ����
		mf.remove(this);
		mf.add(panel);

		mf.revalidate();
		mf.repaint();
	}
}
