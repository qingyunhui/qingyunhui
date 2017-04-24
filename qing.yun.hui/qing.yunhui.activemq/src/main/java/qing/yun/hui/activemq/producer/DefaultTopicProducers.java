package qing.yun.hui.activemq.producer;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/***
 ** @category 消息发布者
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年4月24日下午2:12:43
 **/
@Component
public class DefaultTopicProducers {

	private Logger logger =LoggerFactory.getLogger(getClass());

	@Autowired
	private JmsTemplate jmsTopicTemplate;
	
      /**
       * 向指定队列发送消息
       */
      public void sendMessage(Destination destination, final String msg) {
    	  logger.info("向队列(topic)" + destination.toString() + "发送了消息------------" + msg);
    	  jmsTopicTemplate.send(destination, new MessageCreator() {
	          public Message createMessage(Session session) throws JMSException {
	            return session.createTextMessage(msg);
	          }
        });
      }
     
    /**
     * 向默认队列发送消息
     */
      public void sendMessage(final String msg) {
        String destination =  jmsTopicTemplate.getDefaultDestination().toString();
        logger.info("向队列(topic)" +destination+ "发送了消息------------" + msg);
        jmsTopicTemplate.send(new MessageCreator() {
	          public Message createMessage(Session session) throws JMSException {
	            return session.createTextMessage(msg);
	          }
        });
      }
	
}
