package qing.yun.hui.common.utils;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月23日下午5:17:40
 **/
public class ThreadUtil <T>{
	
	
	

}

@Getter
@Setter
class ThreadStruct{
	
	private String threadName;
	
	private int curThread;
	
	public ThreadStruct(){}
	
	public ThreadStruct(String threadName,int curThread){
		this.threadName=threadName;
		this.curThread=curThread;
	}
}
