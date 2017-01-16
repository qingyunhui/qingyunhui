package qing.yun.hui.common.struct.juhe.nba.match;

import lombok.Getter;
import lombok.Setter;
import qing.yun.hui.common.utils.StringUtil;

import com.alibaba.fastjson.JSONObject;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月16日上午10:23:47
 **/
@Getter
@Setter
public class MatchStatusData {
	
	private String title;
	
	private String statuslist;//JSON 串
	
	private MatchStatus matchStatus;
	
	public MatchStatus getMatchStatus(){
		if(StringUtil.isEmpty(statuslist)) return matchStatus;
		matchStatus=JSONObject.parseObject(statuslist, MatchStatus.class);
		return matchStatus;
	}
}

@Getter
@Setter
class MatchStatus{
	
	private String st0;// "未开赛", 
	
	private String st1;// "直播中", 
	
	private String st2;// "已结束"
	
}