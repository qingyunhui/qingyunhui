package qing.yun.hui.activemq.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月30日下午3:39:48
 **/
public class DefaultMessageListener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		try {
			ObjectMessage defaultMsg=(ObjectMessage)message;
			System.out.println("接收到消息:"+defaultMsg.getObject());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
