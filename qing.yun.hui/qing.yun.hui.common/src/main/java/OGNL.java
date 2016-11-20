import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;


/***
 ** @Description: 非空处理-可用作mybatis中的;sql.xml中的sql非空判断
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Jun 10, 2015 12:00:03 AM
 ** @version: V1.0
 ***/
public class OGNL {
	
	
	public static boolean isNotEmpty(Object obj){
		return !isEmpty(obj);
	}

	
	/***
	 * 非空校验,如果obj为空将会返回true.
	 * @param obj 待校验的对象。
	 * @return true or false。
	 * ***/
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
		}else if(obj.getClass().isArray()){
			if(Array.getLength(obj)==0){
				return true;
			}
		}else{
			return false;
		}
		return false;
	}
	
	public static void main(String[] args){
		System.out.println(isEmpty(""));
	}
	
}
