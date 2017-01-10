package qing.yun.hui.common.struct.weather;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qing.yun.hui.common.utils.StringUtil;

import com.alibaba.fastjson.JSONObject;

/**
 * 描述：趴取网页上的今天的天气
 */
public class WeatherUtil {
	
	public static Logger logger =LoggerFactory.getLogger(WeatherUtil.class);
    
     /** 
     * 发起http get请求获取网页源代码 
     * @param requestUrl 请求地址
     * @return             
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
     * <p>封装调用远程接口后的data</p>
     * @param httpUrl 待请求的url (必填项)
     * @param location <p>输入城市名或经纬度，城市名称如:北京，经纬度格式为lng,lat坐标如: location=116.305145,39.982368;城市天气预报中间"|"分隔,location=116.305145,39.982368| 122.305145,36.982368|….(必填项)</p>
     * @param output <p>输出的数据格式，默认为xml格式，当output设置为’json’时，输出的为json格式的数据(非必填项)<p>
     * @param ak 开发者密钥(必填项)
     * @return  ResponseData
     */  
    public static ResponseData callBaiduWeatherByResponseData(String httpUrl,String location,String output,String ak) {  
    	ResponseData rd=null;
    	if(StringUtil.isEmpty(httpUrl,location,ak)){
    		logger.error("=================>缺少要必要参数.");
    		return rd;
    	}
    	StringBuffer sb=new StringBuffer();
    	sb.append(httpUrl).append("?").append("location=").append(location).append("&output=").append(output).append("&ak=").append(ak);
    	String html=httpRequest(sb.toString());
    	try {
    		if("xml".equals(output)){
    			rd=parseXml(html);
    		}else if("json".equals(output)){
    			rd=parseJson(html);
    		}else{
    			logger.error("==================>指定输出格式有误.");
    			return rd;
    		}
		} catch (Exception e) {
			logger.error("处理异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
		}
        return rd;  
    }
    
    /**
     * TODO
     * <p>json到JavaBean的转换（百度天气预报api接口返回的json串）</p>
     * @param json 待转换的json串
     * @return ResponseData 
     * */
    private static ResponseData parseJson(String json){
    	ResponseData responseData=new ResponseData();
    	if(StringUtil.isEmpty(json)){
    		logger.error("待解释的josn串不能为空.");
    		return responseData;
    	}
    	return responseData;
    }
    
    /**
     * <p>xml到JavaBean的转换（百度天气预报api接口返回的xml文档）</p>
     * @param xml 待转换的xml 文档树
     * @return ResponseData 
     * */
    private static ResponseData parseXml(String xml) {
		ResponseData reponseData=new ResponseData();
		if(StringUtil.isEmpty(xml)){
			logger.error("=============>待转换的xml文档树不能为null.");
			return reponseData;
		}
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElement = doc.getRootElement(); // 获取根节点
			Iterator<?> iter = rootElement.elementIterator("results"); //天气预告信息
			// 获取根节点下的子节点results
			String status = rootElement.elementText("status"); // 获取状态，如果等于success,表示有数据
			if (!status.equals("success"))
				return reponseData; 
			String date = rootElement.elementText("date"); // 获取根节点下的，当天日期
			String error=rootElement.elementText("error");
			reponseData.setStatus(status);
			reponseData.setDate(date);
			reponseData.setError(error);
			City city=new City();
			LinkedList<Index> indexs=new LinkedList<Index>();
			LinkedList<WeatherData> weatherDatas=new LinkedList<WeatherData>();
			//天气
			List<?> dateList = null; // 用来存放日期
			List<?> dayPictureUrlList = null; // 用来存放白天图片路径信息
			List<?> weatherList = null;//天气状况
			List<?> windList = null;//风力
			List<?> temperatureList = null;//温度
			List<?> nightPictureUrlList=null;// 用来存放夜间图片路径信息
			//指数
			List<?> titleList = null; // 用来存放标题 
			List<?> zsList = null; // 用来存放指数
			List<?> tiptList = null;//提示
			List<?> desList = null;//描述
			while (iter.hasNext()) {
				Element cityElement = (Element) iter.next();
				String currentCity=cityElement.elementText("currentCity");
				String pm25=cityElement.elementText("pm25");
				city.setCurrentCity(currentCity);
				city.setPm25(pm25);
				Iterator<?> iters = cityElement.elementIterator("weather_data"); 
				// 遍历results节点下的weather_data节点
				while (iters.hasNext()) {
					Element itemEle = (Element) iters.next();
					dateList = itemEle.elements("date");
					dayPictureUrlList = itemEle.elements("dayPictureUrl");
					nightPictureUrlList = itemEle.elements("nightPictureUrl");
					weatherList = itemEle.elements("weather");
					windList = itemEle.elements("wind");
					temperatureList = itemEle.elements("temperature");
				}
				for (int i = 0; i < dateList.size(); i++) { 
					WeatherData weatherData=new WeatherData();
					Element dateElement = (Element) dateList.get(i); 
					Element dayPictureUrElement = (Element) dayPictureUrlList.get(i);
					Element weatherElement = (Element) weatherList.get(i);
					Element windElement = (Element) windList.get(i);
					Element temperatureEletem = (Element) temperatureList.get(i);
					Element nightPictureElement=(Element) nightPictureUrlList.get(i);
					String _date=dateElement.getText();
					String _dayPictureUr=dayPictureUrElement.getText();
					String _weather=weatherElement.getText();
					String _wind=windElement.getText();
					String _temperature=temperatureEletem.getText();
					String _nightPicture=nightPictureElement.getText();
					weatherData.setDate(_date);
					weatherData.setDayPictureUrl(_dayPictureUr);
					weatherData.setNightPictureUrl(_nightPicture);
					weatherData.setWind(_wind);
					weatherData.setTemperature(_temperature);
					weatherData.setWeather(_weather);
					weatherDatas.add(weatherData);
				}
				//指数
				Iterator<?> itersIndex = cityElement.elementIterator("index"); 
				while (itersIndex.hasNext()) {
					Element itemEle = (Element) itersIndex.next();
					titleList = itemEle.elements("title");
					zsList = itemEle.elements("zs");
					tiptList = itemEle.elements("tipt");
					desList = itemEle.elements("des");
				}
				for (int i = 0; i < titleList.size(); i++) {
					Index index=new Index();
					Element titleDate = (Element) titleList.get(i);
					Element zsData = (Element) zsList.get(i);
					Element tiptData = (Element) tiptList.get(i);
					Element desData = (Element) desList.get(i);
					String title=titleDate.getText();
					String zs=zsData.getText();
					String tipt=tiptData.getText();
					String des=desData.getText();
					index.setTitle(title);
					index.setZs(zs);
					index.setTipt(tipt);
					index.setDes(des);
					indexs.add(index);
				}
			}
			city.setIndexs(indexs);
			city.setWealtherDates(weatherDatas);
			reponseData.setCity(city);
		} catch (DocumentException e) {
			logger.error("处理异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
		} catch (Exception e) {
			logger.error("处理异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
		}
		return reponseData;
	}
    
    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {  
    	String httpUrl="http://api.map.baidu.com/telematics/v3/weather";
    	String location="杭州";
    	String output="xml";//json or xml
    	String ak="cYHchCiGwMbW9HdVWTFfnRssl0eGdi0P";
    	String resultData=callBaiduWeatherByResponse(httpUrl, location, output, ak);
    	System.out.println(resultData);
    }  
    
   /** 
    * <p>获取天气预报接口返回结果</p>
    * @param httpUrl 待请求的url (必填项)
    * @param location <p>输入城市名或经纬度，城市名称如:北京，经纬度格式为lng,lat坐标如: location=116.305145,39.982368;城市天气预报中间"|"分隔,location=116.305145,39.982368| 122.305145,36.982368|….(必填项)</p>
    * @param output <p>输出的数据格式，默认为xml格式，当output设置为’json’时，输出的为json格式的数据(非必填项)<p>
    * @param ak 开发者密钥(必填项)
    * @return  String
    */  
    public static String callBaiduWeatherByResponse(String httpUrl,String location,String output,String ak){
    	ResponseData resData=callBaiduWeatherByResponseData(httpUrl, location, output, ak);
    	if(null==resData){
    		return "暂无数据.";
    	}
    	City city=resData.getCity();
    	String _city=city.getCurrentCity();//当前城市
    	String _pm25=city.getPm25();
    	List<Index> indexs=city.getIndexs();//指数
    	List<WeatherData> weatherDates=city.getWealtherDates();//天气情况;
    	StringBuffer sb=new StringBuffer();
    	sb.append("<h2>").append(_city).append("，指数：").append(_pm25).append("</h2>");
    	List<String> styleList=initStyle();
    	for(int i=0;i<weatherDates.size();i++){
    		WeatherData weatherData=weatherDates.get(i);
    		Index index=indexs.get(i);
    		sb.append("<div  ").append(styleList.get(i)).append(">");
    		sb.append("<p>").append(weatherData.getDate()+"，").append(weatherData.getWeather()+"，").append(weatherData.getWind()).append("</p>");
    		sb.append("<p>").append(index.getTitle()+"：").append(index.getZs()).append("</p>").append(index.getTipt()+"：").append(index.getDes());
    		sb.append("</div>");
    		sb.append("<p></p>");
    	}
    	sb.append("<p></p>");
    	return sb.toString();
    }
    
    private static LinkedList<String> initStyle(){
    	LinkedList<String> classList=new LinkedList<String>();
    	StringBuffer sb=new StringBuffer();
    	sb.append("style=").append("'font-size:18px;font-weight: bold;color: #ff0000;'");
    	classList.add(sb.toString());
    	
    	sb.setLength(0);
    	sb.append("style=").append("'font-size:17px;color: #00BCD4;'");
    	classList.add(sb.toString());
    	
    	sb.setLength(0);
    	sb.append("style=").append("'font-size:16px;color: #4CAF50;'");
    	classList.add(sb.toString());
    	
    	sb.setLength(0);
    	sb.append("style=").append("'font-size:15px;color: #FFC107;'");
    	classList.add(sb.toString());
    	return classList;
    }
    
}