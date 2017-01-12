package qing.yun.hui.common.struct.juhe.news;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月12日下午4:17:10
 **/
@Getter
@Setter
public class NewsTopData {

	/**唯一标识，key*/
	private String uniquekey;
	
	/**标题*/
	private String title;
	
	/**日期时间*/
	private String date;
	
	/**分类*/
	private String category;
	
	/**出版名称*/
	private String author_name;
	
	/**连接url*/
	private String url;
	
	/**短文，头条图*/
	private String thumbnail_pic_s;
	
	/**短文，头条图2*/
	private String thumbnail_pic_s02;
	
	/**短文，头条图3*/
	private String thumbnail_pic_s03;
	
}
