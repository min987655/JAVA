package animal;

// 1. ���� : public static final ����
// 2. �Լ� : public abstrack ����
// 3. ����� ���� - �������ε�
// 4. ������ �߻�޼��常 ���簡�� = ������ �ο�(���� ��)
// 5. new �� �� ����.(�ڽ��� new�ؼ� ���� ��� �� �ۿ� ����)
// 6. �������� �ش�.(���� �ڵ�)
interface Cal {
	public static final int num = 10;
}

public class InterfaceEx01 {

	public static void main(String[] args) {
		System.out.println(Cal.num);
	}

}
