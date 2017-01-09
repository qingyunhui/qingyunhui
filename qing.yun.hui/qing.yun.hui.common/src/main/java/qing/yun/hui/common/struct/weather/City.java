package qing.yun.hui.common.struct.weather;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月9日下午6:22:24
 **/
@Getter
@Setter
public class City {
	
	private String currentCity;//"嘉兴"
	
	private String pm25;	//"123";
	
	private List<Index> index;
	
	private List<WeatherData> weather_data;
	
	
	/*{"error":0,"status":"success","date":"2017-01-09",
	"results":[
	{"currentCity":"嘉兴","pm25":"123",
		 "index":[{"title":"穿衣","zs":"较冷","tipt":"穿衣指数","des":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"},
		          {"title":"洗车","zs":"较适宜","tipt":"洗车指数","des":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},
		          {"title":"旅游","zs":"一般","tipt":"旅游指数","des":"天气较好，风稍大，体感稍凉，旅游指数一般，外出请注意防风御寒。"},
		          {"title":"感冒","zs":"较易发","tipt":"感冒指数","des":"天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。"},
		          {"title":"运动","zs":"较不宜","tipt":"运动指数","des":"阴天，且天气寒冷，推荐您在室内进行低强度运动；若坚持户外运动，请选择合适的运动并注意保暖。"},
		          {"title":"紫外线强度","zs":"最弱","tipt":"紫外线强度指数","des":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"
		         }],
		  "weather_data":[{"date":"周一 01月09日 (实时：6℃)","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/yin.png",
			  			   "nightPictureUrl":"http://api.map.baidu.com/images/weather/night/duoyun.png","weather":"阴转多云",
			  			   "wind":"北风3-4级","temperature":"9 ~ 3℃"
			  			   },
			  			   {"date":"周二","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/duoyun.png",
			  			    "nightPictureUrl":"http://api.map.baidu.com/images/weather/night/yin.png",
			  			    "weather":"多云转阴","wind":"东北风微风","temperature":"10 ~ 5℃"
			  			    },
			  			    {"date":"周三","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/xiaoyu.png",
			  			     "nightPictureUrl":"http://api.map.baidu.com/images/weather/night/xiaoyu.png","weather":"小雨",
			  			     "wind":"东风微风","temperature":"9 ~ 5℃"
			  			     },
			  			     {"date":"周四","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/xiaoyu.png",
			  			      "nightPictureUrl":"http://api.map.baidu.com/images/weather/night/yin.png","weather":"小雨转阴",
			  			       "wind":"东北风微风","temperature":"8 ~ 4℃"
			  			      }
			  			     ]
		  			   }]
		  			}
	*/

}
