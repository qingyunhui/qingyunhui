package qing.yun.hui.rocketmq.listener;

import com.alibaba.rocketmq.common.message.MessageExt;

import qing.yun.hui.rocketmq.bean.ConsumeContext;
import qing.yun.hui.rocketmq.bean.MqMsgResult;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月3日下午2:39:25
 **/
public interface MessageListener {
	
	/**
	 * 消费消息接口，由应用来实现<br>
	 * @param message 消息
	 * @param context消费上下文
	 * @return 消费结果，如果应用抛出异常或者返回Null等价于返回MqMsgResult.FAIL
	 */
	public MqMsgResult consume(final MessageExt message, final ConsumeContext context);
	
}
