package net.hairband.commons;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ToolUtil {
	private static String[] dateFormat = { "yyyy-MM-dd HH:mm:ss",
			"yyyy/MM/dd HH:mm:ss", "yyyy年MM月dd日HH时mm分ss秒", "yyyy-MM-dd",
			"yyyy/MM/dd", "yy-MM-dd", "yy/MM/dd", "yyyy年MM月dd日", "HH:mm:ss",
			"yyyyMMddHHmmss", "yyyyMMdd", "yyyy.MM.dd", "yy.MM.dd",
			"MMyyHHmdd", };

	public static String dateToString(Date date, int index) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(dateFormat[index]).format(date);
	}

	public static long timeDifference(Date d1, Date d2, int index) {
		try {
			long diff = d1.getTime() - d2.getTime();
			long time = diff / 1000;
			return time;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
