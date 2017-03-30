package qing.yun.hui.activemq.producer;

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月30日下午2:00:37
 **/
public class DefaultProducer {
	
	private Logger logger =LoggerFactory.getLogger(getClass());

	//mq的默认用户名
	private final String default_user;
	
	//mq的默认密码
	private final String default_password;
	
	//mq的默认连接
	private final String default_broker_url;
	
	//mq的队列名称
	private final String queue;
	
	//生产者
	private MessageProducer messageProducer;
	
	//会话
	private Session session;
	
	public DefaultProducer(String default_user,String default_password,String default_broker_url,String queue){
		this.default_user=default_user;
		this.default_password=default_password;
		this.default_broker_url=default_broker_url;
		this.queue=queue;
	}
	
	/**
	 * <p>发送消息</p>
	 * @param t 待发送的消息
	 * @return void
	 * */
	public <T> void send(T t) throws JMSException{
		ObjectMessage objMsg= session.createObjectMessage();
		objMsg.setObject((Serializable) t);
		messageProducer.send(objMsg);
		session.commit();
	}
	
	public void init(){
		logger.info("=====开始初始化MQ生产者....");
		ConnectionFactory conFactory;
		Connection connection = null;
		Destination destination=null;
		conFactory=new ActiveMQConnectionFactory(default_user, default_password, default_broker_url);
		try {
			//通过连接工厂获取连接
			connection=conFactory.createConnection();
			//启动
			connection.start();
			//创建session
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            //创建一个名称为HelloWorld的消息队列
            destination=session.createQueue(queue);
            //创建消息生产者
            messageProducer = session.createProducer(destination);
            logger.info("=====初始化MQ生产者完毕....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void destroy() throws JMSException {
		logger.info("=====开始销毁MQ生产者实例....");
		messageProducer.close();
		logger.info("=====销毁MQ生产者实例完毕....");
	}
	
	//-----------------------------------------get----------------------------------------------
	
	public Session getSession(){
		return session;
	}
	
	public MessageProducer getMessageProducer(){
		return messageProducer;
	}
}
