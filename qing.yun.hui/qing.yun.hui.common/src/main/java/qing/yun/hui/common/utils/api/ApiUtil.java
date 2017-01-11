package qing.yun.hui.common.utils.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/***
 ** @category 该API是用来获取一些公共的接口信息，比如获取北京时间，天气预报，等等信息...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月11日上午10:17:33
 **/
public class ApiUtil {
	
	private static Logger logger=LoggerFactory.getLogger(ApiUtil.class);
	
	 /**
     * 获取中国标准时间
     * @param 待请求的url地址
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
