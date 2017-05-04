package cn.com.rocketmq.producer.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;


/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月4日上午9:51:27
 **/

public class BeanUtil {
	
	private static ServletRequestAttributes getRequestAttr() {
		return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	}

	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttr= getRequestAttr();
		if(null==requestAttr) return null;
		return requestAttr.getRequest();
	}

	public static HttpServletResponse getResponse() {
		ServletRequestAttributes requestAttr= getRequestAttr();
		if(null==requestAttr) return null;
		return requestAttr.getResponse();
	}
	
	public static <T> T getBeanOfType(Class<T> clazz) {
		return getApplication().getBean(clazz);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T) getApplication().getBean(name);
	}

	private static WebApplicationContext getApplication() {
		ServletContext servletContext = BeanUtil.getSession().getServletContext();
		return WebApplicationContextUtils.getWebApplicationContext(servletContext);
	}
	
}
