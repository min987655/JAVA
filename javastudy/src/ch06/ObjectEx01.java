package ch06;

class Animal {
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}

public class ObjectEx01 {
	public static void main(String[] args) {
		String d1 = "��";
		String d2 = "��";

		System.out.println(d1.equals(d2)); // ���۷��� �˻� �� ���� �˻�
		System.out.println(d1 == d2); // ���� ������ �ֱ� ������ true

		String d3 = new String("��"); 
		String d4 = new String("��");

		System.out.println(d3.equals(d4)); // ���۷��� �˻� �� ���� �˻�
		System.out.println(d3 == d4); // �ٸ� ������ �ֱ� ������ false
		
		System.out.println(d3.getClass()); // class�� ����(��ο� �̸�) ����
		System.out.println(new ObjectEx01().getClass());
		
		// �ؽ��ڵ� -> �ؽ� �˰��� = ������ ������ ���ڷ� ����
		System.out.println(d3.hashCode());
		System.out.println(d4.hashCode()); // String�� ���� ������ ���� �ؽ��� �������� �������̵� ������.
		
		Animal a1 = new Animal();
		Animal a2 = new Animal();
		
		System.out.println(a1.hashCode()); // ���� ��ü���� �� �� �� ���.
		System.out.println(a2.hashCode()); // String �ƴϱ� ������ �ٸ� ������ �ִ� �� ���� �ؽ� ����.
		
		System.out.println(a1 instanceof Animal); // Ÿ�� Ȯ��
		System.out.println(a2 instanceof Animal);
		}

}
