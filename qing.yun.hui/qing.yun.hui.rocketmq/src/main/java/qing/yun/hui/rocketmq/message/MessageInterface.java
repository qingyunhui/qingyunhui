package qing.yun.hui.rocketmq.message;

import com.alibaba.rocketmq.common.message.Message;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 * @param <T>
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月3日上午11:56:22
 **/
public interface MessageInterface<T> {
	
	/**
	 * <p>构造消息体</p>
	 * @param t 消息对象
	 * @return Message
	 * */
	Message getMessage(T t);

}
