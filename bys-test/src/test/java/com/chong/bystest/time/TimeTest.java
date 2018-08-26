/**
 * 
 */
package com.chong.bystest.time;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

/**
 * @author newli
 * @ClassName: TimeTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2018年1月16日 上午10:05:56
 */
public class TimeTest {

	@Test
	public void getInterTime() {
		
		String webUrl0 = "http://www.169000.net/";// 169000.org
		String webUrl1 = "http://www.163.com/";// 百度
		String webUrl2 = "http://www.baidu.com";// 百度
		String webUrl3 = "http://www.taobao.com";// 淘宝
		String webUrl4 = "http://www.qq.com/";// 360
		String webUrl5 = "http://www.jd.com";// 360
		System.out.println(getWebsiteDatetime(webUrl3) + " [淘宝]");
		System.out.println(getWebsiteDatetime(webUrl1) + " [网易]");
		System.out.println(getWebsiteDatetime(webUrl2) + " [百度]");
//		System.out.println(getWebsiteDatetime(webUrl0) + " [169000]");
		System.out.println(getWebsiteDatetime(webUrl4) + " [QQ]");
		System.out.println(getWebsiteDatetime(webUrl5) + " [JD]");
	}

	/**
	 * 获取指定网站的日期时间
	 * 
	 * @param webUrl
	 * @return
	 * @author newli
	 * @date 2018年1月16日
	 */
	private static Long getWebsiteDatetime(String webUrl) {
		try {
			URL url = new URL(webUrl);// 取得资源对象
			URLConnection uc = url.openConnection();// 生成连接对象
			uc.connect();// 发出连接
			Long ld = uc.getDate();// 读取网站日期时间
			return ld;
//			Date date = new Date(ld);// 转换为标准时间对象
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// 输出北京时间
//			return sdf.format(date);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
