package ch15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class NetworkEx02 {

	public static void main(String[] args) {

		try {
			// 1. �ּ� ��ü ����� - �ڵ����� �Ľ��� ��
			URL url = new URL("https://www.naver.com");

			// 2. ��Ʈ�� ����
			HttpURLConnection con = 
					(HttpURLConnection) url.openConnection();
			
			// 3. ���� ����(���ڿ�)
			BufferedReader br = 
					new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
			
			//tip : ���Ͽ� ��Ʈ�� ����
			FileWriter fr = new FileWriter("c:\\utils\\test.html");
			
			// 4. ���� ���ϱ�
			StringBuilder sb = new StringBuilder();
			
			String input = "";
			while((input = br.readLine()) != null) {
				sb.append(input);
			}
			fr.write(sb.toString());
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
