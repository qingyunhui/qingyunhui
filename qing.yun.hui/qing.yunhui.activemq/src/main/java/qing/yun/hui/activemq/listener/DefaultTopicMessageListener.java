package qing.yun.hui.activemq.listener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.alibaba.fastjson.JSONObject;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年4月21日上午11:44:00
 **/
public class DefaultTopicMessageListener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				String obj=((TextMessage)message).getText();
			    System.out.println("Topic接收的消息："+obj);
			}
		} catch (Exception e) {
			System.out.println("error.is :{}"+new Object[]{JSONObject.toJSONString(e)});
		}
	}
	
}
