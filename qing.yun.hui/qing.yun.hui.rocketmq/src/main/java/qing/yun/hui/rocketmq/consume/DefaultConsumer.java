package qing.yun.hui.rocketmq.consume;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import qing.yun.hui.common.utils.StringUtil;
import qing.yun.hui.rocketmq.bean.ConsumeContext;
import qing.yun.hui.rocketmq.bean.MqMsgResult;
import qing.yun.hui.rocketmq.bean.Subscribers;
import qing.yun.hui.rocketmq.listener.MessageListener;

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
 ** @createTime: 2017年5月3日下午2:06:41
 **/
public class DefaultConsumer {

	private DefaultMQPushConsumer defaultMQPushConsumer;
	private final ConcurrentHashMap<String/* Topic */, MessageListener> subscribeTable = new ConcurrentHashMap<String, MessageListener>();
	private Map<Subscribers,MessageListener> subscribersTable;
	private final String producerGroup;	// 需要由应用来保证唯一
	private final String namesrvAddr;
	private final String instanceName;
	private final int consumeMessageBatchMaxSize;                                                       // 每次消费时拉取得消息条数
	private final int consumeThreadMin;                                                                // 最小线程数
	private final int consumeThreadMax;                                                                 // 最大线程数
	private final static int DEFAULT_CONSUMEMESSAGEBATCHMAXSIZE = 1;
	
	public DefaultConsumer(Map<Subscribers,MessageListener> subscribersTable,String producerGroup,String namesrvAddr,String instanceName,int consumeThreadMin,int consumeThreadMax){
		this(subscribersTable, producerGroup, namesrvAddr, instanceName,  DEFAULT_CONSUMEMESSAGEBATCHMAXSIZE, consumeThreadMin, consumeThreadMax);
	}
	
	public DefaultConsumer(Map<Subscribers,MessageListener> subscribersTable,String producerGroup,String namesrvAddr,String instanceName,int consumeMessageBatchMaxSize,int consumeThreadMin,int consumeThreadMax){
		this.subscribersTable=subscribersTable;
		this.producerGroup=producerGroup;
		this.namesrvAddr=namesrvAddr;
		this.instanceName=instanceName;
		this.consumeMessageBatchMaxSize=consumeMessageBatchMaxSize;
		this.consumeThreadMin=consumeThreadMin;
		this.consumeThreadMax=consumeThreadMax;
	}
	
	public void init() throws MQClientException{
		if(StringUtil.isEmpty(producerGroup,namesrvAddr,instanceName,subscribersTable)){
			throw new RuntimeException(" param is null.");
		}
		defaultMQPushConsumer = new DefaultMQPushConsumer(producerGroup);
		defaultMQPushConsumer.setNamesrvAddr(namesrvAddr);
		defaultMQPushConsumer.setInstanceName(instanceName);
		defaultMQPushConsumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
		defaultMQPushConsumer.setConsumeThreadMin(consumeThreadMin);
		defaultMQPushConsumer.setConsumeThreadMax(consumeThreadMax);
		/**
	     * 一个新的订阅组第一次启动从队列的最前位置开始消费<br>
	     * 后续再启动接着上次消费的进度开始消费
	     */
		defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		defaultMQPushConsumer.setMessageModel(MessageModel.CLUSTERING);
		defaultMQPushConsumer.setVipChannelEnabled(false);  
		
		Iterator<Entry<Subscribers, MessageListener>> sbscribersIterator= subscribersTable.entrySet().iterator();
		
		while(sbscribersIterator.hasNext()){
			Entry<Subscribers, MessageListener> entry= sbscribersIterator.next();
			Subscribers subscribers= entry.getKey();
			MessageListener msgListener= entry.getValue();
			defaultMQPushConsumer.subscribe(subscribers.getTopic(), subscribers.getSubExpression());
			subscribeTable.put(subscribers.getTopic(), msgListener);
		}
		
		defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently(){
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				MessageExt msgExt=msgs.get(0);
				String topic=msgExt.getTopic();
				MessageListener msgListener=subscribeTable.get(topic);
				ConsumeContext content=new ConsumeContext();
				MqMsgResult result= msgListener.consume(msgExt, content);
				switch (result) {
					case SUCCEED:
						return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
					case FAIL:
						return ConsumeConcurrentlyStatus.RECONSUME_LATER;
					default:
						break;
				}
				return null;
			}
		});
		defaultMQPushConsumer.start();
	}
	
	public void destroy(){
		defaultMQPushConsumer.shutdown();
	}
}
