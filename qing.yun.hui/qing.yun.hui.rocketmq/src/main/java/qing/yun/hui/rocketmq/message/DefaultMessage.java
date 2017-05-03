package qing.yun.hui.rocketmq.message;

import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSONObject;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月3日上午11:58:38
 **/
public class DefaultMessage<T> implements MessageInterface<T>{

	private final String topic;
	
	private final String tags;
	
	public DefaultMessage(String topic,String tags){
		this.topic=topic;
		this.tags=tags;
	}
	
	@Override
	public Message getMessage(T t) {
		return new Message(topic,tags, JSONObject.toJSONString(t).getBytes());
	}
}
