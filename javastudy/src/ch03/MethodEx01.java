package ch03;

// �޼���(�Լ�)�� �޼���(�Լ�)�ȿ� �ۼ��� �� ����.
// �޼���(�Լ�)�� Ŭ�����ȿ� �ۼ��� �� �ִ�.
public class MethodEx01 {
	
	
	public static void main(String[] args) {
		int num = Cal.add(3, 5);
		System.out.println("���ϱ�:"+num);
		
		int num1 = Cal.minus(3, 5);
		System.out.println("����:"+num1);
		
		int num2 = Cal.multi(3, 5);
		System.out.println("���ϱ�:"+num2);
		
		int num3 = Cal.divibe(5, 3);
		System.out.println("������:"+num3);
	}
	
}