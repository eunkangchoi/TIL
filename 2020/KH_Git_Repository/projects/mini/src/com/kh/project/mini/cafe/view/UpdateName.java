package com.kh.project.mini.cafe.view;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.kh.project.mini.cafe.controller.MemberController;
import com.kh.project.mini.cafe.model.vo.Member;
import com.kh.project.mini.cafe.view.UpdatePassword.GoMyPageListener;

//�̸������ϴ� GUI
public class UpdateName extends JPanel{
	private MemberController mc = new MemberController();
	private MainFrame mf;
	private JTextField newNameInput;
	private String id;
	private Member me;
	
	public UpdateName(MainFrame mf, String id, Member me) {
		
		System.out.println("�̸� ���� â");
		// �α��μ����Ҷ� ���̵�(HashMap- key)��
		// Member��ü(HashMap- value)��
		// �Ű������� �Ѵ�.
		this.mf=mf;
		this.id=id;
		this.me=me;
		System.out.println("id: "+ this.id+ "me:"+ this.me);
		
		mf.setTitle("�̸�����");
		
		// 1. (��) �����ӿ� Ÿ��Ʋ ���� �ִ´�.
		JLabel title = new JLabel("������ �̸��� �Է����ּ���");
		title.setHorizontalAlignment(JLabel.CENTER);
		this.add(title, "North");

		// 2. (�߾�) �����ӿ� �׸��巹�̾ƿ��� �г��� �ִ´�.
		JPanel newNamePanel = new JPanel();
		newNamePanel.add(new JLabel("������ �̸��Է�"));
		newNameInput= new JTextField(10);
		newNamePanel.add(newNameInput);
		this.add(newNamePanel, "Center");
		
	
		
		//3. (��) ��ư Ŭ��
		JButton submitButton= new JButton("�̸������ϱ�");
		add(submitButton, "Center");
		submitButton.addActionListener(new GoAfterLoginListener() ); 
	
		//���ư����ư���� => ������������ ���ư�.
		JButton backButton = new JButton("������������ ���ư���");
		add(backButton, "Center");
		backButton.addActionListener( new GoMyPageListener());
		
		mf.add(this);
		
	}
	
	// ������������ ���ư���.
	class GoMyPageListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			changePanel(new MyPage(mf, me));
			
		}
	}
	
	// �̸������ϱ�Ŭ�� -> �α��� ���� ���� �������� ���ư���.
	class GoAfterLoginListener implements ActionListener {			
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("�̸����� ��ư Ŭ����");
			String newName= newNameInput.getText();
			System.out.println("���ο� �̸�: "+ newName);
			
			if(newName.length() > 0) {
				//���� �Է��� �̸��� �ּ� 1���� �̻��̶��
				//�����޽���
				JOptionPane.showMessageDialog(null, "�̸� ���� ����!", "�̸� ����(����)", JOptionPane.PLAIN_MESSAGE);
				mc.changeName(id,newName);
				
				System.out.println("<��Ȯ��>");
				mc.showEnrolledMember();
				System.out.println();
				
				//�̸� ���� �����ϸ� �α��� �����ͷ� ���ư���.
				changePanel(new LoginAfter(mf, id));
			}else {
				JOptionPane.showMessageDialog(null, "�ּ� �� ���� �̻��� �̸��� �Է����ּ���!", "�̸� ����(����)", JOptionPane.ERROR_MESSAGE);
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


