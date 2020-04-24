package ch13;

abstract class Ani {
	abstract void sound(); 
}

class Cat extends Ani {
	@Override
	void sound() {
		System.out.println("�Ŀ�");
	}
}

class Bird extends Ani {
	@Override
	void sound() {
		System.out.println("±±");
	}
}

class Fish extends Ani {
	@Override
	void sound() {
		System.out.println("��������");
	}
}


public class AnomyEx04 {
	
	static void start(Ani a) {
		a.sound();
	}
	//�ѹ��� �����ǰ� �� �� ���. new Ani
	public static void main(String[] args) {
		start(new Cat());
		start(new Fish());
		start(new Ani() {
			
			@Override
			void sound() {
				System.out.println("����");
			}
		});
	}

}
