package stars.terran;

import stars.Behavior;

public abstract class Terran implements Behavior {
	
	public void move() {
		System.out.println("�̵�");
	}
	public void repair() {
		System.out.println("SCV ġ��");
	}
	
	public static void upgrade() {
		Tank.attack++;
		Marine.attack++;
		System.out.println("�׶� ���׷��̵� �Ϸ�");
	}
}
