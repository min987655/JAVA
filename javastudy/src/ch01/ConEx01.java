package ch01;

import java.util.Scanner;

public class ConEx01 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean check = sc.nextBoolean();
		
		if (check) {// boolean 1bit ����� �� 2���� (True or false)
			System.out.println("���Դϴ�.");
		} else {
			System.out.println("�����Դϴ�.");
		}

	}

}