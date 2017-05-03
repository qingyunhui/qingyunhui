package qing.yun.hui.rocketmq.test.message;

import qing.yun.hui.rocketmq.message.DefaultMessage;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月3日下午3:45:40
 **/
public class TestMessage extends DefaultMessage<String>{

	public TestMessage(String topic, String tags) {
		super(topic, tags);
	}
	
}
