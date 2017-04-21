package qing.yun.hui.common.utils;

import java.util.Arrays;
import java.util.Map;

import qing.yun.hui.common.struct.baidu.BaiduConstant;

import com.alibaba.fastjson.JSONObject;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月17日下午1:33:22
 **/
public class WebUtil {
	/**
	 * 获取总页数
	 * @param totalCount 总记条数
	 * @param defaultCount 默认一次性处理defaultCount条记录
	 * @return 总页数
	 * */
	public static int getTotalPageCount(int totalCount,int defaultCount){
		int totalSize=totalCount;
		int mod=-1;
		int pageCount=0;
		mod = totalSize % defaultCount;
		if (mod != 0) {
			pageCount = (totalSize / defaultCount) + 1;
		} else {
			pageCount = (totalSize / defaultCount);
		}
		return pageCount;
	}
	
	/**
	 * <p>当args包含contain时,将其转换成JSON串</p>
	 * @param args 参数
	 * @param contains 包含的字符串
	 * @return JSON串
	 * */
	public static String argsToJSON(Object[] args,String ...contains){
		if(null==contains||contains.length<1) return JSONObject.toJSONString(args);
		if(null!=args){
			StringBuffer sb=new StringBuffer();
			int count=0;
			int x=0;
			for(Object arg:args){
				if(StringUtil.isEmpty(arg)) continue;
				String name=arg.getClass().getName();
				for(String contain:contains){
					if(!name.startsWith(contain)) continue;
					if(x>0 && count<args.length)sb.append(",");
					if(x==0)sb.append("[");
					sb.append(JSONObject.toJSONString(arg));
					x++;
					break;
				}
				count++;
				if(x>0 && count==args.length){
					sb.append("]");
				}
			}
			return sb.toString();
		}
		return null;
	}
	
	public static void main(String[] args){
		Object[] argss=new Object[]{Arrays.class,Map.class,String.class,BaiduConstant.class};
		System.out.println(WebUtil.argsToJSON(argss));
	}
}
