package qing.yun.hui.common.struct.juhe.news;

import java.util.List;

import qing.yun.hui.common.struct.juhe.JuheResponse;
import lombok.Getter;
import lombok.Setter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月12日下午3:51:35
 **/
@Getter
@Setter
public class NewsTopResponse extends JuheResponse{
	
	List<NewsTopData> data;//因为该接口，返回的是一个list集合..
	
	//=============----=================
 	
	/** 返回结果集*/
 	private String result	;

	/*{
	    "reason":"成功的返回",
	    	"result":{"stat":"1",
	    		"data":[
	    				{"uniquekey":"88b56c2b263ed236c74e81bcfdcca570","title":"2017购票日历+春运火车票全攻略来了 准备好抢票了吗？","date":"2017-01-12 15:46",
	    				 "category":"头条","author_name":"人民日报","url":"http:\/\/mini.eastday.com\/mobile\/170112154658998.html",
	    				 "thumbnail_pic_s":"http:\/\/07.imgmini.eastday.com\/mobile\/20170112\/20170112154658_68437abb2328ba56768bf7c42a27780e_1_mwpm_03200403.jpeg",
	    				 "thumbnail_pic_s02":"http:\/\/07.imgmini.eastday.com\/mobile\/20170112\/20170112154658_52aa02fc74428953cd9a0c8807fcd779_2_mwpm_03200403.jpeg",
	    				 "thumbnail_pic_s03":"http:\/\/07.imgmini.eastday.com\/mobile\/20170112\/20170112154658_00aa34665da7d8b04ce8894c52fa6989_3_mwpm_03200403.jpeg"
	    				 },
	    				 {"uniquekey":"2ded3415b15f55d782568ac7df7c33e7","title":"首次记者会成“论战”：特朗普与记者严词交锋","date":"2017-01-12 13:22","category":"头条",
	    				     "author_name":"新华社客户端","url":"http:\/\/mini.eastday.com\/mobile\/170112132226943.html",
	    				     "thumbnail_pic_s":"http:\/\/08.imgmini.eastday.com\/mobile\/20170112\/20170112132226_7cf262a2ca512c57e15ca65ea12e2518_1_mwpm_03200403.jpeg",
	    				     "thumbnail_pic_s02":"http:\/\/08.imgmini.eastday.com\/mobile\/20170112\/20170112132226_7cf262a2ca512c57e15ca65ea12e2518_2_mwpm_03200403.jpeg",
	    				     "thumbnail_pic_s03":"http:\/\/08.imgmini.eastday.com\/mobile\/20170112\/20170112132226_7cf262a2ca512c57e15ca65ea12e2518_3_mwpm_03200403.jpeg"
	    				  }
	    				]
	    			  },"error_code":0
	    }
	  */
}
