package qing.yun.hui.common.struct.juhe.joke;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import qing.yun.hui.common.struct.juhe.JuheResponse;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月13日上午10:32:33
 **/
@Getter
@Setter
public class JokeResponse extends JuheResponse{
	
	List<JokeData> data;
	
}
