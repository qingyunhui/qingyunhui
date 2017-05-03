/*package qing.yun.hui.rocketmq.test.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import qing.yun.hui.rocketmq.producer.DefaultProducer;
import qing.yun.hui.rocketmq.test.message.TestMessage;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSONObject;

*//***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月3日下午3:56:11
 **//*
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:rocketmq-producer.xml"})
public class TestProducer {
	
	@Autowired
	private DefaultProducer defaultProducer ;
	
	@Autowired
	private TestMessage testMessage;
	
	@Test
	public void test() throws MQClientException, RemotingException, MQBrokerException, InterruptedException{
		DefaultMQProducer producer= defaultProducer.getDefaultMQProducer();
		Message message= testMessage.getMessage("messageBean");
		SendResult sendResult= producer.send(message);
		System.out.println(JSONObject.toJSONString(sendResult));
	}
	
}
*/