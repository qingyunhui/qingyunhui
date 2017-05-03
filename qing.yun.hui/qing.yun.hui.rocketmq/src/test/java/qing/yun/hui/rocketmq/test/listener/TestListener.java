package qing.yun.hui.rocketmq.test.listener;

import java.util.List;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月3日下午3:48:30
 **/
public class TestListener implements MessageListenerConcurrently{

	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,ConsumeConcurrentlyContext context) {
		
		for(MessageExt msgExt:msgs){
			System.out.println(String.valueOf(msgExt.getBody()));
		}
		
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}

}
