package cn.com.rocketmq.producer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import qing.yun.hui.common.utils.DateUtil;
import qing.yun.hui.rocketmq.message.DefaultMessage;
import qing.yun.hui.rocketmq.producer.DefaultProducer;
import cn.com.rocketmq.producer.bean.Info;
import cn.com.rocketmq.producer.message.SendInfoMessage;
import cn.com.rocketmq.producer.util.BeanUtil;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSONObject;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月3日下午5:12:29
 **/
@Controller
@RequestMapping(TestAction.ACTION_PATH)
public class TestAction {

	protected static final String ACTION_PATH = "/test";
	
	Logger logger=LoggerFactory.getLogger(getClass());
	
	
	
	@RequestMapping(value = "/hello")
	public ModelAndView hello(HttpServletRequest request,HttpServletResponse response, HttpSession session) throws MQClientException, RemotingException, MQBrokerException, InterruptedException {
		logger.info("*********** producer.success ***********");
		DefaultMessage<Info> defaultMessage= BeanUtil.getBeanOfType(SendInfoMessage.class);
		DefaultProducer defaultProducer= BeanUtil.getBeanOfType(DefaultProducer.class);
		 String times=DateUtil.getTimeShort(DateUtil.YYYY_MM_DD_HH_MM_SS);
		Info info=new Info();
		info.setAge(22);
		info.setName("张三"+times);
		info.setNote("测试"+times);
		Message message= defaultMessage.getMessage(info);
		SendResult result= defaultProducer.getDefaultMQProducer().send(message);
		if(null==result){
			logger.error("*****发送失败，啦啦!*******");
		}else{
			logger.info("********"+JSONObject.toJSONString(result)+"*******");
		}
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/hello");
		return modelView;
	}
	
}
