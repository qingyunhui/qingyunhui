package qing.yun.hui.common.utils.api;

import com.alibaba.fastjson.JSONObject;

import qing.yun.hui.common.struct.juhe.idcard.IdCardResponse;
import qing.yun.hui.common.struct.juhe.news.NewsTopResponse;
import qing.yun.hui.common.struct.juhe.phone.mobile.MobileResponse;
import qing.yun.hui.common.struct.juhe.phone.telephone.CallerIDTelephoneResponse;
import qing.yun.hui.common.struct.juhe.robot.RobotResponse;
import qing.yun.hui.common.struct.juhe.wechat.choiceness.WechatChoicenessResponse;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月15日上午11:55:37
 **/
public class ApiTest {
	
	public static void main(String[] args){
		String dtype="json";
		String method="get";
		//手机固话来电显示
		CallerIDTelephoneResponse data=ApiUtil.callCallerIDTelephoneResponse("07466551538", dtype,method);
		System.out.println("手机固话来电显示:"+JSONObject.toJSONString(data));
		//身份证查询
		IdCardResponse IdData= ApiUtil.callIdCardResponse("640202199411288672", dtype, method);
		System.out.println("身份证查询:"+JSONObject.toJSONString(IdData));
		//手机号码归属地
		MobileResponse mobileData= ApiUtil.callMobileResponse("18665300640", dtype, method);
		System.out.println("手机号码归属地:"+JSONObject.toJSONString(mobileData));
		//问答机器人
		RobotResponse robotData=ApiUtil.callRobotResponse("中国人", dtype, method);
		System.out.println("问答机器人:"+JSONObject.toJSONString(robotData));
		//新闻头条
		NewsTopResponse newsData= ApiUtil.callNewsTopResponse("top", dtype, method);
		System.out.println("新闻头条:"+JSONObject.toJSONString(newsData));
		//微信精选
		WechatChoicenessResponse wechatData= ApiUtil.callWechatChoicenessResponse(1, 10, dtype, method);
		System.out.println("微信精选:"+JSONObject.toJSONString(wechatData));
	}

}
