package qing.yun.hui.common.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/***
 ** @Description: 日期时间工具类
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 10:00:38 AM
 ** @version: V1.0
 ***/
public class DateUtil {

		public static final String YYYY_MM_DD_HH_MM_SS_MS = "yyyy-MM-dd HH:mm:ss:ms";
		public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss:SSS";
		public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
		public static final String YYYY_MM_DD = "yyyy-MM-dd";
		public static final String YYYYMMDDHHMMSSMS = "yyyyMMddhhmmssms";
		public static final String YYYYMMDDHHMMSS = "yyyyMMddhhmmss";
		public static final String YYYYMMDD = "yyyyMMdd";
		public static final String YYYY = "yyyy";
		public static final String HH_MM_SS = "hh:mm:ss";
		public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
		public static final String YYYY__MM_DD2="yyyy.MM.dd";
	
		/**
		 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
		 * 
		 * @param dateDate
		 * @return
		 */
		public static String dateToString(Date dateDate, String formatString) {
			String dateString = "";
			if (dateDate != null && formatString != "" && formatString != null) {
				SimpleDateFormat formatter = new SimpleDateFormat(formatString);
				dateString = formatter.format(dateDate);
			}
			return dateString;
		}
		
		/**
		 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
		 * 
		 * @param dateDate
		 * @return
		 */
		public static String dateToString(String dateStr, String formatString) {
			String dateString = "";
			if (dateStr != null && formatString != "" && formatString != null) {
				SimpleDateFormat formatter = new SimpleDateFormat(formatString);
				dateString = formatter.format(dateStr);
			}
			return dateString;
		}
	
		/**
		 * 获取当前时间
		 * 
		 * @param formatString
		 *            yyyyMMddHHmmss, yyyy-MM-dd HH:mm:ss(标准格式), yyyyMMddHHmmssSSS
		 * @return Date
		 */
		public static String getCurrDateStr(String formatString) {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat(formatString);
			String dateString = formatter.format(date);
			return dateString;
		}
		
	
		/**
		 * 获取当前时间字符串
		 * 
		 * @param formatString
		 *            格式化
		 * @return Date
		 */
		public static Date getCurrDateTime(String formatString) {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat(formatString);
			String dateString = formatter.format(date);
			ParsePosition pos = new ParsePosition(8);
			Date currentDateTime = formatter.parse(dateString, pos);
			return currentDateTime;
		}
	
		/**
		 * 格式时间,假如date为空则为当前格式时间
		 * 
		 * @param dateStr
		 *            时间字符串
		 * @param formatString
		 *            (必须与所传时间字符串格式相同)例yyyyMMddHHmmss
		 * @param simpleDateFormatStri需要返回的格试
		 *            例yyyyMMddHHmmss
		 * @return String
		 */
		public static String getDate(String dateStr, String formatString,
				String simpleDateFormatString) {
	
			SimpleDateFormat formatter = new SimpleDateFormat(formatString);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					simpleDateFormatString);
			ParsePosition pos = new ParsePosition(0);
			Date nowDate = formatter.parse(dateStr, pos);
			nowDate = new Date(nowDate.getTime());
			String strDate = simpleDateFormat.format(nowDate);
			return strDate;
		}
	
		/**
		 * 当前的下年,月,日afterOrAgo或上年,月,日beforeOrAfter的时间
		 * 
		 * @param type
		 *            1,年 2,月 3,日
		 * @param beforeOrAfter
		 *            下beforeOrAfter年,月,日份数
		 * @param formatString
		 *            格式化参数 yyyyMMddHHmmss
		 * @return String
		 */
		public static String getDateBeforeOrAfter(int type, int beforeOrAfter,
				String formatString) {
			GregorianCalendar now = new GregorianCalendar();
			SimpleDateFormat outFormat = new SimpleDateFormat(formatString);
			switch (type) {
			case 1:
				now.add(GregorianCalendar.YEAR, beforeOrAfter);
				break;
			case 2:
				now.add(GregorianCalendar.MONTH, beforeOrAfter);
				break;
			case 3:
				now.add(GregorianCalendar.DATE, beforeOrAfter);
				break;
			case 4:
				now.add(GregorianCalendar.HOUR, beforeOrAfter);
				break;
			case 5:
				now.add(GregorianCalendar.SECOND, beforeOrAfter);
				break;
			case 6:
				now.add(GregorianCalendar.MINUTE, beforeOrAfter);
				break;
			}
			return outFormat.format(now.getTime());
		}
	
		/**
		 * 所需时间的下年,月,日afterOrAgo或上年,月,日beforeOrAfter的时间
		 * 
		 * @param dateStr
		 *            时间字符串
		 * @param type
		 *            1,年 2,月 3,日
		 * @param beforeOrAfter
		 *            下beforeOrAfter年,月,日份数
		 * @param formatString
		 *            格式化参数 yyyyMMddHHmmss
		 * @return String
		 * @throws Exception
		 */
		public static String getDateBeforeOrAfter(String dateStr, int type,
				int beforeOrAfter, String formatString) throws Exception {
			SimpleDateFormat outFormat = new SimpleDateFormat(formatString);
			GregorianCalendar calendar = new GregorianCalendar();
			try {
				Date date = outFormat.parse(dateStr);
				calendar.setTime(date);
				switch (type) {
				case 1:
					calendar.add(GregorianCalendar.YEAR, beforeOrAfter);
					break;
				case 2:
					calendar.add(GregorianCalendar.MONTH, beforeOrAfter);
					break;
				case 3:
					calendar.add(GregorianCalendar.DATE, beforeOrAfter);
					break;
				}
			} catch (ParseException ex) {
				throw new Exception("日期格式化异常！", ex);
			}
			return outFormat.format(calendar.getTime());
		}
	
		/**
		 * 获取当前时间前后beforeOrAfterDay天时间
		 * 
		 * @param beforeOrAfterDay
		 * @return Calendar
		 */
		public static Calendar getCalendar(int beforeOrAfterDay) {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.add(GregorianCalendar.DATE, beforeOrAfterDay);
			return calendar;
		}
	
		/**
		 * 获取Calendar前后beforeOrAfterDay天时间
		 * 
		 * @param calendar
		 * @param beforeOrAfterDay
		 *            前后天数用正负表示
		 * @return Calendar
		 */
		public static Calendar getCalendar(Calendar calendar, int beforeOrAfterDay) {
			calendar.add(GregorianCalendar.DATE, beforeOrAfterDay);
			return calendar;
		}
	
		/**
		 * 获取Calendar前后beforeOrAfterDay天时间
		 * 
		 * @param date
		 * @param beforeOrAfterDay
		 *            前后天数用正负表示
		 * @return Calendar
		 */
		public static Calendar getCalendar(Date date, int beforeOrAfterDay) {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(GregorianCalendar.DATE, beforeOrAfterDay);
			return calendar;
		}
	
		/**
		 * 获取当前时间前后beforeOrAfterDay天时间
		 * 
		 * @param beforeOrAfterDay
		 * @return Date
		 */
		public static Date getDate(int beforeOrAfterDay) {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.add(GregorianCalendar.DATE, beforeOrAfterDay);
			return calendar.getTime();
		}
	
		/**
		 * 获取Date前后beforeOrAfterDay天时间
		 * 
		 * @param calendar
		 * @param beforeOrAfterDay
		 * @return Date
		 */
		public static Date getDate(Calendar calendar, int beforeOrAfterDay) {
			calendar.add(GregorianCalendar.DATE, beforeOrAfterDay);
			return calendar.getTime();
		}
	
		/**
		 * 获取Date前后afterOrAgo天时间
		 * 
		 * @param date
		 * @param beforeOrAfterDay
		 * @return Date
		 */
		public static Date getDate(Date date, int beforeOrAfterDay) {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(GregorianCalendar.DATE, beforeOrAfterDay);
			return calendar.getTime();
		}
	
		/**
		 * 两个时间之间的天数
		 * 
		 * @param date1
		 * @param date2
		 * @return
		 */
		public static long getDays(String date1, String date2) {
			if (date1 == null || date1.equals(""))
				return 0;
			if (date2 == null || date2.equals(""))
				return 0;
			// 转换为标准时间
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			Date mydate = null;
			try {
				date = myFormatter.parse(date1);
				mydate = myFormatter.parse(date2);
			} catch (Exception e) {
			}
			long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
			return day;
		}
	
		/**
		 * 返回double值 保留两位小数
		 * */
		public static Double getDouble2(Double d) {
			return new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP)
					.doubleValue();
		}
	
		/**
		 * 获取一个月的最后一天
		 * 
		 * @param dat
		 * @return
		 */
		public static String getEndDateOfMonth(String dat) {
			String str = dat.substring(0, 8);
			String month = dat.substring(5, 7);
			int mon = Integer.parseInt(month);
			if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8
					|| mon == 10 || mon == 12) {
				str += "31";
			} else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
				str += "30";
			} else {
				if (isLeapYear(dat)) {
					str += "29";
				} else {
					str += "28";
				}
			}
			return str;
		}
	
		/**
		 * 得到现在小时
		 */
		public static String getHour() {
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(currentTime);
			String hour;
			hour = dateString.substring(11, 13);
			return hour;
		}
	
		/**
		 * 提取一个月中的最后一天
		 * 
		 * @param day
		 * @return
		 */
		public static Date getLastDate(long day) {
			Date date = new Date();
			long date_3_hm = date.getTime() - 3600000 * 34 * day;
			Date date_3_hm_date = new Date(date_3_hm);
			return date_3_hm_date;
		}
	
		/**
		 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
		 * 
		 * @param nowdate
		 * @param delay
		 * @return
		 */
		public static String getNextDay(String nowdate, String delay) {
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String mdate = "";
				Date d = stringToDate(nowdate, "yyyy-MM-dd");
				long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24
						* 60 * 60;
				d.setTime(myTime * 1000);
				mdate = format.format(d);
				return mdate;
			} catch (Exception e) {
				return "";
			}
		}
	
		/**
		 * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
		 * 此函数返回该日历第一行星期日所在的日期
		 * 
		 * @param sdate
		 * @return
		 */
		public static String getNowMonth(String datestr) {
			// 取该时间所在月的一号
			datestr = datestr.substring(0, 8) + "01";
			// 得到这个月的1号是星期几
			Date date = stringToDate(datestr, "yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int u = c.get(Calendar.DAY_OF_WEEK);
			String newday = getNextDay(datestr, (1 - u) + "");
			return newday;
		}
	
		/**
		 * 时间前推或后推分钟,其中mm表示分钟.
		 */
		public static String getPreTime(String sj1, int mm) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String datestr = "";
			try {
				Date date1 = format.parse(sj1);
				long Time = (date1.getTime() / 1000) + mm * 60;
				date1.setTime(Time * 1000);
				datestr = format.format(date1);
			} catch (Exception e) {
			}
			return datestr;
		}
	
		/**
		 * 产生周序列,即得到当前时间所在的年度是第几周
		 * 
		 * @return
		 */
		public static String getSeqWeek() {
			Calendar c = Calendar.getInstance(Locale.CHINA);
			String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
			if (week.length() == 1)
				week = "0" + week;
			String year = Integer.toString(c.get(Calendar.YEAR));
			return year + week;
		}
	
		/**
		 * 获取当前时间
		 * 
		 * @param formatString
		 *            yyyyMMddHHmmss, yyyy-MM-dd HH:mm:ss(标准格式), yyyyMMddHHmmssSSS
		 * @return String
		 */
		public static String getStringDate(String formatString) {
			Date now = new Date();
			SimpleDateFormat outFormat = new SimpleDateFormat(formatString);
			String str = outFormat.format(now);
			return str;
		}
	
		/**
		 * 得到现在分钟
		 * 
		 * @return
		 */
		public static String getTime() {
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(currentTime);
			String min;
			min = dateString.substring(14, 16);
			return min;
		}
		/**
		 * 获得二个时间之差-以毫秒为单位
		 * @param curDate 
		 * @param targetDate
		 * @return long 计算后的时间毫秒数
		 * **/
		public static long getTimes(Date curDate,Date targetDate){
			if(null==curDate||null==targetDate){
				return 0;
			}
			long times=curDate.getTime()-targetDate.getTime();
			return times;
		}
	
		/**
		 * 获取时间 小时:分;秒 HH:mm:ss
		 * 
		 * @return
		 */
		public static String getTimeShort(String formatString) {
			SimpleDateFormat formatter = new SimpleDateFormat(formatString);
			Date currentTime = new Date();
			String dateString = formatter.format(currentTime);
			return dateString;
		}
	
		/**
		 * 得到二个日期间的间隔天数
		 * 
		 * @param sj1
		 * @param sj2
		 * @return
		 */
		public static String getTwoDay(String str1, String str2) {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
			long day = 0;
			try {
				java.util.Date date = myFormatter.parse(str1);
				java.util.Date mydate = myFormatter.parse(str2);
				day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
			} catch (Exception e) {
				return "";
			}
			return day + "";
		}
	
		/**
		 * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
		 * 
		 * @param st1
		 * @param st2
		 * @return
		 */
		public static String getTwoHour(String st1, String st2) {
			String[] kk = null;
			String[] jj = null;
			kk = st1.split(":");
			jj = st2.split(":");
			if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
				return "0";
			else {
				double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1])
						/ 60;
				double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1])
						/ 60;
				if ((y - u) > 0)
					return y - u + "";
				else
					return "0";
			}
		}
	
		/**
		 * 根据一个日期，返回是星期几的字符串
		 * 
		 * @param sdate
		 * @return
		 */
		public static String getWeek(String datestr) {
			// 再转换为时间
			Date date = stringToDate(datestr, "yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			// int hour=c.get(Calendar.DAY_OF_WEEK);
			// hour中存的就是星期几了，其范围 1~7
			// 1=星期日 7=星期六，其他类推
			return new SimpleDateFormat("EEEE").format(c.getTime());
		}
	
		/**
		 * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
		 * 
		 * @param sdate
		 * @param num
		 * @return
		 */
		public static String getWeek(String date, String num) {
			// 再转换为时间
			Date dd = stringToDate(date, "yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(dd);
			if (num.equals("1")) // 返回星期一所在的日期
				c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			else if (num.equals("2")) // 返回星期二所在的日期
				c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
			else if (num.equals("3")) // 返回星期三所在的日期
				c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
			else if (num.equals("4")) // 返回星期四所在的日期
				c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
			else if (num.equals("5")) // 返回星期五所在的日期
				c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
			else if (num.equals("6")) // 返回星期六所在的日期
				c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
			else if (num.equals("0")) // 返回星期日所在的日期
				c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		}
	
		public static String getWeekStr(String date) {
			String str = "";
			str = getWeek(date);
			if ("1".equals(str)) {
				str = "星期日";
			} else if ("2".equals(str)) {
				str = "星期一";
			} else if ("3".equals(str)) {
				str = "星期二";
			} else if ("4".equals(str)) {
				str = "星期三";
			} else if ("5".equals(str)) {
				str = "星期四";
			} else if ("6".equals(str)) {
				str = "星期五";
			} else if ("7".equals(str)) {
				str = "星期六";
			}
			return str;
		}
	
		/**
		 * 判断是否润年
		 * 
		 * @param date
		 * @return
		 */
		public static boolean isLeapYear(String date) {
			/**
			 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
			 * 3.能被4整除同时能被100整除则不是闰年
			 */
			Date d = stringToDate(date, "yyyy-MM-dd");
			GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
			gc.setTime(d);
			int year = gc.get(Calendar.YEAR);
			if ((year % 400) == 0)
				return true;
			else if ((year % 4) == 0) {
				if ((year % 100) == 0) {
					return false;
				} else {
					return true;
				}
			} else {
				return false;
			}
		}
	
		/**
		 * 判断二个时间是否在同一个周
		 * 
		 * @param date1
		 * @param date2
		 * @return
		 */
		public static boolean isSameWeekDates(Date date1, Date date2) {
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal1.setTime(date1);
			cal2.setTime(date2);
			int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
			if (0 == subYear) {
				if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
						.get(Calendar.WEEK_OF_YEAR))
					return true;
			} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
				// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
				if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
						.get(Calendar.WEEK_OF_YEAR))
					return true;
			} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
				if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
						.get(Calendar.WEEK_OF_YEAR))
					return true;
			}
			return false;
		}
	
		/**
		 * 毫秒转时间
		 * 
		 * @param millisecond
		 * @return
		 */
		public static String MillisecondToSimpleDate(int millisecond) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return sdf.format(millisecond);
		}
	
		/**
		 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
		 * 
		 * @param strDate
		 * @return
		 */
		public static Date stringToDate(String strDate, String formatString) {
			SimpleDateFormat formatter = new SimpleDateFormat(formatString);
			ParsePosition pos = new ParsePosition(0);
			Date strtodate = formatter.parse(strDate, pos);
			return strtodate;
		}
	
		/**
		 * 将date 转换成 Calendar格式
		 * ***/
		public static Calendar dateToCalendar(Date date, String formatString) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			return c;
		}
	
		/**
		 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
		 * 
		 * @param strDate
		 * @return
		 * @throws ParseException
		 */
		public static Calendar stringToCalendar(String strDate, String formatString)
				throws ParseException {
			if (strDate == null) {
				return null;
			}
			SimpleDateFormat formatter = new SimpleDateFormat(formatString);
			formatter.parse(strDate);
			return formatter.getCalendar();
		}
	
		public static Calendar parseTimestamp(String timestamp, String formatString)
				throws ParseException {
			SimpleDateFormat sdf = new SimpleDateFormat(formatString, Locale.CHINA);
			Date date = sdf.parse(timestamp);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		}
	
		public static Calendar parseDatetime(String strDate) throws ParseException {
			if (strDate == null || "".equals(strDate.trim())) {
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(
					YYYY_MM_DD_HH_MM_SS_SSS.substring(0, strDate.length()));
			sdf.parse(strDate);
			return sdf.getCalendar();
		}
	
		public static String getFourSeq(String seq) {
			switch (seq.length()) {
			case 1:
				seq = "000" + seq;
				break;
			case 2:
				seq = "00" + seq;
				break;
			case 3:
				seq = "0" + seq;
				break;
	
			default:
				seq = seq.substring(seq.length() - 4);
				break;
			}
	
			return seq;
		}
	
		/**
		 * 按格式化串比较两个日期,要求格式化串必须按年月日时分秒顺序
		 * 格式化后,日期相同返还0,第一个日期小于第二个日期返回-1,第一个日期大于第二个日期返回1
		 * 
		 * @param cal1
		 * @param cal2
		 * @param formatStr
		 * @return
		 */
		public static int compare(Calendar cal1, Calendar cal2, String formatStr) {
			SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
			String date1 = sdf.format(cal1.getTime());
			String date2 = sdf.format(cal2.getTime());
			return date1.compareTo(date2);
		}
	
		/**
		 * 获取某年第一天日期
		 * 
		 * @param year
		 *            年份
		 * @return Date
		 */
		public static Date getYearFirst(int year) {
			Calendar calendar = Calendar.getInstance();
			calendar.clear();
			calendar.set(Calendar.YEAR, year);
			Date currYearFirst = calendar.getTime();
			return currYearFirst;
		}
	
		/**
		 * 获取某年最后一天日期
		 * 
		 * @param year
		 *            年份
		 * @return Date
		 */
		public static Date getYearLast(int year) {
			Calendar calendar = Calendar.getInstance();
			calendar.clear();
			calendar.set(Calendar.YEAR, year);
			calendar.roll(Calendar.DAY_OF_YEAR, -1);
			Date currYearLast = calendar.getTime();
	
			return currYearLast;
		}
	
		/**
		 * 根据给定时间获取当前小时;
		 * @param date  当前日期
		 * @param is24Hour  是否以24小时显示,true 支持、false 不支持(不支持的情况下、以12小时制显示);
		 * @return int 当前小时
		 * ***/
		public static int getHour(Date date, boolean is24Hour) {
			Calendar calendar = dateToCalendar(date, YYYY_MM_DD_HH_MM_SS);
			int hour = calendar.get(is24Hour ? Calendar.HOUR_OF_DAY : Calendar.HOUR);
			return hour;
		}
		
		/**
		 * <p>根据起始时间，结束时间，查找二个时间段的间隔区间</p>
		 * @param startDate 起始时间
		 * @param endDate   结束时间
		 * @return
		 * */
		public static List<Date> getDaysByStartDateWithEndDate(Date startDate,Date endDate){
			List<Date> list=new ArrayList<Date>();
			Calendar startCalend=Calendar.getInstance();
			startCalend.setTime(startDate);
			Calendar endCalend=Calendar.getInstance();
			endCalend.setTime(endDate);
			//二种方式实现，compareTo 与 before 可以实现相同功能，只是before() 目标只能是Calendar对象。
			for(Calendar c=startCalend;c.compareTo(endCalend)<0;c.add(Calendar.DAY_OF_MONTH, 1)){
				list.add(c.getTime());
			}
			return list;
		}
		
		/**
		 * <p>根据起始时间，结束时间，查找二个时间段的间隔区间</p>
		 * @param startDate 起始时间
		 * @param endDate   结束时间
		 * @return
		 * */
		public static List<String> getDaysByStartDateWithEndDate(String startDate,String endDate){
			List<String> list=new ArrayList<String>();
			Calendar startCalend=Calendar.getInstance();
			startCalend.setTime(stringToDate(startDate,YYYY_MM_DD));
			Calendar endCalend=Calendar.getInstance();
			endCalend.setTime(stringToDate(endDate,YYYY_MM_DD));
			//二种方式实现，compareTo 与 before 可以实现相同功能，只是before() 目标只能是Calendar对象。
			for(Calendar c=startCalend;c.before(endCalend);c.add(Calendar.DAY_OF_MONTH, 1)){
				list.add(dateToString(c.getTime(), YYYY_MM_DD));
			}
			return list;
		}
		
		public static void main(String[] args){
			Date startDate=stringToDate("2017-02-01", YYYY_MM_DD);
			Date endDate=stringToDate("2017-03-01", YYYY_MM_DD);
			getDaysByStartDateWithEndDate(startDate, endDate);
		}
}
