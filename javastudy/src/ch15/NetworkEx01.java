package ch15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkEx01 {

	public static void main(String[] args) {

		try {
			// 1. �ּ� ��ü ����� - �ڵ����� �Ľ��� ��
			URL url = new URL(
					"http://openapi.tago.go.kr/openapi/service/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey=XiDeOhe%2FX427It5zoMfoELqSWyIrFm6LicOSj7n6vJBEiEMZq7tWzLt7Oc1f1WYh9KiwAmWZqrVvaEuS15TlFA%3D%3D&numOfRows=50&pageNo=1&depAirportId=NAARKJJ&arrAirportId=NAARKPC&depPlandTime=20200407&_type=json");

			// 2. ��Ʈ�� ����
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			// 3. ���� ����(���ڿ�)
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

			// 4. ���� ���ϱ�
			StringBuilder sb = new StringBuilder();

			String input = "";
			while ((input = br.readLine()) != null) {
				sb.append(input);
			}

			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
