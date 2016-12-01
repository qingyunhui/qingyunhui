package qing.yun.hui.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qing.yun.hui.common.constants.Constant;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月22日下午10:22:52
 **/
public class CookieUtil {
	
	public static void main(String[] args){
		
		
		
	}
	
	/**
	 * <p>根据给定name获取Cookie的value</p>
	 * @param request
	 * @param name 待查询cookie的name
	 * @return String 
	 * */
	public static String getCookieValueByName(HttpServletRequest request, String name) {
		if(StringUtil.isEmpty(name)) return null;
		Cookie cookie=getCookieByName(request, name);
		if(null==cookie) return null;
		try {
			return URLDecoder.decode(cookie.getValue(), Constant.UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/***
	 * <p>根据给定name获取Cookie</p>
	 * @param request
	 * @param name 待查询cookie的name
	 * @return Cookie
	 * */
	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		if(StringUtil.isEmpty(name)) return null;
		Cookie[] cookies = request.getCookies();
		if (cookies == null)  return null;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name)) {
				return cookie;
			}
		}
		return null;
	}
	
	/**
	 * <p>根据给定参数值设置cookie</p>
	 * @param request
	 * @param response
	 * @param name 非空
	 * @param value 非空
	 * @param domain cookie的有效域(非空)
	 * @param path cookie的有效路径
	 * @param maxAge cookie的有效期(单位秒)
	 * */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value,String domain, String path, int maxAge) {
		if(StringUtil.isEmpty(name,value,domain)) return;
		String cookieValue = "";
		try {
			cookieValue = URLEncoder.encode(value, Constant.UTF_8);//中文进行转码
		}
		catch (UnsupportedEncodingException e) {
			System.out.println("=====>"+e);
		}
		Cookie cookie = new Cookie(name, value == null ? "" : cookieValue.replaceAll("\r\n", ""));
		cookie.setMaxAge(maxAge);
		cookie.setDomain(domain);
		// 如果Cookie的Path为空,则设置路径为当前项目的contextPath
		if (StringUtil.isEmpty(path)) {
			cookie.setPath(getPath(request));
		} else {
			cookie.setPath(path);
		}
		response.addCookie(cookie);
	}
	
	/**
	 * <p>根据给定参数值设置cookie，且cookie的有效期默认为最大值(2的31次方-1)。</p>
	 * @param request
	 * @param response
	 * @param name 非空
	 * @param value 非空
	 * @param domain cookie的有效域(非空)
	 * @param path cookie的有效路径
	 * */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value,String domain, String path) {
		setCookie(request, response, name, value, domain, path, Integer.MAX_VALUE);
	}
	
	/**
	 * 删除cookie
	 * @param request
	 * @param response
	 * @param cookie
	 * @param domain
	 * @param path
	 */
	public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, Cookie cookie,String domain, String path) {
		if (cookie != null) {
			cookie.setDomain(domain);
			if (StringUtil.isEmpty(path)) {
				cookie.setPath(getPath(request));
			} else {
				cookie.setPath(path);
			}
			cookie.setValue("");
			cookie.setMaxAge(0);//将cookie的有效期设置为0，命令浏览器删除该cookie
			response.addCookie(cookie);
		}
	}
	
	/**
	 * <p>获取cookie的有效路径</p>
	 * @param request
	 * @return String
	 * */
	protected static String getPath(HttpServletRequest request) {
		String path = request.getContextPath();
		return (StringUtil.isEmpty(path)?"/":path);
	}
}
