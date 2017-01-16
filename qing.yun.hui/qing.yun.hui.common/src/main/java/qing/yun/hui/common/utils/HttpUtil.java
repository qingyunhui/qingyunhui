package qing.yun.hui.common.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qing.yun.hui.common.constants.Constant;
import qing.yun.hui.common.constants.SymbolConstant;

import com.alibaba.fastjson.JSONObject;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月5日下午2:48:56
 **/
public class HttpUtil {
	
	private static Logger logger =LoggerFactory.getLogger(HttpUtil.class);
	
	 /**默认连接超时时间*/
	 private static final int DEF_CONN_TIMEOUT = 30000;
	 
	 /**默认读取超时时间*/
	 private static final int DEF_READ_TIMEOUT = 30000;
	 
	 private static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
	
	public static void main(String[] args){
		/*String url="http://yzs.movie.com/sys/sysData/addSysDatas.htm";
		String param="";
		sendGet(url, param);
		sendPost(url, param);*/
		String httpUrl="http://api.map.baidu.com/telematics/v3/weather";
    	String location="杭州";
    	String output="json";//json or xml
    	String ak="cYHchCiGwMbW9HdVWTFfnRssl0eGdi0P";
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("location", location);
		param.put("output", output);
		param.put("ak", ak);
    	String json=null;
		try {
			json = sendRequest(httpUrl, param,"post");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(json);
		
	}
	
	/**
    *
    * @param strUrl 请求地址
    * @param params 请求参数
    * @param method <p>请求方法{get 或者 post}，如果为null则会默认以get方式请求，如果值不为get，post则系统也会默认以get请求.</p>
    * @return  网络请求字符串
    * @throws Exception
    */
   public static String sendRequest(String httpUrl, Map<String,Object> params,String method) throws Exception {
	   if(StringUtil.isEmpty(method)) method=Constant.GET;
	   if(!Constant.GBK.equalsIgnoreCase(method) || !Constant.POST.equalsIgnoreCase(method)) method=Constant.GET;
       HttpURLConnection conn = null;
       BufferedReader reader = null;
       String rs = null;
       try {
           StringBuffer sb = new StringBuffer();
           if(method.equalsIgnoreCase(Constant.GET))  httpUrl = httpUrl+SymbolConstant.QUESTION+StringUtil.urlencode(params);
           URL url = new URL(httpUrl);
           conn = (HttpURLConnection) url.openConnection();
           conn.setRequestMethod(method);
           if(method.equalsIgnoreCase(Constant.POST)) conn.setDoOutput(true);
           conn.setRequestProperty("User-agent", userAgent);
           conn.setUseCaches(false);
           conn.setConnectTimeout(DEF_CONN_TIMEOUT);
           conn.setReadTimeout(DEF_READ_TIMEOUT);
           conn.setInstanceFollowRedirects(false);
           conn.connect();
           if (StringUtil.isEmpty(params) && method.equalsIgnoreCase(Constant.POST)) {
               try {
                   DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                   out.writeBytes(StringUtil.urlencode(params));
               } catch (Exception e) {
                   logger.error("处理异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
               }
           }
           InputStream is = conn.getInputStream();
           reader = new BufferedReader(new InputStreamReader(is, Constant.UTF_8));
           String strRead = null;
           while ((strRead = reader.readLine()) != null) {
               sb.append(strRead);
           }
           rs = sb.toString();
       } catch (IOException e) {
    	   logger.error("处理异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
       } finally {
           if (reader != null) {
               reader.close();
           }
           if (conn != null) {
               conn.disconnect();
           }
       }
       return rs;
   }
}
