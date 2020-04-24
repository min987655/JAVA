package chat;

interface StarUnit {
	abstract int gethp();
}

abstract class Protoss implements StarUnit {
	abstract int getSh(); // ����� �����佺�� ������ ����
}

abstract class Zerg implements StarUnit {
}

class Zealot extends Protoss {
	int sh = 100;
	int hp = 100;

	@Override
	public int gethp() {
		return hp;
	}
	
	@Override
	public int getSh() {
		return sh;
	}
}

class Ultra extends Zerg {
	int hp = 100;
	
	@Override
	public int gethp() {
		return hp;
	}
}

public class FinallyEx01 {
	
	// ���� üũ(hp, sh)
	static void check(StarUnit unit) {
		try {
			Zealot z = (Zealot)unit;
			System.out.println("���� ����� : " + z.getSh());	
			System.out.println("���� ü���� : " + z.gethp());	
			
		} catch (Exception e) {
			// ����
			Ultra u = (Ultra)unit;
			System.out.println("���״� ���尡 �����ϴ�.");
			System.out.println("���� ü���� : "+u.gethp());			
		} finally {
			System.out.println("���� ������ ���� ��");
		}
		
	}

	public static void main(String[] args) {
		check(new Zealot());
		check(new Ultra());
	}

}