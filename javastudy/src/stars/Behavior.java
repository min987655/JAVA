package stars;

// �߻�Ŭ������ ���������� 
// �߻� �޼��常 ���� ����
public interface Behavior {

	public abstract void move();

	void repair();

	void attack(Behavior unit);

}