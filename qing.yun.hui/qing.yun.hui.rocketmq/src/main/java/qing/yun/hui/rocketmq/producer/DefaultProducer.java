package qing.yun.hui.rocketmq.producer;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;

/***
 ** @category 生产者...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月3日下午1:51:33
 **/
public class DefaultProducer {
	
	private DefaultMQProducer defaultMQProducer;
	
	private final String producerGroup;	// 需要由应用来保证唯一
	
	private final String namesrvAddr;
	
	private final String instanceName;
	
	public DefaultProducer(String producerGroup,String namesrvAddr,String instanceName){
		this.producerGroup=producerGroup;
		this.namesrvAddr=namesrvAddr;
		this.instanceName=instanceName;
	}

	public void init() throws MQClientException{
		defaultMQProducer=new DefaultMQProducer(producerGroup);
		defaultMQProducer.setNamesrvAddr(namesrvAddr);
		defaultMQProducer.setInstanceName(instanceName);
		defaultMQProducer.setVipChannelEnabled(false);
		defaultMQProducer.start();
	}
	
	public void destroy(){
		defaultMQProducer.shutdown();
	}

	public DefaultMQProducer getDefaultMQProducer() {
		return defaultMQProducer;
	}
	
}
