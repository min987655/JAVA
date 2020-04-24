package dateex;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

// (������ �ڹ� 1.7 ����)��¥ ��Ű����
// java.sql.Date 
// java.sql.Time (X) 
// java.sql.Timestamp
// java.util.Calender (X)
// java.util.Date

// �Ź��� �ڹ� 1.8 �̻�
// java.time.LocalDate
// java.time.LocalDateTime
// java.time.LocalTime

public class TimeEx01 {

	public static void main(String[] args) {
		// 1. Calendar �Ⱦ�
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String today = formater.format(cal.getTime());
		System.out.println("cal.getTime() : " + cal.getTime());
		System.out.println("today : " + today);

		// 2. java.util.Date �Ⱦ�
		// Date�� 1970.01.01 �������� ����� �и��ʸ� ���
		Date time2 = new Date();
		System.out.println("time2 : " + time2);
		System.out.println("time2.getTime() : " + time2.getTime());
//		System.out.println("time2.getDay() : "+time2.getDay()); // �Ⱦ�

		// 3. java.sql.Date �Ⱦ�
		java.sql.Date time3 = new java.sql.Date(24 * 3600 * 1000);
		System.out.println("time3 : " + time3);

		// 4. java.sql.Timestamp : DB���� �ֱ⶧���� ���� ����.
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date d = new Date();
		String timestamp = sf.format(d.getTime());
		Timestamp ts = Timestamp.valueOf(timestamp);
		System.out.println("ts : " + ts);

		// 5. LocalDateTime : DB�� �ȵ������ -> LocalDate�� TimeStamp�� ��Ƽ� ��� �ѱ�
		// (LocalDateTime to Timestamp)
		Timestamp nowDate = Timestamp.valueOf(LocalDateTime.now()); // LocalDate --> TimeStamp
		System.out.println("nowDate : " + nowDate);

		// ((Timestamp to LocalDateTime)
		LocalDateTime ldt = nowDate.toLocalDateTime();
		System.out.println("ldt : " + ldt);

		System.out.println("LocalDateTime : " + LocalDateTime.now());
		System.out.println("LocalDate : " + LocalDate.now());
		System.out.println("LocalTime : " + LocalTime.now());
	}

}