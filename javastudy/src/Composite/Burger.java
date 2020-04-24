package Composite;

import lombok.Data;

@Data // Getter, Setter ����
public class Burger {
	private int price;
	private String desc;
	
	// �⺻ ���� ����
	public Burger() {
		this(1500,"�⺻ ����");
	}
	// ���� ������
	// Ư�� ���� �� ���� �ϴ� ȣ���ϴ� ���� ȿ����
	public Burger(int price, String desc) {
		this.price = price;
		this.desc = desc;
		System.out.println(desc + "�� ����������ϴ�.");
	}
}
