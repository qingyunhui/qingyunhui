package qing.yun.hui.common.struct.baidu.weather;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 城市相关天气预报...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月9日下午6:22:24
 **/
@SuppressWarnings("serial")
@Getter
@Setter
public class City implements Serializable{
	
	
	private String currentCity;//返回城市名 (当前城市)
	
	private String pm25;	//"123";
	
	private List<Index> indexs;
	
	private List<Weather> wealtherDates;
	
	
	/*{"weather_data":[
		{"wind":"东北风微风","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/duoyun.png","weather":"多云转阴",
		 "nightPictureUrl":"http://api.map.baidu.com/images/weather/night/yin.png","date":"周二 01月10日 (实时：6℃)","temperature":"9 ~ 5℃"
	    },
	    {"wind":"东风微风","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/xiaoyu.png","weather":"小雨",
	     "nightPictureUrl":"http://api.map.baidu.com/images/weather/night/xiaoyu.png","date":"周三","temperature":"8 ~ 6℃"
	    },
	    {"wind":"东北风微风","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/xiaoyu.png","weather":"小雨转阴",
	     "nightPictureUrl":"http://api.map.baidu.com/images/weather/night/yin.png","date":"周四","temperature":"7 ~ 3℃"
	    },
	    {"wind":"北风微风","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/duoyun.png","weather":"多云",
	     "nightPictureUrl":"http://api.map.baidu.com/images/weather/night/duoyun.png","date":"周五","temperature":"9 ~ 1℃"
	    }
	  ],
	"index":[
	  {"des":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。","title":"穿衣","zs":"较冷","tipt":"穿衣指数"},
	  {"des":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。","title":"洗车","zs":"较适宜","tipt":"洗车指数"},
	  {"des":"天气较好，同时又有微风伴您一路同行。虽会让人感觉有点凉，但仍适宜旅游，可不要错过机会呦！","title":"旅游","zs":"适宜","tipt":"旅游指数"},
	  {"des":"天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。","title":"感冒","zs":"较易发","tipt":"感冒指数"},
	  {"des":"天气较好，但考虑天气寒冷，推荐您进行各种室内运动，若在户外运动请注意保暖并做好准备活动。","title":"运动","zs":"较不宜","tipt":"运动指数"},
	  {"des":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。","title":"紫外线强度","zs":"最弱","tipt":"紫外线强度指数"}
	 ],
	"pm25":"119","currentCity":"嘉兴"
	}*/

}
