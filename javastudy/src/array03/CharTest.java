package array03;

import java.util.Random;
import java.util.Scanner;

public class CharTest {		
	public static void main(String[] args) {
		System.out.println("���� �����Ͽ����ϴ�. ���߾� ������.");
		System.out.println("0-99");
		
		Random r = new Random();
		int k = r.nextInt(100);
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			int num = sc.nextInt();
			
			if(k<num) {
				System.out.println("�� ����");
			} else if(k>num) {
				System.out.println("�� ����");
			} else {
				System.out.println("�¾ҽ��ϴ�.");
				System.out.println("�ٽ��Ͻðڽ��ϱ�(y/n)>>");
				String check = sc.next();
				if (check.equals("n")) {
					break;
				}
			}
			  
		}
	
	}
		
	}


