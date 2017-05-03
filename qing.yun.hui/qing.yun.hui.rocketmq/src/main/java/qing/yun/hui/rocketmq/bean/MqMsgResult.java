package qing.yun.hui.rocketmq.bean;
/***
 ** @category 消息消费的返回结果...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月3日下午2:41:53
 **/
public enum MqMsgResult {
	
	/**消费成功，继续消费下条消息*/
	SUCCEED,
	
	/**消费失败，告知服务器稍后在投递，继续投递下一条消息*/
	FAIL;

}
