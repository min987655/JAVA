package stars;

import stars.protoss.Dragoon;
import stars.protoss.Protoss;
import stars.protoss.Zealot;
import stars.terran.Marine;
import stars.terran.Tank;
import stars.terran.Terran;
import stars.zerg.Hydra;
import stars.zerg.Ultra;
import stars.zerg.Zerg;

public class StartGame {
	
	public static void move(Behavior b) {
		b.move();
	}
	public static void repair(Behavior b) {
		b.repair();
	}
	public static void attack(Behavior b1, Behavior b2) {
		b1.attack(b2);
	}
	
	public static void main(String[] args) {
		Protoss.upgrade();
		Zealot z1 = new Zealot("����1");
		Dragoon d1 = new Dragoon("���1");
		
		Terran.upgrade();
		Marine m1 = new Marine("����1");
		Tank t1 = new Tank("��ũ1");
		
		Zerg.upgrade();
		Hydra h1 = new Hydra("�����1");
		Ultra u1 = new Ultra("��Ʈ��1");
		
		
		move(z1);
		repair(z1);
		attack(z1,d1);
	}

}
