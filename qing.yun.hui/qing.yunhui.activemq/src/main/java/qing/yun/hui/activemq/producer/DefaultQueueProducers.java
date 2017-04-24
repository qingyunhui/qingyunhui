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
 ** @category 消息生产者...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年4月5日下午3:25:26
 **/
@Component
public class DefaultQueueProducers {

	private Logger logger =LoggerFactory.getLogger(getClass());

	@Autowired
	private JmsTemplate jmsQueueTemplate;
	
      /**
       * 向指定队列发送消息
       */
      public void sendMessage(Destination destination, final String msg) {
    	  logger.info("向队列(queue)" + destination.toString() + "发送了消息------------" + msg);
    	  jmsQueueTemplate.send(destination, new MessageCreator() {
	          public Message createMessage(Session session) throws JMSException {
	            return session.createTextMessage(msg);
	          }
        });
      }
     
    /**
     * 向默认队列发送消息
     */
      public void sendMessage(final String msg) {
        String destination =  jmsQueueTemplate.getDefaultDestination().toString();
        logger.info("向队列(queue)" +destination+ "发送了消息------------" + msg);
        jmsQueueTemplate.send(new MessageCreator() {
	          public Message createMessage(Session session) throws JMSException {
	            return session.createTextMessage(msg);
	          }
        });
      }
}
