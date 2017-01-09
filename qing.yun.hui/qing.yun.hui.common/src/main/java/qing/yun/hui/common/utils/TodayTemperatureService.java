package qing.yun.hui.common.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述：趴取网页上的今天的天气
 */
public class TodayTemperatureService {
    
     /** 
     * 发起http get请求获取网页源代码 
     * @param requestUrl     String    请求地址
     * @return                 String    该地址返回的html字符串
     */  
    private static String httpRequest(String requestUrl) {  
        
        StringBuffer buffer = null;  
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        InputStream inputStream = null;
        HttpURLConnection httpUrlConn = null;
  
        try {  
            // 建立get请求
            URL url = new URL(requestUrl);  
            httpUrlConn = (HttpURLConnection) url.openConnection();  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setRequestMethod("GET");  
  
            // 获取输入流  
            inputStream = httpUrlConn.getInputStream();  
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            bufferedReader = new BufferedReader(inputStreamReader);  
  
            // 从输入流读取结果
            buffer = new StringBuffer();  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  finally {
            // 释放资源
            if(bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inputStreamReader != null){
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(httpUrlConn != null){
                httpUrlConn.disconnect();  
            }
        }
        return buffer.toString();  
    }  
  
    /** 
     * 过滤掉html字符串中无用的信息
     * @param html    String    html字符串
     * @return         String    有用的数据
     */ 
    private static String htmlFiter(String html) {  
        
        StringBuffer buffer = new StringBuffer();  
        String str1 = "";
        String str2 = "";
        buffer.append("今天:");
        
        // 取出有用的范围
        //Pattern p = Pattern.compile("(.*)(<li class=\'dn on\' data-dn=\'7d1\'>)(.*?)(</li>)(.*)");  
        Pattern p=Pattern.compile("(.*<div class=\"con today .*)");
        Matcher m = p.matcher(html);  
        if (m.matches()) { 
        	m.group(2);
            str1 = m.group(3);
            // 匹配日期，注：日期被包含在<h2> 和 </h2>中
            p = Pattern.compile("(.*)(<h2>)(.*?)(</h2>)(.*)");
            m = p.matcher(str1);
            if(m.matches()){
            	
                str2 = m.group(3);
                buffer.append(str2);
                buffer.append("\n天气：");
            }
            // 匹配天气，注：天气被包含在<p class="wea" title="..."> 和 </p>中
            p = Pattern.compile("(.*)(<p class=\"wea\" title=)(.*?)(>)(.*?)(</p>)(.*)");
            m = p.matcher(str1);
            if(m.matches()){
                str2 = m.group(5);
                buffer.append(str2);
                buffer.append("\n温度：");
            }
            // 匹配温度，注：温度被包含在<p class=\"tem tem2\"> <span> 和 </span><i>中
            p = Pattern.compile("(.*)(<p class=\"tem tem2\"> <span>)(.*?)(</span><i>)(.*)");
            m = p.matcher(str1);
            if(m.matches()){
                str2 = m.group(3);
                buffer.append(str2);
                buffer.append("°~");
            }
            p = Pattern.compile("(.*)(<p class=\"tem tem1\"> <span>)(.*?)(</span><i>)(.*)");
            m = p.matcher(str1);
            if(m.matches()){
                str2 = m.group(3);
                buffer.append(str2);
                buffer.append("°\n风力：");
            }
            // 匹配风，注：<i> 和 </i> 中
            p = Pattern.compile("(.*)(<i>)(.*?)(</i>)(.*)");
            m = p.matcher(str1);
            if(m.matches()){
                str2 = m.group(3);
                buffer.append(str2);
            }
        }  
        return buffer.toString();
    }
    
    /** 
     *  对以上两个方法进行封装。
     * @return 
     */  
    public static String getTodayTemperatureInfo() {  
        // 调用第一个方法，获取html字符串
        String html = httpRequest("http://www.weather.com.cn/html/weather/101280101.shtml");  
        // 调用第二个方法，过滤掉无用的信息
        String result = htmlFiter(html);  
        
        return result;  
    }  
  
    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {  
        String info = getTodayTemperatureInfo();
        System.out.println(info);
    }  
}