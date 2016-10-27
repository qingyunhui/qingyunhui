package qing.yun.hui.common.utils;

import java.util.Date;

/***
 ** @Description: 处理时间的类型
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Dec 26, 2015 1:57:51 PM
 ** @version: V1.0
 ***/
public class HandleTime {

	private static final long second=1000;
	private static final long minute=60 * second;
	private static final long hour=60 * minute;
	private static final long day=24 * hour;
	private static final long month=30 * day;
	private static final long year= 12 * month;
	
	/**${yzsTld:getNameByDate(targetDate)}前台页面只须如此调用即可***/
	
	
	/**
	 * 一般用于帖子或文章发布(如:发布前N天或小时..)
	 * @param targetDateStr 目标日期(字符串类型)
	 * @return 计算后的结果
	 * **/
	public static String getNameByDate(String targetDateStr) {
		Date targetDate=DateUtil.stringToDate(targetDateStr, DateUtil.YYYY_MM_DD_HH_MM_SS_SSS);
		return getNameByDate(targetDate);
	}
	
	/**
	 * 一般用于帖子或文章发布(如:发布前N天或小时..)
	 * @param times (long类型:时间戳)
	 * @return 计算后的结果
	 * **/
	public static String getNameByDate(Long times){
		StringBuffer sb=new StringBuffer();
		if(times/year>0){
			sb.append(times/year+"年前");
		}else if(times/month>0){
			sb.append(times/month+"月前");
		}else if(times/day>0){
			sb.append(times/day+"天前");
		}else if(times/hour>0){
			sb.append(times/hour+"小时前");
		}else if(times/minute>0){
			sb.append(times/minute+"分钟前");
		}else if(times/second>0){
			sb.append(times/second+"秒前");
		}else{
			sb.append("刚刚");
		}
		return sb.toString();
	}
	
	/**
	 * 一般用于帖子或文章发布(如:发布前N天或小时..)
	 * @param targetDate (时间类型:目标日期)
	 * @return 计算后的结果
	 * **/
	public static String getNameByDate(Date targetDate) {
		long times= DateUtil.getTimes(new Date(), targetDate);
		return getNameByDate(times);
	}
	
	
	public static String getNameByDate(Object obj){
		if(null==obj){
			return null;
		}
		if(obj instanceof String){
			return getNameByDate((String)obj);
		}else if(obj instanceof Date){
			return getNameByDate((Date)obj);
		}else if(obj instanceof Long){
			return getNameByDate((Long)obj);
		}
		return null;
	}
	
	public static void main(String[] args){
		String strDate="2013-11-26 14:05:55";
		Date targetDate=DateUtil.stringToDate(strDate, DateUtil.YYYY_MM_DD_HH_MM_SS);
		String nameByDate=getNameByDate(targetDate);
		System.out.println(nameByDate);
	}
}
