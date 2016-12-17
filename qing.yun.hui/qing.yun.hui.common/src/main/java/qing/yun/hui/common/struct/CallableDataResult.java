package qing.yun.hui.common.struct;

import java.util.List;
import java.util.concurrent.Future;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 一般用于多线程处理
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月16日下午11:22:11
 **/
@Getter
@Setter
public class CallableDataResult<T> {
	
	/***总线程数*/
	private int totalThread;
	
	/**总线程处理成功总记录数*/
	private int totalSuccess;
	
	/**总线程处理失败总记录数*/
	private int totalFail;
	
	/**总线程处理返回值列表集*/
	private List<Future<T>> futures;
	
	/**是否所有线程处理都返回true*/
	public boolean isAllSuccess(){
		return totalSuccess==totalThread;
	}
	
	/**是否部分处理返回true*/
	public boolean isPartSuccess(){
		return totalSuccess!=totalThread && totalSuccess-totalFail>0;
	}
	
	/**是否所有线程处理返回false*/
	public boolean isAllFail(){
		return totalSuccess==0 || totalFail==totalThread;
	}
}
