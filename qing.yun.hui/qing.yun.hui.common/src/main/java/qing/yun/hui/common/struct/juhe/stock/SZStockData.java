package qing.yun.hui.common.struct.juhe.stock;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 深（上）证指数【沪深股市】
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月13日下午3:18:24
 **/
@Getter
@Setter
public class SZStockData {
	
	/**成交量*/
	private Integer dealNum;// "14584425545"
	
	/**成交额*/
    private String dealPri;// "209305262561.646"
    
    /**最高*/
    private String highPri;// "10155.220", 
    
    /**涨跌百分比*/
    private String increPer;//"-1.21"
    
    /**涨跌幅*/
    private String increase;// "-122.930"
    
    /**最低*/
    private String lowpri;// "9997.403"
    
    /**名称*/
    private String name;// "深证成指"
    
    /**当前价格*/
    private String nowpri;// "10008.301"
    
    /**今天*/
    private String openPri;//"10135.677"
    
    /**时间*/
    private String time;//"2017-01-13 15:05:03"
    
    /**昨收*/
    private String yesPri;// "10131.230"
	
	  //---------------------------------深（上）证指数示例------------------------------------------------------------------
	  /**
		{
		    "error_code": 0, 
		    "reason": "SUCCESSED!", 
		    "result": {
		        "dealNum": "14584425545", //成交量
		        "dealPri": "209305262561.646", //成交额
		        "highPri": "10155.220", //最高
		        "increPer": "-1.21", //涨跌百分比
		        "increase": "-122.930", //涨跌幅
		        "lowpri": "9997.403", //最低
		        "name": "深证成指", //名称
		        "nowpri": "10008.301", //当前价格
		        "openPri": "10135.677", //今天
		        "time": "2017-01-13 15:05:03", //时间
		        "yesPri": "10131.230"//昨收
		    }
		}
	  **/
    /**
    {
        "error_code": 0, 
        "reason": "SUCCESSED!", 
        "result": {
            "dealNum": "14584425545", 
            "dealPri": "209305262561.646", 
            "highPri": "10155.220", 
            "increPer": "-1.21", 
            "increase": "-122.930", 
            "lowpri": "9997.403", 
            "name": "深证成指", 
            "nowpri": "10008.301", 
            "openPri": "10135.677", 
            "time": "2017-01-13 15:05:03", 
            "yesPri": "10131.230"
        }
    }
  **/
}
