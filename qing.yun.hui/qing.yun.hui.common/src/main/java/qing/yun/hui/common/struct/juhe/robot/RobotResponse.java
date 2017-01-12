package qing.yun.hui.common.struct.juhe.robot;

import lombok.Getter;
import lombok.Setter;
import qing.yun.hui.common.struct.juhe.JuheResponse;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月12日下午9:12:00
 **/
@Getter
@Setter
public class RobotResponse extends JuheResponse{

	private String code;/*返回的数据类型，请根据code的值去数据类型API查询*/
	
	private String text;
	
	private String result;
}
