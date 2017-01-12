package qing.yun.hui.common.struct.juhe.joke;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月13日上午10:34:31
 **/
@Getter
@Setter
public class JokeData {

	private String content;
	
	private String hashId;

	private Long unixtime;
	
	private String updatetime;
	
}
