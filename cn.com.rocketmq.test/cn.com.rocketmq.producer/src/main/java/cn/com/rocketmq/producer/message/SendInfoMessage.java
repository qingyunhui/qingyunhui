package cn.com.rocketmq.producer.message;

import qing.yun.hui.rocketmq.message.DefaultMessage;
import cn.com.rocketmq.producer.bean.Info;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月4日上午9:30:19
 **/
public class SendInfoMessage extends DefaultMessage<Info>{

	public SendInfoMessage(String topic, String tags) {
		super(topic, tags);
	}
}
