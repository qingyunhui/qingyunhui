package qing.yun.hui.common.utils;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpClientUtils, 使用 HttpClient 4.x<br>
 * 升级为 4.x 是因为 3.x 多线程环境下设置超时时间会对全局造成影响.
 * 
 * @author dpslife
 */
public abstract class HttpClientUtils {
	
	private static final Logger log = LoggerFactory.getLogger(HttpClientUtils.class);

	private static HttpClient client = null;
	static {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(128);
		cm.setDefaultMaxPerRoute(128);
		client = HttpClients.custom().setConnectionManager(cm).build();
	}

	/**
	 * @deprecated 为了兼容旧代码所以存在此方法.<br>
	 *             1. contentType 此方法中使用了完整 contentType,比如[application/xml;
	 *             charset=GBK], 应该使用更标准的 mimeType 以及 charset<br>
	 *             2. HttpClient 超时设定使用的是 Int32 , 所以这里也不推荐再使用 Int64. <br>
	 */
	public static String post(String url, String body, String contentType,
			String encode, long readTimeout) throws ConnectTimeoutException,
			SocketTimeoutException, Exception {
		String mimeType = contentType;
		if (!StringUtil.isEmpty(contentType) && contentType.contains(";")) {
			mimeType = contentType.substring(0, contentType.indexOf(";"))
					.trim();
		}
		return post(url, body, mimeType, encode, null,
				Integer.valueOf(Long.valueOf(readTimeout).intValue()));
	}

	/**
	 * 发送一个 Post 请求, 使用指定的字符集编码.
	 * 
	 * @param url
	 * @param body
	 *            RequestBody
	 * @param mimeType
	 *            例如 application/xml, 默认: text/plain
	 * @param charset
	 *            编码
	 * @param connTimeout
	 *            建立链接超时时间,毫秒.
	 * @param readTimeout
	 *            响应超时时间,毫秒.
	 * @return ResponseBody, 使用指定的字符集编码.
	 * 
	 * @throws ConnectTimeoutException
	 *             建立链接超时异常
	 * @throws SocketTimeoutException
	 *             响应超时
	 * @throws Exception
	 */
	public static String post(String url, String body, String mimeType,
			String charset, Integer connTimeout, Integer readTimeout)
			throws ConnectTimeoutException, SocketTimeoutException, Exception {
		HttpClient client = null;
		HttpPost post = new HttpPost(url);
		String result = "";

		if (StringUtil.isEmpty(mimeType)) {
			mimeType = "text/plain";
		}

		try {
			if (!StringUtil.isEmpty(body)) {

				HttpEntity entity = new StringEntity(body, ContentType.create(
						mimeType, charset));
				post.setEntity(entity);
			}
			// 设置参数
			Builder customReqConf = RequestConfig.custom();
			if (connTimeout != null) {
				customReqConf.setConnectTimeout(connTimeout);
			}
			if (readTimeout != null) {
				customReqConf.setSocketTimeout(readTimeout);
			}
			post.setConfig(customReqConf.build());

			HttpResponse res;
			if (url.startsWith("https")) {
				// 执行 Https 请求.
				client = createSSLInsecureClient();
				res = client.execute(post);
			} else {
				// 执行 Http 请求.
				client = HttpClientUtils.client;
				res = client.execute(post);
			}
			result = IOUtils.toString(res.getEntity().getContent(), charset);
		} finally {
			post.releaseConnection();
			if (url.startsWith("https") && client != null
					&& client instanceof CloseableHttpClient) {
				((CloseableHttpClient) client).close();
			}
		}
		return result;
	}

	/**
	 * 提交form表单
	 * 
	 * @param url
	 * @param params
	 * @param headers
	 * @param connTimeout
	 * @param readTimeout
	 * @return
	 * @throws ConnectTimeoutException
	 * @throws SocketTimeoutException
	 * @throws Exception
	 */
	public static String postForm(String url, Map<String, String> params,
			Map<String, String> headers, Integer connTimeout,
			Integer readTimeout) throws ConnectTimeoutException,
			SocketTimeoutException, Exception {

		return postForm(url, params, headers, null, connTimeout, readTimeout);
	}

	/**
	 * 提交form表单，可以设置charset，如果charset为null，默认UTF-8
	 * 
	 * @param url
	 * @param params
	 * @param headers
	 * @param charset
	 * @param connTimeout
	 * @param readTimeout
	 * @return
	 * @throws ConnectTimeoutException
	 * @throws SocketTimeoutException
	 * @throws Exception
	 */
	public static String postForm(String url, Map<String, String> params,
			Map<String, String> headers, String charset, Integer connTimeout,
			Integer readTimeout) throws ConnectTimeoutException,
			SocketTimeoutException, Exception {

		HttpClient client = null;

		HttpPost post = new HttpPost(url);
		try {
			if (params != null && !params.isEmpty()) {
				List<NameValuePair> formParams = new ArrayList<org.apache.http.NameValuePair>();
				Set<Entry<String, String>> entrySet = params.entrySet();
				for (Entry<String, String> entry : entrySet) {
					formParams.add(new BasicNameValuePair(entry.getKey(), entry
							.getValue()));
				}
				UrlEncodedFormEntity entity = null;
				if (!StringUtil.isEmpty(charset)) {
					entity = new UrlEncodedFormEntity(formParams,
							Charset.forName(charset.toUpperCase()));
				} else {
					entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
				}
				post.setEntity(entity);
			}
			if (headers != null && !headers.isEmpty()) {
				for (Entry<String, String> entry : headers.entrySet()) {
					post.addHeader(entry.getKey(), entry.getValue());
				}
			}
			// 设置参数
			Builder customReqConf = RequestConfig.custom();
			if (connTimeout != null) {
				customReqConf.setConnectTimeout(connTimeout);
			}
			if (readTimeout != null) {
				customReqConf.setSocketTimeout(readTimeout);
			}
			post.setConfig(customReqConf.build());
			HttpResponse res = null;
			if (url.startsWith("https")) {
				// 执行 Https 请求.
				client = createSSLInsecureClient();
				res = client.execute(post);
			} else {
				// 执行 Http 请求.
				client = HttpClientUtils.client;
				res = client.execute(post);
			}
			HttpEntity resEntity = res.getEntity();
			if (resEntity != null) {
				if (!StringUtil.isEmpty(charset)) {
					return IOUtils.toString(res.getEntity().getContent(),
							charset.toUpperCase());
				} else {
					return IOUtils.toString(res.getEntity().getContent(),
							"UTF-8");
				}
			} else {
				return null;
			}
		} finally {
			post.releaseConnection();
			if (url.startsWith("https") && client != null
					&& client instanceof CloseableHttpClient) {
				((CloseableHttpClient) client).close();
			}
		}
	}

	/**
	 * 发送一个 GET 请求
	 * 
	 * @param url
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public static String get(String url, String charset) throws Exception {
		return get(url, charset, null, null);
	}

	/**
	 * 发送一个 GET 请求
	 * 
	 * @param url
	 * @param charset
	 * @param connTimeout
	 *            建立链接超时时间,毫秒.
	 * @param readTimeout
	 *            响应超时时间,毫秒.
	 * @return
	 * @throws ConnectTimeoutException
	 *             建立链接超时
	 * @throws SocketTimeoutException
	 *             响应超时
	 * @throws Exception
	 */
	public static String get(String url, String charset, Integer connTimeout,
			Integer readTimeout) throws ConnectTimeoutException,
			SocketTimeoutException, Exception {
		HttpClient client = null;

		HttpGet get = new HttpGet(url);
		String result = "";
		try {
			// 设置参数
			Builder customReqConf = RequestConfig.custom();
			if (connTimeout != null) {
				customReqConf.setConnectTimeout(connTimeout);
			}
			if (readTimeout != null) {
				customReqConf.setSocketTimeout(readTimeout);
			}
			get.setConfig(customReqConf.build());

			HttpResponse res = null;

			if (url.startsWith("https")) {
				// 执行 Https 请求.
				client = createSSLInsecureClient();
				res = client.execute(get);
			} else {
				// 执行 Http 请求.
				client = HttpClientUtils.client;
				res = client.execute(get);
			}

			result = IOUtils.toString(res.getEntity().getContent(), charset);
		} finally {
			get.releaseConnection();
			if (url.startsWith("https") && client != null
					&& client instanceof CloseableHttpClient) {
				((CloseableHttpClient) client).close();
			}
		}
		return result;
	}

	/**
	 * 从 response 里获取 charset
	 * 
	 * @param ressponse
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String getCharsetFromResponse(HttpResponse ressponse) {
		// Content-Type:text/html; charset=GBK
		if (ressponse.getEntity() != null
				&& ressponse.getEntity().getContentType() != null
				&& ressponse.getEntity().getContentType().getValue() != null) {
			String contentType = ressponse.getEntity().getContentType()
					.getValue();
			if (contentType.contains("charset=")) {
				return contentType
						.substring(contentType.indexOf("charset=") + 8);
			}
		}
		return null;
	}

	public static void main(String[] args) {
		try {
			for (int i = 0; i < 10; i++) {
				Task t = new Task();
				t.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static class Task extends Thread {
		@Override
		public void run() {
			try {
				get("http://localhost:8080/movie/job/", "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static CloseableHttpClient createSSLInsecureClient()
			throws GeneralSecurityException {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
					null, new TrustStrategy() {
						public boolean isTrusted(X509Certificate[] chain,
								String authType) throws CertificateException {
							return true;
						}
					}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslContext, new X509HostnameVerifier() {

						public boolean verify(String arg0, SSLSession arg1) {
							return true;
						}

						public void verify(String host, SSLSocket ssl)
								throws IOException {
						}

						public void verify(String host, X509Certificate cert)
								throws SSLException {
						}

						public void verify(String host, String[] cns,
								String[] subjectAlts) throws SSLException {
						}

					});
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (GeneralSecurityException e) {
			throw e;
		}
	}
}
