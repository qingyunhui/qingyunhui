/*package qing.yun.hui.activemq.message;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;

import qing.yun.hui.activemq.producer.DefaultProducer;

*//***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月30日下午2:24:40
 **//*
public  class DefaultMessage{
	
	@Autowired
	private DefaultProducer defaultProducer;
	
	*//**
	 * <p>将待发送的消息转换成ObjectMessage</p>
	 * @param t 待发送的消息
	 * @return ObjectMessage
	 * *//*
	public <T> ObjectMessage getInstance(T t) throws JMSException{
		ObjectMessage objMsg= defaultProducer.getSession().createObjectMessage();
		objMsg.setObject((Serializable) t);
		return objMsg;
	}
	
	*//**
	 * <p>发送消息</p>
	 * @param t 待发送的消息
	 * @return void
	 * *//*
	public <T> void send(T t) throws JMSException{
		defaultProducer.send(getInstance(t));
		defaultProducer.getSession().commit();
	}
}
*/