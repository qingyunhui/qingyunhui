/*package qing.yun.hui.activemq.consumer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qing.yun.hui.activemq.listener.DefaultMessageListener;

*//***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月30日下午2:00:23
 **//*

public class DefaultConsumer {
	
	private Logger logger =LoggerFactory.getLogger(getClass());

	//mq的默认用户名
	private final String default_user;
	
	//mq的默认密码
	private final String default_password;
	
	//mq的默认连接
	private final String default_broker_url;
	
	//mq的队列名称
	private final String queue;
	
	//消费者
	private MessageConsumer messageConsumer;
	
	//会话
	private Session session;
	
	private final int timeout;
	
	public DefaultConsumer(String default_user,String default_password,String default_broker_url,String queue,int timeout){
		this.default_user=default_user;
		this.default_password=default_password;
		this.default_broker_url=default_broker_url;
		this.queue=queue;
		this.timeout=timeout;
	}
	
	public void destroy() throws JMSException {
		logger.info("=====开始销毁MQ消费者实例....");
		messageConsumer.close();
		logger.info("=====销毁MQ消费者实例完毕....");
	}
	
	public void init(){
		ConnectionFactory conFactory=null;
		Connection conn=null;
		Session session=null;
		Destination destionation=null;
        //实例化连接工厂
		conFactory = new ActiveMQConnectionFactory(default_user, default_password, default_broker_url);
		try {
				conn=conFactory.createConnection();
				conn.start();
				session=conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
				destionation=session.createQueue(queue);
				//创建消息消费者
	            messageConsumer = session.createConsumer(destionation);
	            messageConsumer.setMessageListener(new DefaultMessageListener());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(null!=conn){
				try {
					conn.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	//-----------------------------------------get----------------------------------------------
	
	public Session getSession(){
		return session;
	}
	
	public MessageConsumer getMessageConsumer(){
		return messageConsumer;
	}
	
}
*/