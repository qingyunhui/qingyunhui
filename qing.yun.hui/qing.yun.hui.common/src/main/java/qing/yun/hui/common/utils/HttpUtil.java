package qing.yun.hui.common.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
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
		
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("name", "张三");
		map.put("age", 29);
		map.put("sex", 2);
		
	}
	
	/**
    *
    * @param strUrl 请求地址
    * @param params 请求参数
    * @param method <p>请求方法{get 或者 post}，如果为null则会默认以get方式请求，如果值不为get，post则系统也会默认以get请求.</p>
    * @return  网络请求字符串
    * @throws Exception
    */
   public static String sendRequest(String strUrl, Map<String,Object> params,String method) throws Exception {
	   if(StringUtil.isEmpty(method)) method=Constant.GET;
	   if(!Constant.GBK.equalsIgnoreCase(method) || !Constant.POST.equalsIgnoreCase(method)) method=Constant.GET;
       HttpURLConnection conn = null;
       BufferedReader reader = null;
       String rs = null;
       try {
           StringBuffer sb = new StringBuffer();
           if(method.equalsIgnoreCase(Constant.GET))  strUrl = strUrl+SymbolConstant.QUESTION+StringUtil.urlencode(params);
           URL url = new URL(strUrl);
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
	
	/**
     * 向指定URL发送GET方法的请求
     * @param url  发送请求的URL
     * @param param  请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    
    /**
     * 向指定 URL 发送POST方法的请求
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader( new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
}
