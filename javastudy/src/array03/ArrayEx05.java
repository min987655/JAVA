package array03;

import java.util.Scanner;

/*
 * ���� 1000000000 �� �Է¹ް� (10��)
 * 3�ڸ� ���� �޸��� �� ��� !
 * 1,000,000,000
 */

public class ArrayEx05 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		String tempNum = num + "";
		String tempNum2[] = tempNum.split("");

		/*
		 * for (int i = 0 ; i < tempNum2.length; i++) { System.out.print(tempNum2[i]);
		 * if(i%3==0 && i!=tempNum2.length-1) { System.out.print(","); } }
		 */
		int len = tempNum2.length; // len = 4

		for (int i = 0; i < len; i++) { // 4�� ���� for��
			if ((len - i) % 3 == 0 && i != 0) { // 4%3, 3%3, 2%3, 1%3
				System.out.print(",");
			}
			System.out.print(tempNum2[i]);
		}
		
		/* �ڸ��� ����� ���� ����
		int p = (tempNum2.length % 3);
		for (int i = 0; i < tempNum2.length; i++) {
			// ù°�ڸ� �տ� ,�� ������ �ȵǴ� ù ��������.
			if (i == 0)
				tempNum2[i] = "" + tempNum2[i];
			// i�� 3���� ���� ���� p���� ������ �տ� ,�� ����
			else if (i % 3 == p)
				tempNum2[i] = "," + tempNum2[i];
			// �ڸ��� ��� ���
			System.out.print(tempNum2[i]);
		}*/

	}

}
