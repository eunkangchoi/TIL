package com.kh.project.mini.cafe.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.kh.project.mini.cafe.model.dao.ModelDao;
import com.kh.project.mini.cafe.model.vo.Member;

public class MemberController {
	private HashMap<String, Member> map = new HashMap<String, Member>();
	private ModelDao md =new ModelDao();
//// map�� ������ ������ �ִ´�.(���ѹ��� �ִ´�)
//	public final void inputRoot() {
//		map.put("root", new Member("root", "root", "20200101", "None"));
//	}
//
//	public MemberController() {
//		inputRoot();
//		}
//
////	// �����̵� ��������� ������Ʈ�Ѵ�.
//	public Member updateMemberInfo(String id) {
//		return map.get(id);
//	}
	
	public MemberController() {
		//dao���� ���� �ҷ��´�.
		map=md.fileOpen();
		
		//���Ե� ȸ���� ������ ��ȸ.
		showEnrolledMember();
	}
	
	//���̵� �ش��ϴ� ȸ����ü�� ����´�.
	public Member getMemberFromID(String id) {
		return map.get(id);
		//�����ϸ� ��ü��, �������� ������ null�� ��ȯ
	}
	

	// ȸ������
	public boolean memberJoinMenu(String id, Member m) {
		// ���̵�(hash map�� Key)�� �ߺ��Ǵ��� Ȯ��
		if (!map.containsKey(id)) {
			map.put(id, m);
			return true;
		}
		return false;
	}

	// �α��� Ȯ��
	// �̹� ���Ե� ȸ���̶�� -> ȸ���� �̸��� ����.
	public Member logIn(String id, String password) {
		// map�� �����ϴ� ���̵�?
		if (map.containsKey(id)) {
			// �̹� ���Ե� ȸ���̶��
			Member m = (Member) map.get(id);// key(id)�� ���� value(id�� �����ϴ� Member��ü)
			String savedPw = m.getPwd();// �н����带 ����´�.

			// ��ϵ� ��й�ȣ�� ������?
			if (savedPw.equals(password))
				return m;
		}
		return null; // ����.
	}

	// �ߺ��� ���̵� ���� ȸ������ Ȯ��.
	public boolean isDuplicatedMember(String id) {
		return map.containsKey(id) ? true : false;
	}

	// ȸ�������� �����ϸ�, map�� ����Ѵ�.
	public void joinMember(String id, Member newMember) {
		map.put(id, newMember); 
		//map�� �߰��ϰ�
		//map�� �ƿ� ���� �����..
		md.fileSave(map);
	}

	// ȸ�� ��й�ȣ ����.
	public void changePassword(String id, String newPw) {
		Member m = map.get(id); // id�� �����ϴ� value(Member)�� ����´�.
		m.setPwd(newPw);//����
		md.fileSave(map); //���������� �����.
	}

	// ȸ�� �̸� ����
	public void changeName(String id, String newName) {
		Member m = map.get(id);
		m.setName(newName); //����
		md.fileSave(map); //���������� �����.
	}


	// ȸ�� �ּҰ� ����
	public void changeAddress(String id, String newAddress) {
		Member m = map.get(id);
		System.out.println("[controller-changeAddress] m: "+m);
		m.setAddress(newAddress);//����
		md.fileSave(map); //���������� �����.
	}
	
	//�ֹ����� ǥ��
	public void updateOrderHistory(String id, String history) {
		Member m=map.get(id);
		
		//�ֹ�Ƚ�� ī��Ʈ
		int orderCnt=m.getOrderCnt()+1;
		
		//�ֹ�Ƚ���� ���Ḯ��Ʈ ����.
		m.setOrderCnt(orderCnt);
		m.setOrderHistory(history);
		
		md.fileSave(map);
	}
	
	

	// ȸ��Ż��
	public Member dropOutMember(String id) {
		Member dropOuted = map.remove(id);
		md.fileSave(map); //Ż������ map�� ����.
		return dropOuted;
	}

	
	//
	// ���� ��ϵ� ȸ������ �����ش�.
	public HashMap<String, Member> enrolledMembers() {
		return map;
	}
	
	public void showEnrolledMember() {
		//���� ��ϵ� ȸ�� ��ȸ
		for(Entry<String, Member> e: map.entrySet()) {
			System.out.println("id: "+e.getKey() +"\t=>"+e.getValue() );
		}
	}
	

	// ���ο� ��� ����/ ���� ���Ż��/ ��� ���� ���� �� �߻��Ѱ��
	// enrolled.txt �� ��� ������ ������Ʈ �Ѵ�.
	// ���� ��ϵ� ������� �����Ѵ�. -> (review - 1�ܰ�) ��������� io�� �̿��Ͽ�
	// (upgrade - 2�ܰ�) mysql db�� ����.


	// ��й�ȣ(char�� �迭)�� ���ڿ��� ��Ÿ����
	public String getPassWordToText(char[] passWords) {
		String cArrToString = ""; // ���� �迭���� ���ڿ��� ��ȯ
		for (int i = 0; i < passWords.length; i++)
			cArrToString += passWords[i];

		return cArrToString;
	}
	
	

}