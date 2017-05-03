package qing.yun.hui.rocketmq.bean;

import lombok.Getter;

/***
 ** @category 消息订阅实体...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月3日下午2:52:15
 **/
@Getter
public class Subscribers {
	
	private final String topic;
	private final String subExpression;
	
	public Subscribers(String topic,String subExpression){
		this.topic=topic;
		this.subExpression=subExpression;
	}

	@Override
	public String toString() {
		return "Subscribers [topic=" + topic + ", expression=" + subExpression + "]";
	}
	
	
}
