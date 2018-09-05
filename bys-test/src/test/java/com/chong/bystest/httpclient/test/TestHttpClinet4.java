package com.chong.bystest.httpclient.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

@Slf4j
public class TestHttpClinet4 {

	@Test
	public void test1() {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://localhost:8090/login?id=304");
		try {
			CloseableHttpResponse response  = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			String s = EntityUtils.toString(entity);
			log.info(s);

			CloseableHttpResponse execute = httpClient.execute(httpGet);
			String s1 = EntityUtils.toString(execute.getEntity());
			log.info(s1);
			response.close();
			httpClient.close();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
