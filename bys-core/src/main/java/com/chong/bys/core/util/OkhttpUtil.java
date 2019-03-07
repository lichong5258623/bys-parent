package com.chong.bys.core.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;

/**
 * @author lichong
 * @ClassName: OkhttpUtil
 * @Description: okhttp工具类
 * @date 2018年1月15日 下午5:09:40
 */
@Slf4j
public class OkhttpUtil {

    /**
     * httpPost请求，发送格式为application/json; charset=utf-8
     *
     * @param url     发送的url
     * @param content json格式的参数
     * @return Response  请求体
     *
     * @throws IOException
     */
    public static Response httpPost(String url, String content) throws IOException {
        // 创建okhttp对象
        OkHttpClient okHttpClient = OKhttpClinetSinglen.getInstance();

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        // 构建请求体
        RequestBody body = RequestBody.create(JSON, content);

        // 请求构建
        Request request = new Request.Builder().url(url).post(body).build();

        // 发起请求，获取响应体
        Response response = okHttpClient.newCall(request).execute();

        log.info("响应状态码：---------》》》》" + response.code());
        log.info("响应是否成功：---------》》》》" + response.isSuccessful());

        return response;
    }


    /**
     * GET请求
     *
     * @param url
     * @return 响应体
     */
    public static Response httpGet(String url) throws IOException {

        // 创建OkHttpClient对象
        OkHttpClient okHttpClient = OKhttpClinetSinglen.getInstance();

        // 构造请求
        Request request = new Request.Builder().url(url).build();

        // 发起请求获取响应
        Response response = okHttpClient.newCall(request).execute();
        log.info("请求是否成功------->>>" + response.isSuccessful());
        log.info("请求的状态码-------》》》" + response.code());
        return response;
    }

    /**
     * get请求方式
     *
     * @param url
     * @return 字符串
     * @throws IOException
     */
    public static String httpGetString(String url) throws IOException {

        log.info("**********响应字符串*********");
        return httpGet(url).body().string();
    }

    /**
     * httpPost请求，发送格式为application/json; charset=utf-8
     *
     * @param url
     * @param content
     * @return 字符串
     */
    public static String httpPostString(String url, String content) throws IOException {

        log.info("**********响应字符串*********");
        return httpPost(url, content).body().string();
    }

}
