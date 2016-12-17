package qing.yun.hui.common.utils;
/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月17日下午1:33:22
 **/
public class WebUtil {
	/**
	 * 获取总页数
	 * @param totalCount 总记条数
	 * @param defaultCount 默认一次性处理defaultCount条记录
	 * @return 总页数
	 * */
	public static int getTotalPageCount(int totalCount,int defaultCount){
		int totalSize=totalCount;
		int mod=-1;
		int pageCount=0;
		mod = totalSize % defaultCount;
		if (mod != 0) {
			pageCount = (totalSize / defaultCount) + 1;
		} else {
			pageCount = (totalSize / defaultCount);
		}
		return pageCount;
	}
}
