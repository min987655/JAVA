package ch06;

class Person {
	String name ="ȫ�浿";
	int age = 15;
	String job ="�л�";
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", job=" + job + "]";
	}

}

public class ObjectEx02 {

	public static void main(String[] args) {
		Integer num = 10;
		String s = num.toString(); // ��� ������Ʈ�� toString �� �� ����
		System.out.println(s);

		Person p = new Person();
		System.out.println(p.toString()); // (toString�� ������ : class @ �ؽ��ڵ�) �������ؼ� ��Ʈ��Ÿ���� ���ϵǰ� ��.
		System.out.println(p); // ��ü�� ȣ���ϸ� �ڵ����� toString ȣ�� ��.
		
		StringBuilder sb = new StringBuilder();
		sb.append("�ȳ�");
		sb.append("�ݰ���");
		
		System.out.println(sb.toString());
		                                                                                                                                      
	}

}
