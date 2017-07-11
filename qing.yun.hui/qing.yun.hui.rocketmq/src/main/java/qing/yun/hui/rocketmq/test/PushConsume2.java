package qing.yun.hui.rocketmq.test;

import java.util.List;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;

/***
 ** @category 以广播模式进行消费...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年7月11日上午10:54:01
 **/
public class PushConsume2 {

	public static void main(String[]args) throws Exception{
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroupName");  
        //consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);  
        consumer.setMessageModel(MessageModel.BROADCASTING); 
        consumer.setNamesrvAddr("112.124.117.151:9876");  
        consumer.subscribe("TopicTest1", "TagA || TagB");  
        consumer.registerMessageListener(new MessageListenerConcurrently() {
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				 System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs);  
	             return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;  
			}  
        });  
        consumer.start();  
	}
	
	
}
