/*package qing.yun.hui.activemq.listener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.alibaba.fastjson.JSONObject;


*//***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月30日下午3:39:48
 **//*
public class DefaultMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		System.out.println("is ok...");
		try {
//			message.acknowledge();//接收响应
			if (message instanceof TextMessage) {
				// 接收数据的时间（等待） 100 ms
				String obj=((TextMessage)message).getText();
			    System.out.println("接收的消息："+obj);
			}
		} catch (Exception e) {
			System.out.println("error.is :{}"+new Object[]{JSONObject.toJSONString(e)});
		}
	}
}
*/