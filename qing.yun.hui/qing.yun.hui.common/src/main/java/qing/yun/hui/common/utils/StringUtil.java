package qing.yun.hui.common.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qing.yun.hui.common.constants.Constant;
import qing.yun.hui.common.constants.SymbolConstant;
import qing.yun.hui.common.enums.TimeSlot;

import com.alibaba.fastjson.JSONObject;

/***
 ** @Description: 常用字符工具类
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 10:07:57 AM
 ** @version: V1.0
 ***/
public class StringUtil {
	
	private static Logger logger=LoggerFactory.getLogger(StringUtil.class);
	
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj){
		if(null==obj){
			return true;
		}
		if(obj instanceof Map){
			if(((Map)obj).isEmpty()){
				return true;
			}
		}else if(obj instanceof Collection){
			if(((Collection)obj).isEmpty()){
				return true;
			}
		}
		else if(obj instanceof String){
			if(((String)obj).trim().length()==0){
				return true;
			}
			if("[]".equals(obj)){
				return true;
			}
		}else if(obj.getClass().isArray()){
			if(Array.getLength(obj)==0){
				return true;
			}
		}else{
			return false;
		}
		return false;
	}
    
	public static boolean isEmpty(Object...objects){
		if(null==objects || objects.length<1){
			return true;
		}
		for(Object obj:objects){
			boolean isEmpty=isEmpty(obj);
			if(isEmpty) return true;
		}
		return false;
	}
    
	/***
	 * 把字符串按照符号分割成数组形式
	 * @param str 待分割的字符串
	 * @param fuhao 用作分割的符号
	 * @return String[]
	 * ***/
	 public static String[] stringToArray(String str,String fuhao){
		String[] array=null;
		if(!StringUtil.isEmpty(str)){
			str=truncateBothCharact(str,fuhao);
			if(str.indexOf(fuhao)!=-1){
				array=str.split(fuhao);
			}else{
				array=new String[]{str};
			}
		}
		return array=null==array?new String[]{}:array;
	}
	 
	/**
	 * 如果给定的字符串、的首尾字母包含符号的话，那么就截掉如:(,1,3,4,56,37,)截取后为:(1,3,4,56,37)
	 * @param str 待截取的字符
	 * @param fuhao 是否包含字符
	 * @return 截取后的新字符
	 * */
	public static String  truncateBothCharact(String str,String fuhao){
		if(str.startsWith(fuhao)){
			str=str.substring(1,str.length());
		}
		if(str.endsWith(fuhao)){
			str=str.substring(0,str.length()-1);
		}
		return str;
	}
	 
	 /**
	  * 把str剪切不超过count个长度;
	  * @param str 待剪切的字符
	  * @param count 剪切字符的最大长度;
	  * @return 剪切后的字符串
	  * ***/
	 public static String truncate(String str,int count){
		 if(null==str) return null;
		 char[] charArray=str.toCharArray();
		 int len=charArray.length;
		 for(int i=0;i<count && i<len;i++){
			 if(charArray[i]>0XFF){
				 count--;
			 }
		 }
		 count=count>0 ?count:0;
		 int currCount=count<len?count:len;
		 return str.substring(0,currCount);
	 }
	 
	 /**
	  * 如果要截取的字符串存在指定的字符那么会返回截取后的字符串;
	  * @param str 待截取的字符串
	  * @param character 待截取的字符
	  * @return 截取后的新字符串
	  * ***/
	 public static String getCutStr(String str,String character){
		 String newStr=null;
		 int index = str.indexOf(character);
		 if (index != -1) {
			 newStr = str.substring(0, index);
			 return newStr;
		 }else{
			return str; 
		 }
	 }
	 
	 /**
	  * 去除重复数据如:([1,3,5,7,1,3]，返回后为:[1,3,5,7])
	  * @param str[] 待去除重复的数组
	  * @return String[] 返回去除重复数组后的新数组
	  * **/
	 public static String[] repeated(String[] str){
			Map<String,String> map=new HashMap<String,String>();
			String newStr[]=null;
			if(null==str || str.length<=0){
				return newStr;
			}
			for(String s:str){
				if(!map.containsKey(s)){
					map.put(s, s);
				}
			}
			List<String> list=new ArrayList<String>();
			Iterator<String> iter=map.keySet().iterator();
			while(iter.hasNext()){
				 String key=iter.next();
				 list.add(key);
			}
			newStr=new String[list.size()];
			for(int i=0;i<list.size();i++){
				newStr[i]=list.get(i);
			}
			return newStr;
		}
	 
	 	/**
	 	 * 去除重复数据(请不要转入Collection[比如:List或者Set]型的数据)
	 	 * @param object 待去重的数据...
	 	 * @return 去重后的数据...
	 	 * ***/
	    public static Object[] repeated(Object... object){
	    	if(null==object || object.length<1){
	    		return object;
	    	}
	    	Set<Object> set=new HashSet<Object>();
	    	for(Object obj:object){
	    		set.add(obj);
	    	}
	    	return set.toArray();
	    }
	    
	    /***
	     * 去除重复数据且保持原始数据顺序
	     * @param list 待去重的数据
	     */
	    @SuppressWarnings({ "rawtypes", "unchecked" })
	    public static void repeatByList(List list) {
			Set set = new HashSet();
			List newList = new ArrayList();
			if(list != null && list.size() > 0) {
				for (Iterator iter = list.iterator(); iter.hasNext();) {
					Object element = iter.next();
					if (set.add(element))
						newList.add(element);
				}
				list.clear();
				list.addAll(newList);
			}
		 }
		 
	    /***
	     * 去除重复数据且保持原始数据顺序
	     * @param list 待去重的数据
	     */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public static void repeatList(List list){
			 if(null!=list&& list.size()>0){
				 Set<Object> set=new LinkedHashSet<Object>();
				 set.addAll(list);
				 list.clear();
				 list.addAll(set);
			 }
		 }
	 
	 	/**
		 * 获取IP地址
		 * 
		 * @return IP地址
		 */
		public static String getIPAddress() {
			InetAddress addr;
			String ipAddress = null;
			try {
				addr = InetAddress.getLocalHost();
				ipAddress = addr.getHostAddress().toString();// 获得本机IP
				// address=addr.getHostName()toString;// 获得本机名称
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			return ipAddress;
		}
		
		/***
		 * 首字母转换
		 * @param str 待转换的字符
		 * @param isUpper true:首字母转换大写 , false:首字母转换小写
		 * @return 转换后的字符
		 * **/
		public static String firstLetterConvert(String str,boolean isUpper) {
			StringBuffer buff = new StringBuffer(str);
			buff.replace(0, 1, String.valueOf(isUpper?Character.toUpperCase(str.charAt(0)):Character.toLowerCase(str.charAt(0))));
			return buff.toString();
		}
		
		/**
		 * 根据给定时间、返回对应的时间段。
		 * @param date 日期
		 * @return 返回对应的时间段。
		 * **/
		public static String getTimeSlotDesc(Date date){
			int hour=DateUtil.getHour(date, true);
			String timeSlotDesc="";
			for(TimeSlot timeSlot:TimeSlot.values()){
				if(hour>=timeSlot.getBeginHour() && hour<timeSlot.getEndHour()){
					timeSlotDesc= timeSlot.getName();
					break;
				}
			}
			return timeSlotDesc;
		}
		
		/**
		 *根据给定文件名、获取其后缀名，如果给定文件名为null或为空将会返回null或空。
		 *@param fileName 待获取后缀的文件名
		 *@param fuhao 以此符号为起点
		 *@return 后缀名 
		 ****/
		public static String getSuffixByFilename(String fileName,String fuhao){
			if(isEmpty(fileName)){
				return fileName;
			}
			return fileName.substring(fileName.lastIndexOf(fuhao)).toLowerCase();
		}
		
		/**
		 * 为要操作的字符串添加分隔符(/),条件是如果str最后一个字符不是/或\(分隔符)以这个为结尾则添加之。
		 * @param str 要操作的字符串
		 * @return 处理后的字符串
		 * */
		public static String addSeparatorChar(String str){
			String lastCharacter=str.substring(str.length()-1);
			if(!lastCharacter.equals("/") && !lastCharacter.equals(File.separatorChar)){
				str+=File.separatorChar;
			  }
			return str;
		}
		
		/***
		 * 根据给定的实体名称得到对应的表名如(AppUser >> app_user的转换)
		 * @param entity 待转换的实体名
		 * @return String 转换后的(如果entity为空则会返回null);
		 * **/
		public static String getTableName(String entity){
			if(isEmpty(entity)){
				return null;
			}
			/*小写字母a-z 对应数字97-122 , 大写字母A-Z对应数字65-90*/
			char[] chars=entity.toCharArray();
			StringBuffer sb=new StringBuffer();
			for(int i=0;i<chars.length;i++){
				if(i==0){
					sb.append(chars[i]);
					continue;
				}
				if((int)chars[i]>=65 && chars[i]<=90){
					sb.append("_").append(chars[i]);
				}else{
					sb.append(chars[i]);
				}
			}
			return sb.toString().toLowerCase();
		}
		
		/***
		 * 根据给定的实体对象,得到对应的表名如(AppUser >> app_user的转换)
		 * @param entity 待转换的实体对象
		 * @return String 转换后的(如果entity为空则会返回null);
		 * **/
		public static String getTableName(Class<?> entity){
			return getTableName(entity.getSimpleName());
		}
		
		/**
		 * 字节到KB的转换
		 * @param byteFile 要转换的字节
		 * @return 转换后的kb
		 * */
		public static double getFileKB(long byteFile){  
	        if(byteFile==0)  
	           return 0;  
	        long kb=1024;  
	        BigDecimal result=new BigDecimal(byteFile).divide(new BigDecimal(kb)).setScale(2, BigDecimal.ROUND_HALF_UP);
	        return result.doubleValue();
//	        return byteFile/kb;  
	    }
		
		/**
		 * 字节到MB的转换
		 * @param byteFile 要转换的字节
		 * @return 转换后的MB
		 * **/
		public static double getFileMb(long byteFile){
			 if(byteFile==0)   return 0;
			 long KB=1024;
			 long MB=1024;
			 BigDecimal result=new BigDecimal(byteFile).divide(new BigDecimal(KB)).setScale(2,BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(MB)).setScale(2,BigDecimal.ROUND_HALF_UP);
			 //return byteFile/KB/MB;  
			 return result.doubleValue();
		}
		
		/**
		 * 字符替换
		 * @param str 要替换的字符
		 * @param sindex 起始位置
		 * @param eindex 结束位置
		 * @param symbol 以symbol为替换符号
		 * @param numberSymbol 替换符号个数..
		 * @return String 替换后的字符串..
		 * **/
		public static String replacePhoneOrEmailByAsterisk(String phoneOrEmail,int sindex,int eindex,int numberSymbol,String symbol){
			if(isEmpty(phoneOrEmail)){
				return phoneOrEmail;
			}
			StringBuffer sb=new StringBuffer(phoneOrEmail);
			StringBuffer sbSymbol=new StringBuffer();
			for(int i=0;i<numberSymbol;i++){
				sbSymbol.append(SymbolConstant.ASTERISK);
			}
			if(ValidateUtil.isMobile(phoneOrEmail)){
				sb.replace(sindex, eindex, sbSymbol.toString());
			}
			if(ValidateUtil.isEmail(phoneOrEmail)){
				sb.replace(sindex, phoneOrEmail.lastIndexOf(SymbolConstant.AT), sbSymbol.toString());
			}
			else{
				sb.replace(sindex, eindex, sbSymbol.toString());
			}
			return sb.toString();
		}
		
		/**
		 * 字符到十六进制 的转换......
		 * @param str 待转换的字符串
		 * @return String 转换后的十六进制字符
		 * */
		public static String strToHexByStr(String str) {
			char[] chars = Constant.HEX_STR.toCharArray();
			StringBuilder sb = new StringBuilder();
			byte[] bs = str.getBytes();
			int bit;
			for (int i = 0; i < bs.length; i++) {
				bit = (bs[i] & 0x0f0) >> 4;
				sb.append(chars[bit]);
				bit = bs[i] & 0x0f;
				sb.append(chars[bit]);
			}
			return sb.toString();
		}
		
		/**
		 * 十六进制字符转换字符串
		 * @param hexStr 待转换的十六进制字符
		 * @return String 转换后的字符串
		 */
		public static String hexStrToStr(String hexStr) {
			String str = "0123456789ABCDEF";
			char[] hexs = hexStr.toCharArray();
			byte[] bytes = new byte[hexStr.length() / 2];
			int n;
			for (int i = 0; i < bytes.length; i++) {
				n = str.indexOf(hexs[2 * i]) * 16;
				n += str.indexOf(hexs[2 * i + 1]);
				bytes[i] = (byte) (n & 0xff);
				/*Integer.toHexString的参数是int，如果不进行&0xff，那么当一个byte会转换成int时，由于int是32位，而byte只有8位这时会进行补位，
				例如补码11111111的十进制数为-1转换为int时变为11111111111111111111111111111111好多1啊，呵呵！即0xffffffff但是这个数是不对的，这种补位就会造成误差。
				和0xff相与后，高24比特就会被清0了，结果就对了。*/
			}
			return new String(bytes);
		}
		
		/**
		 * <p>将map型转为请求参数型，并对其进行utf-8编码</p>
		 * @param data 待转换的map
		 * @return String
		 * **/
	    public static String urlencode(Map<String,Object> data) {
	    	if(StringUtil.isEmpty(data)) return null;
	        StringBuilder sb = new StringBuilder();
	        for (Map.Entry<String,Object> map : data.entrySet()) {
	            try {
	                sb.append(map.getKey()).append("=").append(URLEncoder.encode(map.getValue()+"",Constant.UTF_8)).append("&");
	            } catch (UnsupportedEncodingException e) {
	                logger.error("转换异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
	            }
	        }
	        if(sb.toString().length()>0&&sb.toString().lastIndexOf(SymbolConstant.AND)!=-1){
	        	return sb.substring(0, sb.length()-1);
	        }
	        return sb.toString();
	    }
		
		public static void main(String[] args){
			
			long byteFile=230100213;
			System.out.println(getFileMb(byteFile));
			
			//String s=qing.yun.hui.common.bean.BaseQuery.class.getSimpleName();
			//String s="qing.yun.hui.common.bean.BaseQuery";
			//String s=Object.class.getSimpleName();
			//System.out.println("转换前:"+s+"\n转换后:"+getTableName(s));
//			System.out.println(getTableName(BaseQuery.class));
			/*System.out.println(replacePhoneOrEmailByAsterisk("280672161@qq.com",3,5,4,Symbol.ASTERISK));
			System.out.println(replacePhoneOrEmailByAsterisk("18665300640",3,5,4,Symbol.ASTERISK));
			System.out.println(replacePhoneOrEmailByAsterisk("280admin123qq",3,5,4,Symbol.ASTERISK));*/
			
//			boolean isEmpty=isEmpty(22,null);
//			System.out.println(isEmpty);
		}

}
