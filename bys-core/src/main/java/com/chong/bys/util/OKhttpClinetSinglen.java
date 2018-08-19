package com.chong.bys.util;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * 构建okhttpclient单例对象
 *
 * @author lichong
 * 2018/2/3 下午3:59
 */
public class OKhttpClinetSinglen {
	
	private static  OkHttpClient okHttpClient;


	private OKhttpClinetSinglen(){}
	
	public static OkHttpClient getInstance() {
		if(okHttpClient==null) {
			synchronized(OKhttpClinetSinglen.class) {
				if(okHttpClient==null) {
					okHttpClient = new OkHttpClient.Builder()
							//连接超时时间
							.connectTimeout(10, TimeUnit.SECONDS)
							.build();
				}
			}
		}
		return okHttpClient;
	}

}
