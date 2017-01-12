package qing.yun.hui.common.struct.juhe.wechat.choiceness;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月13日上午11:09:49
 **/
@Getter
@Setter
public class PageData {
	
	private Integer totalPage; //总页数.
    private Integer ps; //每页返回条数，最大50，
    private Integer pno;//当前页数

}
