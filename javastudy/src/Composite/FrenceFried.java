package Composite;

import lombok.Data;

@Data
public class FrenceFried {
	private int price;
	private String desc;

	public FrenceFried() {
		this(2000,"����Ĩ");
	}
	
	public FrenceFried(int price, String desc) {
		this.price = price;
		this.desc = desc;
		System.out.println(desc+"�� ����������ϴ�.");
	}
}
