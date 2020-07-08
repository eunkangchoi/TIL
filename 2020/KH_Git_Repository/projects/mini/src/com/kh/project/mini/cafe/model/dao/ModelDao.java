package com.kh.project.mini.cafe.model.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;

import com.kh.project.mini.cafe.model.vo.Member;


// ��ü ����ȭ�� �̿��Ѵ�.
public class ModelDao {
	//��ϵ� �����ü�� ���� �ϴ� ����
	private String enrolledMembersFile="members.txt";
	
	// ���Ͽ��� �ҷ��� ��ü�� �����ϴ� ����Ʈ.
//	private List<Member> memberList = new ArrayList<Member>();
	private HashMap<String, Member> map;
	
	
	// ������ �о�´�. //
	// ���Ͽ� ������ ��ü�� �ҷ��ͼ� �ؽø����·� �����ϰ�
	public HashMap<String, Member> fileOpen() {
		map = new HashMap<String, Member>();
		
		//��ü ����ȭ
		try(	//������Ʈ���� �����.
				ObjectInputStream ois= new ObjectInputStream(
				new FileInputStream(enrolledMembersFile));) 
		{
			
			Member nowMember;
			
			//���Ͽ��� �о�� �����ü�� �����Ѵٸ� ����Ʈ�� �߰�.
			while((nowMember=(Member)ois.readObject())!=null) {
				System.out.println(nowMember);
				map.put( nowMember.getId(), nowMember );
				// key: nowMember�� id�ʵ�
				// value: Member��ü
			}
			
			ois.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(EOFException e) {
			//������ ���о���~
		}catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	// ������ �����Ѵ�. //
	// Member ������ �����Ѵ�.
	public void fileSave(HashMap<String, Member> updatedMap) {
		try(	
				//��ݽ�Ʈ���� ���� �����.
				FileOutputStream fos =new FileOutputStream(enrolledMembersFile);
				
				//������Ʈ���� �����.
				ObjectOutputStream oos =new ObjectOutputStream(fos);)
		{
			//���� �ҷ��ͼ� ��ü ����.
			for(Entry<String, Member> e : updatedMap.entrySet()) {
				// ��Ʈ���� value�� ����.
				// ������ value�� Member��ü�ε� , Member��ü�ȿ��� ���̵��ʵ尡 ����.
				oos.writeObject(e.getValue());
			}
			
			oos.close();
			fos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
