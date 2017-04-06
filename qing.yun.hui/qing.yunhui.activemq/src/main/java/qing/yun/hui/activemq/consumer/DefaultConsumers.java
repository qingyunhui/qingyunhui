/*package qing.yun.hui.activemq.consumer;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

*//***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年4月5日下午4:27:34
 **//*
public class DefaultConsumers {

	private Logger logger =LoggerFactory.getLogger(getClass());
	
	@Autowired
    private JmsTemplate jmsTemplate;
     
    *//**
     * 接收消息
     *//*
    public TextMessage receive(Destination destination) {
        TextMessage tm = (TextMessage) jmsTemplate.receive(destination);
        try {
        	logger.info("从队列" + destination.toString() + "收到了消息：\t"+ tm.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return tm;
    }
}
*/