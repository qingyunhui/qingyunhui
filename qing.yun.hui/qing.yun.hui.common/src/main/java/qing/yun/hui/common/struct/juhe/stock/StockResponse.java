package qing.yun.hui.common.struct.juhe.stock;

import lombok.Getter;
import lombok.Setter;
import qing.yun.hui.common.struct.juhe.JuheResponse;

/***
 ** @category 股票数据...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月13日下午3:05:57
 **/
@Getter
@Setter
public class StockResponse extends JuheResponse{
	
	private StockData stockData;
	
	private DapanData dapanData;
	
	private GoPicture goPicture;
  
}
