package qing.yun.hui.common.struct.juhe;

import qing.yun.hui.common.enums.ICommonEnum;
import lombok.Getter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月12日下午2:24:18
 **/
public class JuheEnum {
	
	@Getter
	public enum Moble implements ICommonEnum{
		
		HTTP_URL(0,"http://apis.juhe.cn/mobile/get"),//接口请求地址
		APP_KEY(1,"386497698e93fa097d9391f5d0153f9d");//应用APPKEY
		
		private final int value;
	    private final String name;
	    
	    private Moble(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }

		public String getCode() {
			return String.valueOf(value);
		}
	}
	
	@Getter
	public enum Idcard implements ICommonEnum{
		HTTP_URL(0,"http://apis.juhe.cn/idcard/index"),//接口请求地址
		APP_KEY(1,"99838ed63ac14404b2c90b6435ef7b50");//应用APPKEY
		
		private final int value;
	    private final String name;
	    
	    private Idcard(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }
		public String getCode() {
			return String.valueOf(value);
		}
	}
	
	@Getter
	public enum NewsTop implements ICommonEnum{
		HTTP_URL(0,"http://v.juhe.cn/toutiao/index"),//接口请求地址
		APP_KEY(1,"d729a205c1b25536c3af3629d37adb8a");//应用APPKEY
		
		private final int value;
	    private final String name;
	    
	    private NewsTop(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }
		public String getCode() {
			return String.valueOf(value);
		}
	}
	
	@Getter
	public enum BusLong implements ICommonEnum{
		HTTP_URL(0,"http://op.juhe.cn/onebox/bus/query"),//接口请求地址
		APP_KEY(1,"7b38b91e69a83706de90ed154c01e0cd");//应用APPKEY
		
		private final int value;
	    private final String name;
	    
	    private BusLong(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }
		public String getCode() {
			return String.valueOf(value);
		}
	}
	
	@Getter
	public enum Robot implements ICommonEnum{
		HTTP_URL(0,"http://op.juhe.cn/robot/index"),//接口请求地址
		APP_KEY(1,"cb026fcfc5ab6a53bd6d539bafb50601");//应用APPKEY
		
		private final int value;
	    private final String name;
	    
	    private Robot(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }
		public String getCode() {
			return String.valueOf(value);
		}
	}
	
	@Getter
	public enum H5FileTicket implements ICommonEnum{
		HTTP_URL(0,"http://v.juhe.cn/wepiao/query"),//接口请求地址
		APP_KEY(1,"8e695f0e8a57e2c13e66639854cd4024");//应用APPKEY
		
		private final int value;
	    private final String name;
	    
	    private H5FileTicket(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }
		public String getCode() {
			return String.valueOf(value);
		}
	}
	
	@Getter
	public enum CallerIDTelephone implements ICommonEnum{
		HTTP_URL(0,"http://op.juhe.cn/onebox/phone/query"),//接口请求地址
		APP_KEY(1,"2e492b8a7e753cf4c20d0c1eaaa0cc42");//应用APPKEY
		
		private final int value;
	    private final String name;
	    
	    private CallerIDTelephone(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }
		public String getCode() {
			return String.valueOf(value);
		}
	}
	
	@Getter
	public enum FootballLeague implements ICommonEnum{
		HTTP_URL(0,"http://op.juhe.cn/onebox/football/league"),//接口请求地址
		APP_KEY(1,"24e73160ab2784393659fd164667e22d");//应用APPKEY
		
		private final int value;
	    private final String name;
	    
	    private FootballLeague(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }
		public String getCode() {
			return String.valueOf(value);
		}
	}
	
	@Getter
	public enum JokeDaquan implements ICommonEnum{
		HTTP_URL(0,"http://japi.juhe.cn/joke/content/list.from"),//接口请求地址
		APP_KEY(1,"22c487d591261f7841dc3d115a206ac1");//应用APPKEY
		
		private final int value;
	    private final String name;
	    
	    private JokeDaquan(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }
		public String getCode() {
			return String.valueOf(value);
		}
	}
	
	@Getter
	public enum WechatChoiceness implements ICommonEnum{
		HTTP_URL(0,"http://v.juhe.cn/weixin/query"),//接口请求地址
		APP_KEY(1,"0fbc40e9e6b62c0728e27bcde2aa752c");//应用APPKEY
		
		private final int value;
	    private final String name;
	    
	    private WechatChoiceness(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }
		public String getCode() {
			return String.valueOf(value);
		}
	}
	
}
