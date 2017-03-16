package qing.yun.hui.common.utils.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import qing.yun.hui.common.constants.APIConstant;
import qing.yun.hui.common.constants.Constant;
import qing.yun.hui.common.struct.juhe.JuheConstant;
import qing.yun.hui.common.struct.juhe.JuheEnum;
import qing.yun.hui.common.struct.juhe.JuheResponse;
import qing.yun.hui.common.struct.juhe.bus.busline.BuslineData;
import qing.yun.hui.common.struct.juhe.bus.busline.BuslineResponse;
import qing.yun.hui.common.struct.juhe.bus.buslong.BusLongData;
import qing.yun.hui.common.struct.juhe.bus.buslong.BusLongResponse;
import qing.yun.hui.common.struct.juhe.film.ticket.H5FilmTicketResponse;
import qing.yun.hui.common.struct.juhe.idcard.IdCardResponse;
import qing.yun.hui.common.struct.juhe.joke.JokeData;
import qing.yun.hui.common.struct.juhe.joke.JokeResponse;
import qing.yun.hui.common.struct.juhe.news.NewsTopData;
import qing.yun.hui.common.struct.juhe.news.NewsTopResponse;
import qing.yun.hui.common.struct.juhe.phone.mobile.MobileResponse;
import qing.yun.hui.common.struct.juhe.phone.telephone.CallerIDTelephoneResponse;
import qing.yun.hui.common.struct.juhe.phone.telephone.TelephoneData;
import qing.yun.hui.common.struct.juhe.robot.RobotResponse;
import qing.yun.hui.common.struct.juhe.video.searching.SearchingData;
import qing.yun.hui.common.struct.juhe.video.searching.VideoSearchingResponse;
import qing.yun.hui.common.struct.juhe.wechat.choiceness.PageData;
import qing.yun.hui.common.struct.juhe.wechat.choiceness.WechatChoicenessData;
import qing.yun.hui.common.struct.juhe.wechat.choiceness.WechatChoicenessResponse;
import qing.yun.hui.common.utils.DateUtil;
import qing.yun.hui.common.utils.HttpUtil;
import qing.yun.hui.common.utils.StringUtil;

import com.alibaba.fastjson.JSONObject;


/***
 *  <p>使用方式:直接通过ApiUtil.callXX即可.</p>
 ** @category 该API是用来获取一些公共的接口信息，比如获取北京时间，天气预报，等等信息...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月11日上午10:17:33
 **/
public class ApiUtil {
	
	 private static Logger logger=LoggerFactory.getLogger(ApiUtil.class);
	 
	 //TODO 股票数据，NBA赛事，足球联赛 ，这三个因为返回的json串有点杂乱无章，就没有做映射了...
	
	 public static void main(String[]args){
		 //身份证信息查询
		 IdCardResponse card= ApiUtil.callIdCardResponse("431102198811046375", "json", "get");
		 System.out.println(JSONObject.toJSONString(card));
		 //手机固话来电显示
		 CallerIDTelephoneResponse telephone= ApiUtil.callCallerIDTelephoneResponse("07466551538", "json", "get");
		 System.out.println(JSONObject.toJSONString(telephone));
		 //手机号归属地
		 MobileResponse mobile=ApiUtil.callMobileResponse("18665300640", "json", "get");
		 System.out.println(JSONObject.toJSONString(mobile));
		 //全国公交及路径规划查询
		 BuslineResponse  bsline= ApiUtil.callBuslineResponse("杭州", "156",  "json", "get");
		 System.out.println(JSONObject.toJSONString(bsline));
		 //长途汽车信息
		 BusLongResponse buslong= callBusLongResponse("永州",  "json", "get");
		 System.out.println(JSONObject.toJSONString(buslong));
		 //新闻头条
		 NewsTopResponse newsTop= callNewsTopResponse("top", "json", "get");
		 System.out.println(JSONObject.toJSONString(newsTop));
		 //影视影讯检索
		 VideoSearchingResponse videoSearching= callVideoSearchingResponse("十八罗汉", "json", "get");
		 System.out.println(JSONObject.toJSONString(videoSearching));
		 //
	 }
	 
	 /**
	  * <p>足球联赛</p>
	  * @param httpUrl  待请求的url  【y】
	  * @param key  	申请的key    【y】
	  * @param league	联赛名称(提供英超，西甲，德甲，意甲，法甲，中超 等赛程)【y】
	  * @param dtype  返回的数据的格式，json或xml，默认为json 【N】
	  * @param method 请求方式(get或post) ,默认为get【N】
	  * @return 足球联赛
	  * */
	 public static String getFootballLeague(String httpUrl,String key,String league,String dtype,String method){
		 if(StringUtil.isEmpty(httpUrl,key,league)){
			 logger.error("=============>缺少必要参数，httpUrl，key，league必填项。");
	    	 return null;
		 }
		 Map<String,Object> params=initMap(key, dtype);
    	 params.put("league", league);
    	 try {
    		return HttpUtil.sendRequest(httpUrl, params,method);
		 } catch (Exception e) {
			logger.error("处理异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
		 }
		 return null;
	 }
	 
	 /**
	  * <p>股票数据（包含了沪深股市，香港股市，美国股市数据每个股市对应的接口中不一样，但key是一样的）</p>
	  * @param stockShort 枚举类型{股市类型，如:沪深股市，香港股市，美国股市.}【y】
	  * @param code 	股票编号，上海股市以sh开头，深圳股市以sz开头如：sh601009（type为0或者1时gid不是必须）【y】
	  * @param type 0代表上证指数，1代表深证指数 【N】
	  * @param dtype  返回的数据的格式，json或xml，默认为json 【N】
	  * @param method 请求方式(get或post) ,默认为get【N】
	  * @return 股票数据
	  * */
	 public static String getStock(JuheEnum.StockShort stockShort,String code,Integer type,String dtype,String method){
		return intStock(JuheEnum.Stock.HTTP_URL.getCode(), JuheEnum.Stock.APP_KEY.getCode(), stockShort, code, type, dtype, method);
	 }
	 
	 /**
	  * <p>NBA赛事</p>
	  * @param httpUrl 待请求的url 【y】
	  * @param key  	申请的key 【y】
	  * @param dtype  返回的数据的格式，json或xml，默认为json 【N】
	  * @param method 请求方式(get或post) ,默认为get【N】
	  * @return NBA赛事
	  * */
	 public static String getNBACompetition(String httpUrl,String key,String dtype,String method){
		 if(StringUtil.isEmpty(httpUrl,key)){
			 logger.error("=============>缺少必要参数，httpUrl，key必填项。");
	    	 return null;
		 }
		 Map<String,Object> params=initMap(key, dtype);
    	 try {
    		return HttpUtil.sendRequest(httpUrl, params,method);
		 } catch (Exception e) {
			logger.error("处理异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
		 }
		 return null;
	 }
	 
	 /**
	  * <p>H5在线电影票</p>
	  * @param httpUrl  待请求的url   【y】
	  * @param key  	申请的key    【y】
	  * @param dtype  返回的数据的格式，json或xml，默认为json 【N】
	  * @param method 请求方式(get或post) ,默认为get【N】
	  * @return H5在线电影票
	  * */
	 private static String getH5OnLineFilmTicket(String httpUrl,String key,String dtype,String method){
		 if(StringUtil.isEmpty(httpUrl,key)){
			 logger.error("=============>缺少必要参数，httpUrl，key必填项。");
	    	 return null;
		 }
		 Map<String,Object> params=initMap(key, dtype);
    	 try {
    		return HttpUtil.sendRequest(httpUrl, params,method);
		 } catch (Exception e) {
			logger.error("处理异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
		 }
		 return null;
	 }
	 
	 /**
	  * <p>H5在线电影票</p>
	  * @param dtype  返回的数据的格式，json或xml，默认为json 【N】
	  * @param method 请求方式(get或post) ,默认为get【N】
	  * @return H5在线电影票
	  * */
	 public static H5FilmTicketResponse callH5FilmTicketResponse(String dtype,String method){
		 H5FilmTicketResponse h5FilmResponse=null;
		 String json=getH5OnLineFilmTicket(JuheEnum.H5FileTicket.HTTP_URL.getCode(), JuheEnum.H5FileTicket.APP_KEY.getCode(), dtype, method);
		 if(StringUtil.isEmpty(json)) return h5FilmResponse;
		 if(Constant.XML.equalsIgnoreCase(dtype)){
			//TODO 
			return h5FilmResponse;
		 }
		 JuheResponse juheResponse=JSONObject.parseObject(json, JuheResponse.class);
		 String result=juheResponse.getResult();
		 if(StringUtil.isEmpty(result)) return h5FilmResponse;
		 h5FilmResponse=JSONObject.parseObject(result, H5FilmTicketResponse.class);
		 BeanUtils.copyProperties(juheResponse,h5FilmResponse);
		 return h5FilmResponse;
	 }
	 
	 /**
	  * <p>手机固话来电显示</p>
	  * @param httpUrl  待请求的url   【y】
	  * @param key  	申请的key    【y】
	  * @param tel		要查询的号码，如:051262519280 【y】
	  * @param dtype  返回的数据的格式，json或xml，默认为json 【N】
	  * @param method 请求方式(get或post) ,默认为get【N】
	  * @return 手机固话来电显示
	  * */
	 private static String getCallerIDTelephone(String httpUrl,String key,String tel,String dtype,String method){
		 if(StringUtil.isEmpty(httpUrl,key,tel)){
			 logger.error("=============>缺少必要参数，httpUrl，key，tel必填项。");
	    	 return null;
		 }
		 Map<String,Object> params=initMap(key, dtype);
    	 params.put("tel", tel);
    	 try {
    		return HttpUtil.sendRequest(httpUrl, params,method);
		 } catch (Exception e) {
			logger.error("处理异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
		 }
		 return null;
	 }
	 
	 /**
	  * <p>手机固话来电显示</p>
	  * @param tel		要查询的号码，如:051262519280 【y】
	  * @param dtype  返回的数据的格式，json或xml，默认为json 【N】
	  * @param method 请求方式(get或post) ,默认为get【N】
	  * @return 手机固话来电显示
	  * */
	 public static CallerIDTelephoneResponse callCallerIDTelephoneResponse(String tel,String dtype,String method){
		 CallerIDTelephoneResponse response=null;
		 String idcardData=getCallerIDTelephone(JuheEnum.CallerIDTelephone.HTTP_URL.getCode(), JuheEnum.CallerIDTelephone.APP_KEY.getCode(), tel, dtype, method);
    	 if(StringUtil.isEmpty(idcardData)) return response;
    	 if(Constant.XML.equalsIgnoreCase(dtype)){
    		//TODO 
    		return response;
    	 }
    	 JuheResponse juheResponse=JSONObject.parseObject(idcardData, JuheResponse.class);
    	 String result=juheResponse.getResult();
    	 if(StringUtil.isEmpty(result)) return response;
    	 response=JSONObject.parseObject(result, CallerIDTelephoneResponse.class);
    	 JSONObject obj= JSONObject.parseObject(result);
    	 String hy=obj.getString("hy");
    	 if(!StringUtil.isEmpty(hy)){
    		 TelephoneData telephoneData=JSONObject.parseObject(hy, TelephoneData.class);
    		 response.setHy(telephoneData);
    	 }
    	 BeanUtils.copyProperties(juheResponse,response);
    	 return response;
		 
	 }
	 
	 /**
	  * <p>影视影讯检索</p>
	  * @param httpUrl  待请求的url  【y】
	  * @param key  	申请的key    【y】
	  * @param q		影视搜索名称       【y】
	  * @param dtype  返回的数据的格式，json或xml，默认为json 【N】
	  * @param method 请求方式(get或post) ,默认为get【N】
	  * @return 影视影讯检索
	  * */
	 private static String getVideoSearching(String httpUrl,String key,String q,String dtype,String method){
		 if(StringUtil.isEmpty(httpUrl,key,q)){
			 logger.error("=============>缺少必要参数，httpUrl，key必填项。");
	    	 return null;
		 }
		 Map<String,Object> params=initMap(key, dtype);
    	 params.put("q", q);
    	 try {
    		return HttpUtil.sendRequest(httpUrl, params,method);
		 } catch (Exception e) {
			logger.error("处理异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
		 }
		 return null;
	 }
	 
	 /**
	  * <p>影视影讯检索</p>
	  * @param q		影视搜索名称       【y】
	  * @param dtype  返回的数据的格式，json或xml，默认为json 【N】
	  * @param method 请求方式(get或post) ,默认为get【N】
	  * @return 影视影讯检索
	  * */
	 public static VideoSearchingResponse callVideoSearchingResponse(String q,String dtype,String method){
		 VideoSearchingResponse response=null;
		 String json=getVideoSearching(JuheEnum.VideoSearching.HTTP_URL.getCode(), JuheEnum.VideoSearching.APP_KEY.getCode(),q,dtype,method);
		 if(StringUtil.isEmpty(json)) return response;
		 if(Constant.XML.equalsIgnoreCase(dtype)){
			//TODO 
			return response;
		 }
		 JuheResponse juheResponse=JSONObject.parseObject(json, JuheResponse.class);
		 String result=juheResponse.getResult();
		 if(StringUtil.isEmpty(result)) return response;
		 SearchingData searchingData=JSONObject.parseObject(result, SearchingData.class);
		 if(null==searchingData) return response;
		 if(null==response)response=new VideoSearchingResponse();
		 BeanUtils.copyProperties(juheResponse,response);
		 response.setSearchingData(searchingData);
		 return response;
	 }
	 
	 /**
	  * <p>微信精选</p>
	  * @param httpUrl 待请求的url 【y】
	  * @param key  申请的key 【y】
	  * @param pno 	当前页数，默认1 【N】
	  * @param ps 每页返回条数，最大50，默认20 【N】
	  * @param dtype  返回的数据的格式，json或xml，默认为json 【N】
	  * @param method 请求方式(get或post) ,默认为get【N】
	  * @return 微信精选
	  * */
	 private static String getWechatChoiceness(String httpUrl,String key,Integer pno,Integer ps,String dtype,String method){
		 if(StringUtil.isEmpty(httpUrl,key)){
			 logger.error("=============>缺少必要参数，httpUrl，key必填项。");
	    	 return null;
		 }
		 Map<String,Object> params=initMap(key, dtype);
    	 params.put("pno", pno);
    	 params.put("ps", ps);
    	 try {
    		return HttpUtil.sendRequest(httpUrl, params,method);
		 } catch (Exception e) {
			logger.error("处理异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
		 }
		 return null;
	 }
	 
	 /**
	  * <p>微信精选</p>
	  * @param pno 	当前页数，默认1 【N】
	  * @param ps 每页返回条数，最大50，默认20 【N】
	  * @param dtype  返回的数据的格式，json或xml，默认为json 【N】
	  * @param method 请求方式(get或post) ,默认为get【N】
	  * @return 微信精选
	  * */
	 public static WechatChoicenessResponse callWechatChoicenessResponse(Integer pno,Integer ps,String dtype,String method){
		 WechatChoicenessResponse response=null;
		 String json=getWechatChoiceness(JuheEnum.WechatChoiceness.HTTP_URL.getCode(), JuheEnum.WechatChoiceness.APP_KEY.getCode(),pno,ps,dtype,method);
		 if(StringUtil.isEmpty(json)) return response;
		 if(Constant.XML.equalsIgnoreCase(dtype)){
			//TODO 
			return response;
		 }
		 JuheResponse juheResponse=JSONObject.parseObject(json, JuheResponse.class);
		 String result=juheResponse.getResult();
		 if(StringUtil.isEmpty(result)) return response;
		 JSONObject obj=JSONObject.parseObject(result);
		 String list=obj.getString("list");
		 List<WechatChoicenessData> wechatChoicenessDatas=JSONObject.parseArray(list, WechatChoicenessData.class);
		 response=new WechatChoicenessResponse();
		 response.setList(wechatChoicenessDatas);
		 BeanUtils.copyProperties(juheResponse,response);
		 PageData pageData=JSONObject.parseObject(result, PageData.class);
		 response.setPageData(pageData);
		 return response;
	 }
	 
	 /**
	  * <p>身份证查询</p>
	  * @param httpUrl 待请求的url 【y】
	  * @param key  申请的key 【y】
	  * @param cardno 身份证号码 【y】
	  * @param dtype  返回的数据的格式，json或xml，默认为json 【N】
	  * @param method 请求方式(get或post) ,默认为get【N】
	  * @return 身份证查询
	  * */
	 private static String getIdcard(String httpUrl,String key,String cardno,String dtype,String method){
		 if(StringUtil.isEmpty(httpUrl,key,cardno)){
			 logger.error("=============>缺少必要参数，httpUrl，key，cardno必填项。");
	    	 return null;
		 }
		 Map<String,Object> params=initMap(key, dtype);
    	 params.put("cardno", cardno);
    	 try {
    		return HttpUtil.sendRequest(httpUrl, params,method);
		 } catch (Exception e) {
			logger.error("处理异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
		 }
		 return null;
	 }
	 
	 /**
	  * <p>身份证查询</p>
	  * @param cardno 身份证号码 【y】
	  * @param dtype  返回的数据的格式，json或xml，默认为json 【N】
	  * @param method 请求方式(get或post) ,默认为get【N】
	  * @return 身份证查询
	  * */
	 public static IdCardResponse callIdCardResponse(String cardno,String dtype,String method){
		 IdCardResponse idcardResponse=null;
    	 String idcardData=getIdcard(JuheEnum.Idcard.HTTP_URL.getCode(), JuheEnum.Idcard.APP_KEY.getCode(), cardno, dtype, method);
    	 if(StringUtil.isEmpty(idcardData)) return idcardResponse;
    	 if(Constant.XML.equalsIgnoreCase(dtype)){
    		//TODO 
    		return idcardResponse;
    	 }
    	 JuheResponse juheResponse=JSONObject.parseObject(idcardData, JuheResponse.class);
    	 JSONObject jsonObj= JSONObject.parseObject(idcardData);
    	 String resultcode= jsonObj.getString("resultcode");
    	 if(!JuheConstant.resultcode.equals(resultcode)) return idcardResponse;
    	 String result=juheResponse.getResult();
    	 idcardResponse=JSONObject.parseObject(result, IdCardResponse.class);
    	 BeanUtils.copyProperties(juheResponse,idcardResponse);
    	 return idcardResponse;
	 }
	 
	 /**
	  * <p>笑话大全</p>
	  * @param httpUrl 待请求的url 【y】
	  * @param key  申请的key 【y】
	  * @param sort 类型，desc:指定时间之前发布的，asc:指定时间之后发布的 【y】
	  * @param time 时间戳（10位），如：1418816972 【y】
	  * @param page  当前页数,默认1  【N】
	  * @param pagesize 每次返回条数,最大20,默认1 【N】
	  * @param dtype  返回的数据的格式，json或xml，默认为json 【N】
	  * @param method 请求方式(get或post) ,默认为get【N】
	  * @return 笑话大全
	  * */
	 private static String getJokeDaquan(String httpUrl,String key,String sort,String time,int page,int pagesize,String dtype,String method){
		 if(StringUtil.isEmpty(httpUrl,key,sort,time)){
			 logger.error("=============>缺少必要参数，httpUrl，key，sort，time必填项。");
	    	 return null;
		 }
		 Map<String,Object> params=initMap(key, dtype);
    	 params.put("sort", sort);
    	 params.put("time", time);
    	 params.put("page", page);
    	 params.put("pagesize", pagesize);
    	 try {
    		return HttpUtil.sendRequest(httpUrl, params,method);
		 } catch (Exception e) {
			logger.error("处理异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
		 }
		 return null;
	 }
	 
	 /**
	  * <p>笑话大全</p>
	  * @param sort 类型，desc:指定时间之前发布的，asc:指定时间之后发布的 【y】
	  * @param time 时间戳（10位），如：1418816972 【y】
	  * @param page  当前页数,默认1  【N】
	  * @param pagesize 每次返回条数,最大20,默认1 【N】
	  * @param dtype  返回的数据的格式，json或xml，默认为json 【N】
	  * @param method 请求方式(get或post) ,默认为get【N】
	  * @return 笑话大全
	  * */
	 public static JokeResponse callJokeDaquanResponse(String sort,String time,int page,int pagesize,String dtype,String method){
		 JokeResponse jokeResponse=null;
    	 String json=getJokeDaquan(JuheEnum.JokeDaquan.HTTP_URL.getCode(), JuheEnum.JokeDaquan.APP_KEY.getCode(), sort,time,page,pagesize, dtype, method);
    	 if(StringUtil.isEmpty(json)) return jokeResponse;
    	 if(Constant.XML.equalsIgnoreCase(dtype)){
    		//TODO 
    		return jokeResponse;
    	 }
    	 JuheResponse juheResponse=JSONObject.parseObject(json, JuheResponse.class);
    	 if(StringUtil.isEmpty(juheResponse.getResult())) return jokeResponse;
    	 String data=JSONObject.parseObject(juheResponse.getResult()).getString("data");
    	 List<JokeData> jokeDatas=JSONObject.parseArray(data, JokeData.class);
    	 jokeResponse=new JokeResponse();
    	 jokeResponse.setData(jokeDatas);
    	 BeanUtils.copyProperties(juheResponse,jokeResponse);
    	 return jokeResponse;
		 
	 }
	 
	 /**
	  * <p>全国公交及路径规划查询</p>
	  * @param httpUrl 待请求的url 【y】
	  * @param key  申请的key 【y】
	  * @param city 城市名称(如：苏州)或者城市代码（如：0512） 【y】
	  * @param bus  公交线路 【y】
	  * @param dtype  返回的数据的格式，json或xml，默认为json 【N】
	  * @param method 请求方式(get或post) ,默认为get【N】
	  * @return 机器人返回的数据
	  * */
	 private static String getBusline(String httpUrl,String key,String city,String bus,String dtype,String method){
		 if(StringUtil.isEmpty(httpUrl,key,city,bus)){
			 logger.error("=============>缺少必要参数，httpUrl，key，city，bus必填项。");
	    	 return null;
		 }
		 Map<String,Object> params=initMap(key, dtype);
    	 params.put("city", city);
    	 params.put("bus", bus);
    	 try {
    		return HttpUtil.sendRequest(httpUrl, params,method);
		 } catch (Exception e) {
			logger.error("处理异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
		 }
		 return null;
	 }
	 
	 /**
	  * <p>全国公交及路径规划查询</p>
	  * @param city 城市名称(如：苏州)或者城市代码（如：0512） 【y】
	  * @param bus  公交线路 【y】
	  * @param dtype  返回的数据的格式，json或xml，默认为json 【N】
	  * @param method 请求方式(get或post) ,默认为get【N】
	  * @return 机器人返回的数据
	  * */
	 public static BuslineResponse callBuslineResponse(String city,String bus,String dtype,String method){
		 BuslineResponse response=null;
    	 String json=getBusline(JuheEnum.Busline.HTTP_URL.getCode(), JuheEnum.Busline.APP_KEY.getCode(), city,bus, dtype, method);
    	 if(StringUtil.isEmpty(json)) return response;
    	 if(Constant.XML.equalsIgnoreCase(dtype)){
    		//TODO 
    		return response;
    	 }
    	 JuheResponse juheResponse=JSONObject.parseObject(json, JuheResponse.class);
    	 String result=juheResponse.getResult();
    	 if(StringUtil.isEmpty(result)) return response;
    	 List<BuslineData> buslineDatas=JSONObject.parseArray(result, BuslineData.class);
    	 response=new BuslineResponse();
    	 response.setBuslineDatas(buslineDatas);
    	 BeanUtils.copyProperties(juheResponse,response);
    	 return response;
	 }
	 
	 /**
	  * <p>问答机器人</p> 
	  * @param httpUrl 待请求的url 【y】
	  * @param key 申请的key 【y】
	  * @param info 要发送给机器人的内容，不要超过30个字符 【y】
	  * @param dtype 返回的数据的格式，json或xml，默认为json 【N】
	  * @param method 请求方式(get或post),默认为get【N】
	  * @return 机器人返回的数据
	  * */
	 private static String getRobotData(String httpUrl,String key,String info,String dtype,String method){
		 if(StringUtil.isEmpty(httpUrl,key,info)){
			 logger.error("=============>缺少必要参数，httpUrl，key，info必填项。");
	    	 return null;
		 }
		 Map<String,Object> params=initMap(key, dtype);
    	 params.put("info", info);
    	 try {
    		return HttpUtil.sendRequest(httpUrl, params,method);
		 } catch (Exception e) {
			logger.error("处理异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
		 }
		 return null;
	 }
	 
	 /**
	  * <p>问答机器人</p> 
	  * @param info 要发送给机器人的内容，不要超过30个字符 【y】
	  * @param dtype 返回的数据的格式，json或xml，默认为json 【N】
	  * @param method 请求方式(get或post),默认为get【N】
	  * @return 机器人返回的数据
	  * */
	 public static RobotResponse callRobotResponse(String info,String dtype,String method){
		 RobotResponse robotResponse=null;
    	 String json=getRobotData(JuheEnum.Robot.HTTP_URL.getCode(), JuheEnum.Robot.APP_KEY.getCode(), info, dtype, method);
    	 if(StringUtil.isEmpty(json)) return robotResponse;
    	 if(Constant.XML.equalsIgnoreCase(dtype)){
    		//TODO 
    		return robotResponse;
    	 }
    	 JuheResponse juheResponse=JSONObject.parseObject(json, JuheResponse.class);
    	 String result=juheResponse.getResult();
    	 if(StringUtil.isEmpty(result)) return robotResponse;
    	 robotResponse=JSONObject.parseObject(result, RobotResponse.class);
    	 BeanUtils.copyProperties(juheResponse,robotResponse);
    	 return robotResponse;
	 }
	 
	 /**
	  * <p>长途汽车信息</p>
	  * @param httpUrl 待请求的url 【y】
	  * @param key 申请的key 【y】
	  * @param station 	城市名称，如:北京 【y】
	  * @param dtype 返回的数据的格式，json或xml，默认为json 【N】
	  * @param method 请求方式(get或post),默认为get【N】
	  * @return 汽车站相关信息
	  * */
	 private static String getBusLong(String httpUrl,String key,String station,String dtype,String method){
		 if(StringUtil.isEmpty(httpUrl,key,station)){
			 logger.error("=============>缺少必要参数，httpUrl，station必填项。");
	    	 return null;
		 }
		 Map<String,Object> params=initMap(key, dtype);
    	 params.put("station", station);
    	 try {
    		return HttpUtil.sendRequest(httpUrl, params,method);
		 } catch (Exception e) {
			logger.error("处理异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
		 }
		 return null;
	 }
	 

	 /**
	  * <p>长途汽车信息</p>
	  * @param station 	城市名称，如:北京 【y】
	  * @param dtype 返回的数据的格式，json或xml，默认为json 【N】
	  * @param method 请求方式(get或post),默认为get【N】
	  * @return 汽车站相关信息
	  * */
	 public static BusLongResponse callBusLongResponse(String station,String dtype,String method){
		 BusLongResponse busLongResponse=null;
    	 String json=getBusLong(JuheEnum.BusLong.HTTP_URL.getCode(), JuheEnum.BusLong.APP_KEY.getCode(), station, dtype, method);
    	 if(StringUtil.isEmpty(json)) return busLongResponse;
    	 if(Constant.XML.equalsIgnoreCase(dtype)){
    		//TODO 
    		return busLongResponse;
    	 }
    	 JuheResponse juheResponse=JSONObject.parseObject(json, JuheResponse.class);
    	 String result=juheResponse.getResult();
    	 if(StringUtil.isEmpty(result)) return busLongResponse;
    	 JSONObject jsonObject= JSONObject.parseObject(result);
    	 String list=jsonObject.getString("list");
    	 if(StringUtil.isEmpty(list)) return busLongResponse;
    	 String title=jsonObject.getString("title");
    	 busLongResponse=new BusLongResponse();
    	 List<BusLongData> busLongDatas=JSONObject.parseArray(list, BusLongData.class);
    	 busLongResponse.setList(busLongDatas);
    	 busLongResponse.setTitle(title);
    	 BeanUtils.copyProperties(juheResponse,busLongResponse);
    	 return busLongResponse;
	 }
	
	 /**
	  * <p>新闻头条</p>
	  * @param httpUrl 待请求的url 【y】
	  * @param key 申请的key 【y】
	  * @param type <p>类型,top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)，默认头条. 【N】</p>
	  * @param dtype 返回的数据的格式，json或xml，默认为json 【N】
	  * @param method  请求方式(get或post),默认为get【N】
	  * @return 新闻头条
	  * */
	 private static String getNewsTop(String httpUrl,String key,String type,String dtype,String method){
		 if(StringUtil.isEmpty(httpUrl,key)){
	    		logger.error("=============>缺少必要参数，httpUrl，key必填项。");
	    		return null;
	     }
		 Map<String,Object> params=initMap(key, dtype);
    	 params.put("type", type);
    	 try {
    		return HttpUtil.sendRequest(httpUrl, params,method);
		 } catch (Exception e) {
			logger.error("处理异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
		 }
		 return null;
	 }
	 
	 /**
	  * <p>新闻头条</p>
	  * @param type <p>类型,top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)，默认头条. 【N】</p>
	  * @param dtype 返回的数据的格式，json或xml，默认为json 【N】
	  * @param method  请求方式(get或post),默认为get【N】
	  * @return 新闻头条
	  * */
	 public static NewsTopResponse callNewsTopResponse(String type,String dtype,String method){
		 NewsTopResponse newsTopResponse=null;
    	 String json=getNewsTop(JuheEnum.NewsTop.HTTP_URL.getCode(), JuheEnum.NewsTop.APP_KEY.getCode(), type, dtype, method);
    	 if(StringUtil.isEmpty(json)) return newsTopResponse;
    	 if(Constant.XML.equalsIgnoreCase(dtype)){
    		//TODO 
    		return newsTopResponse;
    	 }
    	 JuheResponse juheResponse=JSONObject.parseObject(json, JuheResponse.class);
    	 String result=juheResponse.getResult();
    	 if(StringUtil.isEmpty(result)) return newsTopResponse;
    	 JSONObject jsonObject= JSONObject.parseObject(result);
    	 String data=jsonObject.getString("data");
    	 if(StringUtil.isEmpty(data)) return newsTopResponse;
    	 newsTopResponse=new NewsTopResponse();
    	 List<NewsTopData> newsTopDatas=JSONObject.parseArray(data, NewsTopData.class);
    	 newsTopResponse.setData(newsTopDatas);
    	 BeanUtils.copyProperties(juheResponse,newsTopResponse);
    	 return newsTopResponse;
	 }
	
    /***
     * <p>手机号码归属地</p>
     * @param httpUrl <p>待请求的{@link qing.yun.hui.common.constants.APIConstant httpUrl}地址 【y】</p>
     * @param key    申请的key 【y】
     * @param phone 需要查询的手机号码或手机号码前7位 【y】
     * @param dtype 返回的数据的格式，json或xml，默认为json 【N】
     * @param method 请求方式(get或post),默认为get【N】
     * @return 查询到的手机详情
     * */
    private static String getMobileData(String httpUrl,String key,String phone,String dtype,String method){
    	if(StringUtil.isEmpty(httpUrl,phone,key)){
    		logger.error("=============>缺少必要参数，httpUrl，mobile，key必填项。");
    		return null;
    	}
    	Map<String,Object> params=initMap(key, dtype);
    	params.put("phone", phone);
    	try {
    		return HttpUtil.sendRequest(httpUrl, params,method);
		} catch (Exception e) {
			logger.error("处理异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
		}
    	return null;
    }
    
    /***
     * <p>手机号码归属地</p>
     * @param phone 需要查询的手机号码或手机号码前7位 【y】
     * @param dtype 返回的数据的格式，json或xml，默认为json 【N】
     * @param method 请求方式(get或post),默认为get【N】
     * @return 查询到的手机详情
     * */
    public static MobileResponse callMobileResponse(String phone,String dtype,String method){
    	MobileResponse mobileResponse=null;
    	String mobileData=getMobileData(JuheEnum.Moble.HTTP_URL.getCode(), JuheEnum.Moble.APP_KEY.getCode(), phone, dtype, method);
    	if(StringUtil.isEmpty(mobileData)) return mobileResponse;
    	if(Constant.XML.equalsIgnoreCase(dtype)){
    		//TODO 
    		return mobileResponse;
    	}
    	JuheResponse juheResponse=JSONObject.parseObject(mobileData, JuheResponse.class);
    	JSONObject jsonObj= JSONObject.parseObject(mobileData);
    	String resultcode= jsonObj.getString("resultcode");
    	if(!JuheConstant.resultcode.equals(resultcode)) return mobileResponse;
    	String result=juheResponse.getResult();
    	mobileResponse=JSONObject.parseObject(result, MobileResponse.class);
    	BeanUtils.copyProperties(juheResponse,mobileResponse);
    	return mobileResponse;
    }
    
    /**
     * <p>获取中国【北京】标准时间</p>
     * @param httpUrl <p>待请求的{@link qing.yun.hui.common.constants.APIConstant httpUrl}地址</p>
     * @return Date
     */
    public static Date getChinaDate(String httpUrl){
    	if(StringUtil.isEmpty(httpUrl)){
    		logger.info("==============>请求的url为null，默认将以系统时间为准。");
    		return new Date();
    	}
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
    
    //============================================>以下为私有方法，不能对外提供====================================================>
    
    /**
	  * <p>股票数据（包含了沪深股市，香港股市，美国股市数据每个股市对应的接口中不一样，但key是一样的）</p>
	  * @param httpUrl 待请求的url 【y】
	  * @param key  申请的key 【y】
	  * @param stockShort 枚举类型{股市类型，如:沪深股市，香港股市，美国股市.}【y】
	  * @param code 	股票编号，上海股市以sh开头，深圳股市以sz开头如：sh601009（type为0或者1时gid不是必须）【y】
	  * @param type 0代表上证指数，1代表深证指数 【N】
	  * @param dtype  返回的数据的格式，json或xml，默认为json 【N】
	  * @param method 请求方式(get或post) ,默认为get【N】
	  * @return 股票数据
	  * */
	 private static String intStock(String httpUrl,String key,JuheEnum.StockShort stockShort,String code,Integer type,String dtype,String method){
		 if(StringUtil.isEmpty(httpUrl,key,code) || null==stockShort){
			 logger.error("=============>缺少必要参数，httpUrl，key，code必填项。");
	    	 return null;
		 }
		 Map<String,Object> params=initMap(key, dtype);
		 if(JuheEnum.StockShort.HK.getCode().equals(stockShort.getCode())){
			 params.put(JuheEnum.StockShort.HK.getParameter(), code);
			 httpUrl=httpUrl+JuheEnum.StockShort.HK.getCode();//香港 =HK
		 }else if(JuheEnum.StockShort.HS.getCode().equals(stockShort.getCode())){
			 params.put(JuheEnum.StockShort.HS.getParameter(), code);
			 httpUrl=httpUrl+JuheEnum.StockShort.HS.getCode();//上海，深圳=HZ
		 }else if(JuheEnum.StockShort.USA.getCode().equals(stockShort.getCode())){
			 params.put(JuheEnum.StockShort.USA.getParameter(), code); 
			 httpUrl=httpUrl+JuheEnum.StockShort.USA.getCode();//美国=USA
		 }else{
			 return null;
		 }
		 if(null!=type)params.put("type", type);
   	 try {
   		return HttpUtil.sendRequest(httpUrl, params,method);
		 } catch (Exception e) {
			logger.error("处理异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
		 }
		 return null;
	 }
    
    private static Map<String,Object> initMap(String key,String dtype){
    	Map<String,Object> map=new HashMap<String, Object>();
    	map.put("key", key);
    	map.put("dtype", dtype);
    	return map;
    }
}
