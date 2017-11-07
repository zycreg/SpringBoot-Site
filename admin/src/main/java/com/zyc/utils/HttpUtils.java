package com.zyc.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 
 * 
 * @author Jerry Zhang
 * @version 1.0.0
 * @email jerry.zhang@jamboree.com.cn 2011-8-22
 */
public class HttpUtils {

	public static final String POST = "POST";
	public static final String GET = "GET";
	public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";

	/**
	 * @param url
	 * @param xml
	 * @param method
	 * @param contentType
	 * @return
	 */
	public static String xmlHttpProxy(String url, String xml, String method,
			String contentType) {
		return xmlHttpProxy(url, xml, method, contentType, "utf-8");
	}
	
	/**
	 * @param url
	 * @param xml
	 * @param method
	 * @param contentType
	 * @param charset
	 * @return
	 */
	public static String xmlHttpProxy(String url, String xml, String method,
			String contentType, String charset) {
		InputStream is = null;
		OutputStream os = null;
		/** 接口调用成功标识 */
		try {
			if (StringUtils.isEmpty(method)) {
				method = GET;
			}
			if (StringUtils.isEmpty(contentType)) {
				contentType = CONTENT_TYPE;
			}
			URL _url = new URL(url);
			if ("https".equalsIgnoreCase(_url.getProtocol())) {
				ignoreSsl();
			}
			HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-type", contentType);
			conn.setRequestMethod(method);
			// conn.setRequestProperty("User-Agent",
			// "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
			// 接收参数
			if (!StringUtils.isEmpty(xml)) {
				os = conn.getOutputStream();
				os.write(xml.getBytes());
				os.flush();
			}else{
				conn.setRequestProperty("Content-Length", "0");
			}
			// 返回值
			is = conn.getInputStream();
			return getContent(is, charset);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * @param url
	 * @param xml
	 * @param method
	 * @param contentType
	 * @return
	 */
	public static String xmlSoapProxy(String url, String xml, String method,
			String contentType, String soapAction) {
		InputStream is = null;
		OutputStream os = null;
		/** 接口调用成功标识 */
		try {
			if (StringUtils.isEmpty(method)) {
				method = GET;
			}
			if (StringUtils.isEmpty(contentType)) {
				contentType = CONTENT_TYPE;
			}
			URL _url = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-type", contentType);
			// conn.setRequestProperty("Content-Type","text/xml; charset=utf-8");
			conn.setRequestProperty("SOAPAction", soapAction);//
			conn.setRequestMethod(method);
			// conn.setRequestProperty("User-Agent",
			// "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			// 接收参数
			if (!StringUtils.isEmpty(xml)) {
				os = conn.getOutputStream();
				os.write(xml.getBytes());
				os.flush();
			}
			// 返回值
			is = conn.getInputStream();
			return getContent(is, "utf-8");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 
	 * @param is
	 * @param charset
	 * @return
	 */
	public static String getContent(InputStream is, String charset) {
		String pageString = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuffer sb = null;
		try {
			isr = new InputStreamReader(is, charset);
			br = new BufferedReader(isr);
			sb = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			pageString = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (isr != null) {
					isr.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			sb = null;
		}
		return pageString;
	}

	/***
	 * 替换${*}为具体的值
	 * 
	 * @param sourceUrl
	 * @param paramMap
	 * @return
	 */
	public static String replaceURL(String sourceUrl, Map paramMap) {
		Iterator it = paramMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry en = (Entry) it.next();
			String key = en.getKey().toString();
			String value = en.getValue().toString();
			sourceUrl = sourceUrl.replaceAll("\\$\\{" + key + "\\}", value);
		}
		return sourceUrl;
	}

	private static void trustAllHttpsCertificates() throws Exception {
		TrustManager[] trustAllCerts = new TrustManager[1];
		TrustManager tm = new miTM();
		trustAllCerts[0] = tm;
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, null);
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	}

	static class miTM implements TrustManager, X509TrustManager {
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean isServerTrusted(X509Certificate[] certs) {
			return true;
		}

		public boolean isClientTrusted(X509Certificate[] certs) {
			return true;
		}

		public void checkServerTrusted(X509Certificate[] certs, String authType)
				throws CertificateException {
			return;
		}

		public void checkClientTrusted(X509Certificate[] certs, String authType)
				throws CertificateException {
			return;
		}
	}

	public static void ignoreSsl() throws Exception {
		HostnameVerifier hv = new HostnameVerifier() {
			public boolean verify(String urlHostName, SSLSession session) {
				// System.out.println("Warning: URL Host: " + urlHostName +
				// " vs. " + session.getPeerHost());
				return true;
			}
		};
		trustAllHttpsCertificates();
		HttpsURLConnection.setDefaultHostnameVerifier(hv);
	}

	/**
	 * 向客户端写入字符
	 * 
	 * @date 2012-6-6
	 * @param response
	 * @param msg
	 *            字符串,默认为utf-8编码
	 */
	public static void write2Client(HttpServletResponse response, String msg) {
		String charsetName = "utf-8";
		response.setCharacterEncoding(charsetName);
		response.setContentType("text/html;charset=" + charsetName);
		try {
			write2Client(response, msg.getBytes(charsetName));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向客户端输出2进制流，一般用来向客户端输出图像等信息。
	 * 
	 * @date 2012-6-6
	 * @param response
	 *            response对象
	 * @param bytes
	 *            2进制流。
	 */
	public static void write2Client(HttpServletResponse response, byte[] bytes) {
		if (bytes != null & bytes.length > 0) {
			try {
				OutputStream os = response.getOutputStream();
				os.write(bytes);
				os.flush();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
