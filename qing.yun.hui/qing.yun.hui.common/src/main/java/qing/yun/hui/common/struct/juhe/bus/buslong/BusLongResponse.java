package qing.yun.hui.common.struct.juhe.bus.buslong;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import qing.yun.hui.common.struct.juhe.JuheResponse;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月12日下午8:43:37
 **/
@Getter
@Setter
public class BusLongResponse extends JuheResponse{
	
	//标题
	private String title;	
	
	//长途汽车信息集合
	private List<BusLongData> list;
	
	private String result;
}
