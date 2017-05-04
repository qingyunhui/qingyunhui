package cn.com.rocketmq.consumer.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qing.yun.hui.common.utils.DateUtil;
import qing.yun.hui.rocketmq.bean.ConsumeContext;
import qing.yun.hui.rocketmq.bean.MqMsgResult;
import qing.yun.hui.rocketmq.listener.MessageListener;

import com.alibaba.rocketmq.common.message.MessageExt;

/***
 ** @category 消息监听器...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月4日上午10:11:04
 **/
public class InfoMessageListener implements MessageListener{
	
	Logger logger=LoggerFactory.getLogger(getClass());

	@Override
	public MqMsgResult consume(MessageExt message, ConsumeContext context) {
		
		logger.info("******收到来自：{}的消息，开始从 topic：{}订阅消息.",new Object[]{message.getTags(),message.getTopic()});
		
		String msgBody = new String(message.getBody());
	    String msgExt = message.toString();
		
	    String times=DateUtil.getTimeShort(DateUtil.YYYY_MM_DD_HH_MM_SS);
	    
	    logger.info(times+"{msgBody}:"+msgBody);
	    
	    logger.info(times+"{msgExt}:"+msgExt);
	    
		return MqMsgResult.SUCCEED;
	}
	
	public boolean handlMessage(){
		
		
		return false;
	}

}
