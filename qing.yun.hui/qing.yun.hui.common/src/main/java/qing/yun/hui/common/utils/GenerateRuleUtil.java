package qing.yun.hui.common.utils;


/***
 ** @category 用来生成一些有标识的单号或批号等等...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月1日下午3:57:42
 **/
public class GenerateRuleUtil {
	
	public static void main(String[] args){
		String joint ="&=yzs=?";
		String projectName="movie";
		System.out.println(generateUnique(joint, projectName));
	}
	
	/**
	 * <p>获取当前系统时间戳</p>
	 * @return 当前系统时间戳
	 * */
	public static long getTimestamp(){
		return System.currentTimeMillis();
	}
	
	/**
	 *<p>生成唯一标识</p>
	 *@param joint <p>拼接符</p>
	 * */
	public static String generateUnique(String joint,String projectName){
		long timestamp=getTimestamp();
		StringBuffer sb=new StringBuffer();
		sb.append(timestamp);
		sb.append(joint);
		StringBuilder _sb=new StringBuilder();
		_sb.append(timestamp).append(projectName);
		String encryptionStr= MD5Util.getMD5Encryption(_sb.toString());
		sb.append(encryptionStr);
		return sb.toString();
	}
}
