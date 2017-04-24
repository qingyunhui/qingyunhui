package qing.yun.hui.activemq.listener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.alibaba.fastjson.JSONObject;


/***
 ** @category <p>消息监听器，须要实现MessageListener且须要在对应的xml中指定监控的对象</p>
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月30日下午3:39:48
 **/
public class DefaultQueueMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				String obj=((TextMessage)message).getText();
			    System.out.println("Queue接收的消息："+obj);
			}
		} catch (Exception e) {
			System.out.println("error.is :{}"+new Object[]{JSONObject.toJSONString(e)});
		}
	}
}
