package qing.yun.hui.rocketmq.test;

import java.util.List;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月3日上午11:04:55
 **/
public class DefaultConsumeTest {
	
	/*
	 *  rocket 3.5.9 版本 出现的异常：Send [3] times, still failed：
		默认开启了VIP通道，VIP通道端口为10911-2=10909。若Rocket服务器未启动端口10909，则报connect to <：10909> failed。
		解决办法：添加：producer.setVipChannelEnabled(false);
		消费者接收不到消息
	     （1）同理，如果消费者能够启动成功，但是没有接收到消息，原因可能同上。
	     （2）解决方式：增加一行代码： consumer.setVipChannelEnabled(false);  
	*/
	
	/**
	 * 当前例子是PushConsumer用法，使用方式给用户感觉是消息从RocketMQ服务器推到了应用客户端。<br>
	 * 但是实际PushConsumer内部是使用长轮询Pull方式从MetaQ服务器拉消息，然后再回调用户Listener方法<br>
	 */
	public static void main(String[] args) throws InterruptedException,MQClientException {
		/**
		 * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例<br>
		 * 注意：ConsumerGroupName需要由应用来保证唯一
		 */
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroupName");
		consumer.setNamesrvAddr("mq02.niubangold.com:9876");
		consumer.setInstanceName("Consumber");
		/**
		 * 订阅指定topic下tags分别等于TagA或TagC或TagD
		 */
		consumer.subscribe("TopicTest1", "TagA || TagC || TagD");
		
		/**
	     * 一个新的订阅组第一次启动从队列的最前位置开始消费<br>
	     * 后续再启动接着上次消费的进度开始消费
	     */
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		consumer.setMessageModel(MessageModel.CLUSTERING);
		
		/**
		 * 订阅指定topic下所有消息<br>
		 * 注意：一个consumer对象可以订阅多个topic
		 */
		consumer.subscribe("TopicTest2", "*");
		consumer.setVipChannelEnabled(false);  
		consumer.registerMessageListener(new MessageListenerConcurrently() {
			/**
			 * 默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息
			 */
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				System.out.println(Thread.currentThread().getName()+ " Receive New Messages: " + msgs.size());
				MessageExt msg = msgs.get(0);
				if (msg.getTopic().equals("TopicTest1")) {
					// 执行TopicTest1的消费逻辑
					if (msg.getTags() != null && msg.getTags().equals("TagA")) {
						// 执行TagA的消费
						System.out.println(new String(msg.getBody()));
					} else if (msg.getTags() != null && msg.getTags().equals("TagC")) {
						// 执行TagC的消费
					} else if (msg.getTags() != null && msg.getTags().equals("TagD")) {
						// 执行TagD的消费
					}
				} else if (msg.getTopic().equals("TopicTest2")) {
					System.out.println(new String(msg.getBody()));
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		/**
		 * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
		 */
		consumer.start();
		System.out.println("Consumer Started.");
	}
}
