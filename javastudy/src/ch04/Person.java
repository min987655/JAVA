package ch04;


// ���¿� �ൿ
public class Person {
	String name;
	String job;
	int age;
	char gender;
	String bloodType;
	
	
	//�����ε� : ���� �̸��� �Լ��� �����Ͽ� ���� ��Ŵ. - �Ű������� ������ Ÿ���� �ٸ��� �ٸ��� �ν�.
	//�ϴ� ���� �̸��� �Լ��� �ִµ� ������ �ȳ�.
	//System.out.println() 
	//����������(�Ű������� ���� ����) = �ʱ�ȭ : Ŭ���� �̸��� ����.
	public Person() {

	}
	//�Ű������� �ִ� ������
	public Person(String name, String job, int age, char gender, String bloodType) {
		this.name = name;
		this.job = job;
		this.age = age;
		this.gender = gender;
		this.bloodType = bloodType;
	}
	
	
}
