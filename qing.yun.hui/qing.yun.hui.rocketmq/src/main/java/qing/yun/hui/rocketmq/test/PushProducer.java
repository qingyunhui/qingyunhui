
package qing.yun.hui.rocketmq.test;

import java.util.concurrent.TimeUnit;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年7月11日上午10:33:09
 **/
public class PushProducer {

public static void main(String[] args) throws MQClientException,InterruptedException {
		
		DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
		producer.setNamesrvAddr("112.124.117.151:9876");
		producer.setInstanceName("DefaultProducer");
		producer.start();
		producer.setVipChannelEnabled(false);
		for (int i = 0; i < 1; i++) {
			try {
				{
					Message msg = new Message("TopicTest1","TagA",("Hello MetaQ").getBytes());
					SendResult sendResult = producer.send(msg);
					System.out.println(sendResult);
				}
				{
					Message msg = new Message("TopicTest1","TagB",("Hello MetaQ").getBytes());
					SendResult sendResult = producer.send(msg);
					System.out.println(sendResult);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			TimeUnit.MILLISECONDS.sleep(1000);
		}
		producer.shutdown();
	}
}
