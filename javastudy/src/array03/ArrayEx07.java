package array03;

import java.util.Scanner;

public class ArrayEx07 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("6�ڸ� ���ڸ� �Է��ϼ���.");
		int num = sc.nextInt();
		
		String strNum = num + "";
		
		int len = strNum.length();
		boolean check = false;
		for (int i = 0; i < len; i++) {
			if(strNum.charAt(i) == '3') {
				check = true;
			}
		}
		
		if(check) {
			System.out.println("3�� �����մϴ�.");
		} else {
			System.out.println("3�� �������� �ʽ��ϴ�.");
		}
		
		/*System.out.println(strNum.charAt(2));
		System.out.println(strNum.length());*/
		
	}

}
