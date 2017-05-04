package cn.com.rocketmq.consumer.bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月4日上午9:31:07
 **/
@Getter
@Setter
public class InfoMessage {
	
private String name;
	
	private String note;
	
	private int age;
	
	private Double money;

	private String comment;
	
	private Date curDate;
	
	private Date ctime;
	
	private Long id;
	
	private String address;

	private Long prodId;
	
	/**
	*动作(比如:标的还款..)
	*/
	private Integer actionType;

	/**
	*状态(0:未通知,1.通知中,2.通知失败,3.已通知)
	*/
	private Integer status;
	
}
