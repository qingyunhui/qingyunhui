package qing.yun.hui.common.struct.juhe.phone.telephone;

import qing.yun.hui.common.struct.juhe.JuheResponse;
import lombok.Getter;
import lombok.Setter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月13日上午9:39:21
 **/
@Getter
@Setter
public class CallerIDTelephoneResponse extends JuheResponse{
	
	/**是否诈骗、营销、广告电话*/
	private Integer  iszhapian;
	
	/**号码所属省份*/
	private String province; 
	
	/**号码所属城市*/
	private String city;
	
	/**号码所属运营商*/
	private String sp;
	
	/**查询号码*/
	private String phone;
	
	/**号码性质*/
	private String rpt_type;
	
	/**号码性质描述*/
	private String rpt_comment;
	
	/**标记人数*/
	private String rpt_cnt;
	
	/**json数组，号码详细，可能部分号码为NULL*/
	private TelephoneData hy;
	
	/**描述*/
	private String countDesc;
	
	/**该号码所属公司名称*/
	private String hyname;
	
	private String result;
	
	/*{
	    "reason": "查询成功", 
	    "result": {
	        "iszhapian": 0, 
	        "province": "湖南", 
	        "city": "永州", 
	        "sp": "", 
	        "phone": "07466551538", 
	        "rpt_type": "", 
	        "rpt_comment": "", 
	        "rpt_cnt": null, 
	        "hy": null, 
	        "countDesc": "", 
	        "hyname": ""
	    }, 
	    "error_code": 0
	}
*/
}
