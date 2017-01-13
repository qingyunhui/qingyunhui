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
		
		HTTP_URL(0,"http://apis.juhe.cn/mobile/get","接口请求地址"),//接口请求地址
		APP_KEY(1,"386497698e93fa097d9391f5d0153f9d","应用APPKEY");//应用APPKEY
		
		private final int value;
		private final String code;
	    private final String name;
	    
	    private Moble(int value, String code,String name) {
	        this.value = value;
	        this.code=code;
	        this.name = name;
	    }
	}
	
	@Getter
	public enum Idcard implements ICommonEnum{
		HTTP_URL(0,"http://apis.juhe.cn/idcard/index","接口请求地址"),//接口请求地址
		APP_KEY(1,"99838ed63ac14404b2c90b6435ef7b50","应用APPKEY");//应用APPKEY
		
		private final int value;
		private final String code;
	    private final String name;
	    
	    private Idcard(int value, String code,String name) {
	        this.value = value;
	        this.code=code;
	        this.name = name;
	    }
	}
	
	@Getter
	public enum NewsTop implements ICommonEnum{
		HTTP_URL(0,"http://v.juhe.cn/toutiao/index","接口请求地址"),//接口请求地址
		APP_KEY(1,"d729a205c1b25536c3af3629d37adb8a","应用APPKEY");//应用APPKEY
		
		private final int value;
		private final String code;
	    private final String name;
	    
	    private NewsTop(int value, String code,String name) {
	        this.value = value;
	        this.code=code;
	        this.name = name;
	    }
	}
	@Getter
	public enum NewsTopType implements ICommonEnum{
		//新闻头条类型，
		TOP(0,"top","头条"),
		SHEHUI(1,"shehui","社会"),
		GUONEI(2,"guonei","国内"),
		GUOJI(3,"guoji","国际"),
		YULE(4,"yule","娱乐"),
		TIYU(5,"tiyu","体育"),
		KEJI(6,"keji","科技"),
		JUNSHI(7,"junshi","军事"),
		CAIJING(8,"caijing","财经"),
		SHISHANG(9,"shishang","时尚");
		
		private final int value;
		private final String code;
	    private final String name;
	    
	    private NewsTopType(int value, String code,String name) {
	        this.value = value;
	        this.code=code;
	        this.name = name;
	    }
	}
	
	@Getter
	public enum BusLong implements ICommonEnum{
		HTTP_URL(0,"http://op.juhe.cn/onebox/bus/query","接口请求地址"),//接口请求地址
		APP_KEY(1,"7b38b91e69a83706de90ed154c01e0cd","应用APPKEY");//应用APPKEY
		
		private final int value;
		private final String code;
	    private final String name;
	    
	    private BusLong(int value, String code,String name) {
	        this.value = value;
	        this.code=code;
	        this.name = name;
	    }
	}
	
	@Getter
	public enum Busline implements ICommonEnum{
		HTTP_URL(0,"http://op.juhe.cn/189/bus/busline","接口请求地址"),//接口请求地址
		APP_KEY(1,"b895aabaa0f19ff0244602d1d2f371d8","应用APPKEY");//应用APPKEY
		
		private final int value;
		private final String code;
	    private final String name;
	    
	    private Busline(int value, String code,String name) {
	        this.value = value;
	        this.code=code;
	        this.name = name;
	    }
	}
	
	@Getter
	public enum Robot implements ICommonEnum{
		HTTP_URL(0,"http://op.juhe.cn/robot/index","接口请求地址"),//接口请求地址
		APP_KEY(1,"cb026fcfc5ab6a53bd6d539bafb50601","应用APPKEY");//应用APPKEY
		
		private final int value;
		private final String code;
	    private final String name;
	    
	    private Robot(int value, String code,String name) {
	        this.value = value;
	        this.code=code;
	        this.name = name;
	    }
	}
	
	@Getter
	public enum H5FileTicket implements ICommonEnum{
		HTTP_URL(0,"http://v.juhe.cn/wepiao/query","接口请求地址"),//接口请求地址
		APP_KEY(1,"8e695f0e8a57e2c13e66639854cd4024","应用APPKEY");//应用APPKEY
		
		private final int value;
		private final String code;
	    private final String name;
	    
	    private H5FileTicket(int value, String code,String name) {
	        this.value = value;
	        this.code=code;
	        this.name = name;
	    }
	}
	
	@Getter
	public enum CallerIDTelephone implements ICommonEnum{
		HTTP_URL(0,"http://op.juhe.cn/onebox/phone/query","接口请求地址"),//接口请求地址
		APP_KEY(1,"2e492b8a7e753cf4c20d0c1eaaa0cc42","应用APPKEY");//应用APPKEY
		
		private final int value;
		private final String code;
	    private final String name;
	    
	    private CallerIDTelephone(int value, String code,String name) {
	        this.value = value;
	        this.code=code;
	        this.name = name;
	    }
	}
	
	@Getter
	public enum FootballLeague implements ICommonEnum{
		HTTP_URL(0,"http://op.juhe.cn/onebox/football/league","接口请求地址"),//接口请求地址
		APP_KEY(1,"24e73160ab2784393659fd164667e22d","应用APPKEY");//应用APPKEY
		
		private final int value;
		private final String code;
	    private final String name;
	    
	    private FootballLeague(int value, String code,String name) {
	        this.value = value;
	        this.code=code;
	        this.name = name;
	    }
	}
	
	@Getter
	public enum JokeDaquan implements ICommonEnum{
		HTTP_URL(0,"http://japi.juhe.cn/joke/content/list.from","接口请求地址"),//接口请求地址
		APP_KEY(1,"22c487d591261f7841dc3d115a206ac1","应用APPKEY");//应用APPKEY
		
		private final int value;
		private final String code;
	    private final String name;
	    
	    private JokeDaquan(int value, String code,String name) {
	        this.value = value;
	        this.code=code;
	        this.name = name;
	    }
	}
	
	@Getter
	public enum WechatChoiceness implements ICommonEnum{
		HTTP_URL(0,"http://v.juhe.cn/weixin/query","接口请求地址"),//接口请求地址
		APP_KEY(1,"0fbc40e9e6b62c0728e27bcde2aa752c","应用APPKEY");//应用APPKEY
		
		private final int value;
		private final String code;
	    private final String name;
	    
	    private WechatChoiceness(int value, String code,String name) {
	        this.value = value;
	        this.code=code;
	        this.name = name;
	    }
	}
	
	@Getter
	public enum Stock implements ICommonEnum{
		//HTTP_URL_HS(0,"http://web.juhe.cn:8080/finance/stock/hs","hs","沪深股市【深圳，上海】"),
		//HTTP_URL_HK(1,"http://web.juhe.cn:8080/finance/stock/hk","hk","香港股市【香港】"),
		//HTTP_URL_USA(2,"http://web.juhe.cn:8080/finance/stock/usa","usa","美国股市【美国】"),
		//http://web.juhe.cn:8080/finance/stock/hs?gid=sh601009&key=您申请的APPKEY //沪深股市
		//http://web.juhe.cn:8080/finance/stock/hk?num=00001&key=您申请的APPKEY   //香港股市
		//http://web.juhe.cn:8080/finance/stock/usa?gid=aapl&key=您申请的APPKEY   //美国股市
		HTTP_URL(0,"http://web.juhe.cn:8080/finance/stock/","接口url"),//这里须要注意，根据不同类型，请自己手动拼接
		APP_KEY(3,"620ce93056969a5d44191f1a3d1fc951","应用APPKEY");//应用APPKEY
		private final int value;
		private final String code;
	    private final String name;
	    
	    private Stock(int value, String code,String name) {
	        this.value = value;
	        this.code=code;
	        this.name = name;
	    }
	}
	
	@Getter
	public enum StockShort{
		//股市简称
		HS("hs","gid","沪深股市【深圳，上海】"),
		HK("hk","num","香港股市【香港】"),
		USA("usa","gid","美国股市【美国】");
		
		private final String code;//请求编码（接口地址url的后缀，须要手动拼接）
		private final String parameter;//参数（接口址址url须要携带的参数，须要手动拼接）
	    private final String name;//说明
	    
	    private StockShort(String code,String parameter,String name) {
	        this.code=code;
	        this.parameter=parameter;
	        this.name = name;
	    }
	}
	
}
