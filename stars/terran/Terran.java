package stars.terran;

import stars.Behavior;

public abstract class Terran implements Behavior{
	@Override
	public void move() {
		System.out.println("�̵�");
	}
	@Override
	public void repair() {
		System.out.println("SCV ġ��");	
	}
	public static void upgrade() {
		Marine.attack++;
		Tank.attack++;
	}
}
