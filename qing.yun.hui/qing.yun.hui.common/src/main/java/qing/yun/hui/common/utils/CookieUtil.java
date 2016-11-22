package qing.yun.hui.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月22日下午10:22:52
 **/
public class CookieUtil {
	
	/**
	 * <p>根据给定key从request中查找对应的cookie</p>
	 * @param request
	 * @param key
	 * @return String
	 * */
	public static String getCookieByKey(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(key)) {
				return cookie.getValue();
			}
		}
		return null;
	}
}
