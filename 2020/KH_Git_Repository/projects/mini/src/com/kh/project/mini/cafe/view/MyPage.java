package com.kh.project.mini.cafe.view;



import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.kh.project.mini.cafe.model.vo.Member;

//Ŀ�Ǳ� ���������� GUI - �α��� ���� ȭ�� 
public class MyPage extends JPanel {
	//���������� �ȿ� �ִ� �г��� �ٲ� �����.
	private MainFrame mf;
	private Member me;
	private String id;
	
	// ������
	public MyPage(MainFrame mf, Member me) {
		// �������̸� : Ŀ�Ǳ� - ����������
		mf.setTitle("����������");
		this.mf = mf;
		this.me = me;
		this.id=me.getId();
		
		System.out.println("[����������] me:"+ this.me);
		
		// ���� �г��� ���̾ƿ��� GridLayout���� �Ѵ�.
		setLayout(new GridLayout(5,5,20,20)); //5�� 5��/ ���� 20��
		
	
		//1. ��(Ÿ��Ʋ) ������Ʈ
		String id= me.getId();
		String name= me.getName();
		JLabel titleLabel= new JLabel( name + " ���� ������ �Դϴ�.");
		titleLabel.setHorizontalAlignment(JLabel.CENTER); //���� ��� �����Ѵ�.
		add(titleLabel); //���� �гξȿ� ������Ʈ(Ÿ��Ʋ-��)�� �ִ´�.
		
		
		//2. �޴�����Ʈ ������Ʈ
		String[] menuStr= { 
				"�ֹ����� ��ȸ", 
				"��й�ȣ ����", 
				"�̸� ����",
				"�ּ� ����",
				"ȸ�� Ż��"
		};
		
		JList<String> menu = new JList<String>(menuStr);
		
		// ����Ʈ �޴��� �Ѱ��� ������ �� �ֵ��� �����Ѵ�.
		JPanel resultPanel = new JPanel();
		JLabel choiceMenu = new JLabel("�޴��� Ŭ���Ͻø� �ٷ� �̵��մϴ�.");
		
		resultPanel.add(choiceMenu);
		add(resultPanel, "Center");
		
		//��ũ�ѹٸ� �����
		//��ũ�ѹ� ������Ʈ �ȿ� menu�� �ִ´�.
		JScrollPane scroller= new JScrollPane(menu);
		scroller.setPreferredSize(new Dimension(300,300));//��ũ�ѷ��� ũ�⸦ �����Ѵ�.
		add(scroller); //�гο� ��ũ�ѷ��� �ִ´�.
		
		menu.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		
		//����Ʈ�� �����ϸ�, �߻��ϴ� �̺�Ʈ �����ʸ� �����.
		menu.addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				//����Ʈ�� �ϳ��� ����
				String selectedMenu= menu.getSelectedValue(); //������ ����Ʈ�� ���� ����.
				int selectIdx= menu.getSelectedIndex(); //������ ����Ʈ�� �ε�����ȣ
				

				if(selectIdx == 0) {//�ֹ� ���� ��ȸ
					changePanel(new ShowMyOrders(mf,me));
				}
				else if(selectIdx == 1){//��й�ȣ ����
					changePanel(new UpdatePassword(mf,id, me)); 
				}
				else if(selectIdx == 2) {//�̸�����
					changePanel(new UpdateName(mf,id, me));
				}
				else if(selectIdx == 3) {//�ּҺ���
					changePanel(new UpdateAddress(mf,id, me));
				}
				else if(selectIdx == 4) { //ȸ��Ż�� 
					//�ѹ��� �α����� �ϰ� -> Ż��ó���Ѵ�.
					changePanel(new LoginAgain(mf, id,selectIdx));
				}
			}
		});
		
		//���ư����ư���� => LoginAfter�޴� â����
		JButton button = new JButton("������������ ���ư���");
		add(button, "Center");
		button.addActionListener(new GoLoginAfterListener());
		
		// �����г��� ���������ӿ� �����Ѵ�.
		mf.add(this);
	}
	
	//���ư��� ��ư ������  LoginAfter�� �Ѿ��
	class GoLoginAfterListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			changePanel(new LoginAfter(mf, id));
		}
	}
	
	
	//�޼ҵ�
	public void changePanel(JPanel panel) {
		mf.remove(this);
		mf.add(panel);
		mf.revalidate();
		mf.repaint();
	}

}
