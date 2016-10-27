package qing.yun.hui.common.utils.Validate.bean;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年5月13日下午3:22:13
 **/
public class Validata {
	
	private Rules rules;
	
	private Messages messages;
	
	public Validata(){
		rules=new Rules();
		messages=new Messages();
	}
	
	/***
	 * @param field 校验的字段
	 * @param rules 校验规则
	 * @param rulesValue 校验值
	 * @param rulesMessage 校验提示信息
	 * */
	public void add(String field,String rule,Object rulesValue,String rulesMessage){
		Map<String,Object> ruleMap = this.rules.get(field);
        Map<String,Object>  messagesMap = this.messages.get(field);
        if(ruleMap==null){
        	ruleMap = new HashMap<String, Object>();
        	messagesMap = new HashMap<String, Object>();
            this.rules.put(field, ruleMap);
            this.messages.put(field,messagesMap);
        }
        ruleMap.put(rule,rulesValue);
        messagesMap.put(rule,rulesMessage);
	}
	
	public Rules getRules() {
		return rules;
	}

	public void setRules(Rules rules) {
		this.rules = rules;
	}

	public Messages getMessages() {
		return messages;
	}

	public void setMessages(Messages messages) {
		this.messages = messages;
	}

	@Override
	public String toString(){
		return JSONObject.toJSONString(this);
	}
}
