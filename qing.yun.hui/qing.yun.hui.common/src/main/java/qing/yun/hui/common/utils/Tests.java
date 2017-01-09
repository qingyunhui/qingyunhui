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
public class Tests {
    
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
        
    	System.out.println("html:"+html);
    	
        StringBuffer buffer = new StringBuffer();  
        String str1 = "";
        String str2 = "";
        buffer.append("今天:");
        
        // 取出有用的范围
        //Pattern p = Pattern.compile("(.*)(<li class=\'dn on\' data-dn=\'7d1\'>)(.*?)(</li>)(.*)");  
        Pattern p=Pattern.compile("(.*)(<li class=\"sky skyid lv2 on\">)(.*?)(</li>)(.*)");
        Matcher m = p.matcher(html);  
        if (m.matches()) { 
            str1 = m.group(3);
            // 匹配日期，注：日期被包含在<h2> 和 </h2>中
            p = Pattern.compile("(.*)(<h1>)(.*?)(</h1>)(.*)");
            m = p.matcher(str1);
            if(m.matches()){
                str2 = m.group(3);
                buffer.append(str2);
                buffer.append("\n天气：");
            }
            // 匹配天气，注：天气被包含在<p class="wea" title="..."> 和 </p>中
            p = Pattern.compile("(.*)(<p title=(.*?) class=\"wea\">)(.*?)(.*?)(</p>)(.*)");
            m = p.matcher(str1);
            if(m.matches()){
                str2 = m.group(5);
                buffer.append(str2);
                buffer.append("\n温度：");
            }
            //<p title="多云" class="wea">多云</p>
            // 匹配温度，注：温度被包含在<p class=\"tem tem2\"> <span> 和 </span><i>中
            
            
            //<h1>9日（今天）</h1>
            //<p title="多云" class="wea">多云</p>
            
            //<p class="tem"><span>24</span>/<i>15℃</i></p>
            
            //<p class="win"><em><span title="无持续风向" class=""></span><span title="无持续风向" class=""></span></em><i>微风</i></p>
            
            p = Pattern.compile("(.*)(<p class=\"tem\"><span>)(.*?)(</span>)(.*?)(<i>)(.*?)(</i>)(</p>)(.*)");
            m = p.matcher(str1);
            if(m.matches()){
//            	int count=m.groupCount();//
            	/*for(int i=0;i<count;i++){
            		System.out.println("i:"+m.group(i));
            	}*/
//            	System.out.println("count:"+m.groupCount());
                str2 = m.group(3);
                buffer.append(str2);
                buffer.append("°~");
                str2=m.group(7);
                buffer.append(str2);
                buffer.append("\n风力：");
            }
            //<p class="tem"><span>24</span>/<i>15℃</i></p>
            //p = Pattern.compile("(.*)(<p class=\"tem\"> <span>)(.*?)(</span><i>)(.*)");
           /* m = p.matcher(str1);
            if(m.matches()){
                str2 = m.group(3);
                buffer.append(str2);
                buffer.append("°\n风力：");
            }*/
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
    	
    	
    	String $city="嘉兴";
    	String url="http://api.map.baidu.com/telematics/v3/weather?location="+$city+"&output=json&ak=cYHchCiGwMbW9HdVWTFfnRssl0eGdi0P";
    	
//        String html = httpRequest("http://www.weather.com.cn/html/weather/101280101.shtml");   //广州天气
    	  String html=httpRequest(url);//杭州天气 "http://www.weather.com.cn/weather1d/101210101.shtml"
        //http://www.weather.com.cn/data/sk/101280101.html  该接口直接返回json串...
        
        //1：http://www.weather.com.cn/data/sk/101010100.html
        
        //2：http://www.weather.com.cn/data/cityinfo/101010100.html
        
        //3：http://m.weather.com.cn/data/101110101.html
        
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
    
    
    
    /*{"error":0,"status":"success","date":"2017-01-09",
    "results":[
    {"currentCity":"嘉兴","pm25":"123",
    	 "index":[{"title":"穿衣","zs":"较冷","tipt":"穿衣指数","des":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"},
    	          {"title":"洗车","zs":"较适宜","tipt":"洗车指数","des":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},
    	          {"title":"旅游","zs":"一般","tipt":"旅游指数","des":"天气较好，风稍大，体感稍凉，旅游指数一般，外出请注意防风御寒。"},
    	          {"title":"感冒","zs":"较易发","tipt":"感冒指数","des":"天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。"},
    	          {"title":"运动","zs":"较不宜","tipt":"运动指数","des":"阴天，且天气寒冷，推荐您在室内进行低强度运动；若坚持户外运动，请选择合适的运动并注意保暖。"},
    	          {"title":"紫外线强度","zs":"最弱","tipt":"紫外线强度指数","des":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"
    	         }],
    	  "weather_data":[{"date":"周一 01月09日 (实时：6℃)","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/yin.png",
    		  			   "nightPictureUrl":"http://api.map.baidu.com/images/weather/night/duoyun.png","weather":"阴转多云",
    		  			   "wind":"北风3-4级","temperature":"9 ~ 3℃"
    		  			   },
    		  			   {"date":"周二","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/duoyun.png",
    		  			    "nightPictureUrl":"http://api.map.baidu.com/images/weather/night/yin.png",
    		  			    "weather":"多云转阴","wind":"东北风微风","temperature":"10 ~ 5℃"
    		  			    },
    		  			    {"date":"周三","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/xiaoyu.png",
    		  			     "nightPictureUrl":"http://api.map.baidu.com/images/weather/night/xiaoyu.png","weather":"小雨",
    		  			     "wind":"东风微风","temperature":"9 ~ 5℃"
    		  			     },
    		  			     {"date":"周四","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/xiaoyu.png",
    		  			      "nightPictureUrl":"http://api.map.baidu.com/images/weather/night/yin.png","weather":"小雨转阴",
    		  			       "wind":"东北风微风","temperature":"8 ~ 4℃"
    		  			      }
    		  			     ]
    	  			   }]
    	  			}
    */
  
    
    
}