package dateex;

public class MathEx {
	public static void main(String[] args) {
		System.out.println(Math.PI);
		System.out.println(Math.ceil(1.5));
		System.out.println(Math.floor(1.5));
		System.out.println(Math.sqrt(9));
		System.out.println(Math.exp(2));
		System.out.println(Math.round(3.14));

		// [1, 45] ������ ������ ���� 5�� �߻�
		System.out.println("�̹��� ����� ��ȣ�� ");
		for (int i = 0; i < 5; i++) {
			System.out.println((int) (Math.random() * 45 + 1) + " "); // ���� �߻�
		}
	}

}