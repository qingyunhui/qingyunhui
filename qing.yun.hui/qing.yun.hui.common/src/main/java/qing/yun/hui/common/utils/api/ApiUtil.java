package qing.yun.hui.common.utils.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qing.yun.hui.common.constants.APIConstant;
import qing.yun.hui.common.constants.Constant;
import qing.yun.hui.common.utils.DateUtil;
import qing.yun.hui.common.utils.HttpUtil;

import com.alibaba.fastjson.JSONObject;

/***
 ** @category 该API是用来获取一些公共的接口信息，比如获取北京时间，天气预报，等等信息...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月11日上午10:17:33
 **/
public class ApiUtil {
	
	private static Logger logger=LoggerFactory.getLogger(ApiUtil.class);
	
	
	
	
	
	 	public static final String DEF_CHATSET = "UTF-8";
	    public static final int DEF_CONN_TIMEOUT = 30000;
	    public static final int DEF_READ_TIMEOUT = 30000;
	    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
	   
	    //配置您申请的KEY
	    public static final String APPKEY ="386497698e93fa097d9391f5d0153f9d";
	    
	    //默认手机号
	    public static final String PHONE="18665300640";
	    
	    //默认输出类型
	    public static final String OUTPUT_TYPE="json";
	 
	    public static void getMobileData(String httpUrl,String mobile,String key,String outputType,String method){
	    	Map<String,Object> params=new HashMap<String, Object>();
	    	params.put("phone", mobile);
	    	params.put("key", key);
	    	params.put("dtype", outputType);
	    	try {
	    		HttpUtil.sendRequest(httpUrl, params,method);
			} catch (Exception e) {
				logger.error("处理异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
			}
	    	
	    }
	    
	    //1.手机归属地查询
	    public static void getRequest1(){
	        String result =null;
	        String url ="http://apis.juhe.cn/mobile/get";//请求接口地址
	        Map params = new HashMap();//请求参数
	            params.put("phone",PHONE);//需要查询的手机号码或手机号码前7位
	            params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
	            params.put("dtype",OUTPUT_TYPE);//返回数据的格式,xml或json，默认json
	 
	        try {
	            result =net(url, params, "GET");
	            JSONObject object = JSONObject.parseObject(result);
	            if(object.getInteger("error_code").intValue()==0){
	                System.out.println(object.get("result"));
	            }else{
	                System.out.println(object.get("error_code")+":"+object.get("reason"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 
	    /**
	     *
	     * @param strUrl 请求地址
	     * @param params 请求参数
	     * @param method 请求方法
	     * @return  网络请求字符串
	     * @throws Exception
	     */
	    public static String net(String strUrl, Map params,String method) throws Exception {
	        HttpURLConnection conn = null;
	        BufferedReader reader = null;
	        String rs = null;
	        try {
	            StringBuffer sb = new StringBuffer();
	            if(method==null || method.equals("GET")){
	                strUrl = strUrl+"?"+urlencode(params);
	            }
	            URL url = new URL(strUrl);
	            conn = (HttpURLConnection) url.openConnection();
	            if(method==null || method.equals("GET")){
	                conn.setRequestMethod("GET");
	            }else{
	                conn.setRequestMethod("POST");
	                conn.setDoOutput(true);
	            }
	            conn.setRequestProperty("User-agent", userAgent);
	            conn.setUseCaches(false);
	            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
	            conn.setReadTimeout(DEF_READ_TIMEOUT);
	            conn.setInstanceFollowRedirects(false);
	            conn.connect();
	            if (params!= null && method.equals("POST")) {
	                try {
	                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
	                    out.writeBytes(urlencode(params));
	                } catch (Exception e) {
	                    // TODO: handle exception
	                    e.printStackTrace();
	                }
	                 
	            }
	            InputStream is = conn.getInputStream();
	            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
	            String strRead = null;
	            while ((strRead = reader.readLine()) != null) {
	                sb.append(strRead);
	            }
	            rs = sb.toString();
	        } catch (IOException e) {
	            e.printStackTrace();
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
	 
	    //将map型转为请求参数型
	    public static String urlencode(Map<String,String> data) {
	        StringBuilder sb = new StringBuilder();
	        for (Map.Entry i : data.entrySet()) {
	            try {
	                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
	            } catch (UnsupportedEncodingException e) {
	                e.printStackTrace();
	            }
	        }
	        return sb.toString();
	    }
	
	
	
    
    public static void main(String[]args){
    	
    	
    	getRequest1();
    	
    	/*Date date=getChinaDate(APIConstant.BEIJING_TIME_URL);
    	Date sysDate=new Date();
    	System.out.println("北京时间:"+DateUtil.dateToString(date, DateUtil.YYYY_MM_DD_HH_MM_SS));
    	System.out.println("系统时间:"+DateUtil.dateToString(sysDate, DateUtil.YYYY_MM_DD_HH_MM_SS));*/
    	
    	
    }
    /**
     * <p>获取中国【北京】标准时间</p>
     * @param httpUrl <p>待请求的{@link qing.yun.hui.common.constants.APIConstant httpUrl}地址</p>
     * @return Date
     */
    public static Date getChinaDate(String httpUrl){
        try {
            URL url = new URL(httpUrl);// 取得资源对象
            URLConnection uc = url.openConnection();// 生成连接对象
            uc.connect();// 发出连接
            long datetime = uc.getDate();// 读取网站日期时间
            Date date = new Date(datetime);// 转换为标准时间对象
            return date;
        } catch (MalformedURLException e) {
        	logger.info("请求异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
        } catch (IOException e) {
        	logger.info("请求异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
        }
        return new Date();
    }
}
