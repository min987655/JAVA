package ch05;

// �� ��Ʈ��Ʈ
abstract class Animal {
	abstract String getName();
}

class Mouse extends Animal{
	
	final String NAME="��";
	
	@Override
	String getName() {
		return NAME;
	}
}

class Dog extends Animal{
	final String NAME = "������";
	
	@Override
	String getName() {
		return NAME;
	}
}

class Cat extends Animal {
	final String NAME = "�����";
	
	@Override
	String getName() {
		return NAME;
	}
}

class Bird extends Animal {
	final String NAME = "��";
	
	@Override
	String getName() {
		return NAME;
	}
}

class Tiger extends Animal{
	final String NAME = "ȣ����";
	@Override
	String getName() {
		return NAME;
	}
}

public class AnimalEx01 {

	static void attack(Animal u1, Animal u2) {
		System.out.println(u2.getName()+"�� "+u1.getName()+"���� ���ݴ��߽��ϴ�.");
	}
	
	public static void main(String[] args) {
		Dog d1 = new Dog();
		Cat c1 = new Cat();
		Bird b1 = new Bird();
		Tiger t1 = new Tiger();
		
		System.out.println(d1.NAME+" ź��");
		System.out.println(c1.NAME+" ź��");
		System.out.println(b1.NAME+" ź��");		


		attack(d1, c1);	
		attack(d1, b1);
		attack(c1, b1);
		attack(t1, c1);
	
	}

}