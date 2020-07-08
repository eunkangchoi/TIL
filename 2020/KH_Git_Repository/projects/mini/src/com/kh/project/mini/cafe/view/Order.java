package com.kh.project.mini.cafe.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.kh.project.mini.cafe.controller.MemberController;
import com.kh.project.mini.cafe.model.vo.Member;

public class Order extends JPanel {

	private MemberController mc = new MemberController();
	private MainFrame mf;
	private Member me;
	private String temp;
	private String id;

	public Order(MainFrame mf, Member me) {
		System.out.println("�ֹ��ϱ� â");
		this.mf = mf;
		this.me = me;
		this.id = me.getId();
		mf.setTitle("�ֹ��ϱ�");
		setLayout(new GridLayout(5, 5, 20, 20));

		System.out.println("me: " + me);

		String name = me.getName();
		JLabel label = new JLabel(name + " �� ������ �ֹ��Ͻðڽ��ϱ�?");
		label.setHorizontalAlignment(JLabel.CENTER);
		add(label);

		String[] drinks = { "�Ƹ޸�ī��", "ī���", "ī���ī", "�ٴҶ��", "ī��Ḷ���ƶ�", "��׷���", "ĳ����", "���۹�Ʈ", "����" };

		JList<String> drinksList = new JList<String>(drinks);

		JPanel resultPanel = new JPanel();
		JLabel choicelabel = new JLabel("�����Ͻ� �޴�");
		JTextArea textArea = new JTextArea(20, 25);
		textArea.setLineWrap(true);
		textArea.setEditable(false);

		// drinkList ��� �޴���ư�� Ŭ���ϸ� �����ϴ� �Լ�.
		drinksList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				List<String> l = drinksList.getSelectedValuesList();
				String result = " ";
				for (int i = 0; i < l.size(); i++) {
					result += l.get(i) + " ";
				}
				temp = result; // ������ �޴�
				textArea.setText(result); // ������ �޴��� �ؽ�Ʈ������ ǥ��.

			}
		});

		resultPanel.add(choicelabel);
		resultPanel.add(textArea);
		add(resultPanel, "Center");

		JScrollPane scroller = new JScrollPane(drinksList);
		scroller.setPreferredSize(new Dimension(200, 100));
		add(scroller, "North");

		drinksList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		JButton deliveryBnt = new JButton("�ֹ��ϱ�");
		add(deliveryBnt, "South");

		// �ֹ��ϱ� ��ư�� ������ - GoDeliveryPageListener �̺�Ʈ ������ ����
		deliveryBnt.addActionListener(new GoDeliveryPageListener());

		JButton logoutBtn = new JButton("������������ ���ư���");
		add(logoutBtn, "South");
		logoutBtn.addActionListener(new GoLogInAfterPageListener());

		mf.add(this);

	}

	class GoDeliveryPageListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int result = JOptionPane.showConfirmDialog(null, "�����Ͻ� �޴��� �ֹ��Ͻðڽ��ϱ�?", "�ֹ��ϱ�", JOptionPane.YES_NO_OPTION);
			// �� ������=> �ֹ��� �Ϸ�Ǿ����ϴ� �˸�â �߱�

			if (result == JOptionPane.YES_OPTION) 
			{
				JOptionPane.showMessageDialog(null, "�ֹ��� �Ϸ�Ǿ����ϴ� ! ���� ~ ", "Order", JOptionPane.INFORMATION_MESSAGE);
				String orderHistory = me.getOrderHistory();
//				System.out.println("[���Ȯ��]orderHistory=> " + orderHistory);

				// �ֹ����� �ð� ����ϱ�
				SimpleDateFormat date = new SimpleDateFormat("yyyy�� MM��dd�� HH��mm��ss��");
				Date time = new Date();
				
				//�ֹ� �ð�.
				String orderTime = date.format(time);
				System.out.println("\n\torderTime => "+ orderTime);

				//�ֹ��� ���� ��ȸ
//				String orderInfo= orderTime +" : "+ temp ;

				String updatedOrderHistory;
				if (orderHistory == null) {
					// �ֹ��ð��� null�̶��
					updatedOrderHistory = orderTime + " : " + temp;
				} else {
					// �ֹ��ð��� null�� �ƴϴ�.
//					System.out.println("\t\t date=>" + date);
					updatedOrderHistory = orderHistory + "\n" + orderTime + " : " + temp;
				}

				System.out.println("[���Ȯ��]updatedOrderHistory => " + updatedOrderHistory);
				System.out.println("temp: " + me.getOrderHistory());
				mc.updateOrderHistory(id, updatedOrderHistory);

//				setVisible(false);
				changePanel(new LoginAfter(mf, id));
				setVisible(true);
				
			} else { // if(result == JOptionPane.NO_OPTION)
				JOptionPane.showMessageDialog(null, "�ֹ��� ����ϼ̽��ϴ�. ", "Cancle", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	// �ڷΰ��� ��ư Ŭ���� �̺�Ʈ ����
	class GoLogInAfterPageListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int result = JOptionPane.showConfirmDialog(null, "���ư��ðڽ��ϱ�?", "�ڷΰ���", JOptionPane.YES_NO_OPTION);
			// â �ݱ�, ��, �ƴϿ� ��ư Ŭ���� �̺�Ʈ ����
			if (result == JOptionPane.CLOSED_OPTION) {
				System.exit(0);
			} else if (result == JOptionPane.YES_OPTION) {
				// Yes ��ư�� ������ �α��� ���� ȭ������ ���ư���
				setVisible(false);
				changePanel(new LoginAfter(mf, id));
			}
		}
	}

	public void changePanel(JPanel panel) {
		mf.remove(this);
		mf.add(panel);
		mf.revalidate();
		mf.repaint();

	}

	public static String choicelabel() {
		return null;
	}
}
