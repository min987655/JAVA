package coffeeshop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Ŭ���� �ڷ��� (Beans) : å�Ӿ��� �����͸� �ִ� ������Ʈ = DB Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
	private String name;
	private int price;
}
