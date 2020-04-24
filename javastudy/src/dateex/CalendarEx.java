package dateex;

import java.util.Calendar;

public class CalendarEx {

	// DB Ÿ�� : 2020-03-18:22:11:05
	public static void printCalender(String msg, Calendar cal) {
		int year = cal.get(Calendar.YEAR); // 2020
		int month = cal.get(Calendar.MONTH) + 1; // 4(0���� �����⶧���� +1����� ��
		// DB Ÿ�Կ� ���߱� ���� 0 ����.
		String mon = (month < 10) ? "0" + month : "" + month; // 3�� ������ (DECODE)
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int hour = cal.get(Calendar.HOUR);
		String h = (hour < 10) ? "0" + hour : "" + hour;
		int minte = cal.get(Calendar.MINUTE);
		String m = (minte < 10) ? "0" + minte : "" + minte;
		int second = cal.get(Calendar.SECOND);
		String s = (second < 10) ? "0" + second : "" + second;
		System.out.println(year + "-" + mon + "-" + day + ":" + hour + ":" + minte + ":" + second);
	}

	public static void main(String[] args) {
		Calendar now = Calendar.getInstance(); // �̱���
		printCalender("����", now);
	}
}