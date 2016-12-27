package qing.yun.hui.common.struct;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月19日上午9:49:18
 **/
@Setter
@Getter
public class CallableData <T>{
	
	/**
	 * 线程名称
	 * */
	private String threadName;
	
	/**
	 * 线程返回的结果值
	 * */
	private T data;

}
