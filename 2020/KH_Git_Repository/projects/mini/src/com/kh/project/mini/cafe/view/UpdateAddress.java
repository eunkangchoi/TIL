// ���� url: https://stackoverrun.com/ko/q/2881288
package com.kh.project.mini.cafe.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.kh.project.mini.cafe.controller.MemberController;
import com.kh.project.mini.cafe.model.vo.Member;

//�ּҺ��� GUI
public class UpdateAddress extends JPanel {
	// �ʵ�//
	private MainFrame mf;
	private Member me;
	private String id;
	
	private String inputMcity; //�Է�  ����
	private String inputCity;  //�Է� ��/�� 
	private String inputDetailAddr; //�Է� ���ּ�
	private MemberController mc= new MemberController();

	// ���� �޺� �ڽ�//
	private JComboBox mcityOption; // �����޺��ڽ� - �ƹ��͵� ����.
	private final String mcities[] = { "����Ư����", // 0
			"�λ걤����", // 1
			"�뱸������", // 2
			"��õ������", // 3
			"���ֱ�����", // 4
			"����������", // 5
			"��걤����", // 6
			"����Ư����ġ��", // 7
			"��⵵", // 8
			"������", // 9
			"��û�ϵ�", // 10
			"��û����", // 11
			"����ϵ�", // 12
			"���󳲵�", // 13
			"���ϵ�", // 14
			"��󳲵�", // 15
			"����Ư����ġ��", // 16
			"�� ����" // 17
	};

	// ���� �޺� �ڽ�//
	// �ؽ����̺��� �̿�.
	private JComboBox cityOption; // �����޺��ڽ� - �ƹ��͵� ����.
	private Hashtable<Object, Object> cityOptionTable = new Hashtable<Object, Object>();

	//���ּ�//
	private JTextField detailedAddress;
	
	
	// ������ //
	// id: LoginAgain���� �Է��� ������� ���̵�
	// me: id�� ��ġ�� �ؽø��� value(��� ����)
	// mf: ����������
	public UpdateAddress(MainFrame mf, String id, Member me) {
		System.out.println("�ּ� ���� â");
		
		this.mf = mf;
		mf.setTitle("Ŀ�Ǳ� - �ּҺ���");
		this.id = id;
		
		this.me = me;

		System.out.println("[�ּҺ���] id: "+ id+ " me:"+ this.me);
		
		// BorderLayout���� �Ѵ�.
		// Ÿ��Ʋ �� (��)
		JLabel title = new JLabel("�ּ� ����");
		title.setHorizontalAlignment(JLabel.CENTER); // �������
		add(title, "North");

		//�ȳ�����
		JPanel resultPanel = new JPanel();
		JLabel cityList = new JLabel("�ø� �����ϸ� �ڵ����� ��/���� ���ɴϴ�.");
		resultPanel.add(cityList);
		add(resultPanel, "Center");
		
		// �ּ� ���� �Է¶�(�߾�)
		JPanel changeAddressPanel = new JPanel();
		changeAddressPanel.setLayout(new GridLayout(4, 1));

		// 1. �� �Է�
		JPanel inputMcityPanel = new JPanel();
		JLabel mcityLabel = new JLabel("����");
		inputMcityPanel.add(mcityLabel);

		// �����޺� �ڽ��� �����.
		mcityOption = new JComboBox(mcities);
		inputMcityPanel.add(mcityOption); // inputMcityPanel�� mcityOption ������Ʈ�� �ִ´�..
		mcityOption.setSelectedIndex(17);
		changeAddressPanel.add(inputMcityPanel);

		// 2. ��/�� - SubComboBox
		JPanel inputCityPanel = new JPanel();
		JLabel cityLabel = new JLabel("��/��");
		inputCityPanel.add(cityLabel);
		
		// 0. ����Ư����
		String [] subItem0={ "��/�� ����", "���α�", "�߱�", "��걸", "������", "������", "���빮��", "�߶���", "���ϱ�", "���ϱ�", "������", "�����", "����", "���빮��", "������",
						"��õ��", "������", "���α�", "��õ��", "��������", "���۱�", "���Ǳ�", "���ʱ�", "������", "���ı�", "������" };

		// 1. �λ걤����
		String[]subItem1={ "��/�� ����", "�߱�", "����", "����", "������", "�λ�����", "������", "����", "�ϱ�", "�ؿ�뱸", "���ϱ�", "������", "������", "������", "������", "���",
				"���屺" };

		// 2. �뱸 ������
		String[] subItem2={ "��/�� ����", "�߱�", "����", "����", "����", "�ϱ�", "������", "�޼���", "�޼���" };
		
		//3.��õ������
		String[] subItem3={ "��/�� ����", "�߱�", "����", "����", "������", "������", "����", "��籸", "����", "��ȭ��", "������", "����Ȧ��" };
		
		//4. ���ֱ�����
		String[] subItem4={ "��/�� ����", "����", "����", "����", "�ϱ�", "���걸" };
		
		//5. ����������
		String[] subItem5={ "��/�� ����", "����", "�߱�", "����", "������", "�����" };
		
		//6. ��걤����
		String[] subItem6={ "��/�� ����", "�߱�", "����", "����", "�ϱ�", "���ֱ�" };

		//7. ����Ư����ġ�� - ���� �Ф�
		String[] subItem7={ "��/�� ����" };

		//8. ��⵵
		String[] subItem8={ "��/��/�� ����", "������", "������", "�����ν�", "�Ⱦ��", "��õ��", "�����", "���ý�", "����õ��", "�Ȼ��", "����", "��õ��", "������", "�����ֽ�", "�����",
				"�����", "������", "�ǿս�", "�ϳ���", "���ν�", "���ֽ�", "��õ��", "�ȼ���", "������", "ȭ����", "���ֽ�", "���ֽ�", "��õ��",
				"���ֽ�", "���ֱ�", "��õ��", "����", "����" };

		//9. ������
		String[] subItem9={ "��/��/�� ����", "��õ��", "���ֽ�", "������", "���ؽ�", "�¹��", "���ʽ�", "��ô��", "ȫõ��", "Ⱦ����", "������", "��â��", "������", "ö����", "ȭõ��",
				"�籸��", "������", "����", "��籺" };

		//10. ��û�ϵ�
		String[] subItem10={"��/��/�� ����", "û�ֽ�", "���ֽ�", "��õ��", "û����", "������", "��õ��", "������",
					"��õ��", "���걺", "������", "�ܾ籺", "����" };

		//11. ��û����
		String[] subItem11={ "��/��/�� ����", "õ�Ƚ�", "���ֽ�", "���ɽ�", "�ƻ��", "�����", "����", "����", "������", "�ݻ걺", "���ⱺ", "�ο���", "��õ��", "û�籺", "ȫ����",
				"���걺", "�¾ȱ�", "������" };

		//12. ����ϵ�
		String[] subItem12={ "��/��/�� ����", "���ֽ�", "�����", "�ͻ��", "������", "������", "������", "���ֱ�", 
					"���ȱ�", "���ֱ�", "�����", "�ӽǱ�", "��â��", "��â��", "�ξȱ�" };

		//13. ���󳲵�
		String[] subItem13={ "��/��/�� ����", "������", "������", "��õ��", "���ֽ�", "�����", "��籺", "���", "���ʱ�", "���ﱺ", "������", "ȭ����", "���ﱺ", "������", "�س���",
				"���ϱ�", "���ȱ�", "����", "������", "�强��", "�ϵ���", "������", "�žȱ�" };

		//14. ���ϵ�
		String[] subItem14={ "��/��/�� ����", "���׽�", "���ֽ�", "��õ��", "�ȵ���", "���̽�", "���ֽ�", "��õ��", "���ֽ�", "�����", "����", "������", "�Ǽ���", "û�۱�", "���籺",
				"������", "û����", "��ɱ�", "���ֱ�", "ĥ�", "��õ��", "��ȭ��", "������", "�︪��" };

		//15. ��󳲵�
		String[] subItem15={ "��/��/�� ����", "â����", "�����", "���ֽ�", "���ؽ�", "�뿵��", "��õ��", "���ؽ�", "�о��", "������", "����", "�Ƿɱ�", "�Ծȱ�", "â�籺", "����",
				"���ر�", "�ϵ���", "��û��", "�Ծ籺", "��â��", "��õ��" };

		//16. ���ֵ�
		String[] subItem16={ "��/��/�� ����", "���ֽ�", "��������" };
		


		// �����޺��ڽ��� �����.
		cityOption = new JComboBox();

		// �ؽ����̺� �ִ´�.
		// �ؽ����̺� - key��: String / value��: String[] �迭
		cityOptionTable.put(mcities[0], subItem0);//0. ����Ư����
		cityOptionTable.put(mcities[1], subItem1);// 1. �λ걤����
		cityOptionTable.put(mcities[2], subItem2);// 2. �뱸 ������
		cityOptionTable.put(mcities[3], subItem3);//3.��õ������
		cityOptionTable.put(mcities[4], subItem4);//4. ���ֱ�����
		cityOptionTable.put(mcities[5], subItem5);//5. ����������
		cityOptionTable.put(mcities[6], subItem6);//6. ��걤����
//		cityOptionTable.put(mcities[7], subItem7);//7. ����Ư����ġ��
		cityOptionTable.put(mcities[8], subItem8);//8. ��⵵
		cityOptionTable.put(mcities[9], subItem9);//9. ������
		cityOptionTable.put(mcities[10], subItem10);//10. ��û�ϵ�
		cityOptionTable.put(mcities[11], subItem11);//11. ��û����
		cityOptionTable.put(mcities[12], subItem12);//12. ����ϵ�
		cityOptionTable.put(mcities[13], subItem13);//13. ���󳲵�
		cityOptionTable.put(mcities[14], subItem14);//14. ���ϵ�
		cityOptionTable.put(mcities[15], subItem15);//15. ��󳲵�
		cityOptionTable.put(mcities[16], subItem16);//16. ���ֵ�
		


		inputCityPanel.add(cityOption, "South"); // cityOption������Ʈ�� inputCityPanel�� �ִ´�.
		changeAddressPanel.add(inputCityPanel);

		// 3. ���ּ� �Է�
		JLabel detailedAddressLabel = new JLabel("�� �ּ�");
		detailedAddressLabel.setHorizontalAlignment(JLabel.CENTER);
		changeAddressPanel.add(detailedAddressLabel);

		// JTextField ������Ʈ
		detailedAddress = new JTextField(25);
		changeAddressPanel.add(detailedAddress);
		add(changeAddressPanel, "Center"); // �����гο� �޺��ڽ� ������Ʈ�г��� �ִ´�.

		// (��)
		JButton submitButton = new JButton("�ּ� �����ϱ�");
		submitButton.addActionListener(new GoUpdateAddressListener());
		add(submitButton, "Center");
		mf.add(this); // �����ӿ� �����г��� ����

		//MyPage�� ���ư��� ��ư
		JButton BackButton = new JButton("������������ ���ư���");
		BackButton.addActionListener(new GoMyPageListener());
		add(BackButton,"Center");
		mf.add(this);
		
		
		// ������ġ (��) �����Ҷ� -> ��/�� �޺��ڽ� ������ �޶���.
		mcityOption.addActionListener(new ActionListener() {
			// �ø� �����ϸ�, ������ġ�ÿ� ���ϴ� ���� cityOption�� ��Ÿ����.

			@Override
			public void actionPerformed(ActionEvent e) {
				// �� ����(Ű��)
				String mcity = (String) mcityOption.getSelectedItem();
				
				//�ؽ����̺�cityOptionTable ���� Ű���� �̴´�.
				Object o = cityOptionTable.get(mcity);
				
				if(o==null) {
					cityOption.setModel(new DefaultComboBoxModel());
				}else {
					cityOption.setModel(new DefaultComboBoxModel((String[])o));
				}
			}
		});
	}

	// submitButton ��ư�� ������ ����
	// 3���� �Է¶��� ��� �Էµ� ���¶��?
	class GoUpdateAddressListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			inputMcity=(String) mcityOption.getSelectedItem();
			int mcityIdx= mcityOption.getSelectedIndex(); //17������ ������~
			
			inputCity= (String) cityOption.getSelectedItem();
			int cityIdx= cityOption.getSelectedIndex(); //0������ ������~
			
			System.out.println("��: "+inputMcity);
			System.out.println("��/��: "+inputCity);
			System.out.println("cityIdx: "+ cityIdx);
			
			
			if(inputCity==null || cityIdx==0)
				inputCity="";
			
			inputDetailAddr  =detailedAddress.getText();
			System.out.println("���ּ�: "+ inputDetailAddr);
			
			
			if(mcityIdx==17 || inputDetailAddr.length()==0) {
				JOptionPane.showMessageDialog(null, "�ּҸ� �����Ͻð�, ���ּҸ� �������ּ���!", "�ּ� ���� ����", JOptionPane.ERROR_MESSAGE);
				
			}else {
				//�ּ� ����
				String newAddr=inputMcity+" "+inputCity+" "+ inputDetailAddr;
				System.out.println("�����ּ�: "+ newAddr);
				System.out.println("id: "+ id);
				mc.changeAddress(id, newAddr); //<= ���⼭ Null Pointer Exception�߻� 
				JOptionPane.showMessageDialog(null, "�ּҺ����� �����߽��ϴ�.", "�ּ� ���� ����", JOptionPane.PLAIN_MESSAGE);
				changePanel(new LoginAfter(mf, id));
			}
		}
	}
	
	
	//MyPage�� ���ư��� ��ư�׼��߰�
	class  GoMyPageListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			changePanel(new MyPage(mf,me));
		}
	}	

	
	// �г� ���� - �ּ� ������ ���������� �̷����� LoginAfter�������� �̵�
	public void changePanel(JPanel panel) {
		mf.remove(this); // ���� �г��� �����
		mf.add(panel);// ���ο� �гη� �ٲ۴�.

		mf.revalidate();
		mf.repaint();
	}
}
