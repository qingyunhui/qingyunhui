package qing.yun.hui.activemq.consumer;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/***
 ** @category 从topic中订阅消息...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年4月24日下午2:26:19
 **/
@Component
public class DefaultTopicConsumers {

private Logger logger =LoggerFactory.getLogger(getClass());
	
	@Autowired
    private JmsTemplate jmsTopicTemplate;
     
    /**
     * @param destination 
     * 从指定 destination 中接收消息
     * @return TextMessage
     */
    public TextMessage receive(Destination destination) {
        TextMessage tm = (TextMessage) jmsTopicTemplate.receive(destination);
        try {
        	logger.info("从队列" + destination.toString() + "收到了消息：\t"+ tm.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return tm;
    }
	
    /**
     * 接收默认消息
     * @return TextMessage
     */
    public TextMessage receive() {
    	TextMessage tm=null;
        try {
        	 tm= (TextMessage) jmsTopicTemplate.receive(jmsTopicTemplate.getDefaultDestination());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tm;
    }
}
