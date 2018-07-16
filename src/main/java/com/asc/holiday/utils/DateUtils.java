package com.asc.holiday.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类
 * 
 * @version 版本信息 创建时间 2010-6-25 下午16:00:50
 */
public class DateUtils {

	private final static String PATTERN = "yyyy-MM-dd HH:mm:ss";
	private final static String FOR_PATTERN = "yyyy-M-d";

	public static String dateToString() {
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);
		return sdf.format(new Date());
	}

	/**
	 * 根据销售流向的时间来获得月初
	 * 
	 * @param date
	 *            (转出表)
	 * @return getFirstDateOfCurMonth
	 * @throws ParseException
	 */
	public static String getFirstDateByCurDate(String date)
			throws ParseException {
		Calendar first = stringToCalendar(date);
		int minDay = first.getMinimum(Calendar.DAY_OF_MONTH);
		minDay = first.getMinimum(Calendar.DAY_OF_MONTH);
		first.set(Calendar.DAY_OF_MONTH, minDay);
		SimpleDateFormat sdf = new SimpleDateFormat(FOR_PATTERN);
		return sdf.format(first.getTime());
	}

	public static Calendar stringToCalendar(String date) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		Date firstDate = null;
		try {
			firstDate = DateUtils.stringToDate(date, FOR_PATTERN);
			calendar.setTime(firstDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return calendar;
	}

	public static String dateToString(Date object) {
		if (null == object) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);
		return sdf.format(object);

	}

	public static Date stringToDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);
		return sdf.parse(date);
	}

	public static Date stringToDate(String date, String pattern)
			throws ParseException {
		if (date == null || "".equals(date.trim())
				|| "null".equals(date.trim())) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(date);
	}

	public static String getStrNowDateHmsMs() {
		Date tdate = new Date();
		String nowtime = new Timestamp(tdate.getTime()).toString();
		int len = nowtime.length();
		int between = 23 - len;
		for (int i = 0; i < between; i++) {
			nowtime = nowtime + "0";
		}
		String nowYear = nowtime.substring(0, 4);
		String nowMonth = nowtime.substring(5, 7);
		String nowDay = nowtime.substring(8, 10);
		String nowHour = nowtime.substring(11, 13);
		String nowMinute = nowtime.substring(14, 16);
		String nowSecond = nowtime.substring(17, 19);
		String nowMilliSecond = nowtime.substring(20, 23);
		String nowdate = nowYear + nowMonth + nowDay + nowHour + nowMinute
				+ nowSecond + nowMilliSecond;
		return nowdate;
	}

	/**
	 * 20030801
	 */
	public static String getStrNowDate() {
		java.util.Date tdate = new java.util.Date();
		String nowtime = new Timestamp(tdate.getTime()).toString();
		nowtime = nowtime.substring(0, 10);
		String nowYear = nowtime.substring(0, 4);
		String nowMonth = nowtime.substring(5, 7);
		String nowDay = nowtime.substring(8, 10);
		String nowdate = nowYear + nowMonth + nowDay;
		return nowdate;

	}

	/**
	 * 20030801
	 */
	public static String getStrMonthFirstDay() {
		java.util.Date tdate = new java.util.Date();
		String nowtime = new Timestamp(tdate.getTime()).toString();
		nowtime = nowtime.substring(0, 10);
		String nowYear = nowtime.substring(0, 4);
		String nowMonth = nowtime.substring(5, 7);
		String nowDay = "01";
		String nowdate = nowYear + nowMonth + nowDay;
		return nowdate;

	}

	/**
	 * 20030801
	 */
	public static String getMonthFirstDay() {
		java.util.Date tdate = new java.util.Date();
		String nowtime = new Timestamp(tdate.getTime()).toString();
		return nowtime.substring(0, 8) + "01";

	}

	/**
	 * 取得上个月字符串 20007
	 */
	public static String getStrPreviousMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		String mon = String.valueOf(month);
		if (mon.length() < 2) {
			return year + "0" + mon;
		} else {
			return year + mon;
		}
	}

	/**
	 * 取得上个月 2008-07
	 */
	public static String getPreviousMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		String mon = String.valueOf(month);
		if (mon.length() < 2) {
			return year + "-0" + mon;
		} else {
			return year + "-" + mon;
		}
	}

	/**
	 * 取得上个月最后一天 20050430
	 */
	public static String getStrPreviousMonthDate() {
		Calendar cal = Calendar.getInstance();// 当前日期
		cal.set(Calendar.DATE, 1);// 设为当前月的1号
		cal.add(Calendar.DATE, -1);// 减一天，变为上月最后一天
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		return simpleDateFormat.format(cal.getTime());// 输出
	}

	/**
	 * 取得上个月最后一天 2005-04-30
	 */
	public static String getPreviousMonthDate() {
		Calendar cal = Calendar.getInstance();// 当前日期
		cal.set(Calendar.DATE, 1);// 设为当前月的1号
		cal.add(Calendar.DATE, -1);// 减一天，变为上月最后一天
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(cal.getTime());// 输出20050430
	}

	/**
	 * 取得明天 2005-05-01
	 */
	public static String getTomorrowDate() {
		Calendar cal = Calendar.getInstance();// 当前日期
		cal.add(Calendar.DATE, +1);// 加一天
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(cal.getTime());// 输出2005-05-01
	}

	/**
	 * 20030801151515
	 */
	public static String getStrNowDateHms() {
		java.util.Date tdate = new java.util.Date();
		String nowtime = new Timestamp(tdate.getTime()).toString();
		nowtime = nowtime.substring(0, 19);
		String nowYear = nowtime.substring(0, 4);
		String nowMonth = nowtime.substring(5, 7);
		String nowDay = nowtime.substring(8, 10);
		String nowHour = nowtime.substring(11, 13);
		String nowMinute = nowtime.substring(14, 16);
		String nowSecond = nowtime.substring(17, 19);
		String nowdate = nowYear + nowMonth + nowDay + nowHour + nowMinute
				+ nowSecond;
		return nowdate;
	}

	/**
	 * 2009-03-31
	 */
	public static String getNowDate() {
		java.util.Date tdate = new java.util.Date();
		String nowtime = new Timestamp(tdate.getTime()).toString();
		nowtime = nowtime.substring(0, 10);
		return nowtime;
	}

	/**
	 * 2009-03-31 15:48:28
	 */
	public static String getNowDateHms() {
		java.util.Date tdate = new java.util.Date();
		String nowtime = new Timestamp(tdate.getTime()).toString();
		nowtime = nowtime.substring(0, 19);
		return nowtime;
	}

	public static String dateToString(Date date, String pattern) {
		try {
			DateFormat format = new SimpleDateFormat(pattern);
			String str = format.format(date);
			return str;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据传入的日期得到上个月yyyy-mm的形式，例如传入2009-11-11，得到2009-10
	 * 
	 * @param strDate
	 *            传入的日期
	 * @return 转化后的日期
	 */
	public static String getPreviousMonth(String strDate) {
		java.sql.Date date = java.sql.Date.valueOf(strDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		DateFormat format = new SimpleDateFormat("yyyy-MM");
		return format.format(calendar.getTime());
	}

	public static String getPreviousMonthEndDay(String strDate) {
		java.sql.Date date = java.sql.Date.valueOf(strDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(calendar.getTime());
	}

	/**
	 * 检查日期合法性
	 * 
	 * @param s
	 *            String
	 * @param dataFormat
	 *            String 日期样式，比如"yyyy-mm-dd","yyyymmddHHmmSS"
	 * @return boolean
	 */
	public static boolean checkDate(String s, String dataFormat) {
		boolean ret = true;
		try {
			DateFormat df = new SimpleDateFormat(dataFormat);
			ret = df.format(df.parse(s)).equals(s);
		} catch (ParseException e) {
			ret = false;
		}
		return ret;
	}

	public static Date string2Date(String s) {
		return string2Date(s, true);
	}

	public static Date string2Date(String s, boolean flag) {
		List<DateFormat> formats = new ArrayList<DateFormat>();

		String ttt = s.replaceAll("[\\.\\/年月]", "-");
		ttt = ttt.replaceAll("星期[一二三四五六日天]", " ");
		
		
		if (ttt.indexOf(" AM ")>0) {
			ttt = ttt.substring(0, ttt.indexOf(" AM "));
		} else if(ttt.indexOf(" 上午 ")>0){
			ttt = ttt.substring(0, ttt.indexOf(" 上午 "));
		} else if(ttt.indexOf(" PM ")>0){
			ttt = ttt.substring(0, ttt.indexOf(" PM "));
		} else if(ttt.indexOf(" 下午 ")>0){
			ttt = ttt.substring(0, ttt.indexOf(" 下午 "));
		}
		
		ttt = ttt.replaceAll("[日T]", " ");
		ttt = ttt.replaceAll("   ", " ");
		ttt = ttt.replaceAll("  ", " ");
		
		if (ttt.length() > 13) {
			if (ttt.lastIndexOf("-") > 13) {
				ttt = ttt.substring(0,ttt.lastIndexOf("-"));
			}
		}
		ttt = ttt.trim();
		
		if (ttt.length() >= 8 && ttt.length() <= 10) {
			formats.add(new SimpleDateFormat("yyyy-M-d"));
			formats.add(new SimpleDateFormat("yyyy-M-dd"));
			formats.add(new SimpleDateFormat("yyyy-MM-dd"));
			formats.add(new SimpleDateFormat("yyyy-MM-d"));
		}
		if (ttt.length() >= 12 && ttt.length() <= 16) {
			formats.add(new SimpleDateFormat("yyyy-M-d H:m"));
			formats.add(new SimpleDateFormat("yyyy-M-d H:mm"));
			formats.add(new SimpleDateFormat("yyyy-M-d HH:m"));
			formats.add(new SimpleDateFormat("yyyy-M-dd HH:mm"));
			formats.add(new SimpleDateFormat("yyyy-MM-dd HH:mm"));
			formats.add(new SimpleDateFormat("yyyy-MM-dd H:mm"));
			formats.add(new SimpleDateFormat("yyyy-MM-d HH:mm"));
			formats.add(new SimpleDateFormat("yyyy-MM-d H:mm"));
		}

		if (ttt.length() >= 15 && ttt.length() <= 20) {
			formats.add(new SimpleDateFormat("yyyy-M-d H:m:s"));
			formats.add(new SimpleDateFormat("yyyy-M-d HH:mm:ss"));
			formats.add(new SimpleDateFormat("yyyy-M-d H:mm:ss"));
			formats.add(new SimpleDateFormat("yyyy-M-dd H:mm:ss"));
			formats.add(new SimpleDateFormat("yyyy-MM-dd H:mm:ss"));
			formats.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			formats.add(new SimpleDateFormat("yyyy-MM-dd HH-mm"));
		}

		if (ttt.length() == 8) {
			formats.add(new SimpleDateFormat("yyyyMMdd"));
		} else if (ttt.length() == 10) {
			formats.add(new SimpleDateFormat("yyyyMMddHH"));
		} else if (ttt.length() == 12) {
			formats.add(new SimpleDateFormat("yyyyMMddHHmm"));
		} else if (ttt.length() == 14) {
			formats.add(new SimpleDateFormat("yyyyMMddHHmmss"));
		} else if (ttt.length() == 17) {
			formats.add(new SimpleDateFormat("yyyyMMdd HH:mm:ss"));
		}

		formats.add(new SimpleDateFormat("EEE MMM d hh:mm:ss a z yyyy"));
		formats.add(new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy"));

		for (DateFormat format : formats) {
			try {
				Date d = format.parse(ttt);
				if (flag) {
					String tt = format.format(d);
					if (tt.equalsIgnoreCase(ttt)) {
						return d;
					}
					continue;
				}
				return d;
			} catch (ParseException e) {
			}
		}
		return null;
	}

	/**
	 * 获得当年1月1日的日期
	 */
	public static String getFirstDayOfYear(String str) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		String year = null;
		try {
			date = sd.parse(str);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			year = sdf.format(calendar.getTime());
			return year + "-01-01 00:00:00";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return year;
	}

	/**
	 * 判断时间是否在凌晨19:00:00 ~~23:59:59
	 */
	public static boolean is7To12() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String c = sdf.format(cal.getTime());
		Integer time = Integer.parseInt(c.replace(":", ""));
		if (time >= 190000 && time <= 235959) {
			return true;
		}
		return false;
	}

	/**
	 * 判断时间是否在凌晨00:00:00 ~~05:00:00
	 */
	public static boolean is0To5() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String c = sdf.format(cal.getTime());
		Integer time = Integer.parseInt(c.replace(":", ""));
		if (time >= 1 && time <= 50000) {
			return true;
		}
		return false;
	}

	/**
	 * 根据传入日期param参数获取前一天日期
	 * 
	 * @param param
	 *            : yyyy-MM-dd HH:mm:ss
	 */
	public static String getYesterDay(String param) {
		SimpleDateFormat frt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = frt.parse(param);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, -1);
			return frt.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据传入日期param参数获取35天前的日期
	 * 
	 * @param param
	 *            : yyyy-MM-dd HH:mm:ss
	 **/
	public static String getBefore35(String param) {
		SimpleDateFormat frt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = frt.parse(param);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, -34);
			return frt.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断当前日期是否是一周的第一天
	 */
	public static boolean isFirstDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 这里设置从周一开始,若需要根据系统时区自动获取，则采用下边的方式
		Calendar cal2 = Calendar.getInstance();
		return cal.equals(cal2);
	}

	/**
	 * 判断当前日期是否是一月的第一天
	 */
	public static boolean isFirstDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);// 这里设置从周一开始,若需要根据系统时区自动获取，则采用下边的方式
		Calendar cal2 = Calendar.getInstance();
		return cal.equals(cal2);
	}

	/**
	 * 当前日期的获得本月1号日期 calendar 当前日期
	 */
	public static String getFirstDateOfCurMonth(Calendar calendar) {
		Calendar first = Calendar.getInstance();
		first.setTime(calendar.getTime());

		int minDay = first.getMinimum(Calendar.DAY_OF_MONTH);

		minDay = first.getMinimum(Calendar.DAY_OF_MONTH);
		first.set(Calendar.DAY_OF_MONTH, minDay);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(first.getTime());
	}

	/**
	 * 获得上个月1号
	 */
	public static Date getFirstDateOfLastMonth(Calendar calendar) {
		Calendar first = Calendar.getInstance();
		first.setTime(calendar.getTime());

		int minDay = first.getMinimum(Calendar.DAY_OF_MONTH);
		first.add(Calendar.MONTH, -1);

		minDay = first.getMinimum(Calendar.DAY_OF_MONTH);
		first.set(Calendar.DAY_OF_MONTH, minDay);
		return first.getTime();
	}

	/**
	 * 获取上个月最后一天的日期
	 */
	public static Date getLastDateOfLastMonth(Calendar calendar) {
		Calendar last = Calendar.getInstance();
		last.setTime(calendar.getTime());

		int maxDay = last.getMaximum(Calendar.DAY_OF_MONTH);
		last.add(Calendar.MONTH, -1);

		maxDay = last.getActualMaximum(Calendar.DAY_OF_MONTH);
		last.set(Calendar.DAY_OF_MONTH, maxDay);
		return last.getTime();
	}

	/**
	 * 获取日期所在月的最后一天的日期
	 * 
	 * @param calendar
	 * @return
	 */
	public static Date getLastDateOfMonth(Date date) {
		Calendar last = Calendar.getInstance();
		last.setTime(date);

		int maxDay = last.getActualMaximum(Calendar.DAY_OF_MONTH);
		last.set(Calendar.DAY_OF_MONTH, maxDay);
		return last.getTime();

	}

	public static String getDateYMD(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		String ymd = c.get(Calendar.YEAR) + "" + c.get(Calendar.MONTH) + ""
				+ c.get(Calendar.DATE);
		return ymd;
	}

	/**
	 * 获得上周第一天的日期
	 */
	public static Date getFirstDateOfLastWeek(Calendar calendar) {
		Calendar first = Calendar.getInstance();
		first.setTime(calendar.getTime());
		first.setFirstDayOfWeek(Calendar.MONDAY);
		int index = first.getFirstDayOfWeek();
		first.set(Calendar.DAY_OF_WEEK, index);
		first.add(Calendar.DAY_OF_WEEK, -7);
		return first.getTime();
	}

	/**
	 * 获得上周最后一天的日期
	 */
	public static Date getLastDateOfLastWeek(Calendar calendar) {
		Calendar last = Calendar.getInstance();
		last.setTime(calendar.getTime());
		last.setFirstDayOfWeek(Calendar.MONDAY);
		int index = last.getFirstDayOfWeek();
		last.set(Calendar.DAY_OF_WEEK, index);
		last.add(Calendar.DAY_OF_WEEK, -1);
		return last.getTime();
	}

	/**
	 * 获得日期date所在的当前周数
	 */
	public static String getConvertoWeekDate(String date, String inputFormat)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date myd = format.parse(date);
		SimpleDateFormat sdf = new SimpleDateFormat(inputFormat);
		Calendar ccc = Calendar.getInstance();
		ccc.setTime(myd);
		ccc.setFirstDayOfWeek(Calendar.MONDAY);

		/**/
		String str = date.substring(0, 4);
		SimpleDateFormat tempFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date compara = tempFormat.parse(str + "-01-01");
		Calendar calP = Calendar.getInstance();
		calP.setTime(compara);
		int x = calP.get(Calendar.DAY_OF_WEEK);
		/**/
		if (x != 2) {
			sdf.setCalendar(ccc);
			String s = sdf.format(ccc.getTime());
			String s1 = s.substring(0, 4);
			String s2 = s.substring(4, 6);
			Integer i = Integer.parseInt(s2);
			if (i > 1) {
				i = i - 1;
			}
			s2 = i + "";
			if ((i + "").length() < 2) {
				s2 = "0" + i;
			}
			return s1 + s2;
		} else {
			sdf.setCalendar(ccc);
			return sdf.format(ccc.getTime());
		}
	}

	/**
	 * 获得前一天开始的日期
	 */
	public static String getYesterdayStart(Calendar calendar) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(calendar.getTime());
		cal.add(Calendar.DATE, -1);
		return sdf.format(cal.getTime()) + " 00:00:00";
	}

	/**
	 * 获得前一天结束的日期
	 */
	public static String getYesterdayEnd(Calendar calendar) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(calendar.getTime());
		cal.add(Calendar.DATE, -1);
		return sdf.format(cal.getTime()) + " 23:59:59";
	}

	/**
	 * 获得前一天的日期
	 */
	public static String getYesterday(Calendar calendar) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(calendar.getTime());
		cal.add(Calendar.DATE, -1);
		return sdf.format(cal.getTime());
	}

	public static String getOracleDate() {
		String str = "to_date('" + DateUtils.dateToString()
				+ "','YYYY-MM-DD HH24:MI:SS')";
		return str;
	}

	/**
	 * 史克业务日期判定月份 月份范围-上月的26号到本月的25号 例：201208 2012-07-26~2012-08-25
	 * 
	 * @param operDate
	 *            yyyy-MM-dd
	 * @param monthFlag
	 *            yyyyMMdd
	 * @return
	 * @throws ParseException
	 */
	public static boolean tskfContainDate(String operDate, String monthFlag) {
		try {
			String endDateStr = monthFlag + "25";
			Date tarDate = stringToDate(operDate, "yyyy-MM-dd");
			Date endDate = stringToDate(endDateStr, "yyyyMMdd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(endDate);
			calendar.add(Calendar.MONTH, -1);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			Date firstDate = calendar.getTime();
			if (tarDate.compareTo(firstDate) < 0
					|| tarDate.compareTo(endDate) > 0) {
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * 史克业务月数据处理周期判定 月份范围-本月的26号到下月的25号 例：201208 2012-08-26~2012-09-25
	 * 
	 * @param monthFlag
	 *            yyyy-MM-dd
	 * @param monthFlag
	 *            yyyyMMdd
	 * @return
	 * @throws ParseException
	 */
	public static boolean tskfMonthDate(String monthFlag) {
		try {
			String firstDateStr = monthFlag + "26";
			Date firstDate = stringToDate(firstDateStr, "yyyyMMdd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(firstDate);
			calendar.add(Calendar.MONTH, 1);
			Date endDate = calendar.getTime();
			Date tarDate = new Date();
			if (tarDate.compareTo(firstDate) < 0
					|| tarDate.compareTo(endDate) >= 0) {
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return true;
	}

	public static void main(String[] args) throws ParseException {
		/*
		 * try{ if(tskfContainDate("2012-06-07","201206")){
		 * 
		 * System.out.println("是一致的！！！！！！"); }; }catch (Exception e) { // TODO:
		 * handle exception }
		 */
		// String d="2012-05-20";
		// // Calendar calendar=Calendar.getInstance();
		// // Date firstDate=stringToDate(d);
		// // calendar.setTime(firstDate);
		// System.out.println("=======>"+getFirstDateByCurDate(d));
		String d = "2016-09-08 01:08:01";
		System.out.println(dateToString(string2Date(d)));

	}
}
