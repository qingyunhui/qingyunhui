package qing.yunhui.activemq.message;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import qing.yunhui.activemq.producer.DefaultProducer;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月30日下午2:24:40
 **/
public abstract class DefaultMessage implements TextMessage{
	
	@Autowired
	private DefaultProducer defaultProducer;
	
	public <T> ObjectMessage getInstance(T t) throws JMSException{
		ObjectMessage objMsg= defaultProducer.getSession().createObjectMessage();
		objMsg.setObject(JSONObject.toJSONString(t).getBytes());
		return objMsg;
	}
	
}
